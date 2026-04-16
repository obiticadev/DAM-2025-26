package nivel13_maestria_total;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 93 - MOTOR DE TRANSFORMACIONES ENCADENADAS
 * 
 * SIN GUIA. Construye un motor que aplique una cadena de transformaciones
 * usando Function.andThen y UnaryOperator.
 */
public class Ejercicio93_MotorTransformaciones {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 93: MOTOR DE TRANSFORMACIONES ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 93: CADENA DE TRANSFORMACIONES ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana Garcia", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Perez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Marta Lopez", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucia Martin", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        // TODO 1: Crea una List<UnaryOperator<String>> con transformaciones de texto:
        //   a) trim
        //   b) toUpperCase
        //   c) s -> "[" + s + "]"
        //   d) s -> ">> " + s + " <<"
        // Reduce la lista a una sola Function usando andThen.
        List<UnaryOperator<String>> transformaciones = new ArrayList<>();
        // <- Anade transformaciones

        // Reduce a una sola funcion
        Function<String, String> cadena = null; // <- Usa reduce con andThen

        System.out.println("[Cadena de transformaciones sobre nombres]");
        if (cadena != null) {
            empresa.stream()
                    .map(Empleado::getNombre)
                    .map(cadena)
                    .forEach(System.out::println);
        }

        // TODO 2: Crea un List<Function<Empleado, Empleado>> que simule "pasos de procesamiento":
        //   Nota: como Empleado es inmutable, transforma a strings que representen el estado.
        //   Usa List<Function<String, String>> sobre la representacion toString de cada empleado:
        //     paso1: extraer nombre
        //     paso2: convertir a mayusculas
        //     paso3: anadir "PROCESADO: " delante
        //   Reduce y aplica.
        List<Function<String, String>> pasos = new ArrayList<>();
        // <- Anade pasos

        Function<String, String> pipeline = null; // <- Reduce
        System.out.println("\n[Pipeline de pasos]");
        if (pipeline != null) {
            empresa.stream()
                    .map(Empleado::getNombre)
                    .map(pipeline)
                    .forEach(System.out::println);
        }

        // TODO 3: Crea un motor generico: un metodo que reciba List<UnaryOperator<T>>
        // y devuelva un UnaryOperator<T> compuesto. Aplica al tipo String.
        // <- Implementa como lambda o metodo static

        // --- VALIDACION ---
        boolean v1 = cadena != null && cadena.apply("  hola  ").contains("HOLA");
        boolean v2 = pipeline != null && pipeline.apply("Ana Garcia").contains("PROCESADO");

        if (v1 && v2) {
            System.out.println("\n>> CORRECTO: Motor de transformaciones dominado. [OK]");
        } else {
            System.err.println("\n>> [ERROR] cadena contiene HOLA, pipeline contiene PROCESADO.");
        }
    }
}
