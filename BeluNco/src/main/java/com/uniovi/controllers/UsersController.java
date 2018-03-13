package com.uniovi.controllers;


import org.springframework.beans.factory.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.uniovi.entities.*;
import com.uniovi.services.UsersService;

@Controller
public class UsersController {
	

	@Autowired 
	private UsersService usersService;

	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
	    return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute User user, Model model) {

		usersService.addUser(user);
		return "redirect:/";
	}
	


}
