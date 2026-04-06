package nivel11_composicion_paralelos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import modelos.Empleado;

/**
 * EJERCICIO 77 — PIPELINE DE TRANSFORMACIONES REUTILIZABLE
 * 
 * SIN GUÍA. Construye transformaciones de streams como funciones componibles
 * y aplícalas a diferentes conjuntos de datos.
 * Lee la teoría: teoria/10_Composicion_Funcional_Paralelos.md (sección 10.7)
 */
public class Ejercicio77_PipelineTransformaciones {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 77: PIPELINES REUTILIZABLES ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 77: TRANSFORMA Y REUTILIZA ---");

        List<Empleado> equipoA = new ArrayList<>();
        equipoA.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        equipoA.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        equipoA.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));
        equipoA.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));

        List<Empleado> equipoB = new ArrayList<>();
        equipoB.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        equipoB.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        equipoB.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        equipoB.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));

        // TODO 1: Crea un Predicate<Empleado> "filtroBase" que combine:
        //   - activo
        //   - salario > 30000
        //   - tiene email (no null)
        // Usa .and() para encadenarlos.
        Predicate<Empleado> filtroBase = null; // <- Escribe aquí

        // TODO 2: Crea una Function<Empleado, String> "formatoFicha" que transforme
        // un empleado a: "NOMBRE | depto | salario€"
        // Compónla usando andThen si necesitas varias transformaciones.
        Function<Empleado, String> formatoFicha = null; // <- Escribe aquí

        // TODO 3: Crea un Consumer<String> "imprimirConIndice" que use un int[]
        // para numerar las líneas: "  [1] ficha", "  [2] ficha", etc.
        int[] idx = {0};
        Consumer<String> imprimirConIndice = null; // <- Escribe aquí

        // TODO 4: Aplica el mismo pipeline (filtroBase → sorted por salario DESC →
        // formatoFicha → imprimirConIndice) a AMBOS equipos.
        // Resetea idx[0] = 0 entre equipos.
        System.out.println("[Equipo A — filtrado y formateado]");
        // <- Aplica pipeline a equipoA
        idx[0] = 0;
        System.out.println("\n[Equipo B — filtrado y formateado]");
        // <- Aplica pipeline a equipoB

        // TODO 5: Ahora aplica el pipeline a los dos equipos COMBINADOS con Stream.concat.
        // Muestra los resultados con forEachOrdered.
        idx[0] = 0;
        System.out.println("\n[Ambos equipos combinados]");
        // <- Escribe aquí

        // --- VALIDACIÓN ---
        // EquipoA filtrado: Ana(55k,email), Marcos(65k,email) = 2
        // EquipoB filtrado: Marta(48k,email), Elena(48k,email), Lucía(70k,email) = 3
        boolean v1 = filtroBase != null
                && equipoA.stream().filter(filtroBase).count() == 2;
        boolean v2 = filtroBase != null
                && equipoB.stream().filter(filtroBase).count() == 3;
        boolean v3 = idx[0] == 5; // 2 + 3 combined

        if (v1 && v2 && v3) {
            System.out.println("\n>> CORRECTO: Pipeline reutilizable aplicado a múltiples equipos.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println("\n>> [ERROR] EquipoA=2 filtrados, EquipoB=3, combinados=5.");
        }
    }
}
