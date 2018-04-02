package com.marcioleal.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcioleal.cursomc.domain.Cliente;
import com.marcioleal.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Integer id) {
     Cliente obj = clienteRepository.findOne(id);	
	 if ( obj == null ) {
	   throw new ObjectNotFoundException("Objeto não encontrado ID: " + id + ", tipo:" + Cliente.class.getName() ); 
	  }
	  return obj;
	}

}
