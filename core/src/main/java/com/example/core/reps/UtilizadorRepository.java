package com.example.core.reps;

import com.example.core.models.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {
    boolean existsByEmail(String email);
}
