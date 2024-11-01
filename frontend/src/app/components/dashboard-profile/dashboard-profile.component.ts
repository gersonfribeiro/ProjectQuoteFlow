import { Component } from '@angular/core';
import { SidebarDashboardComponent } from "../sidebar-dashboard/sidebar-dashboard.component";
import { NotificationsFormComponent } from "../notifications-form/notifications-form.component";
import { ProfileFormComponent } from "../profile-form/profile-form.component";

@Component({
  selector: 'app-dashboard-profile',
  standalone: true,
  imports: [SidebarDashboardComponent, NotificationsFormComponent, ProfileFormComponent],
  templateUrl: './dashboard-profile.component.html',
  styleUrl: './dashboard-profile.component.css'
})
export class DashboardProfileComponent {

}
