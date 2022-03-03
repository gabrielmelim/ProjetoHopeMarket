package org.generation.app_hopemarket.controller;


import org.generation.app_hopemarket.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/produtos")
public class CarrinhoController {


        @Autowired
        private CarrinhoRepository repository;

        @GetMapping
        public ResponseEntity<List<Produto>> findAll(){
            return ResponseEntity.ok(repository.findAll());

        }

        @GetMapping("/{id}")
        public ResponseEntity<Produto>findByIdProdutos (@PathVariable Long id) {
            return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
        }

        @GetMapping("/categoria/{categoria}")
        public ResponseEntity <List<Produto>> findByCategoriaProduto (@PathVariable String categoria){
            return ResponseEntity.ok(repository.findAllByCategoriaContainingIgnoreCase(categoria));
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
