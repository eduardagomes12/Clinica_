package com.example.backend.controllers;

import com.example.backend.DTO.EisDTO;
import com.example.core.models.Eis;
import com.example.core.services.EisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/eis")
public class EisController {

    private final EisService service;

    public EisController(EisService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EisDTO>> getAll() {
        List<EisDTO> dtos = service.findAll().stream().map(this::toDTO).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EisDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EisDTO> create(@RequestBody EisDTO dto) {
        try {
            Eis entity = fromDTO(dto);
            Eis saved = service.save(entity, dto.getIdProduto());
            return ResponseEntity.ok(toDTO(saved));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EisDTO dto) {
        try {
            Eis updated = service.update(id, fromDTO(dto), dto.getIdProduto());
            return ResponseEntity.ok(toDTO(updated));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar EIS: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // DTO Conversion
    private EisDTO toDTO(Eis e) {
        EisDTO dto = new EisDTO();
        dto.setId(e.getId());
        dto.setMovimento(e.getMovimento());
        dto.setQtd(e.getQtd());
        dto.setData(e.getData());
        dto.setIdProduto(e.getProduto().getId());
        return dto;
    }

    private Eis fromDTO(EisDTO dto) {
        Eis e = new Eis();
        e.setId(dto.getId());
        e.setMovimento(dto.getMovimento());
        e.setQtd(dto.getQtd());
        e.setData(dto.getData());
        return e;
    }
}
