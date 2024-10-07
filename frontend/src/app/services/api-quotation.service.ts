import { Injectable } from '@angular/core';
// O decorador '@Injectable' marca essa classe como um serviço que pode ser injetado em outros componentes ou serviços do Angular.

import { HttpClient } from '@angular/common/http';
// 'HttpClient' é um serviço Angular que permite realizar requisições HTTP, como GET, POST, etc., para se comunicar com uma API.

import { Observable } from 'rxjs';
// 'Observable' é usado para lidar com dados assíncronos e fluxos de eventos, fornecendo suporte para o tratamento de respostas HTTP.

@Injectable({
  providedIn: 'root',
})
// O serviço é fornecido no nível raiz, tornando-o disponível para toda a aplicação.
// Isso significa que o Angular criará uma única instância do serviço e a compartilhará em toda a aplicação.
export class ApiQuotationService {
  // Esta classe define o serviço 'ApiQuotationService' responsável por gerenciar a comunicação com a API de cotações.

  private apiUrl = 'http://localhost:3000/api/quotation';
  // Esta propriedade define a URL base da API para cotações, que será usada nas requisições HTTP.

  constructor(private http: HttpClient) {}
  // O construtor injeta uma instância do 'HttpClient' no serviço, permitindo que o serviço faça requisições HTTP para a API.
}
