package com.example.backend.DTO;

public class ClienteDTO {
    private Long id;
    private String nome;
    private String contacto;
    private String morada;
    private String email;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }

    public String getMorada() { return morada; }
    public void setMorada(String morada) { this.morada = morada; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
