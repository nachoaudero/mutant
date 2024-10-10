package com.example.mutant.entities;

import java.io.Serializable;
import lombok.*;
import jakarta.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
// Entidad que se persistira a la base de datos
public class Dna implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dna;
    private boolean isMutant;
}
