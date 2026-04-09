package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 98: COMBINADO — Juego RPG (State + Strategy + Observer + Command)
 * ============================================================================
 * 📚 Combina: State + Strategy + Observer + Command
 *
 * CONTEXTO:
 * Un mini juego RPG por turnos donde:
 * - State: el personaje tiene estados (Normal, Envenenado, Fortalecido, Muerto).
 * - Strategy: diferentes estrategias de ataque (Agresivo, Defensivo, Mágico).
 * - Observer: el combate notifica a un log y a un display de stats.
 * - Command: cada acción del turno es un command (Atacar, Defender, Usar Poción).
 *
 * RESTRICCIONES:
 * - Al cambiar de estado, el daño recibido/infligido cambia.
 * - La estrategia de ataque se puede cambiar entre turnos.
 * - Cada acción se logea automáticamente.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio98_RPGCombate {

    // ====== STATE: Estados del personaje ======
    // TODO 1: Interfaz EstadoPersonaje: void aplicarEfecto(Personaje98 p), String getNombre()
    //         NormalState: sin efecto.
    //         EnvenenadoState: pierde 5 HP por turno.
    //         FortalecidoState: ataque x1.5.
    //         MuertoState: no puede actuar.

    // ====== STRATEGY: Estrategias de ataque ======
    // TODO 2: Interfaz EstrategiaAtaque: int calcularDano(int ataqueBase)
    //         Agresivo: ataque x2, pero defensa x0.5.
    //         Defensivo: ataque x0.5, defensa x2.
    //         Magico: ataque x1.5 (ignora parte de la defensa).

    // ====== OBSERVER: Log de combate ======
    // TODO 3: Interfaz ObservadorCombate: void evento(String descripcion)
    //         LogCombate: imprime eventos con timestamp.
    //         DisplayStats: muestra HP/estado tras cada evento.

    // ====== COMMAND: Acciones de turno ======
    // TODO 4: Interfaz AccionCombate: void ejecutar()
    //         AtacarCommand, DefenderCommand, UsarPocionCommand.

    // ====== PERSONAJE ======
    // TODO 5: Implementar Personaje98:
    //         nombre, hp, hpMax, ataqueBase, defensa.
    //         EstadoPersonaje estado, EstrategiaAtaque estrategia.
    //         ObservadorCombate[] observadores.
    //         atacar(Personaje98 objetivo), recibirDano(int dmg), usarPocion(int cura).

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 98: RPG Combate ===\n");

        // TODO 6: Simular un combate por turnos:
        //         Personaje98 guerrero = new Personaje98("Guerrero", 100, 20, 15);
        //         Personaje98 mago = new Personaje98("Mago Oscuro", 80, 25, 10);
        //
        //         guerrero.setEstrategia(new Agresivo());
        //         mago.setEstrategia(new Magico());
        //
        //         // Turno 1
        //         guerrero.atacar(mago);
        //         mago.atacar(guerrero);
        //
        //         // Turno 2: guerrero envenenado
        //         guerrero.setEstado(new EnvenenadoState());
        //         guerrero.atacar(mago);
        //         mago.atacar(guerrero);
        //
        //         // Turno 3: usar poción
        //         guerrero.usarPocion(30);
        //         mago.atacar(guerrero);

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 98 ===");
    }
}
