package com.masterclass.api.b41_anim;

/**
 * Ejercicio 322 · Detección de colisiones: AABB, círculos y respuesta.
 *
 * <p>Teoría: {@code teoria/41_Animacion_Juegos.md} (sección 4).
 *
 * <p>Un juego "siente" el mundo detectando cuándo dos objetos se tocan: la pelota y el ladrillo, la
 * nave y el láser. Las dos técnicas básicas son: <strong>AABB</strong> (Axis-Aligned Bounding Box,
 * "caja alineada con los ejes") — dos rectángulos colisionan si se solapan en X <em>y</em> en Y; y
 * <strong>círculos</strong> — colisionan si la distancia entre centros es menor que la suma de
 * radios. El truco de rendimiento: comparar el cuadrado de la distancia con el cuadrado de la suma
 * de radios para no llamar a {@code sqrt}. Todo son comparaciones numéricas: cero pantalla.
 */
public final class Ej322CollisionDetection {

    private Ej322CollisionDetection() {
    }

    /**
     * ¿Colisionan dos rectángulos AABB? Cada uno se define por su esquina superior-izquierda
     * (x, y) y su tamaño (w, h).
     *
     * @return {@code true} si se solapan; {@code false} sin implementar
     */
    public static boolean colisionanAABB(double ax, double ay, double aw, double ah,
                                         double bx, double by, double bw, double bh) {
        // TODO 1: comprueba el solape por la izquierda de A: ax < bx + bw.
        // TODO 2: y el solape por la derecha de A: ax + aw > bx.
        // TODO 3: comprueba el solape por arriba de A: ay < by + bh.
        // TODO 4: y el solape por abajo de A: ay + ah > by.
        // TODO 5: devuelve el AND de las cuatro condiciones (hay colisión solo si TODAS se cumplen).
        return false;
    }

