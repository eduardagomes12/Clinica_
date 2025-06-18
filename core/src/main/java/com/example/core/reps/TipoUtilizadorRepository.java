package com.example.core.reps;

import com.example.core.models.TipoUtilizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoUtilizadorRepository extends JpaRepository<TipoUtilizador, Long> {

    // Permite buscar um tipo pelo nome, ex: "Cliente"
    Optional<TipoUtilizador> findByNome(String nome);
}
