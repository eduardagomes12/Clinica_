package com.example.clinicadesktop.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_id_gen")
    @SequenceGenerator(name = "produto_id_gen", sequenceName = "produto_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @NotNull
    @Column(name = "preco", nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @NotNull
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @ManyToMany(mappedBy = "produtos")
    private Set<com.example.clinicadesktop.models.Consulta> consultas; // Nome correto da referência na classe Consulta

    @OneToMany(mappedBy = "idProduto")
    private Set<Eis> eis = new LinkedHashSet<>();

    public Set<Eis> getEis() {
        return eis;
    }

    public void setEis(Set<Eis> eis) {
        this.eis = eis;
    }

    // Construtor padrão
    public Produto() {}

    // Construtor com parâmetros
    public Produto(String nome, BigDecimal preco, Integer stock) {
        this.nome = nome;
        this.preco = preco;
        this.stock = stock;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Set<com.example.clinicadesktop.models.Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(Set<com.example.clinicadesktop.models.Consulta> consultas) {
        this.consultas = consultas;
    }
}
