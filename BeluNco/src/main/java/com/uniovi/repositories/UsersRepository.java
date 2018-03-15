package com.uniovi.repositories;

import com.uniovi.entities.*;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UsersRepository extends CrudRepository<User, Long>{
	
	User findByEmail(String email);
	
	Page<User> findAll(Pageable pageable);

	@Query("SELECT r FROM User r WHERE (LOWER(r.email) LIKE LOWER(?1) OR LOWER(r.name) LIKE LOWER(?1))")
	Page<User> searchByEmailAndName(Pageable pageable, String seachtext);
	
	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.peticionado = true WHERE u.id = ?1")
	void updatePeticion(Long id);
	
}
