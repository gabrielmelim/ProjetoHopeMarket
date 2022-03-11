package org.generation.app_hopemarket.security;

import java.util.Optional;

import org.generation.app_hopemarket.model.Usuario;
import org.generation.app_hopemarket.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private @Autowired UsuarioRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<Usuario> usuario = repository.findByEmail(username);
        if (usuario.isPresent()) {
            return new UserDetailsImpl(usuario.get());

        } else {
           throw new UsernameNotFoundException("Usuário não encontrado"); 
        }
    }
    
}
