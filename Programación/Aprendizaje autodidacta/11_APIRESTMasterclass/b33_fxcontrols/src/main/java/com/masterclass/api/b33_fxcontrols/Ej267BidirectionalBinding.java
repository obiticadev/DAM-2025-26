package com.masterclass.api.b33_fxcontrols;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * Ejercicio 267 · Binding bidireccional: {@code bindBidirectional} y sincronizar dos controles.
 *
 * <p>Teoría: {@code teoria/33_JavaFX_Controles_Binding.md} (sección 2.5).
 *
 * <p>Un binding unidireccional (Ej266) va en un solo sentido y deja el destino de solo lectura.
 * El bidireccional mantiene DOS properties en sincronía: cambiar cualquiera actualiza la otra, y
 * ambas siguen siendo escribibles. Es el enlace típico modelo↔control. Lógica pura: molde estándar.
 */
public final class Ej267BidirectionalBinding {

    private Ej267BidirectionalBinding() {
    }

    /**
     * Enlaza dos {@link StringProperty} de forma bidireccional, cambia la PRIMERA y comprueba que
     * la segunda se entera.
     *
     * @param inicial   valor inicial de la property A
     * @param nuevoEnA  valor que se asignará a A tras el enlace
     * @return el valor que acaba teniendo la property B; {@code ""} sin implementar
     */
    public static String sincronizarTextos(String inicial, String nuevoEnA) {
        // TODO 1: crea StringProperty a = new SimpleStringProperty(inicial).
        // TODO 2: crea StringProperty b = new SimpleStringProperty("otro").
        // TODO 3: enláza­las: a.bindBidirectional(b)  (ojo: al enlazar, A toma el valor de B).
        // TODO 4: asigna a.set(nuevoEnA): el cambio debe propagarse a B.
        // TODO 5: devuelve b.get() (debe ser nuevoEnA).
        return "";
    }

    /**
     * Simula dos controles enlazados: escribes en el segundo y el primero queda sincronizado.
     *
     * @param escritoEnB texto que se "teclea" en B
     * @return el valor que acaba teniendo A; {@code ""} sin implementar
     */
    public static String sincronizarDosControles(String escritoEnB) {
        // TODO 6: crea dos StringProperty a y b (cualquier valor inicial).
        // TODO 7: enláza­las bidireccionalmente.
        // TODO 8: asigna b.set(escritoEnB) (como si el usuario teclease en el control B).
        // TODO 9: el binding propaga el cambio de B hacia A automáticamente.
        // TODO 10: devuelve a.get() (debe ser escritoEnB).
        return "";
    }

