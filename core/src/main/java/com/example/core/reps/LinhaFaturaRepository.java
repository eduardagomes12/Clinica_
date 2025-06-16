package com.example.core.reps;

import com.example.core.models.LinhaFatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LinhaFaturaRepository extends JpaRepository<LinhaFatura, Long> {
}
