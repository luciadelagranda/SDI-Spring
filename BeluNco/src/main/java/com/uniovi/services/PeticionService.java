package com.uniovi.services;



import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import org.springframework.stereotype.Service;

import com.uniovi.entities.Peticion;
import com.uniovi.entities.User;
import com.uniovi.repositories.PeticionRepository;

import com.uniovi.repositories.UsersRepository;

@Service
public class PeticionService {
	
	@Autowired
	private PeticionRepository peticionRepository;
	
	@Autowired
	private UsersRepository usersRepository;


	

	public void makePeticion(long id, long id2) {
		peticionRepository.save(new Peticion(id, id2 ));
		
	}
	

	public Page<Long> getUsersPeticionados(Pageable pageable, long l) {
		return peticionRepository.searchUsuariosPeticionados(pageable, l);
	}
	
	public Page<Long> getUsuariosPeticiones(Pageable pageable, long id){
		return peticionRepository.searchUsuariosPeticionadores(pageable, id);
	}
	
	public Page<User> getUsersPeti(Page<Long> peticionList, Pageable pageable){
		Page<User> res =  new PageImpl<User>(new LinkedList<User>());
		for(int i=0;i<peticionList.getContent().size();i++){
			res.getContent().add(usersRepository.findById(peticionList.getContent().get(i)));
		}
		return res;
	}
	

}
