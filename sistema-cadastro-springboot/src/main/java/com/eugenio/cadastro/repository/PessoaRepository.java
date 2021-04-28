package com.eugenio.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eugenio.cadastro.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	
}
