package com.example.backend.services;

import com.example.backend.DTO.ConsultaDTO;
import com.example.core.models.Animal;
import com.example.core.models.Consulta;
import com.example.core.models.Utilizador;
import com.example.core.reps.AnimalRepository;
import com.example.core.reps.ConsultaRepository;
import com.example.core.reps.UtilizadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final AnimalRepository animalRepository;
    private final UtilizadorRepository utilizadorRepository;

    public ConsultaService(ConsultaRepository consultaRepository, AnimalRepository animalRepository, UtilizadorRepository utilizadorRepository) {
        this.consultaRepository = consultaRepository;
        this.animalRepository = animalRepository;
        this.utilizadorRepository = utilizadorRepository;
    }

    public List<ConsultaDTO> findAll() {
        return consultaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ConsultaDTO findById(Long id) {
        return consultaRepository.findById(id).map(this::toDTO).orElse(null);
    }

    public ConsultaDTO save(ConsultaDTO dto) {
        Consulta consulta = toEntity(dto);
        return toDTO(consultaRepository.save(consulta));
    }

    public ConsultaDTO update(Long id, ConsultaDTO dto) {
        Consulta consulta = consultaRepository.findById(id).orElseThrow();
        consulta.setData(dto.getData());
        consulta.setMotivo(dto.getMotivo());
        consulta.setDiagnostico(dto.getDiagnostico());
        consulta.setVeterinarioResponsavel(dto.getVeterinarioResponsavel());

        consulta.setAnimal(animalRepository.findById(dto.getIdAnimal()).orElseThrow());
        consulta.setUtilizador(utilizadorRepository.findById(dto.getIdUtilizador()).orElseThrow());

        return toDTO(consultaRepository.save(consulta));
    }

    public void deleteById(Long id) {
        consultaRepository.deleteById(id);
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
        return dto;
    }

    private Consulta toEntity(ConsultaDTO dto) {
        Consulta c = new Consulta();
        if (dto.getId() != null) c.setId(dto.getId());
        c.setData(dto.getData());
        c.setMotivo(dto.getMotivo());
        c.setDiagnostico(dto.getDiagnostico());
        c.setVeterinarioResponsavel(dto.getVeterinarioResponsavel());

        Animal animal = animalRepository.findById(dto.getIdAnimal()).orElseThrow();
        Utilizador utilizador = utilizadorRepository.findById(dto.getIdUtilizador()).orElseThrow();

        c.setAnimal(animal);
        c.setUtilizador(utilizador);
        return c;
    }
}
