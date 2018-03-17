package com.uniovi.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import org.springframework.stereotype.Service;

import com.uniovi.entities.Peticion;
import com.uniovi.entities.User;
import com.uniovi.repositories.PeticionRepository;


@Service
public class PeticionService {
	
	@Autowired
	private PeticionRepository peticionRepository;


	public void makePeticion(User user1, User user2) {
		peticionRepository.save(new Peticion(user1, user2 ));
		
	}
	


	public Page<User> getUsersPeticionados(Pageable pageable, Long l) {
		return peticionRepository.searchUsuariosPeticionados(pageable, l);
	}
	
	public Page<User> getUsuariosPeticionadores(Pageable pageable, User user){
		return peticionRepository.searchUsuariosPeticionadores(pageable, user.getId());
	}


	public boolean isPeticion(User friend, User user) {
		Peticion peti = peticionRepository.existsPeticion(friend.getId(), user.getId());
		if(peti!=null)
			return true;
		return false;

	}


	public void removePeticion(User friend, User user) {
		peticionRepository.delete(peticionRepository.existsPeticion(friend.getId(), user.getId()).getId());
		
	}
	
}
