package com.masterclass.api.b32_fxbase;

import javafx.scene.layout.Region;

/**
 * Ejercicio 261 · Tamaños (min/pref/max), {@code Insets} y bounds.
 *
 * <p>Teoría: {@code teoria/32_JavaFX_Base.md} (sección 1.6).
 *
 * <p>El tamaño REAL que pinta JavaFX sale de combinar el preferido con sus límites y el espacio
 * disponible. Esa resolución es aritmética pura: justo lo que se prueba aquí sin abrir ventana.
 */
public final class Ej261SizingAndBounds {

    private Ej261SizingAndBounds() {
    }

    /**
     * Tamaño efectivo: recorta el preferido al rango [min, max] (cómo decide JavaFX la medida).
     *
     * @return el tamaño resultante; -1 sin implementar
     */
    public static double tamanoEfectivo(double pref, double min, double max) {
        // TODO 1: si min > max, el rango es inválido -> devuelve min (defensa).
        // TODO 2: si pref < min -> el resultado es min.
        // TODO 3: si pref > max -> el resultado es max.
        // TODO 4: en otro caso, el resultado es pref.
        // TODO 5: devuelve el resultado.
        return -1;
    }

    /**
     * Tamaño del área de contenido al restar los insets (relleno) del tamaño total.
     *
     * @return {@code double[]{anchoContenido, altoContenido}}; {@code null} sin implementar
     */
    public static double[] boundsConInsets(double ancho, double alto,
                                           double top, double right, double bottom, double left) {
        // TODO 6: ancho de contenido = ancho - left - right.
        // TODO 7: alto de contenido = alto - top - bottom.
        // TODO 8: si el ancho de contenido sale negativo, súbelo a 0.
        // TODO 9: si el alto de contenido sale negativo, súbelo a 0.
        // TODO 10: devuelve new double[]{anchoContenido, altoContenido}.
        return null;
    }

