package com.example.core.reps;

import com.example.core.models.ConsultaProduto;
import com.example.core.models.ConsultaProdutoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ConsultaProdutoRepository extends JpaRepository<ConsultaProduto, ConsultaProdutoId> {
}
