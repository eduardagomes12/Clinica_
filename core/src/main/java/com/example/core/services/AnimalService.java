package com.example.core.services;

import com.example.core.models.Animal;
import com.example.core.models.Cliente;
import com.example.core.reps.AnimalRepository;
import com.example.core.reps.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository animalRepo;
    private final ClienteRepository clienteRepo;

    public AnimalService(AnimalRepository animalRepo, ClienteRepository clienteRepo) {
        this.animalRepo = animalRepo;
        this.clienteRepo = clienteRepo;
    }

    public List<Animal> findAll() {
        return animalRepo.findAll();
    }

    public Optional<Animal> findById(Long id) {
        return animalRepo.findById(id);
    }

    public Animal save(Animal animal) {
        return animalRepo.save(animal);
    }

    public boolean delete(Long id) {
        if (!animalRepo.existsById(id)) return false;
        animalRepo.deleteById(id);
        return true;
    }

    public Optional<Cliente> findClienteById(Long id) {
        return clienteRepo.findById(id);
    }
}
