package nivel11_composicion_paralelos;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import modelos.Empleado;

/**
 * EJERCICIO 76 — SUPPLIER<STREAM> PARA STREAMS REUTILIZABLES
 * 
 * SIN GUÍA. Un stream solo se consume UNA vez. Usa Supplier para "reutilizarlo".
 * Lee la teoría: teoria/10_Composicion_Funcional_Paralelos.md (sección 10.6)
 */
public class Ejercicio76_SupplierStreamReutilizable {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 76: SUPPLIER<STREAM> ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 76: STREAMS REUTILIZABLES ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        // TODO 1: Crea un Supplier<Stream<Empleado>> que devuelva un stream de activos
        // cada vez que se invoque .get().
        Supplier<Stream<Empleado>> activos = null; // <- Escribe aquí

        // TODO 2: Usa el Supplier para obtener 3 datos DIFERENTES del mismo "stream":
        //   a) Cantidad de activos
        //   b) Salario medio de activos
        //   c) Lista de nombres de activos
        // Sin el Supplier, el stream se cerraría tras el primer uso.
        long cantidadActivos = 0; // <- Usa activos.get()...
        double salarioMedio = 0; // <- Usa activos.get()...
        List<String> nombresActivos = null; // <- Usa activos.get()...

        System.out.println("Activos: " + cantidadActivos);
        System.out.println("Salario medio: " + salarioMedio + "€");
        System.out.println("Nombres: " + nombresActivos);

        // TODO 3: Crea otro Supplier que devuelva un stream de empleados senior
        // (experiencia >= 5, activos). Úsalo para:
        //   a) Imprimir sus nombres con forEach(System.out::println)
        //   b) Calcular la suma de sus salarios
        Supplier<Stream<Empleado>> seniors = null; // <- Escribe aquí

        System.out.println("\n[Seniors activos]");
        // <- Imprime con forEach aquí usando seniors.get()

        double sumaSeniors = 0; // <- Usa seniors.get()
        System.out.println("Suma salarios seniors: " + sumaSeniors + "€");

        // TODO 4: Demuestra que sin Supplier, un stream da error al reutilizar.
        // Crea un stream normal, úsalo para count(), y luego intenta usarlo
        // para forEach. Captura la IllegalStateException e imprímela.
        System.out.println("\n[Demostración de error sin Supplier]");
        // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = cantidadActivos == 7;
        boolean v2 = Math.abs(salarioMedio - 50428.57) < 1;
        boolean v3 = nombresActivos != null && nombresActivos.size() == 7;
        boolean v4 = sumaSeniors == 286000.0; // Ana(55k)+Marta(48k)+Lucía(70k)+Elena(48k)+Marcos(65k)

        if (v1 && v2 && v3 && v4) {
            System.out.println("\n>> CORRECTO: Supplier<Stream> dominado.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println("\n>> [ERROR] 7 activos, media~50428.57, 7 nombres, sumaSeniors=286000.");
        }
    }
}
