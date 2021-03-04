import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {MessageService} from 'primeng/api';
import { Estado } from '../estado';
import { EstadoService } from '../estado.service';

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
      console.log(data);
      if (data==null){
      this.messageService.add({severity:'warn', summary:'Campo Vazio', detail:'Por favor preencha todos os campos'});
      }else{
        this.voltarMenuEstado();
      }
    });
    
  }

}
