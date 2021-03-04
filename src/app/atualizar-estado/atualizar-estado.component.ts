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
    this.estadoService.atualizarEstado(this.id,this.estado).subscribe(data => {
      if (data!=null){
      this.voltarMenuEstado();
      }else{
        this.messageService.add({severity:'warn', summary:'Campo Vazio', detail:'Por favor preencha todos os campos'});
      }
    });
  }
}
