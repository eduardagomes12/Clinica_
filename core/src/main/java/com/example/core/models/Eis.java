package com.example.core.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "eis")
public class Eis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String movimento; // Ex: "entrada", "saída"
    private Integer qtd;
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMovimento() { return movimento; }
    public void setMovimento(String movimento) { this.movimento = movimento; }

    public Integer getQtd() { return qtd; }
    public void setQtd(Integer qtd) { this.qtd = qtd; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
}
