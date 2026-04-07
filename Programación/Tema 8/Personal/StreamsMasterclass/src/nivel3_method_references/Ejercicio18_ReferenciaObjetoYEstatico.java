package nivel3_method_references;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import modelos.Empleado;

/**
 * EJERCICIO 18 — METHOD REFERENCE: TIPOS 2 Y 3 (SIN GUÍA)
 * 
 * Objetivo: Usar objeto::metodo y Clase::metodoEstatico
 */
public class Ejercicio18_ReferenciaObjetoYEstatico {

    public static void demostracion() {
        System.out.println("--- METHOD REFERENCE TIPO 2 Y 3 ---");
        System.out.println("Tipo 2 (objeto concreto): x -> System.out.println(x)  =>  System.out::println");
        System.out.println("Tipo 3 (estático):        s -> Integer.parseInt(s)     =>  Integer::parseInt");
        System.out.println("Tipo 3 (estático):        d -> Math.abs(d)             =>  Math::abs\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 18: USAR LOS 3 TIPOS DE METHOD REFERENCE ---");
        List<Empleado> equipo = new ArrayList<>();
        equipo.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        equipo.add(new Empleado("Carlos", "Frontend", "JavaScript", 1, 25000, true, "carlos@corp.com"));
        equipo.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));

        // TODO 1: Usa System.out::println en un forEach para imprimir los empleados
        System.out.println("--- Todos los empleados ---");
        // >>> ESCRIBE TU forEach CON System.out::println AQUÍ <<<
        equipo.forEach(System.out::println);

        // TODO 2: Extrae los nombres y conviértelos a mayúsculas usando Method
        // References
        // Paso a) .map(Empleado::getNombre) para extraer nombres
        // Paso b) .map(String::toUpperCase) para convertir a mayúsculas
        List<String> nombresMayus = equipo.stream()
                .map(Empleado::getNombre)
                .map(String::toUpperCase)
                .toList();

        // TODO 3: Convierte estas strings numéricas a Integer usando Integer::parseInt
        List<String> numerosTexto = List.of("10", "20", "30", "40");
        List<Integer> numeros = numerosTexto.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        // --- VALIDACIÓN ---
        boolean v1 = nombresMayus != null && nombresMayus.size() == 3 && nombresMayus.get(0).equals("ANA");
        boolean v2 = numeros != null && numeros.size() == 4 && numeros.get(0) == 10 && numeros.get(3) == 40;
        if (v1 && v2) {
            System.out.println(">> CORRECTO: Has aplicado los 3 tipos de Method Reference.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] nombresMayus=[ANA, CARLOS, LUCÍA], numeros=[10, 20, 30, 40]");
        }
    }
}
