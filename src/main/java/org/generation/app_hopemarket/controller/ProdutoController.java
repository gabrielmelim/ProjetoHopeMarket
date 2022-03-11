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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.web.bind.annotation.PutMapping;

/**
 * Controlador de Produto com endPoints (Get, Post, Put e Delete)
 * Puxar dados do produto, Cadastrar produtos, Alterar dados do produto e
 * deletar produtos
 * 
 * @author Fernando
 * @author Gabriel
 * @author Tamara
 * @author Jamille
 * @author Giovanna
 * @author Jeferson
 * @author Lucas
 * @version 0.0.1
 * @since 2022
 * 
 **/

@RestController
@CrossOrigin("*")
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @Operation(summary = "Buscar todos Produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos encontrados", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) })
    })
    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok(repository.findAll());

    }

    @Operation(summary = "Buscar Produto pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) }),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) })
    })
    @GetMapping("/{id}")
    public ResponseEntity<Produto> findByIdProdutos(@PathVariable Long id) {
        return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar produtos por Categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos Encontrados", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) }),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) })
    })
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Produto>> findByCategoriaProduto(@PathVariable String categoria) {
        return ResponseEntity.ok(repository.findAllByCategoriaContainingIgnoreCase(categoria));
    }

    @Operation(summary = "Cadastro Produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto cadastrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) }),
            @ApiResponse(responseCode = "400", description = "Produto não cadastrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) })
    })
    @PostMapping
    public ResponseEntity<Produto> post(@RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
    }

    @Operation(summary = "Alterar Produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto alterado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) }),
            @ApiResponse(responseCode = "400", description = "Produto não alterado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) })
    })
    @PutMapping
    public ResponseEntity<Produto> put(@RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
    }

    @Operation(summary = "Deletar Produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto Excluído", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) }),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) })
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
