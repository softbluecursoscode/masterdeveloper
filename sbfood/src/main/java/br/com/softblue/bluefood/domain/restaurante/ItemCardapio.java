package br.com.softblue.bluefood.domain.restaurante;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import br.com.softblue.bluefood.infrastructure.web.validator.UploadConstraint;
import br.com.softblue.bluefood.util.FileType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "item_cardapio")
public class ItemCardapio implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@NotBlank(message = "O nome não pode ser vazio")
	@Size(max = 50)
	private String nome;
	
	@NotBlank(message = "A categoria não pode ser vazia")
	@Size(max = 25)
	private String categoria;
	
	@NotBlank(message = "A descrição não pode ser vazio")
	@Size(max = 80)
	private String descricao;
	
	@Size(max = 80)
	private String imagem;
	
	@NotNull(message = "O preço não pode ser vazio")
	@Min(0)
	private BigDecimal preco;
	
	@NotNull
	private Boolean destaque;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "restaurante_id")
	private Restaurante restaurante;
	
	@UploadConstraint(acceptedTypes = FileType.PNG, message = "O arquivo não é um arquivo de imagem válido")
	transient private MultipartFile imagemFile;
	
	public void setImagemFileName() {
		if (getId() == null) {
			throw new IllegalStateException("A instância precisa primeiro ser criada");
		}
		
		this.imagem = String.format("%04d-logo.%s", getId(), FileType.of(imagemFile.getContentType()).getExtension());
	}
}
