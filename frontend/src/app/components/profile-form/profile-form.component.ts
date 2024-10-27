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

  postalCode: string = '';
  street: string = '';
  neighborhood: string = '';
  city: string = '';
  state: string = '';
  region: string = '';

  constructor(private http: HttpClient, private fb: FormBuilder, private toastr: ToastrService) {
    this.profileForm = this.fb.group({
      name: [{value: '', disabled: true}, Validators.required],
      company: [{value: '', disabled: true}, Validators.required],
      email: [{value: '', disabled: true}, [Validators.required, this.validateEmail]],
      cnpj: [{value: '', disabled: true}, Validators.required, this.validateCNPJ],
      phone: [{value: '', disabled: true}, Validators.required, this.validatePhone],
      postalCode: ['', Validators.required],
      street: [{value: '', disabled: true}], // Defina o estado inicial como desabilitado
      neighborhood: [{value: '', disabled: true}],
      city: [{value: '', disabled: true}],
      state: [{value: '', disabled: true}],
    });
  }

  ngOnInit(): void {
    // Carregar dados do usuário armazenados
    const usuarioData = JSON.parse(localStorage.getItem('usuario') || '{}');

    // Preenche o formulário de perfil com os dados do usuário
    this.profileForm.patchValue({
      name: usuarioData.nome,
      email: usuarioData.email,
      company: '', // Adicione outros dados conforme necessário
      cnpj: '' // Se houver CNPJ ou outros campos
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
      return {invalidEmail: true}; // Retorna um erro se o e-mail não for válido
    }
    return null; // Retorna null se o e-mail for válido
  }

  // Função de validação customizada para o CNPJ
  validateCNPJ(control: AbstractControl): ValidationErrors | null {
    const cnpj = control.value?.replace(/[^\d]+/g, ''); // Remove caracteres especiais
    if (cnpj?.length !== 14) {
      return {invalidCNPJ: true};
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

  // Função para buscar o CEP
  searchPostalCode() {
    const postalCodeValue = this.profileForm.get('postalCode')?.value;
    if (postalCodeValue && postalCodeValue.length === 8) {
      this.http
        .get<any>(`https://viacep.com.br/ws/${postalCodeValue}/json/`)
        .subscribe(
          (data) => {
            if (!data.erro) {
              this.profileForm.patchValue({
                street: data.logradouro,
                neighborhood: data.bairro,
                city: data.localidade,
                state: data.uf,
              });
            } else {
              this.toastr.error('CEP não encontrado.');
            }
          },
          (error) => {
            this.toastr.error('Erro ao buscar o CEP.');
          }
        );
    } else {
      this.toastr.warning('Por favor, insira um CEP válido com 8 dígitos.');
    }
  }

  // Submissão do formulário
  onSubmit() {
    if (this.profileForm.valid) {
      const usuarioData = JSON.parse(localStorage.getItem('usuario') || '{}');
      const userId = usuarioData.id_usuario;

      const updatedData = {
        nome: this.profileForm.value.name || usuarioData.nome,
        email: usuarioData.email,
        senha: usuarioData.senha,
        id_usuario: userId
      };

      this.http.put(`http://localhost:8080/usuarios/${userId}`, updatedData).subscribe(
        response => {
          console.log('Dados atualizados com sucesso:', response);
          this.toastr.success('Dados atualizados com sucesso!');

          localStorage.setItem('usuario', JSON.stringify(updatedData));
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
