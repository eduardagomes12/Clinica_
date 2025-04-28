package com.example.clinicadesktop.reps;

import com.example.clinicadesktop.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("SELECT a FROM Animal a JOIN FETCH a.idCliente")
    List<Animal> findAllWithCliente();
}
