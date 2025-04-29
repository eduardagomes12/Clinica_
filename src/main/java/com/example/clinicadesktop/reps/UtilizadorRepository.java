package com.example.clinicadesktop.reps;

import com.example.clinicadesktop.models.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {

    // procura pelo email, senha e nome do tipo de utilizador
    Utilizador findByEmailAndSenhaAndTipoutilizador_Nome(String email, String senha, String tipoutilizador_nome);
}
