package com.example.backend.services;

import com.example.backend.DTO.UtilizadorDTO;
import com.example.core.models.TipoUtilizador;
import com.example.core.models.Utilizador;
import com.example.core.reps.TipoUtilizadorRepository;
import com.example.core.reps.UtilizadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UtilizadorService {

    private final UtilizadorRepository utilizadorRepository;
    private final TipoUtilizadorRepository tipoUtilizadorRepository;

    public UtilizadorService(UtilizadorRepository utilizadorRepository, TipoUtilizadorRepository tipoUtilizadorRepository) {
        this.utilizadorRepository = utilizadorRepository;
        this.tipoUtilizadorRepository = tipoUtilizadorRepository;
    }

    public List<UtilizadorDTO> findAll() {
        return utilizadorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<UtilizadorDTO> findById(Long id) {
        return utilizadorRepository.findById(id)
                .map(this::toDTO);
    }

    public UtilizadorDTO save(UtilizadorDTO dto) {
        TipoUtilizador tipo = tipoUtilizadorRepository.findById(dto.getIdTipoUtilizador())
                .orElseThrow(() -> new RuntimeException("TipoUtilizador não encontrado"));

        Utilizador utilizador = new Utilizador();
        utilizador.setNome(dto.getNome());
        utilizador.setEmail(dto.getEmail());
        utilizador.setSenha(dto.getSenha());
        utilizador.setTipoUtilizador(tipo);

        return toDTO(utilizadorRepository.save(utilizador));
    }

    public UtilizadorDTO update(Long id, UtilizadorDTO dto) {
        Utilizador utilizador = utilizadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado com ID: " + id));

        utilizador.setNome(dto.getNome());
        utilizador.setEmail(dto.getEmail());
        utilizador.setSenha(dto.getSenha());

        if (dto.getIdTipoUtilizador() != null) {
            TipoUtilizador tipo = tipoUtilizadorRepository.findById(dto.getIdTipoUtilizador())
                    .orElseThrow(() -> new RuntimeException("TipoUtilizador não encontrado"));
            utilizador.setTipoUtilizador(tipo);
        }

        return toDTO(utilizadorRepository.save(utilizador));
    }

    public void deleteById(Long id) {
        utilizadorRepository.deleteById(id);
    }

    private UtilizadorDTO toDTO(Utilizador u) {
        UtilizadorDTO dto = new UtilizadorDTO();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setEmail(u.getEmail());
        dto.setSenha(u.getSenha());
        if (u.getTipoUtilizador() != null) {
            dto.setIdTipoUtilizador(u.getTipoUtilizador().getId());
            dto.setNomeTipoUtilizador(u.getTipoUtilizador().getNome());
        }
        return dto;
    }
}
