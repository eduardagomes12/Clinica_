package com.example.core.models;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "consultaproduto")
@IdClass(ConsultaProdutoId.class)
public class ConsultaProduto implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_consulta")
    private Consulta consulta;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    // Getters e Setters
    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
