package org.generation.app_hopemarket.controller;

import org.generation.app_hopemarket.model.Carrinho;
import org.generation.app_hopemarket.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

/**
 * Controlador de Carrinho com endPoints (Get, Post, Put e Delete)
 * Puxar dados do carrinho, Cadastrar carrinho, Alterar dados do carrinho e
 * deletar carrinho
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
@RequestMapping("/carrinho")
@CrossOrigin("*")
public class CarrinhoController {
    @Autowired
    private CarrinhoRepository repository;

    @Operation(summary = "Buscar todos Carrinhos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrinhos encontrados", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Carrinho.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Carrinho.class)) })
    })
    @GetMapping
    public ResponseEntity<List<Carrinho>> findAllCarrinho() {
        return ResponseEntity.ok(repository.findAll());
    }

    @Operation(summary = "Buscar Carrinho pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrinho encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Carrinho.class)) }),
            @ApiResponse(responseCode = "404", description = "Carrinho não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Carrinho.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Carrinho.class)) })
    })

    @GetMapping("/{id}")
    public ResponseEntity<Carrinho> findByIdCarrinho(@PathVariable Long id) {
        return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar Carrinho pelo Token de compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrinho encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Carrinho.class)) }),
            @ApiResponse(responseCode = "404", description = "Carrinho não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Carrinho.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Carrinho.class)) })
    })
    @GetMapping("/carrinho/{email}")
    public ResponseEntity<List<Carrinho>> findByEmailCarrinho(@PathVariable String email) {
        return ResponseEntity.ok(repository.findAllByEmailContainingIgnoreCase(email));
    }

    @Operation(summary = "Cadastrar Carrinho")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carrinho cadastrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Carrinho.class)) }),
            @ApiResponse(responseCode = "400", description = "Carrinho não cadastrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Carrinho.class)) }),
    })
    @PostMapping
    public ResponseEntity<Carrinho> post(@RequestBody Carrinho carrinho) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(carrinho));
    }

    @Operation(summary = "Alterar Carrinho")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carrinho alterado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Carrinho.class)) }),
            @ApiResponse(responseCode = "400", description = "Carrinho não alterado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Carrinho.class)) }),
    })
    @PutMapping
    public ResponseEntity<Carrinho> put(@RequestBody Carrinho carrinho) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(carrinho));
    }

    @Operation(summary = "Deletar Carrinho")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Carrinho Excluído", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Carrinho.class)) }),
            @ApiResponse(responseCode = "404", description = "Carrinho não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Carrinho.class)) })
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
