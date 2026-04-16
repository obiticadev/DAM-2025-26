package nivel6_operaciones_avanzadas_streams;

import modelos.DatosPrueba;
import modelos.Pedido;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * EJERCICIO 23 - DOWNSTREAM COLLECTORS: SUMMING
 * 
 * Objetivo: En lugar de guardar el objeto en la lista, usamos una operación
 * matemática (summingDouble, summingInt, averaging...) para agrupar en totales.
 */
public class Ejercicio23_GroupingAndSumming {

    public static void demostracion() {
        System.out.println("--- DEMO: GROUPING BY + SUMMING ---");
        List<Pedido> ventas = DatosPrueba.obtenerPedidos();

        // Sumar TODO el dinero generado por cada estado (Pendiente, Entregado, etc.)
        Map<String, Double> dineroPorEstado = ventas.stream()
                .collect(Collectors.groupingBy(
                        Pedido::getEstado,
                        Collectors.summingDouble(Pedido::getTotal)
                ));

        System.out.println("Dinero retenido por el estado de envío: " + dineroPorEstado);
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 23: GANANCIAS GLOBALES POR CLIENTE ---");
        List<Pedido> facturas = DatosPrueba.obtenerPedidos();

        // TODO 1: Crea un Map<String, Double> donde las llaves sean los Clientes 
        // y el Valor sea la SUMA TOTAL del importe (.getTotal) de TODOS los pedidos de ese cliente.
        // Usa groupingBy y summingDouble
        Map<String, Double> gananciaPorCliente = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Averigua cuánto se le ha facturado en total al "Cliente C" accediendo al mapa y guárdalo.
        // Usa getOrDefault() y asígnale 0.0 si te devuelve null.
        Double facturadoC = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = gananciaPorCliente != null && gananciaPorCliente.size() == 3;
        boolean ok2 = facturadoC != null && facturadoC == 450.75;
        // Cliente A total: 250(ENTREGADO) + 1200(PENDIENTE) = 1450.0
        boolean ok3 = gananciaPorCliente != null && gananciaPorCliente.get("Cliente A") == 1450.0;

        if (ok1 && ok2 && ok3) {
            System.out.println(">> CORRECTO: SummingDouble es como la cláusula SUM(col) agrupada de SQL.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa el summingDouble. 'Cliente A' debe tener acumulados 1450.0");
        }
    }
}
