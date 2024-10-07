import { Component } from '@angular/core';
import { HeaderRegisterComponent } from '../header_register/header-register.component';
import { RegisterFormComponent } from '../register_form/register-form.component';

@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [HeaderRegisterComponent, RegisterFormComponent],
  templateUrl: './register-page.component.html',
})
export class RegisterPageComponent {}
