package modulo5_patrones_estructurales;

/**
 * ============================================================================
 * EJERCICIO 61: Adapter — Adaptar API Legacy de Pagos
 * ============================================================================
 * 📚 Teoría: teoria/05_Patrones_Estructurales.md - Sección 5.2
 *
 * CONTEXTO:
 * Tu aplicación moderna usa una interfaz ProcesadorPago con el método
 * procesar(double monto, String moneda). Pero debes integrar un sistema
 * legacy (PagoLegacy) que tiene métodos completamente diferentes:
 * realizarPago(int centavos, int codigoMoneda).
 *
 * El Adapter traduce entre las dos interfaces sin modificar ninguna.
 *
 * Implementa:
 * - Interfaz ProcesadorPago: procesar(double monto, String moneda), getEstado().
 * - Clase PagoLegacy: realizarPago(int centavos, int codigoMoneda).
 * - Clase AdaptadorPagoLegacy: implementa ProcesadorPago, delega en PagoLegacy.
 * - Clase PagoModerno: implementación moderna directa.
 *
 * RESTRICCIONES:
 * - El Adapter convierte euros (double) a centavos (int): centavos = (int)(monto * 100).
 * - El Adapter convierte código moneda: "EUR"→1, "USD"→2, "GBP"→3.
 * - PagoLegacy NO se puede modificar (simula librería externa).
 * - El cliente SOLO conoce la interfaz ProcesadorPago.
 * ============================================================================
 */
public class Ejercicio61_AdapterPagos {

    // ==== INTERFAZ MODERNA (Target) ====
    interface ProcesadorPago {
        // TODO 1: Definir:
        //         boolean procesar(double monto, String moneda)
        //         String getEstado()
        //         String getNombre()
    }

    // ==== SISTEMA LEGACY (Adaptee) — NO MODIFICAR ====
    static class PagoLegacy {
        private int ultimoResultado = -1;

        public int realizarPago(int centavos, int codigoMoneda) {
            System.out.printf("  [LEGACY] Procesando %d centavos, moneda código %d...%n",
                    centavos, codigoMoneda);
            if (centavos <= 0) {
                ultimoResultado = 0;
                return 0; // error
            }
            ultimoResultado = 1;
            return 1; // éxito
        }

        public String obtenerDescripcionEstado() {
            return ultimoResultado == 1 ? "LEGACY_OK" : "LEGACY_ERROR";
        }
    }

    // ==== ADAPTER ====
    // TODO 2: Implementar AdaptadorPagoLegacy que:
    //         - Implementa ProcesadorPago.
    //         - Tiene un campo PagoLegacy (composición).
    //         - procesar(): convierte monto→centavos y moneda→código,
    //           luego delega en pagoLegacy.realizarPago().
    //         - getEstado(): delega en pagoLegacy.obtenerDescripcionEstado().
    //         - getNombre(): retorna "Adapter → PagoLegacy v1.0"

    // ==== PAGO MODERNO (para comparar) ====
    // TODO 3: Implementar PagoModerno que implementa ProcesadorPago directamente:
    //         - procesar(): imprime "[MODERNO] Procesando €XX.XX en EUR..."
    //         - Retorna true si monto > 0.

    // TODO 4: Implementar método auxiliar convertirMoneda(String moneda):
    //         "EUR"→1, "USD"→2, "GBP"→3, otro→-1.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 61: Adapter Pagos ===\n");

        // TODO 5: Crear ambos procesadores y usarlos polimórficamente:
        //         ProcesadorPago moderno = new PagoModerno();
        //         ProcesadorPago legacy = new AdaptadorPagoLegacy(new PagoLegacy());
        //
        //         ProcesadorPago[] procesadores = {moderno, legacy};
        //         for (ProcesadorPago p : procesadores) {
        //             System.out.println("--- " + p.getNombre() + " ---");
        //             boolean ok = p.procesar(49.99, "EUR");
        //             System.out.println("Resultado: " + ok + " | Estado: " + p.getEstado());
        //         }

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 61 ===");
    }
}
