import {Component} from '@angular/core';
import {SidebarDashboardComponent} from "../sidebar-dashboard/sidebar-dashboard.component";
import {RegisterProductsFormComponent} from "../register-products-form/register-products-form.component";

@Component({
  selector: 'app-register-products-page',
  standalone: true,
  imports: [
    SidebarDashboardComponent,
    RegisterProductsFormComponent
  ],
  templateUrl: './register-products-page.component.html',
  styleUrl: './register-products-page.component.css'
})
export class RegisterProductsPageComponent {

}
