package com.api.parkingcontrol.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTValidatorFilter extends BasicAuthenticationFilter {
	
	public static final String HEADER_ATTR = "Authorization";
	public static final String HEADER_ATTR_PREFIX = "Bearer";

	
	
	public JWTValidatorFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);		
	}

	/*
	 * Intercepta o cabeçalho da requisição e valida o token enviado
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain chain) throws IOException, ServletException {
		
		String header_attr = request.getHeader(HEADER_ATTR);
		
		if(header_attr  == null || !header_attr.startsWith(HEADER_ATTR_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		
		String token = header_attr.replace(HEADER_ATTR_PREFIX, "");	
		token = token.stripLeading();
		UsernamePasswordAuthenticationToken authToken = getAuthenticationToken(token);
		
		SecurityContextHolder.getContext().setAuthentication(authToken);
		
		chain.doFilter(request, response);		
	}
	
	private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
		
		String user = JWT.require(Algorithm.HMAC512(JWTAuthenticationFilter.PASSWORD_TOKEN))
				.build() //JWT Verifier
				.verify(token) //decoded JWT
				.getSubject();
		
		if (user == null) {
			return null;
		}
		
		return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
		
	}

}
