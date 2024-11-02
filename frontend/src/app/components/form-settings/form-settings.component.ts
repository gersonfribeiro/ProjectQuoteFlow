import {CommonModule} from '@angular/common';
import {Component} from '@angular/core';
import {Router, RouterModule} from '@angular/router';
import {FormsModule} from "@angular/forms";
import {ApiUserService} from "../../services/api-user.service";
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-form-settings',
  standalone: true,
  imports: [RouterModule, CommonModule, FormsModule],
  templateUrl: './form-settings.component.html',
  styleUrls: ['./form-settings.component.css'], // Corrigido para 'styleUrls' ao invés de 'styleUrl'
})
export class FormSettingsComponent {
  showNotificationAlert: boolean;
  nomeUsuario: string | null = null;
  userId: string | null = null; // Adicionando userId

  constructor(private apiService: ApiUserService, private router: Router, private toastr: ToastrService) {
    const notificationSettingsPage = localStorage.getItem('notificationSettingsPage');
    this.showNotificationAlert = !notificationSettingsPage;

    // Recupera o nome do usuário e o ID do localStorage
    const usuario = localStorage.getItem('usuario');
    if (usuario) {
      const usuarioObj = JSON.parse(usuario); // Parse do JSON
      this.nomeUsuario = usuarioObj.nome; // Acessa o nome dentro do objeto
      this.userId = usuarioObj.id_usuario; // Acessa o ID dentro do objeto
    }
  }

  // Método para deletar usuário
  delete() {
    const userId = this.userId; // Obtém o ID do usuário

    // Verifica se o userId é válido
    if (!userId) {
      this.toastr.error('ID do usuário não encontrado.');
      return;
    }

    console.log('Tentando deletar usuário com ID:', userId); // Para depuração

    this.apiService.deleteUser(userId).subscribe(
      response => {
        this.toastr.success('Conta deletada com sucesso!');
        localStorage.removeItem('usuario'); // Remove dados do LocalStorage
        console.log('Conta deletada com sucesso!', response);
        this.router.navigate(['/logout']);
      },
      error => {
        this.toastr.error('Ocorreu um erro ao tentar deletar a conta. Tente novamente.');
        console.error('Erro ao deletar a conta:', error);
      }
    );
  }
}
