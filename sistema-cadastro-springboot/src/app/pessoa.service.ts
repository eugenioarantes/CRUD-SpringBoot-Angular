import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pessoa } from './pessoa'

@Injectable({
  providedIn: 'root'
})
export class PessoaService {
  
   baseUrl="http://localhost:8080/cadastro/pessoas";

  constructor(private httpClient: HttpClient) { }

  getListaPessoa():Observable<Pessoa[]>{
    return this.httpClient.get<Pessoa[]>(`${this.baseUrl}`);
  }

}
