package com.v.accounts.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class CorelationIdFilter extends OncePerRequestFilter {

	public static final String CORRELATION_ID = "v-correlation-id";
	
	public static final Logger logger = LoggerFactory.getLogger(CorelationIdFilter.class);

	
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		 // Extract correlation ID from request header
		String correlationId = request.getHeader(CORRELATION_ID);

		if (correlationId != null && !correlationId.trim().isEmpty()) {
			MDC.put(CORRELATION_ID, correlationId); // Attach to log context
			response.setHeader(CORRELATION_ID, correlationId); // Add to response too
			logger.info("Correlation ID in {}: {}", request.getRequestURI(), correlationId);

		}
		
        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(CORRELATION_ID); // Clear after request completes
        }

	}

}