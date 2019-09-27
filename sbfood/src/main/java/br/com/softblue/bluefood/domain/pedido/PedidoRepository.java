package br.com.softblue.bluefood.domain.pedido;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {
	
	@Query("SELECT p FROM Pedido p WHERE p.cliente.id = ?1")
	public Collection<Pedido> listPedidosByCliente(Integer clienteId);
	
	public List<Pedido> findByRestaurante_Id(Integer restauranteId);
	
	public Pedido findByIdAndRestaurante_Id(Integer pedidoId, Integer restauranteId);
	
	@Query("SELECT p FROM Pedido p WHERE p.restaurante.id = ?1 AND p.data BETWEEN ?2 AND ?3")
	public List<Pedido> findByDateInterval(Integer restauranteId, LocalDateTime dataInicial, LocalDateTime dataFinal);
	
	@Query("SELECT i.itemCardapio.nome, count(i.itemCardapio.id), sum(i.preco) FROM Pedido p INNER JOIN p.itens i WHERE p.restaurante.id = ?1 AND p.data BETWEEN ?2 AND ?3 GROUP BY i.id")
	public List<Object[]> findItensForFaturamamento(Integer restauranteId, LocalDateTime dataInicial, LocalDateTime dataFinal);
	
	@Query("SELECT i.itemCardapio.nome, count(i.itemCardapio.id), sum(i.preco) FROM Pedido p INNER JOIN p.itens i WHERE p.restaurante.id = ?1 AND i.itemCardapio.id = ?2 GROUP BY i.id")
	public List<Object[]> findItensForFaturamamento(Integer restauranteId, Integer itemCardapioId);
}
