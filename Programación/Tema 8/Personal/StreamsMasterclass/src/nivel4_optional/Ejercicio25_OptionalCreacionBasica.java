package nivel4_optional;

import java.util.Optional;

/**
 * EJERCICIO 25 — OPTIONAL: CREACIÓN Y CONSULTA (CON GUÍA)
 * 
 * Objetivo: Entender Optional.of(), Optional.ofNullable(), Optional.empty()
 * Lee primero: teoria/05_Optional_Completo.md
 */
public class Ejercicio25_OptionalCreacionBasica {

    public static void demostracion() {
        System.out.println("--- OPTIONAL: EL CONTENEDOR ANTI-NULL ---");
        System.out.println("Optional<T> es una caja que puede contener un valor o estar vacía.");
        System.out.println("  Optional.of(valor)           -> Crea con valor NO nulo (lanza excepción si null).");
        System.out.println("  Optional.ofNullable(valor)   -> Crea con valor que PUEDE ser null.");
        System.out.println("  Optional.empty()             -> Crea vacío explícitamente.");
        System.out.println("Para consultar:");
        System.out.println("  .isPresent()  -> true si tiene valor.");
        System.out.println("  .get()        -> Obtiene el valor (¡PELIGROSO si está vacío!).");
        System.out.println("  .orElse(def)  -> Obtiene el valor o un valor por defecto.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 25: CREAR Y CONSULTAR OPTIONALS ---");

        String emailExistente = "ana@corp.com";
        String emailNulo = null;

        // TODO 1: Crea un Optional con emailExistente usando Optional.of()
        Optional<String> opt1 = Optional.empty(); // <- Escribe aquí

        // TODO 2: Crea un Optional con emailNulo usando Optional.ofNullable()
        Optional<String> opt2 = Optional.empty(); // <- Escribe aquí

        // TODO 3: Consulta opt1 con .orElse("sin email")
        String resultado1 = opt1.orElse("sin email");

        // TODO 4: Consulta opt2 con .orElse("sin email")
        String resultado2 = opt2.orElse("sin email");

        System.out.println("Resultado 1: " + resultado1);
        System.out.println("Resultado 2: " + resultado2);

        // --- VALIDACIÓN ---
        if (resultado1.equals("ana@corp.com") && resultado2.equals("sin email")) {
            System.out.println(">> CORRECTO: Has creado y consultado Optionals correctamente.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] resultado1 debe ser 'ana@corp.com', resultado2 debe ser 'sin email'.");
        }
    }
}
