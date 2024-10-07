import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-configurations-form',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './configurations-form.component.html',
  styleUrl: './configurations-form.component.css',
})
export class ConfigurationsFormComponent {
  showNotificationAlert: boolean;

  constructor() {
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
}
