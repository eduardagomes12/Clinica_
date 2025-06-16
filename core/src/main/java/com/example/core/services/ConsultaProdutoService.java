package com.example.core.services;

import com.example.core.models.Consulta;
import com.example.core.models.ConsultaProduto;
import com.example.core.models.ConsultaProdutoId;
import com.example.core.models.Produto;
import com.example.core.reps.ConsultaProdutoRepository;
import com.example.core.reps.ConsultaRepository;
import com.example.core.reps.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaProdutoService {

    private final ConsultaProdutoRepository repository;
    private final ConsultaRepository consultaRepository;
    private final ProdutoRepository produtoRepository;

    public ConsultaProdutoService(ConsultaProdutoRepository repository, ConsultaRepository consultaRepository, ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.consultaRepository = consultaRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<ConsultaProduto> findAll() {
        return repository.findAll();
    }

    public Optional<ConsultaProduto> save(Long consultaId, Long produtoId) {
        Consulta consulta = consultaRepository.findById(consultaId).orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        Produto produto = produtoRepository.findById(produtoId).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        ConsultaProduto cp = new ConsultaProduto();
        cp.setConsulta(consulta);
        cp.setProduto(produto);

        return Optional.of(repository.save(cp));
    }

    public void delete(Long consultaId, Long produtoId) {
        repository.deleteById(new ConsultaProdutoId(consultaId, produtoId));
    }
}