    public static void main(String[] args) {
        System.out.println("B tras cambiar A: " + sincronizarTextos("X", "HOLA"));
        System.out.println("A tras escribir en B: " + sincronizarDosControles("mundo"));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Comprobar el enlace.
     * Enlaza A y B bidireccionalmente y devuelve si quedan con el mismo valor.
     */
    public static boolean enlaceBidireccional(StringProperty a, StringProperty b) {
        // GUÍA: teoría 2.5 (bindBidirectional iguala las dos al instante y las mantiene iguales).
        // 1. a.bindBidirectional(b). 2. return a.get().equals(b.get());
        // OJO: al enlazar, A adopta el valor de B (B "gana" en el momento del enlace).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para enlaceBidireccional");
    }

    /**
     * Reto Extra 2: Escribir en uno, leer en el otro.
     * Tras enlazar, asigna 'texto' a A y devuelve el valor de B.
     */
    public static String escribirEnUno(StringProperty a, StringProperty b, String texto) {
        // GUÍA: teoría 2.5 (el cambio viaja en cualquier sentido).
        // 1. a.bindBidirectional(b). 2. a.set(texto). 3. return b.get();
        // OJO: a diferencia de bind() (Ej266), aquí SÍ puedes set() ambas: ninguna es de solo lectura.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escribirEnUno");
    }

    /**
     * Reto Extra 3: ¿Quién gana al enlazar?
     * Enlaza A(=izq) con B(=der) y devuelve el valor que comparten justo tras el enlace.
     */
    public static String valorDerechoGana(String izq, String der) {
        // GUÍA: teoría 2.5 (Trampa: en a.bindBidirectional(b), A toma el valor de B).
        // 1. Crea a=izq, b=der. 2. a.bindBidirectional(b). 3. return a.get();
        // OJO: el resultado es 'der', NO 'izq'. El argumento (el de la derecha) impone su valor.
        //   Esto importa al enlazar modelo↔vista: decide cuál tiene el dato bueno al arrancar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para valorDerechoGana");
    }

    /**
     * Reto Extra 4: Enlace numérico.
     * Enlaza dos {@link IntegerProperty}, asigna A y devuelve B.
     */
    public static int enlaceNumerico(IntegerProperty a, IntegerProperty b, int valor) {
        // GUÍA: teoría 2.5 (bindBidirectional existe para cada tipo de property).
        // 1. a.bindBidirectional(b). 2. a.set(valor). 3. return b.get();
        // OJO: ambas deben ser del MISMO tipo para el bidireccional directo (Integer con Integer).
        //   Para enlazar tipos distintos hace falta un conversor (reto 9).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para enlaceNumerico");
    }

    /**
     * Reto Extra 5: Enlace booleano.
     * Enlaza dos {@link BooleanProperty}, pon A a true y devuelve B.
     */
    public static boolean enlaceBooleano(BooleanProperty a, BooleanProperty b) {
        // GUÍA: teoría 2.5 (útil para sincronizar dos CheckBox "seleccionar todo").
        // 1. a.bindBidirectional(b). 2. a.set(true). 3. return b.get();
        // OJO: el test arranca ambas en false; tras poner A=true, B debe ser true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para enlaceBooleano");
    }

    /**
     * Reto Extra 6: Cambios en ambos sentidos.
     * Enlaza A y B; escribe primero en A y luego en B; devuelve el valor final de A.
     */
    public static String cambioEnAmbasDirecciones(StringProperty a, StringProperty b) {
        // GUÍA: teoría 2.5 (la sincronía es simétrica: vale en los dos sentidos, una y otra vez).
        // 1. a.bindBidirectional(b). 2. a.set("desde A"). 3. b.set("desde B"). 4. return a.get();
        // OJO: tras escribir en B, A vale "desde B" (el último cambio manda en ambas).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cambioEnAmbasDirecciones");
    }

    /**
     * Reto Extra 7: Desligar y comprobar independencia.
     * Enlaza, desliga con unbindBidirectional, cambia A y devuelve B (que NO debe cambiar).
     */
    public static String desligarBidireccional(StringProperty a, StringProperty b) {
        // GUÍA: teoría 2.5 (unbindBidirectional rompe la sincronía; quedan independientes).
        // 1. a.bindBidirectional(b). 2. a.unbindBidirectional(b). 3. a.set("solo A"). 4. return b.get();
        // PISTA: unbindBidirectional necesita la MISMA pareja (a, b) que usaste para enlazar.
        // OJO: tras desligar, cambiar A ya NO toca B; B conserva el valor que tenía al desligar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desligarBidireccional");
    }

    /**
     * Reto Extra 8: Tres properties en cadena.
     * Enlaza A↔B y B↔C; cambia A y devuelve C (la sincronía se propaga por toda la cadena).
     */
    public static String tresEnlazados(StringProperty a, StringProperty b, StringProperty c) {
        // GUÍA: teoría 2.5 (los enlaces bidireccionales se encadenan: A↔B↔C).
        // 1. a.bindBidirectional(b). 2. b.bindBidirectional(c). 3. a.set("nuevo"). 4. return c.get();
        // OJO: el cambio salta de A a B y de B a C; C debe acabar valiendo "nuevo".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tresEnlazados");
    }

    /**
     * Reto Extra 9: Enlace con conversor (tipos distintos).
     * Enlaza una {@link StringProperty} con una {@link IntegerProperty} usando un conversor;
     * asigna 42 al número y devuelve el texto resultante.
     */
    public static String enlaceConConversor(StringProperty texto, IntegerProperty numero) {
        // GUÍA: teoría 2.5 + 3.x (para enlazar tipos distintos hace falta un StringConverter).
        // 1. Bindings.bindBidirectional(texto, numero, new NumberStringConverter()).
        // 2. numero.set(42). 3. return texto.get();
        // PISTA: NumberStringConverter convierte Number↔String; al poner 42, el texto pasa a "42".
        // OJO: el orden de Bindings.bindBidirectional es (stringProperty, otraProperty, conversor).
        //   El conversor es el puente; sin él, no puedes mezclar String con Integer.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para enlaceConConversor");
    }

    /**
     * Reto Extra 10: Formulario espejo (modelo ↔ vista).
     * 'modelo' y 'vista' representan el dato y el control; enlázalos, simula que el usuario teclea
     * en la vista y devuelve lo que ve el modelo.
     */
    public static String formularioEspejo(StringProperty modelo, StringProperty vista) {
        // GUÍA: teoría 2.5 (el enlace modelo↔vista es la base de MVVM, que verás en b34).
        // 1. modelo.bindBidirectional(vista). 2. vista.set("tecleado por el usuario"). 3. return modelo.get();
        // OJO: al teclear en la vista, el modelo se actualiza solo (no copias a mano en un handler).
        // CULTURA: esto es el "two-way data binding": v-model de Vue, [(ngModel)] de Angular,
        //   o un estado controlado en React. El control y el dato son el mismo valor, sincronizado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formularioEspejo");
    }
}
