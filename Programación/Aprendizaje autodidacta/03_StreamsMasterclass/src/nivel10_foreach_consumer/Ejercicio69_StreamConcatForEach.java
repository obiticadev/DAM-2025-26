package nivel10_foreach_consumer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import modelos.Empleado;
import modelos.Producto;

/**
 * EJERCICIO 69 — STREAM.CONCAT Y FOREACH PARA COMBINAR FLUJOS
 * 
 * SIN GUÍA. Combina streams de distintas fuentes con Stream.concat
 * y muestra resultados con forEach + method references.
 * Lee la teoría: teoria/09_ForEach_Consumer_Avanzado.md (sección 9.5)
 */
public class Ejercicio69_StreamConcatForEach {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 69: STREAM.CONCAT + forEach ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 69: COMBINA Y PINTA ---");

        List<Empleado> backend = new ArrayList<>();
        backend.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        backend.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        backend.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        List<Empleado> frontend = new ArrayList<>();
        frontend.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        frontend.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));

        List<Empleado> data = new ArrayList<>();
        data.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        data.add(new Empleado("Sara Díaz", "Data", "Python", 4, 42000, true, "sara@corp.com"));

        List<String> nombresUnidos = new ArrayList<>();

        // TODO 1: Usa Stream.concat para unir backend y frontend en un solo stream.
        // Ordena por nombre y muestra cada uno con forEach(System.out::println).
        // Guarda los nombres en nombresUnidos usando peek.
        System.out.println("[Backend + Frontend ordenados por nombre]");
        // <- Escribe aquí

        // TODO 2: Concatena los 3 equipos (backend + frontend + data).
        // Pista: Stream.concat(Stream.concat(a, b), c)
        // Ordénalos por salario DESC y muestra con forEach usando un lambda que imprima:
        // "  nombre — salario€ (depto)"
        System.out.println("\n[Todos los equipos por salario DESC]");
        // <- Escribe aquí

        // TODO 3: Crea dos Stream<String> con nombres de lenguajes:
        //   equipo1: extraídos de backend con map(Empleado::getLenguajePrincipal)
        //   equipo2: extraídos de data con map(Empleado::getLenguajePrincipal)
        // Concatena ambos, elimina duplicados (distinct), ordena alfabéticamente
        // y muéstralos con forEach(System.out::println).
        System.out.println("\n[Lenguajes únicos de Backend+Data]");
        // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = nombresUnidos.size() == 5;
        boolean v2 = nombresUnidos.get(0).equals("Ana García"); // 'A' primero alfabéticamente

        if (v1 && v2) {
            System.out.println("\n>> CORRECTO: Stream.concat + forEach dominado.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println("\n>> [ERROR] 5 nombres unidos, primero Ana García (orden alfabético).");
        }
    }
}
