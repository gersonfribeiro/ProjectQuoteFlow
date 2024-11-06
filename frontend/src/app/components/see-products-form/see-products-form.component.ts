import {Component} from '@angular/core';
import {RouterLink} from "@angular/router";
import {NgForOf} from "@angular/common";

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
  products: any[] = []; // Declaração da variável products

  ngOnInit(): void {
    // Carrega os produtos do localStorage quando o componente for inicializado
    this.products = this.getProducts();
  }

  // Método para pegar os produtos do localStorage
  getProducts() {
    const products = localStorage.getItem('products');
    return products ? JSON.parse(products) : [];
  }
}
