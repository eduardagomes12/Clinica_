package com.example.core.reps;

import com.example.core.models.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {

    // Verifica se já existe um utilizador com este email (usado no registo)
    Optional<Utilizador> findByEmail(String email);

    // Login com validação de email, senha e tipo de utilizador
    Utilizador findByEmailAndSenhaAndTipoUtilizador_Nome(String email, String senha, String nomeTipoUtilizador);

    // (Opcional) Apenas login com email e senha, sem tipo
    Optional<Utilizador> findByEmailAndSenha(String email, String senha);
}
