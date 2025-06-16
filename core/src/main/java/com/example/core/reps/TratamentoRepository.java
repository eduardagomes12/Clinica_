package com.example.core.reps;

import com.example.core.models.Tratamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TratamentoRepository extends JpaRepository<Tratamento, Long> {
}
