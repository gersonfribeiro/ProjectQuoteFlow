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
  suppliers: any;

  @ViewChild('notificationAlert') notificationAlert!: ElementRef;
  productForm: any;
  products: { skuCode: string, quantity: number }[] = [];

  constructor(private fb: FormBuilder, private toastr: ToastrService, private apiQuotationService: ApiQuotationService, private apiUserService: ApiUserService, private apiProductService: ApiProductService) {
    this.quotationForm = this.fb.group({
      skuCode: ['', [Validators.required]], // Corrigido para chamar o método corretamente
      quantity: [null, [Validators.required, Validators.min(1)]],
      observation: [''],
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

  onSubmit() {
      if (this.quotationForm.valid) {
        const userId = localStorage.getItem('userId');
        const skuCode = this.quotationForm.value.skuCode;
        const quantity = this.quotationForm.value.quantity;

            this.apiUserService.getUserById(userId).subscribe(
                response1 => {
                    const quotationData = {
                      id_empresa: response1.id_empresa
                    };

                    this.apiProductService.getProductBySKU(quotationData.id_empresa, skuCode).subscribe(
                      response2 => {
                        this.apiQuotationService.requestQuotation(quotationData).subscribe(
                          response3 => {
                            const productToQuotationData = {
                                id_produto: response2.id_produto,
                                quantidade: quantity
                              }

                            const quotationId = response3.id_cotacao;

                            this.apiQuotationService.registerProductOnQuotation(quotationId, productToQuotationData).subscribe(
                                response4 => {
                                    this.toastr.success('Produto adicionado na sua cotação!');
                                  },
                                error4 => {
                                    this.toastr.error('Erro ao adicionar produto à cotação.');
                                  }
                              );
                          },
                        error3 => {
                            this.toastr.error('Erro ao criar cotação.');
                          }
                        );
                      },
                      error2 => {
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
