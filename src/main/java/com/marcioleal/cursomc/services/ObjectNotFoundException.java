package com.marcioleal.cursomc.services;

public class ObjectNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6807306076559877799L;

	public ObjectNotFoundException(String msg) {
	  super(msg);	
	}
	
	public ObjectNotFoundException(String msg , Throwable cause) {
	  super(msg, cause);	
	}
	


}
