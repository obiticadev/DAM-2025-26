package com.masterclass.api.b44_nui;

/**
 * Ejercicio 343 · Realidad aumentada: proyección, pose de marcador y matemática 3D↔2D.
 *
 * <p>La realidad aumentada superpone objetos virtuales sobre la imagen real. La pieza clave es
 * <strong>proyectar</strong> un punto 3D del mundo en un píxel 2D de la pantalla (dividiendo por la
 * profundidad: lo lejano se ve pequeño) y conocer la <em>pose</em> (posición + orientación) de un
 * marcador. Toda esa matemática es pura; ARCore/Vuforia detectan el marcador en la cámara ("guion").
 *
 * <p>Teoría: {@code teoria/44_Interfaces_Naturales.md} (sección 7).
 */
public final class Ej343ARMarkerMath {

    private Ej343ARMarkerMath() {
    }

    /**
     * Proyección en perspectiva de un punto 3D a coordenadas 2D de pantalla.
     *
     * @param punto3D arreglo {x, y, z} en el espacio de cámara (z = profundidad)
     * @param focal   distancia focal (escala de la proyección)
     * @return arreglo {x', y'} proyectado; {@code null} si el punto no es 3D o z es 0 (indefinido)
     */
    public static double[] proyectar(double[] punto3D, double focal) {
        // TODO 1: si punto3D es null o su longitud != 3 -> null.
        // TODO 2: extrae x, y, z.
        // TODO 3: si z == 0 -> null (no se puede dividir; el punto está en el plano de la cámara).
        // TODO 4: x' = focal * x / z ; y' = focal * y / z (lo lejano, z grande, se hace pequeño).
        // TODO 5: devuelve new double[]{x', y'}.
        return null;
    }

