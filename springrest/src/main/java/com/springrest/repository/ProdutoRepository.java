package com.springrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {

	
}
