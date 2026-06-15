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

    /**
     * RETO EXTRA 1: Mapear una lista de elementos Origen a Destino.
     *
     * @param lista lista de origen
     * @return lista de destino mapeada
     */
    public static java.util.List<Destino> mapearLista(java.util.List<Origen> lista) {
        // GUÍA: teoría 7.3 (un mapper de lista = el mapper de elemento en un stream).
        // 1. Si lista es null -> devuelve List.of() (o lanza IAE; elige y sé coherente).
        // 2. Abre stream, aplica .map(mapper()) reutilizando la Function ya construida,
        //    y recoge a List.
        // PISTA: return lista.stream().map(mapper()).toList();
        // OJO: el test es PLACEHOLDER -> hoy hace assertNull(res) porque el método aún
        //      lanza. Cuando lo implementes devolverá una lista (no null) y tendrás que
        //      ajustar esa aserción a lo real (ej. assertEquals(2, res.size())).
        // CULTURA: NO reescribas el mapeo aquí; llama a mapper(). Esa reutilización es
        //      justo lo que MapStruct genera para los List<X> automáticamente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearLista");
    }

    /**
     * RETO EXTRA 2: Mapear y filtrar elementos con valores negativos.
     *
     * @param lista lista de origen
     * @return lista de destino filtrada
     */
    public static java.util.List<Destino> mapearConFiltro(java.util.List<Origen> lista) {
        // GUÍA: teoría 7.3 (filtra ANTES de mapear).
        // 1. Si lista es null -> List.of().
        // 2. Filtra los Origen con b negativo (o.b() >= 0) y luego mapea con mapper().
        // PISTA: return lista.stream().filter(o -> o.b() >= 0).map(mapper()).toList();
        // OJO: el test mete [("a",-1), ("b",2)]; al filtrar el negativo quedaría solo
        //      "b" (size 1). Es PLACEHOLDER (assertNull): ajústalo al resolver.
        //      Reutiliza mapper(); no copies la lógica de duplicar b.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearConFiltro");
    }

    /**
     * RETO EXTRA 3: Mapeo inverso de Destino a Origen.
     *
     * @param d destino
     * @return origen mapeado
     */
    public static Origen mapearInverso(Destino d) {
        // GUÍA: mapeo inverso (Destino -> Origen), deshaciendo el *2.
        // 1. Si d es null -> devuelve null.
        // 2. El campo a se copia tal cual; b se recupera dividiendo bDoble entre 2.
        // PISTA: return new Origen(d.a(), d.bDoble() / 2);
        // OJO: el test parte de Destino("x",10) -> Origen esperado ("x",5). Es
        //      PLACEHOLDER (assertNull); ajústalo al implementar. Como bDoble siempre
        //      es par por construcción, la división entera /2 es exacta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearInverso");
    }

    /**
     * RETO EXTRA 4: Mapear con valor por defecto.
     *
     * @param o origen
     * @param defecto valor por defecto para 'a'
     * @return destino con valor por defecto
     */
    public static Destino mapearConDefault(Origen o, String defecto) {
        // GUÍA: mapeo con default cuando el campo origen falta.
        // 1. Si o es null -> devuelve null.
        // 2. Resuelve a: si o.a() es null usa 'defecto'; si no, o.a(). El idiom limpio
        //    es Optional.ofNullable(o.a()).orElse(defecto) (bloque 1.2).
        // 3. bDoble = o.b() * 2.
        // PISTA: String a = java.util.Optional.ofNullable(o.a()).orElse(defecto);
        //        return new Destino(a, o.b() * 2);
        // OJO: el test usa Origen(null, 3) con defecto "defecto" -> esperado
        //      Destino("defecto", 6). Es PLACEHOLDER (assertNull); ajústalo al resolver.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearConDefault");
    }

    /**
     * RETO EXTRA 5: Mapeo con prefijo en campo string.
     *
     * @param o origen
     * @param prefijo prefijo a añadir a 'a'
     * @return destino
     */
    public static Destino mapearConPrefijo(Origen o, String prefijo) {
        // GUÍA: post-proceso del campo string (concatenar prefijo).
        // 1. Si o es null -> devuelve null.
        // 2. a = prefijo + o.a(); bDoble = o.b() * 2.
        // PISTA: return new Destino(prefijo + o.a(), o.b() * 2);
        // OJO: el test usa Origen("val",3) con prefijo "pre-" -> esperado
        //      Destino("pre-val", 6). PLACEHOLDER (assertNull). El prefijo va DELANTE.
        //      Compara con pasoExtra10 (mapearConSuffix): mismo patrón, sufijo detrás.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearConPrefijo");
    }

    /**
     * RETO EXTRA 6: Comprobar equivalencia lógica entre Origen y Destino.
     *
     * @param o origen
     * @param d destino
     * @return true si coinciden en 'a' y d.bDoble == o.b * 2
     */
    public static boolean esEquivalente(Origen o, Destino d) {
        // GUÍA: predicado de coherencia entre origen y destino.
        // 1. Si o o d son null -> devuelve false.
        // 2. Devuelve true si coinciden en 'a' (usa Objects.equals, tolera nulls) Y
        //    d.bDoble() == o.b() * 2.
        // PISTA: return java.util.Objects.equals(o.a(), d.a()) && d.bDoble() == o.b() * 2;
        // OJO: el test usa Origen("x",5) y Destino("x",10): son equivalentes (10==5*2)
        //      -> debería ser true. Es PLACEHOLDER (assertFalse); ajústalo al resolver.
        //      Usa == para los int (primitivos), equals para los String.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEquivalente");
    }

    /**
     * RETO EXTRA 7: Obtener el valor máximo de bDoble tras el mapeo.
     *
     * @param lista lista de origen
     * @return máximo bDoble, o 0 si la lista está vacía
     */
    public static int obtenerMaxBDoble(java.util.List<Origen> lista) {
        // GUÍA: reducción numérica sobre el resultado mapeado (bloque 1.3).
        // 1. Si lista es null o está vacía -> devuelve 0.
        // 2. Mapea cada Origen a su bDoble (= b*2) con mapToInt y toma el max.
        // 3. max() devuelve OptionalInt -> resuelve con orElse(0).
        // PISTA: return lista.stream().mapToInt(o -> o.b() * 2).max().orElse(0);
        // OJO: el test usa [("a",1),("b",10)] -> bDoble máximos 2 y 20 -> esperado 20.
        //      Es PLACEHOLDER (assertEquals(0,...)); ajústalo al resolver. El 0 solo
        //      es el valor legítimo para lista vacía.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerMaxBDoble");
    }

    /**
     * RETO EXTRA 8: Mapear solo el campo numérico.
     *
     * @param o origen
     * @return destino con 'a' nulo
     */
    public static Destino mapearSoloB(Origen o) {
        // GUÍA: mapeo parcial (solo el campo numérico, a queda null).
        // 1. Si o es null -> devuelve null.
        // 2. Devuelve un Destino con a = null y bDoble = o.b() * 2.
        // PISTA: return new Destino(null, o.b() * 2);
        // OJO: el test usa Origen("x",5) -> esperado Destino(null, 10). PLACEHOLDER
        //      (assertNull). Aquí el null en 'a' es intencionado (mapeo parcial), no
        //      un descuido.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearSoloB");
    }

    /**
     * RETO EXTRA 9: Mapeo seguro envolviendo en Optional.
     *
     * @param o origen
     * @return Optional con Destino si origen no es nulo
     */
    public static java.util.Optional<Destino> mapearConSafeCast(Origen o) {
        // GUÍA: teoría 7.3 + Optional como tipo de RETORNO (bloque 1.2).
        // 1. Si o es null -> devuelve Optional.empty().
        // 2. Si no, mapea con mapper() y envuelve en Optional.of(...).
        // 3. Idiom compacto: Optional.ofNullable(o).map(mapper()).
        // PISTA: return java.util.Optional.ofNullable(o).map(mapper());
        // OJO: el test pasa Origen("x",5) (no null) -> debería devolver un Optional
        //      CON valor (isPresent()). Es PLACEHOLDER (assertTrue(res.isEmpty()));
        //      ajústalo al resolver. Aquí Optional SÍ vale como retorno (no como campo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearConSafeCast");
    }

    /**
     * RETO EXTRA 10: Mapeo con sufijo en campo string.
     *
     * @param o origen
     * @param sufijo sufijo
     * @return destino
     */
    public static Destino mapearConSuffix(Origen o, String sufijo) {
        // GUÍA: gemelo de pasoExtra05 pero con el texto DETRÁS.
        // 1. Si o es null -> devuelve null.
        // 2. a = o.a() + sufijo; bDoble = o.b() * 2.
        // PISTA: return new Destino(o.a() + sufijo, o.b() * 2);
        // OJO: el test usa Origen("val",3) con sufijo "-post" -> esperado
        //      Destino("val-post", 6). PLACEHOLDER (assertNull). El sufijo va al final.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearConSuffix");
    }

}
