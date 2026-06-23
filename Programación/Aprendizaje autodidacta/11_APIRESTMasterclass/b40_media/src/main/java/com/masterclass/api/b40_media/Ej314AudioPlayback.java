package com.masterclass.api.b40_media;

/**
 * Ejercicio 314 · Reproducción de audio: la máquina de estados del {@code MediaPlayer}.
 *
 * <p>Teoría: {@code teoria/40_Multimedia.md} (sección 4).
 *
 * <p>Reproducir un sonido con {@code javafx.scene.media.MediaPlayer} necesita códecs nativos y una
 * salida de audio: imposible de testear de forma determinista. Pero lo importante para programar el
 * reproductor NO es la salida de sonido, sino su LÓGICA: un {@code MediaPlayer} es una máquina de
 * estados (READY → PLAYING → PAUSED → STOPPED) y un control de volumen acotado a 0..1. Eso es puro
 * y se prueba sin altavoces. El Playground real reproduce con {@code MediaPlayer}.
 *
 * <p>Estados: {@code READY}, {@code PLAYING}, {@code PAUSED}, {@code STOPPED}.
 * Acciones: {@code play}, {@code pause}, {@code stop}.
 */
public final class Ej314AudioPlayback {

    private Ej314AudioPlayback() {
    }

    /**
     * Calcula el siguiente estado del reproductor dada una acción. Si la acción no es válida desde
     * el estado actual (p. ej. pausar algo ya parado), el estado NO cambia.
     *
     * <pre>
     *   READY   + play  -> PLAYING
     *   PLAYING + pause -> PAUSED      PLAYING + stop -> STOPPED
     *   PAUSED  + play  -> PLAYING     PAUSED  + stop -> STOPPED
     *   STOPPED + play  -> PLAYING
     * </pre>
     *
     * @param actual estado actual
     * @param accion "play" / "pause" / "stop"
     * @return el nuevo estado (o el mismo si la transición no es válida); "" sin implementar
     */
    public static String siguienteEstado(String actual, String accion) {
        // TODO 1: si actual o accion son null, devuelve actual (sin cambio).
        // TODO 2: con "play": desde READY, PAUSED o STOPPED -> "PLAYING".
        // TODO 3: con "pause": solo desde PLAYING -> "PAUSED".
        // TODO 4: con "stop": desde PLAYING o PAUSED -> "STOPPED".
        // TODO 5: en cualquier otro caso (acción no aplicable) devuelve 'actual' sin cambiarlo.
        //         (el test comprueba READY+play=PLAYING, PLAYING+stop=STOPPED, STOPPED+pause=STOPPED).
        return "";
    }

    /**
     * Recorta el volumen al rango válido del {@code MediaPlayer}: 0.0 (silencio) a 1.0 (máximo).
     *
     * @param volumen valor solicitado (puede venir fuera de rango)
     * @return el volumen recortado a [0.0, 1.0]; -1 sin implementar
     */
    public static double clampVolumen(double volumen) {
        // TODO 6: si volumen < 0.0 devuelve 0.0.
        // TODO 7: si volumen > 1.0 devuelve 1.0.
        // TODO 8: en otro caso devuelve volumen tal cual.
        // TODO 9: (equivale a Math.max(0.0, Math.min(1.0, volumen)) — puedes usar esa forma).
        // TODO 10: el test prueba 1.5 -> 1.0, -0.2 -> 0.0 y 0.5 -> 0.5.
        return -1;
    }

