import {Component} from '@angular/core';
import {NgForOf} from "@angular/common";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-see-products-form',
  standalone: true,
  imports: [
    NgForOf,
    RouterLink
  ],
  templateUrl: './see-products-form.component.html',
  styleUrl: './see-products-form.component.css'
})
export class SeeProductsFormComponent {
  produtos: any;

}
