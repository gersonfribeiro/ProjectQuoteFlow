import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";
import {CommonModule , NgForOf} from "@angular/common";
import { ApiUserService } from "../../services/api-user.service";
import { ApiQuotationService } from "../../services/api-quotation.service";
import { DatePipe } from '@angular/common';
import { ApiProductService } from "../../services/api-product.service";

@Component({
  selector: 'app-see-quotations-form',
  standalone: true,
  imports: [
    RouterLink,
    CommonModule,
    NgForOf
  ],
  templateUrl: './see-quotations-form.component.html',
  styleUrl: './see-quotations-form.component.css',
  providers: [DatePipe]
})
export class SeeQuotationsFormComponent {
  quotations: any[] = [];

  constructor(private apiUserService: ApiUserService, private apiQuotationService: ApiQuotationService, private datePipe: DatePipe, private apiProductService: ApiProductService) {
    }

  ngOnInit(): void {
        // Carrega as cotações do backend
        const userId = localStorage.getItem('userId');

        this.apiUserService.getUserById(userId).subscribe(
                response => {
                    const companyId = response.id_empresa;

                  this.apiQuotationService.getQuotationsByCompanyId(companyId).subscribe({
                    next: (quotations) => {
                      this.quotations = quotations.map(quotation => ({
                        ...quotation,
                        data: this.datePipe.transform(quotation.data, 'dd/MM/yy HH:mm')
                      }));

                      this.productDetails();
                    },
                    error: (error) => {
                      console.error('Erro ao carregar cotações:', error);
                    }
                  });
                }
        );
      }

  productDetails(): void {
      const userId = localStorage.getItem('userId');

      this.apiUserService.getUserById(userId).subscribe(
        response => {
          const companyId = response.id_empresa;

          // Iterando sobre as cotações e produtos para fazer a requisição para cada produto
          this.quotations.forEach(quotation => {
            quotation.produtos.forEach((product : any) => {
              const idProduto = product.id_produto;

              // Fazendo a requisição para cada id_produto
              this.apiProductService.getProductById(companyId, idProduto).subscribe(
                productDetails => {
                  const produto = productDetails;
                  product.sku = produto.sku;
                  product.descricao = produto.descricao;
                  product.variacao = produto.variacao;
                  product.observacao = produto.observacao;
                },
                error => {
                  console.error('Erro ao carregar produto:', error);
                }
              );
            });
          });
        }
      );
    }

}
