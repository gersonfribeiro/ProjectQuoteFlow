import {CommonModule} from '@angular/common';
import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import {NgxMaskDirective, NgxMaskPipe} from 'ngx-mask';
import {ToastrService} from 'ngx-toastr';
import {ApiQuotationService} from "../../services/api-quotation.service";
import {ApiUserService} from "../../services/api-user.service";
import {ApiProductService} from "../../services/api-product.service";
import {RouterLink} from "@angular/router";

declare var bootstrap: any;

@Component({
  selector: 'app-quotation-form-dashboard',
  standalone: true,
  imports: [NgxMaskDirective, NgxMaskPipe, ReactiveFormsModule, CommonModule, RouterLink],
  templateUrl: './quotation-form-dashboard.component.html',
  styleUrls: ['./quotation-form-dashboard.component.css'],
})
export class FormDashboardComponent implements OnInit {
  quotationForm: FormGroup;
  isQuotationStarted: boolean = false;
  isAddButtonEnabled: boolean = false;
  quotationId: string = '';

  productForm: any;

  constructor(private fb: FormBuilder, private toastr: ToastrService, private apiQuotationService: ApiQuotationService, private apiUserService: ApiUserService, private apiProductService: ApiProductService) {
    this.quotationForm = this.fb.group({
      skuCode: [{ value: '', disabled: true}, [Validators.required]],
      quantity: [{ value: null, disabled: true}, [Validators.required, Validators.min(1)]],
    });
  }

  ngOnInit(): void {}

  showFormErrors() {
    this.quotationForm.markAllAsTouched();
  }

  onInputChange() {
    const skuCodeControl = this.quotationForm.get('skuCode');
    if (skuCodeControl) {
      skuCodeControl.setValue(skuCodeControl.value.toUpperCase(), {emitEvent: false});
    }
  }

  openQuotation() {
      this.isQuotationStarted = true;
      this.isAddButtonEnabled = true;

      this.quotationForm.get('skuCode')?.enable();
      this.quotationForm.get('quantity')?.enable();

      const userId = localStorage.getItem('userId');

      this.apiUserService.getUserById(userId).subscribe(
        response => {
          const quotationData = {id_empresa: response.id_empresa};

          this.apiQuotationService.requestQuotation(quotationData).subscribe(
            response => {
              console.log('Cotação iniciada!');
              this.quotationId = response.id_cotacao;

              const modal = document.getElementById('openQuotationModal') as any;
              const modalInstance = bootstrap.Modal.getInstance(modal);
              modalInstance.hide();
            },
            error => {
              console.log('Erro ao iniciar cotação.');
            }
          );
        }
      );
    }

  // Método para parar a cotação
  closeQuotation() {

      this.isQuotationStarted = false;
      this.isAddButtonEnabled = false;

      this.quotationForm.get('skuCode')?.disable();
      this.quotationForm.get('quantity')?.disable();

       this.toastr.success('Cotação finalizada!');

       const modal = document.getElementById('closeQuotationModal') as any;
       const modalInstance = bootstrap.Modal.getInstance(modal);
       modalInstance.hide();
    }

  onSubmit() {
    if (this.quotationForm.valid) {
      const userId = localStorage.getItem('userId');
      const sku = this.quotationForm.value.skuCode;
      const quantidade = this.quotationForm.value.quantity;

      this.apiUserService.getUserById(userId).subscribe(
        response => {
          this.apiProductService.getProductBySKU(response.id_empresa, sku).subscribe(
            response => {
              const quotationProductData = {
                id_produto: response.id_produto,
                quantidade: quantidade
              };

              this.apiQuotationService.registerProductOnQuotation(this.quotationId, quotationProductData).subscribe(
                response => {
                  this.toastr.success('Produto adicionado à sua cotação!');
                },
                error => {
                  this.toastr.error('Erro ao adicionar produto à cotação.');
                  console.log(quotationProductData);
                }
              );
            },
            error => {
              this.toastr.error('SKU do produto não encontrado!');
            }
          );
        }
      );

      this.quotationForm.reset();
    } else {
      this.showFormErrors();
    }
  }
}
