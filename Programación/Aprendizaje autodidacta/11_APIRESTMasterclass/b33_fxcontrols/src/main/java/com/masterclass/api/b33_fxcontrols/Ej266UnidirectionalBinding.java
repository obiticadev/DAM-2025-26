package com.masterclass.api.b33_fxcontrols;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Ejercicio 266 · Binding unidireccional: {@code bind()} y recálculo automático.
 *
 * <p>Teoría: {@code teoria/33_JavaFX_Controles_Binding.md} (sección 2.4).
 *
 * <p>Un binding es una property de SOLO LECTURA cuyo valor se CALCULA a partir de otras y se
 * recalcula sola cuando ellas cambian. Es el corazón de "nunca actualices la UI a mano": describes
 * la fórmula UNA vez y JavaFX la mantiene viva. Lógica pura: molde estándar.
 */
public final class Ej266UnidirectionalBinding {

    private Ej266UnidirectionalBinding() {
    }

    /**
     * Total de una línea de pedido: {@code precio * cantidad} expresado como binding observable.
     *
     * @param precio   precio unitario
     * @param cantidad número de unidades
     * @return el total calculado por el binding; {@code -1} sin implementar
     */
    public static int totalLineaPedido(int precio, int cantidad) {
        // TODO 1: crea dos IntegerProperty con 'precio' y 'cantidad'.
        // TODO 2: crea el binding total = precioProp.multiply(cantidadProp).
        // TODO 3: fíjate en que NO multiplicas a mano: la fórmula queda "viva".
        // TODO 4: lee el valor del binding con intValue().
        // TODO 5: devuelve ese valor.
        return -1;
    }

