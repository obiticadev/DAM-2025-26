package com.masterclass.api.b00_http;

/**
 * Ejercicio 008 · Modelado de recursos REST.
 *
 * <p>Teoría: {@code teoria/00_Fundamentos_HTTP_Web.md} (sección 0.3).
 *
 * <p>Las rutas REST usan sustantivos en plural y jerarquía padre/hijo. Aquí
 * construyes rutas canónicas a partir de nombres de recurso e identificadores.
 */
public final class Ej008RestResourceModeler {

    private Ej008RestResourceModeler() {
    }

    /**
     * Ruta de colección: {@code /recursos}.
     *
     * @param resource nombre del recurso en plural (p.ej. "pedidos")
     * @return {@code "/pedidos"}
     * @throws IllegalArgumentException si resource es null o vacío
     */
    public static String collection(String resource) {
        // TODO 1: si resource es null, lanza IllegalArgumentException.
        // TODO 2: si resource está en blanco (vacío o solo espacios), lanza IllegalArgumentException.
        // TODO 3: recorta espacios sobrantes de 'resource'.
        // TODO 4: devuelve "/" + resource.
        return "";
    }

    /**
     * Ruta de elemento: {@code /recursos/{id}}.
     *
     * @param resource nombre del recurso en plural
     * @param id       identificador del elemento
     * @return {@code "/pedidos/42"}
     */
    public static String item(String resource, Object id) {
        // TODO 5: si id es null, lanza IllegalArgumentException (un elemento necesita id).
        // TODO 6: reutiliza collection(resource) para no duplicar validación/formato.
        // TODO 7: concatena "/" + id (usa String.valueOf para Object).
        return "";
    }

    /**
     * Ruta de subrecurso anidado: {@code /padres/{id}/hijos}.
     *
     * @param parent   recurso padre en plural
     * @param parentId id del padre
     * @param child    recurso hijo en plural
     * @return {@code "/pedidos/42/lineas"}
     */
    public static String nested(String parent, Object parentId, String child) {
        // TODO 8: valida 'child' igual que un recurso (null/blank -> IllegalArgumentException).
        // TODO 9: reutiliza item(parent, parentId) para construir el prefijo del padre.
        // TODO 10: añade "/" + child recortado y devuelve la ruta completa.
        return "";
    }

    public static void main(String[] args) {
        System.out.println(collection("pedidos"));
        System.out.println(item("pedidos", 42));
        System.out.println(nested("pedidos", 42, "lineas"));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si resource es null, lanza IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si resource está en blanco (vacío o solo espacios), lanza IllegalArgumentException.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: recorta espacios sobrantes de 'resource'.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: devuelve "/" + resource.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: si id es null, lanza IllegalArgumentException (un elemento necesita id).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: reutiliza collection(resource) para no duplicar validación/formato.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: concatena "/" + id (usa String.valueOf para Object).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: valida 'child' igual que un recurso (null/blank -> IllegalArgumentException).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: reutiliza item(parent, parentId) para construir el prefijo del padre.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: añade "/" + child recortado y devuelve la ruta completa.
    }

}
