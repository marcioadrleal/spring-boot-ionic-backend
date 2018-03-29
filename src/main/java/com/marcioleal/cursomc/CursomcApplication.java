package com.marcioleal.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcioleal.cursomc.domain.Categoria;
import com.marcioleal.cursomc.domain.Produto;
import com.marcioleal.cursomc.repositories.CategoriaRepository;
import com.marcioleal.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepositiry;
	
	@Autowired
	private ProdutoRepository  produtoRepository;
	
	public static void main(String[] args) {
		
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	  Categoria cat1 = new Categoria(null,"Informática");
	  Categoria cat2 = new Categoria(null,"Escritorio");
	  
	  Produto p1 = new Produto(null,"Computador",2000.00);
	  Produto p2 = new Produto(null,"Impressora",800.00);
	  Produto p3 = new Produto(null,"Mouse",20.00);
	  Produto p4 = new Produto(null,"Sulfite",20.00);
	  cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
	  cat2.getProdutos().addAll(Arrays.asList(p4));
	  
	  p1.getCategorias().addAll(Arrays.asList(cat1));
	  p2.getCategorias().addAll(Arrays.asList(cat1));
	  p3.getCategorias().addAll(Arrays.asList(cat1));
	  p4.getCategorias().addAll(Arrays.asList(cat2));
	  
	  categoriaRepositiry.save(Arrays.asList(cat1,cat2));
	  produtoRepository.save(Arrays.asList(p1,p2,p3,p4));
	}
	
	
}
