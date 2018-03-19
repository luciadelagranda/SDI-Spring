package com.uniovi.services;



import javax.annotation.PostConstruct;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RolesService rolesService;
	
	@PostConstruct
	public void init() {
		User user1 = new User("1", "Pedro", "Díaz");
		user1.setPassword("1");
		user1.setRole(rolesService.getRoles()[0]);
		
		User user2 = new User("2", "Lucas", "Núñez");
		user2.setPassword("1");
		user2.setRole(rolesService.getRoles()[0]);

		User user3 = new User("3", "María", "Rodríguez");
		user3.setPassword("1");
		user3.setRole(rolesService.getRoles()[0]);

		User user4 = new User("4", "María", "Rodríguez");
		user4.setPassword("1");
		user4.setRole(rolesService.getRoles()[0]);

		User user5 = new User("5", "Lucía", "Rodríguez");
		user5.setPassword("1");
		user5.setRole(rolesService.getRoles()[1]);

		usersService.saveUser(user1);
		usersService.saveUser(user2);
		usersService.saveUser(user3);
		usersService.saveUser(user4);
		usersService.saveUser(user5);
	}

}
