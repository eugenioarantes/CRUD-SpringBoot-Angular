import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pessoa } from './pessoa'

@Injectable({
  providedIn: 'root'
})
export class PessoaService {
  
   baseUrl="http://localhost:8086/cadastro/pessoas";

  constructor(private httpClient: HttpClient) { }

  getNomesPessoa():Observable<Pessoa[]>{
    return this.httpClient.get<Pessoa[]>(`${this.baseUrl}/listagem`);
  }
  getListaPessoa():Observable<Pessoa[]>{
    return this.httpClient.get<Pessoa[]>(`${this.baseUrl}`);
  }
  criarPessoa(pessoa: Pessoa): Observable<any>{
    return this.httpClient.post(`${this.baseUrl}`, pessoa);
  }

  getPessoaById(id: number): Observable<Pessoa>{
    return this.httpClient.get<Pessoa>(`${this.baseUrl}/${id}`);
  }

  atualizarPessoa(id: number, pessoa: Pessoa): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/${id}`, pessoa);
  }

  deletarPessoa(id:number):Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/${id}`);
  }


}
