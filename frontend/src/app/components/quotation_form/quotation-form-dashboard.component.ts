import { CommonModule } from '@angular/common';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { NgxMaskDirective, NgxMaskPipe } from 'ngx-mask';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-quotation-form-dashboard',
  standalone: true,
  imports: [NgxMaskDirective, NgxMaskPipe, ReactiveFormsModule, CommonModule],
  templateUrl: './quotation-form-dashboard.component.html',
  styleUrls: ['./quotation-form-dashboard.component.css'],
})
export class FormDashboardComponent implements OnInit {
  quotationForm: FormGroup;
  showNotificationAlert: boolean = false;
  suppliers: any;

  @ViewChild('notificationAlert') notificationAlert!: ElementRef;
  productForm: any;
  products: any;

  constructor(private fb: FormBuilder, private toastr: ToastrService) {
    this.quotationForm = this.fb.group({
      skuCode: ['', [Validators.required]], // Corrigido para chamar o método corretamente
      quantity: [null, [Validators.required, Validators.min(1)]],
      observation: [''],
    });
  }

  ngOnInit(): void {
    const quotationNotificationsPage = localStorage.getItem(
      'quotationNotificationsPage'
    );
    this.showNotificationAlert = !quotationNotificationsPage;
  }

  onSubmit() {
    if (this.quotationForm.valid) {
      this.toastr.success('Solicitação enviada com sucesso!', '', {
        positionClass: 'toast-top-right',
        progressBar: true,
        progressAnimation: 'increasing',
        timeOut: 2000,
      });

      console.log(this.quotationForm.value);
      this.quotationForm.reset(); // Limpa o formulário após o envio
    } else {
      this.showFormErrors(); // Mostra os erros caso o formulário seja inválido
    }
  }

  closeButton() {
    if (this.notificationAlert) {
      this.notificationAlert.nativeElement.classList.add('alert-closing');
      setTimeout(() => {
        this.showNotificationAlert = false;
        localStorage.setItem('quotationNotificationsPage', 'true');
      }, 250);
    }
  }

  // Método para mostrar erros nos campos do formulário
  showFormErrors() {
    this.quotationForm.markAllAsTouched(); // Marca todos os campos como tocados
  }

  onSupplierTabClick() {
    // A lógica pode ser expandida conforme necessário
    // Por enquanto, apenas loga no console
    console.log('Aba de Fornecedores clicada');
  }

  // Método para mudar a aba
  showSuppliersTab() {
    const supplierTabButton = document.getElementById('supplier-tab');
    if (supplierTabButton) {
      supplierTabButton.click(); // Simula um clique na aba "Fornecedores"
    }
  }

  onProductSubmit() {
    if (this.productForm.valid) {
      const newProduct = {
        skuCode: this.productForm.value.productSku,
        quantity: this.productForm.value.productQuantity,
      };
      this.products.push(newProduct);
      this.productForm.reset();
    }
  }

  onInputChange() {
    const skuCodeControl = this.quotationForm.get('skuCode');
    if (skuCodeControl) {
      // Transforma o valor do campo em letras maiúsculas
      skuCodeControl.setValue(skuCodeControl.value.toUpperCase(), { emitEvent: false });
    }
  }
  
}
