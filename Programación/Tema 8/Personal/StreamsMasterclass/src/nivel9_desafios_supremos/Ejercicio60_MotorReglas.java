package nivel9_desafios_supremos;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 60 — MOTOR DE REGLAS CON PREDICADOS DINÁMICOS
 * 
 * SIN PISTAS. Construye filtros complejos combinando predicados en tiempo de ejecución.
 */
public class Ejercicio60_MotorReglas {

    public static void demostracion() {
        System.out.println("=== DESAFÍO SUPREMO 60: MOTOR DE REGLAS ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 60: FILTROS DINÁMICOS CON PREDICADOS ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        // TODO 1: Crea una lista de Predicate<Empleado> con estas reglas:
        //   - Está activo
        //   - Salario > 40000
        //   - Tiene email (no null)
        // Luego combínalos TODOS con .and() usando reduce para crear UN solo predicado.
        // Filtra la empresa con ese predicado combinado.
        List<Predicate<Empleado>> reglas = new ArrayList<>();
        // <- Añade las 3 reglas aquí

        Predicate<Empleado> combinado = null; // <- Combina con reduce
        List<Empleado> filtrados1 = null; // <- Filtra con el predicado combinado

        // TODO 2: Ahora crea un filtro OR: empleados que sean de "Backend" O de "Data".
        // Combina los predicados con .or() usando reduce.
        List<Predicate<Empleado>> reglasDept = new ArrayList<>();
        // <- Añade predicados para Backend y Data

        Predicate<Empleado> combinadoOr = null; // <- Combina con reduce usando .or()
        List<Empleado> filtrados2 = null; // <- Filtra con el predicado OR

        // TODO 3: Crea un método de búsqueda flexible: filtra activos + combina reglasDept (OR)
        // + niega (negate) a los que tengan experiencia < 3.
        // Es decir: activo AND (Backend OR Data) AND experiencia >= 3
        List<String> nombresFinales = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = filtrados1 != null && filtrados1.size() == 4; // Ana, Marta, Lucía, Elena
        boolean v2 = filtrados2 != null && filtrados2.size() == 4; // Ana, Luis, Lucía, Marcos (+ Pedro inactivo)
        boolean v3 = nombresFinales != null && nombresFinales.size() == 3
                && nombresFinales.contains("Ana García")
                && nombresFinales.contains("Lucía Martín")
                && nombresFinales.contains("Marcos Vila");

        if (v1 && v2 && v3) {
            System.out.println(">> CORRECTO: Motor de reglas con predicados dinámicos.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] AND=4(Ana,Marta,Lucía,Elena), OR=4(Backend+Data), final=3(Ana,Lucía,Marcos).");
        }
    }
}
