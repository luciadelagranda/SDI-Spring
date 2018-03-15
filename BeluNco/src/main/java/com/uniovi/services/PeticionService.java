package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Peticion;
import com.uniovi.repositories.PeticionRepository;

@Service
public class PeticionService {
	
	@Autowired
	private PeticionRepository peticionRepository;

	public void makePeticion(long id, long id2) {
		peticionRepository.save(new Peticion(id, id2 ));
		
	}

}
