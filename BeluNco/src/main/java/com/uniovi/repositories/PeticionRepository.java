package com.uniovi.repositories;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Peticion;
import com.uniovi.entities.User;




public interface PeticionRepository extends CrudRepository<Peticion, Long>{
	
	@Query("SELECT c.usuarioPeticionado FROM Peticion c WHERE c.usuarioPeticionador.id = ?1 ORDER BY c.usuarioPeticionado.id ASC")

	Page<User> searchUsuariosPeticionados(Pageable pageable, Long l);
	
	@Query("SELECT c.usuarioPeticionador FROM Peticion c WHERE c.usuarioPeticionado.id = ?1")
	Page<User> searchUsuariosPeticionadores(Pageable pageable, Long l);

	@Query("SELECT c FROM Peticion c WHERE c.usuarioPeticionado.id = ?1 and c.usuarioPeticionador.id = ?2 ")
	Peticion existsPeticion(long friend, long user);

	
}
