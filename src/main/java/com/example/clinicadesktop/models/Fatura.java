package com.example.clinicadesktop.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "fatura")
public class Fatura {
    @Id
    @ColumnDefault("nextval('fatura_id_seq')")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "num", nullable = false)
    private Integer num;

    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @NotNull
    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_pagamento", nullable = false)
    private Tipopagamento tipoPagamento;

    @OneToMany(mappedBy = "idFatura")
    private Set<Linhafatura> linhafaturas = new LinkedHashSet<>();

    public Set<Linhafatura> getLinhafaturas() {
        return linhafaturas;
    }

    public void setLinhafaturas(Set<Linhafatura> linhafaturas) {
        this.linhafaturas = linhafaturas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Tipopagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(Tipopagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

}