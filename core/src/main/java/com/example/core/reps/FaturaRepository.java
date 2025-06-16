package com.example.core.reps;

import com.example.core.models.Fatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FaturaRepository extends JpaRepository<Fatura, Long> {
}
