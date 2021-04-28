import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cidade } from '../cidade';
import { CidadeService } from '../cidade.service';
import { Estado } from '../estado';
import { EstadoService } from '../estado.service';
import {MessageService} from 'primeng/api';
@Component({
  selector: 'app-criar-cidade',
  templateUrl: './criar-cidade.component.html',
  styleUrls: ['./criar-cidade.component.css'],
  providers: [MessageService]
})
export class CriarCidadeComponent implements OnInit {

  cidade: Cidade = new Cidade();
  estados: Estado[];

  constructor(private router:Router, private cidadeService: CidadeService,
              private estadosService: EstadoService,
              private messageService: MessageService) { }

  ngOnInit(): void {
    this.getEstados();

  }

  voltarMenuCidade(){
    this.router.navigate(['cadastro/listacidades']);
  }

  salvarCidade(){
    this.cidadeService.criarCidade(this.cidade).subscribe(async data => {
      this.messageService.add({severity:'success',
          summary:'Cidade cadastrada com sucesso!'});
          await this.delay(2000);
        this.voltarMenuCidade();
    },
    error=>{
      this.messageService.add({severity:'error',
          summary:'Esta cidade já existe!', detail:'Tente outro cadastro!'});
    });
    
  }
  
  private getEstados(){
    this.estadosService.getListaEstado().subscribe(data => {
      this.estados=data;
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
