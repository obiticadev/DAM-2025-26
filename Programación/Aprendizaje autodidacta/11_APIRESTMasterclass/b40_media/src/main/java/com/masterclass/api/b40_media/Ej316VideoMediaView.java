package com.masterclass.api.b40_media;

/**
 * Ejercicio 316 · Vídeo con {@code MediaView}: geometría de ajuste (letterbox).
 *
 * <p>Teoría: {@code teoria/40_Multimedia.md} (sección 6).
 *
 * <p>Mostrar vídeo es poner un {@code MediaView} (un nodo) que pinta los fotogramas de un
 * {@code MediaPlayer}. El reto NO es el códec (eso lo hace JavaFX), sino ENCAJAR un vídeo de unas
 * dimensiones en un contenedor de otras SIN deformarlo: hay que conservar la relación de aspecto y
 * dejar barras negras (<i>letterbox</i>) o recortar (<i>cover</i>). Eso es geometría pura: un
 * punto se devuelve como {@code double[]{ancho, alto}}. Testeable sin reproducir nada.
 */
public final class Ej316VideoMediaView {

    private Ej316VideoMediaView() {
    }

    /**
     * Escala un vídeo ({@code vw x vh}) para que QUEPA ENTERO dentro de un contenedor
     * ({@code cw x ch}) conservando la proporción (modo "contain"/letterbox): se usa el menor
     * factor de escala de los dos ejes.
     *
     * @param vw ancho del vídeo
     * @param vh alto del vídeo
     * @param cw ancho del contenedor
     * @param ch alto del contenedor
     * @return {@code [anchoEscalado, altoEscalado]}; {@code null} sin implementar
     */
    public static double[] escalarParaCaber(double vw, double vh, double cw, double ch) {
        // TODO 1: si vw<=0 o vh<=0 devuelve null (no hay vídeo que escalar).
        // TODO 2: calcula el factor del eje X: fx = cw / vw.
        // TODO 3: calcula el factor del eje Y: fy = ch / vh.
        // TODO 4: el factor a usar es el MENOR de los dos (Math.min), para que quepa entero.
        // TODO 5: return new double[]{ vw*factor, vh*factor }.
        //         (el test usa 1920x1080 en 1280x720 -> 1280x720; 200x100 en 100x100 -> 100x50).
        return null;
    }

    /**
     * ¿El formato es apaisado (horizontal)? Lo es cuando el ancho es mayor que el alto.
     *
     * @param ancho ancho
     * @param alto  alto
     * @return true si es apaisado; false sin implementar (centinela compatible)
     */
    public static boolean esApaisado(double ancho, double alto) {
        // TODO 6: return ancho > alto.
        // TODO 7: (un vídeo 16:9 es apaisado; uno 9:16 de móvil, vertical).
        // TODO 8: (cuadrado 1:1 NO es apaisado: ancho==alto -> false).
        // TODO 9: el test prueba (16,9) -> true.
        // TODO 10: el test prueba (9,16) -> false.
        return false;
    }

