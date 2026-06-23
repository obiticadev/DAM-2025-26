package com.masterclass.api.b41_anim;

/**
 * Ejercicio 321 · Sprites: posición, velocidad y límites de pantalla.
 *
 * <p>Teoría: {@code teoria/41_Animacion_Juegos.md} (sección 3).
 *
 * <p>Un <em>sprite</em> es un objeto del juego (la nave, la pelota, el enemigo) con una POSICIÓN y
 * una VELOCIDAD. La física básica de un juego es una sola fórmula: {@code posición_nueva = posición
 * + velocidad · dt} (la integración de Euler que viste en mates como espacio = velocidad · tiempo).
 * Multiplicar por el {@code dt} de Ej320 hace que el movimiento sea independiente de los fps. Luego
 * hay que mantener al sprite DENTRO de la pantalla: recortarlo a los bordes o hacerlo rebotar. Todo
 * es aritmética sobre números: no hace falta dibujar nada para probarlo.
 */
public final class Ej321SpriteAndMovement {

    private Ej321SpriteAndMovement() {
    }

    /**
     * Nueva posición de un sprite tras avanzar {@code dt} segundos a velocidad {@code vel}.
     *
     * @param pos posición actual (en una dimensión: x o y)
     * @param vel velocidad en unidades por segundo (puede ser negativa: hacia atrás)
     * @param dt  segundos transcurridos
     * @return la posición resultante; {@code -1} sin implementar
     */
    public static double nuevaPosicion(double pos, double vel, double dt) {
        // TODO 1: si dt < 0 (no se viaja al pasado) trátalo como 0 para no mover el sprite.
        // TODO 2: calcula el desplazamiento de este frame: desplazamiento = vel * dt.
        // TODO 3: súmalo a la posición actual: resultado = pos + desplazamiento.
        // TODO 4: (no toques el signo) una velocidad negativa mueve a la izquierda/arriba: ya sale solo.
        // TODO 5: devuelve resultado (el test: nuevaPosicion(10, 5, 2) == 20).
        return -1;
    }

    /**
     * Recorta (clamp) una posición para que no salga de la pantalla.
     *
     * @param pos posición a recortar
     * @param min borde mínimo (p.ej. 0)
     * @param max borde máximo (p.ej. ancho - anchoSprite)
     * @return la posición recortada al rango [min, max]; {@code -1} sin implementar
     */
    public static double limitarAPantalla(double pos, double min, double max) {
        // TODO 6: si pos < min, el sprite se sale por la izquierda/arriba: devuelve min.
        // TODO 7: si pos > max, se sale por la derecha/abajo: devuelve max.
        // TODO 8: en otro caso está dentro: devuelve pos tal cual.
        return -1;
    }

    /**
     * Calcula la velocidad tras un posible rebote en un borde. Si el sprite toca o cruza un borde,
     * invierte la velocidad; si no, la mantiene.
     *
     * @param vel velocidad actual
     * @param pos posición actual
     * @param min borde mínimo
     * @param max borde máximo
     * @return la velocidad tras el rebote; {@code -1} sin implementar
     */
    public static double reboteEnBorde(double vel, double pos, double min, double max) {
        // TODO 9: si pos <= min o pos >= max, ha chocado con un borde: devuelve -vel (rebota).
        // TODO 10: en otro caso sigue libre: devuelve vel sin tocar.
        //   (el test: pos en el borde -> invierte el signo; pos en el centro -> igual.)
        return -1;
    }

