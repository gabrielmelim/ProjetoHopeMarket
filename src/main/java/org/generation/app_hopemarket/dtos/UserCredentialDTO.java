package org.generation.app_hopemarket.dtos;

public class UserCredentialDTO {
    private Long id;
    private String nome;
    private String email;
    private String token;
    private String tipo;

    public UserCredentialDTO(){
    }
    public UserCredentialDTO(Long id, String nome, String email, String token, String tipo){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.token = token;
        this.tipo = tipo
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    



    
}
