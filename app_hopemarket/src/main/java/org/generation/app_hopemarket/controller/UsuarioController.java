package org.generation.app_hopemarket.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.app_hopemarket.dtos.UserCredentialDTO;
import org.generation.app_hopemarket.dtos.UserLoginDTO;
import org.generation.app_hopemarket.dtos.UserRegisterDTO;
import org.generation.app_hopemarket.model.Usuario;
import org.generation.app_hopemarket.repository.UsuarioRepository;
import org.generation.app_hopemarket.service.UsuarioService;

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

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*" )
public class UsuarioController {
    
    private @Autowired UsuarioRepository repository; 
    private @Autowired UsuarioService services;

    @GetMapping
    public List<Usuario> findAll(){
        return repository.findAll();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable (value = "id") Long id){
        return repository.findById(id).map(resp -> ResponseEntity.status(200).body(resp)).orElseGet(() -> {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID inexistente!");
        });
    }
    @PutMapping("/config")
    public ResponseEntity<UserCredentialDTO> getCredential(@Valid @RequestBody UserLoginDTO usuario){
        return services.validCredential(usuario);
    }    

    @PostMapping("/login")
    public ResponseEntity<Usuario> save (@Valid @RequestBody UserRegisterDTO usuario) {
      return services.CadastrarUsuario(usuario);
    }

    @PutMapping
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario){
        return repository.findById(usuario.getId()).map(resp -> ResponseEntity.status(200).body(repository.save(usuario))).orElseGet(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID não encontrado");
        });
    }

    @SuppressWarnings("rawtypes")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable (value = "id") Long id){
        return repository.findById(id).map(resp -> {
                repository.deleteById(id);
                return ResponseEntity.status(204).build();  
        }).orElseGet(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID inexistente!");
        });
    }



}

