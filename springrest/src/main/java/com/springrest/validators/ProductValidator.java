package com.springrest.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.springrest.model.Produto;

@Component
public class ProductValidator  implements Validator{

	//Validação no Servico
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Produto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors error) {
			Produto produto = (Produto) target;
			//Validação no Servico
	}

}
