import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { Cidade } from '../cidade';
import { CidadeService } from '../cidade.service';

@Component({
  selector: 'app-lista-cidades',
  templateUrl: './lista-cidades.component.html',
  styleUrls: ['./lista-cidades.component.css']
})
export class ListaCidadesComponent implements OnInit {

  cidades: Cidade[];
  items: MenuItem[];

  constructor(private cidadeService: CidadeService,
    private router:Router) { }

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
    this.cidadeService.getListaCidade().subscribe(data => {
      this.cidades=data;
    });
  }

  btnNovaCidade(){
    this.router.navigate(['cadastro/criarcidade']);
  }

  atualizarCidade(id:number){
    this.router.navigate(['cadastro/atualizarcidade',id]);
  }

  excluirCidade(id:number){
    this.cidadeService.deletarCidade(id).subscribe(data => {
      this.getCidades();
    });
  }

  detalhesCidade(id:number){
    this.router.navigate(['cadastro/detalhescidade',id]);
  }

}
