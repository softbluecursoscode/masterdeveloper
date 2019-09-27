package br.com.softblue.bluefood.domain.cliente;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "cliente")
public class Cliente extends Usuario implements Serializable {

	@NotBlank(message = "O CPF não pode ser vazio")
	@Column(length = 11)
	@Pattern(regexp = "[0-9]{10,11}", message = "O CPF possui formato inválido")
	private String cpf;
	
	@NotBlank(message = "O CEP não pode ser vazio")
	@Column(length = 8)
	@Pattern(regexp = "[0-9]{8}", message = "O CEP possui formato inválido")
	private String cep;
	
	public String getFormattedCep() {
		return cep.substring(0, 5) + "-" + cep.substring(5);
	}
}
