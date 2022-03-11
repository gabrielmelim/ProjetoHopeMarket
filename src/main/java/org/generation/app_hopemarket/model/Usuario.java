package org.generation.app_hopemarket.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.generation.app_hopemarket.util.TipoAssinante;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String nome;

    @NotNull
    private String cpf;

    @NotNull
    private String senha;

    @NotNull
    private String email;
    
    @Enumerated(EnumType.STRING)
    private TipoAssinante pacote;

    public Usuario(){
        super();
    }

    public Usuario(String nome, String cpf, String email, String senha) {
        super();
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(Long id, String nome, String cpf, String email, String senha){
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoAssinante getPacote() {
        return this.pacote;
    }

    public void setPacote(TipoAssinante pacote) {
        this.pacote = pacote;
    }

}
