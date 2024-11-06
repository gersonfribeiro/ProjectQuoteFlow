import {Component} from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators
} from "@angular/forms";
import {NgIf} from "@angular/common";
import {NgxMaskDirective} from "ngx-mask";
import {HttpClient} from "@angular/common/http";
import {ToastrService} from "ngx-toastr";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-register-product-form',
  standalone: true,
  imports: [
    FormsModule,
    NgIf,
    NgxMaskDirective,
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './register-product-form.component.html',
  styleUrl: './register-product-form.component.css'
})
export class RegisterProductFormComponent {
  registerProductForm: FormGroup;

  constructor(private http: HttpClient, private fb: FormBuilder, private toastr: ToastrService) {
    this.registerProductForm = this.fb.group({
      sku: ['', [Validators.required, Validators.minLength(6)]],
      productCategory: ['', Validators.required],
      variation: [''],
      description: [''],
      observation: ['']
    });
  }

  // Método para pegar os produtos do localStorage
  getProducts() {
    const products = localStorage.getItem('products');
    return products ? JSON.parse(products) : [];
  }

  // Submissão do formulário
  onSubmit() {
    console.log("Formulário enviado");
    if (this.registerProductForm.valid) {
      const product = this.registerProductForm.value;

      // Pega a lista atual de produtos
      const products = this.getProducts();

      // Adiciona o novo produto à lista
      products.push(product);

      // Salva a lista atualizada no localStorage
      localStorage.setItem('products', JSON.stringify(products));

      // Exibe a notificação de sucesso
      this.toastr.success('Produto cadastrado com sucesso!');

      // Limpa o formulário após o envio
      this.registerProductForm.reset();
    } else {
      console.log('Formulário inválido');
      this.registerProductForm.markAllAsTouched();
    }
  }
}
