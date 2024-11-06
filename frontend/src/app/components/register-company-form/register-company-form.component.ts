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
import {ApiCompanyService} from "../../services/api-company.service";
import {ApiAddressService} from "../../services/api-address.service";
import {ApiUserService} from "../../services/api-user.service";
import {RouterLink} from "@angular/router";
import {Usuario} from "../../models/user.model";

@Component({
  selector: 'app-register-company-form',
  standalone: true,
  imports: [
    NgxMaskDirective,
    NgxMaskPipe,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterLink,
  ],
  templateUrl: './register-company-form.component.html',
  styleUrls: ['./register-company-form.component.css'],
})
export class RegisterCompanyFormComponent {
  registerCompanyForm: FormGroup;

  constructor(private http: HttpClient, private fb: FormBuilder, private toastr: ToastrService, private apiCompanyService: ApiCompanyService, private apiAddressService: ApiAddressService, private apiUserService: ApiUserService) {
    this.registerCompanyForm = this.fb.group({
      company: [{value: '', disabled: false}, Validators.required],
      cnpj: [{value: '', disabled: false}, [Validators.required, this.validateCNPJ]],
      phone: [{value: '', disabled: false}, [Validators.required, this.validatePhone]],
      email: [{value: '', disabled: false}, [Validators.required, this.validateEmail]],
      postalCode: [{value: '', disabled: false}, Validators.required],
      street: [{value: '', disabled: false}],
      neighborhood: [{value: '', disabled: false}],
      city: [{value: '', disabled: false}],
      state: [{value: '', disabled: false}],
      complement: [{value: '', disabled: false}],
      number: [{value: '', disabled: false}],
    });
  }

  ngOnInit(): void {
    // Carregar dados do endereço
//     this.apiAddressService.getAddress().subscribe(
//                 (response: any) => {
//                     this.registerCompanyForm.patchValue({
//                         postalCode: response.cep
//                       });
//                     if (response.cep) {
//                           this.searchPostalCode();
//                         }
//                   }
//               );
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

  // Função de validação personalizada para e-mail
  validateEmail(control: AbstractControl): ValidationErrors | null {
    const email = control.value;
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (email && !emailPattern.test(email)) {
      return {invalidEmail: true};
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
        nome: this.registerCompanyForm.value.company,
        cnpj: this.registerCompanyForm.value.cnpj,
        telefone: this.registerCompanyForm.value.phone,
        email: this.registerCompanyForm.value.email
      };

      this.apiCompanyService.registerCompany(companyData).subscribe(
        response => {
          console.log("Resposta da API:", response);
          const id_empresa = response.id_empresa;

          const addressData = {
            bairro: this.registerCompanyForm.value.neighborhood,
            cep: this.registerCompanyForm.value.postalCode,
            complemento: this.registerCompanyForm.value.complement,
            localidade: this.registerCompanyForm.value.city,
            logradouro: this.registerCompanyForm.value.street,
            numero: this.registerCompanyForm.value.number,
            uf: this.registerCompanyForm.value.state,
            id_empresa: id_empresa
          };

          this.apiAddressService.registerAddress(addressData).subscribe(
            response => {
              this.toastr.success('Dados cadastrados com sucesso!');

              this.apiUserService.getUser().subscribe(
                (response: Usuario) => {
                  const updatedData = {
                    nome: response.nome,
                    email: response.email,
                    senha: response.senha,
                    telefone: response.telefone,
                    id_empresa: id_empresa,
                    permissao: "EMPRESA",
                    id_usuario: response.id_usuario
                  };

                  this.apiUserService.updateUser(response.id_usuario, updatedData).subscribe(
                    response => {
                      console.log('id_empresa atribuída ao usuário');
                    },
                    error => {
                      this.toastr.error('Erro ao atualizar o usuário.');
                    }
                  );
                },
                error => {
                  this.toastr.error('Erro ao buscar dados do usuário.');
                }
              );
            },
            error => {
              this.toastr.error('Erro ao cadastrar endereço.');
            }
          );
        },
        error => {
          this.toastr.error('Erro ao cadastrar empresa.');
        }
      );

      console.log('Formulário enviado:', this.registerCompanyForm.value);
      this.registerCompanyForm.disable();
    } else {
      console.log('Formulário inválido');
      this.toastr.warning("Para salvar a informação, clique em cadastrar apenas uma vez.")
      this.registerCompanyForm.markAllAsTouched();
    }
  }
}
