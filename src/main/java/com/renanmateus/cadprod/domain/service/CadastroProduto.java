package com.renanmateus.cadprod.domain.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renanmateus.cadprod.domain.model.Produto;
import com.renanmateus.cadprod.domain.repository.ProdutoRepository;

@Service
public class CadastroProduto {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
		
	}
	
	
	public void deletar (Long id) {
		Optional<Produto> produtoId = produtoRepository.findById(id);
		
		if(!produtoId.isPresent()) {
			
			throw new EntityNotFoundException("Produto não encontrado."); 
		}
		produtoRepository.deleteById(id);
		
	}
	
}
