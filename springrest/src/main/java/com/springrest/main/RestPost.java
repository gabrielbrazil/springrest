package com.springrest.main;

import org.springframework.web.client.RestTemplate;

import com.springrest.model.Produto;

public class RestPost {

	public static void main(String[] args) {
		 RestTemplate restTemplate = new RestTemplate();
		  
		   Produto produtoOut = new Produto();
		   produtoOut.setNome("Teste");
		   produtoOut.setPreco("30.0");
		  
		   Produto produto = restTemplate.postForObject("http://localhost:8080/produtos", produtoOut, Produto.class);
		   System.out.println(produto.getId());
		   System.out.println(produto.getNome());
		   System.out.println(produto.getPreco());
	}
}
