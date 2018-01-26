package com.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.springrest.model.Produto;
import com.springrest.repository.ProdutoRepository;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	@GetMapping
	@ResponseBody
	public List<Produto> findAll(){
		return produtoRepository.findAll();
	}
	
	
	@GetMapping(value="/{id}")
	@ResponseBody
	public Produto findOne(@PathVariable("id") Long id) {
		return produtoRepository.findOne(id);
	}
	
	
	@PostMapping
//	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Produto create(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@PutMapping(value="/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("id")Long id,@RequestBody Produto produto) {
		produtoRepository.save(produto);
	}
	
	@DeleteMapping(value="/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id")Long id) {
		produtoRepository.delete(id);
	}
	
	
//	@GetMapping
//	public ModelAndView lista() {
//		return new ModelAndView("produto/lista","produtos",produtoRepository.findAll());
//	}

}
