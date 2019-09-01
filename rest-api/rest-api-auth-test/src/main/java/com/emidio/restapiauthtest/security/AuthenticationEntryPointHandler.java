package com.emidio.restapiauthtest.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * AuthenticationEntryPointHandler
 */
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Acesso Negado.");
		
	}

}