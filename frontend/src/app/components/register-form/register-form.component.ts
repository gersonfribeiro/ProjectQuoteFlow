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
      senha: ['', [Validators.required, Validators.minLength(12)]]
    });

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

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  generatePassword(): void {
    const upperCaseChars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    const lowerCaseChars = 'abcdefghijklmnopqrstuvwxyz';
    const numericChars = '0123456789';
    const specialChars = '!@#$%^&*(),.?":{}|<>';
    const allChars = upperCaseChars + lowerCaseChars + numericChars + specialChars;

    let password = '';

    // Garante que pelo menos um caractere de cada tipo seja adicionado
    password += upperCaseChars.charAt(Math.floor(Math.random() * upperCaseChars.length));
    password += lowerCaseChars.charAt(Math.floor(Math.random() * lowerCaseChars.length));
    password += numericChars.charAt(Math.floor(Math.random() * numericChars.length));
    password += specialChars.charAt(Math.floor(Math.random() * specialChars.length));

    // Completa o restante da senha até alcançar 12 caracteres
    for (let i = 4; i < 12; i++) {
      password += allChars.charAt(Math.floor(Math.random() * allChars.length));
    }

    // Embaralha os caracteres para evitar previsibilidade
    password = this.shuffleString(password);

    // Preenche o campo de senha no formulário
    this.registerForm.get('senha')?.setValue(password);

    this.toastr.success("Senha forte e aleatória gerada com sucesso");
  }

  // Função auxiliar para embaralhar a string
  shuffleString(str: string): string {
    return str.split('').sort(() => Math.random() - 0.5).join('');
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
