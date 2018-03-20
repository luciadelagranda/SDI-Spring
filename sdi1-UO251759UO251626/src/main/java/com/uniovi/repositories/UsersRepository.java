package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.*;

public interface UsersRepository extends CrudRepository<User, Long> {

	/**
	 * 
	 * Busca un usuario por el email que se pasa por parametro
	 * 
	 * @param email
	 * @return
	 */
	User findByEmail(String email);

	/**
	 * 
	 * Lista todos los usuarios
	 * 
	 * @param pageable
	 * @return
	 */
	Page<User> findAll(Pageable pageable);

	/**
	 * 
	 * Al ejecutar la consulta lista todos los usuarios cuyo email/nombre
	 * contienen la cadena que se le pasa por parametro
	 * 
	 * @param pageable
	 * @param seachtext
	 * @return
	 */
	@Query("SELECT r FROM User r WHERE (LOWER(r.email) LIKE LOWER(?1) OR LOWER(r.name) LIKE LOWER(?1))")
	Page<User> searchByEmailAndName(Pageable pageable, String seachtext);

}
