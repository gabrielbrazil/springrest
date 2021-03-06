package com.springrest.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestErrorHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestErrorHandler.class);
		
	@ExceptionHandler(ProdutoNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleProdutoNotFoundException(ProdutoNotFoundException ex) {
		LOGGER.debug("lidando com 404 erro");
	}
	
}
