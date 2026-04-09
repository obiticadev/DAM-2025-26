package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 79: Strategy — Sistema de Descuentos
 * ============================================================================
 * 📚 Teoría: teoria/06_Patrones_Comportamiento.md - Sección 6.3
 *
 * CONTEXTO:
 * Una tienda online aplica diferentes estrategias de descuento según
 * el tipo de cliente, la temporada o promociones activas.
 *
 * Estrategias:
 * - SinDescuento: precio completo.
 * - DescuentoPorcentaje: reduce un % (ej: 20% off).
 * - DescuentoFijo: reduce una cantidad fija (ej: -10€).
 * - DescuentoBlackFriday: 50% en todo + envío gratis.
 * - DescuentoMiembro: escalonado según nivel (Bronce 5%, Plata 10%, Oro 20%).
 *
 * RESTRICCIONES:
 * - Interfaz EstrategiaDescuento: double calcular(double precioOriginal).
 * - CarritoCompra (Context): aplica la estrategia al total.
 * - Demostrar cambio de estrategia en runtime.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio79_StrategyDescuentos {

    interface EstrategiaDescuento {
        // TODO 1: double calcular(double precioOriginal)
        //         String getDescripcion()
    }

    // TODO 2: Implementar las 5 estrategias.

    // TODO 3: Implementar CarritoCompra:
    //         - String[] productos (max 20), double[] precios, int numItems.
    //         - EstrategiaDescuento estrategia.
    //         - agregar(String producto, double precio).
    //         - setEstrategia(EstrategiaDescuento e).
    //         - double subtotal(): suma de precios.
    //         - double total(): subtotal con estrategia aplicada.
    //         - void mostrarResumen(): imprime ticket con detalle.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 79: Strategy Descuentos ===\n");

        // TODO 4:
        //         CarritoCompra carrito = new CarritoCompra();
        //         carrito.agregar("Laptop", 999.99);
        //         carrito.agregar("Mouse", 29.99);
        //         carrito.agregar("Teclado", 79.99);
        //
        //         carrito.setEstrategia(new SinDescuento());
        //         carrito.mostrarResumen();
        //
        //         carrito.setEstrategia(new DescuentoPorcentaje(20));
        //         carrito.mostrarResumen();
        //
        //         carrito.setEstrategia(new DescuentoBlackFriday());
        //         carrito.mostrarResumen();
        //
        //         carrito.setEstrategia(new DescuentoMiembro("Oro"));
        //         carrito.mostrarResumen();

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 79 ===");
    }
}
