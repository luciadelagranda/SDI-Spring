package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.uniovi.entities.*;
import com.uniovi.services.PeticionService;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {

	/**
	 * Inyecta el servicio UsersService
	 */
	@Autowired
	private UsersService usersService;

	/**
	 * Inyecta el servicio SecurityService
	 */
	@Autowired
	private SecurityService securityService;

	/**
	 * Inyecta el servicio SignUpFormValidator
	 */
	@Autowired
	private SignUpFormValidator signUpFormValidator;
	
	/**
	 * Inyecta el servicio PeticionService
	 */
	@Autowired
	private PeticionService peticionService;
	
	@Autowired
	private RolesService rolesService;

	/**
	 * 
	 * Permite registrarse a un usuario
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	/**
	 * 
	 * Permite registrar a un usuario si en el formulario no hay errores
	 * 
	 * @param user
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute User user, BindingResult result, Model model) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		user.setRole(rolesService.getRoles()[0]);
		usersService.saveUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:";
	}

	/**
	 * 
	 * Permite iniciar sesion en la aplicacion si no hay errores
	 * 
	 * @param model
	 * @param error
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, @RequestParam(name = "error", required = false) String error) {
		model.addAttribute("error", error);
		return "login";
	}

	/**
	 * 
	 * Devuelve un listado de los usuarios de la aplicacion
	 * 
	 * @param model
	 * @param searchText
	 * @param pageable
	 * @param principal
	 * @return
	 */
	@RequestMapping("/user/list")
	public String getListado(Model model, 
			@RequestParam(value = "", required = false) String searchText, Pageable pageable, Principal principal) {
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		if (searchText != null && !searchText.isEmpty()) {
			users = usersService.searchByEmailAndName(searchText, pageable);
		} else {
			users = usersService.getUsers(pageable);
		}
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		
		Page<User> peticionList = peticionService.getUsersPeticionados(pageable, user.getId());
		Page<User> friendsList = user.getFriendsList();
		model.addAttribute("friendsList", friendsList.getContent());
		model.addAttribute("peticionsList", peticionList.getContent());
		model.addAttribute("usersList", users.getContent());
		model.addAttribute("page", users);
		
		return "user/list";
	}
	
	/**
	 * 
	 * Actualiza la lista de usuarios de la aplicacion
	 * 
	 * @param model
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/user/list/update")
	public String updateList(Model model, Pageable pageable) { 
		Page<User> users = usersService.getUsers(pageable);
		model.addAttribute("usersList", users.getContent());
		return "user/list :: tableUser";
	}
	
	/**
	 * 
	 * Hace una peticion de amistad al usuario cuyo id se le pasa por parametro
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/{id}/makePeticion", method = RequestMethod.GET)
	public String makePeticion(Model model, @PathVariable Long id) {
		User friend = usersService.getUser(id);
		Authentication sesion = SecurityContextHolder.getContext().getAuthentication();
		String email = sesion.getName();
		User user = usersService.getUserByEmail(email);
		peticionService.makePeticion(friend,user);
		
		return "redirect:/user/list";
	}
	
	/**
	 * 
	 * Permite aceptar la peticion de amistad de un usuario cuyo id se le pasa por parametro
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/{id}/addFriend", method = RequestMethod.GET)
	public String addFriend(Model model, @PathVariable Long id) {
		User friend = usersService.getUser(id);
		Authentication sesion = SecurityContextHolder.getContext().getAuthentication();
		String email = sesion.getName();
		User user = usersService.getUserByEmail(email);
		if(peticionService.isPeticion(user,friend)) {
			user.addFriend(friend);
			usersService.saveUserWithoutEncode(user);
			peticionService.removePeticion(user,friend);
		}
		
		return "redirect:/user/list";
	}
	
	
}
