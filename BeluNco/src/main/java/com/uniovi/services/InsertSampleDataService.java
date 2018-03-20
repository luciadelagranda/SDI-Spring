package com.uniovi.services;



import javax.annotation.PostConstruct;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Publication;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	
	/**
	 * Inyecta el servicio UsersService
	 */
	@Autowired
	private UsersService usersService;
	
	/**
	 * Inyecta el servicio RolesService
	 */
	@Autowired
	private RolesService rolesService;
	
	/**
	 * Inyecta el servicio PeticionService
	 */
	@Autowired
	private PeticionService peticionsService;
	
	/**
	 * Inyecta el servicio PublicationService
	 */
	@Autowired
	private PublicationService publicationService;

	/**
	 * PostConstruct
	 */
	@PostConstruct
	public void init() {
		
		/*AÑADIMOS LOS USUARIOS*/
		User user1 = new User("pedrito@gmail.com", "Pedro", "Díaz");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);
		
		User user2 = new User("lucas@gmail.com", "Lucas", "Núñez");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);

		User user3 = new User("maria@gmail.com", "María", "Rodríguez");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);

		User user4 = new User("petra@gmail.com", "Petra", "Rodríguez");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);
		
		User user5 = new User("dakota_12@gmail.com", "Dakota", "Malaespina");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[0]);
		
		User user6 = new User("anna@gmail.com", "Anna", "Ivañez");
		user6.setPassword("123456");
		user6.setRole(rolesService.getRoles()[0]);
		
		User user7 = new User("unai@gmail.com", "Unai", "Perez");
		user7.setPassword("123456");
		user7.setRole(rolesService.getRoles()[0]);
		
		User user8 = new User("saul@gmail.com", "Saul", "Castillo");
		user8.setPassword("123456");
		user8.setRole(rolesService.getRoles()[0]);
		
		User user9 = new User("pablo@gmail.com", "Pablo", "Baragaño");
		user9.setPassword("123456");
		user9.setRole(rolesService.getRoles()[1]);

		User user10 = new User("lucia@gmail.com", "Lucía", "de la Granda");
		user10.setPassword("123456");
		user10.setRole(rolesService.getRoles()[1]);

		usersService.saveUser(user1);
		usersService.saveUser(user2);
		usersService.saveUser(user3);
		usersService.saveUser(user4);
		usersService.saveUser(user5);
		usersService.saveUser(user6);
		usersService.saveUser(user7);
		usersService.saveUser(user8);
		usersService.saveUser(user9);
		usersService.saveUser(user10);
		
		/*AÑADIMOS PETICIONES*/
		peticionsService.makePeticion(user1, user3);
		peticionsService.makePeticion(user3, user4);
		peticionsService.makePeticion(user5, user6);
		peticionsService.makePeticion(user6, user3);
		peticionsService.makePeticion(user6, user2);
		peticionsService.makePeticion(user6, user7);
		
		/*AÑADIMOS AMIGOS*/
		user1.addFriend(user6);
		user1.addFriend(user5);
		usersService.saveUserWithoutEncode(user1);
		user5.addFriend(user3);
		usersService.saveUserWithoutEncode(user5);
		user8.addFriend(user9);
		usersService.saveUserWithoutEncode(user8);
		user9.addFriend(user1);
		usersService.saveUserWithoutEncode(user9);
		
		/*AÑADIMOS PUBLICACIONES*/
		publicationService.addPublication(new Publication(user1, "Influencer favorita", "Dulceida"));
		publicationService.addPublication(new Publication(user2, "Influencer favorita", "Paula gonu"));
		publicationService.addPublication(new Publication(user3, "Me encanta la moda", "Soy un fanático de la moda"));
		publicationService.addPublication(new Publication(user4, "Influencer españoles", "Estoy en desacuerdo con esta moda"));
		publicationService.addPublication(new Publication(user5, "Necesito ayuda para mi usb", "Necesito recuperar datos de un USB"));
		publicationService.addPublication(new Publication(user6, "Influencer favorita", "El desvan de belu"));
		publicationService.addPublication(new Publication(user7, "Influencer favorita", "Grace Villareal"));
		publicationService.addPublication(new Publication(user8, "Youtuber favorita", "Amarna miller"));
		publicationService.addPublication(new Publication(user9, "Para las mejores compras en aliexpress", "Raquel tres y punto"));
		publicationService.addPublication(new Publication(user10, "Influencer favorita", "El desvan de belu"));

		
	}

}
