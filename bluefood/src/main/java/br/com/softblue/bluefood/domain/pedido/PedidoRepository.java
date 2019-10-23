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
package br.com.softblue.bluefood.domain.pedido;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	@Query("SELECT p FROM Pedido p WHERE p.cliente.id = ?1 ORDER BY p.data DESC")
	public List<Pedido> listPedidosByCliente(Integer clienteId);
	
	public List<Pedido> findByRestaurante_IdOrderByDataDesc(Integer restauranteId);
	
	public Pedido findByIdAndRestaurante_Id(Integer pedidoId, Integer restauranteId);
	
	@Query("SELECT p FROM Pedido p WHERE p.restaurante.id = ?1 AND p.data BETWEEN ?2 AND ?3")
	public List<Pedido> findByDateInterval(Integer restauranteId, LocalDateTime dataInicial, LocalDateTime dataFinal);
	
	@Query("SELECT i.itemCardapio.nome, COUNT(i.itemCardapio.id), SUM(i.preco) FROM Pedido p INNER JOIN p.itens i "
			+ "WHERE p.restaurante.id = ?1 AND i.itemCardapio.id = ?2 AND p.data BETWEEN ?3 AND ?4 GROUP BY i.itemCardapio.nome")
	public List<Object[]> findItensForFaturamento(Integer restauranteId, Integer itemCardapioId, LocalDateTime dataInicial, LocalDateTime dataFinal);
	
	@Query("SELECT i.itemCardapio.nome, SUM(i.quantidade), SUM(i.preco * i.quantidade) FROM Pedido p INNER JOIN p.itens i "
			+ "WHERE p.restaurante.id = ?1 AND p.data BETWEEN ?2 AND ?3 GROUP BY i.itemCardapio.nome")
	public List<Object[]> findItensForFaturamento(Integer restauranteId, LocalDateTime dataInicial, LocalDateTime dataFinal);
}
