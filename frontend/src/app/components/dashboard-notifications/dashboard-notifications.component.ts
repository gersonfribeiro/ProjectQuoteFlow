import { Component, OnInit, ElementRef } from '@angular/core';
import { SidebarDashboardComponent } from '../sidebar-dashboard/sidebar-dashboard.component';
import { NotificationsFormComponent } from '../notifications-form/notifications-form.component';

@Component({
  selector: 'app-dashboard-notifications',
  standalone: true,
  imports: [SidebarDashboardComponent, NotificationsFormComponent],
  templateUrl: './dashboard-notifications.component.html',
  styleUrls: ['./dashboard-notifications.component.css'],
})
export class DashboardNotificationsComponent implements OnInit {
  // Construtor do componente que injeta uma referência ao elemento DOM usando ElementRef
  constructor(private el: ElementRef) {}

  // Método de ciclo de vida OnInit, executado após a inicialização do componente
  ngOnInit(): void {
    // Verifica se o modal já foi exibido anteriormente consultando o localStorage
    const hasModalBeenDisplayed = localStorage.getItem(
      'modalDashboardNotificationShow' // Chave corrigida
    );

    if (!hasModalBeenDisplayed) {
      // Se o modal ainda não foi exibido, obtém o elemento modal através da referência DOM
      const modalElement = this.el.nativeElement.querySelector(
        '#modalNotifications'
      );

      // Inicializa o modal utilizando a funcionalidade Modal do Bootstrap
      const modal = (window as any).bootstrap.Modal.getOrCreateInstance(
        modalElement
      );

      // Exibe o modal na interface do usuário
      modal.show();

      // Armazena no localStorage que o modal foi exibido, evitando que seja reaberto no futuro
      localStorage.setItem('modalDashboardNotificationShow', 'true'); // Chave corrigida
    }
  }
}
