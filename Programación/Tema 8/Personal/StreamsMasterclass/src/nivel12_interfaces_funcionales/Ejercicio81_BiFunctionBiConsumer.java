package nivel12_interfaces_funcionales;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 81 - BiFunction Y BiConsumer
 * 
 * Aprende a usar BiFunction (2 args -> resultado) y BiConsumer (2 args -> accion).
 * Lee la teoria: teoria/11_Interfaces_Funcionales_Personalizadas.md (seccion 11.4)
 * 
 * CONCEPTO CLAVE:
 *   BiFunction<T, U, R> -> R apply(T t, U u)
 *   BiConsumer<T, U>    -> void accept(T t, U u)
 *   Map.forEach recibe un BiConsumer<K, V>
 */
public class Ejercicio81_BiFunctionBiConsumer {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 81: BiFunction Y BiConsumer ===\n");

        // DEMO: BiFunction
        BiFunction<String, Integer, String> repetir = (s, n) -> s.repeat(n);
        System.out.println(repetir.apply("Hola ", 3));

        // DEMO: BiConsumer con Map.forEach
        Map<String, Integer> edades = new HashMap<>();
        edades.put("Ana", 30);
        edades.put("Luis", 25);

        BiConsumer<String, Integer> imprimir = (nombre, edad) ->
                System.out.println("  " + nombre + " tiene " + edad + " anios");
        edades.forEach(imprimir);

        System.out.println("\nAhora completa los TODOs.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 81: PRACTICA BiFunction/BiConsumer ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana Garcia", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Perez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Marta Lopez", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucia Martin", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        // TODO 1: Crea una BiFunction<Empleado, Double, String> que reciba un empleado
        // y un porcentaje de bonus, y devuelva:
        // "nombre -> salarioNuevo EUR (bonus: +X%)"
        BiFunction<Empleado, Double, String> calcularBonus = null; // <- Escribe aqui

        System.out.println("[Bonus del 10%]");
        if (calcularBonus != null) {
            empresa.stream()
                    .map(e -> calcularBonus.apply(e, 10.0))
                    .forEach(System.out::println);
        }

        // TODO 2: Agrupa empleados por departamento.
        // Usa Map.forEach con un BiConsumer<String, List<Empleado>> que imprima:
        // "DEPTO (N personas): nombre1, nombre2, ..."
        Map<String, List<Empleado>> porDepto = empresa.stream()
                .collect(Collectors.groupingBy(Empleado::getDepartamento));
        BiConsumer<String, List<Empleado>> imprimirDepto = null; // <- Escribe aqui
        System.out.println("\n[Departamentos]");
        if (imprimirDepto != null) porDepto.forEach(imprimirDepto);

        // TODO 3: Crea una BiFunction<List<Empleado>, String, Double> que reciba
        // la lista y un departamento, y devuelva el salario medio de ese depto.
        BiFunction<List<Empleado>, String, Double> mediaPorDepto = null; // <- Escribe aqui

        double mediaBackend = mediaPorDepto != null ? mediaPorDepto.apply(empresa, "Backend") : 0;
        System.out.println("\nMedia Backend: " + mediaBackend + " EUR");

        // --- VALIDACION ---
        boolean v1 = calcularBonus != null && calcularBonus.apply(empresa.get(0), 10.0).contains("60500");
        boolean v2 = imprimirDepto != null;
        boolean v3 = Math.abs(mediaBackend - 51666.67) < 1;

        if (v1 && v2 && v3) {
            System.out.println("\n>> CORRECTO: BiFunction y BiConsumer dominados. [OK]");
        } else {
            System.err.println("\n>> [ERROR] bonus con 10%, imprimirDepto, media Backend ~51666.67.");
        }
    }
}
