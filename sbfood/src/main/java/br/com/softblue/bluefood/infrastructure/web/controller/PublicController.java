package br.com.softblue.bluefood.infrastructure.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
import br.com.softblue.bluefood.domain.restaurante.RestauranteRepository;

@Controller
@RequestMapping(path = "/public")
public class PublicController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private CategoriaRestauranteRepository categoriaRestauranteRepository;
	
	@GetMapping(path = "/cliente/new")
	public String createNew(Model model) {
		model.addAttribute("cliente", new Cliente());
		defineCreateMode(model);
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
		
		defineCreateMode(model);
		return "cliente-cadastro";
	}

	@GetMapping(path = "/restaurante/new")
	public String load(Model model) {
		model.addAttribute("restaurante", restauranteRepository.findById(1).orElse(null));
		addCategoriasToRequest(model);
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
		
		addCategoriasToRequest(model);
		return "restaurante-cadastro";
	}
	
	private void addCategoriasToRequest(Model model) {
		model.addAttribute("categorias", categoriaRestauranteRepository.findAll(new Sort(Sort.Direction.ASC, "nome")));
	}
	
	private void defineCreateMode(Model model) {
		model.addAttribute("editMode", false);
	}
}