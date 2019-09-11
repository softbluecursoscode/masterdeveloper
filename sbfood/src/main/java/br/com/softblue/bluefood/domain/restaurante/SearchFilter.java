package br.com.softblue.bluefood.domain.restaurante;

import org.springframework.util.StringUtils;

import lombok.Data;

@Data
public class SearchFilter {
	
	public enum Order {
		TAXA, TEMPO;
	}
	
	public enum Command {
		entregaGratis, maiorTaxa, menorTaxa, maiorTempo, menorTempo;
	}
	
	public enum SearchType {
		TEXTO, CATEGORIA
	}
	
	private String texto;
	private Integer categoriaId;
	private boolean entregaGratis;
	private boolean asc;
	private Order order = Order.TAXA;
	private SearchType searchType;
	
	public void processFilter(String cmdString) {
		if (!StringUtils.isEmpty(cmdString)) {
			Command cmd = Command.valueOf(cmdString);
			
			if (cmd == Command.entregaGratis) {
				entregaGratis = !entregaGratis;
			
			} else if (cmd == Command.maiorTaxa) {
				order = Order.TAXA;
				asc = false;
			
			} else if (cmd == Command.menorTaxa) {
				order = Order.TAXA;
				asc = true;
			
			} else if (cmd == Command.maiorTempo) {
				order = Order.TEMPO;
				asc = false;
			
			} else if (cmd == Command.menorTempo) {
				order = Order.TEMPO;
				asc = true;
			}
		}
		
		if (searchType == SearchType.TEXTO) {
			categoriaId = null;
		
		} else if (searchType == SearchType.CATEGORIA) {
			texto = null;
		}
	}
}
