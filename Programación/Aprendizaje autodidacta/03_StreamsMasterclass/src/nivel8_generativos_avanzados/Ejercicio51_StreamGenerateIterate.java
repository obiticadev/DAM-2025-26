package nivel8_generativos_avanzados;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * EJERCICIO 51 — STREAM.GENERATE Y STREAM.ITERATE (CON GUÍA)
 * 
 * Objetivo: Crear streams desde cero con generate e iterate.
 * Lee la teoría: teoria/07_Streams_Generativos.md
 */
public class Ejercicio51_StreamGenerateIterate {

    public static void demostracion() {
        System.out.println("--- STREAMS GENERATIVOS: GENERATE E ITERATE ---");
        System.out.println("Stream.generate(supplier) -> stream infinito de valores producidos por el supplier.");
        System.out.println("Stream.iterate(seed, op)  -> stream infinito: seed, op(seed), op(op(seed)), ...");
        System.out.println("¡SIEMPRE usa .limit() para evitar bucles infinitos!\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 51: GENERAR SECUENCIAS ---");

        // TODO 1: Usa Stream.generate para crear una lista de 8 strings "ECO".
        List<String> ecos = null; // <- Escribe aquí

        // TODO 2: Usa Stream.iterate para generar los primeros 10 múltiplos de 7 (empezando en 7).
        // Resultado esperado: [7, 14, 21, 28, 35, 42, 49, 56, 63, 70]
        List<Integer> multiplos7 = null; // <- Escribe aquí

        // TODO 3: Usa Stream.iterate para generar las primeras 10 potencias de 2 (empezando en 1).
        // Resultado esperado: [1, 2, 4, 8, 16, 32, 64, 128, 256, 512]
        List<Integer> potencias2 = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = ecos != null && ecos.size() == 8 && ecos.stream().allMatch("ECO"::equals);
        boolean v2 = multiplos7 != null && multiplos7.size() == 10
                && multiplos7.get(0) == 7 && multiplos7.get(9) == 70;
        boolean v3 = potencias2 != null && potencias2.size() == 10
                && potencias2.get(0) == 1 && potencias2.get(9) == 512;

        if (v1 && v2 && v3) {
            System.out.println(">> CORRECTO: Streams generativos dominados.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] ecos=8x\"ECO\", multiplos7=[7..70], potencias2=[1..512].");
        }
    }
}
