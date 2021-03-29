package com.eugenio.cadastro.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import com.eugenio.cadastro.dto.PessoaListagemDTO;
import com.eugenio.cadastro.model.Pessoa;
import com.eugenio.cadastro.repository.Pessoas;

@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/cadastro/pessoas")
public class PessoasController {

	@Autowired
	private Pessoas pessoas;
	
	
	/**
	 * @return retorna somente o Id e o Nome das pessoas
	 */
	@GetMapping("/listagem")
	public ArrayList<PessoaListagemDTO> listarNomes() {
	  List<Pessoa> listapessoas = pessoas.findAll();
	  List<PessoaListagemDTO> pessoasDTO = new ArrayList<>();
	  listapessoas.forEach(c -> {
		  pessoasDTO.add(new PessoaListagemDTO(c.getId(),c.getNome()));
	  });
	  return (ArrayList<PessoaListagemDTO>) pessoasDTO;
	}

	
	/**
	 * @return retorna a lista de todas pessoas com todos seus dados
	 */
	@GetMapping
	public ArrayList<Pessoa> listar() {
	  return (ArrayList<Pessoa>) pessoas.findAll();
	}
	
	/**
	 * @param pessoa - objeto da pessoa a ser salva
	 * @return salva a pessoa no repositorio
	 */
	@PostMapping
	public Pessoa criarPessoa(@RequestBody Pessoa pessoa) {
		 return pessoas.save(pessoa);
	}	
	
	/**
	 * @param id - Id da pessoa a ser encontrada no repositorio
	 * @return retorna o objeto da pessoa encontrada
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> getPessoaById(@PathVariable(value = "id") Long id) {
	  java.util.Optional<Pessoa> pessoa = pessoas.findById(id);
				
		if(pessoa.isPresent())
		   return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
		else
		   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	/**
	 * @param id - Id da pessoa a ser encontrada no repositorio
	 * @param pessoaDetalhes - Dados da pessoa a ser atualizada
	 * @return retorna o objeto da pessoa atualizada
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizaPessoa(@PathVariable Long id, @RequestBody Pessoa pessoaDetalhes){
		java.util.Optional<Pessoa> pessoa = pessoas.findById(id);
				
		if(pessoa.isPresent()){
			Pessoa pessoaNova = pessoa.get();
			pessoaNova.setNome(pessoaDetalhes.getNome());
			pessoaNova.setEmail(pessoaDetalhes.getEmail());
			pessoaNova.setCelular(pessoaDetalhes.getCelular());

		    pessoas.save(pessoaNova);
		        return new ResponseEntity<Pessoa>(pessoaNova, HttpStatus.OK);
			
		}
		else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
			

	/**
	 * @param id - Id da pessoa a ser deletada
	 * @return retorna o status
	 */
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
