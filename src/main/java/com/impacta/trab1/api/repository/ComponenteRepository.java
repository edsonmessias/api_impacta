package com.impacta.trab1.api.repository;


import com.impacta.trab1.api.entity.Componente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ComponenteRepository extends JpaRepository<Componente, Long> {
    List<Componente> findByPecaCodigo(Long codigo);
}
