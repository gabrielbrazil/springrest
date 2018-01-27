package com.springrest.main;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.springrest.controller.ProdutoController;
import com.springrest.errors.ProdutoNotFoundException;
import com.springrest.model.Produto;
import com.springrest.repository.ProdutoRepository;
import com.springrest.util.TestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(ProdutoController.class)
public class TesteProduto {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ProdutoRepository produtoRepository;
	
	@Test
	public void allProdutos() throws Exception {
		Produto produto = new Produto();
		produto.setId(1L);
		produto.setNome("Caderno");
		produto.setPreco("25.5");
		
		
		Produto produto2 = new Produto();
		produto2.setId(2L);
		produto2.setNome("Lapis");
		produto2.setPreco("25.5");
		
		Mockito.when(produtoRepository.findAll()).thenReturn(Arrays.asList(produto,produto2));
		
		mockMvc.perform(get("/produtos"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$",hasSize(2)))
		.andExpect(jsonPath("$[0].id",is(1)))
		.andExpect(jsonPath("$[1].id",is(2)));
		
		verify(produtoRepository,times(1)).findAll();
		verifyNoMoreInteractions(produtoRepository);
		
	}
	
	
	
	@Test
    public void findById_TodoEntryNotFound_ShouldReturnHttpStatusCode404() throws Exception {
        when(produtoRepository.findOne(1L)).thenThrow(new ProdutoNotFoundException(""));
 
        mockMvc.perform(get("/produtos/{id}", 1L))
                .andExpect(status().isNotFound());
 
        verify(produtoRepository, times(1)).findOne(1L);
        verifyNoMoreInteractions(produtoRepository);
    }
	
	
	
	@Test
	public void findByIdRest() throws Exception {
		Produto produto = new Produto();
		produto.setId(1L);
		produto.setNome("Caderno");
		produto.setPreco("25.5");
		
		Mockito.when(produtoRepository.findOne(1L)).thenReturn(produto);
		
		mockMvc.perform(get("/produtos/{id}",1L)).andExpect(status().isOk())
		 .andExpect(jsonPath("$.id", is(1)))
		 .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
         .andExpect(jsonPath("$.nome", is("Caderno")))
         .andExpect(jsonPath("$.preco", is("25.5")));
		
		verify(produtoRepository, times(1)).findOne(1L);
		verifyNoMoreInteractions(produtoRepository);
	}
	
	
	@Test
	public void addNewTest() throws IOException, Exception{
		Produto produto = new Produto();
		produto.setNome("Caderno");
		produto.setPreco("25.5");
		
		Produto produtoTeste = new Produto();
		produtoTeste.setId(1L);
		produtoTeste.setNome("Caderno");
		produtoTeste.setPreco("25.5");
		
		
		when(produtoRepository.save(any(Produto.class))).thenReturn(produtoTeste);
		mockMvc.perform(post("/produtos")
		.contentType(TestUtil.APPLICATION_JSON_UTF8)
		.content(TestUtil.convertObjectToJsonBytes(produto))
		)
		.andExpect(status().isCreated())
		.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.id", is(1)));
		
		 ArgumentCaptor<Produto> dtoCaptor = ArgumentCaptor.forClass(Produto.class);
	        verify(produtoRepository, times(1)).save(dtoCaptor.capture());
	        verifyNoMoreInteractions(produtoRepository);
	 
	        Produto dtoArgument = dtoCaptor.getValue();
	        
	        assertNull(dtoArgument.getId());
	        assertThat(dtoArgument.getNome(),is("Caderno"));
	        assertThat(dtoArgument.getPreco(),is("25.5"));
	}
	
	
	@Test
	public void deleteTest() throws IOException, Exception{
		mockMvc.perform(delete("/produtos/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		verify(produtoRepository, times(1)).delete(1L);
		verifyNoMoreInteractions(produtoRepository);
	}
	
	@Test
	public void updateTest() throws Exception {
		Produto produto = new Produto();
		produto.setId(1L);
		produto.setNome("borracha atual");
		produto.setPreco("20");
		
		when(produtoRepository.save(any(Produto.class))).thenReturn(produto);
		
		mockMvc.perform(put("/produto/{id}",1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(produto)))
				.andExpect(status().isOk());
		
	}
	
}
