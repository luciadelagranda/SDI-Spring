package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Publication;
import com.uniovi.entities.User;
import com.uniovi.repositories.PublicationRepository;

@Service
public class PublicationService {

	/**
	 * Inyecta el repositorio PublicationRepository
	 */
	@Autowired
	private PublicationRepository publicationRepository;

	/**
	 *
	 * Añade una publicacion
	 *
	 * @param publication
	 */
	public void addPublication(Publication publication) {
		publicationRepository.save(publication);

	}

	/**
	 * 
	 * Devuelve una lsita de publicaciones de un user
	 * 
	 * @param pageable
	 * @param user
	 * @return
	 */
	public Page<Publication> getPublications(Pageable pageable, User user) {
		return publicationRepository.findByAutor(pageable, user);
	}

}
