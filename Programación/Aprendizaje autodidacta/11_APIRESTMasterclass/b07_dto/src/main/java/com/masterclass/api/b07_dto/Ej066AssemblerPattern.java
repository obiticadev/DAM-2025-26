package com.masterclass.api.b07_dto;

import java.util.List;

/**
 * Ejercicio 066 · Patrón Assembler (ensambla un DTO desde varias fuentes).
 *
 * <p>Teoría: {@code teoria/07_DTOs_y_Mapeo.md} (sección 7.3).
 */
public final class Ej066AssemblerPattern {

    public record Pedido(Long id, String cliente) {
    }

    public record Linea(Long pedidoId, String producto, int cantidad) {
    }

    /** DTO compuesto que el Assembler ensambla. */
    public record PedidoDetalleDto(Long id, String cliente, int totalUnidades, List<String> productos) {
    }

    private Ej066AssemblerPattern() {
    }

    /**
     * Ensambla el DTO de detalle combinando el pedido y sus líneas.
     *
     * @param pedido cabecera
     * @param lineas líneas del pedido (de otra fuente)
     * @return DTO agregado
     * @throws IllegalArgumentException si pedido es null o las líneas no son de ese pedido
     */
    public static PedidoDetalleDto ensamblar(Pedido pedido, List<Linea> lineas) {
        // TODO 1: si pedido es null -> IllegalArgumentException.
        // TODO 2: si lineas es null, trátalo como lista vacía.
        // TODO 3: valida que TODA línea tenga pedidoId == pedido.id (coherencia).
        // TODO 4: si alguna no coincide -> IllegalArgumentException.
        // TODO 5: calcula totalUnidades sumando cantidad de todas las líneas (stream).
        // TODO 6: extrae la lista de nombres de producto (map + toList).
        // TODO 7: copia id y cliente del pedido.
        // TODO 8: combina datos de DOS fuentes (esa es la esencia del Assembler).
        // TODO 9: construye el PedidoDetalleDto.
        // TODO 10: devuélvelo.
        return null;
    }

    public static void main(String[] args) {
        var d = ensamblar(new Pedido(1L, "Ana"),
                List.of(new Linea(1L, "cafe", 2), new Linea(1L, "te", 1)));
        System.out.println(d);
    }

    /**
     * RETO EXTRA 1: Ensamblar una lista de pedidos a partir de sus líneas correspondientes.
     *
     * @param pedidos lista de pedidos
     * @param todasLasLineas todas las líneas disponibles
     * @return lista de DTOs ensamblados
     */
    public static java.util.List<PedidoDetalleDto> ensamblarLista(java.util.List<Pedido> pedidos, java.util.List<Linea> todasLasLineas) {
        // GUÍA: teoría 7.4 (aplica ensamblar() a cada pedido reutilizándolo).
        // 1. Si pedidos es null -> List.of(); si todasLasLineas es null, trátala vacía.
        // 2. Para cada pedido, FILTRA de todasLasLineas las que tengan su pedidoId y
        //    llama a ensamblar(pedido, lineasDeEsePedido).
        // PISTA: return pedidos.stream().map(p -> {
        //            var suyas = todasLasLineas.stream()
        //                .filter(l -> l.pedidoId().equals(p.id())).toList();
        //            return ensamblar(p, suyas);
        //        }).toList();
        // OJO: el test mete líneas de pedidos 1 y 2; cada DTO debe llevar SOLO sus
        //      líneas (por eso el filtro por pedidoId; si no, ensamblar lanzaría por
        //      incoherencia). Es PLACEHOLDER (assertNull); ajústalo al resolver.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ensamblarLista");
    }

    /**
     * RETO EXTRA 2: Filtrar líneas de pedido por cantidad mínima.
     *
     * @param lineas líneas de pedido
     * @param cantidadMinima cantidad mínima
     * @return líneas filtradas
     */
    public static java.util.List<Linea> filtrarPorCantidad(java.util.List<Linea> lineas, int cantidadMinima) {
        // GUÍA: filtro simple sobre las líneas (bloque 1.3).
        // 1. Si lineas es null -> List.of().
        // 2. Filtra las que tengan cantidad >= cantidadMinima.
        // PISTA: return lineas.stream().filter(l -> l.cantidad() >= cantidadMinima).toList();
        // OJO: el test mete cantidades [2,5] con mínimo 3 -> sobrevive solo la de 5
        //      (size 1). Es PLACEHOLDER (assertNull); ajústalo al resolver. El límite
        //      es >= (inclusivo): una línea con cantidad exactamente 3 pasaría.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para filtrarPorCantidad");
    }

