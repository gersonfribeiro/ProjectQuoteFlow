import { Component } from '@angular/core';
import {ApiUserService} from "../../services/api-user.service";
import {Usuario} from "../../models/user.model";

@Component({
  selector: 'app-modal-notifications',
  standalone: true,
  imports: [],
  templateUrl: './modal-notifications.component.html',
  styleUrl: './modal-notifications.component.css',
})

export class ModalNotificationsComponent {
  constructor(private apiUserService: ApiUserService) {}

  userName: string = ''; // Variável para o nome do usuário

  ngOnInit(): void {
    // Carregar dados do usuário
    this.apiUserService.getUser().subscribe(
      (response: Usuario) => {
         this.userName = response.nome;
        }
      );
  }
}
