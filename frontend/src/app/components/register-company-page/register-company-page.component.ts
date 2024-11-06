import {Component} from '@angular/core';
import {RegisterProductsFormComponent} from "../register-products-form/register-products-form.component";
import {SidebarDashboardComponent} from "../sidebar-dashboard/sidebar-dashboard.component";
import {RegisterCompanyFormComponent} from "../register-company-form/register-company-form.component";

@Component({
  selector: 'app-register-company-page',
  standalone: true,
  imports: [
    RegisterProductsFormComponent,
    SidebarDashboardComponent,
    RegisterCompanyFormComponent
  ],
  templateUrl: './register-company-page.component.html',
  styleUrl: './register-company-page.component.css'
})
export class RegisterCompanyPageComponent {

}
