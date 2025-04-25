package com.example.clinicadesktop.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "tratamento")
public class Tratamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tratamento_id_gen")
    @SequenceGenerator(name = "tratamento_id_gen", sequenceName = "tratamento_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "tipo", nullable = false, length = 100)
    private String tipo;

    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "status", length = 50)
    private String status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipotratamento", nullable = false)
    private com.example.clinicadesktop.models.Tipotratamento tipotratamento;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_consulta", nullable = false)
    private com.example.clinicadesktop.models.Consulta consulta;

    // Construtor padrão
    public Tratamento() {}

    // Construtor com parâmetros
    public Tratamento(String tipo, LocalDate data, String descricao, String status, com.example.clinicadesktop.models.Tipotratamento tipotratamento, com.example.clinicadesktop.models.Consulta consulta) {
        this.tipo = tipo;
        this.data = data;
        this.descricao = descricao;
        this.status = status;
        this.tipotratamento = tipotratamento;
        this.consulta = consulta;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public com.example.clinicadesktop.models.Tipotratamento getTipotratamento() {
        return tipotratamento;
    }

    public void setTipotratamento(com.example.clinicadesktop.models.Tipotratamento tipotratamento) {
        this.tipotratamento = tipotratamento;
    }

    public com.example.clinicadesktop.models.Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(com.example.clinicadesktop.models.Consulta consulta) {
        this.consulta = consulta;
    }
}
