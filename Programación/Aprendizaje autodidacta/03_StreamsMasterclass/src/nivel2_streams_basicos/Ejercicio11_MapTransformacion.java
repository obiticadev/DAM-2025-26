package nivel2_streams_basicos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 11 — .MAP(): TRANSFORMAR DATOS EN LA TUBERÍA (CON GUÍA)
 * 
 * Objetivo: Usar .map() para convertir un Stream<Empleado> en Stream<String>.
 */
public class Ejercicio11_MapTransformacion {

    public static void demostracion() {
        System.out.println("--- .MAP(): EL MUTADOR DE TIPOS ---");
        System.out.println(".map() recibe una Function<T, R> y transforma cada elemento del Stream.");
        System.out.println("Ejemplo: .map(e -> e.getNombre()) convierte Stream<Empleado> en Stream<String>.");
        System.out.println("Puedes encadenar .map() varias veces para transformaciones sucesivas.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 11: EXTRAER EMAILS DE EMPLEADOS ACTIVOS ---");
        List<Empleado> plantilla = new ArrayList<>();
        plantilla.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        plantilla.add(new Empleado("Carlos", "Frontend", "JavaScript", 1, 25000, false, null));
        plantilla.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        plantilla.add(new Empleado("Pedro", "QA", "Java", 3, 30000, true, "pedro@corp.com"));

        // TODO: Crea un pipeline que filtre activos con email no nulo,
        // extraiga el email con .map(), lo convierta a mayúsculas con otro .map(),
        // y recoja en una List<String>.

        List<String> emailsMayus = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        if (emailsMayus != null && emailsMayus.size() == 3
                && emailsMayus.get(0).equals("ANA@CORP.COM")
                && emailsMayus.get(2).equals("PEDRO@CORP.COM")) {
            System.out.println(">> CORRECTO: Has mutado el Stream de objetos a Strings en mayúsculas.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Esperado: [ANA@CORP.COM, LUCIA@CORP.COM, PEDRO@CORP.COM]");
        }
    }
}
