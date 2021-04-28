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

import com.eugenio.cadastro.dto.PessoaListagemCompletaDTO;
import com.eugenio.cadastro.dto.PessoaListagemDTO;
import com.eugenio.cadastro.model.Pessoa;
import com.eugenio.cadastro.service.PessoasService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/cadastro/pessoas")
public class PessoasController {
	
	@Autowired
	private PessoasService pessoasService;
	

	@GetMapping("/listagem")
	public ArrayList<PessoaListagemDTO> listarNomes() {
		return pessoasService.listarNomes();
	}

	@GetMapping
	public ArrayList<PessoaListagemCompletaDTO> listar() {
		return pessoasService.listar();
	}
	
	@PostMapping
	public PessoaListagemCompletaDTO criarPessoa( @RequestBody Pessoa pessoa) {
		return pessoasService.criarPessoa(pessoa);
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<PessoaListagemCompletaDTO> getPessoaById(@PathVariable(value = "id") Long id) {
		return pessoasService.getPessoaById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PessoaListagemCompletaDTO> atualizaPessoa(@PathVariable Long id, @RequestBody Pessoa pessoaDetalhes){
		return pessoasService.atualizaPessoa(id, pessoaDetalhes);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deletaPessoa(@PathVariable Long id){
		return pessoasService.deletaPessoa(id);
	}

}
