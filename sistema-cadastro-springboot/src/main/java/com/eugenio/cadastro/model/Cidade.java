package com.eugenio.cadastro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cidades")
public class Cidade {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "estado")
	private Estado estado;
	
	public Cidade() {
		this.nome="";
		estado= new Estado();
	}

	public Cidade(Long id, String nome, Estado estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.estado = estado;
	}
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public Estado getEstado() {
		return estado;
	}



	public void setEstado(Estado estado) {
		this.estado = estado;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}