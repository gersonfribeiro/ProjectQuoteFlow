import {Component} from '@angular/core';
import {RegisterCompanyFormComponent} from "../register-company-form/register-company-form.component";
import {SidebarDashboardComponent} from "../sidebar-dashboard/sidebar-dashboard.component";
import {SeeProductsFormComponent} from "../see-products-form/see-products-form.component";

@Component({
  selector: 'app-see-products-page',
  standalone: true,
  imports: [
    RegisterCompanyFormComponent,
    SidebarDashboardComponent,
    SeeProductsFormComponent,
    SeeProductsFormComponent,
  ],
  templateUrl: './see-products-page.component.html',
  styleUrl: './see-products-page.component.css'
})
export class SeeProductsPageComponent {

}
