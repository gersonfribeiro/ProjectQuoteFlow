import { Component } from '@angular/core';
import { SidebarDashboardComponent } from '../sidebar/sidebar-dashboard.component';
import { FormDashboardComponent } from '../quotation-form/quotation-form-dashboard.component';

@Component({
  selector: 'app-dashboard-quotation',
  standalone: true,
  imports: [SidebarDashboardComponent, FormDashboardComponent],
  templateUrl: './dashboard-quotation.component.html',
  styleUrl: './dashboard-quotation.component.css',
})
export class DashboardQuotationComponent {}
