import { Component } from '@angular/core';
import { HeaderLoginComponent } from '../login-header/login-header.component';
import { LoginFormComponent } from '../login-form/login-form.component';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [HeaderLoginComponent, LoginFormComponent],
  templateUrl: './login-page.component.html',
})
export class LoginPageComponent {}
