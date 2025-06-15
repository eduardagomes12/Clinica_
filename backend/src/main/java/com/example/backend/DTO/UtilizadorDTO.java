package com.example.backend.DTO;

public class UtilizadorDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Long idTipoUtilizador;
    private String nomeTipoUtilizador;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public Long getIdTipoUtilizador() { return idTipoUtilizador; }
    public void setIdTipoUtilizador(Long idTipoUtilizador) { this.idTipoUtilizador = idTipoUtilizador; }

    public String getNomeTipoUtilizador() { return nomeTipoUtilizador; }
    public void setNomeTipoUtilizador(String nomeTipoUtilizador) { this.nomeTipoUtilizador = nomeTipoUtilizador; }
}
