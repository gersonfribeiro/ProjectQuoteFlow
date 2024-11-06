import { Component } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { ToastrService } from "ngx-toastr";
import { RouterLink } from "@angular/router";
import { NgIf } from "@angular/common";
import { NgxMaskDirective } from "ngx-mask";
import { ApiProductService } from "../../services/api-product.service";
import { ApiCompanyService } from "../../services/api-company.service";

@Component({
  selector: 'app-register-products-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterLink,
    NgIf,
    NgxMaskDirective
  ],
  templateUrl: './register-products-form.component.html',
  styleUrl: './register-products-form.component.css'
})
export class RegisterProductsFormComponent {
  registerProductForm: FormGroup;

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private toastr: ToastrService,
    private apiProductService: ApiProductService,
    private apiCompanyService: ApiCompanyService // Correção aqui
  ) {
    this.registerProductForm = this.fb.group({
      sku: ['', [Validators.required, Validators.minLength(6)]],
      productCategory: ['', Validators.required],
      variation: [''],
      description: [''],
      observation: ['']
    });
  }

  // Submissão do formulário
  onSubmit() {
    console.log("Formulário enviado");
    if (this.registerProductForm.valid) {
      const productData = {
        sku: this.registerProductForm.value.sku,
        descricao: this.registerProductForm.value.description,
        categoria: this.registerProductForm.value.productCategory,
        variacao: this.registerProductForm.value.variation,
        observacao: this.registerProductForm.value.observation
      };

      console.log("Dados do produto:", productData);

      this.apiCompanyService.getCompany().subscribe({
        next: (response: any) => {
          const companyId = response.id_empresa;

          // Chama o serviço para salvar o produto no backend
          this.apiProductService.registerProduct(companyId, productData).subscribe({
            next: (response: any) => {
              console.log(productData);
              this.toastr.success('Produto cadastrado com sucesso!');
              this.registerProductForm.reset();
            },
            error: (error: any) => {  // Correção de tipagem
              this.toastr.error(error.message);
            }
          });
        },
        error: (error: any) => { // Tratamento de erro para a requisição de empresa
          this.toastr.error('Erro ao obter empresa: ' + error.message);
        }
      });
    } else {
      console.log('Formulário inválido');
      this.registerProductForm.markAllAsTouched();
    }
  }
}
