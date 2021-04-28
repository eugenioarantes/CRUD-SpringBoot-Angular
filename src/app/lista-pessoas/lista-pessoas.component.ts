import { Component, OnInit } from '@angular/core';
import {Pessoa} from '../pessoa'
import { PessoaService } from '../pessoa.service';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import {ConfirmationService, MessageService} from 'primeng/api';

@Component({
  selector: 'app-lista-pessoas',
  templateUrl: './lista-pessoas.component.html',
  styleUrls: ['./lista-pessoas.component.css'],
  providers:[ConfirmationService,MessageService]
})
export class ListaPessoasComponent implements OnInit {

  pessoas: Pessoa[];
  items: MenuItem[];

  constructor(private pessoaService: PessoaService,private router:Router,
    private confirmationService: ConfirmationService,
    private messageService: MessageService) { }

  ngOnInit(): void {
    this.getPessoas();

    this.items = [
      {label: 'Lista de Pessoas',command: () => {
        this.voltarMenuPessoa();
    }},
      {label: 'Lista de Cidades', command: () => {
        this.listaCidades();
    }},
      {label: 'Lista de Estados',command: () => {
        this.listaEstados();
    }}
  ];

  }

  listaCidades(){
    this.router.navigate(['cadastro/listacidades']);
  }

  listaEstados(){
    this.router.navigate(['cadastro/listaestados']);
  }

  voltarMenuPessoa(){
    this.router.navigate(['cadastro/listapessoas']);
  }

  private getPessoas(){
    this.pessoaService.getNomesPessoa().subscribe(data => {
      this.pessoas=data;
    });
  }

  btnNovaPessoa(){
    this.router.navigate(['cadastro/criarpessoa']);
  }

  atualizarPessoa(id:number){
    this.router.navigate(['cadastro/atualizarpessoa',id]);
  }

  excluirPessoa(id:number,event:Event){
    this.confirmationService.confirm({
      target: event.target,
      message: 'Deseja mesmo excluir essa pessoa?',
      icon: 'pi pi-exclamation-triangle',
      acceptLabel:'Sim',
      rejectLabel:'Nao',
      accept: () => {
        this.pessoaService.deletarPessoa(id).subscribe(data => {
          this.messageService.add({severity:'success',
          summary:'Pessoa Removida com Sucesso'});
          this.getPessoas();
        });
      },
      reject: () => {}
    });
  }
  detalhesPessoa(id:number){
    this.router.navigate(['cadastro/detalhespessoa',id]);
  }

}
