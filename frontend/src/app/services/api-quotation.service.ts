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

  // Método para solicitar uma cotação usando os dados da cotação
  requestQuotation(quotationData: any): Observable<any> {
    return this.http.post(`${this.apiUrlQuotation}`, quotationData)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Método para obter as cotações de uma outra empresa usando o ID da empresa
  getQuotationsByCompanyId(companyId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrlQuotation}/empresa/${companyId}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Método para adicionar um produto à uma cotação
  registerProductOnQuotation(quotationId: string, productToQuotationData: any): Observable<any> {
      return this.http.post(`http://localhost:8080/${quotationId}/produtos/cotados`, productToQuotationData)
      .pipe(
          catchError(this.handleError)
        );
    }

  // Método para adicionar um produto à uma cotação
    putQuotation(quotationId: string, updatedQuotationData: any): Observable<any> {
        return this.http.put(`${this.apiUrlQuotation}/${quotationId}`, updatedQuotationData)
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
