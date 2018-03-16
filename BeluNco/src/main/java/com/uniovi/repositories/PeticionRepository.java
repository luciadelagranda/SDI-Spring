package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Peticion;

public interface PeticionRepository extends CrudRepository<Peticion, Long>{
	
	@Query("SELECT c.usuarioPeticionado FROM Peticion c WHERE c.usuarioPeticionador = ?1 ORDER BY c.usuarioPeticionado ASC")
	List<Long> searchUsuariosPeticionados(long l);

}
