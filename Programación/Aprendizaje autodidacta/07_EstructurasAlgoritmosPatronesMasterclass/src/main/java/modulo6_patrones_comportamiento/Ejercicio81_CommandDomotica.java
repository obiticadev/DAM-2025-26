package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 81: Command — Control Domótico con Macros
 * ============================================================================
 * 📚 Teoría: teoria/06_Patrones_Comportamiento.md - Sección 6.4
 *
 * CONTEXTO:
 * Un sistema domótico controla luces, termostato y persianas. Cada acción
 * es un Command. Un MacroCommand agrupa varios commands y los ejecuta
 * como uno solo (ej: "Modo Noche" = apagar luces + bajar persianas + bajar temp).
 *
 * Implementa:
 * - Dispositivos: Luz, Termostato, Persiana (Receivers).
 * - Comandos: EncenderLuz, ApagarLuz, SubirTemp, BajarTemp, AbrirPersiana, etc.
 * - MacroCommand: agrupa N commands y los ejecuta/deshace juntos.
 * - ControlRemoto: 7 slots, cada uno con un command ON y OFF.
 *
 * RESTRICCIONES:
 * - Cada dispositivo imprime su acción.
 * - MacroCommand ejecuta en orden y deshace en orden inverso.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio81_CommandDomotica {

    interface Command {
        void ejecutar();
        void deshacer();
    }

    // TODO 1: Implementar Luz, Termostato, Persiana (Receivers)
    //         con acciones: encender/apagar, subirTemp/bajarTemp, abrir/cerrar.

    // TODO 2: Implementar comandos concretos para cada acción del dispositivo.
    //         Cada uno guarda su receiver y el estado previo para undo.

    // TODO 3: Implementar MacroCommand:
    //         - Command[] comandos (max 10), int num.
    //         - ejecutar(): ejecutar todos en orden.
    //         - deshacer(): deshacer todos en orden INVERSO.

    // TODO 4: Implementar ControlRemoto:
    //         - 7 slots de pares ON/OFF.
    //         - Command ultimoComando (para undo genérico).
    //         - presionarON(int slot), presionarOFF(int slot), presionarUndo().

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 81: Command Domótica ===\n");

        // TODO 5:
        //         Luz salon = new Luz("Salón");
        //         Termostato termo = new Termostato();
        //         Persiana persiana = new Persiana("Dormitorio");
        //
        //         // Macro: "Modo Noche"
        //         MacroCommand modoNoche = new MacroCommand();
        //         modoNoche.agregar(new ApagarLuzCommand(salon));
        //         modoNoche.agregar(new CerrarPersianaCommand(persiana));
        //         modoNoche.agregar(new BajarTempCommand(termo, 19));
        //
        //         modoNoche.ejecutar();
        //         System.out.println("\nDeshaciendo Modo Noche:");
        //         modoNoche.deshacer();

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 81 ===");
    }
}
