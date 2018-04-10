package com.marcioleal.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.marcioleal.cursomc.domain.Cliente;
import com.marcioleal.cursomc.repositories.ClienteRepository;
import com.marcioleal.cursomc.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClienteRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Cliente cli = repo.findByEmail(username);
		if ( cli == null ) {
		  throw new UsernameNotFoundException(username);	
		}
		return new UserSS(cli.getId(),cli.getEmail(),cli.getSenha(),cli.getPerfis());
				
	}

}
