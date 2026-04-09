package nivel11_composicion_paralelos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import modelos.Empleado;

/**
 * EJERCICIO 75 — PARALLEL REDUCE Y RENDIMIENTO
 * 
 * SIN GUÍA. Practica reduce con streams paralelos y entiende cuándo es seguro.
 * Lee la teoría: teoria/10_Composicion_Funcional_Paralelos.md (sección 10.4)
 */
public class Ejercicio75_ParallelReduceRendimiento {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 75: PARALLEL REDUCE ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 75: REDUCE EN PARALELO ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        // TODO 1: Usa parallelStream + reduce con 3 argumentos para sumar
        // los salarios de los empleados activos.
        // reduce(identidad, acumulador, combinador)
        // identidad = 0.0, acumulador = (parcial, emp) -> parcial + emp.getSalario()
        // combinador = Double::sum
        double sumaSalariosParallel = 0; // <- Escribe aquí

        // TODO 2: Usa IntStream.rangeClosed(1, 10_000_000).parallel() para calcular
        // la suma de todos los números. Compárala con la secuencial.
        long sumaSecuencial = 0; // <- Escribe aquí (secuencial)
        long sumaParalela = 0; // <- Escribe aquí (paralela)
        System.out.println("Suma secuencial 1..10M: " + sumaSecuencial);
        System.out.println("Suma paralela   1..10M: " + sumaParalela);
        System.out.println("¿Iguales? " + (sumaSecuencial == sumaParalela));

        // TODO 3: Concatena los nombres de empleados activos con reduce en paralelo.
        // Usa reduce("", (a, b) -> a + (a.isEmpty() ? "" : ", ") + b, (a, b) -> a + ", " + b)
        // sobre los nombres extraídos con map.
        // NOTA: Este reduce NO es asociativo de forma limpia. Observa si hay comas extra.
        String nombresConcat = ""; // <- Escribe aquí

        // TODO 4: Ahora haz lo mismo pero usando Collectors.joining(", ") con parallelStream.
        // Este SÍ es seguro para parallel.
        String nombresJoining = ""; // <- Escribe aquí

        System.out.println("\nSalarios parallel: " + sumaSalariosParallel + "€");
        System.out.println("Nombres (reduce): " + nombresConcat);
        System.out.println("Nombres (joining): " + nombresJoining);

        // --- VALIDACIÓN ---
        boolean v1 = sumaSalariosParallel == 353000.0;
        boolean v2 = sumaSecuencial == sumaParalela && sumaSecuencial == 50000005000000L;
        boolean v3 = nombresJoining.contains("Ana García") && nombresJoining.contains("Marcos Vila");

        if (v1 && v2 && v3) {
            System.out.println("\n>> CORRECTO: Parallel reduce dominado.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println("\n>> [ERROR] sumaSalarios=353000, suma1..10M=50000005000000, joining con 7 nombres.");
        }
    }
}
