import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Estado } from '../estado';
import { EstadoService } from '../estado.service';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-criar-estado',
  templateUrl: './criar-estado.component.html',
  styleUrls: ['./criar-estado.component.css'],
  providers: [MessageService]
})
export class CriarEstadoComponent implements OnInit {

  estado: Estado = new Estado();

  constructor(private router:Router, private estadoService: EstadoService,
              private messageService: MessageService) { }

  ngOnInit(): void {
  }

  voltarMenuEstado(){
    this.router.navigate(['cadastro/listaestados']);
  }

  salvarEstado(){
    
    this.estadoService.criarEstado(this.estado).subscribe(data => {
        this.voltarMenuEstado();
    },
    error=>{
      this.messageService.add({severity:'error',
          summary:'Nome ou sigla já cadastrada!', detail:'Tente novamente!'});
    });
    
  }

}
