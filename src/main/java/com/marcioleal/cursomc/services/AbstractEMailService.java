package com.marcioleal.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.marcioleal.cursomc.domain.Pedido;

public abstract class AbstractEMailService implements EmailService {

	@Value("$default.sender")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
	  SimpleMailMessage sm = prepaSimplMessageFromPedido(obj);
	  sendEmail(sm);	
	}

	protected SimpleMailMessage prepaSimplMessageFromPedido(Pedido obj) {
      SimpleMailMessage sm = new SimpleMailMessage();
      sm.setTo(obj.getCliente().getEmail());
      sm.setFrom(sender);
      sm.setSubject("Pedido COnfirmado");
      sm.setSentDate(new Date(System.currentTimeMillis()));
      sm.setText(obj.toString());
	  return null;
	}

	

}
