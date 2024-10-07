import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ApiQuotationService } from "../../services/api-quotation.service";
import { FormsModule } from "@angular/forms";

@Component({
  selector: 'app-configurations-form',
  standalone: true,
  imports: [RouterModule, CommonModule, FormsModule],
  templateUrl: './configurations-form.component.html',
  styleUrl: './configurations-form.component.css',
})
export class ConfigurationsFormComponent {
  showNotificationAlert: boolean;

  constructor(private apiService: ApiQuotationService) {
    const notificationConfigurationsPage = localStorage.getItem(
      'notificationConfigurationsPage'
    );
    this.showNotificationAlert = !notificationConfigurationsPage;
  }

  // Função de fechar o alerta
  closeButton() {
    this.showNotificationAlert = false;
    localStorage.setItem('notificationConfigurationsPage', String(true));
  }

  // Método para deletar usuário
  delete() {
    // const userId =  /* obtenha o ID do usuário de alguma forma, talvez do serviço de autenticação */;
    // if (confirm('Tem certeza de que deseja apagar sua conta? Esta ação não pode ser revertida.')) {
    //   this.apiService.deleteUser(userId).subscribe(
    //     response => {
    //       console.log('Conta deletada com sucesso!', response);
    //       // Aqui você pode redirecionar o usuário ou exibir uma mensagem de sucesso
    //     },
    //     error => {
    //       console.error('Erro ao deletar a conta:', error);
    //       // Aqui você pode exibir uma mensagem de erro ao usuário
    //     }
    //   );
    // }
  }
}
