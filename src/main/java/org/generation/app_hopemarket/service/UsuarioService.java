package org.generation.app_hopemarket.service;

import java.nio.charset.Charset;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.app_hopemarket.dtos.UserCredentialDTO;
import org.generation.app_hopemarket.dtos.UserLoginDTO;
import org.generation.app_hopemarket.dtos.UserRegisterDTO;
import org.generation.app_hopemarket.model.Usuario;
import org.generation.app_hopemarket.repository.UsuarioRepository;
import org.generation.app_hopemarket.util.TipoAssinante;
import org.apache.commons.codec.binary.Base64;
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

    public ResponseEntity<Usuario> CadastrarUsuario(UserRegisterDTO usuario) {
        Optional<Usuario> optional = repository.findByEmail(usuario.getEmail());

        if (optional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail já em uso");
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            usuario.setSenha(encoder.encode(usuario.getSenha()));

            novoUsuario = new Usuario(
                    usuario.getNome(),
                    usuario.getCpf(),
                    usuario.getEmail(),
                    usuario.getSenha());

            return ResponseEntity.status(201).body(repository.save(novoUsuario));
        }
    }

    public ResponseEntity<UserCredentialDTO> validCredential(@Valid UserLoginDTO usuario) {
        return repository.findByEmail(usuario.getEmail()).map(u -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(usuario.getSenha(), u.getSenha())) {
                UserCredentialDTO credential = new UserCredentialDTO(
                        u.getId(),
                        u.getNome(),
                        u.getEmail(),
                        generatorToken(usuario.getEmail(), usuario.getSenha()));
                return ResponseEntity.status(200).body(credential);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha inválida");
            }
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "E-mail não encontrado"));
    }

    private String generatorToken(String email, String senha) {
        String structure = email + ":" + senha;
        byte[] structureBase64 = Base64.encodeBase64(structure.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String(structureBase64);
    }

    public ResponseEntity<?> changePackage(Long id, TipoAssinante tipo){
        return repository.findById(id).map(resp -> {
            resp.setPacote(tipo);
            return ResponseEntity.ok(repository.save(resp));
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id incorreto!"));
    }

}
