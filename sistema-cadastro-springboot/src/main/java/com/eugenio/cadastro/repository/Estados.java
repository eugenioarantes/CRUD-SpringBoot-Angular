package com.eugenio.cadastro.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.eugenio.cadastro.model.Estado;

public interface Estados extends JpaRepository<Estado, Long> {
	
}
