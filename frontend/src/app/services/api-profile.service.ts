import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {Usuario} from "../models/user.model";

@Injectable({
  providedIn: 'root'
})
export class ApiProfileService {
  private apiUrl = 'http://localhost:8080/usuarios';

  constructor(private http: HttpClient) {
  }

  getUser(userId: string): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.apiUrl}/${userId}`.pipe(
      catchError(this.handleError);
    );
  }

  updateUser(userId: string, updatedData: Partial<Usuario>): Observable<Usuario> {
    return this.http.put<Usuario>(`${this.apiUrl}/${userId}`, updatedData).pipe(
      catchError(this.handleError);
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
