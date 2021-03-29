import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { Cidade } from '../cidade';
import { CidadeService } from '../cidade.service';
import {ConfirmationService,MessageService} from 'primeng/api';

@Component({
  selector: 'app-lista-cidades',
  templateUrl: './lista-cidades.component.html',
  styleUrls: ['./lista-cidades.component.css'],
  providers:[ConfirmationService,MessageService]
})
export class ListaCidadesComponent implements OnInit {

  cidades: Cidade[];
  items: MenuItem[];

  constructor(private cidadeService: CidadeService,private router:Router,
              private confirmationService: ConfirmationService,
              private messageService: MessageService) { }

  ngOnInit(): void {
    this.getCidades();


    this.items = [
      {label: 'Lista de Pessoas',command: () => {
        this.listaPessoas();
    }},
      {label: 'Lista de Cidades', command: () => {
        this.voltarMenuCidade();
    }},
      {label: 'Lista de Estados',command: () => {
        this.listaEstados();
    }}
  ];
  }

  listaPessoas(){
    this.router.navigate(['cadastro/listapessoas']);
  }

  listaEstados(){
    this.router.navigate(['cadastro/listaestados']);
  }

  voltarMenuCidade(){
    this.router.navigate(['cadastro/listacidades']);
  }

  private getCidades(){
    this.cidadeService.getNomesCidade().subscribe(data => {
      this.cidades=data;

        console.log(data);

    });
  }

  btnNovaCidade(){
    this.router.navigate(['cadastro/criarcidade']);
  }

  atualizarCidade(id:number){
    this.cidadeService.getListaCidade().subscribe(data => {
      this.cidades=data;
    });
    this.router.navigate(['cadastro/atualizarcidade',id]);
  }

  excluirCidade(id:number,event: Event){

    this.confirmationService.confirm({
      target: event.target,
      message: 'Deseja mesmo excluir essa cidade?',
      icon: 'pi pi-exclamation-triangle',
      acceptLabel:'Sim',
      rejectLabel:'Nao',
      accept: () => {
        this.cidadeService.deletarCidade(id).subscribe(data => {
          this.messageService.add({severity:'success',
          summary:'Cidade Removida com Sucesso'});
          this.getCidades();
        });
      },
      reject: () => {}
    });
    
  }

  detalhesCidade(id:number){

    this.router.navigate(['cadastro/detalhescidade',id]);
  }

}
