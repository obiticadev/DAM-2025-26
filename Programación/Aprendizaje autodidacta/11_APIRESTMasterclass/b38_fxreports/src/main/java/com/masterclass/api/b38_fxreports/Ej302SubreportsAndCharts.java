package com.masterclass.api.b38_fxreports;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Ejercicio 302 · Subinformes y gráficos embebidos.
 *
 * <p>Teoría: {@code teoria/38_Informes_PDF.md} (sección 4).
 *
 * <p>Un <b>subinforme</b> es un informe dentro de otro: el informe maestro recorre pedidos y, por
 * cada uno, incrusta un subinforme con SUS líneas (relación maestro-detalle). Un <b>gráfico
 * embebido</b> es un {@code BarChart}/{@code PieChart} dentro de la banda <i>summary</i> alimentado
 * por datos AGREGADOS. En ambos casos la clave es la misma operación de datos: <b>filtrar</b> las
 * líneas que pertenecen a un maestro (la fuente del subinforme) y <b>agregar</b> por una dimensión
 * (pedido, mes) para el gráfico. Aquí el core hace ese filtrado y esa agregación con lógica pura;
 * el {@code .jrxml} del subinforme y el {@code <barChart>} se enseñan en la teoría.
 */
public final class Ej302SubreportsAndCharts {

    private Ej302SubreportsAndCharts() {
    }

    /**
     * Una línea de pedido: {@code pedidoId} la enlaza con su pedido maestro, {@code mes} es la
     * dimensión temporal para el gráfico, {@code producto} el artículo e {@code importe} su valor.
     */
    public record ItemDto(int pedidoId, String mes, String producto, double importe) {
    }

    /**
     * Líneas de un pedido: la fuente de datos del SUBINFORME que se incrusta en la fila maestra de
     * ese pedido. Conserva el orden original.
     *
     * @param pedidoId id del pedido maestro
     * @param items     todas las líneas (puede ser null)
     * @return las líneas con ese {@code pedidoId}; {@code List.of()} sin implementar
     */
    public static List<ItemDto> itemsDePedido(int pedidoId, List<ItemDto> items) {
        // TODO 1: si items es null, devuelve List.of().
        // TODO 2: recorre y añade a una lista nueva las que it.pedidoId() == pedidoId.
        // TODO 3: devuelve la lista (el test: 2 líneas del pedido 1 sobre un total de 3 -> 2).
        return List.of();
    }

    /**
     * Total por pedido: suma de importes de cada pedido (el total que el subinforme devuelve a la
     * fila maestra). Orden de aparición de los pedidos.
     *
     * @param items todas las líneas (puede ser null)
     * @return mapa pedidoId -> total; {@code Map.of()} sin implementar
     */
    public static Map<Integer, Double> totalPorPedido(List<ItemDto> items) {
        // TODO 4: si items es null o vacía, devuelve Map.of().
        // TODO 5: crea un LinkedHashMap<Integer,Double> (orden de aparición de los pedidos).
        // TODO 6: por cada línea, mapa.merge(it.pedidoId(), it.importe(), Double::sum).
        // TODO 7: devuelve el mapa (el test: pedido 1 {10,20}, pedido 2 {5} -> {1=30.0, 2=5.0}).
        return Map.of();
    }

    /**
     * Ventas por mes: suma de importes agrupada por mes, en orden de aparición. Es el dataset que
     * alimenta el {@code BarChart} embebido en el informe.
     *
     * @param items todas las líneas (puede ser null)
     * @return mapa mes -> total vendido; {@code Map.of()} sin implementar
     */
    public static Map<String, Double> ventasPorMes(List<ItemDto> items) {
        // TODO 8: si items es null o vacía, devuelve Map.of().
        // TODO 9: LinkedHashMap<String,Double>; por cada línea mapa.merge(it.mes(), it.importe(), Double::sum).
        // TODO 10: devuelve el mapa (el test: Enero{10,20}, Febrero{5} -> {Enero=30.0, Febrero=5.0}).
        return Map.of();
    }

