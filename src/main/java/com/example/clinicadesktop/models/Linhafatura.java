package com.example.clinicadesktop.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Table(name = "linhafatura")
public class Linhafatura {
    @Id
    @ColumnDefault("nextval('linhafatura_id_seq')")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "descricao", nullable = false, length = Integer.MAX_VALUE)
    private String descricao;

    @NotNull
    @Column(name = "preco_unit", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoUnit;

    @NotNull
    @Column(name = "qtd", nullable = false)
    private Integer qtd;

    @NotNull
    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_fatura", nullable = false)
    private com.example.clinicadesktop.models.Fatura idFatura;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(BigDecimal precoUnit) {
        this.precoUnit = precoUnit;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public com.example.clinicadesktop.models.Fatura getIdFatura() {
        return idFatura;
    }

    public void setIdFatura(com.example.clinicadesktop.models.Fatura idFatura) {
        this.idFatura = idFatura;
    }

}