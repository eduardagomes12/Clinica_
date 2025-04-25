package com.example.clinicadesktop.reps;

import com.example.clinicadesktop.models.Consultaproduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaProdutoRepository extends JpaRepository<Consultaproduto, Long> {
}
