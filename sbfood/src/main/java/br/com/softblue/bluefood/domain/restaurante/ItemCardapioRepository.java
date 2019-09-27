package br.com.softblue.bluefood.domain.restaurante;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Integer> {
	
	public List<ItemCardapio> findByRestaurante_IdOrderByNome(Integer restauranteId);
	
	public List<ItemCardapio> findByRestaurante_IdAndDestaque(Integer restauranteId, boolean destaque);
	
	public List<ItemCardapio> findByRestaurante_IdAndDestaqueAndCategoria(Integer restauranteId, boolean destaque, String categoria);
	
	@Query("SELECT DISTINCT ic.categoria FROM ItemCardapio ic ORDER BY ic.categoria")
	public List<String> findCategorias();
}
