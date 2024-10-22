import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-header-login',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './header-login.component.html',
  styleUrls: ['./header-login.component.css'],
})
export class HeaderLoginComponent {
  isMenuOpen = false;

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }
}
