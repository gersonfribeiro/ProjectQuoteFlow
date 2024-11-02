import {CommonModule} from '@angular/common';
import {Component} from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import {Router, RouterModule} from '@angular/router';
import {NgxMaskDirective, NgxMaskPipe} from 'ngx-mask';
import {ApiUserService} from "../../services/api-user.service";

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
  isLoading = false;
  errorMessage: string | null = null;
  showPassword = false; // Controle de visibilidade da senha

  constructor(private fb: FormBuilder, private router: Router, private apiService: ApiUserService) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword; // Alterna entre mostrar/ocultar senha
  }

  // login() {
  //   if (this.loginForm.valid) {
  //     this.isLoading = true;
  //     const credentials = this.loginForm.value;
  //     this.apiService.loginUser(credentials).subscribe(
  //       (response) => {
  //         this.isLoading = false;
  //         console.log('Login bem-sucedido:', response);
  //         // Navega para o dashboard ou outra página após o login
  //         this.router.navigate(['/dashboard']);
  //       },
  //       (error) => {
  //         this.isLoading = false;
  //         console.error('Erro ao fazer login:', error);
  //         this.errorMessage = 'Email ou senha incorretos. Tente novamente.';
  //       }
  //     );
  //   } else {
  //     this.loginForm.markAllAsTouched();
  //     console.log('Formulário inválido:', this.loginForm.errors);
  //   }
}
