package com.masterclass.api.b40_media;

import java.util.Random;

/**
 * Ejercicio 315 · Control avanzado de audio: <i>seek</i>, listas de reproducción y muestras.
 *
 * <p>Teoría: {@code teoria/40_Multimedia.md} (sección 5).
 *
 * <p>Sobre la reproducción básica (Ej314) se construye lo que el usuario espera de un reproductor:
 * saltar a un punto (<i>seek</i>) sin pasarse de la duración, encadenar una LISTA de canciones
 * (siguiente/anterior, repetir, barajar) y trabajar con las MUESTRAS del audio (normalizar
 * volumen, mezclar estéreo a mono, decibelios). Todo es aritmética y manejo de índices: puro y
 * testeable, sin tarjeta de sonido.
 */
public final class Ej315AudioControl {

    private Ej315AudioControl() {
    }

    /**
     * Índice de la siguiente pista de una lista. Si hay siguiente, avanza; si es la última y está
     * activado {@code repetir}, vuelve a la 0; si no, devuelve -1 (no hay siguiente).
     *
     * @param actual  índice de la pista actual
     * @param total   número de pistas de la lista
     * @param repetir si la lista se repite al llegar al final
     * @return índice de la siguiente pista o -1; -2 sin implementar
     */
    public static int siguientePista(int actual, int total, boolean repetir) {
        // TODO 1: si total <= 0 devuelve -1 (lista vacía, no hay nada que reproducir).
        // TODO 2: si actual + 1 < total devuelve actual + 1 (hay una pista más).
        // TODO 3: si estamos en la última y repetir es true, devuelve 0 (vuelve al principio).
        // TODO 4: en otro caso (última y sin repetir) devuelve -1.
        // TODO 5: (el test prueba (0,3,false)=1, (2,3,false)=-1, (2,3,true)=0).
        return -2;
    }

