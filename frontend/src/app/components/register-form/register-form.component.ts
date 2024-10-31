import {CommonModule} from '@angular/common';
// Importa o módulo comum que fornece diretivas comuns, como ngIf e ngFor.
import {Component} from '@angular/core';
// Importa o decorador 'Component', que é usado para definir um componente Angular.
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule, ValidationErrors,
  Validators,
} from '@angular/forms';
// Importa classes e módulos necessários para trabalhar com formulários reativos em Angular,
// incluindo FormBuilder para criar instâncias de FormGroup e Validators para validação de campos.
import {Router, RouterModule} from '@angular/router';
// Importa o Router para navegação entre rotas e RouterModule para definir as rotas da aplicação.
import {NgxMaskDirective, NgxMaskPipe} from 'ngx-mask';
import {ToastrService} from 'ngx-toastr';
// Importa diretivas e pipes do pacote 'ngx-mask' para aplicar máscaras a campos de entrada.
import {ApiUserService} from "../../services/./api-user.service";
import {Usuario} from "../../models/user.model";

// Importa o serviço 'ApiUserService' que gerencia a comunicação com a API para registro de usuários.

@Component({
  selector: 'app-register-form',
  standalone: true,
  // Define este componente como um componente independente que pode ser usado sem ser declarado em um módulo.
  imports: [
    RouterModule,
    NgxMaskDirective,
    NgxMaskPipe,
    ReactiveFormsModule,
    CommonModule,
  ],
  // Lista os módulos e diretivas que serão utilizados neste componente.
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css'],
  // Especifica o caminho do arquivo HTML e CSS para este componente.
})
export class RegisterFormComponent {
  registerForm: FormGroup;
  isLoading = false;
  errorMessage: any;
  showPassword = false; // Controle de visibilidade da senha

  constructor(private fb: FormBuilder, private router: Router, private apiService: ApiUserService, private toastr: ToastrService) {
    this.registerForm = this.fb.group({
      nome: ['', [Validators.required]],
      email: ['', [Validators.required, this.validateEmail]],
      senha: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  // Função de validação personalizada para e-mail
  validateEmail(control: AbstractControl): ValidationErrors | null {
    const email = control.value;
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if (email && !emailPattern.test(email)) {
      return {invalidEmail: true}; // Retorna um erro se o e-mail não for válido
    }
    return null; // Retorna null se o e-mail for válido
  }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword; // Alterna entre mostrar/ocultar senha
  }

  register() {
    if (this.registerForm.valid) {
      this.isLoading = true;
      const usuarioData: Usuario = this.registerForm.value;

      this.apiService.registerUser(usuarioData).subscribe({
        next: (response) => {
          this.isLoading = false;
          this.toastr.success('Usuário registrado com sucesso!', '', {
            positionClass: 'toast-top-right',
            progressBar: true,
            progressAnimation: 'increasing',
            timeOut: 2000,
          });

          const usuarioComId: Usuario = {...usuarioData, id_usuario: response.id_usuario};
          localStorage.setItem('usuario', JSON.stringify(usuarioComId));

          setTimeout(() => {
            this.router.navigate(['/dashboard/notifications']);
          }, 2500);
        },
        error: (error) => {
          this.isLoading = false;
          this.errorMessage = error.message;
          this.toastr.error(error.message);
        }
      });
    } else {
      this.registerForm.markAllAsTouched();
      console.log('Formulário inválido:', this.registerForm.errors);
    }
  }
}
