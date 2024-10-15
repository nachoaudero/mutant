package com.example.mutant.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DnaServiceTests {
    // Test comprobando FILAS
    @Test
    public void testRows() {
        String[] dna = {
                "GGGG",
                "TTTT",
                "ACAC",
                "CCAA"
        };
        assertTrue(DnaService.isMutant(dna));
    }
    // Test comprobando COLUMNAS
    @Test
    public void testColumns() {
        String[] dna = {
                "ACTT",
                "ATTT",
                "ACTC",
                "ACTA"
        };
        assertTrue(DnaService.isMutant(dna));
    }
    // Test comprobando DIAGONAL PRINCIPAL
    @Test
    public void testMainDiagonal() {
        String[] dna = {
                "GGGG",
                "TGTT",
                "ACGC",
                "CCAG"
        };
        assertTrue(DnaService.isMutant(dna));
    }
    // Test comprobando DIAGONALES SECUNDARIAS
    @Test
    public void testSecondDiagonal() {
        String[] dna = {
                "AGGGG",
                "TTGTA",
                "ACAGA",
                "CCAAG",
                "CTTAC"
        };
        assertTrue(DnaService.isMutant(dna));
    }
    // Test comprobando DIAGONAL INVERSA
    @Test
    public void testInverceDiagonal() {
        String[] dna = {
                "GGGG",
                "TTGT",
                "AGAC",
                "GCAA"
        };
        assertTrue(DnaService.isMutant(dna));
    }
    // Test comprobando DNA HUMANO
    @Test
    public void testNoMutant() {
        String[] dna = {
                "GTTA",
                "ACCC",
                "ACAC",
                "CCAA"
        };
        assertFalse(DnaService.isMutant(dna));
    }
}
