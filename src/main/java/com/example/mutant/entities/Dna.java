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
public class Dna implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String dna;
    private boolean isMutant;
}
