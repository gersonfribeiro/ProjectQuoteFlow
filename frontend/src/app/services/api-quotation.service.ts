import { Injectable } from '@angular/core';
// O decorador '@Injectable' marca essa classe como um serviço que pode ser injetado em outros componentes ou serviços do Angular.

import { HttpClient } from '@angular/common/http';
// 'HttpClient' é um serviço do Angular que permite realizar requisições HTTP, como GET, POST, PUT e DELETE, para se comunicar com uma API.

import { Observable } from 'rxjs';
// 'Observable' é uma estrutura que permite trabalhar com dados assíncronos e fluxos de eventos, facilitando o tratamento de respostas e erros de chamadas HTTP.

@Injectable({
  providedIn: 'root',
})
// O serviço é fornecido no nível raiz da aplicação, tornando-o acessível em toda a aplicação.
// Isso garante que o Angular crie e compartilhe uma única instância do serviço em toda a aplicação, promovendo a reutilização de dados e lógica.

export class ApiQuotationService {
  // Esta classe define o serviço 'ApiQuotationService', responsável por gerenciar a comunicação com a API de cotações.

  private apiUrlUsers = 'http://localhost:8080/usuarios';
  // Esta propriedade armazena a URL base da API de usuários, que será utilizada nas requisições HTTP para registrar e deletar usuários.

  constructor(private http: HttpClient) {}
  // O construtor injeta uma instância do 'HttpClient' no serviço, permitindo que este serviço faça requisições HTTP à API.

  // Método para registrar um novo usuário com base nos dados fornecidos (nome, email, senha).
  registerUser(user: any): Observable<any> {
    return this.http.post(this.apiUrlUsers, user);
    // Realiza uma requisição POST para a API, enviando os dados do usuário.
    // Retorna um Observable que pode ser assinado para lidar com a resposta da API.
  }

  // Método para deletar um usuário com base no seu ID.
  deleteUser(userId: number): Observable<any> {
    return this.http.delete(`${this.apiUrlUsers}/${userId}`);
    // Realiza uma requisição DELETE para a API, removendo o usuário especificado pelo ID.
    // Retorna um Observable que pode ser assinado para lidar com a confirmação da deleção.
  }
}
