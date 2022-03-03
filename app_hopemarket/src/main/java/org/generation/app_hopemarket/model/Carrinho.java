package org.generation.app_hopemarket.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table ( name = "tb_carrinho")
public class Carrinho {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int id_compras;

    @NotNull
    private double pagamento;
    
    @NotNull
    private String endereco;

    @NotNull
    private Date data;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getId_compras() {
        return this.id_compras;
    }

    public void setId_compras(int id_compras) {
        this.id_compras = id_compras;
    }

    public double getPagamento() {
        return this.pagamento;
    }

    public void setPagamento(double pagamento) {
        this.pagamento = pagamento;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
 
    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    } 

        
}
