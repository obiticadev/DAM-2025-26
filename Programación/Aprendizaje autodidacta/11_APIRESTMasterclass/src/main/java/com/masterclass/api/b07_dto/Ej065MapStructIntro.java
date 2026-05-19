package com.masterclass.api.b07_dto;

import java.util.function.Function;

/**
 * Ejercicio 065 · Mapper "declarativo" (idea de MapStruct sin la librería).
 *
 * <p>Teoría: {@code teoria/07_DTOs_y_Mapeo.md} (sección 7.3).
 *
 * <p>MapStruct genera mappers en compilación. Aquí simulamos la idea: un
 * registro de funciones de mapeo reutilizables y componibles.
 */
public final class Ej065MapStructIntro {

    public record Origen(String a, int b) {
    }

    public record Destino(String a, int bDoble) {
    }

    private Ej065MapStructIntro() {
    }

    /**
     * Devuelve una función de mapeo declarativa Origen -> Destino.
     *
     * @return función que copia 'a' y duplica 'b' en 'bDoble'
     */
    public static Function<Origen, Destino> mapper() {
        // TODO 1: declara una lambda Function<Origen,Destino>.
        // TODO 2: en ella, valida que el origen no sea null.
        // TODO 3: mapea campo 'a' directamente (mismo nombre/tipo).
        // TODO 4: mapea 'b' a 'bDoble' aplicando *2 (transformación).
        // TODO 5: construye y devuelve el Destino dentro de la lambda.
        // TODO 6: devuelve la propia función (declarativa, reutilizable).
        return null;
    }

    /**
     * Compone el mapper con un post-procesado que pone 'a' en mayúsculas.
     *
     * @return función Origen -> Destino con 'a' en mayúsculas
     */
    public static Function<Origen, Destino> mapperMayus() {
        // TODO 7: parte de mapper() (no reescribas el mapeo base).
        // TODO 8: usa andThen para post-procesar el Destino.
        // TODO 9: en el post, crea un Destino con a.toUpperCase() y el mismo bDoble.
        // TODO 10: devuelve la función compuesta.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(mapper().apply(new Origen("x", 5)));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: declara una lambda Function<Origen,Destino>.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: en ella, valida que el origen no sea null.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: mapea campo 'a' directamente (mismo nombre/tipo).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: mapea 'b' a 'bDoble' aplicando *2 (transformación).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: construye y devuelve el Destino dentro de la lambda.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: devuelve la propia función (declarativa, reutilizable).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: parte de mapper() (no reescribas el mapeo base).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: usa andThen para post-procesar el Destino.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: en el post, crea un Destino con a.toUpperCase() y el mismo bDoble.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve la función compuesta.
    }

}
