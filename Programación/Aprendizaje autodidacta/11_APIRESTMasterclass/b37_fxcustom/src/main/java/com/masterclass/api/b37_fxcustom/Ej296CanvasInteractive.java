package com.masterclass.api.b37_fxcustom;

import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 296 · Canvas interactivo: <i>hit testing</i> (¿el click cae dentro de la figura?).
 *
 * <p>Teoría: {@code teoria/37_JavaFX_Componentes_Canvas.md} (sección 4).
 *
 * <p>El Canvas pinta píxeles, no objetos: cuando el usuario hace click, JavaFX te da las
 * coordenadas {@code (x, y)} y TÚ debes averiguar qué figura ha tocado. Eso es el <b>hit testing</b>:
 * geometría de "¿el punto está dentro de este rectángulo / círculo / polígono?". Es la base de
 * dibujar arrastrando el ratón, seleccionar figuras y los juegos en Canvas (b41). El core es
 * geometría booleana pura, testeable sin lienzo ni eventos reales. Un rectángulo es
 * {@code (x, y, ancho, alto)}; un círculo es {@code (cx, cy, r)}.
 */
public final class Ej296CanvasInteractive {

    private Ej296CanvasInteractive() {
    }

    /**
     * ¿El punto {@code (px, py)} cae dentro de un rectángulo {@code (rx, ry, ancho, alto)}? Los
     * bordes cuentan como "dentro" (inclusive).
     *
     * @return {@code true} si el punto está dentro o en el borde; {@code false} sin implementar
     */
    public static boolean dentroDeRectangulo(double px, double py,
                                             double rx, double ry, double ancho, double alto) {
        // TODO 1: el punto está dentro en X si px >= rx Y px <= rx + ancho.
        // TODO 2: el punto está dentro en Y si py >= ry Y py <= ry + alto.
        // TODO 3: devuelve true solo si se cumplen AMBAS condiciones (X e Y).
        // TODO 4: usa <= y >= (inclusive): un click justo en el borde cuenta como acierto.
        // TODO 5: el test prueba (5,5) en rect (0,0,10,10) -> true; (15,5) -> false (fuera en X).
        return false;
    }

    /**
     * ¿El punto {@code (px, py)} cae dentro de un círculo de centro {@code (cx, cy)} y radio
     * {@code r}? Un punto está dentro si su distancia al centro es menor o igual que el radio.
     *
     * @return {@code true} si el punto está dentro o en el borde; {@code false} sin implementar
     */
    public static boolean dentroDeCirculo(double px, double py, double cx, double cy, double r) {
        // TODO 6: calcula dx = px - cx y dy = py - cy.
        // TODO 7: calcula la distancia al cuadrado: distSq = dx*dx + dy*dy.
        // TODO 8: compara distSq <= r*r (comparar al cuadrado evita la raíz, más rápido y exacto).
        // TODO 9: devuelve ese booleano.
        // TODO 10: el test prueba (3,4) en círculo (0,0,5) -> true (dist=5=r, frontera); (4,4) en
        //          círculo (0,0,5) -> true; (5,5) en (0,0,5) -> false (dist≈7.07 > 5).
        return false;
    }

