package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 93: Memento — Sistema de Checkpoints en Juego
 * ============================================================================
 * 📚 Teoría: teoria/06_Patrones_Comportamiento.md - Sección 6.10
 *
 * CONTEXTO:
 * Un juego RPG permite guardar progreso en "checkpoints". El jugador
 * puede cargar cualquier checkpoint guardado, no solo el último.
 *
 * Implementa:
 * - Clase Jugador (Originator): nombre, nivel, vida, posX, posY, inventario[].
 * - Clase Checkpoint (Memento): snapshot inmutable del estado.
 * - Clase GestorPartidas (Caretaker): múltiples slots nombrados.
 *
 * RESTRICCIONES:
 * - GestorPartidas almacena checkpoints con nombres ("autosave", "antes_boss").
 * - Límite de 10 slots. Al guardar en uno existente, se sobreescribe.
 * - El inventario se copia profundamente (deep copy).
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio93_MementoJuego {

    // TODO 1: Implementar Checkpoint (Memento):
    //         Campos inmutables: nivel, vida, posX, posY, String[] inventario (copia).

    // TODO 2: Implementar Jugador (Originator):
    //         guardarCheckpoint(): crea Checkpoint con estado actual.
    //         cargarCheckpoint(Checkpoint cp): restaura estado.
    //         recibirDano(int dmg), subirNivel(), mover(int x, int y).
    //         agregarItem(String item), mostrarEstado().

    // TODO 3: Implementar GestorPartidas (Caretaker):
    //         String[] nombresSlot, Checkpoint[] slots. Max 10.
    //         guardar(String nombre, Checkpoint cp).
    //         Checkpoint cargar(String nombre).
    //         listarSlots().

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 93: Memento Juego ===\n");

        // TODO 4:
        //         Jugador hero = new Jugador("Arthas", 1, 100);
        //         GestorPartidas gestor = new GestorPartidas();
        //
        //         hero.agregarItem("Espada de madera");
        //         hero.mover(10, 20);
        //         gestor.guardar("inicio", hero.guardarCheckpoint());
        //         hero.mostrarEstado();
        //
        //         hero.subirNivel(); hero.subirNivel();
        //         hero.agregarItem("Armadura de hierro");
        //         hero.mover(50, 80);
        //         gestor.guardar("antes_boss", hero.guardarCheckpoint());
        //
        //         hero.recibirDano(95); // Casi muerto
        //         hero.mostrarEstado();
        //
        //         // Cargar checkpoint
        //         hero.cargarCheckpoint(gestor.cargar("antes_boss"));
        //         hero.mostrarEstado(); // Restaurado antes del boss

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 93 ===");
    }
}
