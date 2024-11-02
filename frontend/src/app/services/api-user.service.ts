import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {Usuario} from "../models/user.model";

@Injectable({
  providedIn: 'root'
})
export class ApiUserService {
  private apiUrlRegisterUser = 'http://localhost:8080/usuarios/registrar';
  private apiUrlDeleteUser = 'http://localhost:8080/usuarios';
  // Atualizar nome
  private apiUrlUpdateName // fazer
  // Atualizar email
  private apiUrlUpdateEmail // fazer
  // Inseriu telefone 1 vez
  private apiUrlPhone = 'http://localhost:8080/usuarios'; // <-------------------- TELEFONE
  // Atualizar telefone
  private apiUrlUpdatePhone // fazer

  constructor(private http: HttpClient) {
  }

  // Método para registrar um novo usuário
  registerUser(user: Usuario): Observable<any> {
    return this.http.post(this.apiUrlRegisterUser, user).pipe(
      catchError(this.handleError)
    );
  }

  // Método para deletar um usuário pelo ID
  deleteUser(userId: string | null): Observable<any> {
    return this.http.delete(`${this.apiUrlDeleteUser}/${userId}`).pipe(
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