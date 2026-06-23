package com.masterclass.api.b41_anim;

/**
 * Ejercicio 324 · Mini-juego 2D integrador (tipo Breakout/Pong).
 *
 * <p>Teoría: {@code teoria/41_Animacion_Juegos.md} (sección 6).
 *
 * <p>Este es el ejercicio que JUNTA todo el bloque: la pelota se mueve con la física de Ej321,
 * rebota con las colisiones de Ej322, el juego avanza por estados de Ej323 y el bucle es el de
 * Ej320. Aquí escribes la LÓGICA pura del juego: el rebote de la pelota en paredes y pala, la
 * detección de victoria/derrota y la gestión de ladrillos, puntuación y niveles. Nada de esto
 * necesita pantalla: son números y booleanos. El {@code PlaygroundJuego} coge estos métodos y los
 * convierte en un juego jugable de verdad sobre un {@code Canvas} con un {@code AnimationTimer}.
 */
public final class Ej324MiniGame2D {

    private Ej324MiniGame2D() {
    }

    /**
     * Nueva velocidad de la pelota en un eje tras comprobar el rebote contra los dos bordes
     * (0 y {@code limite}). Solo invierte si la pelota va HACIA el borde que toca (evita el
     * "rebote pegajoso" en el que se queda atascada invirtiendo cada frame).
     *
     * @param pos    posición de la pelota en ese eje
     * @param vel    velocidad en ese eje
     * @param limite borde máximo (el mínimo es 0)
     * @return la velocidad tras el posible rebote; {@code 0} sin implementar
     */
    public static double velocidadTrasPared(double pos, double vel, double limite) {
        // TODO 1: si pos <= 0 y vel < 0 (toca el borde 0 yendo hacia él) -> devuelve -vel.
        // TODO 2: si pos >= limite y vel > 0 (toca el borde superior yendo hacia él) -> devuelve -vel.
        // TODO 3: en cualquier otro caso devuelve vel sin tocar (el test: (0,-3,100)->3; (50,3,100)->3).
        return 0;
    }

    /**
     * ¿La pelota (a la altura {@code pelotaY}) golpea la pala vertical que ocupa
     * [{@code palaY}, {@code palaY + palaAlto}]?
     *
     * @return {@code true} si la pelota está a la altura de la pala; {@code false} sin implementar
     */
    public static boolean golpeaPala(double pelotaY, double palaY, double palaAlto) {
        // TODO 4: calcula el borde superior de la pala: sup = palaY.
        // TODO 5: calcula el borde inferior: inf = palaY + palaAlto.
        // TODO 6: devuelve pelotaY >= sup && pelotaY <= inf (el test: pelota a 25 con pala [20,40] -> true).
        return false;
    }

    /**
     * ¿Hay ganador? Devuelve 1 si el jugador 1 llega a {@code objetivo}, 2 si llega el jugador 2,
     * y 0 si la partida sigue.
     *
     * @return 1, 2 o 0; {@code -1} sin implementar
     */
    public static int hayGanador(int puntos1, int puntos2, int objetivo) {
        // TODO 7: si puntos1 >= objetivo -> devuelve 1.
        // TODO 8: si puntos2 >= objetivo -> devuelve 2.
        // TODO 9: en otro caso -> devuelve 0 (la partida continúa).
        // TODO 10: (nota) el objetivo típico de un Pong es 5; el test usa objetivo 5.
        return -1;
    }

