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

import com.eugenio.cadastro.model.Estado;
import com.eugenio.cadastro.repository.Estados;

@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/cadastro/estados")
public class EstadosController {

	@Autowired
	private Estados estados;

		/**
		 * @return retorna todos os estados com seus dados
		 */
		@GetMapping
		public ArrayList<Estado> listar() {
			return (ArrayList<Estado>) estados.findAll();
		}
	
		/**
		 * @param estado - Estado a ser salvo
		 * @return salva o estado no repositorio
		 */
		@PostMapping
		public Estado criarEstado(@RequestBody Estado estado) {
			return estados.save(estado);
		}	
		
		
		/**
		 * @param id - Id do estado a ser procurado
		 * @return retorna o estado encontrado
		 */
		@GetMapping("/{id}")
		public ResponseEntity<Estado> getEstadoById(@PathVariable(value = "id") Long id) {
		 java.util.Optional<Estado> estado = estados.findById(id);
					
		 	if(estado.isPresent())
			  return new ResponseEntity<Estado>(estado.get(), HttpStatus.OK);
		 	else
			  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		/**
		 * @param id - Id do estado a ser atualizado
		 * @param estadoDetalhes - objeto do estado com os dados a serem atualizados
		 * @return retorna o estado atualizado
		 */
		@PutMapping("/{id}")
		public ResponseEntity<Estado> atualizaEstado(@PathVariable Long id, @RequestBody Estado estadoDetalhes){
			java.util.Optional<Estado> estado = estados.findById(id);
					
			if(estado.isPresent()){
				Estado estadoNovo = estado.get();
				estadoNovo.setNome(estadoDetalhes.getNome());
				estadoNovo.setSigla(estadoDetalhes.getSigla());

			    estados.save(estadoNovo);
			        return new ResponseEntity<Estado>(estadoNovo, HttpStatus.OK);
				
			}
			else
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
		/**
		 * @param id - Id do estado a ser removido
		 * @return retorna o status
		 */
		@DeleteMapping("/{id}")
		public ResponseEntity<Map<String, Boolean>> deletaEstado(@PathVariable Long id){
			java.util.Optional<Estado> estado = estados.findById(id);
					
				if(estado.isPresent()){
				  Estado estadoNovo = estado.get();
				  	estados.delete(estadoNovo);
				  	Map<String, Boolean> response = new HashMap<>();
				  	response.put("deleted", Boolean.TRUE);
				  		return new ResponseEntity<Map<String,Boolean>>(response,HttpStatus.OK);
				 } 
				 else
			        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					
		}
	
}