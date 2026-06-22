package com.masterclass.api.b33_fxcontrols;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 * Ejercicio 268 · Expresiones de binding: {@code Bindings.when/concat/createStringBinding} (fluent).
 *
 * <p>Teoría: {@code teoria/33_JavaFX_Controles_Binding.md} (sección 2.6).
 *
 * <p>Las operaciones de {@link Bindings} y las "fluent" (when/then/otherwise, concat, format…) te
 * dejan escribir fórmulas declarativas complejas que se recalculan solas. Es el SQL/Excel del
 * modelo reactivo. Lógica pura: molde estándar.
 */
public final class Ej268BindingsExpressions {

    private Ej268BindingsExpressions() {
    }

    /**
     * Etiqueta de estado de un saldo: "Rojo" si es negativo, "OK" en caso contrario,
     * expresado con {@code Bindings.when(...).then(...).otherwise(...)}.
     *
     * @param saldo saldo a evaluar
     * @return "Rojo" o "OK"; {@code ""} sin implementar
     */
    public static String etiquetaEstadoSaldo(int saldo) {
        // TODO 1: crea IntegerProperty saldoProp = new SimpleIntegerProperty(saldo).
        // TODO 2: empieza la expresión condicional: Bindings.when(saldoProp.lessThan(0)).
        // TODO 3: .then("Rojo") para la rama verdadera.
        // TODO 4: .otherwise("OK") para la rama falsa (queda un StringBinding).
        // TODO 5: devuelve ese binding con .get().
        return "";
    }

    /**
     * Mensaje del carrito según el nº de unidades, calculado con {@code Bindings.createStringBinding}.
     *
     * @param unidades número de artículos en el carrito
     * @return "vacío" si 0; si no, "<n> artículos"; {@code ""} sin implementar
     */
    public static String mensajeCarrito(int unidades) {
        // TODO 6: crea IntegerProperty u = new SimpleIntegerProperty(unidades).
        // TODO 7: crea el binding con Bindings.createStringBinding(() -> { ... }, u).
        // TODO 8: dentro de la lambda, si u.get()==0 devuelve "vacío".
        // TODO 9: si no, devuelve u.get() + " artículos".
        // TODO 10: pásale 'u' como dependencia (último argumento) y devuelve el binding .get().
        return "";
    }

