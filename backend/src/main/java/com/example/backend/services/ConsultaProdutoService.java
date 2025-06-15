package com.example.backend.services;

import com.example.backend.DTO.ConsultaProdutoDTO;
import com.example.core.models.Consulta;
import com.example.core.models.ConsultaProduto;
import com.example.core.models.Produto;
import com.example.core.reps.ConsultaProdutoRepository;
import com.example.core.reps.ConsultaRepository;
import com.example.core.reps.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ConsultaProdutoDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ConsultaProdutoDTO save(ConsultaProdutoDTO dto) {
        Consulta consulta = consultaRepository.findById(dto.getIdConsulta()).orElseThrow();
        Produto produto = produtoRepository.findById(dto.getIdProduto()).orElseThrow();

        ConsultaProduto cp = new ConsultaProduto();
        cp.setConsulta(consulta);
        cp.setProduto(produto);

        return toDTO(repository.save(cp));
    }

    public void delete(Long consultaId, Long produtoId) {
        repository.deleteById(new com.example.core.models.ConsultaProdutoId(consultaId, produtoId));
    }

    private ConsultaProdutoDTO toDTO(ConsultaProduto cp) {
        ConsultaProdutoDTO dto = new ConsultaProdutoDTO();
        dto.setIdConsulta(cp.getConsulta().getId());
        dto.setIdProduto(cp.getProduto().getId());
        return dto;
    }
}
