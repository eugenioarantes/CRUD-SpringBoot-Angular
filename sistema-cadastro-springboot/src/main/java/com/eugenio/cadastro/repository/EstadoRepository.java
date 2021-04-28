package com.eugenio.cadastro.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.eugenio.cadastro.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
	
}
