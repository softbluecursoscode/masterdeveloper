/*******************************************************************************
 * MIT License
 *
 * Copyright (c) 2019 Softblue
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
package br.com.softblue.bluefood.infrastructure.web.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.softblue.bluefood.application.service.RelatorioService;
import br.com.softblue.bluefood.application.service.RestauranteService;
import br.com.softblue.bluefood.application.service.ValidationException;
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
	private RestauranteService restauranteService;
	
	@Autowired
	private RelatorioService relatorioService;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CategoriaRestauranteRepository categoriaRestauranteRepository;
	
	@Autowired
	private ItemCardapioRepository itemCardapioRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	

	@GetMapping(path = "/home")
	public String home(Model model) {
		Integer restauranteId = SecurityUtils.loggedRestaurante().getId();
		List<Pedido> pedidos = pedidoRepository.findByRestaurante_IdOrderByDataDesc(restauranteId);
		model.addAttribute("pedidos", pedidos);
		
		return "restaurante-home";
	}
	
	@GetMapping("/edit")
	public String edit(Model model) {
		Integer restauranteId = SecurityUtils.loggedRestaurante().getId();
		Restaurante restaurante = restauranteRepository.findById(restauranteId).orElseThrow(NoSuchElementException::new);
		model.addAttribute("restaurante", restaurante);
		
		ControllerHelper.setEditMode(model, true);
		ControllerHelper.addCategoriasToRequest(categoriaRestauranteRepository, model);
		
		return "restaurante-cadastro";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("restaurante") @Valid Restaurante restaurante,
			Errors errors,
			Model model) {
		
		if (!errors.hasErrors()) {
			try {
				restauranteService.saveRestaurante(restaurante);
				model.addAttribute("msg", "Restaurante gravado com sucesso!");
			
			} catch (ValidationException e) {
				errors.rejectValue("email", null, e.getMessage());
			}
		}
		
		ControllerHelper.setEditMode(model, true);
		ControllerHelper.addCategoriasToRequest(categoriaRestauranteRepository, model);
		
		return "restaurante-cadastro";
	}
	
	@GetMapping(path = "/comidas")
	public String viewComidas(Model model) {
		Integer restauranteId = SecurityUtils.loggedRestaurante().getId();
		Restaurante restaurante = restauranteRepository.findById(restauranteId).orElseThrow(NoSuchElementException::new);
		model.addAttribute("restaurante", restaurante);
		
		List<ItemCardapio> itensCardapio = itemCardapioRepository.findByRestaurante_IdOrderByNome(restauranteId);
		model.addAttribute("itensCardapio", itensCardapio);
		
		model.addAttribute("itemCardapio", new ItemCardapio());
		
		return "restaurante-comidas";
	}
	
	@GetMapping(path = "/comidas/remover")
	public String remover(@RequestParam("itemId") Integer itemId, Model model) {
		itemCardapioRepository.deleteById(itemId);
		return "redirect:/restaurante/comidas";
	}
	
	@PostMapping(path = "/comidas/cadastrar")
	public String cadastrar(
			@Valid @ModelAttribute("itemCardapio") ItemCardapio itemCardapio,
			Errors errors,
			Model model) {
		
		if (errors.hasErrors()) {
			Integer restauranteId = SecurityUtils.loggedRestaurante().getId();
			Restaurante restaurante = restauranteRepository.findById(restauranteId).orElseThrow(NoSuchElementException::new);
			model.addAttribute("restaurante", restaurante);
			
			List<ItemCardapio> itensCardapio = itemCardapioRepository.findByRestaurante_IdOrderByNome(restauranteId);
			model.addAttribute("itensCardapio", itensCardapio);
			
			return "restaurante-comidas";
		}
		
		restauranteService.saveItemItemCardapio(itemCardapio);
		return "redirect:/restaurante/comidas";
	}
	
	@GetMapping(path = "/pedido")
	public String viewPedido(
			@RequestParam("pedidoId") Integer pedidoId,
			Model model
			) {
		
		Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(NoSuchElementException::new);
		model.addAttribute("pedido", pedido);
		
		return "restaurante-pedido";
	}
	
	@PostMapping(path = "/pedido/proximoStatus")
	public String proximoStatus(@RequestParam("pedidoId") Integer pedidoId, Model model) {
		
		Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(NoSuchElementException::new);
		pedido.definirProximoStatus();
		pedidoRepository.save(pedido);
		
		model.addAttribute("pedido", pedido);
		model.addAttribute("msg", "Status alterado com sucesso");
		
		return "restaurante-pedido";
	}
	
	@GetMapping("/relatorio/pedidos")
	public String relatorioPedidos(
			@ModelAttribute("relatorioPedidoFilter") RelatorioPedidoFilter filter,
			Model model) {
		
		Integer restauranteId = SecurityUtils.loggedRestaurante().getId();
		List<Pedido> pedidos = relatorioService.listPedidos(restauranteId, filter);
		model.addAttribute("pedidos", pedidos);
		
		model.addAttribute("filter", filter);
		
		return "restaurante-relatorio-pedidos";
	}
	
	@GetMapping("/relatorio/itens")
	public String relatorioItens(
			@ModelAttribute("relatorioItemFilter") RelatorioItemFilter filter,
			Model model) {
		Integer restauranteId = SecurityUtils.loggedRestaurante().getId();
		
		List<ItemCardapio> itensCardapio = itemCardapioRepository.findByRestaurante_IdOrderByNome(restauranteId);
		model.addAttribute("itensCardapio", itensCardapio);
		
		List<RelatorioItemFaturamento> itensCalculados = relatorioService.calcularFaturamentoItens(restauranteId, filter);
		model.addAttribute("itensCalculados", itensCalculados);
		
		model.addAttribute("relatorioItemFilter", filter);
		
		return "restaurante-relatorio-itens";
	}
}
