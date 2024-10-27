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
      phone: [{value: '', disabled: false}, Validators.required, this.validatePhone],
      company: [{value: '', disabled: true}],
      cnpj: [{value: '', disabled: true}],
    });
  }

  ngOnInit(): void {
    // Carregar dados do usuário armazenados
    const usuarioData = JSON.parse(localStorage.getItem('usuario') || '{}');

    // Preenche o formulário de perfil com os dados do usuário
    this.profileForm.patchValue({
      name: usuarioData.nome,
      email: usuarioData.email,
      phone: usuarioData.telefone,
      company: '',
      cnpj: '',

    });
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
    const phone = control.value;
    const phonePattern = /^\(\d{2}\) \d{4,5}-\d{4}$/;
    if (phone && !phonePattern.test(phone)) {
      return {invalidPhone: true};
    }
    return null;
  }

  // Método para enviar dados ao LocalStorage e ao banco de dados ao submeter o formulário
  onSubmit() {
    if (this.profileForm.valid) {
      const usuarioData = {
        nome: this.profileForm.get('name')?.value,
        email: this.profileForm.get('email')?.value,
        telefone: this.profileForm.get('phone')?.value,
      };

      // Salva os dados no LocalStorage
      localStorage.setItem('usuario', JSON.stringify(usuarioData));

      // Envia os dados ao banco de dados (substitua pela URL da sua API)
      this.http.post('https://sua-api.com/usuarios', usuarioData).subscribe({
        next: (response) => {
          this.toastr.success('Perfil salvo com sucesso!', 'Sucesso');
        },
        error: (error) => {
          console.error('Erro ao salvar no banco de dados', error);
          this.toastr.error('Erro ao salvar os dados.', 'Erro');
        },
      });
    } else {
      this.toastr.error('Por favor, preencha todos os campos obrigatórios corretamente.', 'Erro');
    }
  }
}
