import { Component } from '@angular/core';
import { SidebarDashboardComponent } from "../sidebar/sidebar-dashboard.component";
import { ChartComponent } from "../chart/chart.component";

@Component({
  selector: 'app-dashboard-chart',
  standalone: true,
  imports: [SidebarDashboardComponent, ChartComponent],
  templateUrl: './dashboard-chart.component.html',
  styleUrl: './dashboard-chart.component.css'
})
export class DashboardChartComponent {

}
