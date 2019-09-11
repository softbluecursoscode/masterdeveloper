package br.com.softblue.bluefood.domain.pedido;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.softblue.bluefood.domain.restaurante.ItemCardapio;
import lombok.AllArgsConstructor;
import lombok.Data;

@SuppressWarnings("serial")
public class Carrinho implements Serializable {

	private List<Item> itens = new ArrayList<>();
	
	public void adicionarItem(ItemCardapio itemCardapio, Integer quantidade) {
		Item item = new Item(itemCardapio, quantidade);
		itens.add(item);
	}
	
	public BigDecimal calcularTotal() {
		BigDecimal soma = BigDecimal.ZERO;
		for (Item item : itens) {
			soma = soma.add(item.getItemCardapio().getPreco());
		}
		
		return soma;
	}
	
	@Data
	@AllArgsConstructor
	public static class Item {
		private ItemCardapio itemCardapio;
		private Integer quantidade;
	}
}
