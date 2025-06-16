package com.example.core.reps;

import com.example.core.models.TipoTratamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TipoTratamentoRepository extends JpaRepository<TipoTratamento, Long> {
}
