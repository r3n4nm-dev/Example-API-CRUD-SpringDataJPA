package com.renanmateus.cadprod.domain.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.renanmateus.cadprod.domain.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	
	@Query("SELECT p FROM Produto p WHERE p.nome LIKE %:nome%")
	List<Produto> findLikeNome(String nome);
	

}
