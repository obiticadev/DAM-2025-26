package com.masterclass.api.b40_media;

/**
 * Ejercicio 313 · Transformaciones geométricas: recortar, escalar, rotar y miniaturas.
 *
 * <p>Teoría: {@code teoria/40_Multimedia.md} (sección 3).
 *
 * <p>Mientras que los filtros (Ej312) cambian el COLOR de cada píxel, las transformaciones cambian
 * su POSICIÓN: recortar deja una sub-rejilla, rotar reordena, escalar inventa o descarta píxeles.
 * Trabajamos con {@code int[][]} en escala de grises (0..255) y la convención
 * {@code matriz[fila][columna]}, donde la fila es la Y (hacia abajo) y la columna la X (hacia la
 * derecha). Todo es manipulación de índices: puro y testeable sin pantalla.
 */
public final class Ej313ImageTransform {

    private Ej313ImageTransform() {
    }

    /**
     * Recorta una sub-imagen rectangular a partir de la esquina ({@code filaIni, colIni}).
     *
     * @param m       matriz original
     * @param filaIni fila de inicio (incluida)
     * @param colIni  columna de inicio (incluida)
     * @param alto    número de filas a copiar
     * @param ancho   número de columnas a copiar
     * @return la sub-matriz {@code alto x ancho}; {@code new int[0][0]} sin implementar
     */
    public static int[][] recortar(int[][] m, int filaIni, int colIni, int alto, int ancho) {
        // TODO 1: si m es null/vacío o alto<=0 o ancho<=0, devuelve new int[0][0].
        // TODO 2: crea int[][] salida de tamaño alto x ancho.
        // TODO 3: recorre f de 0 a alto-1 y c de 0 a ancho-1.
        // TODO 4: copia salida[f][c] = m[filaIni + f][colIni + c].
        // TODO 5: devuelve salida (el test recorta el centro 2x2 de una matriz 3x3).
        return new int[0][0];
    }

    /**
     * Rota la imagen 90 grados en sentido HORARIO. Una matriz de {@code filas x columnas} pasa a
     * {@code columnas x filas}.
     *
     * @param m matriz original
     * @return la matriz rotada; {@code new int[0][0]} sin implementar
     */
    public static int[][] rotar90Horario(int[][] m) {
        // TODO 6: si m es null/vacío, devuelve new int[0][0].
        // TODO 7: sea filas=m.length, cols=m[0].length; crea salida de tamaño cols x filas.
        // TODO 8: recorre i de 0 a cols-1, j de 0 a filas-1.
        // TODO 9: aplica la fórmula del giro horario: salida[i][j] = m[filas-1-j][i].
        // TODO 10: devuelve salida. (el test rota {{1,2,3},{4,5,6}} -> {{4,1},{5,2},{6,3}}).
        return new int[0][0];
    }

