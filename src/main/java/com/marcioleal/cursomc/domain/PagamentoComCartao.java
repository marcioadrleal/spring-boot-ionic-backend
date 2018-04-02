package com.marcioleal.cursomc.domain;

import javax.persistence.Entity;

import com.marcioleal.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {
  /**
	 * 
	 */
  private static final long serialVersionUID = -3307866645990501906L;
  private Integer numeroDeParcelas;
  
  public PagamentoComCartao(){
	  
	  
  }

  public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido,Integer numeroParcelas ) {
	super(id, estado, pedido);
	this.numeroDeParcelas = numeroParcelas;
	// TODO Auto-generated constructor stub
 }

public Integer getNumeroDeParcelas() {
	return numeroDeParcelas;
}

public void setNumeroDeParcelas(Integer numeroDeParcelas) {
	this.numeroDeParcelas = numeroDeParcelas;
}



  

  
  
}
