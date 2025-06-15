package com.example.backend.DTO;

import java.time.LocalDate;

public class TratamentoDTO {
    private Long id;
    private String tipo;
    private LocalDate data;
    private String descricao;
    private String status;
    private Long idTipoTratamento;
    private Long idConsulta;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getIdTipoTratamento() { return idTipoTratamento; }
    public void setIdTipoTratamento(Long idTipoTratamento) { this.idTipoTratamento = idTipoTratamento; }

    public Long getIdConsulta() { return idConsulta; }
    public void setIdConsulta(Long idConsulta) { this.idConsulta = idConsulta; }
}
