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

  constructor(private apiUserService: ApiUserService, private router: Router, private toastr: ToastrService) {}

  // Método para deletar usuário
  delete() {
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
  }

}
