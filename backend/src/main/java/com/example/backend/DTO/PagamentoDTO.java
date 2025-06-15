package com.example.backend.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PagamentoDTO {
    private Long id;
    private BigDecimal valor;
    private LocalDate data;
    private Long idFatura;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public Long getIdFatura() { return idFatura; }
    public void setIdFatura(Long idFatura) { this.idFatura = idFatura; }
}
