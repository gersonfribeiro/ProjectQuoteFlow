import {CommonModule} from '@angular/common';
import {Component} from '@angular/core';
import {Router, RouterModule} from '@angular/router';
import {FormsModule} from "@angular/forms";
import {ApiAuthService} from "../../services/api-auth.service";

@Component({
  selector: 'app-configurations-form',
  standalone: true,
  imports: [RouterModule, CommonModule, FormsModule],
  templateUrl: './configurations-form.component.html',
  styleUrls: ['./configurations-form.component.css'], // Corrigido para 'styleUrls' ao invés de 'styleUrl'
})
export class ConfigurationsFormComponent {
  showNotificationAlert: boolean;
  nomeUsuario: string | null;
  userId: string | null; // Adicionando userId

  constructor(private apiService: ApiAuthService, private router: Router) {
    const notificationConfigurationsPage = localStorage.getItem('notificationConfigurationsPage');
    this.showNotificationAlert = !notificationConfigurationsPage;

    // Recupera o nome do usuário e o ID do localStorage
    const usuario = localStorage.getItem('usuario');
    if (usuario) {
      const usuarioObj = JSON.parse(usuario); // Parse do JSON
      this.nomeUsuario = usuarioObj.nome; // Acessa o nome dentro do objeto
      this.userId = usuarioObj.id; // Acessa o ID dentro do objeto
    }
  }

  // Método para deletar usuário
  delete() {
    const userId = this.userId; // Obtém o ID do usuário

    // Verifica se o userId é válido
    if (!userId) {
      alert('Erro: ID do usuário não encontrado.');
      return;
    }

    console.log('Tentando deletar usuário com ID:', userId); // Para depuração

    if (confirm('Tem certeza de que deseja apagar sua conta? Esta ação não pode ser revertida.')) {
      this.apiService.deleteUser(userId).subscribe(
        response => {
          console.log('Conta deletada com sucesso!', response);
          this.router.navigate(['/logout']);
        },
        error => {
          console.error('Erro ao deletar a conta:', error);
          alert('Ocorreu um erro ao tentar deletar a conta. Tente novamente.');
        }
      );
    }
  }
}
