package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Peticion;
import com.uniovi.entities.User;
import com.uniovi.repositories.PeticionesRepository;

@Service
public class PeticionesService {

	@Autowired
	public PeticionesRepository peticionesRepository;

	
	public Page<Peticion> getPeticiones(Pageable pageable) {
		Page<Peticion> peticiones = peticionesRepository.findAll(pageable);
		return peticiones;
	}
	
	public void addPeticion(Peticion peticion) {
		peticionesRepository.save(peticion);
	}
	
	public Peticion getPeticion(Long id) {
		return peticionesRepository.findOne(id);
	}	
	
}
