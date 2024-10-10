package com.example.mutant.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
// Formato de respuesta del POST ingresado, lo recibe desde el Controller
public class ResponseDna {
    private String[] dna;
    private boolean isMutant;
}
