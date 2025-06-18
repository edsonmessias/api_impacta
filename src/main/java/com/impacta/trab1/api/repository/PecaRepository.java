package com.impacta.trab1.api.repository;

import com.impacta.trab1.api.entity.Peca;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface PecaRepository extends JpaRepository<Peca, Long> {
    Optional<Peca> findByCodigo(Long codigo);
}
