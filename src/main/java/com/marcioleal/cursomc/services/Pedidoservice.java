package com.marcioleal.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcioleal.cursomc.domain.Pedido;
import com.marcioleal.cursomc.repositories.PedidoRepository;

@Service
public class Pedidoservice {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
	  Pedido obj = repo.findOne(id);	
	  if ( obj == null ) {
		 throw new ObjectNotFoundException("Objeto não encontrado ID: " + id + ", tipo:" + Pedido.class.getName() ); 
	  }
	  return obj;
	}
}
