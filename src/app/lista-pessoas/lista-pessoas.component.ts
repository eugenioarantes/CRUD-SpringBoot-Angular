import { Component, OnInit } from '@angular/core';
import {Pessoa} from '../pessoa'
import { PessoaService } from '../pessoa.service';

@Component({
  selector: 'app-lista-pessoas',
  templateUrl: './lista-pessoas.component.html',
  styleUrls: ['./lista-pessoas.component.css']
})
export class ListaPessoasComponent implements OnInit {

  pessoas: Pessoa[];

  constructor(private pessoaService: PessoaService) { }

  ngOnInit(): void {
    this.getPessoas();
  }

  private getPessoas(){
    this.pessoaService.getListaPessoa().subscribe(data => {
      this.pessoas=data;
    });
  }

}
