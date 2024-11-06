import {Component} from '@angular/core';
import {RouterLink} from "@angular/router";
import {NgForOf} from "@angular/common";
import {ApiProductService} from "../../services/api-product.service";

@Component({
  selector: 'app-see-products-form',
  standalone: true,
  imports: [
    RouterLink,
    NgForOf
  ],
  templateUrl: './see-products-form.component.html',
  styleUrl: './see-products-form.component.css'
})
export class SeeProductsFormComponent {
  products: any[] = [];

  companyId: string = '21a4dfeb-e12c-4cb7-8977-ae6f30432e9f';

  constructor(private productService: ApiProductService) {
  }

  /* Funcionalidade em localStorage */

// ngOnInit(): void {
//   // Carrega os produtos do localStorage quando o componente for inicializado
//   this.products = this.getProducts();
// }
//
// // Método para pegar os produtos do localStorage
// getProducts() {
//   const products = localStorage.getItem('products');
//   return products ? JSON.parse(products) : [];
// }

  ngOnInit(): void {
      // Carrega os produtos do backend
      this.productService.getProducts(this.companyId).subscribe({
        next: (products) => {
          this.products = products;
        },
        error: (error) => {
          console.error('Erro ao carregar produtos:', error);
        }
      });
    }
}
