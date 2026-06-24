package com.masterclass.api.b43_erp;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Ejercicio 335 · Agregaciones para inteligencia de negocio (KPIs, ventas por periodo).
 *
 * <p>Teoría: {@code teoria/43_SGE_Integracion.md} (sección 5).
 *
 * <p>Una vez los datos están integrados, el negocio quiere <b>explotarlos</b>: ventas por mes,
 * clientes top, ticket medio, crecimiento. Eso es <em>Business Intelligence</em>. La operación
 * estrella es <b>agrupar y agregar</b> ({@code GROUP BY} + {@code SUM}/{@code AVG}/{@code COUNT}),
 * que ya viste en SQL/JPA (b15) — aquí lo haces en Java con Streams (b01) sobre {@link PedidoErp}.
 * Todo determinista y testeable: la base del cuadro de mando de un ERP.
 */
public final class Ej335BiAggregations {

    private Ej335BiAggregations() {
    }

    /**
     * Suma el importe de los pedidos agrupados por mes ({@code yyyy-MM}).
     *
     * @param pedidos lista de pedidos (no null; puede ir vacía)
     * @return mapa {@code yyyy-MM} → importe total de ese mes (vacío si no hay pedidos)
     */
    public static Map<String, Double> ventasPorMes(List<PedidoErp> pedidos) {
        // TODO 1: si pedidos es null -> IllegalArgumentException.
        // TODO 2: si está vacía -> Map.of().
        // TODO 3: para cada pedido, el mes es los 7 primeros caracteres de la fecha (yyyy-MM).
        // TODO 4: agrupa por ese mes (Collectors.groupingBy).
        // TODO 5: dentro de cada grupo, suma el importe (Collectors.summingDouble).
        // TODO 6: PISTA: groupingBy(p -> p.fecha().substring(0,7), summingDouble(PedidoErp::importe)).
        // TODO 7: devuelve el mapa resultante.
        return Map.of();
    }

    /**
     * Devuelve los ids de los N clientes con mayor importe total, de más a menos.
     *
     * @param pedidos lista de pedidos
     * @param n       número máximo de clientes a devolver
     * @return lista de idCliente ordenada por total descendente (tamaño ≤ n)
     */
    public static List<String> topNClientes(List<PedidoErp> pedidos, int n) {
        // TODO 1: si pedidos es null -> IllegalArgumentException.
        // TODO 2: si n <= 0 o lista vacía -> List.of().
        // TODO 3: agrupa por idCliente sumando importe (groupingBy + summingDouble).
        // TODO 4: vuelca las entradas del mapa a un stream.
        // TODO 5: ordena por valor (total) DESCENDENTE (Map.Entry.comparingByValue + reversed).
        // TODO 6: limita a n (limit(n)).
        // TODO 7: queda solo la clave idCliente (map Map.Entry::getKey).
        // TODO 8: recógelo en una lista y devuélvela.
        return List.of();
    }

