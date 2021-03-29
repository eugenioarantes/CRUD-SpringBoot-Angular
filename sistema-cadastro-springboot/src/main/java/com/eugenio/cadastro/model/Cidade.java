package com.eugenio.cadastro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cidades")
@AllArgsConstructor 
@NoArgsConstructor 
@Data
public class Cidade {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@ManyToOne(fetch = FetchType.LAZY)
	//@ManyToOne
	@JoinColumn(name = "estado")
	private Estado estado;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}