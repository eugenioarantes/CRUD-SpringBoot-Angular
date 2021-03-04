import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Estado } from '../estado';
import { EstadoService } from '../estado.service';

@Component({
  selector: 'app-detalhes-estado',
  templateUrl: './detalhes-estado.component.html',
  styleUrls: ['./detalhes-estado.component.css']
})
export class DetalhesEstadoComponent implements OnInit {

  id:number;
  estado: Estado;

  constructor(private route: ActivatedRoute, private estadoService: EstadoService,
    private router: Router) { }

  ngOnInit(): void {
    this.id= this.route.snapshot.params['id'];

    this.estado = new Estado();

    this.estadoService.getEstadoById(this.id).subscribe(data => {
      this.estado=data;
    });
  }

  voltarMenuEstado(){
    this.router.navigate(['cadastro/listaestados']);
  }

}
