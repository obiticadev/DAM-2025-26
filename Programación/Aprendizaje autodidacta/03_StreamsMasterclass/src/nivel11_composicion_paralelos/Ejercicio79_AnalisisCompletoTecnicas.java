package nivel11_composicion_paralelos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import modelos.Empleado;
import modelos.Pedido;
import modelos.Producto;

/**
 * EJERCICIO 79 — ANÁLISIS COMPLETO CON TODAS LAS TÉCNICAS
 * 
 * SIN GUÍA. Combina Function.compose/andThen, Consumer.andThen, Predicate.and/or,
 * Supplier&lt;Stream&gt;, parallel, forEach con method references, collectingAndThen,
 * Stream.concat y Collector.of en un único ejercicio.
 */
public class Ejercicio79_AnalisisCompletoTecnicas {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 79: ANÁLISIS CON TODAS LAS TÉCNICAS ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 79: TODO EN UNO ---");

        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));
        empresa.add(new Empleado("Sara Díaz", "Data", "Python", 4, 42000, true, "sara@corp.com"));
        empresa.add(new Empleado("Tomás Ruiz", "QA", "Java", 1, 25000, true, "tomas@corp.com"));

        int[] secciones = {0};

        // TODO 1: Crea un Supplier<Stream<Empleado>> para empleados activos.
        // Úsalo 3 veces: para contar, para sumar salarios, y para obtener nombres.
        Supplier<Stream<Empleado>> activos = null; // <- Escribe aquí
        long totalActivos = 0; // <- activos.get()...
        double sumaSalarios = 0; // <- activos.get()...
        List<String> nombres = null; // <- activos.get()...
        System.out.println("Activos: " + totalActivos + " | Suma: " + sumaSalarios + "€");
        if (nombres != null) System.out.println("Nombres: " + nombres);
        // secciones[0]++;

        // TODO 2: Crea un Predicate compuesto con .and() y .or():
        //   (activo AND experiencia >= 5) OR (activo AND salario > 60000)
        // Filtra y muestra con forEach(System.out::println).
        Predicate<Empleado> filtroComplejo = null; // <- Escribe aquí
        System.out.println("\n[Filtro complejo: senior OR alto salario]");
        // <- Filtra y forEach aquí
        // secciones[0]++;

        // TODO 3: Crea un pipeline de Function con andThen:
        //   Empleado → nombre → mayúsculas → "★ NOMBRE ★"
        // Aplícalo con map y muestra con forEach(System.out::println).
        Function<Empleado, String> pipelineFn = null; // <- Escribe aquí
        System.out.println("\n[Pipeline de Functions]");
        // <- Aplica y forEach aquí
        // secciones[0]++;

        // TODO 4: Crea 2 Consumer<Empleado> y encadénalos con andThen:
        //   a) Imprime "Procesando: nombre"
        //   b) Imprime "  → Depto: depto, Salario: salario€"
        // Aplícalo a los 3 mejor pagados activos con forEach.
        Consumer<Empleado> consumerPipeline = null; // <- Escribe aquí
        System.out.println("\n[Consumer pipeline top 3]");
        // <- Aplica aquí
        // secciones[0]++;

        // TODO 5: Usa parallelStream para calcular la suma de salarios de activos
        // con reduce de 3 argumentos. Muestra el resultado.
        double sumaParallel = 0; // <- Escribe aquí
        System.out.println("\n[Suma parallel]: " + sumaParallel + "€");
        // secciones[0]++;

        // TODO 6: Usa collectingAndThen para obtener el empleado activo mejor pagado
        // directamente como Empleado (no Optional). Muestra su nombre.
        Empleado mejorPagado = null; // <- Escribe aquí
        if (mejorPagado != null) System.out.println("\nMejor pagado: " + mejorPagado.getNombre());
        // secciones[0]++;

        // --- VALIDACIÓN ---
        boolean v1 = totalActivos == 9 && sumaSalarios == 380000.0;
        boolean v3 = pipelineFn != null && pipelineFn.apply(empresa.get(0)).contains("ANA");
        boolean v5 = sumaParallel == 380000.0;
        boolean v6 = mejorPagado != null && mejorPagado.getNombre().equals("Lucía Martín");

        if (secciones[0] == 6 && v1 && v3 && v5 && v6) {
            System.out.println("\n>> CORRECTO: Todas las técnicas combinadas.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println("\n>> [ERROR] 6 secciones, 9 activos, suma=380000, mejorPagado=Lucía.");
        }
    }
}
