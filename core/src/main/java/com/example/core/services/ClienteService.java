package com.example.core.services;

import com.example.core.models.Cliente;
import com.example.core.reps.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        return repository.findById(id);
    }

    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente update(Long id, Cliente dados) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

        cliente.setNome(dados.getNome());
        cliente.setContacto(dados.getContacto());
        cliente.setMorada(dados.getMorada());
        cliente.setEmail(dados.getEmail());

        return repository.save(cliente);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
