package com.uniovi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	/**
	 * 
	 * Devuelve la vista principal
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
		return "index";
	}

}
