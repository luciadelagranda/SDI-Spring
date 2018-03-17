package com.uniovi.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Peticion;




public interface PeticionRepository extends CrudRepository<Peticion, Long>{
	
	@Query("SELECT c.usuarioPeticionado FROM Peticion c WHERE c.usuarioPeticionador = ?1 ORDER BY c.usuarioPeticionado ASC")

	Page<Long> searchUsuariosPeticionados(Pageable pageable, Long l);
	
	@Query("SELECT c.usuarioPeticionador FROM Peticion c WHERE c.usuarioPeticionado = ?1")
	Page<Long> searchUsuariosPeticionadores(Pageable pageable, Long l);

	
}
