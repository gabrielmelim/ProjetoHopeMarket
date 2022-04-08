package org.generation.app_hopemarket.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.app_hopemarket.dtos.UserCredentialDTO;
import org.generation.app_hopemarket.dtos.UserLoginDTO;
import org.generation.app_hopemarket.dtos.UserRegisterDTO;
import org.generation.app_hopemarket.model.Usuario;
import org.generation.app_hopemarket.repository.UsuarioRepository;
import org.generation.app_hopemarket.service.UsuarioService;
import org.generation.app_hopemarket.util.TipoAssinante;
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
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Controlador de Usuário com endPoints (Get, Post, Put e Delete)
 * Puxar dados do usuário, Cadastrar usuários, Alterar dados de usuários e
 * deletar usuários.
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
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    private @Autowired UsuarioRepository repository;
    private @Autowired UsuarioService services;

    @Operation(summary = "Buscar todos Usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários Encontrados", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) })
    })
    @GetMapping
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @Operation(summary = "Encontrar Usuário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) })
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable(value = "id") Long id) {
        return repository.findById(id).map(resp -> ResponseEntity.status(200).body(resp)).orElseGet(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID inexistente!");
        });
    }

    @Operation(summary = "Credenciais Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário Autenticado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserCredentialDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserCredentialDTO.class)) })
    })
    @PutMapping("/auth")
    public ResponseEntity<UserCredentialDTO> getCredential(@Valid @RequestBody UserLoginDTO usuario) {
        return services.validCredential(usuario);
    }

    @Operation(summary = "Alterar Dados Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário Alterado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) })
    })
    @PutMapping
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {
        return repository.findById(usuario.getId())
                .map(resp -> ResponseEntity.status(200).body(repository.save(usuario))).orElseGet(() -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID não encontrado");
                });
    }

    @Operation(summary = "Alterar Assinatura Usuário")
    @PutMapping("/alter/package/{id_user}")
    public ResponseEntity<?> modifyPackage(@PathVariable(value = "id_user") Long id, @RequestBody TipoAssinante tipo) {
        return services.changePackage(id, tipo);
    }

    @Operation(summary = "Cadastrar Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario Criado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserRegisterDTO.class)) }),
            @ApiResponse(responseCode = "422", description = "Usuário já Cadastrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserRegisterDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserRegisterDTO.class)) })
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> save(@Valid @RequestBody UserRegisterDTO usuario) {
        return services.CadastrarUsuario(usuario);
    }

    @Operation(summary = "Deletar Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário Excluído", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) })
    })
    @SuppressWarnings("rawtypes")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(value = "id") Long id) {
        return repository.findById(id).map(resp -> {
            repository.deleteById(id);
            return ResponseEntity.status(204).build();
        }).orElseGet(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID inexistente!");
        });
    }
}