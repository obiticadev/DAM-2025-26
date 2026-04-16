package nivel13_maestria_total;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import modelos.Empleado;
import modelos.Pedido;
import modelos.Producto;

/**
 * ============================================================================
 *  EJERCICIO 100 - GRAN DESAFIO FINAL: MAESTRIA TOTAL EN STREAMS
 * ============================================================================
 * 
 * SIN PISTAS. Este es el desafio definitivo. Demuestra todo lo aprendido
 * en 100 ejercicios creando un SISTEMA COMPLETO DE GESTION EMPRESARIAL.
 * 
 * Debes usar:
 *   - Streams (filter, map, flatMap, reduce, sorted, distinct, limit, peek)
 *   - Collectors (groupingBy, partitioningBy, toMap, joining, summarizing,
 *     collectingAndThen, mapping, counting, downstream)
 *   - Optional (map, flatMap, orElse, ifPresent)
 *   - Method references (estaticos, de instancia, de tipo)
 *   - Interfaces funcionales (Predicate, Function, Consumer, Supplier,
 *     BiFunction, BiConsumer, UnaryOperator, BinaryOperator)
 *   - Composicion funcional (andThen, compose, and, or, negate)
 *   - Parallel streams
 *   - Consumer.andThen para templates de informes
 *   - Map.forEach, compute, merge
 *   - forEach con method references para pintado en pantalla
 *   - Patron Strategy, Decorator y Template Method funcionales
 *   - Interfaces funcionales custom (@FunctionalInterface)
 *   - Stream.concat, Supplier<Stream> reutilizable
 *   - Validacion con Predicate compuestos
 * 
 * NO HAY VALIDACION AUTOMATICA. Tu codigo debe funcionar y mostrar
 * resultados coherentes. Revisa tu output manualmente.
 */
