package com.example.core.services;

import com.example.core.models.TipoUtilizador;
import com.example.core.models.Utilizador;
import com.example.core.reps.TipoUtilizadorRepository;
import com.example.core.reps.UtilizadorRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;


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

    public List<Utilizador> findAll() {
        return utilizadorRepository.findAll();
    }

    public Optional<Utilizador> findById(Long id) {
        return utilizadorRepository.findById(id);
    }

    public Utilizador save(Utilizador utilizador) {
        return utilizadorRepository.save(utilizador);
    }

    public Utilizador update(Long id, Utilizador dadosAtualizados) {
        Utilizador utilizador = utilizadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado com ID: " + id));

        utilizador.setNome(dadosAtualizados.getNome());
        utilizador.setEmail(dadosAtualizados.getEmail());
        utilizador.setSenha(dadosAtualizados.getSenha());
        utilizador.setTipoUtilizador(dadosAtualizados.getTipoUtilizador());

        return utilizadorRepository.save(utilizador);
    }

    public void deleteById(Long id) {
        utilizadorRepository.deleteById(id);
    }

    public TipoUtilizador getTipoUtilizadorById(Long id) {
        return tipoUtilizadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TipoUtilizador não encontrado com ID: " + id));
    }

    public Utilizador guardar(Utilizador utilizador) {
        return utilizadorRepository.save(utilizador);
    }

    // Procura utilizador pelo email (para login)
    public Optional<Utilizador> encontrarPorEmail(String email) {
        return utilizadorRepository.findByEmail(email);
    }

    public List<Utilizador> findVeterinarios() {
        return utilizadorRepository.findAll().stream()
                .filter(u -> u.getTipoUtilizador() != null &&
                        u.getTipoUtilizador().getNome() != null &&
                        u.getTipoUtilizador().getNome().equalsIgnoreCase("Veterinário"))
                .collect(Collectors.toList());
    }


}
