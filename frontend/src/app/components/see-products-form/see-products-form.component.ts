import {Component} from '@angular/core';
import {RouterLink} from "@angular/router";
import {NgForOf} from "@angular/common";
import {ApiProductService} from "../../services/api-product.service";
import { ApiUserService } from "../../services/api-user.service";

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

  constructor(private productService: ApiProductService,private apiUserService: ApiUserService ) {
  }

  ngOnInit(): void {
      // Carrega os produtos do backend
      const userId = localStorage.getItem('userId');

      this.apiUserService.getUserById(userId).subscribe(
              response => {
                  const companyId = response.id_empresa;

                this.productService.getProducts(companyId).subscribe({
                  next: (products) => {
                    this.products = products;
                  },
                  error: (error) => {
                    console.error('Erro ao carregar produtos:', error);
                  }
                });
              }
      );
    }
}
