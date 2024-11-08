import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { catchError, Observable, throwError } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ApiProductService {

  private apiUrlProduct = 'http://localhost:8080/empresas';

  constructor(private http: HttpClient) {}

  // Método para salvar um produto
  registerProduct(companyId: string, productData: any): Observable<any> {
    return this.http.post(`${this.apiUrlProduct}/${companyId}/produtos`, productData)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Método para obter os produtos
  getProducts(companyId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrlProduct}/${companyId}/produtos`)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Erro desconhecido';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Erro de rede: ${error.error.message}`;
    } else {
      switch (error.status) {
        case 409:
          errorMessage = 'Este produto já está registrado.';
          break;
        case 500:
          errorMessage = 'Erro no servidor. Tente novamente mais tarde.';
          break;
        default:
          errorMessage = 'Falha no registro. Tente novamente.';
      }
    }
    return throwError(() => new Error(errorMessage));
  }
}
