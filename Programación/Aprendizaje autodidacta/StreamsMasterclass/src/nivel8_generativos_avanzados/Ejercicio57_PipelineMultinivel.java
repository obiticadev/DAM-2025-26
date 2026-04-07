package nivel8_generativos_avanzados;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import modelos.Empleado;
import modelos.Pedido;
import modelos.Producto;

/**
 * EJERCICIO 57 — PIPELINE MULTINIVEL: TODO COMBINADO (SIN GUÍA)
 * 
 * Objetivo: Combinar generate, iterate, groupingBy con downstream, reduce y flatMap
 * en un solo ejercicio que exige dominar todo lo aprendido en Nivel 8.
 */
public class Ejercicio57_PipelineMultinivel {

    public static void demostracion() {
        System.out.println("=== RETO NIVEL 8: PIPELINE MULTINIVEL ===");
        System.out.println("Combina todo: generate, iterate, downstream collectors, reduce, flatMap.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 57: ANÁLISIS COMPLETO DE EMPRESA ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        // TODO 1: Agrupa empleados activos por departamento.
        // Para cada grupo, genera un String con formato: "DEPTO(N): nombre1, nombre2 [media: X€]"
        // Ordena los departamentos por media salarial DESCENDENTE.
        // Resultado: List<String>
        List<String> resumenDeptos = null; // <- Escribe aquí

        // TODO 2: Usa Stream.iterate para generar una secuencia de 5 umbrales salariales:
        // 30000, 40000, 50000, 60000, 70000
        // Para cada umbral, cuenta cuántos empleados activos ganan MÁS que ese umbral.
        // Resultado: Map<Integer, Long> umbral -> cantidad
        Map<Integer, Long> distribucion = null; // <- Escribe aquí

        // TODO 3: Extrae todas las palabras de todos los nombres de empleados activos,
        // cuenta la frecuencia de cada inicial (primera letra de cada palabra),
        // y ordena por frecuencia descendente.
        // Resultado: Map<Character, Long>
        Map<Character, Long> frecuenciaIniciales = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = resumenDeptos != null && resumenDeptos.size() >= 4
                && resumenDeptos.get(0).contains("Data");
        boolean v2 = distribucion != null && distribucion.size() == 5
                && distribucion.get(30000) == 6L && distribucion.get(70000) == 0L;
        boolean v3 = frecuenciaIniciales != null && frecuenciaIniciales.size() >= 5;

        if (v1 && v2 && v3) {
            System.out.println(">> CORRECTO: Pipeline multinivel dominado.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] deptos ordenados por media desc (Data primero), distrib 5 umbrales, frecuencia iniciales.");
        }
    }
}
