package com.marcioleal.cursomc.dto;

import java.io.Serializable;

import com.marcioleal.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable{

   /**
	 * 
	 */
	private static final long serialVersionUID = 554486367691271750L;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

   private Integer id;
   private String nome;
   
   public CategoriaDTO() {
	   
	   
   }
   
   
   public CategoriaDTO(Categoria obj) {
	 this.id = obj.getId();
	 this.nome = obj.getNome();
   }
	
}
