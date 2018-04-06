package com.marcioleal.cursomc.resourses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcioleal.cursomc.domain.Produto;
import com.marcioleal.cursomc.dto.ProdutoDTO;
import com.marcioleal.cursomc.resourses.utils.URL;
import com.marcioleal.cursomc.services.Produtoservice;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResourse {
	
  @Autowired	
  private Produtoservice service;	

  @RequestMapping(value="/{id}" , method=RequestMethod.GET)	
  public ResponseEntity<Produto> find(@PathVariable Integer id) {
	Produto obj = service.find(id); 
	return ResponseEntity.ok().body(obj); 
  }	
  
  @RequestMapping(method=RequestMethod.GET)	
  public ResponseEntity<Page<ProdutoDTO>> findPage(
    @RequestParam(value="nome" , defaultValue="") String nome,
    @RequestParam(value="categorias" , defaultValue="") String categorias,
	@RequestParam(value="page" , defaultValue="0") Integer page,
	@RequestParam(value="linePerPage" , defaultValue="24") Integer linePerPage ,
	@RequestParam(value="orderBy" , defaultValue="nome") String orderBy ,
	@RequestParam(value="direction" , defaultValue="DESC") String direction ){ 
	Page<Produto> list = service.search(URL.decodeParam(nome), URL.decodeIntList(categorias), page, linePerPage, orderBy, direction);  
	Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
	return ResponseEntity.ok().body(listDto); 
  }	
	
}