    /**
     * Multiplica una matriz 3×3 (pose/rotación) por un vector de longitud 3.
     *
     * @param m matriz 3×3
     * @param v vector de longitud 3
     * @return el vector resultante (longitud 3); {@code null} si las dimensiones no encajan
     */
    public static double[] multiplicarMatrizVector(double[][] m, double[] v) {
        // TODO 6: si m es null o no tiene 3 filas, o v es null o no mide 3 -> null.
        // TODO 7: comprueba que cada fila de m mide 3; si no -> null.
        // TODO 8: prepara el resultado de longitud 3.
        // TODO 9: resultado[i] = m[i][0]*v[0] + m[i][1]*v[1] + m[i][2]*v[2].
        // TODO 10: devuelve el vector resultante.
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Proyección de (2,4,2) f=1: " + java.util.Arrays.toString(proyectar(new double[]{2, 4, 2}, 1.0)));
        double[][] id = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        System.out.println("Identidad * (1,2,3): " + java.util.Arrays.toString(multiplicarMatrizVector(id, new double[]{1, 2, 3})));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Normalizar coordenadas homogéneas.
     * Divide (x, y, w) por w para obtener el punto 2D real.
     */
    public static double[] normalizarPerspectiva(double[] homogeneo) {
        // GUÍA: teoría 7 (las coordenadas homogéneas guardan la profundidad en w; dividir "aplana").
        // 1. Si homogeneo es null o no mide 3 -> null.
        // 2. Si w (la 3.ª componente) es 0 -> null.
        // 3. Devuelve new double[]{x/w, y/w}.
        // OJO: el test: {2,4,2}->{1,2}; w=0 -> null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarPerspectiva");
    }

    /**
     * Reto Extra 2: Error de reproyección.
     * Distancia entre dónde se proyectó un punto y dónde se observó (calidad de la pose).
     */
    public static double distanciaReproyeccion(double[] a, double[] b) {
        // GUÍA: teoría 7 (reprojection error: una pose buena reproyecta cerca de lo observado).
        // 1. Si a o b son null o no miden 2 -> -1.
        // 2. Devuelve Math.hypot(a[0]-b[0], a[1]-b[1]).
        // OJO: el test: ({0,0},{3,4})->5; puntos iguales -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para distanciaReproyeccion");
    }

    /**
     * Reto Extra 3: ¿La matriz es singular?
     * Una matriz 3×3 con determinante 0 no se puede invertir (pose ambigua).
     */
    public static boolean esMatrizSingular(double[][] m) {
        // GUÍA: teoría 7 (sin inversa no hay pose única; determinante 0 = degenerada).
        // 1. Si m no es 3×3 -> true (no es válida).
        // 2. Calcula el determinante por la regla de Sarrus y devuelve det == 0.
        // PISTA: det = a(ei−fh) − b(di−fg) + c(dh−eg).
        // OJO: el test: identidad -> false (det 1); matriz de ceros -> true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMatrizSingular");
    }

    /**
     * Reto Extra 4: Rotar un punto alrededor del eje Z.
     * Gira (x, y) un ángulo; z no cambia.
     */
    public static double[] rotarZ(double[] punto, double anguloGrados) {
        // GUÍA: teoría 7 (rotación 2D dentro del 3D: la base de orientar un objeto AR).
        // 1. Si punto no mide 3 -> null.
        // 2. rad = toRadians(ang); x' = x·cos − y·sen; y' = x·sen + y·cos; z' = z.
        // OJO: el test: rotar {1,0,0} 90° -> {0,1,0} (con tolerancia: cos90 no es exacto en double).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rotarZ");
    }

    /**
     * Reto Extra 5: Grados a radianes.
     * Conversión angular básica que usan todas las rotaciones.
     */
    public static double gradosARadianes(double grados) {
        // GUÍA: teoría 7 (Math.sin/cos trabajan en radianes, no en grados).
        // 1. Devuelve grados * Math.PI / 180 (o Math.toRadians).
        // OJO: el test: 180 -> Math.PI; 0 -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para gradosARadianes");
    }

    /**
     * Reto Extra 6: Escalar al tamaño del mundo real.
     * Multiplica cada componente del punto por una escala.
     */
    public static double[] escalarMundo(double[] punto, double escala) {
        // GUÍA: teoría 7 (el marcador mide X cm; hay que escalar el modelo a unidades reales).
        // 1. Si punto es null -> null.
        // 2. Devuelve un nuevo arreglo con cada componente * escala.
        // OJO: el test: {1,2,3} escala 2 -> {2,4,6}; escala 0 -> {0,0,0}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escalarMundo");
    }

    /**
     * Reto Extra 7: Centro de un marcador.
     * Promedio de sus 4 esquinas (cada una un punto 2D).
     */
    public static double[] centroDeMarcador(double[][] esquinas) {
        // GUÍA: teoría 7 (el centro del marcador es el ancla del objeto virtual).
        // 1. Si esquinas es null o está vacío -> null.
        // 2. Promedia las x y las y de todas las esquinas; devuelve {mediaX, mediaY}.
        // OJO: el test: {{0,0},{2,0},{2,2},{0,2}} -> {1,1}; vacío -> null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para centroDeMarcador");
    }

    /**
     * Reto Extra 8: ¿El punto está dentro del marcador?
     * Comprueba que el punto 2D cae en la caja [min, max] en ambos ejes.
     */
    public static boolean dentroDelMarcador(double[] p, double[] min, double[] max) {
        // GUÍA: teoría 7 (recortar el objeto a la zona del marcador; AABB 2D, como en b41).
        // 1. Si algún arreglo no mide 2 -> false.
        // 2. Devuelve min.x<=p.x<=max.x && min.y<=p.y<=max.y.
        // OJO: el test: p{1,1} en [{0,0},{2,2}] -> true; p{3,1} -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dentroDelMarcador");
    }

    /**
     * Reto Extra 9: ID del marcador desde su patrón de bits.
     * Convierte un patrón binario (los cuadros del marcador) en su identificador decimal.
     */
    public static int idMarcadorPorPatron(int[] bits) {
        // GUÍA: teoría 7 (cada marcador ArUco/QR codifica un ID en su rejilla de blancos y negros).
        // 1. bits null/vacío -> 0.
        // 2. Interpreta el arreglo como binario big-endian: id = id*2 + bit.
        // OJO: el test: {1,0,1} -> 5; {} -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para idMarcadorPorPatron");
    }

    /**
     * Reto Extra 10: Anclar un objeto a la pose del marcador.
     * Suma un offset a la posición del marcador para colocar el objeto virtual.
     */
    public static double[] anclarObjeto(double[] posMarcador, double[] offset) {
        // GUÍA: teoría 7 (el objeto virtual cuelga del marcador con un desplazamiento fijo).
        // 1. Si los arreglos son null o de distinta longitud -> null.
        // 2. Devuelve la suma componente a componente.
        // OJO: el test: {1,2,3}+{0,0,1}->{1,2,4}; longitudes distintas -> null.
        // CULTURA: la misma matemática 3D (vectores, matrices, proyección) reaparece en b45 (3D).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para anclarObjeto");
    }
}
