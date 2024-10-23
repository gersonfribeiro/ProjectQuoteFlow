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
import { ToastrService } from 'ngx-toastr';

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

  constructor(private http: HttpClient, private fb: FormBuilder, private toastr: ToastrService) {
    this.registerCompanyForm = this.fb.group({
      name: ['', Validators.required],
      company: ['', Validators.required],
      cnpj: ['', [Validators.required, this.validateCNPJ]],
      phone: ['', [Validators.required, this.validatePhone]],
    });
  }

  ngOnInit(): void {
      // Carregar dados do usuário armazenados
      const usuarioData = JSON.parse(localStorage.getItem('usuario') || '{}');

      // Preenche o formulário de perfil com os dados do usuário
      this.registerCompanyForm.patchValue({
        name: usuarioData.nome,
        company: '', // Adicione outros dados conforme necessário
        cnpj: '' // Se houver CNPJ ou outros campos
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
      if (this.registerCompanyForm.valid) {
      const usuarioData = JSON.parse(localStorage.getItem('usuario') || '{}');
      const userId = usuarioData.id_usuario;

      const updatedData = {
        nome: this.registerCompanyForm.value.name || usuarioData.nome,
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
        console.log('Formulário enviado:', this.registerCompanyForm.value);
      } else {
        console.log('Formulário inválido');
        this.registerCompanyForm.markAllAsTouched();
      }
    }
}
