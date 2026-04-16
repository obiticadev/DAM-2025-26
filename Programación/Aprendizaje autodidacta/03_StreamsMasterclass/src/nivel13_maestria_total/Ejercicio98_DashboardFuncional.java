package nivel13_maestria_total;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import modelos.Empleado;
import modelos.Pedido;
import modelos.Producto;

/**
 * EJERCICIO 98 - DASHBOARD INTERACTIVO FUNCIONAL
 * 
 * SIN GUIA. Genera un dashboard completo usando TODAS las interfaces funcionales
 * y tecnicas de streams aprendidas. Cada panel es un Consumer<List<Empleado>>.
 */
public class Ejercicio98_DashboardFuncional {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 98: DASHBOARD FUNCIONAL ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 98: DASHBOARD COMPLETO ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana Garcia", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Perez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta Lopez", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucia Martin", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        int[] paneles = {0};
        String sep = "=".repeat(50);

        // TODO 1: Panel "Resumen General" como Consumer<List<Empleado>>:
        //   Total empleados, activos, inactivos, masa salarial, media
        Consumer<List<Empleado>> panelResumen = null; // <- Escribe aqui
        System.out.println(sep);
        System.out.println("  PANEL 1: RESUMEN GENERAL");
        System.out.println(sep);
        if (panelResumen != null) { panelResumen.accept(empresa); paneles[0]++; }

        // TODO 2: Panel "Distribucion por Departamento":
        //   DEPTO: N personas | Media salarial | Lenguajes usados
        Consumer<List<Empleado>> panelDeptos = null;
        System.out.println("\n" + sep);
        System.out.println("  PANEL 2: DISTRIBUCION POR DEPARTAMENTO");
        System.out.println(sep);
        if (panelDeptos != null) { panelDeptos.accept(empresa); paneles[0]++; }

        // TODO 3: Panel "Top 5 Ranking" usando Comparator y limit:
        //   Posicion. NOMBRE - salarioEUR (depto)
        Consumer<List<Empleado>> panelRanking = null;
        System.out.println("\n" + sep);
        System.out.println("  PANEL 3: TOP 5 RANKING SALARIAL");
        System.out.println(sep);
        if (panelRanking != null) { panelRanking.accept(empresa); paneles[0]++; }

        // TODO 4: Panel "Barras de Experiencia":
        //   nombre: ████████ (N anios)
        //   donde cada bloque = 1 anio, usando "#" repetido
        Consumer<List<Empleado>> panelBarras = null;
        System.out.println("\n" + sep);
        System.out.println("  PANEL 4: EXPERIENCIA (BARRAS)");
        System.out.println(sep);
        if (panelBarras != null) { panelBarras.accept(empresa); paneles[0]++; }

        // TODO 5: Panel "Alertas" usando Predicate:
        //   - Empleados sin email
        //   - Empleados inactivos
        //   - Empleados con salario < media
        Consumer<List<Empleado>> panelAlertas = null;
        System.out.println("\n" + sep);
        System.out.println("  PANEL 5: ALERTAS");
        System.out.println(sep);
        if (panelAlertas != null) { panelAlertas.accept(empresa); paneles[0]++; }

        // --- VALIDACION ---
        if (paneles[0] == 5) {
            System.out.println("\n" + sep);
            System.out.println(">> CORRECTO: Dashboard funcional completo. [OK]");
        } else {
            System.err.println("\n>> [ERROR] Completa los 5 paneles del dashboard.");
        }
    }
}
