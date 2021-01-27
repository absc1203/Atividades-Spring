package com.loja.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.game.model.Produto;
import com.loja.game.repository.ProdutoRespository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRespository repository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAllProduto(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> findAllByNomeProduto(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> findByIdProduto(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Produto> postProduto(@RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}
	
	@PutMapping
	public ResponseEntity<Produto> putProduto(@RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduto(@PathVariable long id) {
		repository.deleteById(id);
	}

}
