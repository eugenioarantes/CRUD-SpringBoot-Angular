import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AtualizarEstadoComponent } from './atualizar-estado/atualizar-estado.component';
import { AtualizarPessoaComponent } from './atualizar-pessoa/atualizar-pessoa.component';
import { CriarEstadoComponent } from './criar-estado/criar-estado.component';
import { CriarPessoaComponent } from './criar-pessoa/criar-pessoa.component';
import { DetalhesEstadoComponent } from './detalhes-estado/detalhes-estado.component';
import { DetalhesPessoaComponent } from './detalhes-pessoa/detalhes-pessoa.component';
import { ListaEstadosComponent } from './lista-estados/lista-estados.component';
import { ListaPessoasComponent } from './lista-pessoas/lista-pessoas.component';

const routes: Routes = [
  {path:'cadastro/listapessoas', component: ListaPessoasComponent},
  {path:'', redirectTo:'cadastro/listapessoas', pathMatch:'full'},
  {path:'cadastro/criarpessoa', component: CriarPessoaComponent},
  {path: 'cadastro/atualizarpessoa/:id', component: AtualizarPessoaComponent},
  {path: 'cadastro/detalhespessoa/:id', component: DetalhesPessoaComponent},

  {path: 'cadastro/listaestados', component: ListaEstadosComponent},
  {path:'cadastro/criarestado', component: CriarEstadoComponent},
  {path: 'cadastro/atualizarestado/:id', component: AtualizarEstadoComponent},
  {path: 'cadastro/detalhesestado/:id', component: DetalhesEstadoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
