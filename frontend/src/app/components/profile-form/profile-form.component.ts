import {CommonModule} from '@angular/common';
import {HttpClient} from '@angular/common/http';
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
import {Usuario} from "../../models/user.model";
import {RouterLink} from "@angular/router";
import {ApiUserService} from "../../services/api-user.service";
import {ApiCompanyService} from "../../services/api-company.service";

@Component({
  selector: 'app-profile-form',
  standalone: true,
  imports: [
    NgxMaskDirective,
    NgxMaskPipe,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterLink,
  ],
  templateUrl: './profile-form.component.html',
  styleUrls: ['./profile-form.component.css'],
})
export class ProfileFormComponent {
  profileForm: FormGroup;

  constructor(private http: HttpClient, private fb: FormBuilder, private toastr: ToastrService, private apiUserService: ApiUserService, private apiCompanyService: ApiCompanyService) {
    this.profileForm = this.fb.group({
      name: [{value: '', disabled: false}, Validators.required],
      email: [{value: '', disabled: false}, [Validators.required, this.validateEmail]],
      phone: [{value: '', disabled: false}, [Validators.required, this.validatePhone]],
      company: [{value: '', disabled: true}],
      cnpj: [{value: '', disabled: true}],
    });
  }

  ngOnInit(): void {
    const savedForm = localStorage.getItem('profileForm');

    if (savedForm) {
      this.profileForm.patchValue(JSON.parse(savedForm));
    }

    const userId = this.apiUserService.getUserId();
    this.apiUserService.getUserById(userId).subscribe(
      (response: Usuario) => {
        this.profileForm.patchValue({
          name: response.nome,
          email: response.email,
          phone: response.telefone,
        });
        this.apiCompanyService.getCompany().subscribe(
          (companyResponse: any) => {
            this.profileForm.patchValue({
              company: companyResponse.nome,
              cnpj: companyResponse.cnpj,
            });
          },
          (error) => {
            console.error('Erro ao buscar dados da empresa', error);
          }
        );
      },
      (error) => {
        console.error('Erro ao buscar dados do usuário', error);
      }
    );

    // Salvar os dados do formulário no localStorage sempre que houver alteração
    this.profileForm.valueChanges.subscribe(value => {
      localStorage.setItem('profileForm', JSON.stringify(value));
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
    const phone = control.value?.replace(/[^\d]+/g, ''); // Remove caracteres especiais
    if (phone?.length !== 10 && phone?.length !== 11) {
      return {invalidPhone: true};
    }
    return null;
  }

  // Submissão do formulário
  onSubmit() {
    if (this.profileForm.valid) {
      const userId = this.apiUserService.getUserId();
      this.apiUserService.getUserById(userId).subscribe(
        (response: Usuario) => {
          const updatedData = {
            nome: this.profileForm.value.name,
            email: this.profileForm.value.email,
            senha: response.senha,
            telefone: this.profileForm.value.phone,
            id_empresa: response.id_empresa,
            permissao: response.permissao,
            id_usuario: response.id_usuario
          };

          this.apiUserService.updateUser(response.id_usuario, updatedData).subscribe(
            response => {
              this.toastr.success('Dados atualizados com sucesso!');
            }
          );
        }
      );
      console.log('Formulário enviado:', this.profileForm.value);
    } else {
      console.log('Formulário inválido');
      this.toastr.warning("Para salvar a informação, clique em salvar apenas uma vez.");
      this.profileForm.markAllAsTouched();
    }
  }
}
