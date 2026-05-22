package com.masterclass.api.b19_test;

/**
 * Ejercicio 166 · Dobles de prueba: stub manual (concepto de Mockito).
 *
 * <p>Teoría: {@code teoria/19_Testing_APIs.md} (sección 19.3).
 *
 * <p>Mockito haría {@code when(repo.buscar(1)).thenReturn("Ada")}. Aquí
 * modelamos ese concepto con un stub PURO: {@link RepositorioStub166}
 * devuelve respuestas preconfiguradas. La pieza bajo prueba es el
 * servicio que lo consume.
 */
public final class Ej166MockitoMocks {

    private Ej166MockitoMocks() {
    }

    /**
     * Saluda al usuario cuyo id se resuelve contra un repositorio (stub).
     *
     * @param repo repositorio (en test será un stub) no null
     * @param id   id de usuario a resolver
     * @return "Hola, &lt;nombre&gt;" o "Hola, desconocido" si no existe
     * @throws IllegalArgumentException si repo es null
     */
    public static String saludar(RepositorioStub166 repo, int id) {
        // TODO 1: si repo es null -> IllegalArgumentException.
        // TODO 2: invoca repo.buscarNombre(id) (esto es la "interacción" mockeada).
        // TODO 3: si el resultado es null -> trata como usuario no encontrado.
        // TODO 4: si el resultado es blank -> trata como no encontrado también.
        // TODO 5: para no encontrado devuelve "Hola, desconocido".
        // TODO 6: para encontrado devuelve "Hola, " + nombre.
        // TODO 7: no asumas que el repo accede a BD real: es un doble de prueba.
        // TODO 8: no captures excepciones del repo: deja que propaguen.
        // TODO 9: la lógica de saludo es pura; el stub aísla la dependencia.
        // TODO 10: devuelve la cadena de saludo.
        return null;
    }

    public static void main(String[] args) {
        RepositorioStub166 stub = unused -> "Ada";
        System.out.println(saludar(stub, 1));
    }

        /**
     * RETO EXTRA 01: Obtiene longitud del nombre.
     */
    public static int longitudNombre(RepositorioStub166 repo, int id) {
        // TODO extra: RETO EXTRA 01: Obtiene longitud del nombre.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para longitudNombre");
    }

    /**
     * RETO EXTRA 02: Obtiene el nombre en mayusculas.
     */
    public static String nombreEnMayusculas(RepositorioStub166 repo, int id) {
        // TODO extra: RETO EXTRA 02: Obtiene el nombre en mayusculas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreEnMayusculas");
    }

    /**
     * RETO EXTRA 03: Determina si el nombre es valido.
     */
    public static boolean nombreValido(RepositorioStub166 repo, int id) {
        // TODO extra: RETO EXTRA 03: Determina si el nombre es valido.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreValido");
    }

    /**
     * RETO EXTRA 04: Saluda al usuario con un sufijo.
     */
    public static String saludoConSufijo(RepositorioStub166 repo, int id, String suf) {
        // TODO extra: RETO EXTRA 04: Saluda al usuario con un sufijo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para saludoConSufijo");
    }

    /**
     * RETO EXTRA 05: Comprueba si el nombre contiene una subcadena.
     */
    public static boolean contieneSubcadena(RepositorioStub166 repo, int id, String sub) {
        // TODO extra: RETO EXTRA 05: Comprueba si el nombre contiene una subcadena.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneSubcadena");
    }

    /**
     * RETO EXTRA 06: Determina si el usuario es un invitado.
     */
    public static boolean esInvitado(RepositorioStub166 repo, int id) {
        // TODO extra: RETO EXTRA 06: Determina si el usuario es un invitado.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esInvitado");
    }

    /**
     * RETO EXTRA 07: Devuelve el nombre invertido.
     */
    public static String saludoInverso(RepositorioStub166 repo, int id) {
        // TODO extra: RETO EXTRA 07: Devuelve el nombre invertido.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para saludoInverso");
    }

    /**
     * RETO EXTRA 08: Concatena el ID y el nombre.
     */
    public static String concatenarIdYNombre(RepositorioStub166 repo, int id) {
        // TODO extra: RETO EXTRA 08: Concatena el ID y el nombre.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para concatenarIdYNombre");
    }

    /**
     * RETO EXTRA 09: Comprueba si el nombre es largo.
     */
    public static boolean nombreLargo(RepositorioStub166 repo, int id) {
        // TODO extra: RETO EXTRA 09: Comprueba si el nombre es largo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreLargo");
    }

    /**
     * RETO EXTRA 10: Busca el nombre o lanza excepcion.
     */
    public static String buscarOError(RepositorioStub166 repo, int id) {
        // TODO extra: RETO EXTRA 10: Busca el nombre o lanza excepcion.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para buscarOError");
    }

}

/**
 * Doble de prueba (stub) puro: sustituye un repositorio real.
 */
@FunctionalInterface
interface RepositorioStub166 {
    /** @param id id a resolver @return nombre o null si no existe */
    String buscarNombre(int id);
}
