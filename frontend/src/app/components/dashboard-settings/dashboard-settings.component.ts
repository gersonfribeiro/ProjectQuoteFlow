import {Component} from '@angular/core';
import {SidebarDashboardComponent} from "../sidebar/sidebar-dashboard.component";
import {ConfigurationsFormComponent} from "../form-settings/configurations-form.component";

@Component({
  selector: 'app-dashboard-settings',
  standalone: true,
  imports: [SidebarDashboardComponent, ConfigurationsFormComponent],
  templateUrl: './dashboard-settings.component.html',
  styleUrl: './dashboard-settings.component.css'
})
export class DashboardSettingsComponent {

}
