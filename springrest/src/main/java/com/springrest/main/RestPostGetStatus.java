package com.springrest.main;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.springrest.model.Produto;

public class RestPostGetStatus {

	public static void main(String[] args) {
		
		RestTemplate restTemplate = new RestTemplate();
		 Produto produto = new Produto();
		 produto.setNome("Teste");
		 produto.setPreco("30.0");
		
		HttpEntity<Produto> request =  new HttpEntity<>(produto);
		ResponseEntity<Produto> response = restTemplate.exchange("http://localhost:8080/produtos",HttpMethod.POST,request,Produto.class);
		
		System.out.println(response.getStatusCode());
		
		
	}
	
	
	
}
