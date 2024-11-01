import {Component} from '@angular/core';
import {SidebarDashboardComponent} from '../sidebar/sidebar-dashboard.component';
import {FormDashboardComponent} from '../quotation-form/quotation-form-dashboard.component';

@Component({
  selector: 'app-dashboard-quotations',
  standalone: true,
  imports: [SidebarDashboardComponent, FormDashboardComponent],
  templateUrl: './dashboard-quotations.component.html',
  styleUrl: './dashboard-quotations.component.css',
})
export class DashboardQuotationsComponent {
}
