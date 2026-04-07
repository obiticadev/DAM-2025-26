package nivel6_operaciones_avanzadas_streams;

import modelos.DatosPrueba;
import modelos.Pedido;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * EJERCICIO 22 - DOWNSTREAM COLLECTORS: COUNTING
 * 
 * Objetivo: Sustituir el List<V> devuelto por defecto en groupingBy
 * por la cantidad de elementos usando Collectors.counting().
 */
public class Ejercicio22_GroupingAndCounting {

    public static void demostracion() {
        System.out.println("--- DEMO: GROUPING BY + COUNTING ---");
        List<Pedido> bdd = DatosPrueba.obtenerPedidos();

        // Map donde la llave es el Estado y el valor es un Long (la cantidad)
        Map<String, Long> volumenPorEstado = bdd.stream()
                .collect(Collectors.groupingBy(
                        Pedido::getEstado,
                        Collectors.counting()
                ));

        System.out.println("Volumen por estado: " + volumenPorEstado);
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 22: CONTEO DE MATRÍCULAS POR CLIENTE ---");
        List<Pedido> historial = DatosPrueba.obtenerPedidos();

        // TODO 1: Queremos saber CUÁNTOS pedidos ha hecho cada cliente.
        // Llave: Cliente (String), Valor: Cantidad de Pedidos (Long)
        // Usa groupingBy con downstream counting.
        Map<String, Long> fidelidadClientes = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Como buen analista, lee el mapa e imprime (forEach) la información de manera legible:
        // "Cliente X ha realizado Y pedidos."
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = fidelidadClientes != null && fidelidadClientes.get("Cliente A") == 2L;
        boolean ok2 = fidelidadClientes != null && !fidelidadClientes.containsKey("Cliente D");

        if (ok1 && ok2) {
            System.out.println(">> CORRECTO: Agrupar y contar es la métrica de negocio más solicitada. \033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa el código del collector por el conteo. 'Cliente A' debe tener 2 pedidos.");
        }
    }
}
