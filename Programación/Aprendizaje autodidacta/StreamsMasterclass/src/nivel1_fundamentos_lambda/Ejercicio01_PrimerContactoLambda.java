package nivel1_fundamentos_lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * EJERCICIO 01 — PRIMER CONTACTO CON LAMBDA (CON GUÍA)
 * 
 * Objetivo: Entender la transición de Clase Anónima a Lambda.
 * Lee primero: teoria/01_Interfaces_Funcionales_y_SAM.md
 */
public class Ejercicio01_PrimerContactoLambda {

    public static void demostracion() {
        System.out.println("--- DE LA BUROCRACIA A LA ELEGANCIA: LAMBDAS ---");
        System.out.println("Antes de Java 8, para ordenar una lista de Strings al revés hacías:");
        System.out.println("  lista.sort(new Comparator<String>() {");
        System.out.println("      @Override");
        System.out.println("      public int compare(String a, String b) { return b.compareTo(a); }");
        System.out.println("  });");
        System.out.println("\nCon Lambda, todo eso se reduce a:");
        System.out.println("  lista.sort((a, b) -> b.compareTo(a));");
        System.out.println("\nEl compilador SABE que .sort() necesita un Comparator,");
        System.out.println("y SABE que Comparator solo tiene 1 método: compare(T, T).");
        System.out.println("Por eso puedes obviar todo el ritual y darle solo la lógica pura.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 01: ORDENAR NOMBRES DE Z A A ---");
        List<String> lenguajes = new ArrayList<>(Arrays.asList("Java", "Python", "Go", "Rust", "C"));

        System.out.println("Lista original: " + lenguajes);

        // TODO: Ordena la lista 'lenguajes' de la Z a la A usando una Lambda dentro de .sort()
        // PISTA: lista.sort((a, b) -> ??? );
        // Recuerda que compareTo() devuelve negativo/0/positivo.
        // Para invertir el orden, simplemente invierte quién llama a compareTo.

        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<

        // --- VALIDACIÓN ---
        if (lenguajes.size() == 5 && lenguajes.get(0).equals("Rust") && lenguajes.get(4).equals("C")) {
            System.out.println(">> CORRECTO: Has usado tu primera Lambda para ordenar inversamente.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] La lista debería estar ordenada de Z a A: [Rust, Python, Java, Go, C]");
        }
    }
}
