package com.renanmateus.cadprod.api.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.renanmateus.cadprod.domain.model.Produto;
import com.renanmateus.cadprod.domain.repository.ProdutoRepository;
import com.renanmateus.cadprod.domain.service.CadastroProduto;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CadastroProduto cadastroProduto;

	// Lista Produtos
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<Produto> listar() {
		
		return produtoRepository.findAll();

	}

	// Cria Produto
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Produto produto) {
		
		produto = cadastroProduto.salvar(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(produto);

	}

	// Atualiza Produto
	@PutMapping("/{id}")
	public ResponseEntity<?> atualiza(@RequestBody Produto produto, @PathVariable Long id) {

		Optional<Produto> produtoAtual = produtoRepository.findById(id);
		if (produtoAtual.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
		}
		BeanUtils.copyProperties(produto, produtoAtual.get(), "id");
		Produto produtoSalvo = cadastroProduto.salvar(produtoAtual.get());

		return ResponseEntity.status(HttpStatus.OK).body(produtoSalvo);

	}

	// Procura Produto por Id
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");

		}

		return ResponseEntity.ok(produto);
	}

	// Deleta Produto
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		try {
			cadastroProduto.deletar(id);
			return ResponseEntity.noContent().build();

		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	// Procura Produto por nome
	@GetMapping("/por-nome")
	
	public List<Produto> buscaPorNome(String nome) {
		
		return produtoRepository.findLikeNome(nome);

	}

}
