package com.masterclass.api.b45_juego3d;

import java.util.Arrays;

/**
 * Ejercicio 345 · Matemática de vectores 3D: suma, escala, producto escalar y vectorial, normalizar.
 *
 * <p>Un vector 3D {@code {x, y, z}} es la pieza atómica de todo motor 3D: una posición, una
 * dirección, una velocidad o una normal de superficie son vectores. Antes de mover una nave o
 * iluminar una cara hay que dominar cinco operaciones: <strong>sumar</strong> (combinar
 * desplazamientos), <strong>escalar</strong> (estirar), el <strong>producto escalar</strong> (mide
 * cuánto se "parecen" dos direcciones → ángulos e iluminación), el <strong>producto vectorial</strong>
 * (da un vector perpendicular a los otros dos → normales y orientación) y <strong>normalizar</strong>
 * (llevar a longitud 1 sin cambiar la dirección). Todo es aritmética pura y, por tanto, testeable.
 *
 * <p>Teoría: {@code teoria/45_Juegos3D_Motores.md} (sección 2).
 */
public final class Ej345Vector3DMath {

    private Ej345Vector3DMath() {
    }

    /**
     * Producto vectorial (cruz) de dos vectores 3D: devuelve un vector perpendicular a ambos.
     *
     * @param u primer vector (longitud 3)
     * @param v segundo vector (longitud 3)
     * @return el vector perpendicular {@code u × v}; {@code null} si algún vector no mide 3
     */
    public static double[] productoVectorial(double[] u, double[] v) {
        // TODO 1: si u o v son null, o su longitud != 3 -> devuelve null.
        // TODO 2: componente x = u[1]*v[2] - u[2]*v[1].
        // TODO 3: componente y = u[2]*v[0] - u[0]*v[2]  (¡ojo al orden, esta va "cruzada"!).
        // TODO 4: componente z = u[0]*v[1] - u[1]*v[0]; devuelve new double[]{x, y, z}.
        return null;
    }

    /**
     * Normaliza un vector: lo lleva a longitud 1 conservando su dirección (vector unitario).
     *
     * @param v vector a normalizar (longitud 3)
     * @return el vector unitario; el vector cero {@code {0,0,0}} si su magnitud es 0; {@code null}
     * si {@code v} no mide 3
     */
    public static double[] normalizar(double[] v) {
        // TODO 5: si v es null o no mide 3 -> null.
        // TODO 6: calcula la magnitud m = sqrt(x² + y² + z²).
        // TODO 7: si m == 0 -> devuelve new double[]{0, 0, 0} (no se puede dividir entre cero).
        return null;
    }

