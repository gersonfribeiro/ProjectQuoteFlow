import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, pipe, throwError} from 'rxjs';
import {Usuario} from "../models/user.model";

@Injectable({
  providedIn: 'root'
})
export class ApiAddressService {
  private apiUrlCompany = 'http://localhost:8080/enderecos';

  constructor(private http: HttpClient) {
  }

  registerCompany(companyData: any): Observable<{ id_empresa: string }> {
    return this.http.post<{ id_empresa: string }>(`${this.apiUrlCompany}/empresas`, companyData)
  }

  registerAdress(addressData: any): Observable<any> {
    return this.http.post(`${this.apiUrlCompany}/enderecos`, addressData)
  }
}

