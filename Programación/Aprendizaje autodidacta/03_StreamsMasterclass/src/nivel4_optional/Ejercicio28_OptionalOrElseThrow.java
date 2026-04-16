package nivel4_optional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import modelos.Empleado;

/**
 * EJERCICIO 28 — OPTIONAL: .ORELSEGET(), .ORELSETHROW(), .IFPRESENT() (SIN GUÍA)
 * 
 * Objetivo: Dominar todas las formas de extraer/reaccionar al valor de un Optional.
 */
public class Ejercicio28_OptionalOrElseThrow {

    public static void demostracion() {
        System.out.println("--- OPTIONAL: TODAS LAS SALIDAS ---");
        System.out.println(".orElse(valor)             -> Devuelve valor por defecto (siempre se evalúa).");
        System.out.println(".orElseGet(() -> calcular)  -> Devuelve valor por defecto (lazy, solo si vacío).");
        System.out.println(".orElseThrow(() -> excepción) -> Lanza excepción si vacío.");
        System.out.println(".ifPresent(consumer)        -> Ejecuta acción SOLO si tiene valor.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 28: GESTIONAR OPTIONALS DE MÚLTIPLES FORMAS ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Carlos", "Frontend", "JavaScript", 2, 32000, true, "carlos@corp.com"));
        empresa.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));

        // TODO 1: Busca el empleado con mayor salario usando .stream().max() y .orElseThrow()
        // .max() devuelve Optional<Empleado>
        Empleado mejorPagado = null; // <- Escribe aquí

        // TODO 2: Busca un empleado de "RRHH" (no existe). Usa .orElseGet() para crear uno nuevo por defecto.
        Empleado deRRHH = null; // <- Escribe aquí

        // TODO 3: Busca un empleado de "Backend" y usa .ifPresent() para imprimir su nombre.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<

        // --- VALIDACIÓN ---
        boolean v1 = mejorPagado != null && mejorPagado.getNombre().equals("Lucía");
        boolean v2 = deRRHH != null && deRRHH.getDepartamento().equals("RRHH");
        if (v1 && v2) {
            System.out.println(">> CORRECTO: orElseThrow, orElseGet e ifPresent dominados.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] mejorPagado=Lucía, deRRHH debe ser un empleado de departamento 'RRHH'.");
        }
    }
}
