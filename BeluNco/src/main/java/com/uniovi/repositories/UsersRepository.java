package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.*;





public interface UsersRepository extends CrudRepository<User, Long>{
	
	User findByEmail(String email);
	
	Page<User> findAll(Pageable pageable);
	
	User findById(long id);

	@Query("SELECT r FROM User r WHERE (LOWER(r.email) LIKE LOWER(?1) OR LOWER(r.name) LIKE LOWER(?1))")
	Page<User> searchByEmailAndName(Pageable pageable, String seachtext);
	

	
	
}
