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
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Publication;
import com.uniovi.entities.User;
import com.uniovi.services.PublicationService;
import com.uniovi.services.UsersService;

@Controller
public class PublicationController {

	/**
	 * Inyecta el servicio UsersService
	 */
	@Autowired
	private UsersService usersService;

	/**
	 * Inyecta el servicio PublicationService
	 */
	@Autowired
	private PublicationService publicationService;

	/**
	 * 
	 * Añade una publicacion
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/publication/add", method = RequestMethod.GET)
	public String publication(Model model) {
		model.addAttribute("publication", publicationService.getPublications(null, null));
		return "publication/add";
	}

	/**
	 * 
	 * 
	 * 
	 * @param model
	 * @param publication
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/publication/add", method = RequestMethod.POST)
	public String signup(Model model, Publication publication, Principal principal) {
		String title = publication.getTitle();
		String des = publication.getDescripcion();
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		publicationService.addPublication(new Publication(user, title, des));
		return "redirect:/publication/list";
	}

	/**
	 * 
	 * 
	 * 
	 * @param model
	 * @param principal
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/publication/list")
	public String getListadoPeticiones(Model model, Principal principal, Pageable pageable) {
		Page<Publication> lista = new PageImpl<Publication>(new LinkedList<Publication>());
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		lista = publicationService.getPublications(pageable, user);
		model.addAttribute("publicationUserList", lista.getContent());
		model.addAttribute("page", lista);
		return "publication/list";
	}

	/**
	 * 
	 * 
	 * 
	 * @param model
	 * @param pageable
	 * @param principal
	 * @return
	 */
	@RequestMapping("/publication/list/update")
	public String updateList(Model model, Pageable pageable, Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Publication> users = publicationService.getPublications(pageable, user);
		model.addAttribute("publicationUserList", users.getContent());
		return "publication/list :: tableUserPublication";
	}
}
