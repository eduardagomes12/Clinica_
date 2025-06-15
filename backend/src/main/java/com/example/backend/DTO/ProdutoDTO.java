package com.example.backend.DTO;

import java.math.BigDecimal;

public class ProdutoDTO {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private int stock;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
