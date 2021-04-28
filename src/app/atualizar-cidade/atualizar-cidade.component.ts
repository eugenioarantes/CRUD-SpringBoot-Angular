import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cidade } from '../cidade';
import { CidadeService } from '../cidade.service';
import { Estado } from '../estado';
import { EstadoService } from '../estado.service';
import {MessageService} from 'primeng/api';
@Component({
  selector: 'app-atualizar-cidade',
  templateUrl: './atualizar-cidade.component.html',
  styleUrls: ['./atualizar-cidade.component.css'],
  providers: [MessageService]
})
export class AtualizarCidadeComponent implements OnInit {

  id:number;
  cidade: Cidade = new Cidade();

  estados: Estado[];

  constructor(private cidadeService: CidadeService,private route: ActivatedRoute, 
              private router: Router,private estadosService: EstadoService,
              private messageService: MessageService) { }

  ngOnInit(): void {
    
    this.id = this.route.snapshot.params['id'];
    this.cidadeService.getCidadeById(this.id).subscribe(data => {
      this.cidade=data;
     
    });
    this.getEstados();
     
  }

  voltarMenuCidade(){
    this.router.navigate(['cadastro/listacidades']);
  }

  atualizarCidade(){
    
    this.cidadeService.atualizarCidade(this.id,this.cidade).subscribe(async data => {
      this.messageService.add({severity:'success',
          summary:'Cidade Atualizada com sucesso!'});
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
