package com.marcioleal.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.marcioleal.cursomc.domain.Categoria;
import com.marcioleal.cursomc.domain.Pedido;
import com.marcioleal.cursomc.domain.Produto;
import com.marcioleal.cursomc.repositories.CategoriaRepository;
import com.marcioleal.cursomc.repositories.ProdutoRepository;

@Service
public class Produtoservice {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository repositoryCategoria;
	
	public Produto find(Integer id) {
	  Produto obj = repo.findOne(id);	
	  if ( obj == null ) {
		 throw new ObjectNotFoundException("Objeto não encontrado ID: " + id + ", tipo:" + Pedido.class.getName() ); 
	  }
	  return obj;
	}
	
	
	public Page<Produto> search( String nome , List<Integer> ids ,  Integer page, Integer linesPerPage , String orderBy , String direction   ){
	  PageRequest pageRequest = new PageRequest(page,linesPerPage, Direction.valueOf(direction), orderBy);
	  List<Categoria> categorias = repositoryCategoria.findAll(ids);
	  return repo.search(nome,categorias,pageRequest);	
	}
}
