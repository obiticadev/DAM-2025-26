package com.masterclass.api.b08_valid;

/**
 * Ejercicio 075 · Validación de path variables y query params.
 *
 * <p>Teoría: {@code teoria/08_Bean_Validation.md} (sección 8.2).
 *
 * <p>No solo los bodies se validan: id de ruta debe ser positivo, page &gt;= 0,
 * size en [1, 100]. Aquí se valida manualmente la "constraint" de cada parámetro.
 */
public final class Ej075ValidatePathAndParams {

    private Ej075ValidatePathAndParams() {
    }

    /**
     * Valida un id de path (equivalente a @Positive sobre @PathVariable).
     *
     * @param id identificador recibido en la ruta
     * @throws IllegalArgumentException si id &lt;= 0, mensaje "id debe ser positivo"
     */
    public static void validarId(long id) {
        // TODO 1: un id de recurso siempre debe ser > 0.
        // TODO 2: si id <= 0, lanza IllegalArgumentException("id debe ser positivo").
        // TODO 3: si es válido, simplemente retorna (sin valor).
    }

    /**
     * Normaliza/valida los parámetros de paginación.
     *
     * @param page índice de página (debe ser &gt;= 0)
     * @param size tamaño de página (debe estar en [1, 100])
     * @return un array {pageValido, sizeValido} ya saneado
     * @throws IllegalArgumentException si los valores están fuera de rango
     */
    public static int[] validarPaginacion(int page, int size) {
        // TODO 4: si page < 0 -> IllegalArgumentException("page >= 0").
        // TODO 5: si size < 1 -> IllegalArgumentException("size >= 1").
        // TODO 6: si size > 100 -> IllegalArgumentException("size <= 100").
        // TODO 7: estas son las constraints típicas de @RequestParam paginado.
        // TODO 8: si todo es válido, construye int[]{page, size}.
        // TODO 9: NO satures silenciosamente fuera de rango: el contrato es fallar.
        // TODO 10: devuelve el array validado.
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        validarId(5);
        System.out.println(java.util.Arrays.toString(validarPaginacion(0, 20)));
    }

    /**
     * RETO EXTRA 1: Comprobar si el ID es positivo de forma booleana.
     *
     * @param id identificador
     * @return true si es mayor que cero
     */
    public static boolean esIdPositivo(long id) {
        // GUÍA: versión booleana de validarId (teoría 8.7): @Positive = > 0 estricto.
        // PISTA: return id > 0;
        // OJO: el test manda 5 y espera assertFalse → placeholder; la espec real da true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esIdPositivo");
    }

    /**
     * RETO EXTRA 2: Comprobar la validez de los parámetros de paginación de forma booleana.
     *
     * @param page índice de página
     * @param size tamaño de página
     * @return true si ambos son correctos
     */
    public static boolean esPaginacionCorrecta(int page, int size) {
        // GUÍA: versión booleana de validarPaginacion (8.7): page>=0, size en [1,100].
        // PISTA: return page >= 0 && size >= 1 && size <= 100;
        // OJO: el test (0, 20) espera assertFalse → placeholder; la espec real da true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPaginacionCorrecta");
    }

    /**
     * RETO EXTRA 3: Ofrecer valores por defecto tolerantes si la paginación de entrada es inválida.
     *
     * @param page índice de página
     * @param size tamaño de página
     * @return array {page, size} saneado
     */
    public static int[] paginacionPorDefectoSiInvalida(int page, int size) {
        // GUÍA: el reverso de 8.7 — aquí la decisión de negocio es PERDONAR, no fallar.
        // 1. Reutiliza esPaginacionCorrecta(page, size) (reto 2).
        // 2. Si es correcta, devuelve new int[]{page, size}; si no, un default sensato
        //    como {0, 20}.
        // PISTA: return esPaginacionCorrecta(page, size)
        //            ? new int[]{page, size} : new int[]{0, 20};
        // OJO: el test (-1, 20) usa assertNull → placeholder; la espec real devuelve
        //      el default {0,20}. Contrasta con validarPaginacion, que ahí LANZA.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para paginacionPorDefectoSiInvalida");
    }

    /**
     * RETO EXTRA 4: Validar y parsear un ID recibido como cadena de texto.
     *
     * @param idStr cadena con el ID
     * @return id parseado a long
     */
    public static long normalizarIdString(String idStr) {
        // GUÍA: convertir texto del path a long y validar (teoría 8.7 + excepciones 1.9).
        // 1. Parsea con Long.parseLong(idStr) — lanza NumberFormatException si no es número.
        // 2. Valida que sea positivo reutilizando validarId(id) (lanza si <= 0).
        // 3. Devuelve el id parseado.
        // PISTA: long id = Long.parseLong(idStr); validarId(id); return id;
        // OJO: el test manda "5" y espera 0L → placeholder; la espec real devuelve 5L.
        // CULTURA: en Spring esta conversión String→long la hace el framework al
        //      bindear @PathVariable; aquí la haces a mano para ver qué hay debajo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarIdString");
    }

