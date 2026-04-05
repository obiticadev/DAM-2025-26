package nivel1_fundamentos_lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * EJERCICIO 02 — LAMBDA SIN GUÍA: ORDENAR NÚMEROS
 * 
 * Objetivo: Aplicar lo aprendido en el Ej01 pero ahora con Integer y sin pistas directas.
 */
public class Ejercicio02_LambdaSinGuia {

    public static void demostracion() {
        System.out.println("--- LAMBDA CON ENTEROS ---");
        System.out.println("Ya sabes que .sort() acepta Lambdas para Strings.");
        System.out.println("Lo mismo aplica para cualquier tipo: Integer, Double, objetos...");
        System.out.println("Recuerda: Integer.compare(a, b) devuelve un valor numérico que indica orden.");
        System.out.println("Para descendente, invierte los parámetros.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 02: ORDENAR SALARIOS DE MAYOR A MENOR ---");
        List<Integer> salarios = new ArrayList<>(Arrays.asList(28000, 55000, 42000, 90000, 33000, 67000));

        System.out.println("Salarios desordenados: " + salarios);

        // TODO: Ordena la lista 'salarios' de MAYOR a MENOR usando una Lambda en .sort()
        // No hay pista de sintaxis esta vez. Recuerda lo que hiciste en el ejercicio anterior.

        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<

        // --- VALIDACIÓN ---
        if (salarios.size() == 6 && salarios.get(0) == 90000 && salarios.get(5) == 28000) {
            System.out.println(">> CORRECTO: Salarios ordenados descendentemente sin ayuda.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Esperado: [90000, 67000, 55000, 42000, 33000, 28000]");
        }
    }
}
