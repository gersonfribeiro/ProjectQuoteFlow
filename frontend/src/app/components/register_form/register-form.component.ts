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
  styleUrl: './register-form.component.css',
})
export class RegisterFormComponent {
  registerForm: FormGroup;
  isLoading = false; // Adicionada a variável de controle de carregamento

  constructor(private fb: FormBuilder, private router: Router) {
    this.registerForm = this.fb.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  register() {
    if (this.registerForm.valid) {
      this.isLoading = true; // Ativa o loader
      console.log('Formulário válido:', this.registerForm.value);

      setTimeout(() => {
        this.isLoading = false; // Desativa o loader após a operação
        this.router.navigate(['/dashboard']); // Redireciona após o registro
      }, 2000); // Simulação de um tempo de processamento de 2 segundos
    } else {
      this.registerForm.markAllAsTouched(); // Exibe os erros
    }
  }
}
