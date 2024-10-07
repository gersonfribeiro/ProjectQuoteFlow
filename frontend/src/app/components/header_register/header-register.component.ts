import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-header-register',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './header-register.component.html',
  styleUrl: './header-register.component.css',
})
export class HeaderRegisterComponent {
  isMenuOpen = false;

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }
}
