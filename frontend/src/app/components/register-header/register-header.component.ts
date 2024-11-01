import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-register-header',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './register-header.component.html',
  styleUrl: './register-header.component.css',
})
export class RegisterHeaderComponent {
  isMenuOpen = false;

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }
}
