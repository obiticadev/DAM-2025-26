package nivel2_streams_basicos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 10 — FILTROS ENCADENADOS SIN GUÍA
 * 
 * Objetivo: Encadenar múltiples .filter() en un mismo Stream pipeline.
 */
public class Ejercicio10_FilterEncadenadoSinGuia {

    public static void demostracion() {
        System.out.println("--- FILTROS ENCADENADOS ---");
        System.out.println("Puedes poner tantos .filter() como quieras en cadena.");
        System.out.println("Cada uno actúa como un coladero adicional sobre los datos que sobrevivieron al anterior.");
        System.out.println("Es equivalente a usar && dentro de un solo filter, pero más legible.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 10: TRIPLE FILTRO ---");
        List<Empleado> megacorp = new ArrayList<>();
        megacorp.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        megacorp.add(new Empleado("Carlos", "Frontend", "JavaScript", 1, 25000, true, "carlos@corp.com"));
        megacorp.add(new Empleado("Lucía", "Backend", "Python", 10, 70000, true, "lucia@corp.com"));
        megacorp.add(new Empleado("Pedro", "Backend", "Java", 3, 30000, false, null));
        megacorp.add(new Empleado("Elena", "Backend", "Java", 6, 48000, true, "elena@corp.com"));
        megacorp.add(new Empleado("Marcos", "Backend", "Go", 2, 35000, true, "marcos@corp.com"));

        // TODO: Crea un pipeline que filtre:
        //   1. Solo empleados del departamento "Backend"
        //   2. Que estén activos
        //   3. Que tengan un salario mayor a 40000
        // Recoge el resultado en una List<Empleado>

        List<Empleado> eliteBackend = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        if (eliteBackend != null && eliteBackend.size() == 3
                && eliteBackend.stream().allMatch(e ->
                    e.getDepartamento().equals("Backend") && e.isActivo() && e.getSalario() > 40000)) {
            System.out.println(">> CORRECTO: Triple filtrado ejecutado limpiamente.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Deberían quedar 3: Ana(55k), Lucía(70k), Elena(48k). Todos Backend, activos, >40k.");
        }
    }
}
