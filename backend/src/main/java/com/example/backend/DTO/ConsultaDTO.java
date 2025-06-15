package com.example.backend.DTO;

import java.time.LocalDate;

public class ConsultaDTO {
    private Long id;
    private LocalDate data;
    private String motivo;
    private String diagnostico;
    private String veterinarioResponsavel;
    private Long idAnimal;
    private Long idUtilizador;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getVeterinarioResponsavel() { return veterinarioResponsavel; }
    public void setVeterinarioResponsavel(String veterinarioResponsavel) { this.veterinarioResponsavel = veterinarioResponsavel; }

    public Long getIdAnimal() { return idAnimal; }
    public void setIdAnimal(Long idAnimal) { this.idAnimal = idAnimal; }

    public Long getIdUtilizador() { return idUtilizador; }
    public void setIdUtilizador(Long idUtilizador) { this.idUtilizador = idUtilizador; }
}
