import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Pessoa } from '../pessoa';
import { PessoaService } from '../pessoa.service';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-atualizar-pessoa',
  templateUrl: './atualizar-pessoa.component.html',
  styleUrls: ['./atualizar-pessoa.component.css'],
  providers: [MessageService]
})
export class AtualizarPessoaComponent implements OnInit {

  id:number;
  pessoa: Pessoa = new Pessoa();

  constructor(private pessoaService: PessoaService, private messageService: MessageService,
    private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.pessoaService.getPessoaById(this.id).subscribe(data => {
      this.pessoa=data;
    });
  }

  voltarMenuPessoa(){
    this.router.navigate(['cadastro/listapessoas']);
  }

  atualizarPessoa(){
    this.pessoaService.atualizarPessoa(this.id,this.pessoa).subscribe(data => {
      if (data!=null){
      this.voltarMenuPessoa();
      }else{
        this.messageService.add({severity:'warn', summary:'Campo Vazio', detail:'Por favor preencha todos os campos'});
      }
    });
  }
}
