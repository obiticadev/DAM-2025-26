package nivel7_experto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 49 — RETO EXPERTO: OPTIONAL AVANZADO EN CADENAS DE NEGOCIO
 * 
 * SIN PISTAS. Encadena Optional con streams en escenarios realistas.
 */
public class Ejercicio49_OptionalAvanzado {

    public static void demostracion() {
        System.out.println("=== RETO EXPERTO 49: OPTIONAL EN CADENAS DE NEGOCIO ===\n");
    }

    // Simula una base de datos de empleados
    private static List<Empleado> baseDatos() {
        List<Empleado> db = new ArrayList<>();
        db.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        db.add(new Empleado("Carlos", "Frontend", "JavaScript", 2, 32000, true, null));
        db.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        db.add(new Empleado("Pedro", "QA", "Java", 3, 30000, false, null));
        return db;
    }

    // Busca empleado por nombre (devuelve Optional)
    public static Optional<Empleado> buscarPorNombre(String nombre) {
        return baseDatos().stream()
                .filter(e -> e.getNombre().equals(nombre))
                .findFirst();
    }

    // Busca el dominio de un email (devuelve Optional)
    public static Optional<String> extraerDominio(String email) {
        int at = email.indexOf("@");
        return at > 0 ? Optional.of(email.substring(at + 1)) : Optional.empty();
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 49: CADENAS DE OPTIONAL REALES ---");

        // TODO 1: Busca "Ana", obtén su email, extrae el dominio. Si cualquier paso falla -> "sin dominio"
        String dominioAna = "sin dominio"; // <- Escribe aquí

        // TODO 2: Busca "Carlos" (email null), intenta lo mismo. Debe dar "sin dominio".
        String dominioCarlos = "sin dominio"; // <- Escribe aquí

        // TODO 3: Busca "Nadie" (no existe), intenta lo mismo. Debe dar "sin dominio".
        String dominioNadie = "sin dominio"; // <- Escribe aquí

        // TODO 4: Busca "Lucía", si está activa devuelve "ACTIVA: " + nombre, si no "INACTIVA: " + nombre.
        // Si no existe -> "NO ENCONTRADA"
        String estadoLucia = "NO ENCONTRADA"; // <- Escribe aquí

        // TODO 5: Busca "Pedro" (inactivo) y haz lo mismo.
        String estadoPedro = "NO ENCONTRADA"; // <- Escribe aquí

        System.out.println("Dominio Ana: " + dominioAna);
        System.out.println("Dominio Carlos: " + dominioCarlos);
        System.out.println("Dominio Nadie: " + dominioNadie);
        System.out.println("Estado Lucía: " + estadoLucia);
        System.out.println("Estado Pedro: " + estadoPedro);

        // --- VALIDACIÓN ---
        boolean v1 = dominioAna.equals("corp.com");
        boolean v2 = dominioCarlos.equals("sin dominio");
        boolean v3 = dominioNadie.equals("sin dominio");
        boolean v4 = estadoLucia.equals("ACTIVA: Lucía");
        boolean v5 = estadoPedro.equals("INACTIVA: Pedro");

        if (v1 && v2 && v3 && v4 && v5) {
            System.out.println(">> CORRECTO: Cadenas Optional avanzadas sin NullPointerException.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Ana=corp.com, Carlos=sin dominio, Nadie=sin dominio, Lucía=ACTIVA, Pedro=INACTIVA.");
        }
    }
}
