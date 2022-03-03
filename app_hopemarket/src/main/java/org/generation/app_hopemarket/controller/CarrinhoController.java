package org.generation.app_hopemarket.controller;

import org.generation.app_hopemarket.model.Carrinho;
import org.generation.app_hopemarket.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrinho")
@CrossOrigin("*")
public class CarrinhoController {
    @Autowired
    private CarrinhoRepository repository;

    @GetMapping
    public ResponseEntity<List<Carrinho>> findAllCarrinho()
    {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrinho> findByIdCarrinho(@PathVariable Long id)
    {
        return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/idcompra/{idcompra}")
    public ResponseEntity<List<Carrinho>> findByIdCompraCarrinho(@PathVariable String idcompra)
    {
        return ResponseEntity.ok(repository.findAllByIdcompraContainingIgnoreCase(idcompra));
    }

    @PostMapping
    public ResponseEntity<Carrinho> post (@RequestBody Carrinho carrinho)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(carrinho));
    }

    @PutMapping
    public ResponseEntity<Carrinho> put (@RequestBody Carrinho carrinho)
    {
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(carrinho));
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id)
    {
        repository.deleteById(id);
    }




}
