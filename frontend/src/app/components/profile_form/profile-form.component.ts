import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import { NgxMaskDirective, NgxMaskPipe } from 'ngx-mask';

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

  constructor(private http: HttpClient, private fb: FormBuilder) {
    this.profileForm = this.fb.group({
      name: ['', Validators.required],
      company: ['', Validators.required],
      cnpj: ['', [Validators.required, this.validateCNPJ]],
      phone: ['', [Validators.required, this.validatePhone]],
      postalCode: ['', Validators.required],
      street: [{ value: '', disabled: true }], // Defina o estado inicial como desabilitado
      neighborhood: [{ value: '', disabled: true }],
      city: [{ value: '', disabled: true }],
      state: [{ value: '', disabled: true }],
    });
  }

  // Função de validação customizada para o CNPJ
  validateCNPJ(control: AbstractControl): ValidationErrors | null {
    const cnpj = control.value?.replace(/[^\d]+/g, ''); // Remove caracteres especiais
    if (cnpj?.length !== 14) {
      return { invalidCNPJ: true };
    }
    return null;
  }

  // Função de validação customizada para o telefone
  validatePhone(control: AbstractControl): ValidationErrors | null {
    const phone = control.value?.replace(/[^\d]+/g, ''); // Remove caracteres especiais
    if (phone?.length !== 10 && phone?.length !== 11) {
      return { invalidPhone: true };
    }
    return null;
  }

  // Submissão do formulário
  onSubmit() {
    if (this.profileForm.valid) {
      console.log('Formulário enviado:', this.profileForm.value);
    } else {
      console.log('Formulário inválido');
      this.profileForm.markAllAsTouched();
    }
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
              alert('CEP não encontrado.');
            }
          },
          (error) => {
            alert('Erro ao buscar o CEP.');
          }
        );
    } else {
      alert('Por favor, insira um CEP válido com 8 dígitos.');
    }
  }
}
