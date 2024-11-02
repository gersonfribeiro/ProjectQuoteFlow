import {Component} from '@angular/core';
import {ChartComponent} from "../chart/chart.component";
import {SidebarDashboardComponent} from "../sidebar-dashboard/sidebar-dashboard.component";

@Component({
  selector: 'app-dashboard-chart',
  standalone: true,
  imports: [SidebarDashboardComponent, ChartComponent, SidebarDashboardComponent],
  templateUrl: './dashboard-chart.component.html',
  styleUrl: './dashboard-chart.component.css'
})
export class DashboardChartComponent {

}