    public static void main(String[] args) {
        System.out.println("Saldo -5: " + etiquetaEstadoSaldo(-5));
        System.out.println("Carrito 3: " + mensajeCarrito(3));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Concatenar properties con separador.
     * Devuelve "a-b" usando {@code Bindings.concat}.
     */
    public static String concatenar(StringProperty a, StringProperty b) {
        // GUÍA: teoría 2.6 (Bindings.concat une trozos observables y literales).
        // 1. return Bindings.concat(a, "-", b).getValue();
        // OJO: concat acepta properties y literales mezclados; el resultado es un StringExpression.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para concatenar");
    }

    /**
     * Reto Extra 2: Selección ternaria observable.
     * Devuelve 'siVerdad' o 'siFalso' según la {@link BooleanProperty}, con when/then/otherwise.
     */
    public static String seleccionTernaria(BooleanProperty condicion, String siVerdad, String siFalso) {
        // GUÍA: teoría 2.6 (Bindings.when es el "?:" del mundo observable).
        // 1. return Bindings.when(condicion).then(siVerdad).otherwise(siFalso).get();
        // OJO: si la condición cambiara, el binding cambiaría solo; aquí leemos el valor actual.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para seleccionTernaria");
    }

    /**
     * Reto Extra 3: Máximo observable.
     * Devuelve el mayor de dos properties con {@code Bindings.max}.
     */
    public static int maximo(IntegerProperty a, IntegerProperty b) {
        // GUÍA: teoría 2.6 (Bindings tiene max/min/add/subtract/multiply/divide).
        // 1. return Bindings.max(a, b).intValue();
        // OJO: max/min existen también como métodos fluent (a.add(b)…); aquí usas la forma estática.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para maximo");
    }

    /**
     * Reto Extra 4: Igualdad observable.
     * Indica si la property numérica es igual a 'n' con {@code Bindings.equal}.
     */
    public static boolean iguales(IntegerProperty a, int n) {
        // GUÍA: teoría 2.6 (Bindings.equal/notEqual/greaterThan… devuelven BooleanBinding).
        // 1. return Bindings.equal(a, n).get();
        // OJO: para enteros equal es exacto; para double existe equal(a, b, tolerancia).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para iguales");
    }

    /**
     * Reto Extra 5: Tamaño observable de una lista.
     * Devuelve el número de elementos de la {@link ObservableList} con {@code Bindings.size}.
     */
    public static int tamanoLista(ObservableList<String> lista) {
        // GUÍA: teoría 2.6 (Bindings.size crea un IntegerBinding que sigue al tamaño de la lista).
        // 1. return Bindings.size(lista).get();
        // OJO: si añadieras/quitaras elementos, el binding cambiaría; aquí lees el tamaño actual.
        //   Esto es clave para "habilitar Guardar solo si hay filas" en TableView (b35).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanoLista");
    }

    /**
     * Reto Extra 6: ¿Lista vacía?
     * Indica si la {@link ObservableList} está vacía con {@code Bindings.isEmpty}.
     */
    public static boolean listaVacia(ObservableList<String> lista) {
        // GUÍA: teoría 2.6 (Bindings.isEmpty/isNotEmpty para listas observables).
        // 1. return Bindings.isEmpty(lista).get();
        // OJO: el test prueba lista vacía (true) y lista con elementos (false).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para listaVacia");
    }

    /**
     * Reto Extra 7: Formatear con plantilla.
     * Devuelve "Total: <n>" usando {@code Bindings.format}.
     */
    public static String formatearEtiqueta(IntegerProperty cantidad) {
        // GUÍA: teoría 2.6 (Bindings.format es como String.format pero observable).
        // 1. return Bindings.format("Total: %d", cantidad).get();
        // OJO: usamos %d (entero) para evitar el problema del separador decimal según el idioma
        //   (en España %.2f de 3.14 da "3,14"). Para decimales fija el Locale: Bindings.format(Locale.US, ...).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearEtiqueta");
    }

    /**
     * Reto Extra 8: Binding calculado con varias dependencias.
     * Devuelve "<nombre> (<edad>)" recalculado con {@code createStringBinding} sobre dos properties.
     */
    public static String fichaPersona(StringProperty nombre, IntegerProperty edad) {
        // GUÍA: teoría 2.6 (createStringBinding(lambda, dep1, dep2, …): tú escribes el cálculo).
        // 1. return Bindings.createStringBinding(() -> nombre.get() + " (" + edad.get() + ")", nombre, edad).get();
        // PISTA: pásale TODAS las properties que usas dentro como dependencias, o no se recalculará.
        // OJO: olvidar una dependencia es el error #1 de createStringBinding: el binding se "queda pillado".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para fichaPersona");
    }

    /**
     * Reto Extra 9: Valor en un índice observable.
     * Devuelve el elemento de la lista en la posición que marca 'indice' con {@code Bindings.valueAt}.
     */
    public static String valorEnIndice(ObservableList<String> lista, IntegerProperty indice) {
        // GUÍA: teoría 2.6 (Bindings.valueAt(lista, indiceObservable) sigue a la celda i-ésima).
        // 1. return Bindings.valueAt(lista, indice).getValue();
        // OJO: si el índice se sale de rango, el binding vale null (no lanza excepción).
        //   El test usa una lista de 3 e indice=1 -> el segundo elemento.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para valorEnIndice");
    }

    /**
     * Reto Extra 10: Expresión compleja anidada (semáforo de saldo).
     * Devuelve "NEGATIVO" si saldo<0, "ALTO" si saldo>1000, "NORMAL" en otro caso, con when anidados.
     */
    public static String semaforoSaldo(int saldo) {
        // GUÍA: teoría 2.6 (los when se anidan en el otherwise para más de dos casos).
        // 1. IntegerProperty s = new SimpleIntegerProperty(saldo).
        // 2. StringBinding b = Bindings.when(s.lessThan(0)).then("NEGATIVO")
        //        .otherwise(Bindings.when(s.greaterThan(1000)).then("ALTO").otherwise("NORMAL"));
        // 3. return b.get();
        // PISTA: el segundo argumento de otherwise puede ser OTRA expresión when (anidación).
        // OJO: el orden importa: primero el caso negativo; el test prueba -5, 2000 y 500.
        // CULTURA: esto es exactamente una fórmula =SI(A1<0;"NEGATIVO";SI(A1>1000;"ALTO";"NORMAL"))
        //   de una hoja de cálculo, o un CASE WHEN de SQL, pero VIVO: si cambia el saldo, cambia el texto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para semaforoSaldo");
    }
}
