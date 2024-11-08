import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  Validators,
} from '@angular/forms';

import { Router, RouterModule } from '@angular/router';

import { NgxMaskDirective, NgxMaskPipe } from 'ngx-mask';
import { ToastrService } from 'ngx-toastr';
import { ApiUserService } from "../../services/api-user.service";
import { Usuario } from "../../models/user.model";

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
          Validators.minLength(12),
          this.passwordValidator,
        ],
      ],
      confirmSenha: ['', [Validators.required, this.confirmPasswordValidator]],
    });
  }

  // Validação personalizada de e-mail
  validateEmail(control: AbstractControl): ValidationErrors | null {
    const email = control.value;
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (email && !emailPattern.test(email)) {
      return { invalidEmail: true };
    }
    return null;
  }

  // Validação personalizada de senha
  passwordValidator(control: AbstractControl): ValidationErrors | null {
    const password = control.value;
    const hasUpperCase = /[A-Z]/.test(password);
    const hasLowerCase = /[a-z]/.test(password);
    const hasNumeric = /\d/.test(password);
    const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);
    const isValid =
      password &&
      password.length >= 12 &&
      hasUpperCase &&
      hasLowerCase &&
      hasNumeric &&
      hasSpecialChar;

    if (!isValid) {
      return { invalidPassword: true };
    }
    return null;
  }

  // Validação personalizada de confirmação de senha
  confirmPasswordValidator = (control: AbstractControl): ValidationErrors | null => {
    if (control.parent) {
      const senha = control.parent.get('senha')?.value;
      const confirmSenha = control.value;
      if (senha !== confirmSenha) {
        return { passwordMismatch: true };
      }
    }
    return null;
  };

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
            timeOut: 2000,
          });
          setTimeout(() => this.router.navigate(['/dashboard/notifications']), 2500);
        },
        error: (error) => {
          this.errorMessage = error.message;
          this.toastr.error(error.message);
        },
      });
    } else {
      this.registerForm.markAllAsTouched();
    }
  }
}