    /**
     * RETO EXTRA 3: Obtener conjunto de nombres de productos únicos.
     *
     * @param lineas líneas de pedido
     * @return nombres únicos
     */
    public static java.util.Set<String> productosUnicos(java.util.List<Linea> lineas) {
        // GUÍA: proyección + deduplicación (collect a Set).
        // 1. Si lineas es null -> Set.of().
        // 2. Mapea cada línea a su producto y recoge a un Set (elimina duplicados).
        // PISTA: return lineas.stream().map(Linea::producto)
        //          .collect(java.util.stream.Collectors.toSet());
        // OJO: el test mete "cafe" y "pan" -> Set de 2 elementos. Es PLACEHOLDER
        //      (assertNull); ajústalo al resolver. Un Set no garantiza orden: no
        //      asumas posiciones, compara por contains/size.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para productosUnicos");
    }

    /**
     * RETO EXTRA 4: Calcular valor estimado del pedido en base a precio unitario genérico.
     *
     * @param lineas líneas de pedido
     * @param precioPorUnidad precio por unidad
     * @return valor total estimado
     */
    public static int calcularValorEstimado(java.util.List<Linea> lineas, int precioPorUnidad) {
        // GUÍA: agregación numérica (suma de unidades por precio).
        // 1. Si lineas es null -> devuelve 0.
        // 2. Suma todas las cantidades (mapToInt + sum) y multiplica por precioPorUnidad.
        // PISTA: int unidades = lineas.stream().mapToInt(Linea::cantidad).sum();
        //        return unidades * precioPorUnidad;
        // OJO: el test mete cantidades [2,5] (=7 unidades) con precio 10 -> esperado 70.
        //      Es PLACEHOLDER (assertEquals(0,...)); ajústalo al resolver. Multiplica
        //      DESPUÉS de sumar, no línea a línea (mismo resultado, más claro).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularValorEstimado");
    }

    /**
     * RETO EXTRA 5: Determinar si el pedido es de volumen grande.
     *
     * @param dto detalle del pedido
     * @return true si total de unidades es mayor a 15
     */
    public static boolean esPedidoGrande(PedidoDetalleDto dto) {
        // GUÍA: predicado sobre el DTO ya ensamblado.
        // 1. Si dto es null -> devuelve false.
        // 2. Devuelve dto.totalUnidades() > 15.
        // PISTA: return dto != null && dto.totalUnidades() > 15;
        // OJO: el test usa un DTO con totalUnidades 20 -> debería ser true (20 > 15).
        //      Es PLACEHOLDER (assertFalse); ajústalo al resolver. El umbral es
        //      estricto (> 15): exactamente 15 NO es grande.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPedidoGrande");
    }

    /**
     * RETO EXTRA 6: Agrupar unidades por producto.
     *
     * @param lineas líneas de pedido
     * @return mapa con clave producto y valor cantidad total
     */
    public static java.util.Map<String, Integer> agruparPorProducto(java.util.List<Linea> lineas) {
        // GUÍA: groupingBy CON downstream que suma (bloque 1.4).
        // 1. Si lineas es null -> Map.of().
        // 2. Agrupa por producto y, como downstream, suma las cantidades de cada grupo.
        // PISTA: return lineas.stream().collect(java.util.stream.Collectors.groupingBy(
        //            Linea::producto,
        //            java.util.stream.Collectors.summingInt(Linea::cantidad)));
        // OJO: el test mete dos líneas de "cafe" con cantidades 2 y 5 -> el mapa debe
        //      tener {"cafe": 7}, NO dos entradas ni una lista. Es PLACEHOLDER
        //      (assertNull); ajústalo al resolver. summingInt es la clave (no groupingBy
        //      a secas, que daría List<Linea>).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para agruparPorProducto");
    }

