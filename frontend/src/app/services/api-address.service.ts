import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, pipe, throwError} from 'rxjs';
import {Usuario} from "../models/user.model";

@Injectable({
  providedIn: 'root'
})
export class ApiAddressService {
  private apiUrlAddress = 'http://localhost:8080/enderecos';

  constructor(private http: HttpClient) {
  }

  registerAddress(addressData: any): Observable<any> {
    return this.http.post(this.apiUrlAddress, addressData)
  }
}

