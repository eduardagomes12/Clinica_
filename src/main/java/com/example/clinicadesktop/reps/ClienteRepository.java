package com.example.clinicadesktop.reps;

import com.example.clinicadesktop.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Se precisar de queries customizadas, declare aqui.
}
