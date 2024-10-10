package com.example.mutant.services;

import com.example.mutant.entities.Dna;
import com.example.mutant.repositories.DnaRepository;
import org.springframework.stereotype.Service;

@Service
// Recibe lo enviado desde el Controller, desarrolla la "logica de negocio" y lo comunica con el Repository
public class DnaService {
    private final DnaRepository dnaRepository;
    private static final String VALID_CHAR = "ACGT";

    public DnaService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    // Metodo para verificar si es mutante o humano
    public static boolean isMutant(String[] dna) {
        int n = dna.length;
        int sequenceCount = 0;

        // Recorremos el array para buscar secuencias
        for (int row = 0; row < n; row++) { // Obtenemos el indice de la fila
            for (int col = 0; col < n; col++) { // Obtenemos el indice de la columna

                sequenceCount += checkRow(dna, row, col); // Chequea la fila del indice 'row', 'col' y si encuentra una secuencia suma 1 al contador, si no encuentra ninguna secuencia suma 0
                if (sequenceCount > 1) return true; // Si en el contador ya hay mas de una secuencia retorna true

                sequenceCount += checkCol(dna, row, col); // Chequea la columna del indice 'row', 'col' y si encuentra una secuencia suma 1 al contador, si no encuentra ninguna secuencia suma 0
                if (sequenceCount > 1) return true; // Si en el contador ya hay mas de una secuencia retorna true

                sequenceCount += checkDiag(dna, row, col); // Chequea la diagonal del indice 'row', 'col' y si encuentra una secuencia suma 1 al contador, si no encuentra ninguna secuencia suma 0
                if (sequenceCount > 1) return true; // Si en el contador ya hay mas de una secuencia retorna true

                sequenceCount += checkInvDiag(dna, row, col); // Chequea la diagonal inversa del indice 'row', 'col' y si encuentra una secuencia suma 1 al contador, si no encuentra ninguna secuencia suma 0
                if (sequenceCount > 1) return true; // Si en el contador ya hay mas de una secuencia retorna true
            }
        }
        return false; // Si no se encontraron mas de una secuencia retorna false
    }

    // Verifica secuencia horizontal
    public static int checkRow(String[] dna, int row, int col) {
        if (col + 3 < dna.length) {
            char currentChar = dna[row].charAt(col); // Extraemos el caracter que se encuentra en el indice 'row' y 'col'
            if (currentChar == dna[row].charAt(col + 1) &&
                    currentChar == dna[row].charAt(col + 2) &&
                    currentChar == dna[row].charAt(col + 3)) return 1; // Si los siguientes 3 caracteres en posicion horizontal son iguales al cual nos encontramos posicionados retorna 1
        }
        return 0; // Retorna 0 si al hacer col + 3 es mayor a la longitud del array ya que nos saldriamos de los limites o si no encontro ninguna secuencia
    }

    // Verifica secuencia vertical
    public static int checkCol(String[] dna, int row, int col) {
        if (row + 3 < dna.length) {
            char currentChar = dna[row].charAt(col); // Extraemos el caracter que se encuentra en el indice 'row' y 'col'
            if (currentChar == dna[row + 1].charAt(col) &&
                    currentChar == dna[row + 2].charAt(col) &&
                    currentChar == dna[row + 3].charAt(col)) return 1; // Si los siguientes 3 caracteres en posicion vertical son iguales al cual nos encontramos posicionados retorna 1
        }
        return 0; // Retorna 0 si al hacer row + 3 es mayor o igual a la longitud del array ya que nos saldriamos de los limites o si no encontro ninguna secuencia
    }

    // Verifica secuencia diagonal (de izquierda a derecha, hacia abajo)
    public static int checkDiag(String[] dna, int row, int col) {
        if (row + 3 < dna.length && col + 3 < dna.length) {
            char currentChar = dna[row].charAt(col); // Extraemos el caracter que se encuentra en el indice 'row' y 'col'
            if (currentChar == dna[row + 1].charAt(col + 1) &&
                    currentChar == dna[row + 2].charAt(col + 2) &&
                    currentChar == dna[row + 3].charAt(col + 3)) return 1; // Si los siguientes 3 caracteres en posicion diagonal son iguales al cual nos encontramos posicionados retorna 1
        }
        return 0; // Retorna 0 si al hacer row + 3 y col + 3 es mayor o igual a la longitud del array ya que nos saldriamos de los limites o si no encontro ninguna secuencia
    }

    // Verifica secuencia diagonal inversa (de derecha a izquierda, hacia abajo)
    public static int checkInvDiag(String[] dna, int row, int col) {
        if (row + 3 < dna.length && col - 3 >= 0) {
            char currentChar = dna[row].charAt(col); // Extraemos el caracter que se encuentra en el indice 'row' y 'col'
            if (currentChar == dna[row + 1].charAt(col - 1) &&
                    currentChar == dna[row + 2].charAt(col - 2) &&
                    currentChar == dna[row + 3].charAt(col - 3)) return 1; // Si los siguientes 3 caracteres en posicion diagonal son iguales al cual nos encontramos posicionados retorna 1
        }
        return 0; // Retorna 0 si al hacer row + 3 es mayor o igual a la longitud del array y col - 3 menor a 0 ya que nos saldriamos de los limites o si no encontro ninguna secuencia
    }

    // Persistir DNA
    public boolean persistDna(String[] dna) {
        String dnaString = String.join(",", dna); // Casteamos el array a un string
        boolean isMutant = isMutant(dna); // Verificamos si es mutante o humano
        Dna dnaEntity = Dna.builder()
                .dna(dnaString)
                .isMutant(isMutant)
                .build(); // Creamos la entidad DNA
        dnaRepository.save(dnaEntity); // Persistimos la entidad que creamos usando el Repository
        return isMutant(dna); // Retornamos si es humano o no al Controller
    }

    // Verificar si el DNA que ingresaron es valido
    public String[] dnaValidator(String[] dna) throws Exception {
        // Si el array es null
        if (dna == null) throw new Exception("El array no puede ser null.");
        // Si el array es vacio
        if (dna.length == 0) throw new Exception("El array no puede ser vacio.");
        // Validar si el array no es de NxN
        for (String row : dna) {
            if (row == null || row.length() != dna.length) {
                throw new Exception("El array tiene una fila null o no es una matriz de NxN.");
            }
            for (char letter : row.toCharArray()) {
                if (VALID_CHAR.indexOf(letter) == -1) {
                    throw new Exception("El array contiene caracteres no validos.");
                }
            }
        }
        return dna; // Si el DNA es valido lo retornamos
    }
}
