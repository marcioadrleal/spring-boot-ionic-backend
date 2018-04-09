package com.marcioleal.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.marcioleal.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