    /**
     * ¿Colisionan dos círculos? Cada uno se define por su centro (x, y) y su radio r.
     *
     * @return {@code true} si se tocan o solapan; {@code false} sin implementar
     */
    public static boolean colisionanCirculos(double ax, double ay, double ar,
                                             double bx, double by, double br) {
        // TODO 6: calcula la diferencia de centros: dx = bx - ax, dy = by - ay.
        // TODO 7: calcula la distancia AL CUADRADO: distCuad = dx*dx + dy*dy.
        // TODO 8: calcula la suma de radios: sumaR = ar + br.
        // TODO 9: compara distCuad <= sumaR * sumaR (comparar cuadrados evita el costoso Math.sqrt).
        // TODO 10: devuelve esa comparación (el test: dos círculos de radio 5 con centros a distancia 6 -> true).
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Rect (0,0,10,10) vs (5,5,10,10): " + colisionanAABB(0, 0, 10, 10, 5, 5, 10, 10));
        System.out.println("Círculos r=5 a distancia 6: " + colisionanCirculos(0, 0, 5, 6, 0, 5));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: ¿Un punto (px,py) cae dentro de un rectángulo (rx,ry,rw,rh)?
     */
    public static boolean puntoEnRect(double px, double py, double rx, double ry, double rw, double rh) {
        // GUÍA: teoría 4.3 (el "click dentro de un botón" es exactamente esto).
        // 1. devuelve px >= rx && px <= rx + rw && py >= ry && py <= ry + rh.
        // OJO: el test usa (5,5, 0,0,10,10) -> true y (15,5, ...) -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para puntoEnRect");
    }

    /**
     * Reto Extra 2: ¿Un punto (px,py) cae dentro de un círculo de centro (cx,cy) y radio r?
     */
    public static boolean puntoEnCirculo(double px, double py, double cx, double cy, double r) {
        // GUÍA: teoría 4.4 (mismo truco de los cuadrados: sin sqrt).
        // 1. dx = px - cx, dy = py - cy.
        // 2. devuelve dx*dx + dy*dy <= r*r.
        // OJO: el test usa (3,4, 0,0, 5) -> true (justo en el borde) y (4,4, 0,0,5) -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para puntoEnCirculo");
    }

    /**
     * Reto Extra 3: Profundidad de solape en el eje X entre dos segmentos [ax, ax+aw] y [bx, bx+bw].
     * Devuelve 0 si no se solapan (no negativo).
     */
    public static double solapamientoX(double ax, double aw, double bx, double bw) {
        // GUÍA: teoría 4.6 (la "penetración" dice cuánto hay que separar dos objetos que chocaron).
        // 1. derechaMin = Math.min(ax + aw, bx + bw); izquierdaMax = Math.max(ax, bx).
        // 2. devuelve Math.max(0, derechaMin - izquierdaMax).
        // OJO: el test usa (0,10, 5,10) -> 5 (se solapan 5) y (0,10, 20,10) -> 0 (no se tocan).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para solapamientoX");
    }

    /**
     * Reto Extra 4: Centro X de un rectángulo (x + ancho/2). Útil para alinear y apuntar.
     */
    public static double centroX(double x, double w) {
        // GUÍA: teoría 4.2 (muchas decisiones usan el centro, no la esquina).
        // 1. devuelve x + w / 2.0.
        // OJO: el test usa (10, 20) -> 20.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para centroX");
    }

    /**
     * Reto Extra 5: Respuesta de rebote en 1 eje: si hubo choque, invierte la velocidad; si no, la mantiene.
     */
    public static double responderRebote(double vel, boolean choque) {
        // GUÍA: teoría 4.7 (la "respuesta" a una colisión más simple: invertir la velocidad).
        // 1. devuelve choque ? -vel : vel.
        // OJO: el test: (5, true) -> -5 y (5, false) -> 5.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para responderRebote");
    }

    /**
     * Reto Extra 6: Distancia (real) entre los centros de dos círculos.
     */
    public static double distanciaCentros(double ax, double ay, double bx, double by) {
        // GUÍA: teoría 4.4 (a veces sí necesitas la distancia real, no su cuadrado).
        // 1. devuelve Math.hypot(bx - ax, by - ay).
        // OJO: el test usa (0,0, 3,4) -> 5.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para distanciaCentros");
    }

    /**
     * Reto Extra 7: Colisión rectángulo–círculo. Se busca el punto del rectángulo más cercano al
     * centro del círculo (clamp) y se mira si está dentro del radio.
     */
    public static boolean colisionRectCirculo(double rx, double ry, double rw, double rh,
                                              double cx, double cy, double r) {
        // GUÍA: teoría 4.8 (la técnica del "punto más cercano": clamp del centro a la caja).
        // 1. cercaX = clamp de cx a [rx, rx+rw]: Math.max(rx, Math.min(cx, rx+rw)).
        // 2. cercaY = clamp de cy a [ry, ry+rh] (igual con la Y).
        // 3. dx = cx - cercaX, dy = cy - cercaY; devuelve dx*dx + dy*dy <= r*r.
        // OJO: el test: rect (0,0,10,10) y círculo en (13,5) r=5 -> true (a 3 del borde, dentro del radio);
        //   círculo en (20,5) r=5 -> false (a 10 del borde, fuera).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para colisionRectCirculo");
    }

    /**
     * Reto Extra 8: ¿Está el rectángulo A COMPLETAMENTE contenido dentro del rectángulo B?
     */
    public static boolean contenidoEn(double ax, double ay, double aw, double ah,
                                      double bx, double by, double bw, double bh) {
        // GUÍA: teoría 4.9 (distinto de colisionar: aquí A no puede asomar por ningún lado de B).
        // 1. devuelve ax >= bx && ay >= by && ax + aw <= bx + bw && ay + ah <= by + bh.
        // OJO: el test: A(2,2,4,4) dentro de B(0,0,10,10) -> true; A(8,8,4,4) -> false (se sale).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contenidoEn");
    }

    /**
     * Reto Extra 9: Lado por el que A choca con B (la menor penetración). Devuelve
     * "izquierda", "derecha", "arriba" o "abajo"; "" si no colisionan.
     */
    public static String ladoColision(double ax, double ay, double aw, double ah,
                                      double bx, double by, double bw, double bh) {
        // GUÍA: teoría 4.10 (para responder bien hay que saber por QUÉ lado entró: rebote correcto).
        // 1. si no colisionanAABB(...), devuelve "".
        // 2. calcula las 4 penetraciones: izq = (ax+aw)-bx; der = (bx+bw)-ax; arr = (ay+ah)-by; aba = (by+bh)-ay.
        // 3. el lado real es el de MENOR penetración (por donde menos se ha metido).
        // 4. min = Math.min(Math.min(izq,der), Math.min(arr,aba)); devuelve el nombre del que iguale a min.
        // OJO: el test mete A entrando por la izquierda de B y espera "izquierda".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ladoColision");
    }

    /**
     * Reto Extra 10: ¿El sprite (x, ancho w) ha chocado con una pared lateral de la pantalla
     * (borde izquierdo en 0 o derecho en {@code ancho})?
     */
    public static boolean chocaParedLateral(double x, double w, double ancho) {
        // GUÍA: teoría 4.7 (en Breakout/Pong, la pelota rebota en las paredes; esto lo detecta).
        // 1. devuelve x <= 0 || x + w >= ancho.
        // OJO: el test: (0, 10, 100) -> true (toca la izquierda); (45, 10, 100) -> false (en medio);
        //   (95, 10, 100) -> true (toca la derecha: 95+10=105 >= 100).
        // CULTURA: combinado con responderRebote (reto 5) tienes el rebote completo de una pelota. El
        //   mini-juego de Ej324 usa exactamente este patrón: detectar borde -> invertir velocidad.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para chocaParedLateral");
    }
}
