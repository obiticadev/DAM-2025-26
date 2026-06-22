package com.masterclass.api.b37_fxcustom;

import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 294 · {@code Control} + {@code Skin}: separar el ESTADO del PINTADO (el MVC del control).
 *
 * <p>Teoría: {@code teoria/37_JavaFX_Componentes_Canvas.md} (sección 2).
 *
 * <p>Un control "de verdad" en JavaFX separa dos cosas: el {@code Control} guarda el <b>estado</b>
 * (las propiedades: el valor actual de un medidor, la valoración de un rating) y el {@code Skin}
 * decide cómo se <b>pinta</b> ese estado (cuánto gira la aguja, cuántas estrellas se llenan). Es el
 * patrón MVC aplicado a UN control: Modelo = propiedades del Control, Vista = Skin. La ventaja: el
 * mismo estado puede tener varios Skins (look distinto) y el estado se prueba sin pintar. Aquí el
 * core es la <b>lógica de estado</b> de un medidor (gauge) y un rating de estrellas; el Skin
 * (cálculos de ángulo, posiciones de marcas) son los retos. Todo headless y testeable.
 */
public final class Ej294SkinnableControl {

    private Ej294SkinnableControl() {
    }

    /**
     * Acota un valor al rango [min, max] del medidor. Es la regla de oro de la propiedad de un
     * Control: por mucho que te empujen el valor, nunca sale de su rango legal.
     *
     * @param valor valor propuesto
     * @param min   límite inferior del rango
     * @param max   límite superior del rango
     * @return el valor acotado a [min, max]; {@code -1} sin implementar
     */
    public static double valorAcotado(double valor, double min, double max) {
        // TODO 1: si valor < min, devuelve min.
        // TODO 2: si valor > max, devuelve max.
        // TODO 3: en otro caso, devuelve el valor tal cual.
        // TODO 4: piensa el orden: primero recorta por abajo, luego por arriba.
        // TODO 5: el test prueba valor=150, min=0, max=100 -> 100; y valor=-5 -> 0.
        return -1;
    }

