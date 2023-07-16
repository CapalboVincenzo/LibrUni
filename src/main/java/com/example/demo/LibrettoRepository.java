package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrettoRepository extends JpaRepository<Libretto, Integer> {
    boolean existsByStudenteId(Integer studenteId);
    // Altri metodi specifici se necessario
}