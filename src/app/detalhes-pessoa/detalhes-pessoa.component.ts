import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Pessoa } from '../pessoa';
import { PessoaService } from '../pessoa.service';

@Component({
  selector: 'app-detalhes-pessoa',
  templateUrl: './detalhes-pessoa.component.html',
  styleUrls: ['./detalhes-pessoa.component.css']
})
export class DetalhesPessoaComponent implements OnInit {

  id:number;
  pessoa: Pessoa;

  constructor(private route: ActivatedRoute, private pessoaService: PessoaService,
    private router: Router) { }

  ngOnInit(): void {
    this.id= this.route.snapshot.params['id'];

    this.pessoa = new Pessoa();

    this.pessoaService.getPessoaById(this.id).subscribe(data => {
      this.pessoa=data;
    });
  }

  voltarMenuPessoa(){
    this.router.navigate(['cadastro/listapessoas']);
  }

}
