package bloque4;

import java.util.Scanner;

/**
 * EJERCICIO 21 — Lectura robusta de entrada
 * Teoria: teoria/04_DAO_Menu.md (seccion 3)
 *
 * Clase utilidad con metodos estaticos para leer enteros, doubles y Strings
 * de forma segura (sin InputMismatchException).
 */
public class Ej21_Entrada {

    // TODO 1: Implementa pedirEntero(Scanner sc, String prompt) — imprime prompt,
    //         lee sc.nextLine(), intenta Integer.parseInt. Si falla, imprime
    //         "Introduce un numero entero valido" y reintenta en bucle.
    public static int pedirEntero(Scanner sc, String prompt) { return 0; }

    // TODO 2: Implementa pedirEnteroEnRango(Scanner sc, String prompt, int min, int max).
    //         Usa pedirEntero y ademas valida que este entre min y max (inclusive).
    //         Si no esta en rango, imprime "Debe estar entre min y max" y reintenta.
    public static int pedirEnteroEnRango(Scanner sc, String prompt, int min, int max) { return 0; }

    // TODO 3: Implementa pedirDouble(Scanner sc, String prompt) — similar a pedirEntero
    //         pero con Double.parseDouble.
    public static double pedirDouble(Scanner sc, String prompt) { return 0.0; }

    // TODO 4: Implementa pedirTextoNoVacio(Scanner sc, String prompt) — lee texto,
    //         si es vacio o solo espacios, reintenta.
    public static String pedirTextoNoVacio(Scanner sc, String prompt) { return ""; }

    // TODO 5: Implementa confirmar(Scanner sc, String pregunta) — muestra la pregunta,
    //         espera "s" o "n" (case insensitive). Devuelve true si "s", false si "n".
    //         Reintenta si no es ninguna.
    public static boolean confirmar(Scanner sc, String pregunta) { return false; }

    // TODO 6: Implementa pedirPosicion(Scanner sc, String prompt, int maxFilas, int maxCols).
    //         Pide fila y columna, valida ambas. Devuelve int[]{fila, columna}.
    public static int[] pedirPosicion(Scanner sc, String prompt, int maxFilas, int maxCols) {
        return null;
    }

    // TODO 7: Implementa pedirOpcionMenu(Scanner sc, String[] opciones) — muestra las opciones
    //         numeradas (1 a N + "0. Salir"), pide un entero y lo devuelve.
    //         Valida que este entre 0 y opciones.length.
    public static int pedirOpcionMenu(Scanner sc, String[] opciones) { return 0; }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 21: Entrada Robusta ===\n");
        Scanner sc = new Scanner(System.in);
        int n = pedirEnteroEnRango(sc, "Numero entre 1 y 10: ", 1, 10);
        System.out.println("Elegiste: " + n);
        boolean ok = confirmar(sc, "Seguro? (s/n): ");
        System.out.println("Confirmado: " + ok);
        sc.close();
    }
}
