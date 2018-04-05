package com.marcioleal.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.marcioleal.cursomc.domain.Cliente;
import com.marcioleal.cursomc.dto.ClienteDTO;
import com.marcioleal.cursomc.repositories.ClienteRepository;
import com.marcioleal.cursomc.resourses.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClientUpdate, ClienteDTO> {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(ClientUpdate ann) {

	}

	@SuppressWarnings("unused")
	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		// inclua os testes aqui, inserindo erros na lista
	   @SuppressWarnings("unchecked")
	   Map<String,String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		
		if ( aux != null ) {
		  list.add(new FieldMessage("Email", "Email já cadastrado"));	
		}
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFildName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}
