package org.generation.app_hopemarket.security;

import org.generation.app_hopemarket.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;


public class UserDetailsImpl implements UserDetails {

    private String email;
    private String senha;

    
    public UserDetailsImpl (Usuario usuario){
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }

    public UserDetailsImpl(){}

    
}
