package br.com.softblue.bluefood.infrastructure.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.softblue.bluefood.application.ValidationException;
import br.com.softblue.bluefood.application.service.ClienteService;
import br.com.softblue.bluefood.application.service.RestauranteService;
import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.restaurante.CategoriaRestauranteRepository;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;

@Controller
@RequestMapping(path = "/public")
public class PublicController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private CategoriaRestauranteRepository categoriaRestauranteRepository;
	
	@GetMapping(path = "/cliente/new")
	public String newCliente(Model model) {
		model.addAttribute("cliente", new Cliente());
		ControllerHelper.setEditMode(model, false);
		return "cliente-cadastro";
	}

	@PostMapping(path = "/cliente/save")
	public String save(@Valid Cliente cliente, Errors errors, Model model) throws ValidationException {
		if (!errors.hasErrors()) {
			try {
				clienteService.saveCliente(cliente);
				model.addAttribute("msg", "Cliente cadastrado com sucesso!");
				
			} catch (ValidationException e) {
				errors.rejectValue("email", null, "E-mail já existente");
			}
		}
		
		ControllerHelper.setEditMode(model, false);
		return "cliente-cadastro";
	}

	@GetMapping(path = "/restaurante/new")
	public String newRestaurante(Model model) {
		model.addAttribute("restaurante", new Restaurante());
		ControllerHelper.addCategoriasToRequest(categoriaRestauranteRepository, model);
		ControllerHelper.setEditMode(model, false);
		return "restaurante-cadastro";
	}

	@PostMapping(path = "/restaurante/save")
	public String save(@Valid Restaurante restaurante, Errors errors, Model model) {
		if (!errors.hasErrors()) {
			try {
				restauranteService.saveRestaurante(restaurante);
				model.addAttribute("msg", "Restaurante cadastrado com sucesso!");
			
			} catch (ValidationException e) {
				errors.rejectValue("email", null, "E-mail já existente");
			}
		}
		
		ControllerHelper.addCategoriasToRequest(categoriaRestauranteRepository, model);
		ControllerHelper.setEditMode(model, false);
		return "restaurante-cadastro";
	}
}