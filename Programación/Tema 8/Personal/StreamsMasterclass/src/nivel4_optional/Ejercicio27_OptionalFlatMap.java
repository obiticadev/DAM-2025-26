package nivel4_optional;

import java.util.Optional;

/**
 * EJERCICIO 27 — OPTIONAL: .FLATMAP() Y ENCADENAMIENTO (CON GUÍA)
 * 
 * Objetivo: Encadenar operaciones Optional que devuelven Optional sin anidar.
 */
public class Ejercicio27_OptionalFlatMap {

    public static void demostracion() {
        System.out.println("--- OPTIONAL.FLATMAP(): EVITAR OPTIONAL<OPTIONAL<T>> ---");
        System.out.println("Si tienes un método que ya devuelve Optional, usar .map() crearía Optional<Optional<T>>.");
        System.out.println(".flatMap() aplana eso a Optional<T>.");
        System.out.println("Ejemplo real: buscarUsuario(id).flatMap(u -> buscarDireccion(u)).flatMap(d -> buscarCiudad(d))");
        System.out.println("Cada paso puede fallar (devolver vacío) y la cadena se detiene elegantemente.\n");
    }

    // Simulación de métodos que devuelven Optional
    public static Optional<String> buscarEmail(String nombre) {
        if (nombre.equals("Ana")) return Optional.of("ana@corp.com");
        if (nombre.equals("Pedro")) return Optional.of("pedro@corp.com");
        return Optional.empty();
    }

    public static Optional<String> extraerDominio(String email) {
        int idx = email.indexOf("@");
        if (idx > 0) return Optional.of(email.substring(idx + 1));
        return Optional.empty();
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 27: CADENA DE OPTIONALS CON FLATMAP ---");

        // TODO 1: Usando los métodos buscarEmail() y extraerDominio() de arriba,
        // encadena con flatMap para obtener el dominio del email de "Ana".
        // Si algún paso falla, debe devolver "desconocido".
        String dominioAna = "desconocido"; // <- Escribe aquí

        // TODO 2: Haz lo mismo para "Carlos" (que NO existe en buscarEmail).
        // La cadena debería devolver "desconocido" automáticamente.
        String dominioCarlos = "PENDIENTE"; // <- Escribe aquí con la misma cadena pero "Carlos"

        // TODO 3: Haz lo mismo para "Pedro".
        String dominioPedro = "PENDIENTE"; // <- Escribe aquí

        System.out.println("Dominio de Ana: " + dominioAna);
        System.out.println("Dominio de Carlos: " + dominioCarlos);
        System.out.println("Dominio de Pedro: " + dominioPedro);

        // --- VALIDACIÓN ---
        if (dominioAna.equals("corp.com") && dominioCarlos.equals("desconocido")
                && dominioPedro.equals("corp.com")) {
            System.out.println(">> CORRECTO: flatMap encadena Optionals sin NullPointerException.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Ana=corp.com, Carlos=desconocido, Pedro=corp.com.");
        }
    }
}
