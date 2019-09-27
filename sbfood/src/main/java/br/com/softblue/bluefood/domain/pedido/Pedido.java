package br.com.softblue.bluefood.domain.pedido;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {
	
	public enum Status {
		Producao(1, "Em produção", false),
		Entrega(2, "Saiu para entrega", false),
		Concluido(3, "Concluído", true);
		
		int ordem;
		String descricao;
		boolean ultimo;
		
		Status(int ordem, String descricao, boolean ultimo) {
			this.ordem = ordem;
			this.descricao = descricao;
			this.ultimo = ultimo;
		}
		
		public int getOrdem() {
			return ordem;
		}
		
		public String getDescricao() {
			return descricao;
		}
		
		public boolean isUltimo() {
			return ultimo;
		}
		
		public static Status fromOrdem(int ordem) {
			for (Status status : Status.values()) {
				if (status.getOrdem() == ordem) {
					return status;
				}
			}
			return null;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@NotNull
	private Status status;
	
	@NotNull
	private LocalDateTime data;
	
	@NotNull
	@ManyToOne
	private Cliente cliente;
	
	@NotNull
	@ManyToOne
	private Restaurante restaurante;

	@NotNull
	private BigDecimal subtotal;
	
	@NotNull
	@Column(name = "taxa_entrega")
	private BigDecimal taxaEntrega;
	
	@NotNull
	private BigDecimal total;
	
	@OneToMany(mappedBy = "id.pedido", fetch = FetchType.EAGER)
	private Set<ItemPedido> itens = new LinkedHashSet<>();
	
	
	public String getFormattedId() {
		return String.format("#%04d", id);
	}
	
	public void definirProximoStatus() {
		int ordem = status.getOrdem();
		Status newStatus = Status.fromOrdem(ordem + 1);
		
		if (newStatus != null) {
			this.status = newStatus;
		}
	}
}
