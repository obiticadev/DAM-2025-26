package nivel12_interfaces_funcionales;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import modelos.Empleado;

/**
 * EJERCICIO 85 - PATRON DECORATOR CON FUNCTION
 * 
 * SIN GUIA. Construye decoradores componibles usando Function.andThen.
 * Lee la teoria: teoria/11_Interfaces_Funcionales_Personalizadas.md (seccion 11.7)
 */
public class Ejercicio85_PatronDecorator {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 85: PATRON DECORATOR ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 85: DECORADORES FUNCIONALES ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana Garcia", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Perez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Lucia Martin", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        // TODO 1: Crea decoradores de String como UnaryOperator<String>:
        //   enMayusculas: toUpperCase
        //   conCorchetes: s -> "[" + s + "]"
        //   conEstrella: s -> "* " + s + " *"
        //   conNumero: usa un int[] para numerar -> "1. texto", "2. texto"...
        UnaryOperator<String> enMayusculas = null; // <- Escribe aqui
        UnaryOperator<String> conCorchetes = null;
        UnaryOperator<String> conEstrella = null;
        int[] num = {0};
        UnaryOperator<String> conNumero = null;

        // TODO 2: Compone: conNumero -> enMayusculas -> conCorchetes -> conEstrella
        // Aplica el decorador compuesto a los nombres de empleados con map + forEach.
        Function<String, String> decoradorCompleto = null; // <- Usa andThen
        System.out.println("[Nombres decorados]");
        // <- Aplica con map(Empleado::getNombre).map(decoradorCompleto).forEach(...)

        // TODO 3: Crea un Function<Empleado, String> decorado que:
        //   a) Extraiga nombre + " (" + depto + ")"
        //   b) Lo ponga en mayusculas
        //   c) Le anada " --- salarioEUR"
        // Aplica a todos con map + forEach(System.out::println).
        Function<Empleado, String> fichaDecor = null; // <- Escribe aqui
        System.out.println("\n[Fichas decoradas]");
        // <- Aplica aqui

        // --- VALIDACION ---
        num[0] = 0; // reset
        boolean v1 = decoradorCompleto != null && decoradorCompleto.apply("hola").contains("HOLA");
        boolean v2 = fichaDecor != null && fichaDecor.apply(empresa.get(0)).contains("ANA");

        if (v1 && v2) {
            System.out.println("\n>> CORRECTO: Patron Decorator funcional dominado. [OK]");
        } else {
            System.err.println("\n>> [ERROR] decoradorCompleto contiene HOLA, fichaDecor contiene ANA.");
        }
    }
}
