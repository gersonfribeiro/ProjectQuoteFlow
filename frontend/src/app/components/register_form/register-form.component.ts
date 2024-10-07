import { CommonModule } from '@angular/common';
// Importa o módulo comum que fornece diretivas comuns, como ngIf e ngFor.
import { Component } from '@angular/core';
// Importa o decorador 'Component', que é usado para definir um componente Angular.
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
// Importa classes e módulos necessários para trabalhar com formulários reativos em Angular,
// incluindo FormBuilder para criar instâncias de FormGroup e Validators para validação de campos.
import { Router, RouterModule } from '@angular/router';
// Importa o Router para navegação entre rotas e RouterModule para definir as rotas da aplicação.
import { NgxMaskDirective, NgxMaskPipe } from 'ngx-mask';
// Importa diretivas e pipes do pacote 'ngx-mask' para aplicar máscaras a campos de entrada.
import { ApiUsersService } from "../../services/api-users.service";
// Importa o serviço 'ApiUsersService' que gerencia a comunicação com a API para registro de usuários.

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
  registerForm: FormGroup; // Define o formulário reativo para registrar usuários.
  isLoading = false; // Adiciona uma variável de controle para exibir um indicador de carregamento.

  constructor(private fb: FormBuilder, private router: Router, private apiService: ApiUsersService) {
    // Injeta o FormBuilder, o Router e o ApiUsersService no construtor.
    // Inicializa o formulário com campos para nome, email e senha, incluindo validações.
    this.registerForm = this.fb.group({
      name: ['', [Validators.required]], // Campo nome é obrigatório.
      email: ['', [Validators.required, Validators.email]], // Campo email é obrigatório e deve ser um email válido.
      password: ['', [Validators.required, Validators.minLength(6)]], // Campo senha é obrigatório e deve ter no mínimo 6 caracteres.
    });
  }

  // Método para registrar usuário
  register() {
    if (this.registerForm.valid) {
      this.isLoading = true; // Ativa o indicador de carregamento.
      const usuarioData = this.registerForm.value; // Obtém os dados do formulário.

      // Faz a chamada ao backend para registrar o usuário
      this.apiService.registerUser(usuarioData).subscribe(
        response => {
          console.log('Usuário registrado com sucesso!', response);
          this.isLoading = false; // Desativa o indicador de carregamento.
          this.router.navigate(['/dashboard']); // Redireciona para a página do dashboard após o registro.
        },
        error => {
          console.error('Erro ao registrar o usuário:', error);
          this.isLoading = false; // Desativa o indicador de carregamento em caso de erro.
        }
      );
    } else {
      this.registerForm.markAllAsTouched(); // Marca todos os campos como tocados para mostrar mensagens de erro.
    }
  }
}