    /**
     * Demuestra el recálculo automático: define el total una vez y cambia el precio DESPUÉS;
     * el binding refleja el cambio sin volver a multiplicar a mano.
     *
     * @param precioInicial precio de partida
     * @param cantidad      unidades (constante)
     * @param precioNuevo   nuevo precio que se asigna tras crear el binding
     * @return el total tras el cambio (precioNuevo * cantidad); {@code -1} sin implementar
     */
    public static int recalculoAutomatico(int precioInicial, int cantidad, int precioNuevo) {
        // TODO 6: IntegerProperty precio = new SimpleIntegerProperty(precioInicial); y la cantidad.
        // TODO 7: crea el binding total = precio.multiply(cantidad) UNA sola vez.
        // TODO 8: ahora cambia el precio: precio.set(precioNuevo).
        // TODO 9: NO recalcules a mano: vuelve a leer total.intValue() y verás el valor nuevo.
        // TODO 10: devuelve total.intValue() (debe valer precioNuevo * cantidad).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Total: " + totalLineaPedido(10, 3));
        System.out.println("Recalculado: " + recalculoAutomatico(10, 2, 15));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Enlazar una property a otra.
     * Enlaza 'destino' a 'fuente' con bind() y devuelve el valor que toma el destino.
     */
    public static String enlazarDestino(StringProperty destino, StringProperty fuente) {
        // GUÍA: teoría 2.4 (destino.bind(fuente): el destino COPIA y SIGUE a la fuente).
        // 1. destino.bind(fuente). 2. return destino.get().
        // OJO: tras bind, destino vale lo que fuente; si luego cambia fuente, cambia destino.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para enlazarDestino");
    }

    /**
     * Reto Extra 2: El doble de una property numérica.
     * Devuelve un valor calculado como el doble de la property dada.
     */
    public static int dobleDe(IntegerProperty numero) {
        // GUÍA: teoría 2.4 (las IntegerExpression ofrecen multiply/add/subtract que crean bindings).
        // 1. return numero.multiply(2).intValue();
        // OJO: multiply devuelve un binding; intValue() lee su valor actual.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dobleDe");
    }

    /**
     * Reto Extra 3: Suma observable de dos properties.
     * Devuelve la suma calculada mediante binding.
     */
    public static int suma(IntegerProperty a, IntegerProperty b) {
        // GUÍA: teoría 2.4 (a.add(b) crea un binding que vale la suma y se recalcula solo).
        // 1. return a.add(b).intValue();
        // OJO: si cambiaras a o b, el binding cambiaría; aquí solo leemos el valor inicial.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para suma");
    }

    /**
     * Reto Extra 4: ¿Está enlazada?
     * Indica si la property está actualmente enlazada con bind().
     */
    public static boolean estaEnlazada(Property<?> property) {
        // GUÍA: teoría 2.4 (isBound() dice si la property recibe su valor de otra).
        // 1. return property.isBound();
        // OJO: el test pasa una enlazada (true) y una normal (false). Una property enlazada NO
        //   se puede set() a mano (reto 8).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaEnlazada");
    }

    /**
     * Reto Extra 5: Nombre completo observable.
     * Devuelve "nombre apellido" calculado por concatenación de properties.
     */
    public static String nombreCompleto(StringProperty nombre, StringProperty apellido) {
        // GUÍA: teoría 2.4 (StringExpression.concat encadena texto observable).
        // 1. return nombre.concat(" ").concat(apellido).getValue();
        // PISTA: concat acepta tanto literales (" ") como otras properties.
        // OJO: el resultado es un binding de String; getValue() lee su texto actual.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreCompleto");
    }

    /**
     * Reto Extra 6: ¿Mayor que un umbral?
     * Devuelve si la property numérica supera el umbral, usando un binding booleano.
     */
    public static boolean mayorQue(IntegerProperty numero, int umbral) {
        // GUÍA: teoría 2.4 (greaterThan/lessThan crean BooleanBinding).
        // 1. return numero.greaterThan(umbral).get();
        // OJO: greaterThan es ESTRICTO (>); si numero == umbral, da false. El test lo prueba.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mayorQue");
    }

    /**
     * Reto Extra 7: Longitud observable de un texto.
     * Devuelve la longitud del texto de la property mediante length().
     */
    public static int longitud(StringProperty texto) {
        // GUÍA: teoría 2.4 (StringExpression.length() crea un IntegerBinding).
        // 1. return texto.length().get();
        // OJO: este length() es el de la EXPRESIÓN observable (no String.length()). En Ej270 lo
        //   usarás para "la clave debe tener > 7 caracteres".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para longitud");
    }

    /**
     * Reto Extra 8: Una property enlazada no se puede asignar.
     * Enlaza 'destino' a 'fuente', intenta hacerle set() y devuelve si saltó la excepción.
     */
    public static boolean intentarAsignarEnlazada(IntegerProperty fuente) {
        // GUÍA: teoría 2.4 (Trampa: tras bind(), set() lanza RuntimeException "bound value cannot be set").
        // 1. Crea un destino IntegerProperty. 2. destino.bind(fuente).
        // 3. try { destino.set(99); return false; } catch (RuntimeException e) { return true; }
        // OJO: una property con bind() es de SOLO LECTURA; su valor solo sale de la fuente.
        //   Para volver a asignarla a mano hay que unbind() primero (reto/te lo cuenta la teoría).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para intentarAsignarEnlazada");
    }

    /**
     * Reto Extra 9: Negar una property booleana.
     * Devuelve el valor contrario de la property mediante not().
     */
    public static boolean negar(BooleanProperty bandera) {
        // GUÍA: teoría 2.4 (BooleanExpression.not() crea el binding contrario).
        // 1. return bandera.not().get();
        // OJO: not() NO cambia la property original; crea un binding que vale su contrario.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para negar");
    }

    /**
     * Reto Extra 10: Cadena de dependencias recalculada.
     * Define {@code total = base*2 + 5} como binding, cambia 'base' después y devuelve el total nuevo.
     */
    public static int cadenaDependencias(int baseInicial, int baseNueva) {
        // GUÍA: teoría 2.4 (un binding puede depender de otro; toda la cadena se recalcula sola).
        // 1. IntegerProperty base = new SimpleIntegerProperty(baseInicial).
        // 2. Crea el binding encadenado: base.multiply(2).add(5). Guárdalo en una variable.
        // 3. Cambia base.set(baseNueva). 4. Devuelve el binding .intValue().
        // PISTA: NumberBinding total = base.multiply(2).add(5);
        // OJO: el test pone baseInicial=10 y baseNueva=20 -> 20*2+5 = 45 (recalculado, no 25).
        // CULTURA: esto es EXACTAMENTE una hoja de cálculo: la celda "=A1*2+5" se actualiza sola
        //   cuando cambias A1. Es también el "estado derivado" de React/Vue. Nunca recalculas a mano.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cadenaDependencias");
    }

    /** Helper de demostración para el Playground/tests del alumno. */
    static StringProperty propiedadDe(String valor) {
        return new SimpleStringProperty(valor);
    }
}
