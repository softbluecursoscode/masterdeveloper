package br.com.softblue.bluefood.application.service;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.com.softblue.bluefood.domain.pagamento.DadosCartao;
import br.com.softblue.bluefood.domain.pagamento.Pagamento;
import br.com.softblue.bluefood.domain.pagamento.PagamentoRepository;
import br.com.softblue.bluefood.domain.pagamento.StatusPagamento;
import br.com.softblue.bluefood.domain.pedido.Carrinho;
import br.com.softblue.bluefood.domain.pedido.ItemPedido;
import br.com.softblue.bluefood.domain.pedido.ItemPedidoPK;
import br.com.softblue.bluefood.domain.pedido.ItemPedidoRepository;
import br.com.softblue.bluefood.domain.pedido.Pedido;
import br.com.softblue.bluefood.domain.pedido.Pedido.Status;
import br.com.softblue.bluefood.domain.pedido.PedidoRepository;
import br.com.softblue.bluefood.util.SecurityUtils;

@Service
public class PedidoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository ItemPedidoRepository;
	
	@Value("${bluefood.sbpay.url}")
	private String sbpayUrl;
	
	@Value("${bluefood.sbpay.token}")
	private String token;
	
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = PagamentoException.class)
	public Pedido criarEPagar(Carrinho carrinho, String numCartao) throws PagamentoException {
		Pedido pedido = new Pedido();
		pedido.setData(LocalDateTime.now());
		pedido.setCliente(SecurityUtils.loggedCliente());
		pedido.setRestaurante(carrinho.getRestaurante());
		pedido.setStatus(Status.Producao);
		pedido.setTaxaEntrega(carrinho.getRestaurante().getTaxaEntrega());
		pedido.setSubtotal(carrinho.getPrecoTotal(false));
		pedido.setTotal(carrinho.getPrecoTotal(true));
		pedido = pedidoRepository.save(pedido);
		
		int ordem = 1;
		
		for (ItemPedido itemPedido : carrinho.getItens()) {
			itemPedido.setId(new ItemPedidoPK(pedido, ordem++));
			ItemPedidoRepository.save(itemPedido);
		}
		
		DadosCartao dadosCartao = new DadosCartao();
		dadosCartao.setNumCartao(numCartao);
		
		RestTemplate restTemplate = new RestTemplate();
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Token", token);
		
		HttpEntity<DadosCartao> requestEntity = new HttpEntity<DadosCartao>(dadosCartao, headers);
		
		try {
			Map<String, String> response = restTemplate.postForObject(sbpayUrl, requestEntity, Map.class);
			
			StatusPagamento statusPagamento = StatusPagamento.valueOf(response.get("status"));
			
			if (statusPagamento != StatusPagamento.Autorizado) {
				throw new PagamentoException(statusPagamento.getDescricao());
			}
	
			Pagamento pagamento = new Pagamento();
			pagamento.setData(LocalDateTime.now());
			pagamento.setPedido(pedido);
			pagamento.definirNumeroEBandeira(numCartao);
			pagamentoRepository.save(pagamento);
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new PagamentoException("Erro no servidor de pagamento");
		}
		
		return pedido;
	}
}
