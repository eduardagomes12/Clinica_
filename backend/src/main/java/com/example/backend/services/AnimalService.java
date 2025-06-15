package com.example.backend.services;

import com.example.backend.DTO.AnimalDTO;
import com.example.core.models.Animal;
import com.example.core.models.Cliente;
import com.example.core.reps.AnimalRepository;
import com.example.core.reps.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalService {
    private final AnimalRepository animalRepo;
    private final ClienteRepository clienteRepo;

    public AnimalService(AnimalRepository animalRepo, ClienteRepository clienteRepo) {
        this.animalRepo = animalRepo;
        this.clienteRepo = clienteRepo;
    }

    public List<AnimalDTO> getAll() {
        return animalRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<AnimalDTO> getById(Long id) {
        return animalRepo.findById(id).map(this::toDTO);
    }

    public AnimalDTO create(AnimalDTO dto) {
        Cliente cliente = clienteRepo.findById(dto.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        Animal animal = new Animal();
        updateEntityFromDTO(animal, dto);
        animal.setCliente(cliente);
        return toDTO(animalRepo.save(animal));
    }

    public Optional<AnimalDTO> update(Long id, AnimalDTO dto) {
        return animalRepo.findById(id).map(animal -> {
            updateEntityFromDTO(animal, dto);
            if (dto.getClienteId() != null) {
                Cliente cliente = clienteRepo.findById(dto.getClienteId())
                        .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
                animal.setCliente(cliente);
            }
            return toDTO(animalRepo.save(animal));
        });
    }

    public boolean delete(Long id) {
        if (!animalRepo.existsById(id)) return false;
        animalRepo.deleteById(id);
        return true;
    }

    private AnimalDTO toDTO(Animal animal) {
        AnimalDTO dto = new AnimalDTO();
        dto.setId(animal.getId());
        dto.setNome(animal.getNome());
        dto.setIdade(animal.getIdade());
        dto.setEspecie(animal.getEspecie());
        dto.setRaca(animal.getRaca());
        dto.setHistoricoMedico(animal.getHistoricoMedico());
        dto.setClienteId(animal.getCliente() != null ? animal.getCliente().getId() : null);
        return dto;
    }

    private void updateEntityFromDTO(Animal animal, AnimalDTO dto) {
        animal.setNome(dto.getNome());
        animal.setIdade(dto.getIdade());
        animal.setEspecie(dto.getEspecie());
        animal.setRaca(dto.getRaca());
        animal.setHistoricoMedico(dto.getHistoricoMedico());
    }
}
