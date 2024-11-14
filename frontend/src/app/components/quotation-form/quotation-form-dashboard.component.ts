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
  isQuotationStarted: boolean = false;
  isAddButtonEnabled: boolean = false;
  quotationId: string = '';

  @ViewChild('notificationAlert') notificationAlert!: ElementRef;
  productForm: any;

  constructor(private fb: FormBuilder, private toastr: ToastrService, private apiQuotationService: ApiQuotationService, private apiUserService: ApiUserService, private apiProductService: ApiProductService) {
    this.quotationForm = this.fb.group({
      skuCode: [{ value: '', disabled: true}, [Validators.required]], // Corrigido para chamar o método corretamente
      quantity: [{ value: null, disabled: true}, [Validators.required, Validators.min(1)]],
    });
  }

  ngOnInit(): void {

  }

  // Método para mostrar erros nos campos do formulário
  showFormErrors() {
    this.quotationForm.markAllAsTouched(); // Marca todos os campos como tocados
  }

  onInputChange() {
    const skuCodeControl = this.quotationForm.get('skuCode');
    if (skuCodeControl) {
      // Transforma o valor do campo em letras maiúsculas
      skuCodeControl.setValue(skuCodeControl.value.toUpperCase(), {emitEvent: false});
    }
  }

// Responsável por iniciar a cotação
  createQuotation() {
      const confirmation = window.confirm('Cadastre todos os produtos antes de iniciar a cotação.');

      if (confirmation) {
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
                      },
                    error => {
                        console.log('Erro ao iniciar cotação.');
                      }
                  );
              }
          );
      }
    }

// Responsável por adicionar os produtos na cotação
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
                          }

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

        this.quotationForm.reset(); // Limpa o formulário após o envio
      } else {
        this.showFormErrors(); // Mostra os erros caso o formulário seja inválido
      }
    }

}
