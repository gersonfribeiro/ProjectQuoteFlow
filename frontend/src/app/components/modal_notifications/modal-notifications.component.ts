import { Component } from '@angular/core';

@Component({
  selector: 'app-modal-notifications',
  standalone: true,
  imports: [],
  templateUrl: './modal-notifications.component.html',
  styleUrl: './modal-notifications.component.css',
})
export class ModalNotificationsComponent {
  userName: string = ''; // Variável para o nome do usuário

  ngOnInit(): void {
    // Carregar dados do usuário armazenados no localStorage
    const usuarioData = JSON.parse(localStorage.getItem('usuario') || '{}');

    // Preenche o nome do usuário
    if (usuarioData && usuarioData.nome) {
      this.userName = usuarioData.nome;
    }
  }
}
