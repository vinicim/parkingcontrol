package com.api.parkingcontrol.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api.parkingcontrol.data.UserDetailData;
import com.api.parkingcontrol.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Classe responsável por realizar a autenticação e gerar o token JWT
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	public static final int EXPIRATION_TOKEN = 600000;
	//senha para geração do token, num caso concreto essa senha não deve ficar no código-fonte
	//gerado através de um guid -- uuid generator
	public static final String PASSWORD_TOKEN = "f454ed02-dc39-4d49-a2f1-9b7f44a90b51"; 


	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	/*
	 * Método responsável por realizar a autenticação
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					user.getCpf(), 
					user.getPassword(),
					new ArrayList<>()
			));
			
		} catch (IOException e) {
			throw new RuntimeException("There is an error to autenthicate the user.", e);
		}		
	}
	
	/*
	 * O que fazer quando a autenticação for bem sucedida
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		UserDetailData userDetailData = (UserDetailData) authResult.getPrincipal();
		
		String token = JWT.create().
				withSubject(userDetailData.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN))
				.sign(Algorithm.HMAC512(PASSWORD_TOKEN));
		
		response.getWriter().write(token);
		response.getWriter().flush();
	}

}
