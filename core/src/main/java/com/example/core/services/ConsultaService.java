package com.example.core.services;

import com.example.core.models.Animal;
import com.example.core.models.Consulta;
import com.example.core.models.Utilizador;
import com.example.core.reps.AnimalRepository;
import com.example.core.reps.ConsultaRepository;
import com.example.core.reps.UtilizadorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

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

    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> findById(Long id) {
        return consultaRepository.findById(id);
    }

    public Consulta save(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public Consulta update(Long id, Consulta nova) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        consulta.setData(nova.getData());
        consulta.setMotivo(nova.getMotivo());
        consulta.setDiagnostico(nova.getDiagnostico());
        consulta.setVeterinarioResponsavel(nova.getVeterinarioResponsavel());
        consulta.setAnimal(nova.getAnimal());
        consulta.setUtilizador(nova.getUtilizador());

        return consultaRepository.save(consulta);
    }

    public void deleteById(Long id) {
        consultaRepository.deleteById(id);
    }

    public Optional<Animal> getAnimalById(Long id) {
        return animalRepository.findById(id);
    }

    public Optional<Utilizador> getUtilizadorById(Long id) {
        return utilizadorRepository.findById(id);
    }

    public List<Consulta> findByClienteId(Long clienteId) {
        return consultaRepository.findByAnimalClienteId(clienteId);
    }

    public Optional<Consulta> findProximaConsultaByClienteId(Long clienteId) {
        return consultaRepository.findTopByAnimal_Cliente_IdAndDataAfterOrderByDataAsc(clienteId, LocalDate.now());
    }

    public Optional<Consulta> findUltimaConsultaByClienteId(Long clienteId) {
        return consultaRepository.findTopByAnimal_Cliente_IdAndDataBeforeOrderByDataDesc(clienteId, LocalDate.now());
    }

    public boolean estaDisponivel(String nomeVeterinario, LocalDate data, LocalTime hora) {
        return !consultaRepository.existsByVeterinarioResponsavelAndDataAndHora(nomeVeterinario, data, hora);
    }

}
