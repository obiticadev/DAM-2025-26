package nivel7_experto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import modelos.Empleado;
import modelos.Pedido;
import modelos.Producto;

/**
 * EJERCICIO 48 — RETO EXPERTO: PIPELINE MULTI-MODELO
 * 
 * SIN PISTAS. Cruza datos entre Empleados y Pedidos.
 */
public class Ejercicio48_PipelineMultiModelo {

    public static void demostracion() {
        System.out.println("=== RETO EXPERTO 48: PIPELINE MULTI-MODELO ===");
        System.out.println("Combina datos de Empleados y Pedidos en un solo análisis.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 48: CRUZAR EMPLEADOS CON SUS PEDIDOS ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Carlos", "Frontend", "JavaScript", 2, 32000, true, "carlos@corp.com"));
        empresa.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));

        Producto laptop = new Producto("Laptop Pro", "Electrónica", 1200.0, 15, true);
        Producto raton = new Producto("Ratón Gamer", "Electrónica", 45.0, 120, true);
        Producto cafe = new Producto("Café Premium", "Alimentación", 12.0, 500, true);

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("P001", "Ana", Arrays.asList(laptop, raton), false));
        pedidos.add(new Pedido("P002", "Carlos", Arrays.asList(cafe), true));
        pedidos.add(new Pedido("P003", "Ana", Arrays.asList(cafe, raton), false));
        pedidos.add(new Pedido("P004", "Lucía", Arrays.asList(laptop), true));

        // TODO 1: Para cada empleado activo, calcula cuánto ha gastado en total en pedidos.
        // Resultado: Map<String, Double> nombre -> total gastado
        Map<String, Double> gastoPorEmpleado = null; // <- Escribe aquí

        // TODO 2: Lista de nombres de empleados que han comprado "Laptop Pro", ordenados A-Z
        List<String> compradoresLaptop = null; // <- Escribe aquí

        // TODO 3: Empleado activo que más ha gastado (su nombre)
        String mayorGastador = ""; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = gastoPorEmpleado != null
                && gastoPorEmpleado.get("Ana") == (1200 + 45 + 12 + 45)
                && gastoPorEmpleado.get("Carlos") == 12.0;
        boolean v2 = compradoresLaptop != null && compradoresLaptop.size() == 2
                && compradoresLaptop.get(0).equals("Ana") && compradoresLaptop.get(1).equals("Lucía");
        boolean v3 = mayorGastador.equals("Ana");

        if (v1 && v2 && v3) {
            System.out.println(">> CORRECTO: Pipeline multi-modelo cruzando Empleados y Pedidos.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Ana gastó 1302, Carlos 12, Lucía 1200. Compradores laptop=[Ana,Lucía]. Mayor=Ana.");
        }
    }
}
