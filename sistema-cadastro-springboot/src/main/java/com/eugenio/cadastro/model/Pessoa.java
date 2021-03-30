package com.eugenio.cadastro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="pessoas")
@Data
@AllArgsConstructor 
@NoArgsConstructor 
public class Pessoa {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome", length=50)
	@NotNull
	private String nome;
	
	@Column(name="email",length=50)
	@NotNull
	private String email;
	
	@Column(name="celular",length=20)
	@NotNull
	private String celular;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}