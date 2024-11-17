import {CommonModule} from '@angular/common';
import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import { FormsModule } from '@angular/forms';
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
import {ApiCompanyService} from "../../services/api-company.service";

declare var bootstrap: any;

@Component({
  selector: 'app-quotation-form-dashboard',
  standalone: true,
  imports: [NgxMaskDirective, NgxMaskPipe, ReactiveFormsModule, CommonModule, RouterLink, FormsModule],
  templateUrl: './quotation-form-dashboard.component.html',
  styleUrls: ['./quotation-form-dashboard.component.css'],
})
export class FormDashboardComponent implements OnInit {
  quotationForm: FormGroup;
  isQuotationStarted: boolean = false;
  isAddButtonEnabled: boolean = false;
  quotationId: string = '';
  empresas: any[] = [];
  selectedEmpresa: string = '';

  productForm: any;

  constructor(private fb: FormBuilder, private toastr: ToastrService, private apiQuotationService: ApiQuotationService, private apiUserService: ApiUserService, private apiProductService: ApiProductService, private apiCompanyService: ApiCompanyService) {
    this.quotationForm = this.fb.group({
      skuCode: [{value: '', disabled: true}, [Validators.required]],
      quantity: [{value: null, disabled: true}, [Validators.required, Validators.min(1)]],
    });
  }

  ngOnInit(): void {
    this.loadEmpresas();
  }

  loadEmpresas() {
      this.apiCompanyService.getCompanies().subscribe(
        (response) => {
          this.empresas = response;
          console.log('Empresas carregadas:', response);
        },
        (error) => {
          this.toastr.error('Erro ao carregar as empresas.');
        }
      );
  }

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
    localStorage.setItem('isQuotationStarted', 'true');
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
//     const userId = localStorage.getItem('userId');
//
//      this.apiUserService.getUserById(userId).subscribe(
//         response => {
//             const updatedQuotationData = {
//                 status: "RESPOSTA_PENDENTE",
//                 id_empresa: response.id_empresa
//               };
//
//             this.apiQuotationService.putQuotation(this.quotationId, updatedQuotationData).subscribe(
//                 response => {
//                     this.toastr.success('Cotação finalizada!');
//                   }
//               );
//           }
//        );

    this.isQuotationStarted = false;
    localStorage.setItem('isQuotationStarted', 'false');
    this.isAddButtonEnabled = false;

    this.quotationForm.get('skuCode')?.disable();
    this.quotationForm.get('quantity')?.disable();

    const modal = document.getElementById('closeQuotationModal') as any;
    const modalInstance = bootstrap.Modal.getInstance(modal);
    modalInstance.hide();

    // Ativar a segunda aba
      const secondTabLink = document.querySelector('a[href="#tab-second"]');
      const firstTabLink = document.querySelector('a[href="#tab-quotation"]');

      if (secondTabLink && firstTabLink) {
        secondTabLink.classList.add('active');
        firstTabLink.classList.remove('active');

        // Alternar o conteúdo da aba
        const firstTabContent = document.querySelector('#tab-quotation');
        const secondTabContent = document.querySelector('#tab-second');
        if (firstTabContent && secondTabContent) {
          firstTabContent.classList.remove('show', 'active');
          secondTabContent.classList.add('show', 'active');
        }
      }
  }

  inserirDestinatarioNaCotacao() {
      const destinatarioData = {
          id_destinatario: this.selectedEmpresa
        };

      this.apiQuotationService.insertDestinatarioInCotacao(this.quotationId, destinatarioData).subscribe(
          response => {
              this.toastr.success("Cotação enviada para o destinatário");
            },
          error => {
              this.toastr.error("Erro ao enviar cotação para o destinatário");
            }
        );
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
