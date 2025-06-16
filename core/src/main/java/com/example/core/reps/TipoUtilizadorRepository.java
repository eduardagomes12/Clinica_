package com.example.core.reps;

import com.example.core.models.TipoUtilizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TipoUtilizadorRepository extends JpaRepository<TipoUtilizador, Long> {
}
