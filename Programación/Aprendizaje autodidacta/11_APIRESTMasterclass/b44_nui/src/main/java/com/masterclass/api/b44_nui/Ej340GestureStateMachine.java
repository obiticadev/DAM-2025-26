package com.masterclass.api.b44_nui;

import java.util.List;

/**
 * Ejercicio 340 · Clasificar gestos por su trayectoria y la máquina de estados del toque.
 *
 * <p>Un gesto es una secuencia de puntos en el tiempo. Para reconocerlo se mira el desplazamiento
 * total (¿hacia dónde y cuánto?) y la duración (¿toque corto o mantenido?). Por debajo, el sistema
 * táctil es una <strong>máquina de estados</strong>: IDLE → PRESSED → DRAG → RELEASED.
 *
 * <p>Teoría: {@code teoria/44_Interfaces_Naturales.md} (sección 4).
 */
public final class Ej340GestureStateMachine {

    private Ej340GestureStateMachine() {
    }

    /** Gestos reconocibles a partir de una trayectoria. */
    public enum Gesto {
        TAP, SWIPE_IZQ, SWIPE_DER, SWIPE_ARRIBA, SWIPE_ABAJO, HOLD, DESCONOCIDO
    }

    /** Estados del ciclo de un toque. */
    public enum EstadoGesto {
        IDLE, PRESSED, DRAG, RELEASED
    }

    /** Eventos que llegan del sensor táctil. */
    public enum EventoGesto {
        DOWN, MOVE, UP
    }

    /** Umbral en píxeles por debajo del cual el movimiento se considera un TAP, no un SWIPE. */
    public static final double UMBRAL_TAP = 10.0;

    /**
     * Clasifica una trayectoria de puntos en un gesto, por su dirección dominante y magnitud.
     *
     * @param trayectoria puntos del gesto, del primero (down) al último (up); el eje Y crece hacia abajo
     * @return el {@link Gesto} reconocido; {@link Gesto#DESCONOCIDO} si la trayectoria es insuficiente
     */
    public static Gesto clasificarGesto(List<Punto> trayectoria) {
        // TODO 1: si la trayectoria es null o tiene menos de 2 puntos -> Gesto.DESCONOCIDO.
        // TODO 2: toma el primer y el último punto; calcula dx = ult.x-prim.x y dy = ult.y-prim.y.
        // TODO 3: magnitud = Math.hypot(dx, dy); si magnitud < UMBRAL_TAP -> Gesto.TAP.
        // TODO 4: si |dx| >= |dy| el gesto es horizontal: dx>0 -> SWIPE_DER, si no -> SWIPE_IZQ.
        // TODO 5: si no, es vertical: dy>0 -> SWIPE_ABAJO (Y crece hacia abajo), si no -> SWIPE_ARRIBA.
        return Gesto.DESCONOCIDO;
    }

