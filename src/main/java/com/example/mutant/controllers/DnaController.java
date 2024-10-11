package com.example.mutant.controllers;

import com.example.mutant.dto.RequestDna;
import com.example.mutant.dto.ResponseDna;
import com.example.mutant.dto.ResponseError;
import com.example.mutant.services.DnaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mutant")
// Recibe el request del DTO, lo comunica con el Service y por ultimo envia el response con el formato del DTO
public class DnaController {
    private final DnaService dnaService;

    public DnaController(DnaService dnaService) {
        this.dnaService = dnaService;
    }

    @PostMapping
    public ResponseEntity<?> checkMutant(@RequestBody RequestDna requestDna) {
        try {
            String[] validDna = dnaService.dnaValidator(requestDna.getDna()); // Valido el dna, si el dna ingresado no es valido salta al bloque catch
            boolean isMutant = dnaService.persistDna(validDna); // Verifica si el dna es mutante o no, lo persiste y lo guarda en una variable booleana
            ResponseDna responseDna = ResponseDna.builder().isMutant(isMutant).dna(validDna).build(); // Genera la respuesta con el formato del DTO
            if (isMutant) { // Si es mutante entra en este bloque
                return ResponseEntity.ok(responseDna); // Retorna la respuesta con un HTTP 200-OK
            } else { // Si no es mutante entra en este bloque
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseDna); // Retorna la respuesta con un HTTP 403-FORBIDDEN
            }
        } catch (Exception e) { // Si el dna no es valido entra en este bloque
            ResponseError responseError = ResponseError.builder().errorMessage(e.getMessage()).errorClass(e.getClass().getSimpleName()).build(); // Genera la respuesta del error con el formato del DTO
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError); // Retorna la respuesta con un HTTP 400-BAD_REQUEST
        }
    }
}
