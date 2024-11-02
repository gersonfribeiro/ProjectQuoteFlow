import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {Usuario} from "../models/user.model";

@Injectable({
  providedIn: 'root'
})
export class ApiProfileService {
  private apiUrlProfile = 'http://localhost:8080/usuarios';

  constructor(private http: HttpClient) {
  }

  getUser(userId: string): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.apiUrlProfile}/${userId}`
    )
  }

  updateUser(userId: string, updatedData: Partial<Usuario>): Observable<Usuario> {
    return this.http.put<Usuario>(`${this.apiUrlProfile}/${userId}`, updatedData)
  }
}
