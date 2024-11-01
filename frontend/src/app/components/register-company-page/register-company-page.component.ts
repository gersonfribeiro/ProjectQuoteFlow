import {Component} from '@angular/core';
import {RegisterProductFormComponent} from "../register-product-form/register-product-form.component";
import {SidebarDashboardComponent} from "../sidebar-dashboard/sidebar-dashboard.component";
import {RegisterCompanyFormComponent} from "../register-company-form/register-company-form.component";

@Component({
  selector: 'app-register-company-page',
  standalone: true,
  imports: [
    RegisterProductFormComponent,
    SidebarDashboardComponent,
    RegisterCompanyFormComponent
  ],
  templateUrl: './register-company-page.component.html',
  styleUrl: './register-company-page.component.css'
})
export class RegisterCompanyPageComponent {

}
