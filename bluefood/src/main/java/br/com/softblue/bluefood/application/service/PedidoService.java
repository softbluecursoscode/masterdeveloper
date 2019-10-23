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
import br.com.softblue.bluefood.domain.pedido.PedidoRepository;
import br.com.softblue.bluefood.domain.pedido.Pedido.Status;
import br.com.softblue.bluefood.util.SecurityUtils;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Value("${bluefood.sbpay.url}")
	private String sbPayUrl;
	
	@Value("${bluefood.sbpay.token}")
	private String sbPayToken;

	
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
			itemPedidoRepository.save(itemPedido);
		}
		
		DadosCartao dadosCartao = new DadosCartao();
		dadosCartao.setNumCartao(numCartao);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Token", sbPayToken);
		
		HttpEntity<DadosCartao> requestEntity = new HttpEntity<>(dadosCartao, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		Map<String, String> response;
		try {
			response = restTemplate.postForObject(sbPayUrl, requestEntity, Map.class);
		} catch (Exception e) {
			throw new PagamentoException("Erro no servidor de pagamento");
		}
		
		StatusPagamento statusPagamento = StatusPagamento.valueOf(response.get("status"));
		
		if (statusPagamento != StatusPagamento.Autorizado) {
			throw new PagamentoException(statusPagamento.getDescricao());
		}
		
		Pagamento pagamento = new Pagamento();
		pagamento.setData(LocalDateTime.now());
		pagamento.setPedido(pedido);
		pagamento.definirNumeroEBandeira(numCartao);
		pagamentoRepository.save(pagamento);
		
		return pedido;
	}
}
