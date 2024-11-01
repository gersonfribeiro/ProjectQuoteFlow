import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-login-header',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './login-header.component.html',
  styleUrls: ['./login-header.component.css'],
})
export class HeaderLoginComponent {
  isMenuOpen = false;

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }
}
