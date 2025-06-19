package com.example.backend.controllers;

import com.example.core.models.Consulta;
import com.example.core.reps.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/historico")
@CrossOrigin(origins = "*")
public class HistoricoController {

    @Autowired
    private ConsultaRepository consultaRepository;

    public static class HistoricoDTO {
        public String nomeAnimal;
        public String tipo;
        public String diagnostico;
        public String data;
        public String hora;
        public String veterinario;
        public Long idAnimal;


        public HistoricoDTO(Consulta consulta) {
            this.idAnimal = consulta.getAnimal().getId(); // 🔽 aqui está a chave para o filtro funcionar
            this.nomeAnimal = consulta.getAnimal().getNome();
            this.tipo = consulta.getMotivo(); // Ex: "Vacinação", "Check-up"
            this.diagnostico = consulta.getDiagnostico() != null ? consulta.getDiagnostico() : "-";
            this.data = consulta.getData().toString();
            this.hora = consulta.getHora().toString();
            this.veterinario = consulta.getVeterinarioResponsavel();
        }

    }

    @GetMapping("/{idCliente}")
    public List<HistoricoDTO> getHistoricoByCliente(@PathVariable Long idCliente) {
        LocalDateTime agora = LocalDateTime.now();

        List<Consulta> consultas = consultaRepository.findByAnimalClienteId(idCliente);

        return consultas.stream()
                .filter(c -> c.getData().atTime(c.getHora()).isBefore(agora)) // Somente passadas
                .map(HistoricoDTO::new)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable Long id) {
        if (consultaRepository.existsById(id)) {
            consultaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
