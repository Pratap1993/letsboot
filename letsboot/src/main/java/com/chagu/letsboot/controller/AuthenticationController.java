package com.chagu.letsboot.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chagu.letsboot.model.Users;
import com.chagu.letsboot.service.UserService;

/**
 * @author Pratap Bhanu
 *
 */
@Controller
public class AuthenticationController {

	private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	private UserService userService;

	public AuthenticationController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/login")
	public String login() {
		return "auth/login";
	}

	@GetMapping("/profile")
	public String profile() {
		return "auth/profile";
	}

	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("user", new Users());
		return "auth/register";
	}

	@PostMapping("/register")
	public String registerUser(@Valid Users user, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			logger.info("Validation errors were found while registering a new user");
			model.addAttribute("user", user);
			model.addAttribute("validationErrors", bindingResult.getAllErrors());
			return "auth/register";
		} else {
			Users newUser = userService.register(user);
			redirectAttributes.addAttribute("id", newUser.getId()).addFlashAttribute("success", true);
			return "redirect:/register";
		}
	}

	@GetMapping("/activate/{email}/{activationCode}")
	public String activateUser(@PathVariable String email, @PathVariable String activationCode) {
		Optional<Users> user = userService.findByEmailAndActivationCode(email, activationCode);
		if (user.isPresent()) {
			Users newUser = user.get();
			userService.activateUser(newUser);
			return "auth/activated";
		}
		return "redirect:/";
	}

}