    public static void main(String[] args) {
        double x = 10;
        double vel = 5;
        x = nuevaPosicion(x, vel, 2);   // avanza 2 s
        System.out.println("Nueva x: " + x);
        System.out.println("Recortada a [0,15]: " + limitarAPantalla(x, 0, 15));
        System.out.println("Velocidad tras tocar el borde 20: " + reboteEnBorde(vel, 20, 0, 20));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Módulo (longitud) de un vector velocidad (vx, vy). Es su "rapidez".
     */
    public static double magnitudVelocidad(double vx, double vy) {
        // GUÍA: teoría 3.4 (un vector tiene dirección Y módulo; el módulo es el teorema de Pitágoras).
        // 1. devuelve Math.hypot(vx, vy)  (equivale a Math.sqrt(vx*vx + vy*vy) pero sin desbordar).
        // OJO: el test usa (3, 4) -> 5.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para magnitudVelocidad");
    }

    /**
     * Reto Extra 2: Velocidad de una diagonal para que NO sea más rápida que en recto.
     * Moverse en diagonal a (v, v) da rapidez v·√2; para igualarla, cada eje vale v/√2.
     */
    public static double velocidadDiagonal(double v) {
        // GUÍA: teoría 3.5 (bug clásico: en diagonal el personaje corre un 41% más rápido).
        // 1. devuelve v / Math.sqrt(2).
        // OJO: el test usa 1.0 -> 0.7071 aprox. (compara con tolerancia). Multiplicado por √2 da 1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para velocidadDiagonal");
    }

    /**
     * Reto Extra 3: Mover una posición hacia un objetivo un paso, SIN pasarse del objetivo.
     */
    public static double moverHaciaObjetivo(double pos, double objetivo, double paso) {
        // GUÍA: teoría 3.6 (perseguir un punto: te acercas 'paso' pero clavas el objetivo si lo alcanzas).
        // 1. distancia = objetivo - pos.
        // 2. si Math.abs(distancia) <= paso devuelve objetivo (ya casi estás: clava).
        // 3. si no, avanza 'paso' en el signo de la distancia: pos + Math.signum(distancia) * paso.
        // OJO: el test usa (0, 10, 3) -> 3 y (0, 2, 3) -> 2 (no se pasa de 2).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para moverHaciaObjetivo");
    }

    /**
     * Reto Extra 4: Distancia euclídea entre dos puntos (x1,y1) y (x2,y2).
     */
    public static double distancia(double x1, double y1, double x2, double y2) {
        // GUÍA: teoría 3.4 (la distancia entre dos sprites; útil para IA y colisiones por radio).
        // 1. devuelve Math.hypot(x2 - x1, y2 - y1).
        // OJO: el test usa (0,0, 3,4) -> 5.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para distancia");
    }

    /**
     * Reto Extra 5: ¿El sprite (su esquina x,y, tamaño ancho×alto) está DENTRO de la pantalla?
     */
    public static boolean dentroDePantalla(double x, double y, double w, double h, double pantallaAncho, double pantallaAlto) {
        // GUÍA: teoría 3.7 (un sprite cabe si su caja no se sale por ningún borde).
        // 1. devuelve x >= 0 && y >= 0 && x + w <= pantallaAncho && y + h <= pantallaAlto.
        // OJO: el test: (0,0,10,10, 100,100) -> true; (95,0,10,10, 100,100) -> false (se sale por la derecha).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dentroDePantalla");
    }

    /**
     * Reto Extra 6: Envolver la posición (wrap-around estilo Asteroids): al salir por un lado,
     * reaparece por el contrario. Devuelve pos dentro de [0, ancho).
     */
    public static double envolver(double pos, double ancho) {
        // GUÍA: teoría 3.8 (el "Pac-Man tunnel": salir por la derecha = entrar por la izquierda).
        // 1. si ancho <= 0 devuelve 0.
        // 2. devuelve ((pos % ancho) + ancho) % ancho  (el doble módulo arregla los negativos).
        // OJO: el test usa (105, 100) -> 5 y (-5, 100) -> 95 (un módulo simple daría -5: mal).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para envolver");
    }

    /**
     * Reto Extra 7: Aplicar gravedad a la velocidad vertical: vy + g·dt.
     */
    public static double aplicarGravedad(double vy, double g, double dt) {
        // GUÍA: teoría 3.9 (la gravedad es una aceleración: cada frame suma g·dt a la velocidad).
        // 1. devuelve vy + g * dt.
        // OJO: el test usa (0, 9.8, 1) -> 9.8 (tras 1 s cayendo, la velocidad es 9.8).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aplicarGravedad");
    }

    /**
     * Reto Extra 8: Aplicar rozamiento/fricción: multiplica la velocidad por un factor &lt; 1.
     */
    public static double aplicarFriccion(double vel, double factor) {
        // GUÍA: teoría 3.9 (sin fricción un sprite no para nunca; con factor 0.9 pierde 10% por frame).
        // 1. devuelve vel * factor.
        // OJO: el test usa (10, 0.9) -> 9.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aplicarFriccion");
    }

    /**
     * Reto Extra 9: Limitar la rapidez (velocidad terminal): si el módulo de (vx,vy) supera maxVel,
     * escala el vector para que valga exactamente maxVel; conserva la dirección.
     */
    public static double[] limitarRapidez(double vx, double vy, double maxVel) {
        // GUÍA: teoría 3.10 (sin tope, sumar gravedad indefinidamente da velocidades absurdas).
        // 1. rapidez = Math.hypot(vx, vy).
        // 2. si rapidez <= maxVel o rapidez == 0, devuelve {vx, vy} sin tocar.
        // 3. si no, factor = maxVel / rapidez; devuelve {vx * factor, vy * factor}.
        // OJO: el test usa (3, 4, 5) -> {3,4} (ya cabe) y (6, 8, 5) -> {3,4} (rapidez 10 escalada a 5).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para limitarRapidez");
    }

    /**
     * Reto Extra 10: Ángulo (en grados, redondeado) hacia el que apunta un vector (dx, dy).
     */
    public static int anguloHaciaGrados(double dx, double dy) {
        // GUÍA: teoría 3.11 (apuntar un cañón al ratón = atan2 del vector que los une).
        // 1. radianes = Math.atan2(dy, dx)  (¡OJO al orden: primero Y, luego X!).
        // 2. grados = Math.toDegrees(radianes).
        // 3. devuelve (int) Math.round(grados).
        // OJO: el test usa (1, 0) -> 0 (derecha), (0, 1) -> 90 (abajo en coords de pantalla) y (-1, 0) -> 180.
        // CULTURA: atan2 es EL truco para "mirar hacia": una torreta que sigue al jugador, una flecha
        //   de brújula, el cálculo del rumbo en un mapa (b35). Devuelve el ángulo correcto en los 4
        //   cuadrantes, cosa que atan() solo no hace.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para anguloHaciaGrados");
    }
}
