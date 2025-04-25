package com.example.clinicadesktop.services;

import com.example.clinicadesktop.models.Eis;
import com.example.clinicadesktop.reps.EisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EisService {

    @Autowired
    private EisRepository repository;

    public List<Eis> findAll() {
        return repository.findAll();
    }

    public Eis findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Eis save(Eis ei) {
        return repository.save(ei);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
