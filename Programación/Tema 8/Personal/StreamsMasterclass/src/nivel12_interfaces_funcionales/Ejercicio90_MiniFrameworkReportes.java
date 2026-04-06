package nivel12_interfaces_funcionales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import modelos.Empleado;
import modelos.Pedido;
import modelos.Producto;

/**
 * EJERCICIO 90 - MINI-FRAMEWORK DE REPORTES
 * 
 * SIN GUIA. Usa TODAS las interfaces funcionales aprendidas para crear
 * un mini-framework de reportes configurable.
 */
public class Ejercicio90_MiniFrameworkReportes {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 90: MINI-FRAMEWORK REPORTES ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 90: FRAMEWORK DE REPORTES ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana Garcia", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Perez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta Lopez", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucia Martin", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        Producto laptop = new Producto("Laptop Pro", "Electronica", 1200.0, 15, true);
        Producto raton = new Producto("Raton Gamer", "Electronica", 45.0, 120, true);
        Producto camiseta = new Producto("Camiseta Dev", "Ropa", 18.0, 200, true);
        Producto monitor = new Producto("Monitor 4K", "Electronica", 350.0, 8, true);

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("P001", "Ana Garcia", Arrays.asList(laptop, raton), false));
        pedidos.add(new Pedido("P002", "Marta Lopez", Arrays.asList(camiseta, monitor), true));
        pedidos.add(new Pedido("P003", "Marcos Vila", Arrays.asList(laptop, raton, camiseta), false));

        int[] reportes = {0};

        // TODO 1: Crea un "Reporte de RRHH" usando:
        //   - Predicate: filtrar activos
        //   - Function: Empleado -> "nombre | depto | salarioEUR"
        //   - Consumer cabecera: imprime titulo y separador
        //   - Consumer cuerpo: filtra, transforma y imprime cada linea
        //   - Consumer pie: total empleados, masa salarial, media
        //   - Ensambla con andThen
        System.out.println("[REPORTE RRHH]");
        // <- Escribe aqui
        // reportes[0]++;

        // TODO 2: Crea un "Reporte de Ventas" usando:
        //   - flatMap para extraer todos los productos de todos los pedidos
        //   - groupingBy por categoria con summing del precio
        //   - BiConsumer para imprimir cada entrada del mapa
        //   - Totales: facturacion total, pedidos pendientes, entregados
        System.out.println("\n[REPORTE VENTAS]");
        // <- Escribe aqui
        // reportes[0]++;

        // TODO 3: Crea un "Reporte Cruzado" que:
        //   - Para cada cliente que tiene pedidos, busque su info de empleado
        //   - Imprima: "nombre (depto, salarioEUR) -> N pedidos, totalEUR"
        //   - Use Map.forEach con BiConsumer
        System.out.println("\n[REPORTE CRUZADO]");
        // <- Escribe aqui
        // reportes[0]++;

        // --- VALIDACION ---
        if (reportes[0] == 3) {
            System.out.println("\n>> CORRECTO: Mini-framework de reportes completado. [OK]");
        } else {
            System.err.println("\n>> [ERROR] Genera los 3 reportes.");
        }
    }
}
