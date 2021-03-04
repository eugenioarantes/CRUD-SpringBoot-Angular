import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { Estado } from '../estado';
import { EstadoService } from '../estado.service';

@Component({
  selector: 'app-lista-estados',
  templateUrl: './lista-estados.component.html',
  styleUrls: ['./lista-estados.component.css']
})
export class ListaEstadosComponent implements OnInit {

  estados: Estado[];
  items: MenuItem[];

  constructor(private estadoService: EstadoService,
    private router:Router) { }

  ngOnInit(): void {
    this.getEstados();

    this.items = [
      {label: 'Lista de Pessoas',command: () => {
        this.listaPessoas();
    }},
      {label: 'Lista de Cidades', command: () => {
        this.voltarMenuEstado();
    }},
      {label: 'Lista de Estados',command: () => {
        this.voltarMenuEstado();
    }}
  ];
  }

  listaPessoas(){
    this.router.navigate(['cadastro/listapessoas']);
  }

  voltarMenuEstado(){
    this.router.navigate(['cadastro/listaestados']);
  }

  private getEstados(){
    this.estadoService.getListaEstado().subscribe(data => {
      this.estados=data;
    });
  }

  btnNovoEstado(){
    this.router.navigate(['cadastro/criarestado']);
  }

  atualizarEstado(id:number){
    this.router.navigate(['cadastro/atualizarestado',id]);
  }

  excluirEstado(id:number){
    this.estadoService.deletarEstado(id).subscribe(data => {
      this.getEstados();
    });
  }

  detalhesEstado(id:number){
    this.router.navigate(['cadastro/detalhesestado',id]);
  }


}
