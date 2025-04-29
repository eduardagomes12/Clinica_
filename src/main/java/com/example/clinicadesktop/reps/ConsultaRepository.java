package com.example.clinicadesktop.reps;

import com.example.clinicadesktop.models.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    @Query("SELECT c FROM Consulta c JOIN FETCH c.animal")
    List<Consulta> findAllWithAnimal();
}
