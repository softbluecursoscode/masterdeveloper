package br.com.softblue.bluefood.infrastructure.web.controller;

import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import br.com.softblue.bluefood.domain.restaurante.CategoriaRestauranteRepository;

public class ControllerHelper {

	public static void setEditMode(Model model, boolean isEdit) {
		model.addAttribute("editMode", isEdit);
	}
	
	public static void addCategoriasToRequest(CategoriaRestauranteRepository repository, Model model) {
		model.addAttribute("categorias", repository.findAll(new Sort(Sort.Direction.ASC, "nome")));
	}
}
