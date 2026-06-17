package com.masterclass.api.b15_query;

import java.util.List;

/**
 * Ejercicio 142 · Keyset pagination (cursor por id).
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.4).
 *
 * <p>En vez de OFFSET (lento con millones de filas), avanzas por "el último id visto".
 */
public final class Ej142KeysetPagination {

    private Ej142KeysetPagination() {
    }

    /**
     * Devuelve la siguiente página a partir del último id visto.
     *
     * @param idsOrdenados lista de ids YA ordenada ascendentemente (simula la tabla)
     * @param ultimoIdVisto id tras el cual continuar (null o 0 = desde el principio)
     * @param tamano       tamaño de página (&gt; 0)
     * @return sublista con hasta 'tamano' ids cuyo valor &gt; ultimoIdVisto
     * @throws IllegalArgumentException si tamano &lt;= 0 o la lista es null
     */
    public static List<Long> siguientePagina(List<Long> idsOrdenados, Long ultimoIdVisto, int tamano) {
        // TODO 1: si idsOrdenados es null -> IllegalArgumentException.
        // TODO 2: si tamano <= 0 -> IllegalArgumentException.
        // TODO 3: normaliza el cursor: si ultimoIdVisto es null, trátalo como Long.MIN_VALUE
        //         (o 0 si los ids son positivos) para empezar desde el principio.
        // TODO 4: filtra los ids estrictamente mayores que 'ultimoIdVisto'.
        // TODO 5: la lista ya viene ordenada: NO la reordenes.
        // TODO 6: limita el resultado a 'tamano' elementos (limit).
        // TODO 7: recoge a List.
        // TODO 8: keyset es estable: insertar filas nuevas no descoloca páginas previas.
        // TODO 9: si no hay más allá del cursor, devuelve lista vacía.
        // TODO 10: devuelve la sublista (la "siguiente página").
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println(siguientePagina(List.of(1L, 2L, 3L, 4L, 5L), 2L, 2));
    }

    /**
     * Reto Extra 1: Obtiene el ID de un item de Keyset de forma segura.
     */
    public static Long obtenerId(ItemKeyset142 i) {
        // GUÍA: teoría 15.4 (keyset). El id es el "cursor" por el que avanzas;
        //   acceso seguro a él.
        // 1. Una línea: return i == null ? null : i.getId();
        // OJO: el test espera null (un ItemKeyset142 nuevo no tiene id todavía).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerId");
    }

    /**
     * Reto Extra 2: Obtiene la fecha de creacion de forma segura.
     */
    public static java.time.Instant obtenerCreadoEn(ItemKeyset142 i) {
        // GUÍA: una línea — return i == null ? null : i.getCreadoEn();
        // El test espera null. CULTURA: en keyset real, un timestamp como
        //   creadoEn suele ser el segundo criterio de cursor (id + fecha) para
        //   desempatar filas con el mismo instante.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerCreadoEn");
    }

    /**
     * Reto Extra 3: Comprueba si un item es posterior a otro por ID.
     */
    public static boolean esPosteriorId(ItemKeyset142 a, ItemKeyset142 b) {
        // GUÍA: "posterior" = el id de 'a' es mayor que el de 'b' (la base del
        //   keyset: WHERE id > cursor).
        // 1. Defensa: si a, b o sus ids son null → false (no se puede comparar).
        // 2. return a.getId() > b.getId();
        // ⚠ OJO: el test pasa dos items SIN id (ambos null) y espera FALSE. Si no
        //   defiendes el null antes de comparar, te saltará un NPE. Por eso el
        //   caso null debe devolver false explícitamente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPosteriorId");
    }

    /**
     * Reto Extra 4: Crea un nuevo item.
     */
    public static ItemKeyset142 crearItem(String nombre) {
        // GUÍA: una línea — return new ItemKeyset142(nombre);
        // El test llama crearItem("Laptop") y comprueba assertNotNull.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearItem");
    }

    /**
     * Reto Extra 5: Comprueba si el item tiene fecha de creacion.
     */
    public static boolean tieneCreadoEn(ItemKeyset142 i) {
        // GUÍA: una línea — return i != null && i.getCreadoEn() != null;
        // OJO: el test espera FALSE (item nuevo, creadoEn aún null).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneCreadoEn");
    }

    /**
     * Reto Extra 6: Comprueba si el item es nuevo (ID nulo).
     */
    public static boolean esNuevo(ItemKeyset142 i) {
        // GUÍA: una línea — return i != null && i.getId() == null;
        // El test crea el item sin id → true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNuevo");
    }

    /**
     * Reto Extra 7: Obtiene el nombre de forma segura.
     */
    public static String obtenerNombre(ItemKeyset142 i) {
        // GUÍA: una línea — return i == null ? null : i.getNombre();
        // El test crea new ItemKeyset142("Laptop") y espera "Laptop".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerNombre");
    }

    /**
     * Reto Extra 8: Comprueba si el nombre del item contiene una palabra clave.
     */
    public static boolean nombreContiene(ItemKeyset142 i, String keyword) {
        // GUÍA: comparación "contiene" en minúsculas (como en Ej140/141).
        // 1. return i.getNombre().toLowerCase().contains(keyword.toLowerCase());
        // El test: nombre "Laptop", keyword "lap" → "laptop".contains("lap") = true.
        //   Defiende los nulos antes del contains.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreContiene");
    }

    /**
     * Reto Extra 9: Compara dos items por ID de forma segura.
     */
    public static int compararPorId(ItemKeyset142 a, ItemKeyset142 b) {
        // GUÍA: comparador clásico (-1/0/1) sobre los ids, tolerante a nulls.
        // 1. Si ambos ids son null → 0 (iguales). (Decide tú qué hacer si solo
        //    uno es null; el test no lo cubre.)
        // 2. Cuando haya valores: return Long.compare(a.getId(), b.getId());
        // ⚠ OJO: el test pasa dos items con id null y espera EXACTAMENTE 0. Sin la
        //   defensa del null, Long.compare con autounboxing de null da NPE.
        // PISTA: java.util.Objects.compare o un if previo para el caso ambos null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para compararPorId");
    }

    /**
     * Reto Extra 10: Retorna representacion estructurada de texto.
     */
    public static String formatearItem(ItemKeyset142 i) {
        // GUÍA: String.format con id y nombre.
        // PISTA: String.format("ItemKeyset[Id=%s, Nombre=%s]", i.getId(), i.getNombre());
        // OJO: el test espera EXACTAMENTE "ItemKeyset[Id=null, Nombre=Laptop]".
        //   %s sobre un id null imprime literalmente "null": justo lo que se pide.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearItem");
    }



}

/** Item con cursor para los retos extra (POJO, no entidad). id y creadoEn quedan null. */
class ItemKeyset142 {
    private Long id;
    private String nombre;
    private java.time.Instant creadoEn;

    public ItemKeyset142(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public java.time.Instant getCreadoEn() {
        return creadoEn;
    }
}