    public static void main(String[] args) {
        System.out.println("(5,5) en rect(0,0,10,10)  -> " + dentroDeRectangulo(5, 5, 0, 0, 10, 10));
        System.out.println("(15,5) en rect(0,0,10,10) -> " + dentroDeRectangulo(15, 5, 0, 0, 10, 10));
        System.out.println("(3,4) en circ(0,0,5)      -> " + dentroDeCirculo(3, 4, 0, 0, 5));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: ¿Dentro de un rango en X?
     * La mitad horizontal del hit testing: ¿px está entre min y max?
     */
    public static boolean dentroDeRangoX(double px, double min, double max) {
        // GUÍA: teoría 4.1 (un rango 1D es el ladrillo del hit testing 2D).
        // 1. return px >= min && px <= max;
        // OJO: el test prueba px=5 en [0,10] -> true; px=10 -> true (borde inclusive).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dentroDeRangoX");
    }

    /**
     * Reto Extra 2: Distancia al cuadrado.
     * La distancia entre dos puntos SIN la raíz (para comparar distancias rápido).
     */
    public static double distanciaAlCuadrado(double x1, double y1, double x2, double y2) {
        // GUÍA: teoría 4.1 (en hit testing se compara al cuadrado para ahorrarse el sqrt en cada frame).
        // 1. double dx = x2-x1, dy = y2-y1; return dx*dx + dy*dy;
        // OJO: el test prueba (0,0)-(3,4) -> 25.0 (NO 5.0; es la distancia AL CUADRADO).
        // CULTURA: en un juego (b41) con cientos de colisiones por frame, evitar sqrt es oro.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para distanciaAlCuadrado");
    }

    /**
     * Reto Extra 3: ¿El punto está sobre un segmento horizontal, con tolerancia?
     * Una línea no tiene grosor, así que se acepta el click si cae a menos de 'tol' de la línea.
     */
    public static boolean sobreLineaHorizontal(double px, double py, double xIni, double xFin, double yLinea, double tol) {
        // GUÍA: teoría 4.2 (las líneas se "engordan" con una tolerancia para poder clicarlas).
        // 1. ¿px está entre xIni y xFin? (usa dentroDeRangoX, reto 1).
        // 2. ¿|py - yLinea| <= tol? (Math.abs de la diferencia vertical).
        // 3. devuelve true solo si AMBAS.
        // PISTA: Math.abs(py - yLinea) <= tol.
        // OJO: el test prueba (5, 10.5) sobre línea y=10 de x=0..10 con tol=1 -> true (0.5 <= 1).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sobreLineaHorizontal");
    }

    /**
     * Reto Extra 4: Ajustar a la rejilla (snap to grid).
     * Redondea una coordenada al múltiplo de 'rejilla' más cercano (para que las figuras encajen).
     */
    public static double ajustarARejilla(double valor, double rejilla) {
        // GUÍA: teoría 4.2 (el "imán" de las herramientas de dibujo: las cosas se pegan a la rejilla).
        // 1. Si rejilla <= 0, devuelve el valor tal cual (evita dividir por cero).
        // 2. return Math.round(valor / rejilla) * rejilla;
        // PISTA: valor=23, rejilla=10 -> round(2.3)=2 -> 20.0.
        // OJO: el test prueba 23 con rejilla 10 -> 20.0; 27 -> 30.0 (round, no truncar).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ajustarARejilla");
    }

    /**
     * Reto Extra 5: Acotar el arrastre dentro del lienzo.
     * Al arrastrar una figura, su posición no debe salirse de [0, limite] (clamp por eje).
     */
    public static double clampArrastre(double pos, double limite) {
        // GUÍA: teoría 4.3 (mientras arrastras, se acota la posición para no perder la figura fuera).
        // 1. return Math.max(0, Math.min(pos, limite));
        // OJO: el test prueba pos=-5, limite=100 -> 0; pos=150 -> 100; pos=50 -> 50.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clampArrastre");
    }

    /**
     * Reto Extra 6: ¿Solapan dos rectángulos (colisión AABB)?
     * Dos cajas alineadas con los ejes chocan si se solapan en X Y en Y a la vez.
     */
    public static boolean colisionAABB(double ax, double ay, double aw, double ah,
                                       double bx, double by, double bw, double bh) {
        // GUÍA: teoría 4.3 (AABB = Axis-Aligned Bounding Box; la colisión más usada en juegos 2D).
        // 1. Solapan en X si ax < bx+bw Y bx < ax+aw.
        // 2. Solapan en Y si ay < by+bh Y by < ay+ah.
        // 3. return (solapanX && solapanY).
        // PISTA: es más fácil pensar "NO chocan si A está totalmente a la izquierda/derecha/arriba/abajo de B".
        // OJO: el test prueba A(0,0,10,10) vs B(5,5,10,10) -> true; A vs B(20,20,5,5) -> false.
        // CULTURA: esta es LA función de colisión de un juego en Canvas (b41).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para colisionAABB");
    }

    /**
     * Reto Extra 7: ¿Dentro de un polígono? (ray casting).
     * Algoritmo del rayo: lanza un rayo horizontal y cuenta cuántos lados cruza (impar = dentro).
     */
    public static boolean dentroDePoligono(List<double[]> vertices, double px, double py) {
        // GUÍA: teoría 4.4 (ray casting: par de cruces = fuera, impar = dentro).
        // 1. boolean dentro = false; n = vertices.size().
        // 2. Recorre cada lado (i, j) con j = el vértice anterior (j empieza en n-1).
        // 3. Si ((yi > py) != (yj > py)) y px < (xj-xi)*(py-yi)/(yj-yi) + xi, invierte 'dentro'.
        // 4. Avanza j = i. Devuelve 'dentro'.
        // PISTA: la condición (yi>py)!=(yj>py) detecta que el lado cruza la altura py.
        // OJO: el test usa un cuadrado (0,0),(4,0),(4,4),(0,4): (2,2) -> true; (5,5) -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dentroDePoligono");
    }

    /**
     * Reto Extra 8: Figura tocada (respetando el z-order).
     * Dada una lista de rectángulos pintados en orden, devuelve el índice del que está ARRIBA en
     * el click (el último pintado tapa a los anteriores).
     */
    public static int indiceFiguraTocada(List<double[]> rectangulos, double px, double py) {
        // GUÍA: teoría 4.5 (z-order: lo pintado DESPUÉS está ENCIMA; recorre al revés para el de arriba).
        // 1. Recorre la lista DESDE EL FINAL (i de size-1 a 0).
        // 2. Cada rect es double[]{x, y, ancho, alto}: si dentroDeRectangulo(px,py,...) devuelve true,
        //    return i (es el de más arriba que casa).
        // 3. Si ninguno casa, return -1.
        // PISTA: recorrer al revés es la clave: el primer acierto desde el final es el visible.
        // OJO: el test apila dos rects que se solapan; el click debe devolver el ÚLTIMO (índice mayor).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para indiceFiguraTocada");
    }

    /**
     * Reto Extra 9: Convertir coordenada de escena a coordenada del Canvas.
     * El Canvas puede estar desplazado dentro de la ventana; resta su origen para tener la local.
     */
    public static double[] aCoordenadaCanvas(double escenaX, double escenaY, double canvasX, double canvasY) {
        // GUÍA: teoría 4.5 (un MouseEvent da coords de ESCENA; el Canvas necesita las suyas LOCALES).
        // 1. return new double[]{ escenaX - canvasX, escenaY - canvasY };
        // PISTA: si el Canvas empieza en (50,30) y clicas en escena (60,40), en el Canvas es (10,10).
        // OJO: el test prueba escena (60,40) con canvas en (50,30) -> (10,10).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aCoordenadaCanvas");
    }

    /**
     * Reto Extra 10: Selección por rectángulo (lazo).
     * Dado un rectángulo de selección y una lista de puntos, devuelve los índices que caen dentro.
     */
    public static List<Integer> seleccionEnRectangulo(List<double[]> puntos,
                                                      double rx, double ry, double ancho, double alto) {
        // GUÍA: teoría 4.6 (arrastrar un rectángulo para seleccionar varios elementos a la vez).
        // 1. Crea una List<Integer>.
        // 2. Recorre los puntos por índice; si dentroDeRectangulo(p[0],p[1],rx,ry,ancho,alto), añade i.
        // 3. Devuelve la lista (en orden de índice).
        // OJO: el caso límite del test es un rectángulo que no contiene a nadie -> List.of().
        // CULTURA: es el "lazo" de cualquier editor (selecciona iconos del escritorio arrastrando).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para seleccionEnRectangulo");
    }

    /** Helper de demostración: arma una lista mutable de rectángulos/puntos. */
    static List<double[]> figuras(double[]... fs) {
        return new ArrayList<>(List.of(fs));
    }
}
