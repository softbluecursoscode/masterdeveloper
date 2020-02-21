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
package br.com.softblue.bluefood.application.test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.cliente.ClienteRepository;
import br.com.softblue.bluefood.domain.pedido.Pedido;
import br.com.softblue.bluefood.domain.pedido.PedidoRepository;
import br.com.softblue.bluefood.domain.pedido.Pedido.Status;
import br.com.softblue.bluefood.domain.restaurante.CategoriaRestaurante;
import br.com.softblue.bluefood.domain.restaurante.CategoriaRestauranteRepository;
import br.com.softblue.bluefood.domain.restaurante.ItemCardapio;
import br.com.softblue.bluefood.domain.restaurante.ItemCardapioRepository;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import br.com.softblue.bluefood.domain.restaurante.RestauranteRepository;
import br.com.softblue.bluefood.util.StringUtils;

@Component
public class InsertDataForTesting {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CategoriaRestauranteRepository categoriaRestauranteRepository;
	
	@Autowired
	private ItemCardapioRepository itemCardapioRepository;
	
	@Autowired
	private PedidoRepository pedidoRespository;

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Cliente[] clientes = clientes();
		Restaurante[] restaurantes = restaurantes();
		itensCardapio(restaurantes);
		
		Pedido p = new Pedido();
		p.setData(LocalDateTime.now());
		p.setCliente(clientes[0]);
		p.setRestaurante(restaurantes[0]);
		p.setStatus(Status.Producao);
		p.setSubtotal(BigDecimal.valueOf(10));
		p.setTaxaEntrega(BigDecimal.valueOf(2));
		p.setTotal(BigDecimal.valueOf(12.0));
		pedidoRespository.save(p);
	}
	
	private Restaurante[] restaurantes() {
		List<Restaurante> restaurantes = new ArrayList<>(); 
		
		CategoriaRestaurante categoriaPizza = categoriaRestauranteRepository.findById(1).orElseThrow(NoSuchElementException::new);
		CategoriaRestaurante categoriaSanduiche = categoriaRestauranteRepository.findById(2).orElseThrow(NoSuchElementException::new);
		CategoriaRestaurante categoriaSobremesa = categoriaRestauranteRepository.findById(5).orElseThrow(NoSuchElementException::new);
		CategoriaRestaurante categoriaJapones = categoriaRestauranteRepository.findById(6).orElseThrow(NoSuchElementException::new);
		
		Restaurante r = new Restaurante();
		r.setNome("Bubger King");
		r.setEmail("r1@bluefood.com.br");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("00000000000101");
		r.setTaxaEntrega(BigDecimal.valueOf(3.2));
		r.setTelefone("99876671010");
		r.getCategorias().add(categoriaSanduiche);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0001-logo.png");
		r.setTempoEntregaBase(30);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		r = new Restaurante();
		r.setNome("Mc Naldo's");
		r.setEmail("r2@bluefood.com.br");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("00000000000102");
		r.setTaxaEntrega(BigDecimal.valueOf(4.5));
		r.setTelefone("99876671011");
		r.getCategorias().add(categoriaSanduiche);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0002-logo.png");
		r.setTempoEntregaBase(25);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		r = new Restaurante();
		r.setNome("Sbubby");
		r.setEmail("r3@bluefood.com.br");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("00000000000103");
		r.setTaxaEntrega(BigDecimal.valueOf(12.2));
		r.setTelefone("99876671012");
		r.getCategorias().add(categoriaSanduiche);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0003-logo.png");
		r.setTempoEntregaBase(38);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		r = new Restaurante();
		r.setNome("Pizza Brut");
		r.setEmail("r4@bluefood.com.br");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("00000000000104");
		r.setTaxaEntrega(BigDecimal.valueOf(9.8));
		r.setTelefone("99876671013");
		r.getCategorias().add(categoriaPizza);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0004-logo.png");
		r.setTempoEntregaBase(22);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		r = new Restaurante();
		r.setNome("Wiki Japa");
		r.setEmail("r5@bluefood.com.br");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("00000000000105");
		r.setTaxaEntrega(BigDecimal.valueOf(14.9));
		r.setTelefone("99876671014");
		r.getCategorias().add(categoriaJapones);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0005-logo.png");
		r.setTempoEntregaBase(19);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		Restaurante[] array = new Restaurante[restaurantes.size()]; 
		return restaurantes.toArray(array);
	}
	
	private Cliente[] clientes() {
		List<Cliente> clientes = new ArrayList<>(); 
		
		Cliente c = new Cliente();
		c.setNome("João Silva");
		c.setEmail("joao@bluefood.com.br");
		c.setSenha(StringUtils.encrypt("c"));
		c.setCep("89300100");
		c.setCpf("03099887666");
		c.setTelefone("99355430001");
		clientes.add(c);
		clienteRepository.save(c);
		
		c = new Cliente();
		c.setNome("Maria Torres");
		c.setEmail("maria@bluefood.com.br");
		c.setSenha(StringUtils.encrypt("c"));
		c.setCep("89300101");
		c.setCpf("03099887677");
		c.setTelefone("99355430002");
		clientes.add(c);
		clienteRepository.save(c);
		
		Cliente[] array = new Cliente[clientes.size()]; 
		return clientes.toArray(array);
	}
	
	private void itensCardapio(Restaurante[] restaurantes) {
		ItemCardapio ic = new ItemCardapio();
		ic.setCategoria("Sanduíche");
		ic.setDescricao("Delicioso sanduíche com molho");
		ic.setNome("Double Cheese Burger Special");
		ic.setPreco(BigDecimal.valueOf(23.8));
		ic.setRestaurante(restaurantes[0]);
		ic.setDestaque(true);
		ic.setImagem("0001-comida.png");
		itemCardapioRepository.save(ic);
		
		ic = new ItemCardapio();
		ic.setCategoria("Sanduíche");
		ic.setDescricao("Sanduíche padrão que mata a fome");
		ic.setNome("Cheese Burger Simples");
		ic.setPreco(BigDecimal.valueOf(17.8));
		ic.setRestaurante(restaurantes[0]);
		ic.setDestaque(false);
		ic.setImagem("0006-comida.png");
		itemCardapioRepository.save(ic);
		
		ic = new ItemCardapio();
		ic.setCategoria("Sanduíche");
		ic.setDescricao("Sanduíche natural com peito de peru");
		ic.setNome("Sanduíche Natural da Casa");
		ic.setPreco(BigDecimal.valueOf(11.8));
		ic.setRestaurante(restaurantes[0]);
		ic.setDestaque(false);
		ic.setImagem("0007-comida.png");
		itemCardapioRepository.save(ic);
		
		ic = new ItemCardapio();
		ic.setCategoria("Bebida");
		ic.setDescricao("Refrigerante com gás");
		ic.setNome("Refrigerante Tradicional");
		ic.setPreco(BigDecimal.valueOf(9));
		ic.setRestaurante(restaurantes[0]);
		ic.setDestaque(false);
		ic.setImagem("0004-comida.png");
		itemCardapioRepository.save(ic);
		
		ic = new ItemCardapio();
		ic.setCategoria("Bebida");
		ic.setDescricao("Suco natural e docinho");
		ic.setNome("Suco de Laranja");
		ic.setPreco(BigDecimal.valueOf(9));
		ic.setRestaurante(restaurantes[0]);
		ic.setDestaque(false);
		ic.setImagem("0005-comida.png");
		itemCardapioRepository.save(ic);
		
		ic = new ItemCardapio();
		ic.setCategoria("Pizza");
		ic.setDescricao("Pizza saborosa com cebola");
		ic.setNome("Pizza de Calabresa");
		ic.setPreco(BigDecimal.valueOf(38.9));
		ic.setRestaurante(restaurantes[3]);
		ic.setDestaque(false);
		ic.setImagem("0002-comida.png");
		itemCardapioRepository.save(ic);
		
		ic = new ItemCardapio();
		ic.setCategoria("Japonesa");
		ic.setDescricao("Delicioso Uramaki tradicional");
		ic.setNome("Uramaki");
		ic.setPreco(BigDecimal.valueOf(16.8));
		ic.setRestaurante(restaurantes[4]);
		ic.setDestaque(false);
		ic.setImagem("0003-comida.png");
		itemCardapioRepository.save(ic);
	}
}
