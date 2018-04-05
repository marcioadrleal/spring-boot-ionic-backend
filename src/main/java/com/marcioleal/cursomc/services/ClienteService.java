package com.marcioleal.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.marcioleal.cursomc.domain.Cliente;
import com.marcioleal.cursomc.dto.ClienteDTO;
import com.marcioleal.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Integer id) {
     Cliente obj = clienteRepository.findOne(id);	
	 if ( obj == null ) {
	   throw new ObjectNotFoundException("Objeto não encontrado ID: " + id + ", tipo:" + Cliente.class.getName() ); 
	  }
	  return obj;
	}
	
	public Cliente insert(Cliente obj) {
	  obj.setId(null);	
	  return clienteRepository.save(obj);	
	}

	public List<Cliente> findAll(){
	  return clienteRepository.findAll();	
	}
	
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage , String orderBy , String direction   ){
	  PageRequest pageRequest = new PageRequest(page,linesPerPage, Direction.valueOf(direction) , orderBy);	
	  return clienteRepository.findAll(pageRequest);	
	}
	
	
	public Cliente update(Cliente obj) {
	  find(obj.getId());	
	  return clienteRepository.save(obj);	
	}
	
	public void delete(Integer id) {
	  find(id);
	  try {
	    clienteRepository.delete(id);
	  }catch(DataIntegrityViolationException e) {
		  throw new DataIntegrityException("Não é possível excluir uma Cliente que possui produtos");
	  }
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
	  return null;	
	}

}
