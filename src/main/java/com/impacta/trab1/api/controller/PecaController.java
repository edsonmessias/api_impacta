package com.impacta.trab1.api.controller;

import com.impacta.trab1.api.entity.Peca;
import com.impacta.trab1.api.services.PecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/peca")
public class PecaController {

    @Autowired
    private PecaService pecaService;

    @PostMapping
    public ResponseEntity<Peca> criarPeca(@RequestBody Peca peca) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pecaService.salvar(peca));
    }

    @GetMapping
    public List<Peca> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(pecaService.listarTodas()).getBody();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Peca> buscarPorCodigo(@PathVariable Long codigo) {
        return pecaService.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

