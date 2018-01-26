package com.springrest.main;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.springrest.model.Produto;

public class Main {
   public static void main(String[] args) {
	   
	   RestTemplate restTemplate = new RestTemplate();
	   Produto produto = restTemplate.getForObject("http://localhost:8080/produtos/1", Produto.class);
	   List<Produto> produtos =  Arrays.asList(restTemplate.getForObject("http://localhost:8080/produtos",Produto[].class));
	   System.out.println(produto.getNome());
	   System.out.println("Lista");
	   for (Produto produt : produtos) {
		System.out.println(produt.getNome());
	}
	 
	   Produto produtoOut = new Produto();
	   produtoOut.setNome("Teste");
	   produtoOut.setPreco("30.0");
	   
	   Produto example = restTemplate.postForObject("http://localhost:8080/produtos", produtoOut, Produto.class);
	   System.out.println(example.getNome()+ "foir inserido");   
	   
//		ResponseEntity<String> response = restTemplate.postForEntity(url+restParm, null, String.class);
//		HttpStatus status = response.getStatusCode();
//		String restCall = response.getBody();
	   
   }
}
