package com.masterclass.api.b41_anim;

/**
 * Ejercicio 323 · Entrada de teclado y máquina de estados del juego.
 *
 * <p>Teoría: {@code teoria/41_Animacion_Juegos.md} (sección 5).
 *
 * <p>Un juego responde a dos cosas: la ENTRADA (qué teclas pulsa el jugador) y su propio ESTADO
 * (¿estamos en el menú, jugando, en pausa o en game over?). La entrada en un game loop no se lee con
 * eventos sueltos sino con un "mapa de teclas pulsadas" que consultas cada frame: así puedes mover
 * mientras mantienes pulsado. El estado del juego se modela como una MÁQUINA DE ESTADOS: un conjunto
 * finito de situaciones y las transiciones permitidas entre ellas. Aquí implementas ambas: tablas de
 * transición y combinación de teclas. Todo son cadenas y booleanos: sin ventana.
 */
public final class Ej323InputGameState {

    private Ej323InputGameState() {
    }

    // Estados del juego (constantes para no escribir cadenas sueltas y equivocarse).
    public static final String MENU = "MENU";
    public static final String JUGANDO = "JUGANDO";
    public static final String PAUSA = "PAUSA";
    public static final String FIN = "FIN";

    /**
     * Transición de la máquina de estados del juego: dado el estado actual y un evento, devuelve el
     * nuevo estado. Si la combinación no está permitida, devuelve el MISMO estado (no se rompe nada).
     *
     * @param estado estado actual (MENU/JUGANDO/PAUSA/FIN)
     * @param evento evento recibido ("empezar", "pausar", "reanudar", "morir", "reiniciar")
     * @return el nuevo estado; {@code null} sin implementar
     */
    public static String siguienteEstado(String estado, String evento) {
        // TODO 1: si estado==MENU y evento=="empezar" -> devuelve JUGANDO.
        // TODO 2: si estado==JUGANDO y evento=="pausar" -> devuelve PAUSA.
        // TODO 3: si estado==PAUSA y evento=="reanudar" -> devuelve JUGANDO.
        // TODO 4: si estado==JUGANDO y evento=="morir" -> devuelve FIN.
        // TODO 5: si estado==FIN y evento=="reiniciar" -> devuelve MENU.
        //   En CUALQUIER otro caso (evento no válido para ese estado) devuelve el mismo 'estado'.
        return null;
    }

    /**
     * Eje horizontal de entrada a partir de las teclas izquierda/derecha pulsadas.
     * Devuelve -1 (izquierda), +1 (derecha) o 0 (ninguna o ambas, que se cancelan).
     *
     * @param izquierda ¿está pulsada la tecla de ir a la izquierda?
     * @param derecha   ¿está pulsada la de ir a la derecha?
     * @return el eje en {-1, 0, 1}; {@code -99} sin implementar (centinela imposible)
     */
    public static int ejeHorizontal(boolean izquierda, boolean derecha) {
        // TODO 6: parte de eje = 0.
        // TODO 7: si 'izquierda' está pulsada, resta 1 al eje.
        // TODO 8: si 'derecha' está pulsada, suma 1 al eje.
        // TODO 9: (date cuenta) si ambas están pulsadas, -1 + 1 = 0: se cancelan solas.
        // TODO 10: devuelve eje (el test: solo izq -> -1; solo der -> +1; ambas -> 0; ninguna -> 0).
        return -99;
    }

