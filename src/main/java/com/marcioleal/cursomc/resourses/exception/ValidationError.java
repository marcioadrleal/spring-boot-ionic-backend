package com.marcioleal.cursomc.resourses.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	
	private List<FieldMessage> list = new ArrayList<FieldMessage>();
	
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7792238072132216958L;


	public List<FieldMessage> getError() {
		return list;
	}

	public void addError(String fieldName , String message) {
	 list.add(new FieldMessage(fieldName,message)); 	
	}
	
	

}
