package com.eugenio.cadastro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor 
@Data
public class PessoaListagemCompletaDTO {
	
	private Long Id;
	private String nome;
	private String email;
	private String celular;
	
}