    public static void main(String[] args) {
        String estado = MENU;
        estado = siguienteEstado(estado, "empezar");      // -> JUGANDO
        System.out.println("Tras empezar: " + estado);
        System.out.println("Eje con izquierda pulsada: " + ejeHorizontal(true, false));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Eje vertical (arriba=-1, abajo=+1, ambas o ninguna=0). Mismo patrón que el core.
     */
    public static int ejeVertical(boolean arriba, boolean abajo) {
        // GUÍA: teoría 5.4 (en pantalla, Y crece hacia ABAJO; por eso 'abajo' es +1).
        // 1. eje = 0; si arriba eje -= 1; si abajo eje += 1; devuelve eje.
        // OJO: el test: solo arriba -> -1; solo abajo -> +1; ambas -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ejeVertical");
    }

    /**
     * Reto Extra 2: ¿El juego está pausado?
     */
    public static boolean estaPausado(String estado) {
        // GUÍA: teoría 5.5 (en PAUSA el update se salta pero el render sigue mostrando la escena).
        // 1. devuelve PAUSA.equals(estado).
        // OJO: el test: "PAUSA" -> true; "JUGANDO" -> false. Usa PAUSA.equals(estado) para tolerar null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaPausado");
    }

    /**
     * Reto Extra 3: ¿El juego está activo (en marcha, no en menú/pausa/fin)?
     */
    public static boolean juegoActivo(String estado) {
        // GUÍA: teoría 5.5 (solo en JUGANDO se actualiza la física).
        // 1. devuelve JUGANDO.equals(estado).
        // OJO: el test: "JUGANDO" -> true; "MENU" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para juegoActivo");
    }

    /**
     * Reto Extra 4: ¿Se puede pausar ahora mismo? Solo si estamos jugando.
     */
    public static boolean puedePausar(String estado) {
        // GUÍA: teoría 5.6 (no tiene sentido pausar el menú o un game over).
        // 1. devuelve JUGANDO.equals(estado).
        // OJO: el test: "JUGANDO" -> true; "PAUSA" -> false (ya está en pausa).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para puedePausar");
    }

    /**
     * Reto Extra 5: Alternar pausa: JUGANDO -> PAUSA y PAUSA -> JUGANDO; cualquier otro estado, igual.
     */
    public static String togglePausa(String estado) {
        // GUÍA: teoría 5.6 (la tecla P o ESC suele alternar entre jugar y pausa).
        // 1. si estado==JUGANDO devuelve PAUSA; si estado==PAUSA devuelve JUGANDO; si no, el mismo.
        // OJO: el test: "JUGANDO" -> "PAUSA"; "PAUSA" -> "JUGANDO"; "MENU" -> "MENU".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para togglePausa");
    }

    /**
     * Reto Extra 6: ¿Es un estado válido del juego (uno de los cuatro)?
     */
    public static boolean esEstadoValido(String estado) {
        // GUÍA: teoría 5.3 (una máquina de estados tiene un conjunto FINITO y cerrado de estados).
        // 1. devuelve MENU.equals(estado) || JUGANDO.equals(estado) || PAUSA.equals(estado) || FIN.equals(estado).
        // OJO: el test: "MENU" -> true; "VOLANDO" -> false; null -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEstadoValido");
    }

    /**
     * Reto Extra 7: Traducir una tecla a dirección textual. "W"/"ARRIBA"->"arriba", "S"->"abajo",
     * "A"->"izquierda", "D"->"derecha"; cualquier otra -> "".
     */
    public static String teclaADireccion(String tecla) {
        // GUÍA: teoría 5.4 (el clásico mapeo WASD; permite teclas alternativas con un switch).
        // 1. usa un switch sobre tecla: "W" -> "arriba", "S" -> "abajo", "A" -> "izquierda", "D" -> "derecha".
        // 2. default -> "".
        // OJO: el test: "W" -> "arriba", "D" -> "derecha", "Z" -> "".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para teclaADireccion");
    }

    /**
     * Reto Extra 8: Aplicar el movimiento de un eje a una posición entera: pos + eje·paso.
     */
    public static int aplicarMovimiento(int pos, int eje, int paso) {
        // GUÍA: teoría 5.7 (juntar entrada (eje de -1..1) con la física: mover 'paso' celdas).
        // 1. devuelve pos + eje * paso.
        // OJO: el test: (10, -1, 5) -> 5 (eje izquierda mueve hacia atrás).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aplicarMovimiento");
    }

    /**
     * Reto Extra 9: Contar cuántas teclas hay pulsadas a la vez (cuántos {@code true} en el array).
     */
    public static int teclasPulsadas(boolean[] teclas) {
        // GUÍA: teoría 5.8 (los combos y los "inputs simultáneos" necesitan contar pulsaciones).
        // 1. si teclas es null devuelve 0.
        // 2. recorre el array y cuenta los true.
        // OJO: el test: {true,false,true,true} -> 3; array vacío -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para teclasPulsadas");
    }

    /**
     * Reto Extra 10: Vector de movimiento 2D a partir de las 4 teclas WASD/flechas.
     * Devuelve {@code int[]{ejeX, ejeY}} combinando los dos ejes del core y del reto 1.
     */
    public static int[] vectorMovimiento(boolean arriba, boolean abajo, boolean izquierda, boolean derecha) {
        // GUÍA: teoría 5.9 (la entrada de movimiento completa es un vector 2D; cada eje es independiente).
        // 1. ejeX = ejeHorizontal(izquierda, derecha) (reutiliza el core).
        // 2. ejeY = ejeVertical(arriba, abajo) (reutiliza el reto 1).
        // 3. devuelve new int[]{ejeX, ejeY}.
        // OJO: el test: (true,false,true,false) -> {-1,-1} (arriba-izquierda, diagonal) y todo false -> {0,0}.
        // CULTURA: este vector (ejeX, ejeY) es la entrada cruda; antes de moverte conviene normalizarlo
        //   (Ej321 velocidadDiagonal) para que la diagonal no sea más rápida. Entrada -> vector ->
        //   normalizar -> física: ese es el pipeline de control de cualquier juego.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para vectorMovimiento");
    }
}
