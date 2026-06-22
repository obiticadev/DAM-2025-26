package com.masterclass.api.b37_fxcustom;

import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 295 · {@code Canvas}/{@code GraphicsContext}: geometría pura de lo que dibujas.
 *
 * <p>Teoría: {@code teoria/37_JavaFX_Componentes_Canvas.md} (sección 3).
 *
 * <p>Un {@code Canvas} es un lienzo de mapa de bits: pides al {@code GraphicsContext} "traza una
 * línea de aquí a allá", "rellena este polígono", "gira 30 grados". Lo difícil no es la API de
 * pintado (dos métodos: {@code strokeXxx}/{@code fillXxx}), sino calcular <b>dónde</b> van los
 * puntos. Por eso el core es <b>geometría pura</b>: vértices de un polígono regular, rotación de
 * un punto, áreas, *bounding box*. Eso se prueba con JUnit sin abrir lienzo; el Playground luego
 * usa estos puntos para pintar de verdad. Un punto se representa como {@code double[]{x, y}}.
 *
 * <p>Sistema de coordenadas del Canvas: el origen {@code (0,0)} está ARRIBA-IZQUIERDA, la X crece
 * hacia la derecha y la <b>Y crece hacia ABAJO</b> (al revés que en matemáticas). Aquí trabajamos
 * la geometría en coordenadas matemáticas; la conversión Y la hace el Skin al pintar.
 */
public final class Ej295CanvasDrawing {

    private Ej295CanvasDrawing() {
    }

    /**
     * Calcula los vértices de un polígono regular de {@code lados} lados, centrado en
     * {@code (cx, cy)} y con radio {@code r}. El primer vértice está en el ángulo 0 (a la derecha
     * del centro) y los demás se reparten en sentido antihorario.
     *
     * @param cx    coordenada X del centro
     * @param cy    coordenada Y del centro
     * @param r     radio (distancia del centro a cada vértice)
     * @param lados número de lados (>= 3)
     * @return lista de puntos {@code [x, y]}, uno por vértice; {@code List.of()} sin implementar
     */
    public static List<double[]> verticesPoligonoRegular(double cx, double cy, double r, int lados) {
        // TODO 1: si lados < 3, devuelve List.of() (no es un polígono).
        // TODO 2: crea una List<double[]> (new ArrayList<>()).
        // TODO 3: recorre i de 0 a lados-1.
        // TODO 4: calcula el ángulo de cada vértice: angulo = 2 * Math.PI * i / lados.
        // TODO 5: x del vértice = cx + r * Math.cos(angulo).
        // TODO 6: y del vértice = cy + r * Math.sin(angulo).
        // TODO 7: añade el punto new double[]{x, y} a la lista.
        // TODO 8: devuelve la lista (el test comprueba el cuadrado n=4, r=1 centrado en (0,0)).
        return List.of();
    }

