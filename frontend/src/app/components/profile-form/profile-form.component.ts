import {CommonModule} from '@angular/common';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {Component} from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import {NgxMaskDirective, NgxMaskPipe} from 'ngx-mask';
import {ToastrService} from 'ngx-toastr';
import {Observable} from "rxjs";
import {Usuario} from "../../models/user.model";

@Component({
  selector: 'app-profile-form',
  standalone: true,
  imports: [
    NgxMaskDirective,
    NgxMaskPipe,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  templateUrl: './profile-form.component.html',
  styleUrls: ['./profile-form.component.css'],
})
export class ProfileFormComponent {
  profileForm: FormGroup;
  showNotificationAlert?: boolean;

  constructor(private http: HttpClient, private fb: FormBuilder, private toastr: ToastrService) {
    this.profileForm = this.fb.group({
      name: [{value: '', disabled: true}, Validators.required],
      email: [{value: '', disabled: true}, [Validators.required, this.validateEmail]],
      phone: [{value: '', disabled: true}, [Validators.required, this.validatePhone]], // Síncrono
      //
      company: [{value: '', disabled: true}],
      cnpj: [{value: '', disabled: true}],
    });
  }

  ngOnInit(): void {
    // Carregar id do usuário
    const usuarioData = JSON.parse(localStorage.getItem('usuario') || '{}');
    const userId = usuarioData.id_usuario;

    this.http.get<Usuario>(`http://localhost:8080/usuarios/${userId}`).subscribe(
      (response: Usuario) => {
        this.profileForm.patchValue({
          name: response.nome,
          email: response.email,
          phone: response.telefone,
        });
      },
      error => {
        console.error("Erro");
      }
    );
  }

  // Método para alternar o estado de habilitado/desabilitado de um campo específico
  toggleField(field: string) {
    const control = this.profileForm.get(field);
    if (control) {
      control.enabled ? control.disable() : control.enable();
    }
  }

  // Função de validação personalizada para e-mail
  validateEmail(control: AbstractControl): ValidationErrors | null {
    const email = control.value;
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (email && !emailPattern.test(email)) {
      return {invalidEmail: true};
    }
    return null;
  }

  // Função de validação customizada para o telefone
  validatePhone(control: AbstractControl): ValidationErrors | null {
    const phone = control.value?.replace(/[^\d]+/g, ''); // Remove caracteres especiais
    if (phone?.length !== 10 && phone?.length !== 11) {
      return {invalidPhone: true};
    }
    return null;
  }

  // Método para enviar dados ao LocalStorage e ao banco de dados ao submeter o formulário
  // Submissão do formulário
  onSubmit() {
    if (this.profileForm.valid) {
      const usuarioData = JSON.parse(localStorage.getItem('usuario') || '{}');
      const userId = usuarioData.id_usuario;

      const updatedData = {
        nome: this.profileForm.value.name || usuarioData.nome,
        email: this.profileForm.value.email || usuarioData.email,
        senha: usuarioData.senha,
        telefone: this.profileForm.value.phone || usuarioData.telefone,
        id_usuario: userId
      };

      this.http.put(`http://localhost:8080/usuarios/${userId}`, updatedData).subscribe(
        response => {
          console.log('Dados atualizados com sucesso:', response);
          this.toastr.success('Dados atualizados com sucesso!');
          localStorage.setItem('usuario', JSON.stringify(updatedData));

          // Desabilitar todos os campos após o envio
          this.profileForm.disable();
        },
        error => {
          console.error('Erro ao atualizar os dados:', error);
          this.toastr.error('Erro ao atualizar os dados.');
        }
      );
      console.log('Formulário enviado:', this.profileForm.value);
    } else {
      console.log('Formulário inválido');
      this.profileForm.markAllAsTouched();
    }
  }
}
