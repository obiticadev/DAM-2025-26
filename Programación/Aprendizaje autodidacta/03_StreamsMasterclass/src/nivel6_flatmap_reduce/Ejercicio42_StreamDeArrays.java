package nivel6_flatmap_reduce;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * EJERCICIO 42 — STREAMS DESDE FUENTES ALTERNATIVAS (SIN GUÍA)
 * 
 * Objetivo: Crear streams desde arrays, rangos y Stream.of().
 */
public class Ejercicio42_StreamDeArrays {

    public static void demostracion() {
        System.out.println("--- STREAMS DESDE FUENTES ALTERNATIVAS ---");
        System.out.println("No solo las listas tienen .stream(). También puedes crear streams desde:");
        System.out.println("  Arrays.stream(array)    -> Stream desde un array");
        System.out.println("  IntStream.range(0, 10)  -> Stream de enteros del 0 al 9");
        System.out.println("  IntStream.rangeClosed(1, 10) -> Stream de enteros del 1 al 10");
        System.out.println("  Stream.of(\"a\", \"b\", \"c\") -> Stream inline\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 42: STREAMS DESDE DIFERENTES FUENTES ---");

        // TODO 1: Crea un IntStream del 1 al 20, filtra solo los pares, y súmalos.
        int sumaPares = 0; // <- Escribe aquí

        // TODO 2: Dado este array, conviértelo en un Stream, filtra los > 50 y recoge en lista.
        int[] numeros = {10, 25, 50, 75, 100, 30, 80};
        List<Integer> mayores50 = null; // <- Escribe aquí

        // TODO 3: Genera una lista de los cuadrados del 1 al 10 usando IntStream.
        List<Integer> cuadrados = null; // <- Escribe aquí

        System.out.println("Suma pares 1-20: " + sumaPares);
        System.out.println("Mayores 50: " + mayores50);
        System.out.println("Cuadrados: " + cuadrados);

        // --- VALIDACIÓN ---
        boolean v1 = sumaPares == 110; // 2+4+6+8+10+12+14+16+18+20 = 110
        boolean v2 = mayores50 != null && mayores50.size() == 3
                && mayores50.contains(75) && mayores50.contains(100) && mayores50.contains(80);
        boolean v3 = cuadrados != null && cuadrados.size() == 10
                && cuadrados.get(0) == 1 && cuadrados.get(9) == 100;

        if (v1 && v2 && v3) {
            System.out.println(">> CORRECTO: Streams desde IntStream, Arrays y rangos.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] sumaPares=110, mayores50=[75,100,80], cuadrados=[1,4,9,...,100].");
        }
    }
}
