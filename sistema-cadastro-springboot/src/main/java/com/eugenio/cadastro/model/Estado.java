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
@Table(name="estados")
@Data
@AllArgsConstructor 
@NoArgsConstructor 
public class Estado {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome", length=20)
	@NotNull
	private String nome;
	
	@Column(name="sigla", length=2)
	@NotNull
	private String sigla;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}