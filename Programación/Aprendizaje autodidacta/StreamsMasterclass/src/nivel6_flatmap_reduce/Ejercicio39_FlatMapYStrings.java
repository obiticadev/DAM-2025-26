package nivel6_flatmap_reduce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 39 — FLATMAP CON STRINGS: DIVIDIR Y APLANAR (CON GUÍA)
 * 
 * Objetivo: Usar flatMap para dividir strings en palabras y aplanar el resultado.
 */
public class Ejercicio39_FlatMapYStrings {

    public static void demostracion() {
        System.out.println("--- FLATMAP CON STRINGS ---");
        System.out.println("flatMap también sirve para dividir strings en palabras:");
        System.out.println("  frases.stream()");
        System.out.println("      .flatMap(frase -> Arrays.stream(frase.split(\" \")))");
        System.out.println("Cada frase se convierte en un Stream de palabras, y flatMap los une todos.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 39: EXTRAER TODAS LAS INICIALES DE TODOS LOS EMPLEADOS ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana García López", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, "carlos@corp.com"));
        empresa.add(new Empleado("Lucía Martín Sánchez", "Data", "Python", 10, 70000, true, "lucia@corp.com"));

        // TODO 1: Extrae los nombres completos, divídelos en palabras con flatMap,
        // y recoge TODAS las palabras individuales en una lista.
        List<String> todasLasPalabras = null; // <- Escribe aquí

        // TODO 2: Del resultado anterior, obtén solo las palabras que empiecen por 'L' (mayúscula).
        List<String> conL = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = todasLasPalabras != null && todasLasPalabras.size() == 7;
        boolean v2 = conL != null && conL.size() == 2 && conL.contains("López") && conL.contains("Lucía");

        if (v1 && v2) {
            System.out.println(">> CORRECTO: flatMap divide strings en palabras y las aplana.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] 7 palabras totales, 2 empiezan por L: López, Lucía.");
        }
    }
}
