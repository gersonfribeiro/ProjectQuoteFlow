import {Component} from '@angular/core';
import {SidebarDashboardComponent} from "../sidebar/sidebar-dashboard.component";
import {ConfigurationsFormComponent} from "../form-settings/configurations-form.component";

@Component({
  selector: 'app-dashboard-configurations',
  standalone: true,
  imports: [SidebarDashboardComponent, ConfigurationsFormComponent],
  templateUrl: './dashboard-configurations.component.html',
  styleUrl: './dashboard-configurations.component.css'
})
export class DashboardConfigurationsComponent {

}
