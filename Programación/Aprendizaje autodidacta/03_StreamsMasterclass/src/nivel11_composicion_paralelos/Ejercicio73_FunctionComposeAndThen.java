package nivel11_composicion_paralelos;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 73 — FUNCTION.compose() Y FUNCTION.andThen()
 * 
 * Aprende a componer funciones para crear pipelines de transformación reutilizables.
 * Lee la teoría: teoria/10_Composicion_Funcional_Paralelos.md (secciones 10.1 y 10.2)
 * 
 * CONCEPTO CLAVE:
 *   Function<A,B>.andThen(Function<B,C>) → Function<A,C>  (primero A→B, luego B→C)
 *   Function<A,B>.compose(Function<C,A>) → Function<C,B>  (primero C→A, luego A→B)
 */
public class Ejercicio73_FunctionComposeAndThen {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 73: Function.compose / andThen ===\n");

        // DEMO: Componer funciones simples
        Function<String, String> trim = String::trim;
        Function<String, String> upper = String::toUpperCase;
        Function<String, Integer> length = String::length;

        // andThen: trim → upper
        Function<String, String> trimYUpper = trim.andThen(upper);
        System.out.println("andThen: '  hola  ' → '" + trimYUpper.apply("  hola  ") + "'");

        // compose: length recibe el resultado de upper
        Function<String, Integer> upperYLength = length.compose(upper);
        System.out.println("compose: 'hola' → longitud = " + upperYLength.apply("hola"));

        // DEMO: Pipeline de transformación para Empleado
        Function<Empleado, String> nombre = Empleado::getNombre;
        Function<String, String> mayusculas = String::toUpperCase;
        Function<String, String> decorar = s -> "★ " + s + " ★";

        Function<Empleado, String> pipeline = nombre.andThen(mayusculas).andThen(decorar);

        List<Empleado> demo = new ArrayList<>();
        demo.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        demo.add(new Empleado("Luis Pérez", "QA", "Java", 3, 35000, true, "luis@corp.com"));

        demo.stream().map(pipeline).forEach(System.out::println);
        System.out.println("\nAhora completa los TODOs.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 73: COMPÓN FUNCIONES ---");
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empleados.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empleados.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empleados.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empleados.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));

        // TODO 1: Crea 3 funciones y compónlas con andThen:
        //   a) extraerNombre: Empleado → String (getNombre)
        //   b) aMayusculas: String → String (toUpperCase)
        //   c) conDepto: necesitas crear una Function<Empleado, String> que devuelva
        //      "NOMBRE (depto)" — para esto, crea el pipeline completo directamente.
        // Resultado: Function<Empleado, String> que transforma Empleado → "NOMBRE (depto)"
        Function<Empleado, String> ficha = null; // <- Escribe aquí

        List<String> fichas = null; // <- Aplica ficha con map y recoge en lista
        System.out.println("[Fichas compuestas]");
        if (fichas != null) fichas.forEach(System.out::println);

        // TODO 2: Crea un pipeline de UnaryOperator<String> que:
        //   a) trim
        //   b) toUpperCase
        //   c) añada " ✓" al final
        // Compónlo con andThen y aplícalo a estas cadenas:
        List<String> cadenas = List.of("  hola  ", " mundo ", "  java  ");
        UnaryOperator<String> paso1 = String::trim;
        // <- Completa paso2, paso3 y compón

        List<String> transformadas = null; // <- Aplica el pipeline a cadenas con map
        System.out.println("\n[Cadenas transformadas]");
        if (transformadas != null) transformadas.forEach(System.out::println);

        // TODO 3: Usa compose para crear una función que primero extraiga el nombre
        // de un Empleado y luego devuelva su longitud.
        // Function<Empleado, Integer> longitudNombre usando compose.
        Function<String, Integer> longitud = String::length;
        Function<Empleado, Integer> longitudNombre = null; // <- Usa compose

        List<Integer> longitudes = null; // <- Aplica longitudNombre con map
        System.out.println("\n[Longitudes de nombres]");
        if (longitudes != null) longitudes.forEach(System.out::println);

        // --- VALIDACIÓN ---
        boolean v1 = fichas != null && fichas.size() == 5
                && fichas.get(0).contains("ANA") && fichas.get(0).contains("Backend");
        boolean v2 = transformadas != null && transformadas.size() == 3
                && transformadas.get(0).equals("HOLA ✓");
        boolean v3 = longitudes != null && longitudes.size() == 5 && longitudes.get(0) == 10;

        if (v1 && v2 && v3) {
            System.out.println("\n>> CORRECTO: Composición de funciones dominada.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println("\n>> [ERROR] fichas con NOMBRE(depto), cadenas=HOLA ✓, longitudes correctas.");
        }
    }
}
