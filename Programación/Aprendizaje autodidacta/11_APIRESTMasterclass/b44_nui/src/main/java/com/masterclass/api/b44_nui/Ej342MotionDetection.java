package com.masterclass.api.b44_nui;

import java.util.List;

/**
 * Ejercicio 342 · Detección de movimiento: diferencia de frames, umbral y suavizado.
 *
 * <p>La forma más simple y robusta de detectar movimiento en una cámara es restar dos fotogramas
 * consecutivos: los píxeles que cambian "mucho" (más que un umbral) son movimiento. Aquí cada frame
 * es una matriz de intensidades {@code int[][]} (escala de grises 0..255). El suavizado por media
 * móvil quita el ruido de la señal resultante.
 *
 * <p>Teoría: {@code teoria/44_Interfaces_Naturales.md} (sección 6).
 */
public final class Ej342MotionDetection {

    private Ej342MotionDetection() {
    }

    /**
     * Fracción de píxeles que cambian más que el umbral entre dos fotogramas.
     *
     * @param f1     primer fotograma (intensidades)
     * @param f2     segundo fotograma, de las MISMAS dimensiones
     * @param umbral diferencia mínima de intensidad para contar como movimiento
     * @return fracción en [0,1] de píxeles en movimiento; -1 si las dimensiones no coinciden
     */
    public static double fraccionMovimiento(int[][] f1, int[][] f2, int umbral) {
        // TODO 1: si f1 o f2 son null, o tienen distinto nº de filas -> -1.
        // TODO 2: prepara contadores: total de píxeles y píxeles en movimiento.
        // TODO 3: recorre fila a fila comprobando que cada fila mide igual; si no -> -1.
        // TODO 4: por cada píxel, si Math.abs(f1-f2) > umbral, incrementa el contador de movimiento.
        // TODO 5: si total es 0 devuelve 0; si no, devuelve movimiento/total como double.
        return -1;
    }

    /**
     * Media móvil de una serie: cada valor se promedia con los anteriores dentro de la ventana.
     *
     * @param serie   valores de la señal (p. ej. fracción de movimiento por frame)
     * @param ventana tamaño de la ventana (>=1)
     * @return la serie suavizada; arreglo vacío si la serie es null o la ventana es inválida
     */
    public static double[] mediaMovil(double[] serie, int ventana) {
        // TODO 6: si serie es null o ventana < 1 -> devuelve new double[0] (centinela).
        // TODO 7: crea un arreglo resultado del mismo tamaño que la serie.
        // TODO 8: para cada índice i, considera los elementos desde max(0, i-ventana+1) hasta i.
        // TODO 9: suma esos elementos y divide por cuántos hay (media de la ventana hasta i).
        // TODO 10: guarda la media en resultado[i] y, al terminar, devuelve el arreglo.
        return new double[0];
    }

