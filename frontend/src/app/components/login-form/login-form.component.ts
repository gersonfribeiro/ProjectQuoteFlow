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
import {ApiUserService} from "../../services/api-user.service";
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [
    RouterModule,
    NgxMaskDirective,
    NgxMaskPipe,
    ReactiveFormsModule,
    CommonModule,
  ],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.css',
})
export class LoginFormComponent {
  loginForm: FormGroup;
  errorMessage: string | null = null;
  showPassword = false; // Controle de visibilidade da senha

  constructor(private fb: FormBuilder, private router: Router, private apiService: ApiUserService, private toastr: ToastrService) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, this.validateEmail]],
      senha: [
        '',
        [
          Validators.required,
          Validators.minLength(12), // Validação de no mínimo 12 caracteres
          this.passwordValidator,
        ],
      ],
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
    this.showPassword = !this.showPassword; // Alterna entre mostrar/ocultar senha
  }

  login() {
    if (this.loginForm.valid) {
      const credentials = this.loginForm.value;
      this.apiService.loginUser(credentials).subscribe(
        (response) => {
          console.log('Login bem-sucedido.');
          // Armazenando o token no localStorage
            if (response.id_usuario && response.token) {
              localStorage.setItem('userId', response.id_usuario);
              localStorage.setItem('authToken', response.token);
            }
          // Navega para o dashboard ou outra página após o login
          this.router.navigate(['/dashboard/notifications']);
        },
        (error) => {
          console.error('Erro ao fazer login:', error);
          console.log(credentials);
          this.errorMessage = 'Email ou senha incorretos. Tente novamente.';
        }
      );
    } else {
      this.loginForm.markAllAsTouched();
      this.toastr.warning("Email ou senha incorretos. Tente novamente!")
      console.log('Formulário inválido:', this.loginForm.errors);
    }
  }
}
