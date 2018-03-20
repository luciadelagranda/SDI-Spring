package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Publication;
import com.uniovi.entities.User;

public interface PublicationRepository extends CrudRepository<Publication, Long> {

	/**
	 * 
	 * Devuelve las publicaciones hechas por el usuario que se pasa por
	 * parametro
	 *
	 * @param pageable
	 * @param autor
	 * @return
	 */
	Page<Publication> findByAutor(Pageable pageable, User autor);

}
