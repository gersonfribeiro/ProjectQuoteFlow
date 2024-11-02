import {Injectable} from '@angular/core';
// O decorador '@Injectable' marca essa classe como um serviço que pode ser injetado em outros componentes ou serviços do Angular.

import {HttpClient} from '@angular/common/http';
// 'HttpClient' é um serviço do Angular que permite realizar requisições HTTP, como GET, POST, PUT e DELETE, para se comunicar com uma API.

import {Observable} from 'rxjs';

// 'Observable' é uma estrutura que permite trabalhar com dados assíncronos e fluxos de eventos, facilitando o tratamento de respostas e erros de chamadas HTTP.

@Injectable({
  providedIn: 'root',
})
// O serviço é fornecido no nível raiz da aplicação, tornando-o acessível em toda a aplicação.
// Isso garante que o Angular crie e compartilhe uma única instância do serviço em toda a aplicação, promovendo a reutilização de dados e lógica.

export class ApiQuotationService {
  // Esta classe define o serviço 'ApiQuotationService', responsável por gerenciar a comunicação com a API de cotações.

}