    public static void main(String[] args) {
        String s = siguienteEstado("READY", "play");
        System.out.println("READY + play -> " + s);
        System.out.println("Volumen 1.5 recortado -> " + clampVolumen(1.5));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Formatear un tiempo en segundos como "m:ss".
     */
    public static String formatearTiempo(double segundos) {
        // GUÍA: teoría 4.3 (el típico contador de reproducción 1:05).
        // 1. total = (int) segundos; min = total/60; seg = total%60.
        // 2. return min + ":" + (seg<10 ? "0"+seg : ""+seg);  // o String.format("%d:%02d", min, seg).
        // OJO: el test usa 65 -> "1:05" y 5 -> "0:05" (segundos siempre con DOS dígitos).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearTiempo");
    }

    /**
     * Reto Extra 2: Porcentaje de progreso (0..100, entero).
     */
    public static int porcentajeProgreso(double actual, double total) {
        // GUÍA: teoría 4.3 (la posición de la barra de progreso).
        // 1. si total <= 0 devuelve 0 (evita dividir por cero).
        // 2. return (int) Math.round(actual / total * 100);
        // OJO: el test usa 30 de 120 -> 25. Caso límite: total 0 -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para porcentajeProgreso");
    }

    /**
     * Reto Extra 3: ¿Está sonando? (estado == PLAYING).
     */
    public static boolean estaReproduciendo(String estado) {
        // GUÍA: teoría 4.2 (consultas sobre el estado para habilitar/deshabilitar botones).
        // 1. return "PLAYING".equals(estado);
        // OJO: el test comprueba PLAYING -> true y PAUSED -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaReproduciendo");
    }

    /**
     * Reto Extra 4: ¿Se puede pausar ahora? (solo si está sonando).
     */
    public static boolean puedePausar(String estado) {
        // GUÍA: teoría 4.2 (el botón "pausa" solo tiene sentido en PLAYING).
        // 1. return "PLAYING".equals(estado);
        // OJO: el test comprueba que en READY/STOPPED devuelve false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para puedePausar");
    }

    /**
     * Reto Extra 5: Subir/bajar el volumen un paso, sin salirse de [0,1].
     */
    public static double cambiarVolumen(double actual, double paso) {
        // GUÍA: teoría 4.4 (los botones +/- de volumen reutilizan el clamp del core).
        // 1. return clampVolumen(actual + paso);  // reutiliza el core.
        // OJO: el test usa (0.9, 0.2) -> 1.0 (saturado) y (0.5, -0.3) -> 0.2 aprox (usa delta).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cambiarVolumen");
    }

    /**
     * Reto Extra 6: Volumen como porcentaje 0..100 (para mostrarlo al usuario).
     */
    public static int volumenAPorcentaje(double volumen) {
        // GUÍA: teoría 4.4 (el MediaPlayer trabaja en 0..1; el usuario piensa en 0..100%).
        // 1. return (int) Math.round(clampVolumen(volumen) * 100);
        // OJO: el test usa 0.5 -> 50. Un valor fuera de rango como 1.3 -> 100 (gracias al clamp).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para volumenAPorcentaje");
    }

    /**
     * Reto Extra 7: ¿Es válida una velocidad de reproducción? El {@code rate} debe estar en (0, 8].
     */
    public static boolean velocidadValida(double rate) {
        // GUÍA: teoría 4.5 (MediaPlayer.setRate admite de 0 a 8x; 0 o negativo no reproduce).
        // 1. return rate > 0 && rate <= 8.0;
        // OJO: el test comprueba 1.0 -> true, 0.0 -> false y 9.0 -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para velocidadValida");
    }

    /**
     * Reto Extra 8: Estado al que se pasa cuando la pista TERMINA.
     * Si está en modo repetir, vuelve a "PLAYING" (la canción se reinicia); si no, "STOPPED".
     */
    public static String estadoAlTerminar(boolean repetir) {
        // GUÍA: teoría 4.6 (el evento onEndOfMedia: o repites o paras).
        // 1. return repetir ? "PLAYING" : "STOPPED";
        // OJO: el test comprueba repetir=true -> "PLAYING".
        // CULTURA: este es el callback setOnEndOfMedia(...) que dispara el MediaPlayer al acabar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estadoAlTerminar");
    }

    /**
     * Reto Extra 9: Tiempo restante de reproducción (nunca negativo).
     */
    public static double tiempoRestante(double actual, double total) {
        // GUÍA: teoría 4.3 (el "-1:23" que cuenta hacia atrás).
        // 1. return Math.max(0, total - actual);
        // OJO: el test usa (40, 100) -> 60 y (120, 100) -> 0 (si te pasas, no hay tiempo negativo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tiempoRestante");
    }

    /**
     * Reto Extra 10: ¿Es un nombre de estado válido del reproductor?
     */
    public static boolean esEstadoValido(String estado) {
        // GUÍA: teoría 4.1 (validar la entrada antes de meterla en la máquina de estados).
        // 1. return "READY".equals(estado) || "PLAYING".equals(estado)
        //         || "PAUSED".equals(estado) || "STOPPED".equals(estado);
        // OJO: el test comprueba "PLAYING" -> true y "ROTO" / null -> false.
        // CULTURA: esta máquina de estados es la misma idea que el ciclo de vida de una conexión
        //   (b29 sockets) o de una Activity de Android (b42): estados + transiciones válidas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEstadoValido");
    }
}
