package com.eugenio.cadastro.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.eugenio.cadastro.dto.EstadoListagemDTO;
import com.eugenio.cadastro.model.Estado;
import com.eugenio.cadastro.service.EstadosService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/cadastro/estados")
public class EstadosController {
	
	@Autowired
	private EstadosService estadosService;


		@GetMapping
		public ArrayList<EstadoListagemDTO> listar() {
			return estadosService.listar();
		}
	
		@PostMapping
		public EstadoListagemDTO criarEstado(@RequestBody Estado estado) {
			return estadosService.criarEstado(estado);
		}	
		
		@GetMapping("/{id}")
		public ResponseEntity<EstadoListagemDTO> getEstadoById(@PathVariable(value = "id") Long id) {
			return estadosService.getEstadoById(id);
		}
			
		@PutMapping("/{id}")
		public ResponseEntity<EstadoListagemDTO> atualizaEstado(@PathVariable Long id, @RequestBody Estado estadoDetalhes){
			return estadosService.atualizaEstado(id, estadoDetalhes);
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<Map<String, Boolean>> deletaEstado(@PathVariable Long id){
			return estadosService.deletaEstado(id);
		}

}
