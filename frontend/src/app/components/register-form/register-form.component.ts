import {CommonModule} from '@angular/common';
import {Component} from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule, ValidationErrors,
  Validators,
} from '@angular/forms';

import {Router, RouterModule} from '@angular/router';

import {NgxMaskDirective, NgxMaskPipe} from 'ngx-mask';
import {ToastrService} from 'ngx-toastr';
import {ApiUserService} from "../../services/api-user.service";
import {Usuario} from "../../models/user.model";

@Component({
  selector: 'app-register-form',
  standalone: true,
  imports: [
    RouterModule,
    NgxMaskDirective,
    NgxMaskPipe,
    ReactiveFormsModule,
    CommonModule,
  ],

  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css'],
})
export class RegisterFormComponent {
  registerForm: FormGroup;
  errorMessage: any;
  showPassword = false;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private apiService: ApiUserService,
    private toastr: ToastrService
  ) {
    this.registerForm = this.fb.group({
      nome: ['', [Validators.required]],
      email: ['', [Validators.required, this.validateEmail]],
      senha: [
        '',
        [
          Validators.required,
          Validators.minLength(12), // Validação de no mínimo 12 caracteres
          this.passwordValidator,
        ],
      ],
      confirmarSenha: ['', Validators.required],
    }, {validators: this.passwordMatchValidator});
  }

  // Função de validação personalizada para e-mail
  validateEmail(control: AbstractControl): ValidationErrors | null {
    const email = control.value;
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (email && !emailPattern.test(email)) {
      return {invalidEmail: true};
    }
    return null;
  }

  // Validação personalizada da senha
  passwordValidator(control: AbstractControl): ValidationErrors | null {
    const password = control.value;
    const hasUpperCase = /[A-Z]/.test(password);
    const hasLowerCase = /[a-z]/.test(password);
    const hasNumeric = /\d/.test(password);
    const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);
    const isValid =
      password &&
      password.length >= 12 && // Confere se a senha tem no mínimo 12 caracteres
      hasUpperCase &&
      hasLowerCase &&
      hasNumeric &&
      hasSpecialChar;

    if (!isValid) {
      return {invalidPassword: true};
    }
    return null;
  }

  // Validação para confirmar que as senhas são iguais
  passwordMatchValidator(form: AbstractControl): ValidationErrors | null {
    const senha = form.get('senha')?.value;
    const confirmarSenha = form.get('confirmarSenha')?.value;
    return senha === confirmarSenha ? null : {passwordMismatch: true};
  }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  register() {
    if (this.registerForm.valid) {
      const usuarioData: Usuario = this.registerForm.value;

      this.apiService.registerUser(usuarioData).subscribe({
        next: (response) => {
          this.toastr.success('Usuário registrado com sucesso!', '', {
            positionClass: 'toast-top-right',
            progressBar: true,
            progressAnimation: 'increasing',
            timeOut: 2000,
          });
          setTimeout(() => {
            this.router.navigate(['/dashboard/notifications']);
          }, 2500);

          const usuarioComId: Usuario = {
            ...usuarioData,
            id_usuario: response.id_usuario,
          };

          const loginCredentials = {
            email: usuarioData.email,
            senha: usuarioData.senha
          }

          this.apiService.loginUser(loginCredentials).subscribe(
            (response) => {
              console.log('Login bem-sucedido.');
              // Armazenando o id do usuário e o token no localStorage
              if (response.id_usuario && response.token) {
                localStorage.setItem('userId', response.id_usuario);
                localStorage.setItem('authToken', response.token);
              }
              // Navega para o dashboard ou outra página após o login
              setTimeout(() => {
                this.router.navigate(['/dashboard/notifications']);
              }, 2500);
            },
            (error) => {
              console.error('Erro ao fazer login:', error);
            }
          );
        },
        error: (error) => {
          this.errorMessage = error.message;
          this.toastr.error(error.message);
        },
      });
    } else {
      this.registerForm.markAllAsTouched();
      console.log('Formulário inválido:', this.registerForm.errors);
    }
  }
}
