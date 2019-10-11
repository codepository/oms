package com.lt.cloud.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class MyCorsFilter extends OncePerRequestFilter{
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		    response.setHeader("Access-Control-Allow-Origin", "*");//* or origin as u prefer
		    response.setHeader("Access-Control-Allow-Credentials", "true");
		    response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, OPTIONS, DELETE");
		    response.setHeader("Access-Control-Allow-Headers", "*");
		    if (request.getMethod().equals("OPTIONS"))
		      response.setStatus(HttpServletResponse.SC_OK);
		    else
		      filterChain.doFilter(request, response);
		
	}

}
