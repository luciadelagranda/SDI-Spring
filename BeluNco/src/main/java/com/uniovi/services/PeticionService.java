package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Peticion;
import com.uniovi.entities.User;
import com.uniovi.repositories.PeticionRepository;

@Service
public class PeticionService {
	
	@Autowired
	private PeticionRepository peticionRepository;

	public void makePeticion(long id, long id2) {
		peticionRepository.save(new Peticion(id, id2 ));
		
	}
	
	public List<Long> getUsersPeticionados(long l) {
		return peticionRepository.searchUsuariosPeticionados(l);
	}
}
