package com.uniovi.controllers;

import java.security.Principal;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

@Controller
public class AdminController {

	@Autowired
	public UsersService usersService;

	@RequestMapping(value = "admin/login", method = RequestMethod.GET)
	public String loginAdmin(Model model, @RequestParam(name = "error", required = false) String error) {
		model.addAttribute("error", error);
		return "admin/login";
	}

	@RequestMapping("/admin/list")
	public String getListado(Model model, @RequestParam(value = "", required = false) String searchText,
			Pageable pageable, Principal principal) {

		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		if (searchText != null && !searchText.isEmpty()) {
			users = usersService.searchByEmailAndName(searchText, pageable);
		} else {
			users = usersService.getUsers(pageable);
		}

		model.addAttribute("usersList", users.getContent());
		model.addAttribute("page", users);

		return "admin/list";
	}
	
	@RequestMapping("/admin/list/update")
	public String updateList(Model model, Pageable pageable) {
		Page<User> users = usersService.getUsers(pageable);
		model.addAttribute("usersList", users.getContent());
		return "admin/list :: tableUsers";
	}
	
	
	@RequestMapping("/admin/{id}/delete" )
	public String delete(@PathVariable Long id){
		User user = usersService.getUser(id);
		user.unlink(user);
		usersService.saveUser(user);
		usersService.deleteUser(id);
		return "redirect:/user/list";
	}
}
