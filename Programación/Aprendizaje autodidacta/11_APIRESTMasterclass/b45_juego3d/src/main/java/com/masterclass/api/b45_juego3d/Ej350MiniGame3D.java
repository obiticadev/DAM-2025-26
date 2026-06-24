package com.masterclass.api.b45_juego3d;

import java.util.Arrays;

/**
 * Ejercicio 350 · Mini-juego 3D integrador.
 *
 * <p>Reúne todo el bloque: vectores (Ej345), una cámara que sigue al jugador (Ej347), colisiones de
 * esfera (Ej348) y la idea de actualizar el estado en un tick (Ej349). El juego es mínimo: mueves un
 * objeto en 3D, una cámara lo sigue desde detrás, recoges ítems al tocarlos (colisión de esfera) y
 * sumas puntos. Aquí vive solo la <strong>lógica</strong> del juego (sin pintar): el
 * {@code PlaygroundMiniGame3D} la conecta con una escena JavaFX real.
 *
 * <p>Teoría: {@code teoria/45_Juegos3D_Motores.md} (sección 7).
 */
public final class Ej350MiniGame3D {

    private Ej350MiniGame3D() {
    }

    /**
     * Mueve una posición en una dirección a una velocidad durante un instante {@code dt}.
     *
     * @param pos        posición actual {x, y, z}
     * @param direccion  dirección del movimiento {x, y, z} (no tiene por qué estar normalizada)
     * @param velocidad  rapidez (unidades por segundo)
     * @param dt         delta de tiempo en segundos
     * @return la nueva posición; {@code null} si pos o direccion no miden 3
     */
    public static double[] mover(double[] pos, double[] direccion, double velocidad, double dt) {
        // TODO 1: si pos o direccion son null, o no miden 3 -> null.
        // TODO 2: factor = velocidad * dt (cuánto se avanza este frame).
        // TODO 3: nx = pos[0] + direccion[0]*factor.
        // TODO 4: ny = pos[1] + direccion[1]*factor ; nz = pos[2] + direccion[2]*factor.
        // TODO 5: devuelve new double[]{nx, ny, nz}.
        return null;
    }

    /**
     * Suma un punto si el jugador está lo bastante cerca del ítem para recogerlo.
     *
     * @param puntos   puntuación actual
     * @param jugador  posición del jugador {x, y, z}
     * @param item     posición del ítem {x, y, z}
     * @param radio    radio de recogida
     * @return {@code puntos + 1} si está dentro del radio; {@code puntos} si no; {@code -1} si algún punto es inválido
     */
    public static int puntuarSiRecoge(int puntos, double[] jugador, double[] item, double radio) {
        // TODO 6: si jugador o item son null, o no miden 3 -> -1.
        // TODO 7: calcula las diferencias dx, dy, dz entre jugador e item.
        // TODO 8: distancia al cuadrado d2 = dx² + dy² + dz² (sin raíz, como en las colisiones).
        // TODO 9: si d2 <= radio*radio -> devuelve puntos + 1 (recogido).
        // TODO 10: si no -> devuelve puntos (sin cambios).
        return -1;
    }

