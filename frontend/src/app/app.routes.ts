import { Routes } from '@angular/router';
import { RegisterPageComponent } from './components/register_page/register-page.component';
import { LoginPageComponent } from './components/login_page/login-page.component';
import { ForgotPasswordPageComponent } from './components/forgot_password_page/forgot-password-page.component';
import { DashboardQuotationComponent } from './components/dashboard_quotations/dashboard-quotation.component';
import { DashboardNotificationsComponent } from './components/dashboard_notifications/dashboard-notifications.component';
import { DashboardProfileComponent } from './components/dashboard_profile/dashboard-profile.component';
import { NotificationsHistoricComponent } from './components/notifications_historic/notifications-historic.component';
import { DashboardConfigurationsComponent } from './components/dashboard_configurations/dashboard-configurations.component';
import { DashboardChartComponent } from './components/dashboard_chart/dashboard-chart.component';

export const routes: Routes = [
  { path: '', component: RegisterPageComponent }, // Home
  { path: 'login', component: LoginPageComponent }, // Página de login
  { path: 'register', component: RegisterPageComponent }, // Página de registro
  { path: 'forgot_password', component: ForgotPasswordPageComponent }, // Página de recuperar senha
  { path: 'dashboard/quotation', component: DashboardQuotationComponent }, // Página de cotação
  { path: 'dashboard/notifications', component: DashboardNotificationsComponent }, // Página de notificações
  { path: 'dashboard/notifications_historic', component: NotificationsHistoricComponent }, // Página de histórico de notificações
  { path: 'dashboard/profile', component: DashboardProfileComponent }, // Página de perfil
  { path: 'dashboard/configurations', component: DashboardConfigurationsComponent }, // Página de configurações
  { path: 'dashboard/panel', component: DashboardChartComponent}
  // Outras rotas
];
