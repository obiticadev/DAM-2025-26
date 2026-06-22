package com.masterclass.api.b32_fxbase;

import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 257 · El {@code Stage} (ventana del SO): título, tamaño, posición y modalidad.
 *
 * <p>Teoría: {@code teoria/32_JavaFX_Base.md} (sección 1.3).
 *
 * <p>Un {@code Stage} solo se puede crear/mostrar en el FX App Thread, así que la lógica que
 * SÍ se puede testear sin abrir ventana es el <b>cálculo de su geometría</b>: centrar, ajustar
 * el tamaño a límites, posicionar ventanas secundarias en cascada… Eso es lo que practicas aquí.
 */
public final class Ej257StageWindow {

    private Ej257StageWindow() {
    }

    /**
     * Calcula la esquina superior-izquierda para centrar una ventana en la pantalla.
     *
     * @return {@code double[]{x, y}}; {@code null} sin implementar
     */
    public static double[] centrar(double anchoVentana, double altoVentana,
                                   double anchoPantalla, double altoPantalla) {
        // TODO 1: calcula x = (anchoPantalla - anchoVentana) / 2.
        // TODO 2: calcula y = (altoPantalla - altoVentana) / 2.
        // TODO 3: si la ventana es más ancha que la pantalla, no dejes x negativa: súbela a 0.
        // TODO 4: igual con y: nunca negativa (Math.max(0, y)).
        // TODO 5: devuelve new double[]{x, y}.
        return null;
    }

