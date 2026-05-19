package com.masterclass.api.b17_nosql;

import java.util.List;
import java.util.Map;

/**
 * Ejercicio 153 · Pipeline de agregación ($match, $group, $sort).
 *
 * <p>Teoría: {@code teoria/17_NoSQL_MongoDB.md} (sección 17.5).
 *
 * <p>Simulamos un pipeline en memoria: filtramos pedidos por total mínimo
 * ($match), agrupamos por cliente sumando el total ($group) y ordenamos el
 * resultado por suma descendente ($sort).
 */
public final class Ej153AggregationPipeline {

    private Ej153AggregationPipeline() {
    }

    /**
     * Ejecuta el pipeline $match → $group → $sort sobre la colección.
     *
     * @param pedidos     colección de pedidos
     * @param totalMinimo umbral inclusivo del $match sobre total
     * @return lista de {cliente, sumaTotal} ordenada por sumaTotal desc
     * @throws IllegalArgumentException si pedidos es null
     */
    public static List<Map<String, Object>> agregar(List<Pedido149> pedidos, double totalMinimo) {
        // TODO 1: si pedidos es null -> IllegalArgumentException.
        // TODO 2: etapa $match: descarta pedidos con total < totalMinimo.
        // TODO 3: etapa $group: agrupa por pedido.cliente() como _id del grupo.
        // TODO 4: dentro del grupo acumula la suma de total ($sum: "$total").
        // TODO 5: representa cada grupo como un LinkedHashMap {cliente, sumaTotal}.
        // TODO 6: etapa $sort: ordena los grupos por sumaTotal DESCENDENTE.
        // TODO 7: en empate de sumaTotal, ordena por cliente ascendente (orden estable).
        // TODO 8: la salida de cada etapa es la entrada de la siguiente (pipeline).
        // TODO 9: si tras $match no queda nada, devuelve lista vacía.
        // TODO 10: retorna la lista de documentos agregados.
        return List.of();
    }

    public static void main(String[] args) {
        List<Pedido149> col = List.of(
                new Pedido149("a", "ana", 100),
                new Pedido149("b", "ana", 50),
                new Pedido149("c", "leo", 200));
        System.out.println(agregar(col, 40));
    }
}
