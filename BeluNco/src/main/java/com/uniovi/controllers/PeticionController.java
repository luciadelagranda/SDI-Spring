package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.User;
import com.uniovi.services.PeticionService;
import com.uniovi.services.UsersService;

@Controller
public class PeticionController {

	/**
	 * Inyecta el servicio UsersService
	 */
	@Autowired
	public UsersService usersService;

	/**
	 * Inyecta el servicio PeticionService
	 */
	@Autowired
	public PeticionService peticionService;

	/**
	 * 
	 * Devuelve un listado con las peticiones recibidas del usuario actual
	 * 
	 * @param model
	 * @param principal
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/peticion/list")
	public String getListadoPeticiones(Model model, Principal principal, Pageable pageable) {
		Page<User> lista = new PageImpl<User>(new LinkedList<User>());
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		lista = peticionService.getUsuariosPeticionadores(pageable, user);
		Page<User> friendsList = user.getFriendsList();
		model.addAttribute("friendsList", friendsList.getContent());
		model.addAttribute("peticionUserList", lista.getContent());
		model.addAttribute("page", lista);
		return "peticion/list";
	}

	/**
	 * 
	 * Actualiza la lista de peticiones del usuario actual
	 * 
	 * @param model
	 * @param pageable
	 * @param principal
	 * @return
	 */
	@RequestMapping("/peticion/list/update")
	public String updateList(Model model, Pageable pageable, Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<User> users = peticionService.getUsuariosPeticionadores(pageable, user);
		model.addAttribute("peticionUserList", users.getContent());
		return "peticion/list :: tableUserPeticion";
	}

}
