package br.com.softblue.bluefood.application.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.softblue.bluefood.application.ValidationException;
import br.com.softblue.bluefood.domain.cliente.ClienteRepository;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import br.com.softblue.bluefood.domain.restaurante.RestauranteComparator;
import br.com.softblue.bluefood.domain.restaurante.RestauranteRepository;
import br.com.softblue.bluefood.domain.restaurante.SearchFilter;
import br.com.softblue.bluefood.domain.restaurante.SearchFilter.SearchType;
import br.com.softblue.bluefood.util.IOUtils;
import br.com.softblue.bluefood.util.SecurityUtils;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Value("${bluefood.upload.logotipo}")
	private String uploadDir;
	
	@Transactional
	public void saveRestaurante(Restaurante restaurante) throws ValidationException {
		if (clienteRepository.findByEmail(restaurante.getEmail()) != null ||
				restauranteRepository.findByEmail(restaurante.getEmail()) != null) {
			throw new ValidationException("Este e-mail já está cadastrado");
		}
		
		restaurante = restauranteRepository.save(restaurante);
		restaurante.setLogotipoFileName();
		restaurante.encryptSenha();
		
		MultipartFile file = restaurante.getLogotipoFile();
		
		try {
			IOUtils.copy(file.getInputStream(), restaurante.getLogotipo(), uploadDir);
		} catch (IOException e) {
			throw new ApplicationServiceException(e);
		}
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
		
		RestauranteComparator comparator = new RestauranteComparator(filter, SecurityUtils.loggedClienteCep());
		restaurantes.sort(comparator);
		return restaurantes;
	}
}
