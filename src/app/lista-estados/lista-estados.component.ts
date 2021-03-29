import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { Estado } from '../estado';
import { EstadoService } from '../estado.service';
import {ConfirmationService, MessageService} from 'primeng/api';

@Component({
  selector: 'app-lista-estados',
  templateUrl: './lista-estados.component.html',
  styleUrls: ['./lista-estados.component.css'],
  providers:[ConfirmationService,MessageService]
})
export class ListaEstadosComponent implements OnInit {

  estados: Estado[];
  items: MenuItem[];

  constructor(private estadoService: EstadoService,private router:Router,
    private confirmationService: ConfirmationService,
     private messageService: MessageService) { }

  ngOnInit(): void {
    this.getEstados();

    this.items = [
      {label: 'Lista de Pessoas',command: () => {
        this.listaPessoas();
    }},
      {label: 'Lista de Cidades', command: () => {
        this.listaCidades();
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
  listaCidades(){
    this.router.navigate(['cadastro/listacidades']);
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

  excluirEstado(id:number,event:Event){
    
    this.confirmationService.confirm({
      target: event.target,
      message: 'Deseja mesmo excluir esse estado?',
      icon: 'pi pi-exclamation-triangle',
      acceptLabel:'Sim',
      rejectLabel:'Nao',
      accept: () => {

        this.estadoService.deletarEstado(id).subscribe(data => {
          this.messageService.add({severity:'success',
          summary:'Estado Removido com Sucesso'});
          this.getEstados();
        },
        error =>{
          this.messageService.add({severity:'error',
          summary:'Impossível Remover',detail:'Este estado contém cidades cadastradas!'});  
        });
      },
      reject: () => {}
      
    });
  }


  detalhesEstado(id:number){
    this.router.navigate(['cadastro/detalhesestado',id]);
  }


}
