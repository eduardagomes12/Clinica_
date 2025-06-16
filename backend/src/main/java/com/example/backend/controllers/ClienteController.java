package com.example.backend.controllers;

import com.example.backend.DTO.ClienteDTO;
import com.example.core.models.Cliente;
import com.example.core.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todos os clientes")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAll() {
        List<ClienteDTO> dtos = service.findAll().stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Buscar cliente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Criar cliente")
    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO dto) {
        Cliente cliente = fromDTO(dto);
        Cliente saved = service.save(cliente);
        return ResponseEntity.ok(toDTO(saved));
    }

    @Operation(summary = "Atualizar cliente")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        Cliente dados = fromDTO(dto);
        Cliente updated = service.update(id, dados);
        return ResponseEntity.ok(toDTO(updated));
    }

    @Operation(summary = "Remover cliente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Conversão entre Cliente <-> ClienteDTO
    private ClienteDTO toDTO(Cliente c) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(c.getId());
        dto.setNome(c.getNome());
        dto.setContacto(c.getContacto());
        dto.setMorada(c.getMorada());
        dto.setEmail(c.getEmail());
        return dto;
    }

    private Cliente fromDTO(ClienteDTO dto) {
        Cliente c = new Cliente();
        c.setId(dto.getId());
        c.setNome(dto.getNome());
        c.setContacto(dto.getContacto());
        c.setMorada(dto.getMorada());
        c.setEmail(dto.getEmail());
        return c;
    }
}
