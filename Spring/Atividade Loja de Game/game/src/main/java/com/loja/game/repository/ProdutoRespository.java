package com.loja.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loja.game.model.Produto;

@Repository
public interface ProdutoRespository extends JpaRepository<Produto, Long> {

	public List<Produto> findAllByNomeContainingIgnoreCase(String nome);
}
