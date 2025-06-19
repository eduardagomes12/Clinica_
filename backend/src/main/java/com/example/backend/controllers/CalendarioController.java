package com.example.backend.controllers;

import com.example.backend.DTO.ConsultaDTO;
import com.example.core.models.Consulta;
import com.example.core.services.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendario")
public class CalendarioController {

    private final ConsultaService consultaService;

    public CalendarioController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping("/consultas/{clienteId}")
    public ResponseEntity<List<ConsultaDTO>> getConsultasDoCliente(@PathVariable Long clienteId) {
        List<ConsultaDTO> dtos = consultaService.findByClienteId(clienteId).stream()
                .map(this::toDTO)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    private ConsultaDTO toDTO(Consulta c) {
        ConsultaDTO dto = new ConsultaDTO();
        dto.setId(c.getId());
        dto.setData(c.getData());
        dto.setHora(c.getHora());
        dto.setMotivo(c.getMotivo());
        dto.setDiagnostico(c.getDiagnostico());
        dto.setVeterinarioResponsavel(c.getVeterinarioResponsavel());
        dto.setIdAnimal(c.getAnimal().getId());
        dto.setNomeAnimal(c.getAnimal().getNome());
        dto.setIdUtilizador(c.getUtilizador().getId());
        return dto;
    }
}
