package com.marcioleal.cursomc.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ItemPedido {
	
	@Id
	private ItemPedidoPK id = new ItemPedidoPK();
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;

}
