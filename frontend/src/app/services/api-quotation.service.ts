import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";

@Injectable({
  providedIn: 'root',
})
export class ApiQuotationService {

  private apiUrlQuotation = 'http://localhost:8080/cotacoes';

  constructor(private http: HttpClient) {
  }

  // Método para solicitar uma cotação usando o ID de cotação e os dados da cotação
  requestQuotation(quotationId: string, quotationData: any): Observable<any> {
    return this.http.post(`${this.apiUrlQuotation}/${quotationId}`, quotationData)
      .pipe(
        catchError(this.handleError)
      );
  }

  // O usuário precisa ter empresa cadastrada
  // O usuário precisar ter produtos criados com SKU para traze-los
  // Podemos criar um dropdown com os SKUS cadastrados na página de produtos
  // e mostrar na página de cotações, o que acha? No caso
  // ele não vai poder digitar um SKU na cotações apenas escolher o SKU que existe cadastrado, não
  // porque no caso a gente pode entrar em outro usuario é trazer do banco esses dados do SKU,
  // meio que todos os usuários vao alimentar a tabela de produtos e qualquer empresa consegue trazer
  // esses dados

  // Método para obter as cotações de uma outra empresa usando o ID da empresa
  getQuotationsByCompanyId(companyId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrlQuotation}/empresa/${companyId}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Função para tratamento de erro
  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Erro desconhecido';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Erro de rede: ${error.error.message}`;
    } else {
      switch (error.status) {
        case 404:
          errorMessage = 'Cotação não encontrada.';
          break;
        case 409:
          errorMessage = 'Conflito ao solicitar cotação.';
          break;
        case 500:
          errorMessage = 'Erro no servidor. Tente novamente mais tarde.';
          break;
        default:
          errorMessage = 'Falha ao solicitar cotação. Tente novamente.';
      }
    }
    return throwError(() => new Error(errorMessage));
  }
}
