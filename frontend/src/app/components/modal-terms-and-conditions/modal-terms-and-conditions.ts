import { Component } from '@angular/core';

@Component({
  selector: 'app-modal-terms-and-conditions',
  standalone: true,
  imports: [],
  templateUrl: './modal-terms-and-conditions.html',
  styleUrl: './modal-terms-and-conditions.css',
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
