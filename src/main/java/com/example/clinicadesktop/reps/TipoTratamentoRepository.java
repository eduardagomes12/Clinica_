package com.example.clinicadesktop.reps;

import com.example.clinicadesktop.models.Tipotratamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTratamentoRepository extends JpaRepository<Tipotratamento, Long> {
}
