import {Component} from '@angular/core';
import {ForgotPasswordFormComponent} from '../forgot-password-form/forgot-password-form.component';


@Component({
  selector: 'app-forgot-password-page',
  standalone: true,
  imports: [ForgotPasswordFormComponent],
  templateUrl: './forgot-password-page.component.html',
})
export class ForgotPasswordPageComponent {
}