    /**
     * Distancia euclídea entre dos puntos 3D.
     *
     * @param a primer punto (longitud 3)
     * @param b segundo punto (longitud 3)
     * @return la distancia; {@code -1} si algún punto no mide 3
     */
    public static double distancia(double[] a, double[] b) {
        // TODO 8: si a o b son null, o no miden 3 -> devuelve -1.
        // TODO 9: calcula las diferencias dx, dy, dz.
        // TODO 10: devuelve sqrt(dx² + dy² + dz²).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("u × v: " + Arrays.toString(productoVectorial(new double[]{1, 0, 0}, new double[]{0, 1, 0})));
        System.out.println("normalizar(3,0,0): " + Arrays.toString(normalizar(new double[]{3, 0, 0})));
        System.out.println("distancia: " + distancia(new double[]{0, 0, 0}, new double[]{1, 2, 2}));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Producto escalar (dot product).
     * Suma de los productos componente a componente; mide cuánto apuntan al mismo sitio.
     */
    public static double productoEscalar(double[] u, double[] v) {
        // GUÍA: teoría 2 (el dot es la operación más usada en 3D: ángulos, iluminación, proyección).
        // 1. Si u o v son null, o no miden 3 -> -1 (centinela; ojo: el dot real puede ser negativo).
        // 2. Devuelve u[0]*v[0] + u[1]*v[1] + u[2]*v[2].
        // OJO: el test usa vectores válidos; {1,2,3}·{4,5,6} = 4+10+18 = 32.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para productoEscalar");
    }

    /**
     * Reto Extra 2: Magnitud (longitud) de un vector.
     * La raíz de la suma de cuadrados; es la distancia del origen al punto.
     */
    public static double magnitud(double[] v) {
        // GUÍA: teoría 2 (magnitud = √(x²+y²+z²); normalizar es dividir por ella).
        // 1. Si v es null o no mide 3 -> -1.
        // 2. Devuelve Math.sqrt(x² + y² + z²).
        // OJO: el test: {3,4,0} -> 5 (terna pitagórica clásica en el plano).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para magnitud");
    }

    /**
     * Reto Extra 3: Suma de dos vectores.
     * Combina dos desplazamientos componente a componente.
     */
    public static double[] sumar(double[] u, double[] v) {
        // GUÍA: teoría 2 (sumar vectores = encadenar movimientos; pos_nueva = pos + velocidad·dt).
        // 1. Si u o v son null o no miden 3 -> null.
        // 2. Devuelve {u0+v0, u1+v1, u2+v2}.
        // OJO: el test: {1,2,3}+{4,5,6} = {5,7,9}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sumar");
    }

    /**
     * Reto Extra 4: Escalar un vector por un número.
     * Estira o encoge el vector sin cambiar su dirección (si el factor es positivo).
     */
    public static double[] escalar(double[] v, double k) {
        // GUÍA: teoría 2 (escalar la velocidad cambia la rapidez; ·dt es escalar por el tiempo).
        // 1. Si v es null o no mide 3 -> null.
        // 2. Devuelve {v0*k, v1*k, v2*k}.
        // OJO: el test: {1,2,3}·2 = {2,4,6}; k=0 -> {0,0,0}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escalar");
    }

    /**
     * Reto Extra 5: Ángulo entre dos vectores, en grados.
     * Sale del producto escalar: cos θ = (u·v) / (|u|·|v|).
     */
    public static double anguloEntre(double[] u, double[] v) {
        // GUÍA: teoría 2 (el ángulo es acos del dot normalizado; clave para "¿el enemigo me ve?").
        // 1. Si u o v no miden 3, o alguna magnitud es 0 -> -1.
        // 2. cos = productoEscalar / (magnitud(u) * magnitud(v)).
        // 3. RECORTA cos a [-1, 1] antes de acos (errores de redondeo lo sacan del rango -> NaN).
        // 4. Devuelve Math.toDegrees(Math.acos(cos)).
        // OJO: el test: {1,0,0} y {0,1,0} -> 90 (perpendiculares); mismos vectores -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para anguloEntre");
    }

    /**
     * Reto Extra 6: Proyección de u sobre v.
     * La "sombra" de u en la dirección de v: (u·v / v·v) · v.
     */
    public static double[] proyeccion(double[] u, double[] v) {
        // GUÍA: teoría 2 (proyectar descompone un movimiento en "a favor" y "perpendicular" a v).
        // 1. Si u o v no miden 3, o v·v == 0 -> null.
        // 2. factor = (u·v) / (v·v).
        // 3. Devuelve escalar(v, factor).
        // OJO: el test: proyectar {2,2,0} sobre {1,0,0} -> {2,0,0} (la parte "horizontal" de u).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para proyeccion");
    }

    /**
     * Reto Extra 7: Reflejar una dirección sobre una normal (rebote).
     * d_reflejado = d - 2·(d·n)·n, con n normalizada.
     */
    public static double[] reflejar(double[] d, double[] n) {
        // GUÍA: teoría 2 (el rebote de una pelota en una pared 3D; n es la normal de la superficie).
        // 1. Si d o n no miden 3 -> null.
        // 2. dot = d·n ; resta a d el vector escalar(n, 2*dot).
        // PISTA: resultado[i] = d[i] - 2*dot*n[i].
        // OJO: el test: d={1,-1,0}, n={0,1,0} -> {1,1,0} (invierte la componente vertical).
        // CULTURA: en b41 (2D) el rebote invertía vx o vy; en 3D se generaliza con la normal.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para reflejar");
    }

    /**
     * Reto Extra 8: Interpolación lineal (lerp) entre dos puntos.
     * a + (b - a)·t, con t recortado a [0, 1].
     */
    public static double[] lerp(double[] a, double[] b, double t) {
        // GUÍA: teoría 2 (lerp mueve suavemente la cámara o un objeto de A a B; t=0->A, t=1->B).
        // 1. Si a o b no miden 3 -> null.
        // 2. RECORTA t a [0, 1] (t<0 -> 0; t>1 -> 1).
        // 3. resultado[i] = a[i] + (b[i] - a[i]) * t.
        // OJO: el test: lerp {0,0,0}->{10,10,10} con t=0.5 -> {5,5,5}; t=2 (recortado) -> {10,10,10}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para lerp");
    }

    /**
     * Reto Extra 9: Triple producto escalar u · (v × w).
     * Es el volumen (con signo) del paralelepípedo formado por los tres vectores.
     */
    public static double tripleProducto(double[] u, double[] v, double[] w) {
        // GUÍA: teoría 2 (mide volumen; si es 0, los tres vectores son coplanares -> reto 10).
        // 1. Si alguno no mide 3 -> -1.
        // 2. Calcula cruz = productoVectorial(v, w) y devuelve productoEscalar(u, cruz).
        // OJO: el test: la base canónica {1,0,0},{0,1,0},{0,0,1} -> 1 (cubo unidad).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tripleProducto");
    }

    /**
     * Reto Extra 10: ¿Son coplanares tres vectores?
     * Lo son cuando su triple producto es (casi) cero.
     */
    public static boolean sonCoplanares(double[] u, double[] v, double[] w) {
        // GUÍA: teoría 2 (coplanaridad = volumen nulo; útil para detectar caras degeneradas de una malla).
        // 1. Calcula el triple producto.
        // 2. Devuelve Math.abs(triple) < 1e-9 (tolerancia para errores de coma flotante).
        // OJO: el test: tres vectores en el plano z=0 -> true; la base canónica -> false (volumen 1).
        // CULTURA: la misma matemática de proyección/normales reaparece en b44 (Ej343, marcadores AR).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sonCoplanares");
    }
}
