import {Component} from '@angular/core';
import {RegisterProductFormComponent} from "../register-product-form/register-product-form.component";
import {SidebarDashboardComponent} from "../sidebar-dashboard/sidebar-dashboard.component";

@Component({
  selector: 'app-register-product-page',
  standalone: true,
  imports: [
    RegisterProductFormComponent,
    SidebarDashboardComponent
  ],
  templateUrl: './register-product-page.component.html',
  styleUrl: './register-product-page.component.css'
})
export class RegisterProductPageComponent {

}
