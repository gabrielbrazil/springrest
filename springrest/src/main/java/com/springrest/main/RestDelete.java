package com.springrest.main;

import org.springframework.web.client.RestTemplate;



public class RestDelete {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete("http://localhost:8080/produtos/1");
	}
}
