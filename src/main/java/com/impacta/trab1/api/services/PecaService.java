package com.impacta.trab1.api.services;

import com.impacta.trab1.api.entity.Peca;
import com.impacta.trab1.api.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PecaService {
    @Autowired
    private PecaRepository pecaRepo;

    public Peca salvar(Peca peca) {
        return pecaRepo.save(peca);
    }

    public List<Peca> listarTodas() {
        return pecaRepo.findAll();
    }

    public Optional<Peca> buscarPorCodigo(Long codigo) {
        return pecaRepo.findByCodigo(codigo);
    }
}

