package com.springrest.main;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.springrest.model.Produto;

public class RestPutGetStatus {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		Produto produto = new Produto();
		produto.setId(1L);
		produto.setNome("teste2");
		produto.setPreco("40");
		
		HttpEntity<Produto> requestUpdate = new HttpEntity<>(produto);
		ResponseEntity<Produto> response = restTemplate.exchange("http://localhost:8080/produtos/1",HttpMethod.PUT,requestUpdate,Produto.class);
		System.out.println(response.getStatusCode());
	}
	
}
