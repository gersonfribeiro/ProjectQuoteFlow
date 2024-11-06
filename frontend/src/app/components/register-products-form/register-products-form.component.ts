import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {ToastrService} from "ngx-toastr";
import {RouterLink} from "@angular/router";
import {NgIf} from "@angular/common";
import {NgxMaskDirective} from "ngx-mask";

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
