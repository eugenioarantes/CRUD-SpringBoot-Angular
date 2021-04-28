package com.eugenio.cadastro.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.eugenio.cadastro.dto.CidadeListagemCompletaDTO;
import com.eugenio.cadastro.dto.CidadeListagemDTO;
import com.eugenio.cadastro.model.Cidade;
import com.eugenio.cadastro.repository.CidadeRepository;

@Service
public class CidadesService { 

	@Autowired
	private CidadeRepository cidades;

	/**
	 * @return retorna somente o Id e o Nome das cidades
	 */
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
	public ArrayList<CidadeListagemCompletaDTO> listar() {

		List<Cidade> lista = cidades.findAll();
		List<CidadeListagemCompletaDTO> cidadesDTO = new ArrayList<>();
		lista.forEach(c -> {
			cidadesDTO.add(new CidadeListagemCompletaDTO(c.getId(),c.getNome(),c.getEstado()));
		 });
		
	  return (ArrayList<CidadeListagemCompletaDTO>) cidadesDTO;
	}
	
	/**
	 * @param cidade - recebe a cidade para salvar
	 * @return salva a cidade no repositório
	 */
	public CidadeListagemCompletaDTO criarCidade(@Validated @RequestBody Cidade cidade) {
		Cidade c = cidades.save(cidade);
		CidadeListagemCompletaDTO cidadeDTO = new CidadeListagemCompletaDTO();
			cidadeDTO.setId(c.getId());
			cidadeDTO.setNome(c.getNome());
			cidadeDTO.setEstado(c.getEstado());
				return cidadeDTO;
	}	
	
	/**
	 * @param id - id da cidade a encontrar
	 * @return retorna a cidade encontrada a partir do id
	 */
	public ResponseEntity<CidadeListagemCompletaDTO> getCidadeById(@PathVariable(value = "id") Long id) {
		java.util.Optional<Cidade> cidade = cidades.findById(id);
			CidadeListagemCompletaDTO cidadeDTO = new CidadeListagemCompletaDTO();
			 cidadeDTO.setId(cidade.get().getId());
			 cidadeDTO.setNome(cidade.get().getNome());
			 cidadeDTO.setEstado(cidade.get().getEstado());
						
			if(cidade.isPresent())
				return new ResponseEntity<CidadeListagemCompletaDTO>(cidadeDTO, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	/**
	 * @param id - Id da cidade que deve ser atualizada
	 * @param cidadeDetalhes - objeto cidade com os novos dados para ser atualizado
	 * @return - retorna a cidade atualizada
	 */
	public ResponseEntity<CidadeListagemCompletaDTO> atualizaCidade(@PathVariable Long id, @RequestBody Cidade cidadeDetalhes){
		java.util.Optional<Cidade> cidade = cidades.findById(id);
		 CidadeListagemCompletaDTO cidadeDTO = new CidadeListagemCompletaDTO();
		  
			if(cidade.isPresent()){
				Cidade cidadeNova = cidade.get();
				cidadeNova.setNome(cidadeDetalhes.getNome());
				cidadeNova.setEstado(cidadeDetalhes.getEstado());

				  cidades.save(cidadeNova);
				  
				   cidadeDTO.setId(cidadeNova.getId());
				   cidadeDTO.setNome(cidadeNova.getNome());
				   cidadeDTO.setEstado(cidadeNova.getEstado());
				   
				    return new ResponseEntity<CidadeListagemCompletaDTO>(cidadeDTO, HttpStatus.OK);
			  }  
			else
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	

	  /**
	 * @param id - Id da cidade a ser deletada
	 * @return retorna o status 
	 */
      public ResponseEntity<Map<String, Boolean>>deletaCidade(@PathVariable Long id){ 
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