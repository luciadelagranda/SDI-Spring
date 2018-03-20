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

	/**
	 * Inyecta el repositorio PeticionRepository
	 */
	@Autowired
	private PeticionRepository peticionRepository;

	/**
	 * 
	 * Hace una peticion del user2 al user1
	 * 
	 * @param user1
	 * @param user2
	 */
	public void makePeticion(User user1, User user2) {
		peticionRepository.save(new Peticion(user1, user2));

	}

	/**
	 * 
	 * Devuelve la lista de usuarios peticionados por el parametro
	 * 
	 * @param pageable
	 * @param l
	 * @return
	 */
	public Page<User> getUsersPeticionados(Pageable pageable, Long l) {
		return peticionRepository.searchUsuariosPeticionados(pageable, l);
	}

	/**
	 * 
	 * Devuelve la lista de usuarios peticionadores del user del parametro
	 * 
	 * @param pageable
	 * @param user
	 * @return
	 */
	public Page<User> getUsuariosPeticionadores(Pageable pageable, User user) {
		return peticionRepository.searchUsuariosPeticionadores(pageable, user.getId());
	}

	/**
	 * 
	 * True si hay una peticion entre los dos usuarios False lo contrario
	 * 
	 * @param friend
	 * @param user
	 * @return
	 */
	public boolean isPeticion(User friend, User user) {
		Peticion peti = peticionRepository.existsPeticion(friend.getId(), user.getId());
		if (peti != null)
			return true;
		return false;

	}

	/**
	 * 
	 * Elimina la peticion. Se usa cuando se acepta la peticion y hay que
	 * eliminarla puesto que ya esta aceptada
	 * 
	 * @param friend
	 * @param user
	 */
	public void removePeticion(User friend, User user) {
		peticionRepository.delete(peticionRepository.existsPeticion(friend.getId(), user.getId()).getId());

	}

}
