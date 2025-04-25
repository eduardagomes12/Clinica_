package com.example.clinicadesktop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "consultaproduto")
public class Consultaproduto {
    @EmbeddedId
    private ConsultaprodutoId id;

    @MapsId("idConsulta")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_consulta", nullable = false)
    private com.example.clinicadesktop.models.Consulta idConsulta;

    @MapsId("idProduto")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto idProduto;

    public ConsultaprodutoId getId() {
        return id;
    }

    public void setId(ConsultaprodutoId id) {
        this.id = id;
    }

    public com.example.clinicadesktop.models.Consulta getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(com.example.clinicadesktop.models.Consulta idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Produto getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Produto idProduto) {
        this.idProduto = idProduto;
    }

}