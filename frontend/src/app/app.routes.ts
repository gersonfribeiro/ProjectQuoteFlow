import { Routes } from '@angular/router';
import { RegisterPageComponent } from './components/register-page/register-page.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { ForgotPasswordPageComponent } from './components/forgot-password-page/forgot-password-page.component';
import { DashboardQuotationsComponent } from './components/dashboard-quotations/dashboard-quotations.component';
import { DashboardNotificationsComponent } from './components/dashboard-notifications/dashboard-notifications.component';
import { DashboardProfileComponent } from './components/dashboard-profile/dashboard-profile.component';
import { NotificationsHistoricComponent } from './components/notifications-historic/notifications-historic.component';
import { DashboardSettingsComponent } from './components/dashboard-settings/dashboard-settings.component';
import { RegisterCompanyPageComponent } from "./components/register-company-page/register-company-page.component";
import { DashboardChartComponent } from "./components/dashboard-chart/dashboard-chart.component";
import { SeeProductsPageComponent } from "./components/see-products-page/see-products-page.component";
import { RegisterProductsPageComponent } from "./components/register-products-page/register-products-page.component";
import { SeeQuotationsPageComponent } from "./components/see-quotations-page/see-quotations-page.component";
import { authGuard } from './auth/auth.guard';
import { companyGuard } from './auth/company.guard';

export const routes: Routes = [
  { path: '', component: RegisterPageComponent }, // Home
  { path: 'login', component: LoginPageComponent }, // Página de login
  { path: 'register', component: RegisterPageComponent }, // Página de registro
  { path: 'forgot_password', component: ForgotPasswordPageComponent }, // Página de recuperar senha
  { path: 'dashboard/quotation', component: DashboardQuotationsComponent, canActivate: [authGuard, companyGuard] }, // Página de cotação
  { path: 'dashboard/notifications', component: DashboardNotificationsComponent, canActivate: [authGuard] }, // Página de notificações
  { path: 'dashboard/notifications_historic', component: NotificationsHistoricComponent, canActivate: [authGuard] }, // Página de histórico de notificações
  { path: 'dashboard/profile', component: DashboardProfileComponent, canActivate: [authGuard] }, // Página de perfil
  { path: 'dashboard/settings', component: DashboardSettingsComponent, canActivate: [authGuard] }, // Página de configurações
  { path: 'dashboard/panel', component: DashboardChartComponent, canActivate: [authGuard] }, // Página de painel
  { path: 'dashboard/register-companies', component: RegisterCompanyPageComponent, canActivate: [authGuard] }, // Página de cadastro de empresas
  { path: 'dashboard/register-products', component: RegisterProductsPageComponent, canActivate: [authGuard, companyGuard] }, // Página de cadastro de produtos
  { path: 'dashboard/products', component: SeeProductsPageComponent, canActivate: [authGuard, companyGuard] }, // Página de produtos cadastrados
  { path: 'dashboard/quotations', component: SeeQuotationsPageComponent, canActivate: [authGuard, companyGuard] }, // Página de cotações cadastradas

  // Outras rotas
];
