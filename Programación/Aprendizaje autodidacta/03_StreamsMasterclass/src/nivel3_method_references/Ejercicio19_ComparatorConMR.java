package nivel3_method_references;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 19 — COMPARATOR.COMPARING CON METHOD REFERENCE (CON GUÍA)
 * 
 * Objetivo: Usar Comparator.comparing(Clase::getter) con encadenamiento.
 */
public class Ejercicio19_ComparatorConMR {

    public static void demostracion() {
        System.out.println("--- COMPARATOR.COMPARING + METHOD REFERENCE ---");
        System.out.println("La combinación más elegante de Java moderno:");
        System.out.println("  lista.sort(Comparator.comparing(Empleado::getNombre))");
        System.out.println("  lista.sort(Comparator.comparing(Empleado::getSalario).reversed())");
        System.out.println("  lista.sort(Comparator.comparing(Empleado::getDepartamento)");
        System.out.println("                       .thenComparing(Empleado::getSalario).reversed())\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 19: ORDENAR CON ELEGANCIA MÁXIMA ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos", "Frontend", "JavaScript", 2, 32000, true, "carlos@corp.com"));
        empresa.add(new Empleado("Marta", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía", "Backend", "Python", 10, 70000, true, "lucia@corp.com"));

        // TODO: Ordena la lista usando SOLO Method References dentro de Comparator.comparing:
        //   Primer criterio: departamento ASCENDENTE
        //   Segundo criterio (desempate): salario DESCENDENTE

        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<

        // --- VALIDACIÓN ---
        if (empresa.get(0).getNombre().equals("Lucía") && empresa.get(2).getNombre().equals("Luis")
                && empresa.get(3).getNombre().equals("Marta") && empresa.get(4).getNombre().equals("Carlos")) {
            System.out.println(">> CORRECTO: Comparator.comparing con Method Reference y multicriterio.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Esperado: Lucía(B,70k), Ana(B,55k), Luis(B,35k), Marta(F,48k), Carlos(F,32k)");
        }
    }
}
