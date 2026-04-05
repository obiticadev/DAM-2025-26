package nivel9_desafios_supremos;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * EJERCICIO 61 — PROCESAMIENTO DE MATRICES NUMÉRICAS CON STREAMS
 * 
 * SIN PISTAS. Usa IntStream y flatMap para operar sobre matrices bidimensionales.
 */
public class Ejercicio61_MatricesNumericas {

    public static void demostracion() {
        System.out.println("=== DESAFÍO SUPREMO 61: MATRICES CON STREAMS ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 61: OPERACIONES SOBRE MATRICES ---");
        int[][] matriz = {
            {3, 7, 1, 9},
            {5, 2, 8, 4},
            {6, 0, 3, 7}
        };

        // TODO 1: Aplana la matriz en una sola lista de enteros.
        List<Integer> plana = null; // <- Escribe aquí

        // TODO 2: Calcula la suma de TODOS los elementos de la matriz.
        int sumaTotal = 0; // <- Escribe aquí

        // TODO 3: Encuentra el valor máximo de la matriz.
        int maximo = 0; // <- Escribe aquí

        // TODO 4: Para cada fila, calcula su media aritmética.
        // Resultado: List<Double> con 3 medias (una por fila)
        List<Double> mediasPorFila = null; // <- Escribe aquí

        // TODO 5: Obtén los índices de columna donde al menos un valor es > 7.
        // Columna 0: max=6, Columna 1: max=7, Columna 2: max=8, Columna 3: max=9
        // Resultado: [2, 3] (columnas con algún valor > 7)
        List<Integer> columnasConGrandes = null; // <- Escribe aquí

        // TODO 6: Transpón la matriz (filas -> columnas) y aplana.
        // Original:  {{3,7,1,9},{5,2,8,4},{6,0,3,7}}
        // Transpuesta: {{3,5,6},{7,2,0},{1,8,3},{9,4,7}}
        // Aplanada: [3,5,6,7,2,0,1,8,3,9,4,7]
        List<Integer> transpuesta = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = plana != null && plana.size() == 12;
        boolean v2 = sumaTotal == 55;
        boolean v3 = maximo == 9;
        boolean v4 = mediasPorFila != null && mediasPorFila.size() == 3
                && mediasPorFila.get(0) == 5.0 && mediasPorFila.get(1) == 4.75;
        boolean v5 = columnasConGrandes != null && columnasConGrandes.size() == 2
                && columnasConGrandes.contains(2) && columnasConGrandes.contains(3);
        boolean v6 = transpuesta != null && transpuesta.size() == 12
                && transpuesta.get(0) == 3 && transpuesta.get(1) == 5 && transpuesta.get(2) == 6;

        if (v1 && v2 && v3 && v4 && v5 && v6) {
            System.out.println(">> CORRECTO: Matrices procesadas con streams.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] plana=12, suma=55, max=9, medias=[5.0,4.75,4.0], cols>7=[2,3], transpuesta correcta.");
        }
    }
}
