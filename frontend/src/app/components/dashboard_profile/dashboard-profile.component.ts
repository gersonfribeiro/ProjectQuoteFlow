import { Component } from '@angular/core';
import { SidebarDashboardComponent } from "../sidebar_dashboard/sidebar-dashboard.component";
import { NotificationsFormComponent } from "../notifications_form/notifications-form.component";
import { ProfileFormComponent } from "../profile_form/profile-form.component";

@Component({
  selector: 'app-dashboard-profile',
  standalone: true,
  imports: [SidebarDashboardComponent, NotificationsFormComponent, ProfileFormComponent],
  templateUrl: './dashboard-profile.component.html',
  styleUrl: './dashboard-profile.component.css'
})
export class DashboardProfileComponent {

}
