package com.masterclass.api.b40_media;

/**
 * Ejercicio 317 · {@code snapshot}: capturar un nodo/escena como imagen.
 *
 * <p>Teoría: {@code teoria/40_Multimedia.md} (sección 7).
 *
 * <p>JavaFX permite "fotografiar" cualquier nodo a una {@code WritableImage} con
 * {@code node.snapshot(params, null)}, indicando una ESCALA (para exportar a más resolución). Luego
 * esa imagen se guarda con {@code ImageIO} (lo que ya sabes de b26). Lo testeable aquí es la
 * MATEMÁTICA del snapshot: qué tamaño tiene la imagen resultante, cómo se direcciona un píxel en el
 * buffer lineal, cómo se codifica un color en hexadecimal y cómo se componen capas (alpha
 * compositing). Puro y sin pantalla.
 */
public final class Ej317NodeSnapshot {

    private Ej317NodeSnapshot() {
    }

    /**
     * Dimensiones en píxeles de la imagen que produce un snapshot de un nodo de
     * {@code anchoNodo x altoNodo} puntos, aplicando una {@code escala} (2.0 = el doble de
     * resolución). Se redondea HACIA ARRIBA porque la imagen no puede tener fracciones de píxel.
     *
     * @param anchoNodo ancho del nodo en puntos
     * @param altoNodo  alto del nodo en puntos
     * @param escala    factor de escala del snapshot
     * @return {@code [anchoPx, altoPx]} como enteros; {@code new int[]{-1,-1}} sin implementar
     */
    public static int[] dimensionSnapshot(double anchoNodo, double altoNodo, double escala) {
        // TODO 1: si escala <= 0 devuelve new int[]{0, 0} (escala inválida).
        // TODO 2: anchoPx = (int) Math.ceil(anchoNodo * escala).
        // TODO 3: altoPx  = (int) Math.ceil(altoNodo * escala).
        // TODO 4: return new int[]{anchoPx, altoPx}.
        // TODO 5: (el test usa 100x50 con escala 2.0 -> {200,100}; 99x99 con 1.0 -> {99,99}).
        return new int[]{-1, -1};
    }

