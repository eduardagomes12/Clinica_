package com.example.core.reps;

import com.example.core.models.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {
    Utilizador findByEmailAndSenhaAndTipoUtilizador_Nome(String email, String senha, String nomeTipoUtilizador);
}
