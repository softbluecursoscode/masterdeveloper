package br.com.softblue.bluefood.infrastructure.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/restaurante")
public class RestauranteController {

	
	@GetMapping(path = "/home")
	public String home() {
		return "restaurante-home";
	}
	
	
}