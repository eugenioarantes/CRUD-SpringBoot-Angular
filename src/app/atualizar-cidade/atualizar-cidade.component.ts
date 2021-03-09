import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService, SelectItem} from 'primeng/api';
import { Cidade } from '../cidade';
import { CidadeService } from '../cidade.service';
import { Estado } from '../estado';
import { EstadoService } from '../estado.service';

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

  constructor(private cidadeService: CidadeService, private messageService: MessageService,
    private route: ActivatedRoute, private router: Router,private estadosService: EstadoService) { }

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
    
    this.cidadeService.atualizarCidade(this.id,this.cidade).subscribe(data => {
    
      if (data!=null){
      console.log("cidade:",this.cidade);

      this.voltarMenuCidade();
      }else{ 
        this.messageService.add({severity:'warn', summary:'Campo Vazio',
         detail:'Por favor preencha todos os campos'});
      }
    });
  }
  private getEstados(){
    this.estadosService.getListaEstado().subscribe(data => {
      this.estados=data;
      console.log("estados:",this.estados);  
    });
    
  }

}
