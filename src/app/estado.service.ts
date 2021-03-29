import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import{Estado} from './estado'

@Injectable({
  providedIn: 'root'
})
export class EstadoService {

   baseUrl="http://localhost:8086/cadastro/estados";

  constructor(private httpClient: HttpClient) { }

  getListaEstado():Observable<Estado[]>{
    return this.httpClient.get<Estado[]>(`${this.baseUrl}`);
  }

  criarEstado(estado: Estado): Observable<any>{
    return this.httpClient.post(`${this.baseUrl}`, estado);
  }

  getEstadoById(id: number): Observable<Estado>{
    return this.httpClient.get<Estado>(`${this.baseUrl}/${id}`);
  }

  atualizarEstado(id: number, estado: Estado): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/${id}`, estado);
  }

  deletarEstado(id:number):Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/${id}`);
  }

}
