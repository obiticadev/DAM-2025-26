package nivel1_fundamentos_lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import modelos.Empleado;

/**
 * EJERCICIO 04 — ORDENACIÓN MULTICRITERIO SIN GUÍA
 * 
 * Objetivo: Encadenar .thenComparing() para ordenar por varios campos.
 */
public class Ejercicio04_OrdenMulticriterioSinGuia {

    public static void demostracion() {
        System.out.println("--- MULTICRITERIO CON THENCOMPARING ---");
        System.out.println("Cuando dos empleados comparten departamento, necesitas un desempate.");
        System.out.println("Comparator.comparing(campo1).thenComparing(campo2) encadena criterios.");
        System.out.println("Cada paso puede llevar .reversed() independiente.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 04: ORDENAR POR DEPARTAMENTO ASC + SALARIO DESC ---");
        List<Empleado> plantilla = new ArrayList<>();
        plantilla.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        plantilla.add(new Empleado("Luis", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        plantilla.add(new Empleado("Carlos", "Frontend", "JavaScript", 2, 32000, true, "carlos@corp.com"));
        plantilla.add(new Empleado("Marta", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        plantilla.add(new Empleado("Lucía", "Backend", "Python", 10, 70000, true, "lucia@corp.com"));

        // TODO: Ordena 'plantilla' primero por departamento ASCENDENTE (A-Z),
        // y cuando empaten en departamento, desempata por salario DESCENDENTE (mayor
        // primero).
        // No hay pista de sintaxis. Recuerda
        // Comparator.comparing(...).thenComparing(...)

        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        plantilla.sort(Comparator.comparing(Empleado::getDepartamento)
                .thenComparing(Comparator.comparing(Empleado::getSalario).reversed()));

        // --- VALIDACIÓN ---
        boolean deptoOk = plantilla.get(0).getDepartamento().equals("Backend")
                && plantilla.get(4).getDepartamento().equals("Frontend");
        boolean primerBackend = plantilla.get(0).getNombre().equals("Lucía"); // 70000 es el mayor salario Backend
        boolean ultimoFrontend = plantilla.get(4).getNombre().equals("Carlos"); // 32000 es el menor salario Frontend

        if (deptoOk && primerBackend && ultimoFrontend) {
            System.out.println(">> CORRECTO: Multicriterio dominado sin pistas.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(
                    ">> [ERROR] Orden esperado: Lucía(Backend,70k), Ana(Backend,55k), Luis(Backend,35k), Marta(Frontend,48k), Carlos(Frontend,32k)");
        }
    }
}
