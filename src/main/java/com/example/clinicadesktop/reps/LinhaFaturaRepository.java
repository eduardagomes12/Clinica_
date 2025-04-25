package com.example.clinicadesktop.reps;

import com.example.clinicadesktop.models.Linhafatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinhaFaturaRepository extends JpaRepository<Linhafatura, Long> {
}
