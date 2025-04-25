package com.example.clinicadesktop.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "tipotratamento")
public class Tipotratamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipotratamento_id_gen")
    @SequenceGenerator(name = "tipotratamento_id_gen", sequenceName = "tipotratamento_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @NotNull
    @Column(name = "valor", nullable = false)
    private Double valor;

    @OneToMany(mappedBy = "tipotratamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Tratamento> tratamentos;

    // Construtor padrão
    public Tipotratamento() {}

    // Construtor com parâmetros
    public Tipotratamento(String nome, String descricao, Double valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Set<Tratamento> getTratamentos() {
        return tratamentos;
    }

    public void setTratamentos(Set<Tratamento> tratamentos) {
        this.tratamentos = tratamentos;
    }
}
