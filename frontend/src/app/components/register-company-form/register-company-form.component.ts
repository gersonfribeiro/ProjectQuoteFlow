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
  selector: 'app-register-company-form',
  standalone: true,
  imports: [
    NgxMaskDirective,
    NgxMaskPipe,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  templateUrl: './register-company-form.component.html',
  styleUrls: ['./register-company-form.component.css'],
})
export class RegisterCompanyFormComponent {
  registerCompanyForm: FormGroup;
  showNotificationAlert?: boolean;

  postalCode: string = '';
  street: string = '';
  neighborhood: string = '';
  city: string = '';
  state: string = '';
  region: string = '';

  constructor(private http: HttpClient, private fb: FormBuilder, private toastr: ToastrService) {
    this.registerCompanyForm = this.fb.group({
      company: [{value: '', disabled: false}, Validators.required],
      cnpj: [{value: '', disabled: false}, [Validators.required, this.validateCNPJ]],
      phone: [{value: '', disabled: false}, [Validators.required, this.validatePhone]],
      postalCode: [{value: '', disabled: false}, Validators.required],
      street: [{value: '', disabled: false}], // Defina o estado inicial como desabilitado
      neighborhood: [{value: '', disabled: false}],
      city: [{value: '', disabled: false}],
      state: [{value: '', disabled: false}],
      complement: [{value: '', disabled: false}],
      number: [{value: '', disabled: false}],
    });
  }

  ngOnInit(): void {
    // Carregar dados do usuário armazenados
    const cepData = JSON.parse(localStorage.getItem('cep') || '{}');

    // Preenche o formulário de perfil com os dados do usuário
    this.registerCompanyForm.patchValue({
      postalCode: cepData.postalCode
    });

    if (cepData.postalCode) {
        cepData.postalCode
        this.searchPostalCode();
      }
  }

  // Método para alternar o estado de habilitado/desabilitado de um campo específico
  toggleField(field: string) {
    const control = this.registerCompanyForm.get(field);
    if (control) {
      control.enabled ? control.disable() : control.enable();
    }
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
    const postalCodeValue = this.registerCompanyForm.get('postalCode')?.value;
    if (postalCodeValue && postalCodeValue.length === 8) {
      this.http
        .get<any>(`https://viacep.com.br/ws/${postalCodeValue}/json/`)
        .subscribe(
          (data) => {
            if (!data.erro) {
              const dataForm = {
                street: data.logradouro,
                neighborhood: data.bairro,
                city: data.localidade,
                state: data.uf,
                postalCode: data.cep.replace('-', ''),
              }
              this.registerCompanyForm.patchValue(dataForm);
              localStorage.setItem('cep', JSON.stringify(dataForm));
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
    if (this.registerCompanyForm.valid) {

      const companyData = {
          cnpj: this.registerCompanyForm.value.cnpj,
          email: "teste@email.com",
          nome: this.registerCompanyForm.value.company,
          senha: "teste123",
        };

      this.http.post('http://localhost:8080/empresas', companyData).subscribe(
          response => {
            console.log("Resposta da API:", response);
            const id_empresa = "67790e81-9d9d-4bfc-86b3-b2f44cd62fe1";

            const addressData = {
              bairro: this.registerCompanyForm.value.neighborhood,
              cep: this.registerCompanyForm.value.postalCode,
              complemento: this.registerCompanyForm.value.complement,
              localidade: this.registerCompanyForm.value.city,
              logradouro: this.registerCompanyForm.value.street,
              numero: this.registerCompanyForm.value.number,
              uf: this.registerCompanyForm.value.state,
              id_empresa: "67790e81-9d9d-4bfc-86b3-b2f44cd62fe1"
            };

            this.http.post('http://localhost:8080/enderecos', addressData).subscribe(
                response => {
                    this.toastr.success('Empresa e endereço cadastrados com sucesso!');
                  },
                error => {
                    this.toastr.error('Erro ao cadastrar endereço.');
                  }
              );

          },
        (error) => {
                console.error('Erro ao cadastrar empresa:', error);
                this.toastr.error('Erro ao cadastrar empresa.');
              }
        );

      console.log('Formulário enviado:', this.registerCompanyForm.value);
      this.registerCompanyForm.disable();
    } else {
      console.log('Formulário inválido');
      this.registerCompanyForm.markAllAsTouched();
    }
  }
}
