package com.example.mutant.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
// Formato de lo ingresado a travez de POST, se comunica con el Controller
public class RequestDna {
    private String[] dna;
}