    /**
     * Transición de la máquina de estados del toque ante un evento.
     *
     * @param actual estado actual
     * @param evento evento recibido del sensor
     * @return el nuevo estado; transición inválida vuelve a {@code IDLE}; {@code null} sin implementar
     */
    public static EstadoGesto estadoSiguiente(EstadoGesto actual, EventoGesto evento) {
        // TODO 6: si actual o evento son null -> devuelve IDLE (entrada inválida).
        // TODO 7: desde IDLE, un DOWN pasa a PRESSED.
        // TODO 8: desde PRESSED, MOVE pasa a DRAG y UP pasa a RELEASED.
        // TODO 9: desde DRAG, MOVE se queda en DRAG y UP pasa a RELEASED; desde RELEASED, todo vuelve a IDLE.
        // TODO 10: cualquier otra combinación (transición inválida) -> IDLE.
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Gesto de (0,0)->(100,2): " + clasificarGesto(List.of(new Punto(0, 0), new Punto(100, 2))));
        System.out.println("IDLE + DOWN: " + estadoSiguiente(EstadoGesto.IDLE, EventoGesto.DOWN));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: ¿Es un flick (deslizamiento rápido)?
     * Un flick es un swipe con velocidad alta: distancia/tiempo por encima de un mínimo.
     */
    public static boolean esFlick(Punto inicio, Punto fin, double duracionSeg, double velocidadMin) {
        // GUÍA: teoría 4 (un flick lanza el scroll por inercia; lo define la VELOCIDAD, no la distancia).
        // 1. Si duracionSeg <= 0 -> false (evita dividir por cero).
        // 2. velocidad = hypot(fin-inicio) / duracionSeg; devuelve velocidad >= velocidadMin.
        // OJO: el test: de (0,0) a (100,0) en 0.1 s -> 1000 >= 500 -> true; en 1.0 s -> 100 < 500 -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFlick");
    }

    /**
     * Reto Extra 2: Longitud recorrida por la trayectoria.
     * Suma la distancia de cada segmento consecutivo (no la línea recta).
     */
    public static double distanciaTrayectoria(List<Punto> trayectoria) {
        // GUÍA: teoría 4 (la longitud real del trazo: útil para distinguir una curva de una recta).
        // 1. Menos de 2 puntos -> 0.
        // 2. Acumula Math.hypot entre cada punto y el siguiente.
        // OJO: el test: (0,0),(3,0),(3,4) -> 3+4 = 7; un solo punto -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para distanciaTrayectoria");
    }

    /**
     * Reto Extra 3: ¿Es un HOLD (pulsación mantenida)?
     * El dedo se queda quieto el tiempo suficiente.
     */
    public static boolean esHold(double duracionSeg, double umbralSeg) {
        // GUÍA: teoría 4 (long-press: mantener abre el menú contextual).
        // 1. Devuelve duracionSeg >= umbralSeg.
        // OJO: el test: (1.0,0.5)->true; (0.2,0.5)->false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esHold");
    }

    /**
     * Reto Extra 4: ¿Es un doble tap?
     * Dos toques separados por menos del intervalo máximo.
     */
    public static boolean esDobleTap(double intervaloSeg, double maxIntervalo) {
        // GUÍA: teoría 4 (doble tap = dos taps muy seguidos; si tardan demasiado, son dos taps sueltos).
        // 1. Devuelve intervaloSeg <= maxIntervalo.
        // OJO: el test: (0.2,0.3)->true; (0.5,0.3)->false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esDobleTap");
    }

    /**
     * Reto Extra 5: Ángulo del movimiento en grados.
     * Dirección del vector inicio→fin medida con atan2 (0°=derecha, 90°=abajo).
     */
    public static double anguloMovimiento(Punto a, Punto b) {
        // GUÍA: teoría 4 (el ángulo afina el swipe: distinguir diagonal de horizontal).
        // 1. dx = b.x-a.x, dy = b.y-a.y; devuelve Math.toDegrees(Math.atan2(dy, dx)).
        // OJO: el test: (0,0)->(0,5) -> 90.0 (Y hacia abajo); (0,0)->(5,0) -> 0.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para anguloMovimiento");
    }

    /**
     * Reto Extra 6: ¿Dentro de la zona muerta?
     * Movimientos minúsculos se ignoran para no confundir un toque con un arrastre.
     */
    public static boolean dentroDeZonaMuerta(Punto a, Punto b, double radio) {
        // GUÍA: teoría 4 (dead zone: el dedo tiembla; por debajo del radio NO es arrastre).
        // 1. Devuelve Math.hypot(b.x-a.x, b.y-a.y) <= radio.
        // OJO: el test: (0,0)->(2,0) radio 3 -> true; radio 1 -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dentroDeZonaMuerta");
    }

    /**
     * Reto Extra 7: Cancelar el gesto.
     * Ante una cancelación (sale de la pantalla, llamada entrante), el estado vuelve a IDLE.
     */
    public static EstadoGesto cancelarGesto(EstadoGesto actual) {
        // GUÍA: teoría 4 (un gesto cancelado NO debe disparar acción; se resetea la máquina).
        // 1. Devuelve siempre EstadoGesto.IDLE.
        // OJO: el test: DRAG->IDLE.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cancelarGesto");
    }

    /**
     * Reto Extra 8: Clasificar el número de punteros.
     * 0 dedos, 1 dedo (simple) o 2+ (multitáctil: pinch/zoom).
     */
    public static String cuentaPunteros(int activos) {
        // GUÍA: teoría 4 (un puntero = arrastrar; dos = pellizcar para hacer zoom).
        // 1. 0 -> "ninguno", 1 -> "simple", 2 o más -> "multiple".
        // OJO: el test: 1->"simple"; 2->"multiple".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cuentaPunteros");
    }

    /**
     * Reto Extra 9: Suavizar la trayectoria (media móvil de puntos).
     * Cada punto se promedia con sus vecinos en una ventana para quitar el temblor.
     */
    public static List<Punto> suavizarTrayectoria(List<Punto> trayectoria, int ventana) {
        // GUÍA: teoría 4 (el trazo capturado es ruidoso; la media móvil lo alisa, como Ej342).
        // 1. Trayectoria null/vacía o ventana < 1 -> List.of().
        // 2. Para cada índice, promedia x e y de los puntos de la ventana (céntrala en el índice).
        // OJO: el test usa ventana 1: cada punto se promedia consigo mismo -> la lista no cambia.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para suavizarTrayectoria");
    }

    /**
     * Reto Extra 10: Velocidad media del gesto.
     * Distancia total entre tiempo total (enlaza con la entrada por estados del juego de b41).
     */
    public static double velocidadMedia(double distancia, double tiempoSeg) {
        // GUÍA: teoría 4 + b41 (la velocidad del input alimenta la física: lanzar con inercia).
        // 1. Si tiempoSeg <= 0 -> 0 (evita dividir por cero).
        // 2. Devuelve distancia / tiempoSeg.
        // OJO: el test: (100,2)->50; (100,0)->0.
        // CULTURA: en b41 esta misma velocidad de entrada se convertía en la velocidad del sprite.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para velocidadMedia");
    }
}
