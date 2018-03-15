package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Peticion;
import com.uniovi.entities.User;


public interface PeticionesRepository extends CrudRepository<Peticion, Long>{

	Page<Peticion> findAll(Pageable pageable);
	
}