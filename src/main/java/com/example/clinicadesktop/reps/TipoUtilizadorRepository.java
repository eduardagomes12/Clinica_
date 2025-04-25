package com.example.clinicadesktop.reps;

import com.example.clinicadesktop.models.Tipoutilizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUtilizadorRepository extends JpaRepository<Tipoutilizador, Long> {
}
