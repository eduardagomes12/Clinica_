package com.example.clinicadesktop.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "utilizador")
public class Utilizador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @NotNull
    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @NotNull
    @Column(name = "senha", nullable = false, length = 255)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "id_tipoutilizador", nullable = false)
    private com.example.clinicadesktop.models.Tipoutilizador tipoutilizador;

    @OneToMany(mappedBy = "utilizador")
    private Set<com.example.clinicadesktop.models.Consulta> consultas = new LinkedHashSet<>();

    public Set<com.example.clinicadesktop.models.Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(Set<com.example.clinicadesktop.models.Consulta> consultas) {
        this.consultas = consultas;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public com.example.clinicadesktop.models.Tipoutilizador getTipoutilizador() {
        return tipoutilizador;
    }

    public void setTipoutilizador(com.example.clinicadesktop.models.Tipoutilizador tipoutilizador) {
        this.tipoutilizador = tipoutilizador;
    }
}
