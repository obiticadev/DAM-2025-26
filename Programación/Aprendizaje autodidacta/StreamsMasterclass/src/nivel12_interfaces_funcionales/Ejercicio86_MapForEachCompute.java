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
 * EJERCICIO 86 - MAP.forEach, MAP.compute Y MAP.merge
 * 
 * Aprende a usar metodos funcionales de Map con BiFunction y BiConsumer.
 * Lee la teoria: teoria/11_Interfaces_Funcionales_Personalizadas.md (seccion 11.8)
 */
public class Ejercicio86_MapForEachCompute {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 86: Map.forEach / compute / merge ===\n");

        Map<String, Integer> puntos = new HashMap<>();
        puntos.put("Ana", 10);
        puntos.put("Luis", 20);

        // DEMO: Map.forEach con BiConsumer
        puntos.forEach((k, v) -> System.out.println("  " + k + " = " + v));

        // DEMO: compute
        puntos.compute("Ana", (k, v) -> v + 5);
        System.out.println("\nTras compute Ana+5: " + puntos);

        // DEMO: merge
        puntos.merge("Carlos", 15, Integer::sum);
        puntos.merge("Ana", 3, Integer::sum);
        System.out.println("Tras merge: " + puntos);

        System.out.println("\nAhora completa los TODOs.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 86: OPERACIONES FUNCIONALES SOBRE MAP ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana Garcia", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Perez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta Lopez", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucia Martin", "Data", "Python", 10, 70000, true, "lucia@corp.com"));

        // TODO 1: Crea un Map<String, Double> con salarios por nombre.
        // Usa Map.forEach con un BiConsumer que imprima "nombre -> salarioEUR".
        Map<String, Double> salarios = new HashMap<>(); // <- Rellena aqui
        System.out.println("[Salarios con forEach]");
        // <- Map.forEach aqui

        // TODO 2: Usa compute para subir un 10% el salario de "Ana Garcia".
        // Usa merge para anadir "Sara Diaz" con 42000, y si ya existe, sumar.
        // <- Escribe aqui
        System.out.println("\n[Tras compute y merge]");
        salarios.forEach((k, v) -> System.out.printf("  %-15s -> %,.0f EUR%n", k, v));

        // TODO 3: Crea un Map<String, List<String>> donde la clave sea el departamento
        // y el valor la lista de nombres. Usa Map.forEach con un BiConsumer para imprimir:
        // "DEPTO: nombre1, nombre2"
        Map<String, List<String>> porDepto = empresa.stream()
                .collect(Collectors.groupingBy(
                        Empleado::getDepartamento,
                        Collectors.mapping(Empleado::getNombre, Collectors.toList())));
        BiConsumer<String, List<String>> imprimirGrupo = null; // <- Escribe aqui
        System.out.println("\n[Grupos con BiConsumer]");
        if (imprimirGrupo != null) porDepto.forEach(imprimirGrupo);

        // TODO 4: Usa replaceAll sobre el mapa de salarios para aplicar un descuento del 5%.
        // replaceAll recibe una BiFunction<String, Double, Double>.
        // <- Escribe aqui
        System.out.println("\n[Tras descuento 5%]");
        salarios.forEach((k, v) -> System.out.printf("  %-15s -> %,.0f EUR%n", k, v));

        // --- VALIDACION ---
        boolean v1 = salarios.containsKey("Sara Diaz");
        boolean v2 = imprimirGrupo != null;

        if (v1 && v2) {
            System.out.println("\n>> CORRECTO: Map funcional dominado. [OK]");
        } else {
            System.err.println("\n>> [ERROR] Sara Diaz en mapa, BiConsumer para grupos.");
        }
    }
}
