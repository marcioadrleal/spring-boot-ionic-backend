package com.marcioleal.cursomc.dto;

import java.io.Serializable;

import com.marcioleal.cursomc.domain.Cliente;

public class ClienteDTO implements Serializable{

   /**
	 * 
	 */
  private static final long serialVersionUID = 554486367691271750L;
  private Integer id;
  private String nome;
  private String email;
    
   
   public ClienteDTO() {
	   
	   
   }
   
   
   public ClienteDTO(Cliente obj) {
	 this.id = obj.getId();
	 this.nome = obj.getNome();
	 this.email = obj.getEmail();
   }


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


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}
   
   
	
}
