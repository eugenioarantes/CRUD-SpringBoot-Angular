import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Pessoa } from '../pessoa';
import { PessoaService } from '../pessoa.service';
import {MessageService}  from 'primeng/api';

@Component({
  selector: 'app-atualizar-pessoa',
  templateUrl: './atualizar-pessoa.component.html',
  styleUrls: ['./atualizar-pessoa.component.css'],
  providers: [MessageService]
})
export class AtualizarPessoaComponent implements OnInit {

  id:number;
  pessoa: Pessoa = new Pessoa();

  constructor(private pessoaService: PessoaService,private route: ActivatedRoute,
              private router: Router,private messageService: MessageService) { }

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
    this.pessoaService.atualizarPessoa(this.id,this.pessoa).subscribe(async data => {
      this.messageService.add({severity:'success',
          summary:'Pessoa Atualizada com sucesso!'});
          await this.delay(2000);
      this.voltarMenuPessoa(); 
    },
    error=>{
      this.messageService.add({severity:'error',
          summary:'Email ou Celular já cadastrado', detail:'Tente novamente!'});
    });
  }

  private delay(ms: number): Promise<boolean> {
    return new Promise(resolve => {
      setTimeout(() => {
        resolve(true);
      }, ms);
    });
  }
}
