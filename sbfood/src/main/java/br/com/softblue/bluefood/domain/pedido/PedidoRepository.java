package br.com.softblue.bluefood.domain.pedido;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {
	
	@Query("SELECT p FROM Pedido p WHERE p.cliente.id = ?1")
	public Collection<Pedido> listPedidosByCliente(Integer clienteId);
}