    /**
     * Recorta una posición de <i>seek</i> al rango válido [0, duracion].
     *
     * @param posicion segundo al que se quiere saltar
     * @param duracion duración total de la pista
     * @return la posición recortada; -1 sin implementar
     */
    public static double clampSeek(double posicion, double duracion) {
        // TODO 6: si posicion < 0 devuelve 0 (no se puede saltar antes del inicio).
        // TODO 7: si posicion > duracion devuelve duracion (no más allá del final).
        // TODO 8: en otro caso devuelve posicion.
        // TODO 9: (puedes usar Math.max(0, Math.min(duracion, posicion))).
        // TODO 10: el test prueba (-5,100)=0, (150,100)=100, (50,100)=50.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Siguiente de la 0 (de 3) -> " + siguientePista(0, 3, false));
        System.out.println("Seek a 150 sobre 100s -> " + clampSeek(150, 100));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Índice de la pista ANTERIOR (simétrico de siguientePista).
     */
    public static int pistaAnterior(int actual, int total, boolean repetir) {
        // GUÍA: teoría 5.2 (el botón "anterior" es el espejo de "siguiente").
        // 1. si total<=0 -> -1. 2. si actual>0 -> actual-1.
        // 3. si es la primera y repetir -> total-1 (salta al final); si no -> -1.
        // OJO: el test prueba (2,3,false)=1, (0,3,false)=-1, (0,3,true)=2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pistaAnterior");
    }

    /**
     * Reto Extra 2: Duración total (suma) de una lista de pistas.
     */
    public static double duracionTotal(double[] duraciones) {
        // GUÍA: teoría 5.3 (el "1h 23min" total que muestra una playlist).
        // 1. recorre el array sumando; si es null o vacío devuelve 0.
        // OJO: el test usa {120, 200, 100} -> 420.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para duracionTotal");
    }

    /**
     * Reto Extra 3: ¿Es un índice de pista válido para una lista de {@code total} pistas?
     */
    public static boolean indiceValido(int indice, int total) {
        // GUÍA: teoría 5.2 (validar antes de acceder evita IndexOutOfBounds).
        // 1. return indice >= 0 && indice < total;
        // OJO: el test comprueba (0,3)=true, (3,3)=false (el último índice válido es total-1).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para indiceValido");
    }

    /**
     * Reto Extra 4: Tiempo acumulado ANTES de empezar la pista número {@code indice}.
     */
    public static double tiempoAcumuladoHasta(double[] duraciones, int indice) {
        // GUÍA: teoría 5.3 (en qué segundo global empieza la pista N de la lista).
        // 1. suma las duraciones de las pistas 0..indice-1 (no incluye la pista 'indice').
        // OJO: el test con {120,200,100} e indice 2 -> 320. indice 0 -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tiempoAcumuladoHasta");
    }

    /**
     * Reto Extra 5: ¿En qué pista cae un segundo global de la lista? (-1 si se pasa del total).
     */
    public static int pistaEnSegundo(double[] duraciones, double segundo) {
        // GUÍA: teoría 5.3 (convierte un punto del tiempo total al índice de pista que suena ahí).
        // 1. acumulado = 0. Recorre las pistas: acumulado += duraciones[i].
        // 2. si segundo < acumulado, devuelve i (el segundo cae dentro de esa pista).
        // 3. si el bucle termina sin encontrarla, devuelve -1.
        // OJO: el test con {100,100,100}: segundo 0 -> 0; segundo 150 -> 1; segundo 350 -> -1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pistaEnSegundo");
    }

    /**
     * Reto Extra 6: Normalizar una muestra de audio respecto a su pico (rango [-1, 1]).
     */
    public static double normalizarMuestra(double muestra, double pico) {
        // GUÍA: teoría 5.5 (normalizar = escalar para que el pico llegue al máximo sin distorsionar).
        // 1. si pico == 0 devuelve 0 (silencio, evita dividir por cero).
        // 2. return muestra / Math.abs(pico);
        // OJO: el test usa (0.5, 1.0) -> 0.5 y (0.25, 0.5) -> 0.5. Caso límite pico 0 -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarMuestra");
    }

    /**
     * Reto Extra 7: Mezclar dos canales (izquierdo y derecho) a mono.
     */
    public static double mezclarAMono(double izquierdo, double derecho) {
        // GUÍA: teoría 5.5 (mono = media de los dos canales estéreo).
        // 1. return (izquierdo + derecho) / 2.0;
        // OJO: el test usa (1.0, 0.0) -> 0.5.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mezclarAMono");
    }

    /**
     * Reto Extra 8: Convertir una ganancia lineal a decibelios: {@code 20 * log10(ganancia)}.
     */
    public static double gananciaADecibelios(double ganancia) {
        // GUÍA: teoría 5.6 (el oído percibe el volumen en escala logarítmica; de ahí los dB).
        // 1. si ganancia <= 0 devuelve Double.NEGATIVE_INFINITY (silencio absoluto = -inf dB).
        // 2. return 20 * Math.log10(ganancia);
        // OJO: el test usa ganancia 1.0 -> 0.0 dB y 10.0 -> 20.0 dB (usa delta). ganancia 0 -> -Infinity.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para gananciaADecibelios");
    }

    /**
     * Reto Extra 9: Duración total de la lista formateada como "m:ss".
     */
    public static String duracionTotalFormateada(double[] duraciones) {
        // GUÍA: teoría 5.3 (combina duracionTotal con el formateo de tiempo del Ej314).
        // 1. total = duracionTotal(duraciones) (reto 2).
        // 2. min = (int)total/60; seg = (int)total%60; devuélvelo como "m:ss" (segundos con 2 dígitos).
        // OJO: el test usa {120, 200, 100} (=420s) -> "7:00".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para duracionTotalFormateada");
    }

    /**
     * Reto Extra 10: Orden aleatorio DETERMINISTA de una lista (modo aleatorio reproducible).
     * Devuelve una permutación de 0..n-1 generada con una semilla fija (mismo orden cada vez).
     */
    public static int[] ordenAleatorio(int n, long semilla) {
        // GUÍA: teoría 5.4 (el "modo aleatorio" se implementa con una mezcla de Fisher-Yates).
        // 1. si n <= 0 devuelve new int[0].
        // 2. crea int[] orden = {0,1,...,n-1}.
        // 3. usa Random rnd = new Random(semilla); recorre i de n-1 a 1, j = rnd.nextInt(i+1),
        //    e intercambia orden[i] con orden[j] (mezcla de Fisher-Yates).
        // 4. devuelve orden.
        // PISTA: java.util.Random con la MISMA semilla da SIEMPRE la misma secuencia -> reproducible.
        // OJO: el test no fija el orden exacto, sino que comprueba que sea una PERMUTACIÓN de 0..n-1
        //   (ordenada da {0,1,2}) y que con la misma semilla salga idéntica dos veces.
        // CULTURA: la reproducibilidad con semilla es la misma idea que usarás para tests con datos
        //   "aleatorios" controlados y para el *spawn* de enemigos en el juego de b41.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ordenAleatorio");
    }

    /** Helper interno: no usar desde el test (solo evita el aviso de import sin usar si lo necesitas). */
    static Random nuevoAleatorio(long semilla) {
        return new Random(semilla);
    }
}
