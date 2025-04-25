package com.example.clinicadesktop.reps;

import com.example.clinicadesktop.models.Eis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EisRepository extends JpaRepository<Eis, Long> {
}