public class Ejercicio100_GranDesafioFinal {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 100: GRAN DESAFIO FINAL ===");
        System.out.println("=== MAESTRIA TOTAL EN STREAMS ===\n");
        System.out.println("No hay demostracion. Crea todo tu solo.\n");
    }

    public static void ejercicio() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("  GRAN DESAFIO FINAL - SISTEMA DE GESTION EMPRESARIAL");
        System.out.println("=".repeat(60));

        // === DATOS ===
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana Garcia", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Perez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta Lopez", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucia Martin", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));
        empresa.add(new Empleado("Sara Diaz", "Data", "Python", 4, 42000, true, "sara@corp.com"));
        empresa.add(new Empleado("Javier Ruiz", "Frontend", "TypeScript", 1, 28000, true, "javier@corp.com"));

        Producto laptop = new Producto("Laptop Pro", "Electronica", 1200.0, 15, true);
        Producto raton = new Producto("Raton Gamer", "Electronica", 45.0, 120, true);
        Producto camiseta = new Producto("Camiseta Dev", "Ropa", 18.0, 200, true);
        Producto monitor = new Producto("Monitor 4K", "Electronica", 350.0, 8, true);
        Producto teclado = new Producto("Teclado Mecanico", "Electronica", 89.0, 50, true);
        Producto cafe = new Producto("Cafe Premium", "Alimentacion", 12.0, 500, true);
        Producto libro = new Producto("Clean Code", "Libros", 35.0, 80, true);

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("P001", "Ana Garcia", Arrays.asList(laptop, raton, libro), false));
        pedidos.add(new Pedido("P002", "Marta Lopez", Arrays.asList(camiseta, cafe, monitor), true));
        pedidos.add(new Pedido("P003", "Marcos Vila", Arrays.asList(laptop, raton, camiseta), false));
        pedidos.add(new Pedido("P004", "Lucia Martin", Arrays.asList(libro, cafe, teclado), false));
        pedidos.add(new Pedido("P005", "Ana Garcia", Arrays.asList(teclado, monitor), true));
        pedidos.add(new Pedido("P006", "Sara Diaz", Arrays.asList(cafe, camiseta, libro), true));

        // ============================================================
        // SECCION 1: DASHBOARD EJECUTIVO
        // ============================================================
        // TODO 1: Crea un dashboard con 5 paneles usando Consumer<List<Empleado>>:
        //   Panel A: Resumen (total, activos, masa salarial, media)
        //   Panel B: Top 3 por salario con barras visuales
        //   Panel C: Distribucion por departamento (personas, media, lenguajes)
        //   Panel D: Alertas (sin email, inactivos, salario bajo)
        //   Panel E: Medallas (oro/plata/bronce por experiencia)
        // Ensambla todo con andThen y ejecuta.
        System.out.println("\n--- SECCION 1: DASHBOARD EJECUTIVO ---");
        // <- Escribe aqui

        // ============================================================
        // SECCION 2: MOTOR DE CONSULTAS SQL
        // ============================================================
        // TODO 2: Ejecuta estas "queries" usando Predicate, Comparator, Function:
        //   Q1: SELECT nombre, salario FROM empresa WHERE activo ORDER BY salario DESC LIMIT 5
        //   Q2: SELECT depto, COUNT(*), AVG(salario) FROM empresa GROUP BY depto
        //   Q3: SELECT DISTINCT lenguaje FROM empresa WHERE activo ORDER BY lenguaje
        //   Q4: SELECT nombre FROM empresa WHERE salario > (SELECT AVG(salario) FROM empresa)
        System.out.println("\n--- SECCION 2: MOTOR DE CONSULTAS ---");
        // <- Escribe aqui

        // ============================================================
        // SECCION 3: ANALISIS DE VENTAS
        // ============================================================
        // TODO 3: Usando los pedidos y productos:
        //   a) Facturacion total (flatMap + mapToDouble + sum)
        //   b) Producto mas vendido (flatMap + groupingBy + counting)
        //   c) Ranking de categorias por facturacion
        //   d) Clientes con mas gasto total
        //   e) Pedidos pendientes vs entregados (partitioningBy)
        System.out.println("\n--- SECCION 3: ANALISIS DE VENTAS ---");
        // <- Escribe aqui

        // ============================================================
        // SECCION 4: CRUCE EMPLEADOS-CLIENTES
        // ============================================================
        // TODO 4: Para cada empleado que sea cliente:
        //   Muestra: "nombre (depto, salarioEUR) -> N pedidos, gastoEUR, ratio gasto/salario"
        //   Ordena por ratio DESC.
        //   Usa Map<String, List<Pedido>> + empresa.stream().filter(...)
        System.out.println("\n--- SECCION 4: CRUCE EMPLEADOS-CLIENTES ---");
        // <- Escribe aqui

        // ============================================================
        // SECCION 5: PIPELINE FUNCIONAL AVANZADO
        // ============================================================
        // TODO 5:
        //   a) Crea un Validador custom con metodos and/or/negate
        //   b) Crea decoradores con UnaryOperator.andThen
        //   c) Usa BiFunction para calcular bonus por empleado+porcentaje
        //   d) Usa Supplier<Stream<Empleado>> para 3 consultas distintas
        //   e) Usa parallelStream para sumar todos los salarios
        System.out.println("\n--- SECCION 5: PIPELINE FUNCIONAL ---");
        // <- Escribe aqui

        // ============================================================
        // SECCION 6: REPORTE FINAL FORMATEADO
        // ============================================================
        // TODO 6: Genera un reporte final con:
        //   - Cabecera con titulo y fecha (usa String.format)
        //   - Tabla formateada con printf: nombre | depto | salario | exp
        //   - Separadores con "-".repeat()
        //   - Totales y promedios al final
        //   - Pie con mensaje de cierre
        //   Todo usando Consumer<List<Empleado>> compuesto con andThen.
        //   Imprime con forEach + method references donde sea posible.
        System.out.println("\n--- SECCION 6: REPORTE FINAL ---");
        // <- Escribe aqui

        System.out.println("\n" + "=".repeat(60));
        System.out.println("  Si has completado las 6 secciones, FELICIDADES!");
        System.out.println("  Has dominado los Streams de Java.");
        System.out.println("=".repeat(60));
    }
}
