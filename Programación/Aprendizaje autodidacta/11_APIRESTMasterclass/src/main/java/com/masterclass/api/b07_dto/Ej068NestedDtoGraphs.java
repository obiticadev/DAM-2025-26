package com.masterclass.api.b07_dto;

import java.util.List;

/**
 * Ejercicio 068 · Grafos de DTOs anidados.
 *
 * <p>Teoría: {@code teoria/07_DTOs_y_Mapeo.md} (sección 7.1).
 */
public final class Ej068NestedDtoGraphs {

    public record DireccionEntity(String calle, String ciudad) {
    }

    public record ClienteEntity(Long id, String nombre, DireccionEntity direccion, List<String> telefonos) {
    }

    public record DireccionDto(String calle, String ciudad) {
    }

    public record ClienteDto(Long id, String nombre, DireccionDto direccion, int numTelefonos) {
    }

    private Ej068NestedDtoGraphs() {
    }

    /**
     * Mapea el grafo completo Cliente→Direccion a su DTO.
     *
     * @param e entidad cliente con dirección anidada
     * @return DTO con dirección mapeada y conteo de teléfonos
     * @throws IllegalArgumentException si e es null
     */
    public static ClienteDto toDto(ClienteEntity e) {
        // TODO 1: si e es null -> IllegalArgumentException.
        // TODO 2: mapea la dirección anidada por separado (sub-mapper mental).
        // TODO 3: si e.direccion() es null, el DTO de dirección debe ser null (no NPE).
        // TODO 4: si no es null, crea DireccionDto con calle y ciudad.
        // TODO 5: copia id y nombre del cliente.
        // TODO 6: calcula numTelefonos: tamaño de la lista (0 si la lista es null).
        // TODO 7: NO expongas la List de teléfonos: solo su conteo (decisión de contrato).
        // TODO 8: ensambla el ClienteDto con la DireccionDto anidada.
        // TODO 9: respeta la inmutabilidad (records nuevos, sin mutar la entidad).
        // TODO 10: devuelve el ClienteDto.
        return null;
    }

    public static void main(String[] args) {
        var e = new ClienteEntity(1L, "Ana", new DireccionEntity("C/ Mayor", "Madrid"), List.of("600"));
        System.out.println(toDto(e));
    }

    /**
     * RETO EXTRA 1: Obtener una lista de nombres de ciudades de todos los clientes.
     *
     * @param clientes lista de entidades de cliente
     * @return lista de nombres de ciudades (sin duplicados, ignorando nulos)
     */
    public static java.util.List<String> mapearCiudades(java.util.List<ClienteEntity> clientes) {
        // TODO extra: RETO EXTRA 1: Obtener una lista de nombres de ciudades de todos los clientes.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearCiudades");
    }

    /**
     * RETO EXTRA 2: Filtrar clientes por una ciudad concreta.
     *
     * @param clientes lista de clientes
     * @param ciudad nombre de la ciudad a filtrar
     * @return clientes de esa ciudad
     */
    public static java.util.List<ClienteEntity> filtrarPorCiudad(java.util.List<ClienteEntity> clientes, String ciudad) {
        // TODO extra: RETO EXTRA 2: Filtrar clientes por una ciudad concreta.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para filtrarPorCiudad");
    }

    /**
     * RETO EXTRA 3: Recopilar todos los números de teléfono del listado de clientes de forma global.
     *
     * @param clientes lista de clientes
     * @return conjunto de todos los teléfonos únicos
     */
    public static java.util.Set<String> obtenerTodosTelefonos(java.util.List<ClienteEntity> clientes) {
        // TODO extra: RETO EXTRA 3: Recopilar todos los números de teléfono del listado de clientes de forma global.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerTodosTelefonos");
    }

    /**
     * RETO EXTRA 4: Comprobar si el cliente reside en Madrid.
     *
     * @param e cliente
     * @return true si la ciudad es "Madrid" (insensible a mayúsculas/minúsculas)
     */
    public static boolean esDeMadrid(ClienteEntity e) {
        // TODO extra: RETO EXTRA 4: Comprobar si el cliente reside en Madrid.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esDeMadrid");
    }

    /**
     * RETO EXTRA 5: Crear un DTO de Cliente omitiendo su dirección física.
     *
     * @param e cliente original
     * @return DTO con direccion = null
     */
    public static ClienteDto crearClienteSinDireccion(ClienteEntity e) {
        // TODO extra: RETO EXTRA 5: Crear un DTO de Cliente omitiendo su dirección física.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearClienteSinDireccion");
    }

    /**
     * RETO EXTRA 6: Contar cuántos clientes no tienen una dirección registrada.
     *
     * @param clientes lista de clientes
     * @return conteo de clientes con dirección nula
     */
    public static long contarClientesSinDireccion(java.util.List<ClienteEntity> clientes) {
        // TODO extra: RETO EXTRA 6: Contar cuántos clientes no tienen una dirección registrada.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarClientesSinDireccion");
    }

    /**
     * RETO EXTRA 7: Mapear a DTO asegurando que la ciudad se guarda en mayúsculas.
     *
     * @param e cliente original
     * @return DTO con la ciudad en mayúsculas
     */
    public static ClienteDto capitalizarCiudad(ClienteEntity e) {
        // TODO extra: RETO EXTRA 7: Mapear a DTO asegurando que la ciudad se guarda en mayúsculas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para capitalizarCiudad");
    }

    /**
     * RETO EXTRA 8: Obtener el primer teléfono registrado de un cliente.
     *
     * @param e cliente
     * @return el primer teléfono, o "Sin Teléfono" si no tiene ninguno o la lista es nula
     */
    public static String telefonoPrincipal(ClienteEntity e) {
        // TODO extra: RETO EXTRA 8: Obtener el primer teléfono registrado de un cliente.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para telefonoPrincipal");
    }

    /**
     * RETO EXTRA 9: Determinar si el cliente es frecuente por poseer más de 3 teléfonos.
     *
     * @param e cliente
     * @return true si tiene > 3 teléfonos
     */
    public static boolean esClienteFrecuente(ClienteEntity e) {
        // TODO extra: RETO EXTRA 9: Determinar si el cliente es frecuente por poseer más de 3 teléfonos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esClienteFrecuente");
    }

    /**
     * RETO EXTRA 10: Devolver una copia modificada de ClienteDto con una nueva calle.
     *
     * @param dto DTO original
     * @param nuevaCalle nueva calle a asignar
     * @return nuevo DTO actualizado
     */
    public static ClienteDto actualizarCalle(ClienteDto dto, String nuevaCalle) {
        // TODO extra: RETO EXTRA 10: Devolver una copia modificada de ClienteDto con una nueva calle.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para actualizarCalle");
    }

}
