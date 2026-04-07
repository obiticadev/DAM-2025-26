package nivel5_map_y_streams;

import modelos.DatosPrueba;
import modelos.Pedido;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * EJERCICIO 19 - EL GROUP BY DE SQL A JAVA: groupingBy
 * 
 * Objetivo: Entender cómo clasificar una lista plana en un diccionario de Listas
 * basado en una propiedad en común (Ej: Agrupar pedidos por Estado).
 */
public class Ejercicio19_GroupingBy {

    public static void demostracion() {
        System.out.println("--- DEMO: Collectors.groupingBy() ---");
        List<Pedido> bdd = DatosPrueba.obtenerPedidos();

        // SQL: SELECT * FROM Pedidos GROUP BY estado;
        Map<String, List<Pedido>> pedidosPorEstado = bdd.stream()
                .collect(Collectors.groupingBy(Pedido::getEstado));

        System.out.println("Pedidos ENTREGADOS:");
        pedidosPorEstado.get("ENTREGADO").forEach(p -> System.out.println("  - " + p.getIdPedido()));
        
        System.out.println("Pedidos PENDIENTES:");
        pedidosPorEstado.get("PENDIENTE").forEach(p -> System.out.println("  - " + p.getIdPedido()));
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 19: FACTURACIÓN POR CLIENTE ---");
        List<Pedido> historial = DatosPrueba.obtenerPedidos();

        // TODO 1: Crea un Map<String, List<Pedido>> llamado 'pedidosPorCliente'
        // donde la llave sea el Nombre del Cliente ("Cliente A", "Cliente B"...) 
        // y el valor sea toda su lista de pedidos asociados.
        Map<String, List<Pedido>> pedidosPorCliente = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Acabas de crear un potente sistema de consultas.
        // Extrae todos los pedidos del "Cliente B" y cuenta visualmente mediante un forEach (o size) cuántos tiene.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = pedidosPorCliente != null && pedidosPorCliente.containsKey("Cliente A");
        boolean ok2 = pedidosPorCliente != null && pedidosPorCliente.get("Cliente B").size() == 2;
        boolean ok3 = pedidosPorCliente != null && pedidosPorCliente.get("Cliente C").size() == 1;

        if (ok1 && ok2 && ok3) {
            System.out.println(">> CORRECTO: groupingBy() es la función más poderosa y usada de todo java.util.stream.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Asegúrate de haber llamado a Collectors.groupingBy(Pedido::getCliente).");
        }
    }
}
