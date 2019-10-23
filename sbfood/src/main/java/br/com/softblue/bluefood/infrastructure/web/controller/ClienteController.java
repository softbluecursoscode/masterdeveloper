package br.com.softblue.bluefood.infrastructure.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.softblue.bluefood.application.ValidationException;
import br.com.softblue.bluefood.application.service.ClienteService;
import br.com.softblue.bluefood.application.service.RestauranteService;
import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.cliente.ClienteRepository;
import br.com.softblue.bluefood.domain.pedido.PedidoRepository;
import br.com.softblue.bluefood.domain.restaurante.CategoriaRestaurante;
import br.com.softblue.bluefood.domain.restaurante.CategoriaRestauranteRepository;
import br.com.softblue.bluefood.domain.restaurante.ItemCardapio;
import br.com.softblue.bluefood.domain.restaurante.ItemCardapioRepository;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import br.com.softblue.bluefood.domain.restaurante.RestauranteRepository;
import br.com.softblue.bluefood.domain.restaurante.SearchFilter;
import br.com.softblue.bluefood.util.SecurityUtils;

@Controller
@RequestMapping(path = "/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CategoriaRestauranteRepository categoriaRestauranteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private ItemCardapioRepository itemCategoriaRepository;
	
	
	@PostMapping(path = "/save")
	public String save(@Valid Cliente cliente, Errors errors, Model model) throws ValidationException {
		if (!errors.hasErrors()) {
			
			try {
				clienteService.saveCliente(cliente);
				model.addAttribute("msg", "Cliente alterado com sucesso!");
				
			} catch (ValidationException e) {
				errors.rejectValue("email", null, "E-mail já existente");
			}
		}

		ControllerHelper.setEditMode(model, true);
		return "cliente-cadastro";
	}
	
	@GetMapping(path = "/home")
	public String home(Model model) {
		var categorias = categoriaRestauranteRepository.findAll(new Sort(Direction.ASC, "nome"));
		model.addAttribute("categorias", categorias);
		
		Integer loggedUserId = SecurityUtils.loggedUser().getUsuario().getId();
		var pedidos = pedidoRepository.listPedidosByCliente(loggedUserId);
		model.addAttribute("pedidos", pedidos);
		
		model.addAttribute("filter", new SearchFilter());
		
		return "cliente-home";
	}
	
	@GetMapping(path = "/edit")
	public String edit(Model model) {
		Integer loggedUserId = SecurityUtils.loggedUser().getUsuario().getId();
		model.addAttribute("cliente", clienteRepository.findById(loggedUserId).orElseThrow());
		ControllerHelper.setEditMode(model, true);
		return "cliente-cadastro";
	}
	
	@GetMapping(path = "/search")
	public String search(
			@RequestParam(value = "cmd", required = false) String command, 
			@RequestParam(value = "categoria", required = false) Integer categoriaId,
			SearchFilter filter, 
			Model model) {
		
		filter.processFilter(command);
		
		List<Restaurante> restaurantes = restauranteService.search(filter);
		model.addAttribute("restaurantes", restaurantes);
		
		List<CategoriaRestaurante> categorias = categoriaRestauranteRepository.findAll(new Sort(Direction.ASC, "nome"));
		model.addAttribute("categorias", categorias);
		
		String cep = SecurityUtils.loggedCliente().getCep();
		model.addAttribute("cep", cep);
		
		model.addAttribute("filter", filter);
		
		return "cliente-busca";
	}
	
	@GetMapping(path = "/restaurante")
	public String viewRestaurante(
			@RequestParam("restauranteId") Integer restauranteId, 
			@RequestParam(value = "categoria", required = false) 
			String categoria, Model model) {
		
		Restaurante restaurante = restauranteRepository.findById(restauranteId).orElseThrow();
		model.addAttribute("restaurante", restaurante);
		
		List<ItemCardapio> itensCardapioDestaque;
		List<ItemCardapio> itensCardapioNaoDestaque;
		
		if (categoria == null) {
			itensCardapioDestaque = itemCategoriaRepository.findByRestaurante_IdAndDestaque(restauranteId, true);
			itensCardapioNaoDestaque = itemCategoriaRepository.findByRestaurante_IdAndDestaque(restauranteId, false);
		} else {
			itensCardapioDestaque = itemCategoriaRepository.findByRestaurante_IdAndDestaqueAndCategoria(restauranteId, true, categoria);
			itensCardapioNaoDestaque = itemCategoriaRepository.findByRestaurante_IdAndDestaqueAndCategoria(restauranteId, false, categoria);
		}
		
		model.addAttribute("itensCardapioDestaque", itensCardapioDestaque);
		model.addAttribute("itensCardapioNaoDestaque", itensCardapioNaoDestaque);
		
		List<String> categorias = itemCategoriaRepository.findCategorias(restauranteId);
		model.addAttribute("categorias", categorias);
		
		String cep = SecurityUtils.loggedCliente().getCep();
		model.addAttribute("cep", cep);

		return "cliente-restaurante";
	}
}