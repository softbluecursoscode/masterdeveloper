package br.com.softblue.bluwitter.domain.mensagem;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.softblue.bluwitter.domain.usuario.Usuario;

@Entity
public class Mensagem {

	@Id
	@GeneratedValue
	private Integer id;
	
	private LocalDateTime dataHora;
	
	private String texto;
	
	private Integer curtidas = 0;
	
	@ManyToOne
	private Usuario usuario;
	
	public void curtir() {
		curtidas++;
	}
	
	public long calcularTempoMinutos(LocalDateTime ref) {
		return ChronoUnit.MINUTES.between(dataHora, ref);
	}

	public Integer getId() {
		return id;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public Integer getCurtidas() {
		return curtidas;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
