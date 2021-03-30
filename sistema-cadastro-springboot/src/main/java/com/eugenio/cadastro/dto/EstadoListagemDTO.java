package com.eugenio.cadastro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor 
@Data
public class EstadoListagemDTO {
	
	private Long Id;
	private String nome;
	private String sigla;
}
