package br.com.softblue.bluefood.infrastructure.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.softblue.bluefood.application.ValidationException;
import br.com.softblue.bluefood.application.service.RelatorioService;
import br.com.softblue.bluefood.application.service.RestauranteService;
import br.com.softblue.bluefood.domain.pedido.Pedido;
import br.com.softblue.bluefood.domain.pedido.PedidoRepository;
import br.com.softblue.bluefood.domain.pedido.RelatorioItemFaturamento;
import br.com.softblue.bluefood.domain.pedido.RelatorioItemFilter;
import br.com.softblue.bluefood.domain.pedido.RelatorioPedidoFilter;
import br.com.softblue.bluefood.domain.restaurante.CategoriaRestauranteRepository;
import br.com.softblue.bluefood.domain.restaurante.ItemCardapio;
import br.com.softblue.bluefood.domain.restaurante.ItemCardapioRepository;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import br.com.softblue.bluefood.domain.restaurante.RestauranteRepository;
import br.com.softblue.bluefood.util.SecurityUtils;

@Controller
@RequestMapping(path = "/restaurante")
public class RestauranteController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemCardapioRepository itemCardapioRepository;
	
	@Autowired
	private RelatorioService relatorioService;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CategoriaRestauranteRepository categoriaRestauranteRepository;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@GetMapping(path = "/home")
	public String home(Model model) {
		Restaurante restaurante = SecurityUtils.loggedRestaurante();
		
		List<Pedido> pedidos = pedidoRepository.findByRestaurante_Id(restaurante.getId());
		model.addAttribute("pedidos", pedidos);
		
		return "restaurante-home";
	}
	
	@GetMapping(path = "/edit")
	public String edit(Model model) {
		Restaurante restaurante = SecurityUtils.loggedRestaurante();
		
		model.addAttribute("restaurante", restauranteRepository.findById(restaurante.getId()).orElseThrow());
		ControllerHelper.setEditMode(model, true);
		
		ControllerHelper.addCategoriasToRequest(categoriaRestauranteRepository, model);
		
		return "restaurante-cadastro";
	}
	
	@PostMapping(path = "/save")
	public String save(@Valid Restaurante restaurante, Errors errors, Model model) throws ValidationException {
		if (!errors.hasErrors()) {
			try {
				restauranteService.saveRestaurante(restaurante);
				model.addAttribute("msg", "Restaurante alterado com sucesso!");
				
			} catch (ValidationException e) {
				errors.rejectValue("email", null, "E-mail já existente");
			}
		}

		ControllerHelper.setEditMode(model, true);
		ControllerHelper.addCategoriasToRequest(categoriaRestauranteRepository, model);
		return "restaurante-cadastro";
	}
	
	@GetMapping(path = "/pedido")
	public String viewPedido(@RequestParam("pedidoId") Integer pedidoId, Model model, HttpServletRequest request) {
		Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow();
		model.addAttribute("pedido", pedido);
		
		return "restaurante-pedido";
	}
	
	@PostMapping(path = "/pedido/proximoStatus")
	public String proximoStatus(@RequestParam("pedidoId") Integer pedidoId, Model model) {
		Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow();
		pedido.definirProximoStatus();
		pedidoRepository.save(pedido);
		
		model.addAttribute("pedido", pedido);
		model.addAttribute("msg", "Status alterado com sucesso!");
		
		return "restaurante-pedido";
	}
	
	@GetMapping(path = "/comidas")
	public String viewComidas(Model model) {
		Restaurante restaurante = SecurityUtils.loggedRestaurante();
		List<ItemCardapio> itensCardapio = itemCardapioRepository.findByRestaurante_IdOrderByNome(restaurante.getId());
		
		model.addAttribute("itensCardapio", itensCardapio);
		
		return "restaurante-comidas";
	}
	
	@GetMapping(path = "/comidas/remover")
	public String removerComida(@RequestParam("itemId") Integer itemId, Model model) {
		itemCardapioRepository.deleteById(itemId);
		return "redirect:/restaurante/comidas";
	}
	
	@GetMapping(path = "/relatorio/pedidos")
	public String relatorioPedidos(RelatorioPedidoFilter filter, Model model) {
		
		Restaurante restaurante = SecurityUtils.loggedRestaurante();
		List<Pedido> pedidos = relatorioService.listPedidos(restaurante.getId(), filter);
		
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("filter", filter);
		
		return "restaurante-relatorio-pedidos";
	}
	
	@GetMapping(path = "/relatorio/itens")
	public String relatorioItenss(RelatorioItemFilter filter, Model model) {
		Restaurante restaurante = SecurityUtils.loggedRestaurante();
		
		List<ItemCardapio> itensCardapio = itemCardapioRepository.findByRestaurante_IdOrderByNome(restaurante.getId());
		model.addAttribute("itensCardapio", itensCardapio);
		model.addAttribute("filter", filter);
		
		List<RelatorioItemFaturamento> itensCalculados = relatorioService.calcularFaturamentoItens(restaurante.getId(), filter);
		model.addAttribute("itensCalculados", itensCalculados);
		
		return "restaurante-relatorio-itens";
	}
}