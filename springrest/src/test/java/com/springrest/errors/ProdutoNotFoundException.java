package com.springrest.errors;


public class ProdutoNotFoundException extends RuntimeException {

	 public ProdutoNotFoundException() {
	        super();
	    }
	    public ProdutoNotFoundException(String message, Throwable cause) {
	        super(message, cause);
	    }
	    public ProdutoNotFoundException(String message) {
	        super(message);
	    }
	    public ProdutoNotFoundException(Throwable cause) {
	        super(cause);
	    }
	
	
}
