package br.com.softblue.bluefood.domain.pedido;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RelatorioItemFaturamento {

	private String nome;
	private Long quantidade;
	private BigDecimal valor;
}
