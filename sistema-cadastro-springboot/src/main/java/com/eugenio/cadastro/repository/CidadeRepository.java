package com.eugenio.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eugenio.cadastro.model.Cidade;


public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
}
