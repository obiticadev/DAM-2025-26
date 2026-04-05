package nivel1_fundamentos_lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 06 — FUNCTION<T,R> Y TRANSFORMACIONES SIN GUÍA
 * 
 * Objetivo: Usar Function como variable para transformar datos.
 */
public class Ejercicio06_FunctionYTransformacion {

    public static void demostracion() {
        System.out.println("--- FUNCTION<T, R>: EL TRANSFORMADOR ---");
        System.out.println("Function<Empleado, String> recibe un Empleado y devuelve un String.");
        System.out.println("Su método abstracto es: R apply(T t)");
        System.out.println("Se puede encadenar con .andThen(): primero aplica una función, luego otra.");
        System.out.println("Ejemplo: Function<Empleado,String> f = e -> e.getNombre();");
        System.out.println("         Function<String,String> g = s -> s.toUpperCase();");
        System.out.println("         Function<Empleado,String> h = f.andThen(g);\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 06: CREAR UNA CADENA DE FUNCIONES ---");
        List<Empleado> equipo = new ArrayList<>();
        equipo.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        equipo.add(new Empleado("Carlos López", "Frontend", "JavaScript", 2, 32000, true, "carlos@corp.com"));
        equipo.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));

        // TODO 1: Crea una Function<Empleado, String> llamada 'aEmail' que extraiga el email del empleado.
        Function<Empleado, String> aEmail = null; // <- Escribe aquí

        // TODO 2: Crea una Function<String, String> llamada 'aMayusculas' que convierta un String a mayúsculas.
        Function<String, String> aMayusculas = null; // <- Escribe aquí

        // TODO 3: Combínalas usando .andThen() para crear 'emailEnMayusculas'.
        // Después aplícala al stream para obtener una lista de emails en mayúsculas.
        Function<Empleado, String> emailEnMayusculas = null; // <- Escribe aquí

        List<String> resultado = new ArrayList<>();
        if (emailEnMayusculas != null) {
            resultado = equipo.stream()
                    .map(emailEnMayusculas)
                    .collect(Collectors.toList());
        }

        // --- VALIDACIÓN ---
        if (resultado.size() == 3
                && resultado.get(0).equals("ANA@CORP.COM")
                && resultado.get(2).equals("LUCIA@CORP.COM")) {
            System.out.println(">> CORRECTO: Has encadenado Functions para transformar datos en pipeline.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Esperado: [ANA@CORP.COM, CARLOS@CORP.COM, LUCIA@CORP.COM]");
        }
    }
}
