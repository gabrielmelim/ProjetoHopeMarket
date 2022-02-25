package org.generation.app_hopemarket.controller;

import java.util.List;

import org.generation.app_hopemarket.model.Produto;
import org.generation.app_hopemarket.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@CrossOrigin ("*")
@RequestMapping("/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll(){
        return ResponseEntity.ok(repository.findAll());

    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Produto>findByIdProdutos (@PathVariable Long id) {
    	return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Produto> post (@RequestBody Produto produto){
    	return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
    }

    @PutMapping
    public ResponseEntity<Produto> put (@RequestBody Produto produto) {
          return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }
}
