package nivel4_optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import modelos.Empleado;

/**
 * EJERCICIO 26 — OPTIONAL: .MAP() Y .ORELSE() (SIN GUÍA)
 * 
 * Objetivo: Transformar el contenido de un Optional sin abrirlo manualmente.
 */
public class Ejercicio26_OptionalMapYOrElse {

    public static void demostracion() {
        System.out.println("--- OPTIONAL.MAP(): TRANSFORMAR SIN ABRIR ---");
        System.out.println("Optional tiene su propio .map(), igual que Stream.");
        System.out.println("Si el Optional tiene valor, aplica la función. Si está vacío, devuelve vacío.");
        System.out.println("Ejemplo: Optional.ofNullable(email).map(String::toUpperCase).orElse(\"SIN EMAIL\")");
        System.out.println("Esto es mucho más limpio que: if (email != null) { email.toUpperCase() } else ...\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 26: EXTRAER EMAIL SEGURO DE EMPLEADOS ---");
        List<Empleado> equipo = new ArrayList<>();
        equipo.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        equipo.add(new Empleado("Pedro", "QA", "Java", 3, 30000, false, null));
        equipo.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));

        // TODO: Para cada empleado, extrae su email de forma segura:
        // 1. Envuelve getEmail() en Optional.ofNullable()
        // 2. Usa .map(String::toUpperCase) para transformarlo
        // 3. Usa .orElse("EMAIL NO DISPONIBLE") como fallback
        // Guarda los resultados en una lista.

        List<String> emailsSeguros = new ArrayList<>();
        emailsSeguros = equipo.stream()
                .map(e -> Optional.ofNullable(e.getEmail())
                        .map(String::toUpperCase)
                        .orElse("EMAIL NO DISPONIBLE"))
                .toList();

        emailsSeguros.forEach(System.out::println);

        // --- VALIDACIÓN ---
        if (emailsSeguros.size() == 3
                && emailsSeguros.get(0).equals("ANA@CORP.COM")
                && emailsSeguros.get(1).equals("EMAIL NO DISPONIBLE")
                && emailsSeguros.get(2).equals("LUCIA@CORP.COM")) {
            System.out.println(
                    ">> CORRECTO: Optional.map() evita NullPointerException de forma elegante.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Esperado: [ANA@CORP.COM, EMAIL NO DISPONIBLE, LUCIA@CORP.COM]");
        }
    }
}
