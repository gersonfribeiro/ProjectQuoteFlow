import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ApiCompanyService {

  private apiUrlCompany = 'http://localhost:8080/empresas';

  constructor(private http: HttpClient) {
  }

}
