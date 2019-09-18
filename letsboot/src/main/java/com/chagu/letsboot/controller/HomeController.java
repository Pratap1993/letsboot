package com.chagu.letsboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Pratap Bhanu
 *
 */
@Controller
public class HomeController {

	@GetMapping("/")
	public String homePage() {
		return "redirect:/link/getLinkList";
	}

	@GetMapping("/home")
	public String homePage(Model model) {
		model.addAttribute("message", "This is Spring Boot 2 developed By Chagulu !!!");
		return "home";
	}
}
