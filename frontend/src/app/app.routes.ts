import {Routes} from '@angular/router';
import {RegisterPageComponent} from './components/register-page/register-page.component';
import {LoginPageComponent} from './components/login-page/login-page.component';
import {ForgotPasswordPageComponent} from './components/forgot-password-page/forgot-password-page.component';
import {DashboardQuotationsComponent} from './components/dashboard-quotations/dashboard-quotations.component';
import {DashboardNotificationsComponent} from './components/dashboard-notifications/dashboard-notifications.component';
import {DashboardProfileComponent} from './components/dashboard-profile/dashboard-profile.component';
import {NotificationsHistoricComponent} from './components/notifications-historic/notifications-historic.component';
import {DashboardSettingsComponent} from './components/dashboard-settings/dashboard-settings.component';
import {ChartComponent} from './components/chart/chart.component';
import {RegisterProductPageComponent} from "./components/register-product-page/register-product-page.component";
import {RegisterCompanyPageComponent} from "./components/register-company-page/register-company-page.component";

export const routes: Routes = [
  {path: '', component: RegisterPageComponent}, // Home
  {path: 'login', component: LoginPageComponent}, // Página de login
  {path: 'register', component: RegisterPageComponent}, // Página de registro
  {path: 'forgot_password', component: ForgotPasswordPageComponent}, // Página de recuperar senha
  {path: 'dashboard/quotation', component: DashboardQuotationsComponent}, // Página de cotação
  {path: 'dashboard/notifications', component: DashboardNotificationsComponent}, // Página de notificações
  {path: 'dashboard/notifications_historic', component: NotificationsHistoricComponent}, // Página de histórico de notificações
  {path: 'dashboard/profile', component: DashboardProfileComponent}, // Página de perfil
  {path: 'dashboard/settings', component: DashboardSettingsComponent}, // Página de configurações
  {path: 'dashboard/panel', component: ChartComponent}, // Página de painel
  {path: 'dashboard/register-companies', component: RegisterCompanyPageComponent}, // Página de cadastro de empresas
  {path: 'dashboard/register-products', component: RegisterProductPageComponent}, // Página de cadastro de produtos
  // Outras rotas
];
