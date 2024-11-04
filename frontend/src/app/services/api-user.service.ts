import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {Usuario} from "../models/user.model";

@Injectable({
  providedIn: 'root'
})
export class ApiUserService {
  // URL geral
  private apiUrlUser = 'http://localhost:8080/usuarios';
  // URL de registro de usuarios
  private apiUrlRegisterUser = 'http://localhost:8080/usuarios/registrar';
  // URL de login de usuarios
  private apiUrlLoginUser = 'http://localhost:8080/usuarios/login'

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
    return this.http.delete(`${this.apiUrlUser}/${userId}`).pipe(
      catchError(this.handleError)
    );
  }

  // Método para pegar um usuário pelo ID
  getUserById(userId: string): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.apiUrlUser}/${userId}`).pipe(
      catchError(this.handleError)
    );
  }

  // Método para atualizar informações de um usuário
  updateUser(userId: string, updatedData: Partial<Usuario>): Observable<Usuario> {
    return this.http.put<Usuario>(`${this.apiUrlUser}/${userId}`, updatedData).pipe(
      catchError(this.handleError)
    );
  }

  // Método para login de usuário
  loginUser(credentials: { email: string; password: string }): Observable<any> {
    return this.http.post(`${this.apiUrlLoginUser}`, credentials).pipe(
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
