import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, pipe, throwError, map} from 'rxjs';
import {Usuario} from "../models/user.model";

@Injectable({
  providedIn: 'root'
})
export class ApiCompanyService {
  private apiUrlCompany = 'http://localhost:8080/empresas';

  constructor(private http: HttpClient) {
  }

  registerCompany(companyData: any): Observable<{ id_empresa: string }> {
    return this.http.post<{ id_empresa: string }>(this.apiUrlCompany, companyData)
  }

  getCompanyById(companyId: string | null): Observable<any> {
      return this.http.get<any>(`${this.apiUrlCompany}/id/${companyId}`).pipe(
        catchError(this.handleError)
      );
    }

  // Função de tratamento de erro
    private handleError(error: HttpErrorResponse) {
      let errorMessage = 'Erro desconhecido';
      if (error.error instanceof ErrorEvent) {
        errorMessage = `Erro de rede: ${error.error.message}`;
      } else {
        switch (error.status) {
          case 409:
            errorMessage = 'Este email já está registrado. Por favor, utilize outro.';
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

