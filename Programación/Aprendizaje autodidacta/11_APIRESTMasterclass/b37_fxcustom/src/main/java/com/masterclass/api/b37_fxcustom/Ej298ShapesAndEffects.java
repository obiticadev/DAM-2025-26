package com.masterclass.api.b37_fxcustom;

import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 298 · {@code Shape}, {@code Color}/{@code Paint} y {@code Effect}: la matemática del color.
 *
 * <p>Teoría: {@code teoria/37_JavaFX_Componentes_Canvas.md} (sección 6).
 *
 * <p>Las formas ({@code Rectangle}, {@code Circle}, {@code Polygon}) y los efectos
 * ({@code DropShadow}, {@code GaussianBlur}) se rellenan con un {@code Paint} (normalmente un
 * {@code Color}). Un {@code Color} en el fondo son tres canales 0..255 (rojo, verde, azul) más una
 * opacidad. Lo testeable y reutilizable es la <b>matemática del color</b>: mezclar, aclarar,
 * pasar a hexadecimal, calcular el contraste para elegir texto legible, interpolar un degradado.
 * El core trabaja con un color como {@code int[]{r, g, b}}; el Playground lo convierte a
 * {@code Color.rgb(...)} real para pintar formas y aplicar efectos.
 */
public final class Ej298ShapesAndEffects {

    private Ej298ShapesAndEffects() {
    }

    /**
     * Mezcla dos colores haciendo la media de cada canal (el degradado más simple: el punto medio).
     *
     * @param c1 primer color {@code [r, g, b]}
     * @param c2 segundo color {@code [r, g, b]}
     * @return el color mezclado {@code [r, g, b]}; {@code null} sin implementar
     */
    public static int[] mezclarColores(int[] c1, int[] c2) {
        // TODO 1: el rojo resultante es la media de c1[0] y c2[0]: (c1[0] + c2[0]) / 2 (división entera).
        // TODO 2: el verde resultante es (c1[1] + c2[1]) / 2.
        // TODO 3: el azul resultante es (c1[2] + c2[2]) / 2.
        // TODO 4: devuelve new int[]{ r, g, b }.
        // TODO 5: el test prueba [0,0,0] + [255,255,255] -> [127,127,127] (255/2 = 127, entero).
        return null;
    }

    /**
     * Convierte un color {@code [r, g, b]} a su cadena hexadecimal de CSS/web, en MAYÚSCULAS y con
     * dos dígitos por canal: {@code #RRGGBB}.
     *
     * @param r rojo 0..255
     * @param g verde 0..255
     * @param b azul 0..255
     * @return la cadena {@code "#RRGGBB"}; {@code ""} sin implementar
     */
    public static String aHex(int r, int g, int b) {
        // TODO 6: usa String.format con el patrón "#%02X%02X%02X".
        // TODO 7: el %02X formatea un int como 2 dígitos hexadecimales en MAYÚSCULAS (rellena con 0).
        // TODO 8: pásale r, g, b en ese orden.
        // TODO 9: devuelve la cadena formateada.
        // TODO 10: el test prueba (255,0,128) -> "#FF0080" y (0,0,0) -> "#000000".
        return "";
    }

