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
      const companyId = localStorage.getItem('companyId');

      if (companyId) {
          this.productService.getProducts(companyId).subscribe({
            next: (products) => {
              this.products = products;
            },
            error: (error) => {
              console.error('Erro ao carregar produtos:', error);
            }
          });
        }
    }
}
