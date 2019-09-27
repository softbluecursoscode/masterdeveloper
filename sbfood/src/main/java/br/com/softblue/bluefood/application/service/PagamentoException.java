package br.com.softblue.bluefood.application.service;

import br.com.softblue.bluefood.domain.pagamento.StatusPagamento;

@SuppressWarnings("serial")
public class PagamentoException extends Exception {

	public PagamentoException(StatusPagamento status) {
		super("O pagamento foi recusado com o status: " + status.getDescricao());
	}
	
	public PagamentoException(String message) {
		super(message);
	}
}
