package modulo5_patrones_estructurales;

/**
 * ============================================================================
 * EJERCICIO 66: Facade — Proceso de Pedido E-Commerce
 * ============================================================================
 * 📚 Teoría: teoria/05_Patrones_Estructurales.md - Sección 5.4
 *
 * CONTEXTO:
 * Un sistema e-commerce para procesar un pedido necesita coordinar:
 * Inventario, Pago, Envío, Notificación y Facturación. El Facade
 * expone un solo método: realizarPedido() que orquesta todo.
 *
 * Subsistemas:
 * - Inventario: verificarStock(String producto, int cantidad).
 * - ServicioPago: cobrar(double monto, String metodoPago).
 * - ServicioEnvio: crearEnvio(String destino, String producto).
 * - Notificador: enviarEmail(String destino, String asunto, String cuerpo).
 * - Facturacion: generarFactura(String cliente, String producto, double monto).
 *
 * RESTRICCIONES:
 * - El Facade valida el stock ANTES de cobrar.
 * - Si el pago falla, NO crear envío ni factura.
 * - Notificar al cliente en cada paso (confirmación, envío, factura).
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio66_FacadePedido {

    // TODO 1: Implementar los 5 subsistemas con 1-2 métodos cada uno.
    //         Cada método imprime un mensaje descriptivo y retorna boolean/String.

    // TODO 2: Implementar PedidoFacade con método:
    //         boolean realizarPedido(String cliente, String email, String producto,
    //                                int cantidad, double precioUnitario,
    //                                String metodoPago, String direccionEnvio)
    //         Orquestación:
    //         1. Verificar stock → si no hay, abortar con mensaje.
    //         2. Calcular total = cantidad × precioUnitario.
    //         3. Cobrar → si falla, abortar.
    //         4. Crear envío.
    //         5. Generar factura.
    //         6. Notificar al cliente por email.
    //         7. Retornar true si todo ok.

    // TODO 3: Implementar método cancelarPedido(String numeroPedido):
    //         1. Reembolsar pago.
    //         2. Cancelar envío.
    //         3. Anular factura.
    //         4. Notificar al cliente.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 66: Facade Pedido ===\n");

        // TODO 4: Crear los subsistemas y el facade.
        //         Realizar un pedido exitoso:
        //         facade.realizarPedido("Alice", "alice@mail.com",
        //             "Laptop Gaming", 1, 1299.99, "Visa ****4242", "C/ Gran Vía 1, Madrid");
        //
        //         Realizar un pedido sin stock:
        //         facade.realizarPedido("Bob", "bob@mail.com",
        //             "PS6 Pro", 1, 599.99, "PayPal", "Av. Diagonal 100, Barcelona");

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 66 ===");
    }
}
