package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Peticion;
import com.uniovi.entities.User;

public interface PeticionRepository extends CrudRepository<Peticion, Long>{
	
	@Query("SELECT c.usuarioPeticionado FROM Peticion c WHERE c.usuarioPeticionador = ?1 ORDER BY c.usuarioPeticionado ASC")
	Page<Long> searchUsuariosPeticionados(Pageable pageable, long l);
	
	@Query("SELECT c.usuarioPeticionador FROM Peticion c WHERE c.usuarioPeticionado = ?1")
	Page<Long> searchUsuariosPeticionadores(Pageable pageable, long l);

	
}