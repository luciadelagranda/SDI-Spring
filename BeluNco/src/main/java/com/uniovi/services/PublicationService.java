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

	@Autowired
	private PublicationRepository publicationRepository;
	
	public void addPublication(Publication publication) {
		publicationRepository.save(publication);
		
	}

	public Page<Publication> getPublications(Pageable pageable, User user) {
		return publicationRepository.findByAutor(pageable,user);
	}

	

}
