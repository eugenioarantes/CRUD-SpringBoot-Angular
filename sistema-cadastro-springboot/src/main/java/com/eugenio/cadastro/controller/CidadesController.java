package com.eugenio.cadastro.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.eugenio.cadastro.dto.CidadeListagemDTO;
import com.eugenio.cadastro.model.Cidade;
import com.eugenio.cadastro.repository.Cidades;

@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/cadastro/cidades")
public class CidadesController { 

	@Autowired
	private Cidades cidades;

	/**
	 * @return retorna somente o Id e o Nome das cidades
	 */
	@GetMapping("/listagem")
	public ArrayList<CidadeListagemDTO> listarNomes() {
	  List<Cidade> listacidades = cidades.findAll();
	  List<CidadeListagemDTO> cidadesDTO = new ArrayList<>();
	  listacidades.forEach(c -> {
		  cidadesDTO.add(new CidadeListagemDTO(c.getId(),c.getNome()));
	  });
	  return (ArrayList<CidadeListagemDTO>) cidadesDTO;
	}

	/**
	 * @return retorna a lista de todas cidades com todos seus dados
	 */
	@GetMapping
	public ArrayList<Cidade> listar() {

		List<Cidade> lista = cidades.findAll();
	  return (ArrayList<Cidade>) lista;
	}
	
	/**
	 * @param cidade - recebe a cidade para salvar
	 * @return salva a cidade no repositório
	 */
	@PostMapping
	public Cidade criarCidade(@Validated @RequestBody Cidade cidade) {
		return cidades.save(cidade);
	}	
	
	/**
	 * @param id - id da cidade a encontrar
	 * @return retorna a cidade encontrada a partir do id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Cidade> getCidadeById(@PathVariable(value = "id") Long id) {
		java.util.Optional<Cidade> cidade = cidades.findById(id);
						
			if(cidade.isPresent())
				return new ResponseEntity<Cidade>(cidade.get(), HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	/**
	 * @param id - Id da cidade que deve ser atualizada
	 * @param cidadeDetalhes - objeto cidade com os novos dados para ser atualizado
	 * @return - retorna a cidade atualizada
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Cidade> atualizaCidade(@PathVariable Long id, @RequestBody Cidade cidadeDetalhes){
		java.util.Optional<Cidade> cidade = cidades.findById(id);
						
			if(cidade.isPresent()){
				Cidade cidadeNova = cidade.get();
				cidadeNova.setNome(cidadeDetalhes.getNome());
				cidadeNova.setEstado(cidadeDetalhes.getEstado());

				  cidades.save(cidadeNova);
				    return new ResponseEntity<Cidade>(cidadeNova, HttpStatus.OK);
			  }  
			else
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	

	  /**
	 * @param id - Id da cidade a ser deletada
	 * @return retorna o status 
	 */
	@DeleteMapping("/{id}") public ResponseEntity<Map<String, Boolean>>deletaCidade(@PathVariable Long id){ 
		  java.util.Optional<Cidade> cidade =cidades.findById(id);
	   System.out.println("Entrou no excluir");
	  	if(cidade.isPresent()){
	  		Cidade cidadeNova = cidade.get();
	  		cidades.delete(cidadeNova); Map<String, Boolean> response = new HashMap<>();
	  		response.put("deleted", Boolean.TRUE);
	  		  return 
	  			new ResponseEntity<Map<String,Boolean>>(response,HttpStatus.OK);
	  	} else return
	  			new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }	

			
}