    public static void main(String[] args) {
        System.out.println("pref 1000 en [200,800] -> " + tamanoEfectivo(1000, 200, 800));
        System.out.println("contenido de 300x200 con insets 10 -> "
                + java.util.Arrays.toString(boundsConInsets(300, 200, 10, 10, 10, 10)));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: ¿Es redimensionable?
     * Un nodo se puede redimensionar si su min es estrictamente menor que su max.
     */
    public static boolean esRedimensionable(double min, double max) {
        // GUÍA: teoría 1.6 (si min == max, el nodo queda "clavado" a ese tamaño).
        // 1. return min < max;
        // OJO: el test prueba min==max -> false (tamaño fijo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRedimensionable");
    }

    /**
     * Reto Extra 2: Área de contenido.
     * Devuelve ancho*alto, pero nunca negativo (si alguno es negativo, devuelve 0).
     */
    public static double areaContenido(double ancho, double alto) {
        // GUÍA: teoría 1.6 (un tamaño negativo no tiene sentido físico).
        // 1. Si ancho < 0 o alto < 0 -> 0. 2. Si no, ancho*alto.
        // OJO: el test mete un ancho negativo y espera 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para areaContenido");
    }

    /**
     * Reto Extra 3: Fijar el tamaño preferido de una región.
     * Aplica setPrefSize y devuelve la región.
     */
    public static Region aplicarPref(Region region, double ancho, double alto) {
        // GUÍA: teoría 1.6 (prefWidth/prefHeight = el tamaño que el nodo "querría" tener).
        // 1. region.setPrefSize(ancho, alto); 2. devuelve region.
        // OJO: el test lee getPrefWidth()/getPrefHeight().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aplicarPref");
    }

    /**
     * Reto Extra 4: Fijar min y max (solo ancho) de una región.
     * Aplica setMinWidth y setMaxWidth y devuelve la región.
     */
    public static Region aplicarMinMaxAncho(Region region, double min, double max) {
        // GUÍA: teoría 1.6 (los límites acotan cuánto puede encogerse/crecer el nodo).
        // 1. region.setMinWidth(min); region.setMaxWidth(max); 2. devuelve region.
        // OJO: el test lee getMinWidth()/getMaxWidth().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aplicarMinMaxAncho");
    }

    /**
     * Reto Extra 5: ¿Cabe el preferido en el espacio disponible?
     */
    public static boolean cabe(double pref, double disponible) {
        // GUÍA: teoría 1.6. Cabe si el preferido no supera lo disponible.
        // 1. return pref <= disponible;
        // OJO: usa <= (justo del tamaño disponible, cabe).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cabe");
    }

    /**
     * Reto Extra 6: Recorte genérico a un rango (clamp).
     * Devuelve 'valor' recortado a [min, max].
     */
    public static double clamp(double valor, double min, double max) {
        // GUÍA: teoría 1.6 (este clamp es el ladrillo que usa tamanoEfectivo por dentro).
        // 1. return Math.max(min, Math.min(valor, max));
        // OJO: el orden importa: primero min(valor,max), luego max(min,...). El test prueba
        //   un valor por debajo y otro por encima.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clamp");
    }

    /**
     * Reto Extra 7: Ancho total sumando insets a un contenido.
     * Operación inversa al core: del ancho de contenido obtén el ancho total con padding.
     */
    public static double anchoConInsets(double anchoContenido, double left, double right) {
        // GUÍA: teoría 1.6 (total = contenido + relleno; inverso de boundsConInsets).
        // 1. return anchoContenido + left + right;
        // OJO: el test: contenido 280 + 10 + 10 = 300.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para anchoConInsets");
    }

    /**
     * Reto Extra 8: Expandir para llenar (respetando el máximo).
     * Un nodo crece hasta lo disponible, pero sin pasarse de su max ni bajar de su pref.
     */
    public static double expandirParaLlenar(double pref, double max, double disponible) {
        // GUÍA: teoría 1.6 (comportamiento de un nodo con grow: ocupa lo disponible, topado por max).
        // 1. objetivo = min(disponible, max). 2. resultado = max(pref, objetivo).
        // PISTA: primero acota por arriba con max, luego asegura el mínimo con pref.
        // OJO: si disponible < pref el nodo NO se encoge por debajo de pref (devuelve pref).
        //   Test: pref 100, max 400, disponible 300 -> 300; disponible 500 -> 400 (topa en max).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para expandirParaLlenar");
    }

    /**
     * Reto Extra 9: Redondear a píxel.
     * Ajusta una coordenada al píxel entero más cercano (snap to pixel).
     */
    public static double redondearPixel(double valor) {
        // GUÍA: teoría 1.6 (JavaFX "engancha" al píxel para que los bordes salgan nítidos).
        // 1. return Math.round(valor); (Math.round(double) devuelve long -> se promociona a double)
        // OJO: 10.4 -> 10.0, 10.6 -> 11.0. El test usa tolerancia.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para redondearPixel");
    }

    /**
     * Reto Extra 10: Factor de escala para que un tamaño quepa en un marco.
     * Devuelve el factor (<=1) para encajar (ancho,alto) dentro de (maxAncho,maxAlto) sin deformar.
     */
    public static double escalaParaCaber(double ancho, double alto, double maxAncho, double maxAlto) {
        // GUÍA: teoría 1.6 (mismo principio que escalarManteniendoAspecto de Ej257, pero solo el factor).
        // 1. factor = min(maxAncho/ancho, maxAlto/alto). 2. devuelve min(factor, 1.0) (nunca agrandar).
        // OJO: el test: 1600x1200 en 800x800 -> 0.5 (limita el ancho). Si ya cabe -> 1.0.
        // CULTURA: este "fit" es lo que usarás para encajar imágenes en un ImageView (b40, multimedia)
        //   manteniendo la relación de aspecto. La geometría de la UI se repite por todas partes.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escalaParaCaber");
    }
}
