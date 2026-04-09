package nivel13_maestria_total;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import modelos.Empleado;
import modelos.Pedido;
import modelos.Producto;

/**
 * EJERCICIO 97 - PROCESADOR DE DATOS MULTI-FUENTE
 * 
 * SIN GUIA. Procesa datos de multiples fuentes (empleados, pedidos, productos)
 * combinandolos con Stream.concat, flatMap, y Supplier para reutilizar streams.
 */
public class Ejercicio97_ProcesadorMultiFuente {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 97: PROCESADOR MULTI-FUENTE ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 97: COMBINA MULTIPLES FUENTES ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana Garcia", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Perez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Marta Lopez", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucia Martin", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));

        Producto laptop = new Producto("Laptop Pro", "Electronica", 1200.0, 15, true);
        Producto raton = new Producto("Raton Gamer", "Electronica", 45.0, 120, true);
        Producto camiseta = new Producto("Camiseta Dev", "Ropa", 18.0, 200, true);
        Producto monitor = new Producto("Monitor 4K", "Electronica", 350.0, 8, true);
        Producto teclado = new Producto("Teclado Mecanico", "Electronica", 89.0, 50, true);
        Producto cafe = new Producto("Cafe Premium", "Alimentacion", 12.0, 500, true);

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("P001", "Ana Garcia", Arrays.asList(laptop, raton), false));
        pedidos.add(new Pedido("P002", "Marta Lopez", Arrays.asList(camiseta, cafe, monitor), true));
        pedidos.add(new Pedido("P003", "Lucia Martin", Arrays.asList(raton, cafe, teclado), false));
        pedidos.add(new Pedido("P004", "Marcos Vila", Arrays.asList(laptop, monitor, camiseta), false));
        pedidos.add(new Pedido("P005", "Ana Garcia", Arrays.asList(teclado, monitor), false));

        int[] secciones = {0};

        // TODO 1: Usa Stream.concat para unir nombres de empleados y nombres de clientes
        // de pedidos. Obtener DISTINCT y ordenar alfabeticamente.
        System.out.println("[Todos los nombres unicos]");
        // <- Escribe aqui
        // secciones[0]++;

        // TODO 2: Crea un Supplier<Stream<Producto>> que devuelva todos los productos
        // de todos los pedidos (flatMap). Usalo 3 veces:
        //   a) Contar productos totales vendidos
        //   b) Facturacion total (sum de precios)
        //   c) Producto mas caro vendido
        Supplier<Stream<Producto>> todosProductos = null; // <- Escribe aqui
        System.out.println("\n[Analisis de productos vendidos]");
        // <- Usa todosProductos.get() 3 veces
        // secciones[0]++;

        // TODO 3: Para cada empleado que es cliente, calcula:
        //   "nombre | depto | salarioEUR | N pedidos | gastoTotalEUR | ratio gasto/salario"
        // Usa Map<String, List<Pedido>> para agrupar pedidos por cliente.
        System.out.println("\n[Empleados-clientes cruzados]");
        // <- Escribe aqui
        // secciones[0]++;

        // TODO 4: Genera un "ranking de categorias" con:
        //   categoria -> unidades vendidas (sum de ocurrencias), facturacion
        // Ordenado por facturacion DESC. Usa flatMap + groupingBy + summingDouble.
        System.out.println("\n[Ranking de categorias]");
        // <- Escribe aqui
        // secciones[0]++;

        // --- VALIDACION ---
        if (secciones[0] == 4) {
            System.out.println("\n>> CORRECTO: Procesador multi-fuente dominado. [OK]");
        } else {
            System.err.println("\n>> [ERROR] Completa las 4 secciones.");
        }
    }
}
