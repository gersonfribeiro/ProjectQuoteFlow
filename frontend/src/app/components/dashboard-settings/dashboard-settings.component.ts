import {Component} from '@angular/core';
import {SidebarDashboardComponent} from "../sidebar-dashboard/sidebar-dashboard.component";
import {FormSettingsComponent} from "../form-settings/form-settings.component";

@Component({
  selector: 'app-dashboard-settings',
  standalone: true,
  imports: [SidebarDashboardComponent, FormSettingsComponent],
  templateUrl: './dashboard-settings.component.html',
  styleUrl: './dashboard-settings.component.css'
})
export class DashboardSettingsComponent {

}