    public static void main(String[] args) {
        List<PedidoErp> ped = List.of(
                new PedidoErp("SO-1", "CLI-1", "2026-01-10", "Software", 100),
                new PedidoErp("SO-2", "CLI-1", "2026-02-05", "Software", 200),
                new PedidoErp("SO-3", "CLI-2", "2026-02-20", "Hardware", 50));
        System.out.println(ventasPorMes(ped));
        System.out.println(topNClientes(ped, 1));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Suma el importe total de todos los pedidos.
     */
    public static double totalVentas(List<PedidoErp> pedidos) {
        // GUÍA: teoría 5 (el KPI más básico: facturación total).
        // 1. null/vacía -> 0.0.
        // 2. suma todos los importes.
        // PISTA: return pedidos.stream().mapToDouble(PedidoErp::importe).sum();
        // OJO: el test suma 100+200+50 -> 350.0. mapToDouble + sum evita autoboxing.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para totalVentas");
    }

    /**
     * Reto Extra 2: Calcula el ticket medio (importe medio por pedido).
     */
    public static double ticketMedio(List<PedidoErp> pedidos) {
        // GUÍA: teoría 5 (ticket medio = total / nº pedidos; KPI comercial clásico).
        // 1. null/vacía -> 0.0 (¡evita dividir por cero!).
        // 2. media de los importes.
        // PISTA: return pedidos.stream().mapToDouble(PedidoErp::importe).average().orElse(0.0);
        // OJO: el test pasa lista vacía -> 0.0. average() devuelve OptionalDouble: usa orElse(0).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ticketMedio");
    }

    /**
     * Reto Extra 3: Suma el importe agrupado por categoría de producto.
     */
    public static Map<String, Double> totalPorCategoria(List<PedidoErp> pedidos) {
        // GUÍA: teoría 5 (otro GROUP BY: ventas por familia de producto).
        // 1. null/vacía -> Map.of().
        // 2. agrupa por categoria sumando importe.
        // PISTA: groupingBy(PedidoErp::categoria, summingDouble(PedidoErp::importe)).
        // OJO: el test comprueba que "Software" -> 300.0. Misma técnica que ventasPorMes
        //   pero con otra clave de agrupación.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para totalPorCategoria");
    }

    /**
     * Reto Extra 4: Devuelve el pedido de mayor importe (Optional vacío si no hay).
     */
    public static Optional<PedidoErp> pedidoMayor(List<PedidoErp> pedidos) {
        // GUÍA: teoría 5 (el pedido estrella). Devuelve Optional (idea de b01).
        // 1. null/vacía -> Optional.empty().
        // 2. el máximo por importe.
        // PISTA: return pedidos.stream().max(Comparator.comparingDouble(PedidoErp::importe));
        // OJO: el test espera "SO-2" (importe 200). max sobre stream vacío ya da Optional.empty(),
        //   pero protégete del null de la lista aparte.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pedidoMayor");
    }

    /**
     * Reto Extra 5: Cuenta cuántos pedidos hizo cada cliente.
     */
    public static Map<String, Long> contarPorCliente(List<PedidoErp> pedidos) {
        // GUÍA: teoría 5 (frecuencia de compra por cliente; COUNT ... GROUP BY).
        // 1. null/vacía -> Map.of().
        // 2. agrupa por idCliente contando.
        // PISTA: groupingBy(PedidoErp::idCliente, Collectors.counting()).
        // OJO: el test comprueba que CLI-1 -> 2L. counting() devuelve Long, no int.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarPorCliente");
    }

    /**
     * Reto Extra 6: Devuelve el mes ({@code yyyy-MM}) con mayor facturación.
     */
    public static String mesPico(List<PedidoErp> pedidos) {
        // GUÍA: teoría 5 (¿cuál fue el mejor mes? reutiliza ventasPorMes).
        // 1. null/vacía -> "".
        // 2. calcula ventasPorMes y quédate con la clave del valor máximo.
        // PISTA: ventasPorMes(pedidos).entrySet().stream()
        //          .max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("");
        // OJO: el test espera "2026-02" (200+50=250 > 100). Apóyate en tu ventasPorMes.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mesPico");
    }

    /**
     * Reto Extra 7: Calcula el crecimiento porcentual entre dos periodos.
     */
    public static double crecimientoPct(double anterior, double actual) {
        // GUÍA: teoría 5 (variación % = (actual - anterior) / anterior * 100).
        // 1. si anterior == 0 -> devuelve 0.0 (no se puede dividir; evita Infinity).
        // 2. aplica la fórmula.
        // PISTA: return anterior == 0 ? 0.0 : (actual - anterior) / anterior * 100.0;
        // OJO: el test pasa (100, 150) -> 50.0. Cuida el caso anterior=0 (división por cero
        //   daría Infinity/NaN y reventaría el cuadro de mando).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crecimientoPct");
    }

    /**
     * Reto Extra 8: Devuelve la serie acumulada (suma corrida) de una lista de importes.
     */
    public static List<Double> acumulado(List<Double> importes) {
        // GUÍA: teoría 5 (la curva acumulada de ventas de un dashboard).
        // 1. null/vacía -> List.of().
        // 2. recorre sumando: cada elemento de salida es la suma de todos los anteriores + el actual.
        // PISTA: double suma = 0; for (Double v : importes) { suma += v; res.add(suma); }
        // OJO: el test pasa [10, 20, 5] -> [10.0, 30.0, 35.0]. El primero es el propio valor.
        // CULTURA: es el "running total" de SQL (window function) y el acumulado de b37/b38.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para acumulado");
    }

    /**
     * Reto Extra 9: Filtra los pedidos cuya fecha cae en el rango [desde, hasta] (inclusive).
     */
    public static List<PedidoErp> ventasEntreFechas(List<PedidoErp> pedidos, String desde, String hasta) {
        // GUÍA: teoría 5 (acotar el periodo de un informe). Las fechas ISO yyyy-MM-dd se
        //   comparan como CADENAS (el orden lexicográfico coincide con el cronológico).
        // 1. null -> List.of().
        // 2. filtra los pedidos con fecha >= desde y <= hasta (compareTo).
        // PISTA: p.fecha().compareTo(desde) >= 0 && p.fecha().compareTo(hasta) <= 0.
        // OJO: el test usa desde="2026-02-01", hasta="2026-02-28" -> solo los de febrero.
        //   Funciona SOLO porque el formato es ISO (yyyy-MM-dd); con dd/MM/yyyy fallaría.
        // CULTURA: esto es un WHERE fecha BETWEEN ... de SQL (b11/b15) hecho en memoria.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ventasEntreFechas");
    }

    /**
     * Reto Extra 10: Calcula la tasa de conversión (cerrados / oportunidades) como porcentaje.
     */
    public static double tasaConversion(int oportunidades, int cerrados) {
        // GUÍA: teoría 5 (KPI de CRM: % de oportunidades que acaban en venta).
        // 1. si oportunidades <= 0 -> 0.0.
        // 2. devuelve cerrados / oportunidades * 100 (cuidado con la división entera).
        // PISTA: return oportunidades <= 0 ? 0.0 : (cerrados * 100.0) / oportunidades;
        // OJO: el test pasa (4, 1) -> 25.0. Multiplica por 100.0 (double) ANTES de dividir,
        //   o la división entera te daría 0.
        // CULTURA: el embudo de CRM (leads -> oportunidades -> ventas) es el corazón del
        //   módulo de ventas del ERP; este KPI mide su salud.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tasaConversion");
    }
}
