import { Component } from '@angular/core';
import { SidebarDashboardComponent } from "../sidebar_dashboard/sidebar-dashboard.component";
import { ConfigurationsFormComponent } from "../configurations_form/configurations-form.component";

@Component({
  selector: 'app-dashboard-configurations',
  standalone: true,
  imports: [SidebarDashboardComponent, ConfigurationsFormComponent],
  templateUrl: './dashboard-configurations.component.html',
  styleUrl: './dashboard-configurations.component.css'
})
export class DashboardConfigurationsComponent {

}
