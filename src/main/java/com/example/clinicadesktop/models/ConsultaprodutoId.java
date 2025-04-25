package com.example.clinicadesktop.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class ConsultaprodutoId implements java.io.Serializable {
    private static final long serialVersionUID = 1669829950583192565L;
    @NotNull
    @Column(name = "id_consulta", nullable = false)
    private Long idConsulta;

    @NotNull
    @Column(name = "id_produto", nullable = false)
    private Long idProduto;

    public Long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ConsultaprodutoId entity = (ConsultaprodutoId) o;
        return Objects.equals(this.idProduto, entity.idProduto) &&
                Objects.equals(this.idConsulta, entity.idConsulta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduto, idConsulta);
    }

}