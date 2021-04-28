package com.eugenio.cadastro.controller;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eugenio.cadastro.dto.CidadeListagemCompletaDTO;
import com.eugenio.cadastro.dto.CidadeListagemDTO;
import com.eugenio.cadastro.model.Cidade;
import com.eugenio.cadastro.service.CidadesService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/cadastro/cidades")
public class CidadesController {
	
	@Autowired
	private CidadesService cidadesService;


	@GetMapping("/listagem")
	public ArrayList<CidadeListagemDTO> listarNomes() {
	  return cidadesService.listarNomes();
	}

	@GetMapping
	public ArrayList<CidadeListagemCompletaDTO> listar() {
		
	  return cidadesService.listar();
	}
	
	@PostMapping
	public CidadeListagemCompletaDTO criarCidade(@Validated @RequestBody Cidade cidade) {
	  return cidadesService.criarCidade(cidade);
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<CidadeListagemCompletaDTO> getCidadeById(@PathVariable(value = "id") Long id) {
		return cidadesService.getCidadeById(id);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<CidadeListagemCompletaDTO> atualizaCidade(@PathVariable Long id, @RequestBody Cidade cidadeDetalhes){
		return cidadesService.atualizaCidade(id, cidadeDetalhes);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>>deletaCidade(@PathVariable Long id){ 
		return cidadesService.deletaCidade(id);
	  }	

}
