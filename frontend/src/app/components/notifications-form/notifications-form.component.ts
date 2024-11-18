import {CommonModule} from '@angular/common';
import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {RouterModule} from '@angular/router';
import {ModalNotificationsComponent} from '../modal-notifications/modal-notifications.component';

@Component({
  selector: 'app-notifications-form',
  standalone: true,
  imports: [CommonModule, RouterModule, ModalNotificationsComponent],
  templateUrl: './notifications-form.component.html',
  styleUrls: ['./notifications-form.component.css'],
})
export class NotificationsFormComponent implements OnInit {
  showNotificationAlert: boolean = false; // Inicialmente false, será definido no ngOnInit

  User: string = 'Lucas';

  Dates: Array<string> = ['05/09/2024', '06/09/2024', '00/00/0000'];
  Notifications: Array<string> = [
    'Lorem ipsum dolor sit, amet consectetur adipisicing elit. Est modi inventore libero nisi sunt rem nemo quaerat velit. ',
    'Lorem ipsum dolor sit, amet consectetur adipisicing elit. Est modi inventore libero nisi sunt rem nemo quaerat velit. Expedita, ab impedit dolorum rem a adipisci magni minus mollitia nam maxime!',
    'Lorem ipsum dolor sit, amet consectetur adipisicing elit. Est modi inventore libero nisi sunt rem nemo quaerat velit. ',
  ];

  @ViewChild('notificationAlert') notificationAlert!: ElementRef;

  ngOnInit(): void {
    // Verifica o localStorage para decidir se deve mostrar a notificação
    const notificationNotificationsPage = localStorage.getItem(
      'notificationNotificationsPage'
    );
    this.showNotificationAlert = !notificationNotificationsPage;
  }

  // Função de fechar o alerta
  closeButton() {
    // Adiciona a classe 'alert-closing' para aplicar a animação de fechamento
    if (this.notificationAlert) {
      this.notificationAlert.nativeElement.classList.add('alert-closing');
      // Espera o tempo da animação e então oculta o alerta
      setTimeout(() => {
        this.showNotificationAlert = false;
        localStorage.setItem('notificationNotificationsPage', 'true');
      }, 250); // Tempo de animação em milissegundos (0.5s)
    }
  }
}