    public static void main(String[] args) {
        int[][] m = {{1, 2, 3}, {4, 5, 6}};
        System.out.println("Rotada 90°: " + java.util.Arrays.deepToString(rotar90Horario(m)));
        System.out.println("Recorte 1x2 en (0,1): " + java.util.Arrays.deepToString(recortar(m, 0, 1, 1, 2)));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Voltear horizontalmente (espejo izquierda-derecha).
     */
    public static int[][] voltearHorizontal(int[][] m) {
        // GUÍA: teoría 3.2 (el "selfie espejo": invierte el orden de las columnas de cada fila).
        // 1. crea salida del mismo tamaño; salida[f][c] = m[f][cols-1-c].
        // OJO: el test usa {{1,2,3}} -> {{3,2,1}}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para voltearHorizontal");
    }

    /**
     * Reto Extra 2: Voltear verticalmente (espejo arriba-abajo).
     */
    public static int[][] voltearVertical(int[][] m) {
        // GUÍA: teoría 3.2 (invierte el orden de las filas; las columnas no cambian).
        // 1. salida[f][c] = m[filas-1-f][c].
        // OJO: el test usa {{1},{2},{3}} -> {{3},{2},{1}}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para voltearVertical");
    }

    /**
     * Reto Extra 3: Transponer (intercambiar filas por columnas).
     */
    public static int[][] transponer(int[][] m) {
        // GUÍA: teoría 3.3 (la transposición es media rotación; salida es cols x filas).
        // 1. salida[c][f] = m[f][c] (o salida[i][j] = m[j][i]).
        // OJO: el test usa {{1,2,3},{4,5,6}} -> {{1,4},{2,5},{3,6}}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para transponer");
    }

    /**
     * Reto Extra 4: Rotar 180 grados (cabeza abajo).
     */
    public static int[][] rotar180(int[][] m) {
        // GUÍA: teoría 3.3 (rotar 180 = invertir filas Y columnas a la vez).
        // 1. salida[f][c] = m[filas-1-f][cols-1-c].
        // PISTA: equivale a voltearHorizontal(voltearVertical(m)).
        // OJO: el test usa {{1,2},{3,4}} -> {{4,3},{2,1}}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rotar180");
    }

    /**
     * Reto Extra 5: Rotar 90 grados en sentido ANTIHORARIO.
     */
    public static int[][] rotar90Antihorario(int[][] m) {
        // GUÍA: teoría 3.3 (el giro contrario al del core; cambia los índices de la fórmula).
        // 1. filas=m.length, cols=m[0].length; salida es cols x filas.
        // 2. salida[i][j] = m[j][cols-1-i].
        // OJO: el test usa {{1,2,3},{4,5,6}} -> {{3,6},{2,5},{1,4}}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rotar90Antihorario");
    }

    /**
     * Reto Extra 6: Dimensiones de una miniatura manteniendo la proporción.
     * El lado más largo pasa a valer {@code maxLado}; el otro se reduce proporcionalmente.
     */
    public static int[] dimensionMiniatura(int ancho, int alto, int maxLado) {
        // GUÍA: teoría 3.5 (un thumbnail nunca debe deformar: se escala con un único factor).
        // 1. si ancho>=alto: factor = maxLado/(double)ancho; nuevoAncho=maxLado, nuevoAlto=round(alto*factor).
        // 2. si no: al revés (el alto manda).
        // 3. return new int[]{nuevoAncho, nuevoAlto}.
        // OJO: el test usa (800,400,200) -> (200,100). Caso límite cuadrado (100,100,50) -> (50,50).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dimensionMiniatura");
    }

    /**
     * Reto Extra 7: Recortar el cuadrado central más grande (para avatares cuadrados).
     */
    public static int[][] recortarCuadradoCentral(int[][] m) {
        // GUÍA: teoría 3.4 (el lado del cuadrado es el menor de ancho/alto; se centra el recorte).
        // 1. lado = Math.min(filas, cols).
        // 2. filaIni = (filas-lado)/2; colIni = (cols-lado)/2.
        // 3. reutiliza recortar(m, filaIni, colIni, lado, lado) (el core).
        // OJO: el test usa una matriz 2x4 -> sale 2x2 con las columnas centrales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para recortarCuadradoCentral");
    }

    /**
     * Reto Extra 8: Escalar por "vecino más cercano" (nearest neighbor).
     * Cambia el tamaño tomando, para cada píxel de destino, el píxel de origen más próximo.
     */
    public static int[][] escalarVecino(int[][] m, int nuevoAlto, int nuevoAncho) {
        // GUÍA: teoría 3.6 (el método más simple de reescalado; rápido pero "pixelado").
        // 1. filas=m.length, cols=m[0].length; crea salida nuevoAlto x nuevoAncho.
        // 2. para cada (f,c) de destino: fOrig = f*filas/nuevoAlto; cOrig = c*cols/nuevoAncho (división ENTERA).
        // 3. salida[f][c] = m[fOrig][cOrig].
        // PISTA: la división entera hace de "redondeo hacia abajo" del vecino.
        // OJO: el test duplica {{1,2}} (1x2) a 1x4 -> {{1,1,2,2}}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escalarVecino");
    }

    /**
     * Reto Extra 9: Incrustar (pegar) una imagen pequeña sobre otra en una posición dada.
     * Copia {@code sello} sobre una COPIA de {@code fondo} en la esquina ({@code fila, col});
     * ignora la parte del sello que se salga del fondo.
     */
    public static int[][] incrustar(int[][] fondo, int[][] sello, int fila, int col) {
        // GUÍA: teoría 3.7 (pegar una marca de agua o un logo = copiar píxeles en una región).
        // 1. copia fondo a una matriz nueva (no modifiques el original).
        // 2. recorre el sello (sf,sc); destino df=fila+sf, dc=col+sc.
        // 3. si df,dc caen DENTRO de la copia, copia sello[sf][sc]; si no, ignóralo.
        // OJO: el test pega {{9}} sobre {{1,2},{3,4}} en (0,1) -> {{1,9},{3,4}} y comprueba que el
        //   fondo original no cambió.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para incrustar");
    }

    /**
     * Reto Extra 10: Dimensiones tras rotar un múltiplo de 90 grados.
     * Para 90 y 270 se intercambian ancho y alto; para 0 y 180 se mantienen.
     */
    public static int[] dimensionTrasRotacion(int ancho, int alto, int grados) {
        // GUÍA: teoría 3.8 (saber el tamaño final ANTES de rotar evita reservar un buffer mal).
        // 1. normaliza el ángulo: g = ((grados % 360) + 360) % 360.
        // 2. si g==90 o g==270 -> return new int[]{alto, ancho}; si no -> {ancho, alto}.
        // OJO: el test usa (4,2,90) -> (2,4) y (4,2,180) -> (4,2). Prueba también un ángulo negativo.
        // CULTURA: las fotos del móvil guardan la rotación en el metadato EXIF "Orientation" en vez
        //   de rotar los píxeles; el visor la aplica al mostrar (lo lees con los metadatos de Ej318).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dimensionTrasRotacion");
    }
}
