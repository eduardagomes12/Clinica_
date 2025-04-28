package com.example.clinicadesktop.services;

import com.example.clinicadesktop.models.Animal;
import com.example.clinicadesktop.reps.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository repository;

    public List<Animal> findAll() {
        return repository.findAllWithCliente();
    }

    public Animal findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Animal save(Animal animal) {
        return repository.save(animal);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