    public static void main(String[] args) {
        double[] pos = mover(new double[]{0, 0, 0}, new double[]{1, 0, 0}, 2, 0.5);
        System.out.println("nueva posición: " + Arrays.toString(pos));
        System.out.println("¿recoge? puntos: " + puntuarSiRecoge(0, new double[]{0, 0, 0}, new double[]{0.5, 0, 0}, 1));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Distancia entre el jugador y un punto (reutiliza la matemática de Ej345).
     */
    public static double distancia(double[] a, double[] b) {
        // GUÍA: teoría 7 (toda la lógica de juego se apoya en distancias: recoger, perseguir, disparar).
        // 1. Si a o b no miden 3 -> -1.
        // 2. Devuelve Ej345Vector3DMath.distancia(a, b).
        // OJO: el test: {0,0,0} y {0,3,4} -> 5.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para distancia");
    }

    /**
     * Reto Extra 2: ¿Está la posición dentro de los límites cúbicos del mundo?
     */
    public static boolean dentroDelMundo(double[] pos, double limite) {
        // GUÍA: teoría 7 (mantener al jugador dentro del nivel; un cubo de lado 2·limite centrado en 0).
        // 1. Si pos no mide 3 -> false.
        // 2. Devuelve |x| <= limite && |y| <= limite && |z| <= limite.
        // OJO: el test: {1,1,1} con límite 2 -> true; {3,0,0} -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dentroDelMundo");
    }

    /**
     * Reto Extra 3: Recortar (clamp) la posición a los límites del mundo.
     */
    public static double[] clampPosicion(double[] pos, double limite) {
        // GUÍA: teoría 7 (en vez de rechazar el movimiento, se "pega" al borde; sensación más suave).
        // 1. Si pos no mide 3 -> null.
        // 2. Cada coordenada: Math.max(-limite, Math.min(coord, limite)).
        // OJO: el test: {5,-5,0} con límite 2 -> {2,-2,0}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clampPosicion");
    }

    /**
     * Reto Extra 4: Posición de la cámara en tercera persona (sigue al jugador con un offset fijo).
     */
    public static double[] camaraSigue(double[] jugador, double[] offset) {
        // GUÍA: teoría 7 (cámara en 3.ª persona = posición del jugador + un desplazamiento detrás/arriba).
        // 1. Si jugador u offset no miden 3 -> null.
        // 2. Devuelve la suma componente a componente (usa Ej345Vector3DMath.sumar).
        // OJO: el test: jugador {1,1,1}, offset {0,5,-10} -> {1,6,-9}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para camaraSigue");
    }

    /**
     * Reto Extra 5: Nivel actual según los puntos (cada 10 puntos se sube de nivel).
     */
    public static int nivelPorPuntos(int puntos) {
        // GUÍA: teoría 7 (dificultad creciente: nivel = puntos/10 + 1, con división entera).
        // 1. Si puntos < 0 -> 1.
        // 2. Devuelve puntos / 10 + 1.
        // OJO: el test: 0 -> 1; 25 -> 3 (25/10 = 2, +1).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nivelPorPuntos");
    }

    /**
     * Reto Extra 6: Vidas tras recibir un golpe (sin bajar de 0).
     */
    public static int vidaTrasGolpe(int vidas, boolean golpe) {
        // GUÍA: teoría 7 (restar una vida al ser golpeado, pero nunca por debajo de cero).
        // 1. Si !golpe -> devuelve vidas sin cambios.
        // 2. Devuelve Math.max(0, vidas - 1).
        // OJO: el test: (3, true) -> 2; (0, true) -> 0 (no baja de cero).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para vidaTrasGolpe");
    }

    /**
     * Reto Extra 7: ¿Game over? (sin vidas).
     */
    public static boolean gameOver(int vidas) {
        // GUÍA: teoría 7 (condición de derrota; enlaza con la máquina de estados del juego de b41).
        // 1. Devuelve vidas <= 0.
        // OJO: el test: 0 -> true; 1 -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para gameOver");
    }

    /**
     * Reto Extra 8: Posición de aparición (spawn) cíclica entre varios puntos.
     */
    public static double[] spawnPosicion(int indice, double[][] puntos) {
        // GUÍA: teoría 7 (los enemigos/ítems aparecen rotando por una lista de spawns; módulo cíclico).
        // 1. Si puntos es null o está vacío -> null.
        // 2. Usa el índice módulo la cantidad de puntos (maneja índices grandes: indice % puntos.length).
        // OJO: el test: índice 3 sobre 2 puntos -> 3%2=1 -> el segundo punto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para spawnPosicion");
    }

    /**
     * Reto Extra 9: Aplicar gravedad a una velocidad vertical (salto/caída).
     * vy_nueva = vy + g·dt.
     */
    public static double aplicarGravedadSalto(double vy, double g, double dt) {
        // GUÍA: teoría 7 (física de salto: la gravedad va frenando el ascenso y acelera la caída).
        // 1. Devuelve vy + g*dt.
        // OJO: el test: vy 0, g -10, dt 0.5 -> -5 (empieza a caer).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aplicarGravedadSalto");
    }

    /**
     * Reto Extra 10: IA simple — un enemigo persigue al jugador moviéndose hacia él.
     */
    public static double[] enemigoPersigue(double[] enemigo, double[] jugador, double velocidad, double dt) {
        // GUÍA: teoría 7 (perseguir = dirección normalizada hacia el objetivo · velocidad · dt, sumada).
        // 1. Si enemigo o jugador no miden 3 -> null.
        // 2. dir = jugador - enemigo (resta componente a componente).
        // 3. dirN = normalizar(dir) (usa Ej345); si la magnitud es 0, ya está encima -> devuelve enemigo.
        // 4. Devuelve enemigo + dirN·(velocidad·dt).
        // OJO: el test: enemigo {0,0,0}, jugador {10,0,0}, vel 2, dt 1 -> {2,0,0}.
        // CULTURA: en b41 (2D) "perseguir sin pasarse" usaba la misma idea con dos ejes; aquí, tres.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para enemigoPersigue");
    }
}
