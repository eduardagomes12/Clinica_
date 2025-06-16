package com.example.backend.controllers;

import com.example.backend.DTO.AnimalDTO;
import com.example.core.models.Animal;
import com.example.core.models.Cliente;
import com.example.core.services.AnimalService;
import com.example.core.reps.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animais")
public class AnimalController {

    private final AnimalService animalService;
    private final ClienteRepository clienteRepository;

    public AnimalController(AnimalService animalService, ClienteRepository clienteRepository) {
        this.animalService = animalService;
        this.clienteRepository = clienteRepository;
    }

    @Operation(summary = "Listar todos os animais")
    @GetMapping
    public ResponseEntity<List<AnimalDTO>> getAll() {
        List<AnimalDTO> dtos = animalService.findAll().stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Buscar animal por ID")
    @GetMapping("/{id}")
    public ResponseEntity<AnimalDTO> getById(@PathVariable Long id) {
        return animalService.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Criar novo animal")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody AnimalDTO dto) {
        try {
            Animal entity = fromDTO(dto);
            if (dto.getClienteId() != null) {
                Cliente cliente = clienteRepository.findById(dto.getClienteId())
                        .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
                entity.setCliente(cliente);
            }
            Animal saved = animalService.save(entity);
            return ResponseEntity.ok(toDTO(saved));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Atualizar animal")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AnimalDTO dto) {
        Optional<Animal> existing = animalService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Animal updated = fromDTO(dto);
        updated.setId(id);

        if (dto.getClienteId() != null) {
            Cliente cliente = clienteRepository.findById(dto.getClienteId())
                    .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
            updated.setCliente(cliente);
        }

        Animal saved = animalService.save(updated);
        return ResponseEntity.ok(toDTO(saved));
    }

    @Operation(summary = "Eliminar animal")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return animalService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // Conversões dentro do controller
    private AnimalDTO toDTO(Animal a) {
        AnimalDTO dto = new AnimalDTO();
        dto.setId(a.getId());
        dto.setNome(a.getNome());
        dto.setIdade(a.getIdade());
        dto.setEspecie(a.getEspecie());
        dto.setRaca(a.getRaca());
        dto.setHistoricoMedico(a.getHistoricoMedico());
        dto.setClienteId(a.getCliente() != null ? a.getCliente().getId() : null);
        return dto;
    }

    private Animal fromDTO(AnimalDTO dto) {
        Animal a = new Animal();
        a.setNome(dto.getNome());
        a.setIdade(dto.getIdade());
        a.setEspecie(dto.getEspecie());
        a.setRaca(dto.getRaca());
        a.setHistoricoMedico(dto.getHistoricoMedico());
        return a;
    }
}
