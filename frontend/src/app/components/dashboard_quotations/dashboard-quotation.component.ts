import { Component } from '@angular/core';
import { SidebarDashboardComponent } from '../sidebar_dashboard/sidebar-dashboard.component';
import { FormDashboardComponent } from '../quotation_form/quotation-form-dashboard.component';

@Component({
  selector: 'app-dashboard-quotation',
  standalone: true,
  imports: [SidebarDashboardComponent, FormDashboardComponent],
  templateUrl: './dashboard-quotation.component.html',
  styleUrl: './dashboard-quotation.component.css',
})
export class DashboardQuotationComponent {}