    /**
     * Ajusta un tamaño pedido a sus límites [min, max] (cómo resuelve JavaFX el tamaño real).
     *
     * @return el valor ya recortado al rango; -1 sin implementar
     */
    public static double aplicarLimitesTamano(double pedido, double min, double max) {
        // TODO 6: si pedido < min, el resultado es min.
        // TODO 7: si pedido > max, el resultado es max.
        // TODO 8: en otro caso, el resultado es 'pedido'.
        // TODO 9: (defensa) si min > max, considera el rango inválido y devuelve min.
        // TODO 10: devuelve el valor calculado.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Centrada en 1920x1080: " + java.util.Arrays.toString(centrar(800, 600, 1920, 1080)));
        System.out.println("Tamaño 2000 limitado a [400,1280]: " + aplicarLimitesTamano(2000, 400, 1280));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Título por defecto.
     * Devuelve el título dado, o "Sin título" si es null o está en blanco.
     */
    public static String tituloPorDefecto(String titulo) {
        // GUÍA: teoría 1.3 (toda ventana muestra un título en su barra).
        // 1. Si titulo es null o titulo.isBlank() -> "Sin título". 2. Si no, titulo.
        // OJO: isBlank() cubre "" y "   "; ordena la comprobación de null PRIMERO.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tituloPorDefecto");
    }

    /**
     * Reto Extra 2: Área de la ventana.
     * Devuelve ancho * alto (en píxeles²).
     */
    public static double areaVentana(double ancho, double alto) {
        // GUÍA: cálculo simple, base para decidir si una ventana "cabe".
        // 1. return ancho * alto;
        // OJO: el test usa 800x600 = 480000.0 (double, no int).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para areaVentana");
    }

    /**
     * Reto Extra 3: ¿Cabe en la pantalla?
     * Indica si la ventana cabe entera dentro de la pantalla.
     */
    public static boolean cabeEnPantalla(double ancho, double alto, double anchoPantalla, double altoPantalla) {
        // GUÍA: teoría 1.3. Cabe si NO se sale por ningún lado.
        // 1. return ancho <= anchoPantalla && alto <= altoPantalla;
        // OJO: usa <= (una ventana exactamente del tamaño de la pantalla cabe).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cabeEnPantalla");
    }

    /**
     * Reto Extra 4: Empujar la ventana dentro de la pantalla.
     * Dada una posición (x,y) y un tamaño, devuelve la posición corregida para que la ventana
     * quede totalmente visible (sin salirse por la derecha/abajo ni por (0,0)).
     */
    public static double[] clampPosicion(double x, double y, double ancho, double alto,
                                         double anchoPantalla, double altoPantalla) {
        // GUÍA: teoría 1.3 (mantener la ventana on-screen). Recorta en dos pasos por eje:
        // 1. Si x + ancho > anchoPantalla -> x = anchoPantalla - ancho.
        // 2. Luego, si x < 0 -> x = 0 (este orden importa si la ventana es enorme).
        // 3. Repite con y/alto/altoPantalla. 4. Devuelve new double[]{x, y}.
        // OJO: aplica primero el borde derecho/inferior y DESPUÉS el (0,0); el test mete una
        //   ventana parcialmente fuera por la derecha y por arriba a la vez.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clampPosicion");
    }

    /**
     * Reto Extra 5: ¿La modalidad bloquea otras ventanas?
     * Devuelve true para "APPLICATION_MODAL" y "WINDOW_MODAL"; false para "NONE".
     */
    public static boolean modalidadBloquea(String modalidad) {
        // GUÍA: teoría 1.3 (Modality: NONE no bloquea; las MODAL sí impiden interactuar con otras).
        // 1. return "APPLICATION_MODAL".equals(modalidad) || "WINDOW_MODAL".equals(modalidad);
        // OJO: compara con la constante a la izquierda (null-safe). "NONE" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para modalidadBloquea");
    }

    /**
     * Reto Extra 6: Relación de aspecto.
     * Devuelve ancho/alto (p.ej. 16:9 ≈ 1.777…).
     */
    public static double relacionAspecto(double ancho, double alto) {
        // GUÍA: base para escalar manteniendo proporción (reto 7).
        // 1. Si alto == 0 -> devuelve 0 (evita dividir por cero). 2. Si no, ancho/alto.
        // OJO: el test comprueba 1920/1080 con tolerancia (compara double, usa delta).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para relacionAspecto");
    }

    /**
     * Reto Extra 7: Escalar manteniendo el aspecto.
     * Reduce (o mantiene) un tamaño para que quepa dentro de (maxAncho, maxAlto) sin deformarlo.
     */
    public static double[] escalarManteniendoAspecto(double ancho, double alto,
                                                     double maxAncho, double maxAlto) {
        // GUÍA: teoría 1.3 (escalado uniforme, como una imagen "contain").
        // 1. factor = min(maxAncho/ancho, maxAlto/alto). 2. Si factor > 1, déjalo en 1 (no agrandar).
        // 3. Devuelve new double[]{ancho*factor, alto*factor}.
        // PISTA: double f = Math.min(maxAncho/ancho, maxAlto/alto); f = Math.min(f, 1.0);
        // OJO: se usa el factor MENOR de los dos para que quepa por ambos lados; el test mete
        //   1600x1200 dentro de 800x800 -> sale 800x600 (limita el ancho).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escalarManteniendoAspecto");
    }

    /**
     * Reto Extra 8: Posición de la siguiente ventana en cascada.
     * Dada la posición de una ventana, devuelve la de la siguiente desplazada 'paso' px en diagonal.
     */
    public static double[] siguienteCascada(double x, double y, double paso) {
        // GUÍA: teoría 1.3 (varias Stage secundarias se abren "en cascada" para no taparse).
        // 1. return new double[]{x + paso, y + paso};
        // OJO: base del reto 9 (apilar N ventanas).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para siguienteCascada");
    }

    /**
     * Reto Extra 9: Pila de ventanas en cascada.
     * Devuelve las posiciones de N ventanas, empezando en (0,0) y aplicando 'paso' en cada una.
     */
    public static List<double[]> pilaDeVentanas(int n, double paso) {
        // GUÍA: teoría 1.3. Genera la secuencia (0,0), (paso,paso), (2·paso,2·paso)…
        // 1. Lista vacía. 2. for i in 0..n-1 -> añade {i*paso, i*paso}.
        // PISTA: reaprovecha la idea de siguienteCascada dentro del bucle.
        // OJO: con n=0 devuelve lista vacía; la primera ventana va en (0,0).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pilaDeVentanas");
    }

    /**
     * Reto Extra 10: Convertir dp a px según la escala de pantalla.
     * Multiplica una medida en "dp" (densidad independiente) por el factor de escala y redondea.
     */
    public static int dpToPx(double dp, double escala) {
        // GUÍA: teoría 1.3 (pantallas HiDPI/Retina: 1 dp = 'escala' px reales).
        // 1. return (int) Math.round(dp * escala);
        // OJO: escala 2.0 (Retina) sobre 100 dp -> 200 px; el test prueba 1.5 -> redondeo.
        // CULTURA: este "dp" es EXACTAMENTE el concepto de densidad de Android (PMDM, b42):
        //   diseñas en dp y el SO multiplica por la densidad del dispositivo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dpToPx");
    }
}
