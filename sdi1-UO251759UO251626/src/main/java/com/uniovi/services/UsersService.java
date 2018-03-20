package com.uniovi.services;

import java.util.*;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

@Service
public class UsersService {

	/**
	 * Inyecta UsersRepository
	 */
	@Autowired
	private UsersRepository usersRepository;

	/**
	 * Inyecta UsersRepository
	 */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * PostConstruct
	 */
	@PostConstruct
	public void init() {

	}

	/**
	 * 
	 * Devuelve una lista paginable de usuarios
	 * 
	 * @param pageable
	 * @return
	 */
	public Page<User> getUsers(Pageable pageable) {
		Page<User> users = usersRepository.findAll(pageable);
		return users;
	}

	/**
	 * 
	 * Registra al usuario user
	 * 
	 * @param user
	 */
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}

	/**
	 * 
	 * Registra al usuario sin encriptar la password
	 * 
	 * @param user
	 */
	public void saveUserWithoutEncode(User user) {
		usersRepository.save(user);
	}

	/**
	 * 
	 * Devuelve un usuario buscando por su email
	 * 
	 * @param email
	 * @return
	 */
	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

	/**
	 * 
	 * Devuelve una lista de usuarios segun su email/nombre
	 * 
	 * @param searchText
	 * @param pageable
	 * @return
	 */
	public Page<User> searchByEmailAndName(String searchText, Pageable pageable) {
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		searchText = "%" + searchText + "%";
		users = usersRepository.searchByEmailAndName(pageable, searchText);
		return users;
	}

	/**
	 * 
	 * Actualiza el user
	 * 
	 * @param user
	 */
	public void update(User user) {
		usersRepository.save(user);

	}

	/**
	 * 
	 * Devuelve el usuario cuyo id es el del parametro
	 *
	 * @param id
	 * @return
	 */
	public User getUser(Long id) {
		return usersRepository.findOne(id);
	}
	
	/**
	 * Elimina un usuario
	 * @param id, id del usuario a eliminar
	 */
	public void deleteUser(Long id) {
		usersRepository.delete(id);
		
	}

}
