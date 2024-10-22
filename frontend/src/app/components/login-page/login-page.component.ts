import { Component } from '@angular/core';
import { HeaderLoginComponent } from '../login-header/header-login.component';
import { LoginFormComponent } from '../login-form/login-form.component';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [HeaderLoginComponent, LoginFormComponent],
  templateUrl: './login-page.component.html',
})
export class LoginPageComponent {}
