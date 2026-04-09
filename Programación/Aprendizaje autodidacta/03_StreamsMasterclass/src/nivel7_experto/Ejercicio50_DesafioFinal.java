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
 * EJERCICIO 50 — DESAFÍO FINAL: SISTEMA DE GESTIÓN COMPLETO
 * 
 * SIN PISTAS. El ejercicio más complejo de todos.
 * Demuestra dominio absoluto de Streams, Optional, Lambdas y Method References.
 */
public class Ejercicio50_DesafioFinal {

    public static void demostracion() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║     DESAFÍO FINAL — EJERCICIO 50: SISTEMA COMPLETO      ║");
        System.out.println("║  Si completas este ejercicio, dominas Java Streams.      ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- DESAFÍO 50: INFORME INTEGRAL DE LA EMPRESA ---");

        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Pérez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        Producto laptop = new Producto("Laptop Pro", "Electrónica", 1200.0, 15, true);
        Producto raton = new Producto("Ratón Gamer", "Electrónica", 45.0, 120, true);
        Producto camiseta = new Producto("Camiseta Dev", "Ropa", 18.0, 200, true);
        Producto cafe = new Producto("Café Premium", "Alimentación", 12.0, 500, true);
        Producto monitor = new Producto("Monitor 4K", "Electrónica", 350.0, 8, true);

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("P001", "Ana García", Arrays.asList(laptop, raton), false));
        pedidos.add(new Pedido("P002", "Marta López", Arrays.asList(camiseta, cafe, monitor), true));
        pedidos.add(new Pedido("P003", "Ana García", Arrays.asList(cafe, raton), false));
        pedidos.add(new Pedido("P004", "Lucía Martín", Arrays.asList(laptop, monitor), true));
        pedidos.add(new Pedido("P005", "Elena Torres", Arrays.asList(camiseta, cafe), false));

        // ===== TODO 1: SECCIÓN EMPLEADOS =====
        // Departamento con mayor salario MEDIO entre empleados activos
        String deptoMejorPagado = ""; // <- Escribe aquí

        // Empleado activo con más experiencia que use Java
        String javaExpert = ""; // <- Escribe aquí

        // Lista de lenguajes únicos usados por activos, ordenados A-Z, como string separado por ", "
        String lenguajes = ""; // <- Escribe aquí

        // ===== TODO 2: SECCIÓN PEDIDOS =====
        // Nombre del cliente con más pedidos
        String clienteFrecuente = ""; // <- Escribe aquí

        // Producto más vendido (aparece en más pedidos) - nombre
        String productoEstrella = ""; // <- Escribe aquí

        // Ingreso total de todos los pedidos
        double ingresoTotal = 0; // <- Escribe aquí

        // ===== TODO 3: SECCIÓN CRUZADA =====
        // Lista de empleados activos que NO han hecho ningún pedido (nombres ordenados A-Z)
        List<String> sinPedidos = null; // <- Escribe aquí

        // ===== VALIDACIÓN FINAL =====
        int score = 0;
        if (deptoMejorPagado.equals("Data")) { score++; System.out.println("  [1/7] Depto mejor pagado: OK"); }
        else System.err.println("  [1/7] FALLO: deptoMejorPagado=" + deptoMejorPagado + " (esperado: Data)");

        if (javaExpert.equals("Ana García")) { score++; System.out.println("  [2/7] Java expert: OK"); }
        else System.err.println("  [2/7] FALLO: javaExpert=" + javaExpert + " (esperado: Ana García)");

        if (lenguajes.equals("Go, Java, JavaScript, Python, Rust")) { score++; System.out.println("  [3/7] Lenguajes: OK"); }
        else System.err.println("  [3/7] FALLO: lenguajes=" + lenguajes + " (esperado: Go, Java, JavaScript, Python, Rust)");

        if (clienteFrecuente.equals("Ana García")) { score++; System.out.println("  [4/7] Cliente frecuente: OK"); }
        else System.err.println("  [4/7] FALLO: clienteFrecuente=" + clienteFrecuente + " (esperado: Ana García)");

        if (productoEstrella.equals("Café Premium")) { score++; System.out.println("  [5/7] Producto estrella: OK"); }
        else System.err.println("  [5/7] FALLO: productoEstrella=" + productoEstrella + " (esperado: Café Premium)");

        double expectedTotal = (1200+45) + (18+12+350) + (12+45) + (1200+350) + (18+12);
        if (ingresoTotal == expectedTotal) { score++; System.out.println("  [6/7] Ingreso total: OK"); }
        else System.err.println("  [6/7] FALLO: ingresoTotal=" + ingresoTotal + " (esperado: " + expectedTotal + ")");

        if (sinPedidos != null && sinPedidos.size() == 4
                && sinPedidos.get(0).equals("Carlos Ruiz") && sinPedidos.contains("Luis Pérez")
                && sinPedidos.contains("Marcos Vila")) {
            score++; System.out.println("  [7/7] Sin pedidos: OK");
        } else {
            System.err.println("  [7/7] FALLO: sinPedidos esperado=[Carlos Ruiz, Luis Pérez, Marcos Vila, Pedro Sanz? -> solo activos]");
        }

        System.out.println("\n========================================");
        System.out.println("  PUNTUACIÓN FINAL: " + score + "/7");
        if (score == 7) {
            System.out.println("  ¡¡¡ENHORABUENA!!! Has completado la Streams Masterclass.");
            System.out.println("  Dominas Lambdas, Streams, Optional y Method References.");
            System.out.println("\033[0;32m  >>> MASTERCLASS COMPLETADA <<<\033[0m");
        } else if (score >= 5) {
            System.out.println("  ¡Muy bien! Casi lo tienes. Revisa los fallos.");
        } else {
            System.out.println("  Sigue practicando. Repasa la teoría y los ejercicios anteriores.");
        }
        System.out.println("========================================");
    }
}
