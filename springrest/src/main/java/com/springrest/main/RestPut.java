package com.springrest.main;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.springrest.model.Produto;

public class RestPut {
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		 Produto produto = new Produto();
		 produto.setNome("Teste5");
		 produto.setPreco("30.0");
		
		HttpEntity<Produto> requestUpdate =  new HttpEntity<>(produto);
		restTemplate.put("http://localhost:8080/produtos/1", requestUpdate);
		ResponseEntity<Produto> response = restTemplate.exchange("http://localhost:8080/produtos/1",HttpMethod.PUT,requestUpdate,Produto.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().getNome());
		
	}
}