    public static void main(String[] args) {
        int[][] f1 = {{0, 0}, {0, 0}};
        int[][] f2 = {{0, 100}, {0, 0}};
        System.out.println("Fracción de movimiento: " + fraccionMovimiento(f1, f2, 50));
        System.out.println("Media móvil v1: " + java.util.Arrays.toString(mediaMovil(new double[]{2, 4, 6}, 1)));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Diferencia absoluta de dos intensidades.
     * El bloque elemental de la resta de frames.
     */
    public static int diferenciaAbsoluta(int a, int b) {
        // GUÍA: teoría 6 (|f1-f2| por píxel es la base de toda detección por diferencia).
        // 1. Devuelve Math.abs(a - b).
        // OJO: el test: (10,3)->7; (3,10)->7 (siempre positivo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para diferenciaAbsoluta");
    }

    /**
     * Reto Extra 2: ¿Supera el umbral?
     * Decide si una diferencia cuenta como movimiento.
     */
    public static boolean superaUmbral(int diferencia, int umbral) {
        // GUÍA: teoría 6 (el umbral separa ruido de movimiento real).
        // 1. Devuelve diferencia > umbral (estrictamente mayor).
        // OJO: el test: (60,50)->true; (40,50)->false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para superaUmbral");
    }

    /**
     * Reto Extra 3: Contar píxeles activos en una matriz de diferencias.
     * Cuántos píxeles superan el umbral en toda la imagen.
     */
    public static int contarPixelesActivos(int[][] diferencias, int umbral) {
        // GUÍA: teoría 6 (el recuento da el "tamaño" del movimiento detectado).
        // 1. Matriz null/vacía -> 0.
        // 2. Recorre y cuenta los valores > umbral.
        // OJO: el test: {{10,60},{70,5}} umbral 50 -> 2; matriz vacía -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarPixelesActivos");
    }

    /**
     * Reto Extra 4: Binarizar una fila de diferencias.
     * Cada valor pasa a 1 si supera el umbral, o 0 si no (máscara de movimiento).
     */
    public static int[] binarizarFila(int[] fila, int umbral) {
        // GUÍA: teoría 6 (la máscara binaria 0/1 marca DÓNDE hay movimiento).
        // 1. Fila null -> new int[0].
        // 2. Para cada valor, 1 si > umbral, 0 en caso contrario.
        // OJO: el test: ([10,60], 50) -> [0,1].
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para binarizarFila");
    }

    /**
     * Reto Extra 5: ¿Hay movimiento según la sensibilidad?
     * Compara la fracción de movimiento con la sensibilidad configurada.
     */
    public static boolean detectaMovimiento(double fraccion, double sensibilidad) {
        // GUÍA: teoría 6 (sensibilidad alta = salta con poco; baja = solo con movimiento amplio).
        // 1. Devuelve fraccion >= sensibilidad.
        // OJO: el test: (0.3,0.2)->true; (0.1,0.2)->false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para detectaMovimiento");
    }

    /**
     * Reto Extra 6: Frames procesados al saltar (frame skipping).
     * Si solo procesas 1 de cada N frames, ¿cuántos procesas de un total?
     */
    public static int framesProcesados(int totalFrames, int cada) {
        // GUÍA: teoría 6 (procesar todos los frames es caro; se salta para ahorrar CPU).
        // 1. Si total <= 0 o cada <= 0 -> 0.
        // 2. Devuelve el techo de total/cada: (total + cada - 1) / cada.
        // OJO: el test: (10,2)->5; (0,2)->0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para framesProcesados");
    }

    /**
     * Reto Extra 7: Ajustar por iluminación.
     * Resta un offset de iluminación y recorta a [0,255].
     */
    public static int ajustarPorIluminacion(int valor, int offset) {
        // GUÍA: teoría 6 (un cambio global de luz NO es movimiento; se compensa el offset).
        // 1. r = valor - offset; recórtalo a [0,255] (clamp).
        // PISTA: Math.max(0, Math.min(255, valor - offset)).
        // OJO: el test: (100,30)->70; (10,30)->0 (no negativo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ajustarPorIluminacion");
    }

    /**
     * Reto Extra 8: Contar movimiento dentro de una región de interés (ROI).
     * Solo cuenta los píxeles que superan el umbral dentro de un rectángulo.
     */
    public static int contarEnRegion(int[][] m, int fila0, int col0, int alto, int ancho, int umbral) {
        // GUÍA: teoría 6 (ROI: vigilar solo una zona —la puerta— ignora el resto de la escena).
        // 1. Matriz null/vacía -> 0.
        // 2. Recorre solo las filas [fila0, fila0+alto) y columnas [col0, col0+ancho) que existan;
        //    cuenta los valores > umbral.
        // OJO: el test: {{0,60},{70,0}}, región (0,0) alto 2 ancho 1, umbral 50 -> col 0: 0 y 70 -> 1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarEnRegion");
    }

    /**
     * Reto Extra 9: Umbral adaptativo por la media de la imagen.
     * El umbral se ajusta a la intensidad media multiplicada por un factor.
     */
    public static double umbralAdaptativoMedia(int[][] m, double factor) {
        // GUÍA: teoría 6 (en escenas oscuras el umbral fijo falla; se escala con la media local).
        // 1. Matriz null/vacía -> 0.
        // 2. Calcula la media de todos los píxeles y devuelve media * factor.
        // OJO: el test: {{0,100},{0,0}} (media 25) factor 1.0 -> 25.0; matriz vacía -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para umbralAdaptativoMedia");
    }

    /**
     * Reto Extra 10: Alarma por movimiento sostenido.
     * Solo dispara si hay movimiento durante varios frames seguidos (evita falsos positivos).
     */
    public static boolean alarmaSostenida(List<Boolean> detecciones, int minConsecutivos) {
        // GUÍA: teoría 6 (un parpadeo no es intrusión; se exige una racha mínima). Enlaza con b27
        //   (procesar frames en paralelo) y b40 (convolución/filtros).
        // 1. Lista null/vacía -> false.
        // 2. Recorre contando la racha de 'true'; si alguna racha alcanza minConsecutivos -> true.
        // OJO: el test: [false,true,true,false] min 2 -> true; [true,false,true] min 2 -> false.
        // CULTURA: en b40 los frames se procesaban por píxel; aquí esa señal alimenta la alarma.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para alarmaSostenida");
    }
}