    /**
     * Calcula cuántas estrellas se pintan LLENAS en un rating, dado el valor y el total. El Skin
     * usaría este número para dibujar N estrellas llenas y (total-N) vacías.
     *
     * @param valoracion valoración actual (0..total)
     * @param total      número total de estrellas
     * @return estrellas llenas, acotado a [0, total]; {@code -1} sin implementar
     */
    public static int estrellasLlenas(int valoracion, int total) {
        // TODO 6: si valoracion < 0, las llenas son 0 (no hay valoración negativa).
        // TODO 7: si valoracion > total, las llenas son 'total' (no se puede pasar del máximo).
        // TODO 8: en otro caso, las llenas son la propia valoracion.
        // TODO 9: este redondeo/acotado es justo lo que el Control hace al recibir un set fuera de rango.
        // TODO 10: el test prueba valoracion=3, total=5 -> 3; valoracion=9, total=5 -> 5 (caso límite).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Valor 150 en [0,100] -> " + valorAcotado(150, 0, 100));
        System.out.println("Valor -5 en [0,100]  -> " + valorAcotado(-5, 0, 100));
        System.out.println("Rating 3 de 5        -> " + estrellasLlenas(3, 5));
        System.out.println("Rating 9 de 5        -> " + estrellasLlenas(9, 5));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Símbolo de estrella.
     * Devuelve el carácter de estrella llena o vacía que pintaría el Skin.
     */
    public static String simboloEstrella(boolean llena) {
        // GUÍA: teoría 2.1 (el Skin traduce un booleano de estado al glifo a pintar).
        // 1. return llena ? "★" : "☆";
        // OJO: el test compara con los caracteres exactos ★ (U+2605) y ☆ (U+2606).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para simboloEstrella");
    }

    /**
     * Reto Extra 2: Texto de la valoración.
     * Devuelve el texto accesible "N de TOTAL" que el Skin pone como accessibleText.
     */
    public static String textoValoracion(int valoracion, int total) {
        // GUÍA: teoría 2.1 (el estado también se expone como TEXTO para accesibilidad, ver b36).
        // 1. return valoracion + " de " + total;
        // OJO: el test prueba 3 y 5 -> "3 de 5" (con los espacios exactos alrededor de "de").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para textoValoracion");
    }

    /**
     * Reto Extra 3: Fracción del rango.
     * Convierte el valor del medidor en una fracción 0.0..1.0 dentro de su rango (lo que el Skin necesita).
     */
    public static double fraccionDeRango(double valor, double min, double max) {
        // GUÍA: teoría 2.2 (el Skin trabaja en fracciones 0..1, no en el valor crudo).
        // 1. Si max == min, devuelve 0 (rango degenerado, evita dividir por cero).
        // 2. return (valor - min) / (max - min).
        // PISTA: valor=25, min=0, max=100 -> 0.25.
        // OJO: el test usa delta 1e-9 al comparar doubles; calcula con cuidado, sin redondear.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para fraccionDeRango");
    }

    /**
     * Reto Extra 4: Ángulo de la aguja.
     * Un medidor barre 270 grados. Convierte una fracción 0..1 en el ángulo de la aguja (0..270).
     */
    public static double anguloAguja(double fraccion) {
        // GUÍA: teoría 2.2 (el Skin de un gauge mapea la fracción a un ángulo de barrido).
        // 1. return fraccion * 270.0;
        // PISTA: fraccion=0 -> 0°; fraccion=1 -> 270°; fraccion=0.5 -> 135°.
        // OJO: el test prueba 0.5 -> 135.0 con delta 1e-9.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para anguloAguja");
    }

    /**
     * Reto Extra 5: Color por zona.
     * Según la fracción, el medidor pinta la zona verde/ámbar/roja (semáforo de un dashboard).
     */
    public static String colorPorZona(double fraccion) {
        // GUÍA: teoría 2.3 (el Skin elige color según el TRAMO en el que cae el valor).
        // 1. si fraccion < 0.5 -> "verde".
        // 2. si fraccion < 0.8 -> "ambar".
        // 3. en otro caso -> "rojo".
        // PISTA: aprovecha que los if van en orden creciente (ya descartaste < 0.5 antes de mirar < 0.8).
        // OJO: el test prueba 0.3 -> "verde", 0.6 -> "ambar", 0.9 -> "rojo". La frontera 0.5 es "ambar".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para colorPorZona");
    }

    /**
     * Reto Extra 6: Acotar un entero.
     * La versión entera de {@code valorAcotado}, para propiedades int del Control (p.ej. un Spinner).
     */
    public static int acotarEntero(int valor, int min, int max) {
        // GUÍA: teoría 2.3 (la misma regla de acotado, pero con int — patrón Math.max/Math.min).
        // 1. return Math.max(min, Math.min(valor, max));
        // PISTA: Math.min(valor, max) recorta por arriba; Math.max(min, ...) recorta por abajo.
        // OJO: el test prueba valor=12, min=1, max=10 -> 10; valor=0 -> 1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para acotarEntero");
    }

    /**
     * Reto Extra 7: Incrementar dentro del rango.
     * Sube la valoración en 1, sin pasar del total (el "+1" de un Spinner o un rating clicado).
     */
    public static int incrementar(int valoracion, int total) {
        // GUÍA: teoría 2.4 (un evento del Skin pide subir el estado; el Control acota el resultado).
        // 1. Suma 1 a la valoración. 2. Acótala a [0, total] (reutiliza la idea de acotarEntero).
        // PISTA: return Math.min(valoracion + 1, total);  (no puede bajar de 0 si ya era >= 0).
        // OJO: el caso límite del test es valoracion=5, total=5 -> 5 (ya en el tope, no sube).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para incrementar");
    }

    /**
     * Reto Extra 8: Posiciones de las marcas.
     * Devuelve las fracciones 0..1 donde el Skin dibuja las marcas (ticks) de la escala.
     */
    public static List<Double> posicionesMarcas(int numeroMarcas) {
        // GUÍA: teoría 2.4 (el Skin reparte N marcas equidistantes a lo largo de la escala).
        // 1. Si numeroMarcas <= 1, devuelve List.of(0.0) (una sola marca en el origen).
        // 2. Para i de 0 a numeroMarcas-1, añade i / (double)(numeroMarcas - 1).
        // PISTA: con 3 marcas -> [0.0, 0.5, 1.0]; el divisor es (n-1) para incluir 0 y 1.
        // OJO: el test compara cada elemento con delta 1e-9; usa (double) en la división.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para posicionesMarcas");
    }

    /**
     * Reto Extra 9: Pseudo-clase activa del estado.
     * El Skin reacciona a pseudo-clases según el valor (vacío/lleno/parcial), igual que en b36.
     */
    public static String pseudoEstado(double fraccion) {
        // GUÍA: teoría 2.5 (el Control define PseudoClass propias que el .css usa: :vacio, :lleno...).
        // 1. si fraccion <= 0.0 -> "vacio".
        // 2. si fraccion >= 1.0 -> "lleno".
        // 3. en otro caso -> "parcial".
        // OJO: el test prueba 0.0 -> "vacio", 1.0 -> "lleno", 0.4 -> "parcial". Cuida los <= y >=.
        // CULTURA: enlaza con b36 (Ej288 pseudo-clases): el Control llama pseudoClassStateChanged según su estado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pseudoEstado");
    }

    /**
     * Reto Extra 10: Serializar el estado del control.
     * Vuelca el estado a una cadena "valor/total" para guardarlo (preferencias, JSON de b02).
     */
    public static String serializarEstado(int valoracion, int total) {
        // GUÍA: teoría 2.5 (el estado del Control se persiste; el Skin no, se reconstruye al pintar).
        // 1. Acota la valoración a [0, total] antes de serializar (estado siempre limpio).
        // 2. return acotada + "/" + total;
        // PISTA: reutiliza acotarEntero(valoracion, 0, total).
        // OJO: el test prueba valoracion=7, total=5 -> "5/5" (la acotación se aplica AL serializar).
        // CULTURA: persistir el estado de un control conecta con b02 (JSON) y las preferencias de b39.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarEstado");
    }

    /** Helper de demostración: arma una lista mutable de fracciones. */
    static List<Double> fracciones(double... fs) {
        List<Double> r = new ArrayList<>();
        for (double f : fs) {
            r.add(f);
        }
        return r;
    }
}
