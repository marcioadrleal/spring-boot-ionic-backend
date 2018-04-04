package com.marcioleal.cursomc.services;

public class DataIntegrityException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6807306076559877799L;

	public DataIntegrityException(String msg) {
	  super(msg);	
	}
	
	public DataIntegrityException(String msg , Throwable cause) {
	  super(msg, cause);	
	}
	


}