    public static void main(String[] args) {
        List<ItemDto> items = List.of(
                new ItemDto(1, "Enero", "Teclado", 10),
                new ItemDto(1, "Enero", "Ratón", 20),
                new ItemDto(2, "Febrero", "Monitor", 5));
        System.out.println("Items del pedido 1: " + itemsDePedido(1, items).size());
        System.out.println("Total por pedido: " + totalPorPedido(items));
        System.out.println("Ventas por mes: " + ventasPorMes(items));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Número de pedidos distintos.
     * Cuántas filas maestras tendrá el informe (pedidos distintos).
     */
    public static int numeroDePedidos(List<ItemDto> items) {
        // GUÍA: teoría 4.1 (cada pedido distinto = una fila maestra con su subinforme).
        // 1. Si items es null, devuelve 0.
        // 2. Mete cada it.pedidoId() en un Set<Integer> y devuelve su tamaño.
        // OJO: el test con pedidos [1,1,2] -> 2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para numeroDePedidos");
    }

    /**
     * Reto Extra 2: Total de un pedido concreto.
     * El total que muestra el subinforme de ese pedido (0 si no existe).
     */
    public static double totalDePedido(List<ItemDto> items, int pedidoId) {
        // GUÍA: teoría 4.2 (el campo "Total pedido" que el subinforme devuelve al maestro).
        // 1. Mira totalPorPedido(items).
        // 2. Devuelve getOrDefault(pedidoId, 0.0).
        // PISTA: reutiliza totalPorPedido(items).getOrDefault(pedidoId, 0.0).
        // OJO: el test: pedido 1 {10,5} -> 15.0; pedido 99 inexistente -> 0.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para totalDePedido");
    }

    /**
     * Reto Extra 3: Número de líneas de un pedido.
     * Cuántas filas pinta el subinforme de ese pedido.
     */
    public static int numeroDeItems(List<ItemDto> items, int pedidoId) {
        // GUÍA: teoría 4.2 (el subinforme puede mostrar "(2 artículos)" en su cabecera).
        // 1. Devuelve el tamaño de itemsDePedido(pedidoId, items).
        // PISTA: reutiliza itemsDePedido(pedidoId, items).size().
        // OJO: el test: pedido 1 -> 2; pedido 99 -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para numeroDeItems");
    }

    /**
     * Reto Extra 4: Mes con más ventas.
     * Qué barra del gráfico es la más alta, envuelto en Optional.
     */
    public static Optional<String> mesConMasVentas(List<ItemDto> items) {
        // GUÍA: teoría 4.3 (el "mes pico" que destaca el gráfico embebido).
        // 1. Calcula ventasPorMes(items). Si está vacío, Optional.empty().
        // 2. Halla la entrada de mayor valor y devuelve su clave en un Optional.
        // PISTA: ventas.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).
        // OJO: el test con Enero=30, Febrero=5 -> Optional[Enero]; vacío -> empty.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mesConMasVentas");
    }

    /**
     * Reto Extra 5: Pedido con más líneas.
     * El pedido cuyo subinforme es más largo (Optional con su id).
     */
    public static Optional<Integer> pedidoConMasItems(List<ItemDto> items) {
        // GUÍA: teoría 4.3 (detectar el pedido "grande" para, por ejemplo, paginarlo aparte).
        // 1. Si items es null o vacía, Optional.empty().
        // 2. Cuenta líneas por pedidoId (LinkedHashMap<Integer,Integer> con merge(...,1,Integer::sum)).
        // 3. Devuelve la clave de mayor conteo en un Optional.
        // OJO: el test: pedido 1 (2 líneas), pedido 2 (1) -> Optional[1].
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pedidoConMasItems");
    }

    /**
     * Reto Extra 6: Media de importe por pedido.
     * El ticket medio de cada pedido (total / nº de líneas).
     */
    public static Map<Integer, Double> mediaPorPedido(List<ItemDto> items) {
        // GUÍA: teoría 4.4 (campo AVERAGE del subinforme; el "importe medio por línea").
        // 1. Recorre totalPorPedido(items); para cada pedido divide su total entre numeroDeItems(...).
        // 2. Mete el resultado en un LinkedHashMap<Integer,Double>.
        // OJO: el test: pedido 1 {10,20} -> 15.0; pedido 2 {5} -> 5.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mediaPorPedido");
    }

    /**
     * Reto Extra 7: Porcentaje de ventas por mes.
     * El peso de cada mes sobre el total (para un PieChart embebido).
     */
    public static Map<String, Double> porcentajePorMes(List<ItemDto> items) {
        // GUÍA: teoría 4.5 (de barras absolutas a tarta de porcentajes, como en b37).
        // 1. Calcula ventasPorMes(items) y su total. Si el total es 0, devuelve Map.of().
        // 2. Para cada mes, porcentaje = venta / total * 100; mételo en un LinkedHashMap.
        // OJO: el test con Enero=30, Febrero=10 (total 40) -> {Enero=75.0, Febrero=25.0}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para porcentajePorMes");
    }

    /**
     * Reto Extra 8: Meses ordenados por ventas (desc).
     * El orden en que el gráfico mostraría las barras si las ordenas de mayor a menor.
     */
    public static List<String> mesesOrdenadosPorVenta(List<ItemDto> items) {
        // GUÍA: teoría 4.5 (un gráfico de barras ordenado = "ranking de meses").
        // 1. Vuelca ventasPorMes(items).entrySet() a una lista.
        // 2. Ordénala por valor DESCENDENTE y proyecta las claves.
        // PISTA: lista.sort(Map.Entry.<String,Double>comparingByValue().reversed()); luego .getKey().
        // OJO: el test con Enero=30, Febrero=5, Marzo=50 -> [Marzo, Enero, Febrero].
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mesesOrdenadosPorVenta");
    }

    /**
     * Reto Extra 9: Productos distintos (leyenda del gráfico).
     * La lista de productos sin repetir, en orden de primera aparición.
     */
    public static List<String> productosDistintos(List<ItemDto> items) {
        // GUÍA: teoría 4.6 (la leyenda de un gráfico = las categorías únicas que aparecen).
        // 1. Si items es null, devuelve List.of().
        // 2. Usa un LinkedHashSet<String> con cada it.producto() (quita duplicados, conserva orden).
        // 3. Devuelve new ArrayList<>(set).
        // OJO: el test con [Teclado, Ratón, Teclado] -> [Teclado, Ratón].
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para productosDistintos");
    }

    /**
     * Reto Extra 10: Acumulado de ventas según un orden de meses.
     * La suma corrida de ventas siguiendo el orden de meses dado (línea de acumulado del gráfico).
     */
    public static List<Double> acumuladoMensual(List<ItemDto> items, List<String> mesesEnOrden) {
        // GUÍA: teoría 4.7 (la serie "acumulado del año" superpuesta a las barras).
        // 1. Calcula ventasPorMes(items).
        // 2. Lleva un acumulador 'suma'; por cada mes de mesesEnOrden, súmale su venta
        //    (getOrDefault(mes, 0.0)) y añade 'suma' a la lista resultado.
        // PISTA: es el mismo "running sum" que el reto acumulado de b37, pero sobre el mapa de ventas.
        // OJO: el test con Enero=10, Febrero=20, Marzo=5 y orden [Enero,Febrero,Marzo] -> [10.0,30.0,35.0].
        // CULTURA: gráfico embebido en informe = el mismo dato agregado que pintaste en b37, ahora en PDF.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para acumuladoMensual");
    }

    /** Helper de demostración: arma una lista mutable de items. */
    static List<ItemDto> items(ItemDto... is) {
        return new ArrayList<>(List.of(is));
    }

    /** Helper de demostración: lista mutable de cadenas (orden de meses, productos...). */
    static List<String> orden(String... ss) {
        return new ArrayList<>(new LinkedHashSet<>(List.of(ss)));
    }

    /** Helper de demostración: arma un mapa ordenado clave->valor. */
    static Map<String, Double> mapa(String[] claves, double[] valores) {
        Map<String, Double> m = new LinkedHashMap<>();
        for (int i = 0; i < claves.length; i++) {
            m.put(claves[i], valores[i]);
        }
        return m;
    }
}
