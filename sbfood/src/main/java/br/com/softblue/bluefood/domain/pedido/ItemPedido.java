package br.com.softblue.bluefood.domain.pedido;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.softblue.bluefood.domain.restaurante.ItemCardapio;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "item_pedido")
public class ItemPedido implements Serializable {
	
	@EmbeddedId
	@EqualsAndHashCode.Include
	private ItemPedidoPK id;
	
	@ManyToOne
	@NotNull
	private ItemCardapio itemCardapio;
	
	@NotNull
	private Integer quantidade;
	
	@Size(max = 50)
	private String observacoes;

}
