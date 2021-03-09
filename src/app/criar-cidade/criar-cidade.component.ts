import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService, SelectItem} from 'primeng/api';
import { Cidade } from '../cidade';
import { CidadeService } from '../cidade.service';
import { Estado } from '../estado';
import { EstadoService } from '../estado.service';

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
    private messageService: MessageService, private estadosService: EstadoService) { }

  ngOnInit(): void {
    this.getEstados();

  }

  voltarMenuCidade(){
    this.router.navigate(['cadastro/listacidades']);
  }

  salvarCidade(){
    console.log(this.cidade);
    this.cidadeService.criarCidade(this.cidade).subscribe(data => {
     
      console.log(data);
      if (data==null){
      this.messageService.add({severity:'warn', summary:'Campo Vazio', detail:'Por favor preencha todos os campos'});
      }else{
        this.voltarMenuCidade();
      }
    });
    
  }
  
  private getEstados(){
    this.estadosService.getListaEstado().subscribe(data => {
      this.estados=data;
    });
  }

}
