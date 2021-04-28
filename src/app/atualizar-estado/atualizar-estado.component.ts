import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {MessageService} from 'primeng/api';
import { Estado } from '../estado';
import { EstadoService } from '../estado.service';

@Component({
  selector: 'app-atualizar-estado',
  templateUrl: './atualizar-estado.component.html',
  styleUrls: ['./atualizar-estado.component.css'],
  providers: [MessageService]
})
export class AtualizarEstadoComponent implements OnInit {

  id:number;
  estado: Estado = new Estado();

  constructor(private estadoService: EstadoService, private messageService: MessageService,
    private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.estadoService.getEstadoById(this.id).subscribe(data => {
      this.estado=data;
    });
  }

  voltarMenuEstado(){
    this.router.navigate(['cadastro/listaestados']);
  }

  atualizarEstado(){
    this.estadoService.atualizarEstado(this.id,this.estado).subscribe(async data => {
      this.messageService.add({severity:'success',
          summary:'Estado Atualizado com sucesso!'});
          await this.delay(2000);
      this.voltarMenuEstado();
    },
    error=>{
      this.messageService.add({severity:'error',
          summary:'Nome ou sigla já cadastrada!', detail:'Tente novamente!'});
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
