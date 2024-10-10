package com.example.mutant.repositories;

import com.example.mutant.entities.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Recibe lo enviado desde el Service
public interface DnaRepository extends JpaRepository<Dna, Long> {
    long countByIsMutant(boolean isMutant);
}
