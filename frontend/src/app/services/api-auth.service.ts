import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Usuario} from "../models/user.model";

@Injectable({
  providedIn: 'root'
})
export class ApiAuthService {
  private apiUrlAuth = 'http://localhost:8080/usuarios';

  constructor(private http: HttpClient) {}

  // Atualize a tipagem para que o método aceite um objeto do tipo Usuario
  registerUser(user: Usuario): Observable<any> {
    return this.http.post(this.apiUrlAuth, user); // Envia o objeto diretamente
  }

  // Método para deletar um usuário pelo ID
  deleteUser(userId: number): Observable<any> {
    return this.http.delete(`${this.apiUrlAuth}/${userId}`);
  }
}
