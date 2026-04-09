package modulo5_patrones_estructurales;

/**
 * ============================================================================
 * EJERCICIO 71: Bridge — Dispositivos y Controles Remotos
 * ============================================================================
 * 📚 Teoría: teoria/05_Patrones_Estructurales.md - Sección 5.7
 *
 * CONTEXTO:
 * Tienes dispositivos (TV, Radio, SmartSpeaker) y controles remotos
 * (Básico, Avanzado). Sin Bridge, necesitarías N×M clases:
 * ControlBasicoTV, ControlBasicoRadio, ControlAvanzadoTV, etc.
 *
 * Con Bridge, las abstracciones (controles) y las implementaciones
 * (dispositivos) varían independientemente.
 *
 * Implementa:
 * - Interfaz Dispositivo (Implementation): encender(), apagar(),
 *   setVolumen(int), getVolumen(), estaEncendido().
 * - Dispositivos: TV, Radio, SmartSpeaker.
 * - Clase ControlRemoto (Abstraction): referencia a Dispositivo.
 * - Subclases: ControlBasico (encender/apagar/volumen),
 *   ControlAvanzado (+ mute, + definir canal/frecuencia).
 *
 * RESTRICCIONES:
 * - El ControlRemoto NO sabe qué dispositivo concreto controla.
 * - Puede cambiar de dispositivo en runtime (setDispositivo()).
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio71_BridgeDispositivos {

    // TODO 1: Definir interfaz Dispositivo (Implementation):
    //         void encender(), void apagar(), boolean estaEncendido()
    //         int getVolumen(), void setVolumen(int vol)
    //         String getNombre()

    // TODO 2: Implementar TV: volumen 0-100, canal 1-999.
    //         Cada acción imprime: "[TV Samsung] Encendida"

    // TODO 3: Implementar Radio: volumen 0-50, frecuencia (double).
    //         "[Radio Sony] Frecuencia: 98.5 FM"

    // TODO 4: Implementar ControlBasico (Abstraction):
    //         - Campo Dispositivo dispositivo.
    //         - encender(), apagar(), subirVolumen(), bajarVolumen().
    //         - Delega al dispositivo.

    // TODO 5: Implementar ControlAvanzado (extends ControlBasico):
    //         - mute(): setVolumen(0).
    //         - setCanal(int): si el dispositivo es TV (instanceOf → cast).
    //         - info(): imprime estado completo del dispositivo.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 71: Bridge Dispositivos ===\n");

        // TODO 6: Crear dispositivos y controles:
        //         Dispositivo tv = new TV();
        //         Dispositivo radio = new Radio();
        //
        //         ControlAvanzado control = new ControlAvanzado(tv);
        //         control.encender();
        //         control.subirVolumen();
        //         control.subirVolumen();
        //         control.info();
        //
        //         // Cambiar dispositivo en runtime
        //         control.setDispositivo(radio);
        //         control.encender();
        //         control.subirVolumen();
        //         control.info();
        //         control.mute();
        //         control.info();

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 71 ===");
    }
}
