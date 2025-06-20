package com.example.backend.controllers;

import com.example.backend.DTO.ConsultaDTO;
import com.example.core.models.Consulta;
import com.example.core.services.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import org.springframework.format.annotation.DateTimeFormat;
import com.example.core.models.Utilizador;
import com.example.core.reps.UtilizadorRepository;


import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    private final ConsultaService service;

    private final UtilizadorRepository utilizadorRepository;

    public ConsultaController(ConsultaService service, UtilizadorRepository utilizadorRepository) {
        this.service = service;
        this.utilizadorRepository = utilizadorRepository;
    }


    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> getAll() {
        List<ConsultaDTO> dtos = service.findAll().stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ConsultaDTO> create(@RequestBody ConsultaDTO dto) {
        Consulta consulta = fromDTO(dto);
        Consulta saved = service.save(consulta);
        return ResponseEntity.ok(toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDTO> update(@PathVariable Long id, @RequestBody ConsultaDTO dto) {
        Consulta consulta = fromDTO(dto);
        Consulta updated = service.update(id, consulta);
        return ResponseEntity.ok(toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ConsultaDTO toDTO(Consulta c) {
        ConsultaDTO dto = new ConsultaDTO();
        dto.setId(c.getId());
        dto.setData(c.getData());
        dto.setMotivo(c.getMotivo());
        dto.setDiagnostico(c.getDiagnostico());
        dto.setVeterinarioResponsavel(c.getVeterinarioResponsavel());
        dto.setIdAnimal(c.getAnimal().getId());
        dto.setIdUtilizador(c.getUtilizador().getId());

        // NOVO:
        dto.setNomeAnimal(c.getAnimal().getNome());
        dto.setHora(c.getHora());

        return dto;
    }


    private Consulta fromDTO(ConsultaDTO dto) {
        Consulta c = new Consulta();
        if (dto.getId() != null) c.setId(dto.getId());
        c.setData(dto.getData());
        c.setMotivo(dto.getMotivo());
        c.setDiagnostico(dto.getDiagnostico());
        c.setVeterinarioResponsavel(dto.getVeterinarioResponsavel());

        c.setAnimal(service.getAnimalById(dto.getIdAnimal())
                .orElseThrow(() -> new RuntimeException("Animal não encontrado")));
        c.setUtilizador(service.getUtilizadorById(dto.getIdUtilizador())
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado")));

        c.setHora(dto.getHora());


        return c;
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<ConsultaDTO>> getByCliente(@PathVariable Long clienteId) {
        List<ConsultaDTO> consultas = service.findByClienteId(clienteId).stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/cliente/{clienteId}/proxima")
    public ResponseEntity<ConsultaDTO> getProximaConsulta(@PathVariable Long clienteId) {
        return service.findProximaConsultaByClienteId(clienteId)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }


    @GetMapping("/cliente/{clienteId}/ultima")
    public ResponseEntity<ConsultaDTO> getUltimaConsulta(@PathVariable Long clienteId) {
        return service.findUltimaConsultaByClienteId(clienteId)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/disponivel")
    public ResponseEntity<Boolean> verificarDisponibilidade(
            @RequestParam Long veterinarioId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime hora) {

        Optional<Utilizador> vet = utilizadorRepository.findById(veterinarioId);
        if (vet.isEmpty()) {
            return ResponseEntity.badRequest().body(false);
        }

        String nomeVet = vet.get().getNome();

        boolean disponivel = service.estaDisponivel(nomeVet, data, hora);
        return ResponseEntity.ok(disponivel);
    }




}
