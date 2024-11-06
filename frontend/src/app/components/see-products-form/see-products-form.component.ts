import {Component, OnInit} from '@angular/core';
import {RegisterCompanyFormComponent} from "../register-company-form/register-company-form.component";
import {SidebarDashboardComponent} from "../sidebar-dashboard/sidebar-dashboard.component";

@Component({
  selector: 'app-see-products-page',
  standalone: true,
  imports: [
    RegisterCompanyFormComponent,
    SidebarDashboardComponent,
  ],
  templateUrl: './see-products-page.component.html',
  styleUrls: ['./see-products-page.component.css']
})
export class SeeProductsPageComponent implements OnInit {
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
