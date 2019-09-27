package br.com.softblue.bluefood.application.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.softblue.bluefood.application.ValidationException;
import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.cliente.ClienteRepository;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import br.com.softblue.bluefood.domain.restaurante.RestauranteComparator;
import br.com.softblue.bluefood.domain.restaurante.RestauranteRepository;
import br.com.softblue.bluefood.domain.restaurante.SearchFilter;
import br.com.softblue.bluefood.domain.restaurante.SearchFilter.SearchType;
import br.com.softblue.bluefood.util.SecurityUtils;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ImageController imageController;

	@Transactional
	public void saveRestaurante(Restaurante restaurante) throws ValidationException {
		if (!validateEmail(restaurante.getEmail(), restaurante.getId())) {
			throw new ValidationException("Este e-mail já está cadastrado");
		}
		
		if (restaurante.getId() != null) {
			Restaurante restauranteDB = restauranteRepository.findById(restaurante.getId()).orElseThrow();
			restaurante.setSenha(restauranteDB.getSenha());
		
		} else {
			restaurante.encryptSenha();
			restaurante.setLogotipoFileName();
			imageController.uploadLogotipo(restaurante.getLogotipoFile(), restaurante.getLogotipo());
		}
		
		restaurante = restauranteRepository.save(restaurante);
	}
	
	public List<Restaurante> search(SearchFilter filter) {
		List<Restaurante> restaurantes = new ArrayList<>();
		
		if (filter.getSearchType() == SearchType.TEXTO) {
			if (!StringUtils.isEmpty(filter.getTexto())) {
				restaurantes = restauranteRepository.findByNomeIgnoreCaseContaining(filter.getTexto());	
			}
			
		} else if (filter.getSearchType() == SearchType.CATEGORIA) {
			restaurantes = restauranteRepository.findByCategorias_Id(filter.getCategoriaId());
		
		} else {
			throw new IllegalStateException("O tipo de busca '" + filter.getSearchType() + "' não é suportado");
		}
		
		Iterator<Restaurante> it = restaurantes.iterator();
		
		while (it.hasNext()) {
			Restaurante restaurante = it.next();
			double taxaEntrega = restaurante.getTaxaEntrega().doubleValue();
			
			if (filter.isEntregaGratis() && taxaEntrega > 0
					|| !filter.isEntregaGratis() && taxaEntrega == 0 ) {
				it.remove();
			}
		}
		
		RestauranteComparator comparator = new RestauranteComparator(filter, SecurityUtils.loggedCliente().getCep());
		restaurantes.sort(comparator);
		return restaurantes;
	}
	
	private boolean validateEmail(String email, Integer id) {
		Cliente cliente = clienteRepository.findByEmail(email);
		
		if (cliente != null) {
			return false;
		}
		
		Restaurante restaurante = restauranteRepository.findByEmail(email);
		
		if (restaurante != null && id != null && restaurante.getId() != id) {
			return false;
		}
		
		return true;
	}
}
