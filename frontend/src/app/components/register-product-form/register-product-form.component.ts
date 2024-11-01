import {Component} from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators
} from "@angular/forms";
import {NgIf} from "@angular/common";
import {NgxMaskDirective} from "ngx-mask";
import {HttpClient} from "@angular/common/http";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-register-product-form',
  standalone: true,
  imports: [
    FormsModule,
    NgIf,
    NgxMaskDirective,
    ReactiveFormsModule
  ],
  templateUrl: './register-product-form.component.html',
  styleUrl: './register-product-form.component.css'
})
export class RegisterProductFormComponent {
  registerProductForm: FormGroup;

  constructor(private http: HttpClient, private fb: FormBuilder, private toastr: ToastrService) {
    this.registerProductForm = this.fb.group({
      productCategory: ['', Validators.required],
      sku: ['', [Validators.required, Validators.minLength(6)]],
      description: [''],
      observation: ['']
    });
  }

  // Submissão do formulário
  onSubmit() {
    if (this.registerProductForm.valid) {
      console.log('Formulário enviado:', this.registerProductForm.value);
      this.toastr.success('Produto cadastrado com sucesso!');
      this.registerProductForm.disable();
    } else {
      console.log('Formulário inválido');
      this.registerProductForm.markAllAsTouched();
    }
  }
}
