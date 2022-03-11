package org.generation.app_hopemarket.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserLoginDTO { 
    private @NotBlank @Email String email;
    private @NotBlank String senha;
    
    public UserLoginDTO(){
    }
    
    public UserLoginDTO(String email, String senha){
        this.email = email;
        this.senha = senha;
    }


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
