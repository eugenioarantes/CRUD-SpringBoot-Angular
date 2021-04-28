package com.eugenio.cadastro.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.eugenio.cadastro.dto.PessoaListagemCompletaDTO;
import com.eugenio.cadastro.dto.PessoaListagemDTO;
import com.eugenio.cadastro.model.Pessoa;
import com.eugenio.cadastro.repository.PessoaRepository;

@Service
public class PessoasService {

	@Autowired
	private PessoaRepository pessoas;
	
	
	/**
	 * @return retorna somente o Id e o Nome das pessoas
	 */
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
	public ArrayList<PessoaListagemCompletaDTO> listar() {
		List<Pessoa> lista = pessoas.findAll();
		List<PessoaListagemCompletaDTO> pessoasDTO = new ArrayList<>();
		lista.forEach(p -> {
			pessoasDTO.add(new PessoaListagemCompletaDTO(p.getId(),p.getNome(),p.getEmail(),p.getCelular()));
		 });
		
	  return (ArrayList<PessoaListagemCompletaDTO>) pessoasDTO;
	}
	
	/**
	 * @param pessoa - objeto da pessoa a ser salva
	 * @return salva a pessoa no repositorio
	 */
	public PessoaListagemCompletaDTO criarPessoa( @RequestBody Pessoa pessoa) {
		Pessoa p = pessoas.save(pessoa);
		PessoaListagemCompletaDTO pessoaDTO = new PessoaListagemCompletaDTO();
			pessoaDTO.setId(p.getId());
			pessoaDTO.setNome(p.getNome());
			pessoaDTO.setEmail(p.getEmail());
			pessoaDTO.setCelular(p.getCelular());
				return pessoaDTO;
	}	
	
	/**
	 * @param id - Id da pessoa a ser encontrada no repositorio
	 * @return retorna o objeto da pessoa encontrada
	 */
	public ResponseEntity<PessoaListagemCompletaDTO> getPessoaById(@PathVariable(value = "id") Long id) {
	  java.util.Optional<Pessoa> pessoa = pessoas.findById(id);
	  PessoaListagemCompletaDTO pessoaDTO = new PessoaListagemCompletaDTO();
		 pessoaDTO.setId(pessoa.get().getId());
		 pessoaDTO.setNome(pessoa.get().getNome());
		 pessoaDTO.setEmail(pessoa.get().getEmail());
		 pessoaDTO.setCelular(pessoa.get().getCelular());
		 
		if(pessoa.isPresent())
		   return new ResponseEntity<PessoaListagemCompletaDTO>(pessoaDTO, HttpStatus.OK);
		else
		   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	/**
	 * @param id - Id da pessoa a ser encontrada no repositorio
	 * @param pessoaDetalhes - Dados da pessoa a ser atualizada
	 * @return retorna o objeto da pessoa atualizada
	 */
	public ResponseEntity<PessoaListagemCompletaDTO> atualizaPessoa(@PathVariable Long id, @RequestBody Pessoa pessoaDetalhes){
		java.util.Optional<Pessoa> pessoa = pessoas.findById(id);
		PessoaListagemCompletaDTO pessoaDTO = new PessoaListagemCompletaDTO();
		
		if(pessoa.isPresent()){
			Pessoa pessoaNova = pessoa.get();
			pessoaNova.setNome(pessoaDetalhes.getNome());
			pessoaNova.setEmail(pessoaDetalhes.getEmail());
			pessoaNova.setCelular(pessoaDetalhes.getCelular());

		    pessoas.save(pessoaNova);
		    
		    pessoaDTO.setId(pessoaNova.getId());
		    pessoaDTO.setNome(pessoaNova.getNome());
		    pessoaDTO.setEmail(pessoaNova.getEmail());
		    pessoaDTO.setCelular(pessoaNova.getCelular());
		    
		        return new ResponseEntity<PessoaListagemCompletaDTO>(pessoaDTO, HttpStatus.OK);
			
		}
		else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
			

	/**
	 * @param id - Id da pessoa a ser deletada
	 * @return retorna o status
	 */
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
