package com.example.mutant.services;

import com.example.mutant.dto.ResponseStats;
import com.example.mutant.repositories.DnaRepository;
import org.springframework.stereotype.Service;

@Service
// Recibe lo enviado desde el Controller, desarrolla la "logica de negocio" y lo comunica con el Repository
public class StatsService {
    private final DnaRepository dnaRepository;

    public StatsService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public ResponseStats getStats() {
        long countHumans = dnaRepository.countByIsMutant(false); // Trae cuantos DNA no mutantes se han ingresado
        long countMutants = dnaRepository.countByIsMutant(true); // Trae cuantos DNA mutantes se han ingresado
        double ratio = countHumans != 0 ? (double) countMutants / countHumans : 0; // Si no se han ingresado humanos retorna 0, si se han ingresado humanos divide la cantidad de mutantes por la cantidad de humanos
        return ResponseStats.builder().countHumanDna(countHumans).countMutantDna(countMutants).ratio(ratio).build(); // Genera una respuesta con el formato del DTO y lo retorna al Controller
    }
}
