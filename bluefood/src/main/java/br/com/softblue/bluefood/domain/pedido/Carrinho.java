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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.softblue.bluefood.domain.restaurante.ItemCardapio;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class Carrinho implements Serializable {

	private List<ItemPedido> itens = new ArrayList<>();
	private Restaurante restaurante;
	
	public void adicionarItem(ItemCardapio itemCardapio, Integer quantidade, String observacoes) throws RestauranteDiferenteException {
		if (itens.size() == 0) {
			restaurante = itemCardapio.getRestaurante();
		
		} else if (!itemCardapio.getRestaurante().getId().equals(restaurante.getId())) {
			throw new RestauranteDiferenteException();
		}
		
		if (!exists(itemCardapio)) {	
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setItemCardapio(itemCardapio);
			itemPedido.setQuantidade(quantidade);
			itemPedido.setObservacoes(observacoes);
			itemPedido.setPreco(itemCardapio.getPreco());
			itens.add(itemPedido);
		}
	}
	
	public void adicionarItem(ItemPedido itemPedido) {
		try {
			adicionarItem(itemPedido.getItemCardapio(), itemPedido.getQuantidade(), itemPedido.getObservacoes());
		} catch (RestauranteDiferenteException e) {
		}
	}
	
	public void removerItem(ItemCardapio itemCardapio) {
		for (Iterator<ItemPedido> iterator = itens.iterator(); iterator.hasNext();) {
			ItemPedido itemPedido = iterator.next();
			if (itemPedido.getItemCardapio().getId().equals(itemCardapio.getId())) {
				iterator.remove();
				break;
			}
		}
		
		if (itens.size() == 0) {
			restaurante = null;
		}
	}
	
	private boolean exists(ItemCardapio itemCardapio) {
		for (ItemPedido itemPedido : itens) {
			if (itemPedido.getItemCardapio().getId().equals(itemCardapio.getId())) {
				return true;
			}
		}
		return false;
	}
	
	public BigDecimal getPrecoTotal(boolean adicionarTaxaEntrega) {
		BigDecimal soma = BigDecimal.ZERO;
		
		for (ItemPedido item : itens) {
			soma = soma.add(item.getPrecoCalculado());
		}
		
		if (adicionarTaxaEntrega) {
			soma = soma.add(restaurante.getTaxaEntrega());
		}
		
		return soma;
	}
	
	public void limpar() {
		itens.clear();
		restaurante = null;
	}
	
	public boolean vazio() {
		return itens.size() == 0;
	}
}
