import { Component } from '@angular/core';
import { HeaderRegisterComponent } from '../register-header/header-register.component';
import { RegisterFormComponent } from '../register-form/register-form.component';

@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [HeaderRegisterComponent, RegisterFormComponent],
  templateUrl: './register-page.component.html',
})
export class RegisterPageComponent {}
