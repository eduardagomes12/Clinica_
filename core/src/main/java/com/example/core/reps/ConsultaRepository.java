package com.example.core.reps;

import com.example.core.models.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByAnimalClienteId(Long clienteId);
    Optional<Consulta> findTopByAnimal_Cliente_IdAndDataAfterOrderByDataAsc(Long clienteId, LocalDate data);
    Optional<Consulta> findTopByAnimal_Cliente_IdAndDataBeforeOrderByDataDesc(Long clienteId, LocalDate data);
    boolean existsByVeterinarioResponsavelAndDataAndHora(String veterinarioResponsavel, LocalDate data, LocalTime hora);



}
