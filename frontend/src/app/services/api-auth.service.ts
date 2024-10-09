import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiAuthService {
  private apiUrlAuth = 'http://localhost:8080/usuarios';
  // Esta propriedade armazena a URL base da API de usuários, que será utilizada nas requisições HTTP para registrar e deletar usuários.

  constructor(private http: HttpClient) {}
  // O construtor injeta uma instância do 'HttpClient' no serviço, permitindo que este serviço faça requisições HTTP à API.

  // Método para registrar um novo usuário com base nos dados fornecidos (nome, email, senha).
  registerUser(user: any): Observable<any> {
    return this.http.post(this.apiUrlAuth, user);
    // Realiza uma requisição POST para a API, enviando os dados do usuário.
    // Retorna um Observable que pode ser assinado para lidar com a resposta da API.
  }

  // Método para deletar um usuário com base no seu ID.
  deleteUser(userId: number): Observable<any> {
    return this.http.delete(`${this.apiUrlAuth}/${userId}`);
    // Realiza uma requisição DELETE para a API, removendo o usuário especificado pelo ID.
    // Retorna um Observable que pode ser assinado para lidar com a confirmação da deleção.
  }
}
