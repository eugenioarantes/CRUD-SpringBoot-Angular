package com.eugenio.cadastro.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eugenio.cadastro.model.Pessoa;
import com.eugenio.cadastro.repository.Pessoas;

@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/cadastro/pessoas")
public class PessoasController {

	@Autowired
	private Pessoas pessoas;

	@GetMapping
	public ArrayList<Pessoa> listar() {
	  return (ArrayList<Pessoa>) pessoas.findAll();
	}
	
	// criar pessoa
	@PostMapping
	public Pessoa criarPessoa(@RequestBody Pessoa pessoa) {
		if((pessoa.getNome()==null)||(pessoa.getEmail()==null)||
		   (pessoa.getCelular()==null)) {
			return null;
		}
		else {return pessoas.save(pessoa);}
	}	
	
	// get pessoa by id rest api
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> getPessoaById(@PathVariable(value = "id") Long id) {
	  java.util.Optional<Pessoa> pessoa = pessoas.findById(id);
				
		if(pessoa.isPresent())
		   return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
		else
		   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// atualizando pessoa
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizaPessoa(@PathVariable Long id, @RequestBody Pessoa pessoaDetalhes){
		java.util.Optional<Pessoa> pessoa = pessoas.findById(id);
				
		if(pessoa.isPresent()){
			Pessoa pessoaNova = pessoa.get();
			pessoaNova.setNome(pessoaDetalhes.getNome());
			pessoaNova.setEmail(pessoaDetalhes.getEmail());
			pessoaNova.setCelular(pessoaDetalhes.getCelular());
			
			if((pessoaNova.getNome().contentEquals(""))||(pessoaNova.getEmail().contentEquals(""))||
					   (pessoaNova.getCelular().contentEquals(""))) {
						return null;
			}else {
		    pessoas.save(pessoaNova);
		        return new ResponseEntity<Pessoa>(pessoaNova, HttpStatus.OK);
			}
		}
		else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
			
	// deletando pessoa
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deletaPessoa(@PathVariable Long id){
		java.util.Optional<Pessoa> pessoa = pessoas.findById(id);
				
			if(pessoa.isPresent()){
			  Pessoa PessoaNova = pessoa.get();
			  pessoas.delete(PessoaNova);
			  Map<String, Boolean> response = new HashMap<>();
			  response.put("deleted", Boolean.TRUE);
				return new ResponseEntity<Map<String,Boolean>>(response,HttpStatus.OK);
			 } 
			 else
		        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				
	}
	
	
}
