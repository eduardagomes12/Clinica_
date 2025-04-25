package com.example.clinicadesktop.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "tipoutilizador")
public class Tipoutilizador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @NotNull
    @Column(name = "permissoes", nullable = false, columnDefinition = "TEXT")
    private String permissoes;

    @OneToMany(mappedBy = "tipoutilizador", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Utilizador> utilizadors;

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

    public String getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(String permissoes) {
        this.permissoes = permissoes;
    }

    public Set<Utilizador> getUtilizadors() {
        return utilizadors;
    }

    public void setUtilizadors(Set<Utilizador> utilizadors) {
        this.utilizadors = utilizadors;
    }
}