    /**
     * Rota un punto {@code (x, y)} alrededor del origen un ángulo dado en GRADOS (antihorario en
     * coordenadas matemáticas). Es el cálculo que hay detrás de {@code gc.rotate(...)}.
     *
     * @param x            coordenada X del punto
     * @param y            coordenada Y del punto
     * @param anguloGrados ángulo de rotación en grados
     * @return el punto rotado {@code [x', y']}; {@code null} sin implementar
     */
    public static double[] puntoTrasRotacion(double x, double y, double anguloGrados) {
        // TODO 9: convierte el ángulo a radianes con Math.toRadians(anguloGrados) y calcula cos y sin.
        //         x' = x*cos - y*sin ;  y' = x*sin + y*cos. Devuelve new double[]{xp, yp}.
        // TODO 10: el test prueba (1,0) rotado 90° -> (0,1) con delta; cuida los signos de la fórmula.
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Vértices triángulo: " + verticesPoligonoRegular(0, 0, 1, 3).size());
        double[] p = puntoTrasRotacion(1, 0, 90);
        System.out.println("(1,0) girado 90° -> " + (p == null ? "sin implementar" : "(" + p[0] + ", " + p[1] + ")"));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Centro de un rectángulo.
     * Dado origen (x,y) y tamaño (ancho,alto), devuelve el punto central.
     */
    public static double[] centroRectangulo(double x, double y, double ancho, double alto) {
        // GUÍA: teoría 3.1 (para centrar texto/figura necesitas el centro del rectángulo de destino).
        // 1. return new double[]{ x + ancho/2, y + alto/2 };
        // OJO: el test prueba (0,0,10,4) -> (5,2). Divide entre 2.0, no entre 2 entero (aunque aquí da igual).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para centroRectangulo");
    }

    /**
     * Reto Extra 2: Punto medio entre dos puntos.
     * El punto que está justo a mitad de camino del segmento p1-p2.
     */
    public static double[] puntoMedio(double[] p1, double[] p2) {
        // GUÍA: teoría 3.1 (interpolación lineal con t=0.5).
        // 1. return new double[]{ (p1[0]+p2[0])/2, (p1[1]+p2[1])/2 };
        // OJO: el test prueba (0,0) y (4,6) -> (2,3).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para puntoMedio");
    }

    /**
     * Reto Extra 3: Distancia entre dos puntos.
     * La longitud del segmento p1-p2 (teorema de Pitágoras).
     */
    public static double distancia(double[] p1, double[] p2) {
        // GUÍA: teoría 3.2 (Math.hypot evita el desbordamiento de elevar al cuadrado a mano).
        // 1. double dx = p2[0]-p1[0]; double dy = p2[1]-p1[1]; return Math.hypot(dx, dy);
        // PISTA: Math.hypot(dx, dy) == sqrt(dx*dx + dy*dy), pero más robusto.
        // OJO: el test prueba (0,0)-(3,4) -> 5.0 (el triángulo 3-4-5 de toda la vida).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para distancia");
    }

    /**
     * Reto Extra 4: Escalar un punto respecto al origen.
     * Multiplica ambas coordenadas por un factor (zoom desde el (0,0)).
     */
    public static double[] escalar(double[] p, double factor) {
        // GUÍA: teoría 3.2 (gc.scale(factor, factor) hace esto a todo lo que pintes después).
        // 1. return new double[]{ p[0]*factor, p[1]*factor };
        // OJO: el test prueba (2,3) escalado x2 -> (4,6).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escalar");
    }

    /**
     * Reto Extra 5: Trasladar un punto.
     * Suma un desplazamiento (dx,dy) al punto (mover el origen del pincel).
     */
    public static double[] trasladar(double[] p, double dx, double dy) {
        // GUÍA: teoría 3.3 (gc.translate(dx, dy) mueve el origen de coordenadas del lienzo).
        // 1. return new double[]{ p[0]+dx, p[1]+dy };
        // OJO: el test prueba (1,1) + (5,-2) -> (6,-1). Atención al signo negativo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para trasladar");
    }

    /**
     * Reto Extra 6: Punto en una circunferencia.
     * Devuelve el punto del borde de un círculo (cx,cy,r) en el ángulo dado (grados).
     */
    public static double[] puntoEnCircunferencia(double cx, double cy, double r, double anguloGrados) {
        // GUÍA: teoría 3.3 (la base para repartir cosas en círculo: relojes, ruletas, el reto 10).
        // 1. double a = Math.toRadians(anguloGrados);
        // 2. return new double[]{ cx + r*Math.cos(a), cy + r*Math.sin(a) };
        // PISTA: ángulo 0 -> (cx+r, cy); ángulo 90 -> (cx, cy+r).
        // OJO: el test prueba centro (0,0), r=2, ángulo 0 -> (2,0) con delta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para puntoEnCircunferencia");
    }

    /**
     * Reto Extra 7: Caja contenedora (bounding box).
     * Devuelve [minX, minY, maxX, maxY] que encierra a todos los puntos.
     */
    public static double[] cajaContenedora(List<double[]> puntos) {
        // GUÍA: teoría 3.4 (la bounding box delimita lo que repintar y sirve para hit testing rápido, b296).
        // 1. Si la lista está vacía, devuelve new double[]{0,0,0,0}.
        // 2. Inicializa minX=maxX=puntos.get(0)[0] y minY=maxY=puntos.get(0)[1].
        // 3. Recorre todos los puntos actualizando min/max con Math.min/Math.max.
        // 4. return new double[]{minX, minY, maxX, maxY}.
        // OJO: el test usa puntos (1,2),(5,0),(3,7) -> [1,0,5,7]. No mezcles las X con las Y.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cajaContenedora");
    }

    /**
     * Reto Extra 8: Trasladar un polígono entero.
     * Aplica un desplazamiento (dx,dy) a TODOS los vértices de un polígono.
     */
    public static List<double[]> trasladarPoligono(List<double[]> poligono, double dx, double dy) {
        // GUÍA: teoría 3.4 (mover una figura = trasladar todos sus puntos; lo que hace gc.translate).
        // 1. Crea una lista nueva. 2. Por cada vértice, añade trasladar(vertice, dx, dy) (reto 5).
        // 3. Devuelve la lista nueva (NO modifiques la original).
        // OJO: el test comprueba que el primer vértice se movió y que la lista original NO cambió.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para trasladarPoligono");
    }

    /**
     * Reto Extra 9: Área de un polígono (fórmula del cordón / shoelace).
     * Calcula el área que encierra un polígono dado por sus vértices en orden.
     */
    public static double areaPoligono(List<double[]> vertices) {
        // GUÍA: teoría 3.5 (fórmula de Gauss/shoelace: suma cruzada de coordenadas consecutivas).
        // 1. Si hay menos de 3 vértices, devuelve 0.
        // 2. suma = 0; para cada i, j = (i+1) % n; suma += x[i]*y[j] - x[j]*y[i].
        // 3. return Math.abs(suma) / 2.0.
        // PISTA: el "% n" hace que el último vértice se cierre con el primero.
        // OJO: el test usa un cuadrado de lado 2 (0,0),(2,0),(2,2),(0,2) -> área 4.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para areaPoligono");
    }

    /**
     * Reto Extra 10: Vértices de una estrella.
     * Una estrella alterna vértices en radio exterior y radio interior. Devuelve sus puntas y valles.
     */
    public static List<double[]> verticesEstrella(double cx, double cy, double rExterior, double rInterior, int puntas) {
        // GUÍA: teoría 3.5 (una estrella = polígono con DOS radios alternados; base de iconos y charts).
        // 1. Crea una lista. El número total de vértices es 2*puntas.
        // 2. Recorre k de 0 a 2*puntas-1. El radio es rExterior si k es PAR, rInterior si es IMPAR.
        // 3. El ángulo de cada vértice es 2*PI*k / (2*puntas).
        // 4. Añade new double[]{ cx + radio*cos(ang), cy + radio*sin(ang) }.
        // PISTA: alterna el radio con (k % 2 == 0) ? rExterior : rInterior.
        // OJO: el test comprueba que una estrella de 5 puntas tiene 10 vértices y que el primero
        //   (k=0, ángulo 0, radio exterior) es (cx + rExterior, cy).
        // CULTURA: la misma idea (repartir N elementos en círculo) sostiene el PieChart de Ej297.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para verticesEstrella");
    }

    /** Helper de demostración: arma una lista mutable de puntos. */
    static List<double[]> puntos(double[]... ps) {
        return new ArrayList<>(List.of(ps));
    }
}
