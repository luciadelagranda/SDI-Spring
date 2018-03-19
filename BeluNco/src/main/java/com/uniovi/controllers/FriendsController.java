package com.uniovi.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

@Controller
public class FriendsController {

	/**
	 * Inyecta el servicio UsersService
	 */
	@Autowired
	public UsersService usersService;

	/**
	 * 
	 * Devuelve el listado de los amigos del usuario actual
	 * 
	 * @param model
	 * @param principal
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/friend/list")
	public String getListadoAmidos(Model model, Principal principal, Pageable pageable) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<User> friendsList = user.getFriendsList();
		model.addAttribute("friendsList", friendsList.getContent());
		model.addAttribute("page", friendsList);
		return "friend/list";
	}

	/**
	 * 
	 * Actualiza la lista de amigos de el usuario actual
	 * 
	 * @param model
	 * @param pageable
	 * @param principal
	 * @return
	 */
	@RequestMapping("/friend/list/update")
	public String updateList(Model model, Pageable pageable, Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<User> friendsList = user.getFriendsList();
		model.addAttribute("friendsList", friendsList.getContent());
		return "friend/list :: tableUserFriends";
	}
}
