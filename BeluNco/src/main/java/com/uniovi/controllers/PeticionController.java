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

	@Autowired
	public UsersService usersService;
	
	@Autowired
	public PeticionService peticionService;
	
	
	@RequestMapping("/peticion/list")
	public String getListadoPeticiones(Model model, Principal principal, Pageable pageable) {
		Page<User> lista = new PageImpl<User>(new LinkedList<User>());
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Long> peticionList = peticionService.getUsuariosPeticiones(pageable, user.getId());
		lista = peticionService.getUsersPeti(peticionList, pageable);
		model.addAttribute("peticionUserList", lista.getContent());
		model.addAttribute("page", lista);
		return "peticion/list";
	}
}
