package org.generation.app_hopemarket.service;

import java.util.Optional;

import org.generation.app_hopemarket.dtos.UserRegisterDTO;
import org.generation.app_hopemarket.model.Usuario;
import org.generation.app_hopemarket.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService { 
    private @Autowired UsuarioRepository repository;
    private Usuario novoUsuario;

    public ResponseEntity<Usuario> CadastrarUsuario(UserRegisterDTO usuario){
        Optional<Usuario> optional= repository.findByEmail(usuario.getEmail());

        if (optional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail já em uso");
        } else {
            BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
                usuario.setSenha(encoder.encode(usuario.getSenha()));
        }
        novoUsuario = new Usuario(
            usuario.getNome(),
            usuario.getCpf(),
            usuario.getEmail(),
            usuario.getSenha());

            return ResponseEntity.status(201).body(repository.save(novoUsuario));
    } 



    
}
