package com.example.core.models;

import java.io.Serializable;
import java.util.Objects;

public class ConsultaProdutoId implements Serializable {
    private Long consulta;
    private Long produto;

    public ConsultaProdutoId() {}

    public ConsultaProdutoId(Long consulta, Long produto) {
        this.consulta = consulta;
        this.produto = produto;
    }

    // equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConsultaProdutoId)) return false;
        ConsultaProdutoId that = (ConsultaProdutoId) o;
        return Objects.equals(consulta, that.consulta) && Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consulta, produto);
    }
}
