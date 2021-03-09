import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListaPessoasComponent } from './lista-pessoas/lista-pessoas.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ButtonModule} from 'primeng/button';
import {TableModule} from 'primeng/table';
import { CriarPessoaComponent } from './criar-pessoa/criar-pessoa.component';
import {InputMaskModule} from 'primeng/inputmask';
import {InputTextModule} from 'primeng/inputtext';
import {FormsModule} from '@angular/forms';
import { AtualizarPessoaComponent } from './atualizar-pessoa/atualizar-pessoa.component'
import {SplitButtonModule} from 'primeng/splitbutton';
import { DetalhesPessoaComponent } from './detalhes-pessoa/detalhes-pessoa.component';

import {ToastModule} from 'primeng/toast';
import { ListaEstadosComponent } from './lista-estados/lista-estados.component';
import { CriarEstadoComponent } from './criar-estado/criar-estado.component';
import { AtualizarEstadoComponent } from './atualizar-estado/atualizar-estado.component';
import { DetalhesEstadoComponent } from './detalhes-estado/detalhes-estado.component';
import { ListaCidadesComponent } from './lista-cidades/lista-cidades.component';
import { CriarCidadeComponent } from './criar-cidade/criar-cidade.component';
import { AtualizarCidadeComponent } from './atualizar-cidade/atualizar-cidade.component';
import { DetalhesCidadeComponent } from './detalhes-cidade/detalhes-cidade.component';
import {DropdownModule} from 'primeng/dropdown';


@NgModule({
  declarations: [
    AppComponent,
    ListaPessoasComponent,
    CriarPessoaComponent,
    AtualizarPessoaComponent,
    DetalhesPessoaComponent,
    ListaEstadosComponent,
    CriarEstadoComponent,
    AtualizarEstadoComponent,
    DetalhesEstadoComponent,
    ListaCidadesComponent,
    CriarCidadeComponent,
    AtualizarCidadeComponent,
    DetalhesCidadeComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    TableModule,
    ButtonModule,
    InputMaskModule,
    InputTextModule,
    FormsModule,
    SplitButtonModule,
    ToastModule,
    DropdownModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
