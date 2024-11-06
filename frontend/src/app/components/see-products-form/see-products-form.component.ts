import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RegisterCompanyFormComponent} from "../register-company-form/register-company-form.component";
import {SidebarDashboardComponent} from "../sidebar-dashboard/sidebar-dashboard.component";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-see-products-form',
  standalone: true,
  imports: [
    RegisterCompanyFormComponent,
    SidebarDashboardComponent,
    CommonModule,
    RouterLink,
  ],
  templateUrl: './see-products-form.component.html',
  styleUrls: ['./see-products-form.component.css']
})
export class SeeProductsFormComponent implements OnInit {
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
