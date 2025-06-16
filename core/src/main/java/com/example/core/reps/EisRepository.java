package com.example.core.reps;

import com.example.core.models.Eis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EisRepository extends JpaRepository<Eis, Long> {
}
