package com.example.backend.services;

import com.example.backend.DTO.EisDTO;
import com.example.core.models.Eis;
import com.example.core.models.Produto;
import com.example.core.reps.EisRepository;
import com.example.core.reps.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EisService {

    private final EisRepository eisRepository;
    private final ProdutoRepository produtoRepository;

    public EisService(EisRepository eisRepository, ProdutoRepository produtoRepository) {
        this.eisRepository = eisRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<EisDTO> findAll() {
        return eisRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public EisDTO findById(Long id) {
        return eisRepository.findById(id).map(this::toDTO).orElse(null);
    }

    public EisDTO save(EisDTO dto) {
        Produto produto = produtoRepository.findById(dto.getIdProduto())
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));
        Eis eis = toEntity(dto);
        eis.setProduto(produto);
        return toDTO(eisRepository.save(eis));
    }

    public EisDTO update(Long id, EisDTO dto) {
        Eis eis = eisRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("EIS com id " + id + " não encontrado"));
        Produto produto = produtoRepository.findById(dto.getIdProduto())
                .orElseThrow(() -> new NoSuchElementException("Produto com id " + dto.getIdProduto() + " não encontrado"));

        eis.setMovimento(dto.getMovimento());
        eis.setQtd(dto.getQtd());
        eis.setData(dto.getData());
        eis.setProduto(produto);

        return toDTO(eisRepository.save(eis));
    }

    public void deleteById(Long id) {
        eisRepository.deleteById(id);
    }

    // Conversões
    private EisDTO toDTO(Eis e) {
        EisDTO dto = new EisDTO();
        dto.setId(e.getId());
        dto.setMovimento(e.getMovimento());
        dto.setQtd(e.getQtd());
        dto.setData(e.getData());
        dto.setIdProduto(e.getProduto().getId());
        return dto;
    }

    private Eis toEntity(EisDTO dto) {
        Eis e = new Eis();
        e.setId(dto.getId());
        e.setMovimento(dto.getMovimento());
        e.setQtd(dto.getQtd());
        e.setData(dto.getData());
        return e;
    }
}
