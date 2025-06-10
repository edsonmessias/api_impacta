package com.impacta.trab1.api.controller;

import com.impacta.trab1.api.entity.Componente;
import com.impacta.trab1.api.services.ComponenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/peca/{codigo}/componente")
public class ComponenteController {

    @Autowired
    private ComponenteService componenteService;

    @PostMapping
    public ResponseEntity<Componente> adicionar(@PathVariable String codigo, @RequestBody Componente comp) {
        return ResponseEntity.status(HttpStatus.CREATED).body(componenteService.adicionarComponente(codigo, comp));
    }

    @GetMapping
    public List<Componente> listarPorPeca(@PathVariable String codigo) {
        return ResponseEntity.status(HttpStatus.OK).body(componenteService.listarPorPeca(codigo)).getBody();
    }
}