    public static void main(String[] args) {
        System.out.println("Mezcla negro+blanco -> " + java.util.Arrays.toString(mezclarColores(
                new int[]{0, 0, 0}, new int[]{255, 255, 255})));
        System.out.println("Hex (255,0,128)     -> " + aHex(255, 0, 128));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: De hexadecimal a RGB.
     * El inverso de {@code aHex}: parsea "#RRGGBB" a sus tres canales.
     */
    public static int[] desdeHex(String hex) {
        // GUÍA: teoría 6.1 (Integer.parseInt(texto, 16) interpreta el texto en base 16).
        // 1. Coge los pares: hex.substring(1,3) (rojo), (3,5) (verde), (5,7) (azul).
        // 2. Convierte cada par con Integer.parseInt(par, 16).
        // 3. return new int[]{ r, g, b }.
        // OJO: el test prueba "#FF0080" -> [255, 0, 128]. El '#' está en el índice 0 (sáltatelo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desdeHex");
    }

    /**
     * Reto Extra 2: Aclarar un color.
     * Sube el brillo multiplicando cada canal por (1+factor), sin pasar de 255.
     */
    public static int[] aclarar(int[] color, double factor) {
        // GUÍA: teoría 6.2 (aclarar = acercar al blanco; Color.brighter() hace algo parecido).
        // 1. Por cada canal: nuevo = (int) Math.round(canal * (1 + factor)).
        // 2. ACÓTALO a 255 como máximo (Math.min(255, ...)).
        // 3. return new int[]{ r, g, b }.
        // PISTA: usa un bucle o repítelo a mano para los 3 canales.
        // OJO: el test prueba [100,100,100] con factor 0.5 -> [150,150,150]; canales altos topan en 255.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aclarar");
    }

    /**
     * Reto Extra 3: Oscurecer un color.
     * Baja el brillo multiplicando cada canal por (1-factor), sin bajar de 0.
     */
    public static int[] oscurecer(int[] color, double factor) {
        // GUÍA: teoría 6.2 (oscurecer = acercar al negro; Color.darker()).
        // 1. Por cada canal: nuevo = (int) Math.round(canal * (1 - factor)).
        // 2. ACÓTALO a 0 como mínimo (Math.max(0, ...)).
        // OJO: el test prueba [100,100,100] con factor 0.5 -> [50,50,50].
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para oscurecer");
    }

    /**
     * Reto Extra 4: Luminancia percibida.
     * El "brillo" que ve el ojo humano: pondera más el verde que el rojo, y el rojo más que el azul.
     */
    public static double luminancia(int[] color) {
        // GUÍA: teoría 6.3 (fórmula clásica 0.299·R + 0.587·G + 0.114·B; el ojo es más sensible al verde).
        // 1. return 0.299*color[0] + 0.587*color[1] + 0.114*color[2];
        // OJO: el test prueba blanco [255,255,255] -> 255.0 y negro [0,0,0] -> 0.0 (delta 1e-6).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para luminancia");
    }

    /**
     * Reto Extra 5: Color de texto legible sobre un fondo.
     * Si el fondo es oscuro, el texto va en blanco; si es claro, en negro (contraste automático).
     */
    public static String colorTextoLegible(int[] fondo) {
        // GUÍA: teoría 6.3 (la regla de oro de accesibilidad: texto que contraste con el fondo).
        // 1. Calcula la luminancia del fondo (reto 4).
        // 2. Si luminancia < 128 -> "blanco" (fondo oscuro); si no -> "negro".
        // OJO: el test prueba fondo blanco -> "negro" y fondo negro -> "blanco".
        // CULTURA: es el contraste WCAG de b36 (Ej290) aplicado a elegir el color del texto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para colorTextoLegible");
    }

    /**
     * Reto Extra 6: Color con opacidad (rgba).
     * Construye la cadena CSS rgba(r, g, b, a) con la opacidad indicada (0..1).
     */
    public static String conAlpha(int[] color, double alpha) {
        // GUÍA: teoría 6.4 (un Paint con opacidad: Color.rgb(r,g,b,a) / rgba(...) en CSS).
        // 1. return "rgba(" + color[0] + ", " + color[1] + ", " + color[2] + ", " + alpha + ")";
        // OJO: el test prueba [255,0,0] con alpha 0.5 -> "rgba(255, 0, 0, 0.5)" (espacios tras cada coma).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para conAlpha");
    }

    /**
     * Reto Extra 7: Interpolar dos colores (parada de degradado).
     * Para t=0 devuelve c1, para t=1 devuelve c2, y para t intermedio, el color a esa fracción.
     */
    public static int[] interpolar(int[] c1, int[] c2, double t) {
        // GUÍA: teoría 6.4 (un LinearGradient calcula los colores intermedios así, canal a canal).
        // 1. Por cada canal: nuevo = (int) Math.round(c1[k] + (c2[k] - c1[k]) * t).
        // 2. return new int[]{ r, g, b }.
        // PISTA: para t=0.5 entre [0,0,0] y [255,255,255] da round(127.5)=128.
        // OJO: el test prueba c1=[0,0,0], c2=[255,255,255], t=0.5 -> [128,128,128] (Math.round redondea .5 hacia arriba).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para interpolar");
    }

    /**
     * Reto Extra 8: Radio de una sombra (parámetro de efecto).
     * El radio del DropShadow crece con la distancia y un factor, pero nunca es negativo.
     */
    public static double radioSombra(double distancia, double factor) {
        // GUÍA: teoría 6.5 (los Effect tienen parámetros numéricos; un radio negativo es ilegal).
        // 1. return Math.max(0, distancia * factor);
        // OJO: el test prueba (10, 0.5) -> 5.0 y (10, -2) -> 0.0 (se acota a 0, no negativo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para radioSombra");
    }

    /**
     * Reto Extra 9: Mezcla ponderada de dos colores.
     * Como {@code mezclarColores} pero con un peso: peso=0 -> todo c1; peso=1 -> todo c2.
     */
    public static int[] mezclarPonderada(int[] c1, int[] c2, double peso) {
        // GUÍA: teoría 6.5 (mezcla con peso = interpolación, base de animar un color de A a B).
        // 1. Por cada canal: nuevo = (int) Math.round(c1[k] * (1 - peso) + c2[k] * peso).
        // 2. return new int[]{ r, g, b }.
        // PISTA: con peso=0.25 entre [0,0,0] y [100,100,100] da round(25.0)=25.
        // OJO: el test prueba c1=[0,0,0], c2=[100,100,100], peso=0.25 -> [25,25,25].
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mezclarPonderada");
    }

    /**
     * Reto Extra 10: Paleta de tonos a partir de un color base.
     * Genera N tonos cada vez más oscuros (paso fijo de 0.25), empezando por el color base.
     */
    public static List<int[]> paletaDesde(int[] base, int n) {
        // GUÍA: teoría 6.6 (una paleta de tema se deriva de un color "semilla", como en b36).
        // 1. Crea una List<int[]>.
        // 2. Para i de 0 a n-1, añade oscurecer(base, i * 0.25) (reto 3): el tono i.
        // 3. Devuelve la lista (el primero, i=0, es el propio base sin oscurecer).
        // PISTA: reutiliza oscurecer(base, i * 0.25).
        // OJO: el test usa base [200,200,200], n=2 -> [[200,200,200],[150,150,150]]
        //   (i=0 factor 0 -> base; i=1 factor 0.25 -> 200*0.75=150).
        // CULTURA: derivar una paleta de un color base es lo que hacen los "looked-up colors" de b36.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para paletaDesde");
    }

    /** Helper de demostración: arma una lista mutable de colores. */
    static List<int[]> colores(int[]... cs) {
        return new ArrayList<>(List.of(cs));
    }
}
