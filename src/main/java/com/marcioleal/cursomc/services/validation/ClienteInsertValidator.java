package com.marcioleal.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.marcioleal.cursomc.domain.Cliente;
import com.marcioleal.cursomc.dto.ClienteNewDTO;
import com.marcioleal.cursomc.repositories.ClienteRepository;
import com.marcioleal.cursomc.resourses.exception.FieldMessage;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {

	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		// inclua os testes aqui, inserindo erros na lista
	
		Cliente aux = repo.findByEmail(objDto.getEmail());
		
		if ( aux != null ) {
		  list.add(new FieldMessage("Email", "Email já cadastrado"));	
		}
		
		if ( objDto.getTipo() == null ) {
		  list.add(new FieldMessage("Tipo","Tipo Não podeser nulo"));	
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFildName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}