    /**
     * Índice de un píxel ({@code fila, columna}) dentro del buffer LINEAL de la imagen (orden por
     * filas, "row-major"): es como guarda los píxeles un {@code PixelWriter} y como leíste bytes en
     * b26. {@code indice = fila * ancho + columna}.
     *
     * @param fila    fila del píxel (Y)
     * @param columna columna del píxel (X)
     * @param ancho   ancho de la imagen en píxeles
     * @return el índice lineal; -1 sin implementar
     */
    public static int indicePixel(int fila, int columna, int ancho) {
        // TODO 6: return fila * ancho + columna.
        // TODO 7: (la fila 0 columna 0 es el índice 0; la primera fila ocupa los índices 0..ancho-1).
        // TODO 8: (la fila 1 columna 0 es el índice 'ancho').
        // TODO 9: (esto es por qué una imagen WxH ocupa W*H posiciones seguidas en memoria).
        // TODO 10: el test usa fila 2, columna 3, ancho 10 -> 23.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Snapshot 100x50 @2x -> " + java.util.Arrays.toString(dimensionSnapshot(100, 50, 2.0)));
        System.out.println("Índice (2,3) en ancho 10 -> " + indicePixel(2, 3, 10));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Bytes que ocupa el snapshot en memoria (4 bytes ARGB por píxel).
     */
    public static long bytesSnapshot(int anchoPx, int altoPx) {
        // GUÍA: teoría 7.2 (un snapshot 4K @2x puede ocupar cientos de MB en RAM: ¡ojo!).
        // 1. return (long) anchoPx * altoPx * 4;
        // OJO: el test usa 200x100 -> 80000.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para bytesSnapshot");
    }

    /**
     * Reto Extra 2: Coordenada ({@code fila, columna}) a partir de un índice lineal (inverso del core).
     */
    public static int[] coordDesdeIndice(int indice, int ancho) {
        // GUÍA: teoría 7.3 (deshacer el row-major: la fila es la división, la columna el resto).
        // 1. fila = indice / ancho; columna = indice % ancho; return new int[]{fila, columna}.
        // OJO: el test usa indice 23, ancho 10 -> {2, 3} (inverso del core indicePixel).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para coordDesdeIndice");
    }

    /**
     * Reto Extra 3: ¿La región ({@code x,y,w,h}) cabe dentro de un nodo de {@code anchoNodo x altoNodo}?
     */
    public static boolean regionDentro(double x, double y, double w, double h, double anchoNodo, double altoNodo) {
        // GUÍA: teoría 7.4 (un snapshot parcial usa un viewport; debe quedar dentro del nodo).
        // 1. return x >= 0 && y >= 0 && (x+w) <= anchoNodo && (y+h) <= altoNodo;
        // OJO: el test prueba (10,10,50,50) en 100x100 -> true; (80,80,50,50) -> false (se sale).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para regionDentro");
    }

    /**
     * Reto Extra 4: Escala necesaria para que un nodo de {@code anchoNodo} salga con {@code anchoObjetivo} px.
     */
    public static double escalaParaResolucion(double anchoNodo, int anchoObjetivo) {
        // GUÍA: teoría 7.2 (exportar "a 4K" = elegir la escala que da ese ancho en píxeles).
        // 1. si anchoNodo == 0 devuelve 0. 2. return anchoObjetivo / anchoNodo;
        // OJO: el test usa nodo 960 y objetivo 1920 -> 2.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escalaParaResolucion");
    }

    /**
     * Reto Extra 5: Color ARGB a cadena hexadecimal "#RRGGBB" (sin alfa, dos dígitos por canal).
     */
    public static String aHexColor(int argb) {
        // GUÍA: teoría 7.5 (el formato #RRGGBB es el de CSS y el de Color.web de JavaFX).
        // 1. extrae r,g,b (como en Ej311). 2. return String.format("#%02X%02X%02X", r, g, b).
        // PISTA: %02X = dos dígitos hex en MAYÚSCULAS con cero a la izquierda.
        // OJO: el test usa 0xFF102030 -> "#102030" (el alfa FF se descarta).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aHexColor");
    }

    /**
     * Reto Extra 6: Cadena hexadecimal "#RRGGBB" a color ARGB OPACO (alfa 255).
     */
    public static int desdeHexColor(String hex) {
        // GUÍA: teoría 7.5 (parsear el color que escribe el usuario o un fichero CSS/tema).
        // 1. quita la almohadilla inicial si la hay (hex.startsWith("#")).
        // 2. rgb = Integer.parseInt(parte, 16); return 0xFF000000 | rgb;  (fuerza opaco).
        // OJO: el test usa "#102030" -> 0xFF102030 (inverso de aHexColor, ahora con alfa).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desdeHexColor");
    }

    /**
     * Reto Extra 7: Componer un píxel con alfa sobre un fondo opaco (alpha compositing "over").
     * out = primerPlano * alfa + fondo * (1 - alfa), por canal; el resultado es opaco.
     */
    public static int componerSobre(int argbPrimerPlano, int rgbFondo) {
        // GUÍA: teoría 7.6 (así se "pegan" capas semitransparentes: la fórmula de Porter-Duff "over").
        // 1. a = ((argbPrimerPlano>>>24)&0xFF) / 255.0;  // alfa normalizado 0..1.
        // 2. para cada canal: out = round(fg*a + bg*(1-a)). Extrae fg y bg de cada color.
        // 3. reempaqueta opaco: 0xFF000000 | (rOut<<16) | (gOut<<8) | bOut.
        // OJO: el test comprueba: alfa 255 (0xFFAABBCC sobre cualquier fondo) -> 0xFFAABBCC (gana el frente);
        //   alfa 0 (0x00AABBCC sobre 0x102030) -> 0xFF102030 (gana el fondo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para componerSobre");
    }

    /**
     * Reto Extra 8: Escalar manteniendo proporción fijando el ancho destino (devuelve enteros).
     */
    public static int[] escalarManteniendo(int ancho, int alto, int nuevoAncho) {
        // GUÍA: teoría 7.2 (al exportar a un ancho fijo, el alto se deduce y se redondea).
        // 1. si ancho == 0 devuelve {0,0}. 2. nuevoAlto = (int)Math.round((double)alto*nuevoAncho/ancho).
        // 3. return new int[]{nuevoAncho, nuevoAlto}.
        // OJO: el test usa (800,600,400) -> (400,300).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escalarManteniendo");
    }

    /**
     * Reto Extra 9: ¿Cuántas baldosas (tiles) hacen falta para cubrir una imagen con baldosas de
     * lado {@code tam}? Devuelve {@code [columnas, filas]} redondeando HACIA ARRIBA.
     */
    public static int[] baldosasNecesarias(int ancho, int alto, int tam) {
        // GUÍA: teoría 7.7 (trocear una imagen grande en tiles: mapas, sprite sheets, mosaicos).
        // 1. si tam <= 0 devuelve {0,0}.
        // 2. columnas = (int)Math.ceil(ancho/(double)tam); filas = (int)Math.ceil(alto/(double)tam).
        // 3. return new int[]{columnas, filas}.
        // OJO: el test usa (250,100) con tam 100 -> {3, 1} (250/100 redondea a 3, no a 2).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para baldosasNecesarias");
    }

    /**
     * Reto Extra 10: Convertir milímetros a puntos de impresión (1 pulgada = 72 pt = 25.4 mm).
     */
    public static double milimetrosAPuntos(double milimetros) {
        // GUÍA: teoría 7.8 (para imprimir/exportar a PDF un snapshot hay que pasar a la unidad "punto").
        // 1. return milimetros / 25.4 * 72.0;
        // OJO: el test usa 25.4 -> 72.0 (una pulgada). Caso 0 -> 0.
        // CULTURA: esta es la MISMA conversión que usaste al imprimir un nodo con PrinterJob en b38.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para milimetrosAPuntos");
    }
}
