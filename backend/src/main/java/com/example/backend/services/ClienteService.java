package com.example.backend.services;

import com.example.backend.DTO.ClienteDTO;
import com.example.core.models.Cliente;
import com.example.core.reps.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<ClienteDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<ClienteDTO> findById(Long id) {
        return repository.findById(id).map(this::toDTO);
    }

    public ClienteDTO save(ClienteDTO dto) {
        Cliente cliente = toEntity(dto);
        return toDTO(repository.save(cliente));
    }

    public ClienteDTO update(Long id, ClienteDTO dto) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

        cliente.setNome(dto.getNome());
        cliente.setContacto(dto.getContacto());
        cliente.setMorada(dto.getMorada());
        cliente.setEmail(dto.getEmail());

        return toDTO(repository.save(cliente));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // Conversão
    private ClienteDTO toDTO(Cliente c) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(c.getId());
        dto.setNome(c.getNome());
        dto.setContacto(c.getContacto());
        dto.setMorada(c.getMorada());
        dto.setEmail(c.getEmail());
        return dto;
    }

    private Cliente toEntity(ClienteDTO dto) {
        Cliente c = new Cliente();
        c.setId(dto.getId());
        c.setNome(dto.getNome());
        c.setContacto(dto.getContacto());
        c.setMorada(dto.getMorada());
        c.setEmail(dto.getEmail());
        return c;
    }
}
