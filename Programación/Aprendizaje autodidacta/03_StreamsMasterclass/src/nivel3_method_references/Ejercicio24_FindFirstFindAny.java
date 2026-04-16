package nivel3_method_references;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import modelos.Empleado;

/**
 * EJERCICIO 24 — FINDFIRST Y FINDANY (SIN GUÍA)
 * 
 * Objetivo: Buscar un elemento concreto en el stream.
 */
public class Ejercicio24_FindFirstFindAny {

    public static void demostracion() {
        System.out.println("--- FINDFIRST() Y FINDANY() ---");
        System.out.println(".findFirst() -> Devuelve Optional<T> con el primer elemento que sobrevive al pipeline.");
        System.out.println(".findAny()   -> Igual, pero en streams paralelos puede devolver cualquiera.");
        System.out.println("Ambos devuelven Optional, NO el objeto directo. Hay que usar .orElse() o .isPresent().\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 24: BUSCAR EMPLEADOS ESPECÍFICOS ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Carlos", "Frontend", "JavaScript", 2, 32000, true, "carlos@corp.com"));
        empresa.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro", "QA", "Java", 3, 30000, false, null));

        // TODO 1: Encuentra el PRIMER empleado que use Python. Devuelve su nombre o "No encontrado".
        String primerPython = "No encontrado"; // <- Escribe aquí usando findFirst() + map() + orElse()

        // TODO 2: Encuentra CUALQUIER empleado inactivo. Devuelve su nombre o "Todos activos".
        String algunInactivo = "Todos activos"; // <- Escribe aquí usando findAny()

        // TODO 3: Intenta encontrar un empleado que use "Kotlin". 
        // Como no existe, el Optional estará vacío. Devuelve "No hay kotlineros".
        String kotlinero = "Pendiente"; // <- Escribe aquí

        System.out.println("Primer Python: " + primerPython);
        System.out.println("Algún inactivo: " + algunInactivo);
        System.out.println("Kotlinero: " + kotlinero);

        // --- VALIDACIÓN ---
        if (primerPython.equals("Lucía") && algunInactivo.equals("Pedro")
                && kotlinero.equals("No hay kotlineros")) {
            System.out.println(">> CORRECTO: findFirst/findAny con Optional dominados.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] primerPython=Lucía, algunInactivo=Pedro, kotlinero='No hay kotlineros'.");
        }
    }
}
