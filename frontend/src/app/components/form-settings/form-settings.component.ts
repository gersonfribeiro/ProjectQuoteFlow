import {CommonModule} from '@angular/common';
import {Component} from '@angular/core';
import {Router, RouterModule} from '@angular/router';
import {FormsModule} from "@angular/forms";
import {ApiUserService} from "../../services/api-user.service";
import {ToastrService} from 'ngx-toastr';
import {Usuario} from "../../models/user.model";

@Component({
  selector: 'app-form-settings',
  standalone: true,
  imports: [RouterModule, CommonModule, FormsModule],
  templateUrl: './form-settings.component.html',
  styleUrls: ['./form-settings.component.css'], // Corrigido para 'styleUrls' ao invés de 'styleUrl'
})
export class FormSettingsComponent {

  constructor(private apiUserService: ApiUserService, private router: Router, private toastr: ToastrService) {
  }

  // Método para deletar usuário
  delete() {
    this.apiUserService.getUser().subscribe(
      (response: Usuario) => {
        const userId = response.id_usuario;

        // Verifica se o userId é válido
        if (!userId) {
          this.toastr.error('ID do usuário não encontrado.');
          return;
        }

        console.log('Tentando deletar usuário com ID:', userId); // Para depuração

        this.apiUserService.deleteUser(userId).subscribe(
          response => {
            this.toastr.success('Conta deletada com sucesso!', '', {
              positionClass: 'toast-top-right',
              progressBar: true,
              progressAnimation: 'increasing',
              timeOut: 2000,
            });
            setTimeout(() => {
              this.router.navigate(['/login']);
            }, 2500);
    const userId = localStorage.getItem('userId')

        this.apiUserService.deleteUser(userId).subscribe(
          response => {
            this.toastr.success('Conta deletada com sucesso!');
            localStorage.removeItem('userId');
            this.router.navigate(['/logout']);
          },
          error => {
            this.toastr.error('Ocorreu um erro ao tentar deletar a conta. Tente novamente.');
            console.error('Erro ao deletar a conta:', error);
          }
        );
      },
      error => {
        console.error("Erro ao obter usuário:", error);
        this.toastr.error('Não foi possível obter os dados do usuário.');
      }
    );
  }
}
