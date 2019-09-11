package br.com.softblue.bluefood.application;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.cliente.ClienteRepository;
import br.com.softblue.bluefood.domain.pedido.ItemPedido;
import br.com.softblue.bluefood.domain.pedido.ItemPedidoPK;
import br.com.softblue.bluefood.domain.pedido.ItemPedidoRepository;
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
public class StartupApplicationListener {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CategoriaRestauranteRepository categoriaRestauranteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemCardapioRepository itemCardapioRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		CategoriaRestaurante categoriaRestaurante = categoriaRestauranteRepository.findById(2).orElse(null);
		
		Restaurante r1 = new Restaurante();
		r1.setNome("Bubger King");
		r1.setEmail("r1@softblue.com.br");
		r1.setSenha(StringUtils.encrypt("a"));
		r1.setCnpj("00000000000101");
		r1.setTaxaEntrega(BigDecimal.valueOf(3.2));
		r1.setTelefone("41996159554");
		r1.getCategorias().add(categoriaRestaurante);
		r1.setLogotipo("0001-logo.png");
		r1.setTempoEntregaBase(30);
		restauranteRepository.save(r1);
		
		Restaurante r2 = new Restaurante();
		r2.setNome("Mc Naldo's");
		r2.setEmail("r2@softblue.com.br");
		r2.setSenha(StringUtils.encrypt("a"));
		r2.setCnpj("00000000000102");
		r2.setTaxaEntrega(BigDecimal.valueOf(4.5));
		r2.setTelefone("41996159554");
		r2.getCategorias().add(categoriaRestaurante);
		r2.setLogotipo("0002-logo.png");
		r2.setTempoEntregaBase(25);
		restauranteRepository.save(r2);
		
		Restaurante r3 = new Restaurante();
		r3.setNome("Sbubby");
		r3.setEmail("r3@softblue.com.br");
		r3.setSenha(StringUtils.encrypt("a"));
		r3.setCnpj("00000000000102");
		r3.setTaxaEntrega(BigDecimal.valueOf(12.2));
		r3.setTelefone("41996159554");
		r3.getCategorias().add(categoriaRestaurante);
		r3.setLogotipo("0003-logo.png");
		r3.setTempoEntregaBase(38);
		restauranteRepository.save(r3);
		
		Cliente c1 = new Cliente();
		c1.setNome("Carlos Tosin");
		c1.setEmail("carlos2@softblue.com.br");
		c1.setSenha(StringUtils.encrypt("c"));
		c1.setCep("80250130");
		c1.setCpf("03292445906");
		c1.setTelefone("41999793037");
		clienteRepository.save(c1);
		
		ItemCardapio ic1 = new ItemCardapio();
		ic1.setCategoria("Sanduíche");
		ic1.setDescricao("Delicioso sanduíche com molho");
		ic1.setNome("Cheese Burger");
		ic1.setPreco(BigDecimal.valueOf(23.8));
		ic1.setRestaurante(r1);
		ic1.setDestaque(true);
		ic1.setImagem("0001-comida.png");
		itemCardapioRepository.save(ic1);
		
		ItemCardapio ic2 = new ItemCardapio();
		ic2.setCategoria("Pizza");
		ic2.setDescricao("Pizza saborosa com cebola");
		ic2.setNome("Pizza de Calabresa");
		ic2.setPreco(BigDecimal.valueOf(38.9));
		ic2.setRestaurante(r1);
		ic2.setDestaque(false);
		ic2.setImagem("0002-comida.png");
		itemCardapioRepository.save(ic2);
		
		Pedido p1 = new Pedido();
		p1.setCliente(c1);
		p1.setRestaurante(r1);
		p1.setData(LocalDateTime.now());
		p1.setStatus(Status.Concluido);
		p1.setSubtotal(BigDecimal.valueOf(100));
		p1.setTaxaEntrega(BigDecimal.ONE);
		p1.setTotal(BigDecimal.valueOf(101));
		pedidoRepository.save(p1);
		
		ItemPedido item1 = new ItemPedido();
		item1.setId(new ItemPedidoPK(p1, 1));
		item1.setObservacoes("Obs 1");
		item1.setQuantidade(2);
		item1.setItemCardapio(ic1);
		itemPedidoRepository.save(item1);

		Pedido p2 = new Pedido();
		p2.setCliente(c1);
		p2.setRestaurante(r1);
		p2.setData(LocalDateTime.now());
		p2.setStatus(Status.Concluido);
		p2.setSubtotal(BigDecimal.valueOf(100));
		p2.setTaxaEntrega(BigDecimal.ONE);
		p2.setTotal(BigDecimal.valueOf(101));
		pedidoRepository.save(p2);
	}
}
