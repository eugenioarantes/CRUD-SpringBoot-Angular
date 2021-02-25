package com.eugenio.cadastro.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eugenio.cadastro.model.Estado;
import com.eugenio.cadastro.repository.Estados;

@RestController 
@RequestMapping("/cadastro/estados")
public class EstadosController {

	@Autowired
	private Estados estados;

	@GetMapping
	public ArrayList<Estado> listar() {
	  return (ArrayList<Estado>) estados.findAll();
	}
	
	
}