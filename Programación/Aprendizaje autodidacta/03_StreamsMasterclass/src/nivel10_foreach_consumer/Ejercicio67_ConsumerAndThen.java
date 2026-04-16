package nivel10_foreach_consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import modelos.Empleado;

/**
 * EJERCICIO 67 — CONSUMER.andThen() PARA ENCADENAR ACCIONES
 * 
 * SIN GUÍA. Encadena múltiples Consumer con andThen y úsalos en forEach.
 * Lee la teoría: teoria/09_ForEach_Consumer_Avanzado.md (sección 9.3)
 */
public class Ejercicio67_ConsumerAndThen {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 67: CONSUMER.andThen() ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 67: ENCADENA CONSUMERS ---");
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empleados.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empleados.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empleados.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));

        List<String> log = new ArrayList<>();
        int[] contador = {0};

        // TODO 1: Crea tres Consumer<Empleado>:
        //   a) "imprimir" → imprime el nombre por consola con System.out.println
        //   b) "registrar" → añade el nombre a la lista "log"
        //   c) "contar" → incrementa contador[0] en 1
        Consumer<Empleado> imprimir = null; // <- Escribe aquí
        Consumer<Empleado> registrar = null; // <- Escribe aquí
        Consumer<Empleado> contar = null; // <- Escribe aquí

        // TODO 2: Encadénalos con andThen: imprimir → registrar → contar
        // Y aplícalo con forEach sobre los empleados
        Consumer<Empleado> pipeline = null; // <- Escribe aquí usando andThen
        System.out.println("[Ejecutando pipeline de 3 consumers]");
        // <- Aplica con forEach aquí

        // TODO 3: Crea un Consumer<Empleado> "formatear" que imprima con formato:
        //   "  ★ NOMBRE (depto) — salario€"
        // Encadénalo con "registrar" y aplícalo con forEach solo a los que ganan > 40000
        Consumer<Empleado> formatear = null; // <- Escribe aquí
        System.out.println("\n[Empleados premium formateados]");
        // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = log.size() >= 4 && log.contains("Ana García") && log.contains("Lucía Martín");
        boolean v2 = contador[0] >= 4;

        if (v1 && v2) {
            System.out.println("\n>> CORRECTO: Consumer.andThen encadenado correctamente.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println("\n>> [ERROR] log debe tener >=4 nombres, contador >= 4.");
        }
    }
}
