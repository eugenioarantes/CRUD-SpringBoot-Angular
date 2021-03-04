package com.eugenio.cadastro.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eugenio.cadastro.model.Cidade;
import com.eugenio.cadastro.repository.Cidades;

@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/cadastro/cidades")
public class CidadesController {

	@Autowired
	private Cidades cidades;

	@GetMapping
	public ArrayList<Cidade> listar() {
	  return (ArrayList<Cidade>) cidades.findAll();
	}
			
}