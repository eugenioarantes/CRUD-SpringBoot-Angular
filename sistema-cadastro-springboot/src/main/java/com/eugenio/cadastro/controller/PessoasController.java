package com.eugenio.cadastro.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eugenio.cadastro.model.Pessoa;
import com.eugenio.cadastro.repository.Pessoas;

@RestController 
@RequestMapping("/cadastro/pessoas")
public class PessoasController {

	@Autowired
	private Pessoas pessoas;

	@GetMapping
	public ArrayList<Pessoa> listar() {
	  return (ArrayList<Pessoa>) pessoas.findAll();
	}
	
	
}
