package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 91: Mediator — Torre de Control de Aeropuerto
 * ============================================================================
 * 📚 Teoría: teoria/06_Patrones_Comportamiento.md - Sección 6.9
 *
 * CONTEXTO:
 * Los aviones no se comunican entre sí directamente. La torre de control
 * (mediador) coordina aterrizajes, despegues y la pista disponible.
 *
 * Implementa:
 * - Interfaz TorreControl: registrar(Avion), solicitarAterrizaje(Avion),
 *   solicitarDespegue(Avion), notificarTodos(String msg, Avion emisor).
 * - Clase Aeropuerto (Mediator): pista disponible (boolean), lista de aviones.
 * - Clase Avion: id, torre, aterrizaje(), despegue().
 *
 * RESTRICCIONES:
 * - Solo un avión puede usar la pista a la vez.
 * - Si la pista está ocupada → en espera.
 * - Al desocupar la pista, notificar a todos.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio91_MediatorTorreControl {

    // TODO 1: Definir interfaz TorreControl y clase Aeropuerto (Mediator).

    // TODO 2: Implementar Avion con estados (en vuelo, en tierra, aterrizando).

    // TODO 3: Implementar lógica de pista en Aeropuerto:
    //         solicitarAterrizaje(): si pista libre → "✈️ IB-340 aterrizando...", ocupar pista.
    //                                si ocupada → "⏳ IB-340 en espera de pista."
    //         solicitarDespegue(): liberar pista, notificar.

    // TODO 4: notificarTodos(String msg, Avion emisor):
    //         enviar mensaje a todos los aviones EXCEPTO al emisor.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 91: Mediator Torre Control ===\n");

        // TODO 5:
        //         Aeropuerto torre = new Aeropuerto("Madrid-Barajas");
        //         Avion a1 = new Avion("IB-340", torre);
        //         Avion a2 = new Avion("VY-102", torre);
        //         Avion a3 = new Avion("FR-789", torre);
        //         torre.registrar(a1); torre.registrar(a2); torre.registrar(a3);
        //
        //         a1.solicitarAterrizaje();  // OK: pista libre
        //         a2.solicitarAterrizaje();  // Espera: pista ocupada
        //         a1.solicitarDespegue();    // Libera pista, notifica
        //         a2.solicitarAterrizaje();  // Ahora sí

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 91 ===");
    }
}
