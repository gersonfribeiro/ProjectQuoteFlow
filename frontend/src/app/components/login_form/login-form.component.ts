import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { NgxMaskDirective, NgxMaskPipe } from 'ngx-mask';

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
  isLoading = false; // Controle do estado de carregamento

  constructor(private fb: FormBuilder, private router: Router) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  // Método de login
  login() {
    if (this.loginForm.valid) {
      this.isLoading = true; // Ativa o loader
      console.log('Formulário válido:', this.loginForm.value);

      // Simula um tempo de login e redireciona
      setTimeout(() => {
        this.isLoading = false; // Desativa o loader ao finalizar
        this.router.navigate(['/dashboard/notifications']); // Redireciona ao dashboard
      }, 2000); // Simula 2 segundos de carregamento
    } else {
      this.loginForm.markAllAsTouched();
    }
  }
}
