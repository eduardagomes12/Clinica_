package com.example.core.reps;

import com.example.core.models.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
