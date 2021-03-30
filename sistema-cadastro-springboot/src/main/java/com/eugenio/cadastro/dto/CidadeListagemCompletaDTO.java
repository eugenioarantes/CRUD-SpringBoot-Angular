package com.eugenio.cadastro.dto;

import com.eugenio.cadastro.model.Estado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor 
@Data
public class CidadeListagemCompletaDTO {

	private Long id;
	private String nome;
	private Estado estado;
	
}
