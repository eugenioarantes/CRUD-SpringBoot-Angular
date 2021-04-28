import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cidade } from '../cidade';
import { CidadeService } from '../cidade.service';

@Component({
  selector: 'app-detalhes-cidade',
  templateUrl: './detalhes-cidade.component.html',
  styleUrls: ['./detalhes-cidade.component.css']
})
export class DetalhesCidadeComponent implements OnInit {

  id:number;
  cidade: Cidade;

  constructor(private route: ActivatedRoute, private cidadeService: CidadeService,
    private router: Router) { }

  ngOnInit(): void {
    this.id= this.route.snapshot.params['id'];

    this.cidade = new Cidade();

    this.cidadeService.getCidadeById(this.id).subscribe(data => {
      this.cidade=data;
    });
  }

  voltarMenuCidade(){
    this.router.navigate(['cadastro/listacidades']);
  }

}
