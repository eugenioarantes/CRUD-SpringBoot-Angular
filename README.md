# SistemaCadastroAngular

Este projeto foi gerado com [Angular CLI](https://github.com/angular/angular-cli) version 11.1.3.

## Baixe as imagens Docker para rodar o projeto

• docker pull mysql:8.0.11

• docker pull openjdk:11

• docker pull eugenioarantes/spring-container:latest

## Crie os containers e execute-os

<h3>Criando container MYSQL e o executando</h3>

• docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=root -e MYSQL_USER=root -e MYSQL_USER_PASSWORD=root -e MYSQL_PASSWORD=root -e MYSQL_DATABASE=cadastro -d mysql:8.0.11

• docker container logs -f mysql-container

`OBS: este processo pode demorar alguns instantes`

<h3>Criando container da Aplicação Spring boot</h3>

• docker run -p 8086:8086 --name spring-container --link mysql-container:mysql -d eugenioarantes/spring-container

<h3> Startando os containers:</h3>

• docker start mysql-container

• docker start spring-container

<h3>Executando o container spring boot</h3>

• docker container logs -f spring-container

## Executando o projeto

Execute `ng serve` na pasta do projeto angular. Navegue até `http://localhost:4200/`. O aplicativo será recarregado automaticamente se você alterar qualquer um dos arquivos de origem.

## Andaime de código

Execute `ng generate component-name` para gerar um novo componente. Você também pode usar `ng generate Directive | pipe | service | class | guard | interface | enum | module`.

## Build

Execute `ng build` para construir o projeto. Os artefatos de construção serão armazenados no diretório `dist/`. Use o sinalizador `--prod` para uma construção de produção.

## Executando testes de unidade

Execute `ng test` para executar os testes de unidade via [Karma](https://karma-runner.github.io).

## Executando testes de ponta a ponta

Execute `ng e2e` para executar os testes de ponta a ponta via [Protractor](http://www.protractortest.org/).

## Mais ajuda

para obter mais ajuda sobre o Angular CLI, use `ng help` ou confira a página [Visão geral e referência de comandos do Angular CLI](https://angular.io/cli).
