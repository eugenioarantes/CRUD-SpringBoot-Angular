import { Component, OnInit } from '@angular/core';
import {Pessoa} from '../pessoa'
import { PessoaService } from '../pessoa.service';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-lista-pessoas',
  templateUrl: './lista-pessoas.component.html',
  styleUrls: ['./lista-pessoas.component.css']
})
export class ListaPessoasComponent implements OnInit {

  pessoas: Pessoa[];
  items: MenuItem[];
  
  constructor(private pessoaService: PessoaService,
    private router:Router) { }

  ngOnInit(): void {
    this.getPessoas();

    this.items = [
      {label: 'Lista de Pessoas',command: () => {
        this.voltarMenuPessoa();
    }},
      {label: 'Lista de Cidades', command: () => {
        this.voltarMenuPessoa();
    }},
      {label: 'Lista de Estados',command: () => {
        this.listaEstados();
    }}
  ];

  }

  listaEstados(){
    this.router.navigate(['cadastro/listaestados']);
  }

  voltarMenuPessoa(){
    this.router.navigate(['cadastro/listapessoas']);
  }

  private getPessoas(){
    this.pessoaService.getListaPessoa().subscribe(data => {
      this.pessoas=data;
    });
  }

  btnNovaPessoa(){
    this.router.navigate(['cadastro/criarpessoa']);
  }

  atualizarPessoa(id:number){
    this.router.navigate(['cadastro/atualizarpessoa',id]);
  }

  excluirPessoa(id:number){
    this.pessoaService.deletarPessoa(id).subscribe(data => {
      this.getPessoas();
    });
  }
  detalhesPessoa(id:number){
    this.router.navigate(['cadastro/detalhespessoa',id]);
  }

}
