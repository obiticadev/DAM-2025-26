package nivel2_streams_basicos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 12 — .SORTED() Y .DISTINCT() DENTRO DE STREAMS (SIN GUÍA)
 * 
 * Objetivo: Ordenar y eliminar duplicados dentro de un pipeline.
 */
public class Ejercicio12_SortedYDistinct {

    public static void demostracion() {
        System.out.println("--- .SORTED() Y .DISTINCT() EN STREAMS ---");
        System.out.println(".sorted() ordena el stream (usa Comparable por defecto o acepta Comparator).");
        System.out.println(".distinct() elimina duplicados usando equals()/hashCode() del objeto.");
        System.out.println("Ambos son operaciones INTERMEDIAS: no disparan la tubería por sí solos.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 12: LIMPIAR Y ORDENAR UNA LISTA SUCIA ---");
        List<Empleado> datosCorruptos = new ArrayList<>();
        datosCorruptos.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        datosCorruptos.add(new Empleado("Carlos", "Frontend", "JavaScript", 2, 32000, true, "carlos@corp.com"));
        datosCorruptos.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com")); // DUPLICADO
        datosCorruptos.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        datosCorruptos.add(new Empleado("Carlos", "Frontend", "JavaScript", 2, 32000, true, "carlos@corp.com")); // DUPLICADO
        datosCorruptos.add(new Empleado("Elena", "DevOps", "Go", 4, 45000, true, "elena@corp.com"));

        // TODO: Crea un pipeline que:
        //   1. Elimine duplicados con .distinct()
        //   2. Ordene por nombre A-Z usando .sorted(Comparator.comparing(...))
        //   3. Recoja en List<Empleado>

        List<Empleado> limpios = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        if (limpios != null && limpios.size() == 4
                && limpios.get(0).getNombre().equals("Ana")
                && limpios.get(3).getNombre().equals("Lucía")) {
            System.out.println(">> CORRECTO: Datos depurados y ordenados en una sola tubería.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Esperado 4 empleados únicos ordenados A-Z: Ana, Carlos, Elena, Lucía.");
        }
    }
}
