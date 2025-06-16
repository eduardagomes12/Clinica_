package com.example.core.reps;

import com.example.core.models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
