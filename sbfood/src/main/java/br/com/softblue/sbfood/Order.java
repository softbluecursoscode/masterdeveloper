package br.com.softblue.sbfood;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity(name = "pedido")
public class Order implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String description;
}
