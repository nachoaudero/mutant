package com.example.mutant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
// Formato de respuesta del GET ingresado, lo recibe desde el Controller
public class ResponseStats {
    @JsonProperty("count_mutant_dna") // Esto sirve para respetar la convencion de snake_case en los formatos JSON
    private long countMutantDna;
    @JsonProperty("count_human_dna") // Esto sirve para respetar la convencion de snake_case en los formatos JSON
    private long countHumanDna;
    private double ratio;
}