    /**
     * RETO EXTRA 7: Copiar PedidoDetalleDto modificando el nombre de cliente.
     *
     * @param dto DTO original
     * @param nuevoCliente nuevo nombre de cliente
     * @return copia modificada
     */
    public static PedidoDetalleDto copiarConClienteNuevo(PedidoDetalleDto dto, String nuevoCliente) {
        // GUÍA: "wither" inmutable (bloque 1.1) -> copia con un campo cambiado.
        // 1. Si dto es null -> devuelve null.
        // 2. Crea un PedidoDetalleDto NUEVO copiando todo menos cliente, que pasa a
        //    nuevoCliente. NO muta el original.
        // PISTA: return new PedidoDetalleDto(dto.id(), nuevoCliente,
        //            dto.totalUnidades(), dto.productos());
        // OJO: el test copia un DTO de "Ana" a "Pedro": el resto de campos (id,
        //      totalUnidades, productos) se conservan idénticos. Es PLACEHOLDER
        //      (assertNull); ajústalo al resolver.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para copiarConClienteNuevo");
    }

    /**
     * RETO EXTRA 8: Contar líneas inválidas (sin producto o cantidad <= 0).
     *
     * @param lineas líneas
     * @return cantidad de líneas inválidas
     */
    public static long contarLineasInvalidas(java.util.List<Linea> lineas) {
        // GUÍA: filtro + count (devuelve long).
        // 1. Si lineas es null -> devuelve 0.
        // 2. Cuenta las líneas inválidas: producto null/vacío (isBlank) O cantidad <= 0.
        // PISTA: return lineas.stream()
        //          .filter(l -> l.producto() == null || l.producto().isBlank() || l.cantidad() <= 0)
        //          .count();
        // OJO: el test mete una línea con producto "" (vacío) y otra con cantidad 0 ->
        //      ambas son inválidas -> esperado 2L. Es PLACEHOLDER (assertEquals(0,...));
        //      ajústalo al resolver. count() devuelve long: la aserción real usaría 2L.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarLineasInvalidas");
    }

    /**
     * RETO EXTRA 9: Formatear PedidoDetalleDto a un texto tipo ticket.
     *
     * @param dto detalle
     * @return representación en texto
     */
    public static String formatoTicket(PedidoDetalleDto dto) {
        // GUÍA: proyección a texto (como pasoExtra06/10 de Ej063).
        // 1. Si dto es null -> devuelve "" (cadena vacía).
        // 2. Construye un texto con cliente, total de unidades y la lista de productos.
        //    Diseña tú el formato (no hay aserción exacta), pero que sea legible.
        // PISTA: return "Pedido #" + dto.id() + " - " + dto.cliente() + "\n"
        //          + "Unidades: " + dto.totalUnidades() + "\n"
        //          + "Productos: " + String.join(", ", dto.productos());
        //        (String.join une la lista con el separador, sin streams).
        // OJO: el test es PLACEHOLDER (assertEquals("", ...)); solo el caso null debe
        //      dar "". Para un dto real devolverás el ticket; ajusta la aserción.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatoTicket");
    }

    /**
     * RETO EXTRA 10: Reconstruir PedidoDetalleDto añadiendo una línea de envío genérica (+1 unidad).
     *
     * @param dto DTO original
     * @return DTO modificado
     */
    public static PedidoDetalleDto reconstruirConEnvio(PedidoDetalleDto dto) {
        // GUÍA: copia inmutable que AÑADE un elemento a la lista (bloque 1.1).
        // 1. Si dto es null -> devuelve null.
        // 2. Crea una lista NUEVA = productos actuales + "envio", y suma +1 a unidades.
        //    Como dto.productos() puede ser inmutable, NO le hagas add: copia.
        // PISTA: var conEnvio = new java.util.ArrayList<>(dto.productos());
        //        conEnvio.add("envio");
        //        return new PedidoDetalleDto(dto.id(), dto.cliente(),
        //            dto.totalUnidades() + 1, java.util.List.copyOf(conEnvio));
        // OJO: el test parte de unidades 20 y productos ["cafe"] -> el resultado tendría
        //      21 unidades y ["cafe","envio"]. Es PLACEHOLDER (assertNull); ajústalo.
        // CUIDADO: List.of() y dto.productos() suelen ser INMUTABLES: si haces
        //      dto.productos().add(...) directamente saltará UnsupportedOperationException.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para reconstruirConEnvio");
    }

}
