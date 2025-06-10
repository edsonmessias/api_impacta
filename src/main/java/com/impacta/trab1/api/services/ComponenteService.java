package com.impacta.trab1.api.services;

import com.impacta.trab1.api.entity.Componente;
import com.impacta.trab1.api.entity.Peca;
import com.impacta.trab1.api.repository.ComponenteRepository;
import com.impacta.trab1.api.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponenteService {
    @Autowired
    private ComponenteRepository compRepo;

    @Autowired
    private PecaRepository pecaRepo;

    public Componente adicionarComponente(String codigoPeca, Componente comp) {
        Peca peca = pecaRepo.findByCodigo(codigoPeca)
                .orElseThrow(() -> new RuntimeException("Peça não encontrada"));
        comp.setPeca(peca);
        return compRepo.save(comp);
    }

    public List<Componente> listarPorPeca(String codigoPeca) {
        return compRepo.findByPecaCodigo(codigoPeca);
    }
}


