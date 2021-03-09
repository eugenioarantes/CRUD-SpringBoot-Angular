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

import com.eugenio.cadastro.model.Cidade;
import com.eugenio.cadastro.repository.Cidades;

@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/cadastro/cidades")
public class CidadesController { 

	@Autowired
	private Cidades cidades;

	@GetMapping
	public ArrayList<Cidade> listar() {
	  return (ArrayList<Cidade>) cidades.findAll();
	}
	
	// criar cidade
	@PostMapping
	public Cidade criarCidade(@RequestBody Cidade cidade) {
		if((cidade.getNome()==null)||(cidade.getEstado().getNome()==null)) {
				return null;
		}
		else {return cidades.save(cidade);}
	}	
	
	// get cidade by id rest api
	@GetMapping("/{id}")
	public ResponseEntity<Cidade> getCidadeById(@PathVariable(value = "id") Long id) {
		java.util.Optional<Cidade> cidade = cidades.findById(id);
						
			if(cidade.isPresent())
				return new ResponseEntity<Cidade>(cidade.get(), HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// atualizando cidade
	@PutMapping("/{id}")
	public ResponseEntity<Cidade> atualizaCidade(@PathVariable Long id, @RequestBody Cidade cidadeDetalhes){
		java.util.Optional<Cidade> cidade = cidades.findById(id);
						
			if(cidade.isPresent()){
				Cidade cidadeNova = cidade.get();
				cidadeNova.setNome(cidadeDetalhes.getNome());
				cidadeNova.setEstado(cidadeDetalhes.getEstado());
					
			  if((cidadeNova.getNome()==null)||(cidadeNova.getEstado().getNome()==null)) {
					return null;
			  }else {
				  cidades.save(cidadeNova);
				    return new ResponseEntity<Cidade>(cidadeNova, HttpStatus.OK);
			  }
		    }
			else
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// deletando cidade
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deletaCidade(@PathVariable Long id){
		java.util.Optional<Cidade> cidade = cidades.findById(id);
						
			if(cidade.isPresent()){
				Cidade cidadeNova = cidade.get();
			    cidades.delete(cidadeNova);
			    Map<String, Boolean> response = new HashMap<>();
			    response.put("deleted", Boolean.TRUE);
					return new ResponseEntity<Map<String,Boolean>>(response,HttpStatus.OK);
			} 
				else
				    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
						
	}
			
}