    public static void main(String[] args) {
        double[] r = escalarParaCaber(1920, 1080, 1280, 720);
        System.out.println("1080p en 1280x720 -> " + (r == null ? "sin implementar" : r[0] + "x" + r[1]));
        System.out.println("16:9 apaisado? " + esApaisado(16, 9));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Relación de aspecto (ancho / alto).
     */
    public static double relacionAspecto(double ancho, double alto) {
        // GUÍA: teoría 6.2 (16:9 = 1.777..., 4:3 = 1.333...).
        // 1. si alto == 0 devuelve 0 (evita dividir por cero). 2. return ancho / alto.
        // OJO: el test usa (16,9) -> 1.777... (usa delta).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para relacionAspecto");
    }

    /**
     * Reto Extra 2: Escalar para RELLENAR el contenedor (modo "cover": se recorta lo que sobra).
     */
    public static double[] escalarParaRellenar(double vw, double vh, double cw, double ch) {
        // GUÍA: teoría 6.3 (cover usa el MAYOR factor; el vídeo cubre todo y sobresale por un eje).
        // 1. fx = cw/vw; fy = ch/vh; factor = Math.max(fx, fy).
        // 2. return new double[]{ vw*factor, vh*factor }.
        // OJO: el test usa 200x100 en 100x100 -> factor max(0.5,1.0)=1.0 -> 200x100 (sobresale a lo ancho).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escalarParaRellenar");
    }

    /**
     * Reto Extra 3: Grosor de cada barra negra (letterbox) sobre un eje.
     */
    public static double barraLetterbox(double tamanoContenedor, double tamanoContenido) {
        // GUÍA: teoría 6.4 (el hueco sobrante se reparte en DOS barras iguales).
        // 1. return Math.max(0, (tamanoContenedor - tamanoContenido) / 2.0);
        // OJO: el test usa contenedor 720, contenido 540 -> 90 por barra. Si el contenido es mayor -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para barraLetterbox");
    }

    /**
     * Reto Extra 4: ¿Cabe el vídeo SIN escalar (a tamaño real) dentro del contenedor?
     */
    public static boolean cabeSinEscalar(double vw, double vh, double cw, double ch) {
        // GUÍA: teoría 6.3 (si ya es más pequeño, no hace falta tocar nada).
        // 1. return vw <= cw && vh <= ch;
        // OJO: el test prueba (100,100, 200,200) -> true y (300,100, 200,200) -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cabeSinEscalar");
    }

    /**
     * Reto Extra 5: Desplazamiento para CENTRAR un elemento dentro de un contenedor (un eje).
     */
    public static double offsetCentrado(double tamanoContenedor, double tamanoElemento) {
        // GUÍA: teoría 6.4 (centrar = dejar la mitad del hueco a cada lado).
        // 1. return (tamanoContenedor - tamanoElemento) / 2.0;
        // OJO: el test usa contenedor 100, elemento 40 -> 30. Puede salir negativo si el elemento es mayor.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para offsetCentrado");
    }

    /**
     * Reto Extra 6: ¿La relación de aspecto es 16:9 (con tolerancia)?
     */
    public static boolean es16por9(double ancho, double alto) {
        // GUÍA: teoría 6.2 (comparar floats SIEMPRE con un epsilon, nunca con ==).
        // 1. if (alto == 0) return false; return Math.abs(ancho/alto - 16.0/9.0) < 0.01;
        // OJO: el test prueba (1920,1080) -> true y (1024,768) [4:3] -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para es16por9");
    }

    /**
     * Reto Extra 7: Escalar fijando el ANCHO destino y deduciendo el alto proporcional.
     */
    public static double[] escalarAAncho(double vw, double vh, double nuevoAncho) {
        // GUÍA: teoría 6.5 (fitWidth de MediaView con preserveRatio activado).
        // 1. si vw == 0 devuelve null. 2. factor = nuevoAncho/vw.
        // 3. return new double[]{ nuevoAncho, vh*factor }.
        // OJO: el test usa (1920,1080,960) -> (960,540).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escalarAAncho");
    }

    /**
     * Reto Extra 8: Escalar fijando el ALTO destino y deduciendo el ancho proporcional.
     */
    public static double[] escalarAAlto(double vw, double vh, double nuevoAlto) {
        // GUÍA: teoría 6.5 (fitHeight de MediaView; simétrico del reto 7).
        // 1. si vh == 0 devuelve null. 2. factor = nuevoAlto/vh; return {vw*factor, nuevoAlto}.
        // OJO: el test usa (1920,1080,540) -> (960,540).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escalarAAlto");
    }

    /**
     * Reto Extra 9: Porcentaje de escala aplicado (tamañoEscalado / tamañoOriginal, en %).
     */
    public static int porcentajeEscala(double original, double escalado) {
        // GUÍA: teoría 6.5 (el "75%" que muestra un reproductor al ajustar el zoom).
        // 1. si original == 0 devuelve 0. 2. return (int) Math.round(escalado/original*100).
        // OJO: el test usa (1920, 1440) -> 75.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para porcentajeEscala");
    }

    /**
     * Reto Extra 10: Píxeles recortados por eje en modo "cover".
     * Tras escalar para rellenar, devuelve cuánto sobresale del contenedor por cada eje
     * {@code [recorteAncho, recorteAlto]} (lo que se recorta, ya como sobrante TOTAL del eje).
     */
    public static double[] recorteCover(double vw, double vh, double cw, double ch) {
        // GUÍA: teoría 6.3 (en cover el vídeo sobresale; esto mide cuánto se pierde).
        // 1. escalado = escalarParaRellenar(vw,vh,cw,ch) (reto 2).
        // 2. recorteAncho = Math.max(0, escalado[0]-cw); recorteAlto = Math.max(0, escalado[1]-ch).
        // 3. return new double[]{recorteAncho, recorteAlto}.
        // OJO: el test usa 200x100 en 100x100: escalado 200x100 -> recorte {100, 0} (sobra a lo ancho).
        // CULTURA: esto es exactamente el object-fit: cover de CSS y el centerCrop de Android (b42).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para recorteCover");
    }
}
