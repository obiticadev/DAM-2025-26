package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 84: State — Máquina Expendedora
 * ============================================================================
 * 📚 Teoría: teoria/06_Patrones_Comportamiento.md - Sección 6.6
 *
 * CONTEXTO:
 * Una máquina expendedora tiene 4 estados: SinMoneda, ConMoneda,
 * Vendiendo, SinStock. Cada estado reacciona diferente a las mismas
 * acciones: insertarMoneda(), expulsarMoneda(), girarManivela(), dispensar().
 *
 * Sin State, tendrías if/else gigantescos. Con State, cada estado
 * es una clase con su propio comportamiento.
 *
 * Implementa:
 * - Interfaz Estado: insertarMoneda(), expulsarMoneda(), girarManivela(), dispensar().
 * - Estados: SinMonedaState, ConMonedaState, VendiendoState, SinStockState.
 * - MaquinaExpendedora (Context): mantiene el estado actual y el stock.
 *
 * RESTRICCIONES:
 * - Cada estado decide cómo reaccionar y a qué estado transicionar.
 * - La máquina NUNCA hace if sobre su estado, delega al objeto State.
 * - Imprimir mensajes en cada transición y acción.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio84_StateMaquinaExpendedora {

    interface Estado {
        // TODO 1: void insertarMoneda(), void expulsarMoneda(),
        //         void girarManivela(), void dispensar()
    }

    // TODO 2: Implementar MaquinaExpendedora (Context):
    //         - Estado actual.
    //         - int stock.
    //         - Instancias de los 4 estados (para reutilizar).
    //         - setEstado(Estado e).
    //         - Los 4 métodos de acción delegan al estado actual.

    // TODO 3: Implementar SinMonedaState:
    //         - insertarMoneda() → "Moneda insertada." → cambiar a ConMonedaState.
    //         - expulsarMoneda() → "No hay moneda insertada."
    //         - girarManivela() → "Necesitas insertar una moneda."
    //         - dispensar() → "Necesitas pagar primero."

    // TODO 4: Implementar ConMonedaState:
    //         - insertarMoneda() → "Ya hay una moneda."
    //         - expulsarMoneda() → "Moneda devuelta." → cambiar a SinMonedaState.
    //         - girarManivela() → "Girando..." → cambiar a VendiendoState → dispensar().
    //         - dispensar() → "No se puede dispensar sin girar."

    // TODO 5: Implementar VendiendoState:
    //         - dispensar() → "¡Producto dispensado!" → decrementar stock.
    //           Si stock > 0 → SinMonedaState.
    //           Si stock == 0 → SinStockState.
    //         - Otros → "Espera, dispensando..."

    // TODO 6: Implementar SinStockState:
    //         - Todas las acciones → "Máquina sin stock. Lo sentimos."

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 84: State Máquina Expendedora ===\n");

        // TODO 7:
        //         MaquinaExpendedora maquina = new MaquinaExpendedora(3);
        //
        //         maquina.insertarMoneda();
        //         maquina.girarManivela();  // Dispensa
        //
        //         maquina.girarManivela();  // Error: sin moneda
        //
        //         maquina.insertarMoneda();
        //         maquina.expulsarMoneda(); // Devuelve moneda
        //
        //         // Vaciar stock
        //         maquina.insertarMoneda(); maquina.girarManivela();
        //         maquina.insertarMoneda(); maquina.girarManivela();
        //
        //         // Sin stock
        //         maquina.insertarMoneda(); // Error: sin stock

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 84 ===");
    }
}
