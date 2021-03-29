import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cidade } from './cidade';

@Injectable({
  providedIn: 'root'
})
export class CidadeService {

  baseUrl="http://localhost:8086/cadastro/cidades";

  constructor(private httpClient: HttpClient) { }

  getNomesCidade():Observable<Cidade[]>{
    return this.httpClient.get<Cidade[]>(`${this.baseUrl}/listagem`);
  }

  getListaCidade():Observable<Cidade[]>{
    return this.httpClient.get<Cidade[]>(`${this.baseUrl}`);
  }

  criarCidade(cidade: Cidade): Observable<any>{
    return this.httpClient.post(`${this.baseUrl}`, cidade);
  }

  getCidadeById(id: number): Observable<Cidade>{
    return this.httpClient.get<Cidade>(`${this.baseUrl}/${id}`);
  }

  atualizarCidade(id: number, cidade: Cidade): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/${id}`, cidade);
  }
  
  deletarCidade(id:number):Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/${id}`);
  }
  
}