    public static void main(String[] args) {
        double vy = velocidadTrasPared(0, -3, 100);  // toca el techo
        System.out.println("Velocidad tras rebotar en el techo: " + vy);
        System.out.println("¿Golpea la pala? " + golpeaPala(25, 20, 20));
        System.out.println("¿Ganador con 5-2 a 5? " + hayGanador(5, 2, 5));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Sumar puntos al marcador.
     */
    public static int puntuar(int marcador, int puntos) {
        // GUÍA: teoría 6.3 (el score es un acumulador; cada acierto suma).
        // 1. devuelve marcador + puntos.
        // OJO: el test: (10, 5) -> 15.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para puntuar");
    }

    /**
     * Reto Extra 2: Vidas tras un fallo (resta 1, nunca por debajo de 0).
     */
    public static int vidasTrasFallo(int vidas) {
        // GUÍA: teoría 6.4 (perder una vida no debe dejar vidas negativas).
        // 1. devuelve Math.max(0, vidas - 1).
        // OJO: el test: 3 -> 2 y 0 -> 0 (no baja de cero).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para vidasTrasFallo");
    }

    /**
     * Reto Extra 3: ¿Se acabó la partida por quedarse sin vidas?
     */
    public static boolean juegoTerminado(int vidas) {
        // GUÍA: teoría 6.4 (cuando vidas llega a 0 -> game over -> estado FIN de Ej323).
        // 1. devuelve vidas <= 0.
        // OJO: el test: 0 -> true; 1 -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para juegoTerminado");
    }

    /**
     * Reto Extra 4: Velocidad de la pelota según el nivel (cada nivel un 10% más rápida).
     */
    public static double velocidadPorNivel(double base, int nivel) {
        // GUÍA: teoría 6.5 (la dificultad sube acelerando: base · (1 + 0.1·nivel)).
        // 1. devuelve base * (1 + 0.1 * nivel).
        // OJO: el test: (100, 0) -> 100 (nivel 0 = base) y (100, 2) -> 120 (compara con tolerancia).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para velocidadPorNivel");
    }

    /**
     * Reto Extra 5: Recortar la posición de la pala para que no se salga de la pantalla.
     */
    public static double clampPala(double palaY, double palaAlto, double pantallaAlto) {
        // GUÍA: teoría 6.6 (la pala sigue al jugador pero no puede salir; clamp como Ej321).
        // 1. maxY = pantallaAlto - palaAlto.
        // 2. devuelve Math.max(0, Math.min(palaY, maxY)).
        // OJO: el test: (-5, 20, 100) -> 0 y (95, 20, 100) -> 80 (80+20=100, justo el borde).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clampPala");
    }

    /**
     * Reto Extra 6: "Efecto" del rebote en la pala: posición relativa del impacto en [-1, 1]
     * (-1 = golpea el borde superior, 0 = el centro, +1 = el inferior). Define hacia dónde sale.
     */
    public static double efectoRebotePala(double pelotaY, double palaY, double palaAlto) {
        // GUÍA: teoría 6.7 (en Breakout/Pong, golpear el extremo de la pala "tuerce" la pelota).
        // 1. centro = palaY + palaAlto / 2.
        // 2. relativo = (pelotaY - centro) / (palaAlto / 2)  (lo normaliza a [-1,1]).
        // 3. recorta el resultado a [-1, 1] por si la pelota pega justo en el borde.
        // OJO: el test: golpe en el centro (pelota 30, pala [20,40]) -> 0.0; en el borde superior (20) -> -1.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para efectoRebotePala");
    }

    /**
     * Reto Extra 7: Cuántos ladrillos quedan sin romper (cuántos {@code true} en el array).
     */
    public static int ladrillosRestantes(boolean[] ladrillos) {
        // GUÍA: teoría 6.8 (true = ladrillo vivo; romper uno lo pone a false).
        // 1. si ladrillos es null devuelve 0.
        // 2. cuenta los true.
        // OJO: el test: {true, false, true} -> 2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ladrillosRestantes");
    }

    /**
     * Reto Extra 8: ¿Está el nivel completado (no queda ningún ladrillo vivo)?
     */
    public static boolean nivelCompletado(boolean[] ladrillos) {
        // GUÍA: teoría 6.8 (ganas el nivel cuando todos los ladrillos están a false).
        // 1. reutiliza ladrillosRestantes: devuelve ladrillosRestantes(ladrillos) == 0.
        // OJO: el test: {false, false} -> true; {false, true} -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nivelCompletado");
    }

    /**
     * Reto Extra 9: Índice lineal de un ladrillo en una rejilla (fila, columna) con {@code columnas}
     * columnas por fila. Convierte coordenadas 2D a un índice de array 1D.
     */
    public static int indiceLadrillo(int fila, int columna, int columnas) {
        // GUÍA: teoría 6.9 (una rejilla se guarda en un array plano: índice = fila·columnas + columna).
        // 1. devuelve fila * columnas + columna.
        // OJO: el test: (2, 3, 5) -> 13 (2 filas completas de 5 = 10, más 3). Mismo truco que el píxel
        //   row-major de b40 (Ej317).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para indiceLadrillo");
    }

    /**
     * Reto Extra 10: Siguiente nivel: si el nivel está completado, avanza uno, pero sin pasar de
     * {@code maxNivel}. Si no está completado, se queda en el nivel actual.
     */
    public static int siguienteNivel(int nivel, boolean completado, int maxNivel) {
        // GUÍA: teoría 6.10 (la progresión de niveles: avanzar al limpiar, con un tope).
        // 1. si no 'completado' devuelve nivel.
        // 2. si completado devuelve Math.min(nivel + 1, maxNivel).
        // OJO: el test: (1, true, 3) -> 2; (3, true, 3) -> 3 (ya en el máximo); (1, false, 3) -> 1.
        // CULTURA: este "avanzar de fase con tope" es una máquina de estados más (Ej323) y, junto con
        //   hayGanador y juegoTerminado, completa el ciclo de vida de la partida: jugar -> ganar nivel
        //   -> siguiente -> ... -> sin vidas -> FIN. Has cerrado el bucle del juego entero.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para siguienteNivel");
    }
}
