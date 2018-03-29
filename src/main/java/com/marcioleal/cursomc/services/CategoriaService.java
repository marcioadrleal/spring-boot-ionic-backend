package com.marcioleal.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcioleal.cursomc.domain.Categoria;
import com.marcioleal.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
	  Categoria obj = repo.findOne(id);	
	  if ( obj == null ) {
		 throw new ObjectNotFoundException("Objeto não encontrado ID: " + id + ", tipo:" + Categoria.class.getName() ); 
	  }
	  return obj;
	}
}
