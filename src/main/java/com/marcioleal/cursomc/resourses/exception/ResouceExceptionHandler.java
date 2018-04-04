package com.marcioleal.cursomc.resourses.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.marcioleal.cursomc.services.DataIntegrityException;
import com.marcioleal.cursomc.services.ObjectNotFoundException;

@ControllerAdvice
public class ResouceExceptionHandler {

	
  @ExceptionHandler(DataIntegrityException.class)	
  public ResponseEntity<StandardError>	dataIntegrity( ObjectNotFoundException e , HttpServletRequest request ){
	StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
  } 
	
  @ExceptionHandler(ObjectNotFoundException.class)	
  public ResponseEntity<StandardError>	objectNotFound( ObjectNotFoundException e , HttpServletRequest request ){
	StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  } 

}