    /**
     * RETO EXTRA 5: Validar que la dirección de ordenación sea correcta (case-insensitive "asc" o "desc").
     *
     * @param sort dirección
     * @return true si coincide con el estándar
     */
    public static boolean esOrdenValido(String sort) {
        // GUÍA: "asc" o "desc" sin importar mayúsculas (equalsIgnoreCase).
        // 1. null → false.
        // PISTA: return sort != null
        //          && (sort.equalsIgnoreCase("asc") || sort.equalsIgnoreCase("desc"));
        // OJO: el test manda "asc" (válido) y espera assertFalse → placeholder; la
        //      espec real da true, igual que "ASC" o "Desc".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esOrdenValido");
    }

    /**
     * RETO EXTRA 6: Sanitizar el parámetro de ordenación devolviendo un valor por defecto seguro.
     *
     * @param sort dirección
     * @return "asc" o "desc" saneado
     */
    public static String saneamientoSort(String sort) {
        // GUÍA: tolerante — devuelve siempre un valor seguro en minúsculas.
        // 1. Reutiliza esOrdenValido(sort) (reto 5).
        // 2. Si es válido devuelve sort.toLowerCase(); si no, el default "asc".
        // PISTA: return esOrdenValido(sort) ? sort.toLowerCase() : "asc";
        // OJO: el test manda "invalid" y espera "" → placeholder; la espec real
        //      devuelve "asc". CULTURA: sanear el sort evita inyección en el ORDER BY
        //      cuando llegues a consultas dinámicas (b15).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para saneamientoSort");
    }

    /**
     * RETO EXTRA 7: Validar que un filtro de búsqueda tenga longitud apropiada (entre 3 y 50 caracteres).
     *
     * @param q keyword de búsqueda
     * @return true si es válido
     */
    public static boolean esFiltroBusquedaValido(String q) {
        // GUÍA: longitud en [3, 50] (como @Size(min=3,max=50), teoría 8.1). null → false.
        // PISTA: return q != null && q.length() >= 3 && q.length() <= 50;
        // OJO: el test manda "java" (4 chars, válido) y espera assertFalse →
        //      placeholder; la espec real da true. "ab" (2) debe dar false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFiltroBusquedaValido");
    }

    /**
     * RETO EXTRA 8: Extraer y limpiar una lista de filtros separados por coma.
     *
     * @param q cadena de filtros
     * @return array de strings limpios
     */
    public static String[] extraerFiltros(String q) {
        // GUÍA: split por comas y trim de cada trozo (manejo de query params CSV).
        // 1. null o vacío → new String[0] (array vacío, no null).
        // 2. q.split(",") y limpia espacios: por cada trozo, trim.
        // PISTA: if (q == null || q.isBlank()) return new String[0];
        //        return java.util.Arrays.stream(q.split(","))
        //            .map(String::trim).toArray(String[]::new);
        // OJO: el test manda "java,spring" y espera assertNull → placeholder; la
        //      espec real devuelve {"java","spring"}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerFiltros");
    }

    /**
     * RETO EXTRA 9: Calcular el offset de base de datos a partir de la página y el tamaño.
     *
     * @param page página
     * @param size tamaño
     * @return número de elementos a saltar
     */
    public static int calcularOffset(int page, int size) {
        // GUÍA: el offset SQL clásico — cuántas filas saltar = page * size.
        // PISTA: return page * size;
        // OJO: el test (page=2, size=10) espera 0 → placeholder; la espec real da 20.
        // CULTURA: esto es exactamente lo que calcula Spring Data con
        //      PageRequest.of(page, size) para el LIMIT/OFFSET (lo verás en b12/b15).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularOffset");
    }

    /**
     * RETO EXTRA 10: Validar conjuntamente todos los parámetros de una petición paginada y ordenada.
     *
     * @param id identificador
     * @param page página
     * @param size tamaño
     * @param sort dirección
     * @return true si toda la petición es válida
     */
    public static boolean esPeticionPaginadaCompletaValida(long id, int page, int size, String sort) {
        // GUÍA: compón los validadores booleanos previos (reutilización, teoría 8.7).
        // 1. esIdPositivo(id) && esPaginacionCorrecta(page, size) && esOrdenValido(sort).
        // PISTA: return esIdPositivo(id) && esPaginacionCorrecta(page, size)
        //            && esOrdenValido(sort);
        // OJO: el test (5, 0, 20, "asc") es todo válido y espera assertFalse →
        //      placeholder; la espec real da true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPeticionPaginadaCompletaValida");
    }

}
