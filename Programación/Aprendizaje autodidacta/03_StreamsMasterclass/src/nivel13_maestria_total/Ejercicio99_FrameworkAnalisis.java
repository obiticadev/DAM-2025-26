package nivel13_maestria_total;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import modelos.Empleado;
import modelos.Pedido;
import modelos.Producto;

/**
 * EJERCICIO 99 - FRAMEWORK DE ANALISIS COMPLETO
 * 
 * SIN GUIA. Construye un framework que combine TODAS las tecnicas funcionales
 * en un sistema de analisis de datos empresariales multi-modelo.
 */
public class Ejercicio99_FrameworkAnalisis {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 99: FRAMEWORK DE ANALISIS ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 99: FRAMEWORK COMPLETO ---");
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
        Producto cafe = new Producto("Cafe Premium", "Alimentacion", 12.0, 500, true);

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("P001", "Ana Garcia", Arrays.asList(laptop, raton), false));
        pedidos.add(new Pedido("P002", "Marta Lopez", Arrays.asList(camiseta, cafe, monitor), true));
        pedidos.add(new Pedido("P003", "Marcos Vila", Arrays.asList(laptop, raton, camiseta), false));

        int[] modulos = {0};

        // TODO 1: Modulo "Filtrado Avanzado":
        //   Crea un Predicate compuesto con .and/.or/.negate.
        //   Filtra: (activo AND senior) OR (salario >= 65000)
        //   Muestra con forEach(System.out::println)
        System.out.println("[Modulo 1: Filtrado avanzado]");
        // <- Escribe aqui
        // modulos[0]++;

        // TODO 2: Modulo "Transformacion":
        //   Crea un pipeline de Function: Empleado -> nombre -> UPPER -> "[NOMBRE]"
        //   Aplica a activos ordenados por salario DESC, top 5.
        System.out.println("\n[Modulo 2: Transformacion]");
        // <- Escribe aqui
        // modulos[0]++;

        // TODO 3: Modulo "Agrupacion":
        //   groupingBy depto con downstream: counting, averagingDouble(salario), joining(nombres)
        //   Muestra con Map.forEach(BiConsumer)
        System.out.println("\n[Modulo 3: Agrupacion]");
        // <- Escribe aqui
        // modulos[0]++;

        // TODO 4: Modulo "Cruce de datos":
        //   Para cada pedido, busca si el cliente es empleado.
        //   Si lo es, muestra: "clienteNombre (depto) -> pedidoId: producto1, producto2"
        //   Usa flatMap sobre pedidos + filter sobre empresa.
        System.out.println("\n[Modulo 4: Cruce]");
        // <- Escribe aqui
        // modulos[0]++;

        // TODO 5: Modulo "Parallel + Reduce":
        //   Usa parallelStream().reduce() para sumar salarios de activos.
        //   Compara con la suma secuencial para verificar.
        System.out.println("\n[Modulo 5: Parallel]");
        // <- Escribe aqui
        // modulos[0]++;

        // --- VALIDACION ---
        if (modulos[0] == 5) {
            System.out.println("\n>> CORRECTO: Framework de analisis completado. [OK]");
        } else {
            System.err.println("\n>> [ERROR] Completa los 5 modulos.");
        }
    }
}
