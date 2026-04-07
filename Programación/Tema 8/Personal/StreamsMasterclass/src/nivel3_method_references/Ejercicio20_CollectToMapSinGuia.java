package nivel3_method_references;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 20 — COLLECTORS.TOMAP() CON METHOD REFERENCES (SIN GUÍA)
 * 
 * Objetivo: Recolectar un Stream en un Map usando Method References.
 */
public class Ejercicio20_CollectToMapSinGuia {

    public static void demostracion() {
        System.out.println("--- COLLECTORS.TOMAP() ---");
        System.out.println("Convierte un Stream en un Map<K, V> eligiendo qué campo es la clave y cuál el valor.");
        System.out.println("Ejemplo: .collect(Collectors.toMap(Empleado::getNombre, Empleado::getSalario))");
        System.out.println("Si hay claves duplicadas, necesitas una función de merge como tercer argumento.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 20: DICCIONARIO NOMBRE -> SALARIO ---");
        List<Empleado> equipo = new ArrayList<>();
        equipo.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        equipo.add(new Empleado("Carlos", "Frontend", "JavaScript", 1, 25000, true, "carlos@corp.com"));
        equipo.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        equipo.add(new Empleado("Pedro", "QA", "Java", 3, 30000, true, "pedro@corp.com"));

        // TODO: Crea un Map<String, Double> donde:
        // - La clave sea el nombre del empleado
        // - El valor sea su salario
        // Usa Method References donde sea posible.

        Map<String, Double> directorio = equipo.stream()
                .collect(Collectors.toMap(Empleado::getNombre, Empleado::getSalario));

        // --- VALIDACIÓN ---
        if (directorio != null && directorio.size() == 4
                && directorio.get("Ana") == 55000.0
                && directorio.get("Lucía") == 70000.0) {
            System.out.println(">> CORRECTO: Map generado con toMap y Method References.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(
                    ">> [ERROR] El Map debe tener 4 entradas: Ana->55000, Carlos->25000, Lucía->70000, Pedro->30000.");
        }
    }
}
