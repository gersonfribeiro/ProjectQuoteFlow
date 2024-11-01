import { Component } from '@angular/core';
import { RegisterHeaderComponent } from '../register-header/register-header.component';
import { RegisterFormComponent } from '../register-form/register-form.component';

@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [RegisterHeaderComponent, RegisterFormComponent],
  templateUrl: './register-page.component.html',
})
export class RegisterPageComponent {}
