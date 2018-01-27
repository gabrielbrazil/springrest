package com.springrest.main;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.springrest.model.Produto;

public class RestGetAll {

	public static void main(String[] args) {
		   RestTemplate restTemplate = new RestTemplate();
		   List<Produto> produtos =  Arrays.asList(restTemplate.getForObject("http://localhost:8080/produtos",Produto[].class));
		   for (Produto produto : produtos) {
			  System.out.println(produto.getId());
			  System.out.println(produto.getNome());
			  System.out.println(produto.getPreco());
		   }
		
	}
	
}
