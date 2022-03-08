package org.generation.app_hopemarket.controller;

import java.util.List;

import org.generation.app_hopemarket.model.Usuario;
import org.generation.app_hopemarket.repository.ProdutoRepository;
import org.generation.app_hopemarket.repository.UsuarioRepository;
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

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
       return ResponseEntity.ok (repository.findAll());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Usuario> post (@RequestBody Usuario usuario){
      return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
    }
    @PutMapping
    public ResponseEntity<Usuario> put (@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuario));
    }
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id){
        repository.deleteById(id);
    }
}
