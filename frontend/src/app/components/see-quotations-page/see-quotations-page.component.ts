import { Component } from '@angular/core';
import {RegisterFormComponent} from "../register-form/register-form.component";
import {RegisterHeaderComponent} from "../register-header/register-header.component";
import {RegisterCompanyFormComponent} from "../register-company-form/register-company-form.component";
import {SidebarDashboardComponent} from "../sidebar-dashboard/sidebar-dashboard.component";
import {SeeQuotationsFormComponent} from "../see-quotations-form/see-quotations-form.component";

@Component({
  selector: 'app-see-quotations-page',
  standalone: true,
  imports: [
      RegisterFormComponent,
      RegisterHeaderComponent,
      RegisterCompanyFormComponent,
      SidebarDashboardComponent,
      SeeQuotationsFormComponent
    ],
  templateUrl: './see-quotations-page.component.html',
  styleUrl: './see-quotations-page.component.css'
})
export class SeeQuotationsPageComponent {

}
