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
  styleUrls: ['./form-settings.component.css'],
})
export class FormSettingsComponent {

  constructor(private apiUserService: ApiUserService, private router: Router, private toastr: ToastrService) {
  }

  // Método para deletar usuário
  delete() {
    const userId = localStorage.getItem('userId')

    this.apiUserService.deleteUser(userId).subscribe(
      response => {
        localStorage.removeItem('userId');
        this.toastr.success('Conta deletada com sucesso!', '', {
          positionClass: 'toast-top-right',
          progressBar: true,
          progressAnimation: 'increasing',
          timeOut: 2000,
        });
        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 2500);
      },
      error => {
        this.toastr.error('Ocorreu um erro ao tentar deletar a conta. Tente novamente.');
        console.error('Erro ao deletar a conta:', error);
      }
    );
  }

  // Método chamado ao confirmar a exclusão
    confirmDelete() {
      this.delete();
    }
}
