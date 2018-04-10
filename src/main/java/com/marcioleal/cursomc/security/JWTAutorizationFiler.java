package com.marcioleal.cursomc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAutorizationFiler extends BasicAuthenticationFilter {

	private JWTUtil jwtUtil;
	
	private UserDetailsService userDetailService;
	
	public JWTAutorizationFiler(AuthenticationManager authenticationManager , JWTUtil jwtUtil , UserDetailsService userDetailService ) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailService = userDetailService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		if ( header != null && header.startsWith("Bearer ") ) { 
			
		  UsernamePasswordAuthenticationToken auth = getAuthentication(request,header.substring(7));	
		  if ( auth != null ) {
			SecurityContextHolder.getContext().setAuthentication(auth);  
		  } 	
		}
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, String token) {
		if ( jwtUtil.tokenValido(token)) {
		  String username = jwtUtil.getUsername(token);
		  UserDetails user = userDetailService.loadUserByUsername(username);
		  return new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
		}
		return null;
	}
	
	

}
