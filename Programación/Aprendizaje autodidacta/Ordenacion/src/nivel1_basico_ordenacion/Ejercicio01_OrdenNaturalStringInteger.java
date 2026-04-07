package nivel1_basico_ordenacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ejercicio01_OrdenNaturalStringInteger {

    public static void demostracion() {
        System.out.println("--- INTRODUCCIÓN A COLLECTIONS.SORT ---");
        System.out.println(
                "Las clases base de Java como String o Integer ya vienen con 'Comparable' implementado por los ingenieros de Oracle.");
        System.out.println("Su orden natural es obvio: Alfabético y Numérico Menor a Mayor.\n");

        List<String> armas = new ArrayList<>(Arrays.asList("Espada", "Arco", "Daga", "Hacha"));
        System.out.println("Sin ordenar: " + armas);

        System.out.println("Aplicando Collections.sort(armas)...");
        Collections.sort(armas);

        System.out.println("Ordenadas (A-Z): " + armas);
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 01: ORDENACIÓN DE NÚMEROS ---");
        List<Integer> niveles = new ArrayList<>(Arrays.asList(99, 1, 50, 24, 10));

        // TODO 1: Usa Collections.sort() para ordenar la lista 'niveles' usando el
        // orden natural de Integer.

        // TODO 2: ¡Problema! Quiero que la inviertas. Ojo, invierte el array que ya has
        // ordenado en el TODO 1, no uses un comparator.
        // PISTA: Revisa los otros métodos estáticos útiles de la clase Collections
        // (como reverse).
        niveles.sort(Collections.reverseOrder());

        // --- VALIDACIÓN ---
        if (niveles.size() == 5 && niveles.get(0) == 99 && niveles.get(4) == 1) {
            System.out.println(
                    ">> ¡CORRECTO! Has manejado la ordenación natural básica del sistema.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(
                    ">> [ERROR] El Array de niveles no coincide con lo esparado. Debería ser: [99, 50, 24, 10, 1]");
        }
    }
}
