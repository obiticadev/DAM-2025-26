package nivel8_generativos_avanzados;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import modelos.Empleado;

/**
 * EJERCICIO 56 — UNARYOPERATOR Y BINARYOPERATOR EN STREAMS (SIN GUÍA)
 * 
 * Objetivo: Usar UnaryOperator en iterate y BinaryOperator en reduce de forma explícita.
 */
public class Ejercicio56_UnaryBinaryOperator {

    public static void demostracion() {
        System.out.println("--- UNARYOPERATOR Y BINARYOPERATOR ---");
        System.out.println("UnaryOperator<T>  extiende Function<T,T>  -> transforma T en T");
        System.out.println("BinaryOperator<T> extiende BiFunction<T,T,T> -> combina dos T en uno");
        System.out.println("Son las interfaces detrás de iterate y reduce.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 56: OPERADORES FUNCIONALES ---");
        List<Empleado> equipo = new ArrayList<>();
        equipo.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        equipo.add(new Empleado("Carlos", "Frontend", "JavaScript", 2, 32000, true, "carlos@corp.com"));
        equipo.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        equipo.add(new Empleado("Pedro", "QA", "Java", 3, 30000, false, null));
        equipo.add(new Empleado("Elena", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));

        // TODO 1: Define un UnaryOperator<String> que convierta un string a "***string***".
        // Luego úsalo con Stream.iterate para generar: "X", "***X***", "***...***X***...***" (5 niveles).
        UnaryOperator<String> envolver = null; // <- Escribe aquí
        List<String> niveles = null; // <- Usa Stream.iterate("X", envolver).limit(5)

        // TODO 2: Define un BinaryOperator<Empleado> que dados dos empleados devuelva
        // el que tenga más experiencia. Luego úsalo con reduce para encontrar al más experto activo.
        BinaryOperator<Empleado> masExperto = null; // <- Escribe aquí
        String nombreMasExperto = ""; // <- Usa equipo.stream().filter(...).reduce(masExperto)...

        // TODO 3: Define un UnaryOperator<Double> que aplique un 10% de aumento.
        // Genera una lista de 6 salarios empezando en 30000 donde cada uno es 10% más que el anterior.
        UnaryOperator<Double> aumento10 = null; // <- Escribe aquí
        List<Double> escalaSalarial = null; // <- Usa Stream.iterate(30000.0, aumento10).limit(6)

        // --- VALIDACIÓN ---
        boolean v1 = niveles != null && niveles.size() == 5
                && niveles.get(0).equals("X") && niveles.get(1).equals("***X***");
        boolean v2 = nombreMasExperto.equals("Lucía");
        boolean v3 = escalaSalarial != null && escalaSalarial.size() == 6
                && escalaSalarial.get(0) == 30000.0
                && Math.abs(escalaSalarial.get(5) - 30000 * Math.pow(1.1, 5)) < 0.01;

        if (v1 && v2 && v3) {
            System.out.println(">> CORRECTO: UnaryOperator y BinaryOperator dominados.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] niveles=5 strings, masExperto=Lucía, escala=6 salarios crecientes 10%.");
        }
    }
}
