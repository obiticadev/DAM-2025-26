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
        // GUÍA: teoría 7.6 (navegar el grafo con null-safety en cada nivel).
        // 1. Si clientes es null -> List.of().
        // 2. De cada cliente saca la dirección; descarta clientes con direccion null;
        //    extrae ciudad; quita duplicados con distinct().
        // PISTA: return clientes.stream()
        //          .map(ClienteEntity::direccion)
        //          .filter(java.util.Objects::nonNull)
        //          .map(DireccionEntity::ciudad)
        //          .distinct()
        //          .toList();
        // OJO: el test mete clientes de "Madrid" y "Barcelona" -> esperado 2 ciudades
        //      sin duplicados. Es PLACEHOLDER (assertNull); ajústalo al resolver.
        //      El filter de null va ANTES de pedir la ciudad, o saltaría NPE.
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
        // GUÍA: filtro navegando el anidado con guardia de null.
        // 1. Si clientes es null -> List.of().
        // 2. Quédate con los clientes cuya direccion no sea null Y su ciudad coincida
        //    con 'ciudad' (usa equals; reutiliza esDeMadrid como modelo de comparación).
        // PISTA: return clientes.stream()
        //          .filter(c -> c.direccion() != null
        //                    && c.direccion().ciudad().equals(ciudad))
        //          .toList();
        // OJO: el test filtra un cliente de "Madrid" por "Madrid" -> size 1. Es
        //      PLACEHOLDER (assertNull); ajústalo al resolver. Comprueba direccion no
        //      nula ANTES de leer ciudad (corto-circuito del &&).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para filtrarPorCiudad");
    }

    /**
     * RETO EXTRA 3: Recopilar todos los números de teléfono del listado de clientes de forma global.
     *
     * @param clientes lista de clientes
     * @return conjunto de todos los teléfonos únicos
     */
    public static java.util.Set<String> obtenerTodosTelefonos(java.util.List<ClienteEntity> clientes) {
        // GUÍA: teoría 7.6 (flatMap aplana List<Cliente>·List<Telefono>, bloque 1.4).
        // 1. Si clientes es null -> Set.of().
        // 2. De cada cliente toma su lista de teléfonos (si es null, trátala vacía con
        //    Optional o un filtro) y APLÁNALA con flatMap a un único stream de teléfonos.
        // 3. Recoge a un Set (únicos).
        // PISTA: return clientes.stream()
        //          .flatMap(c -> (c.telefonos() == null ? java.util.List.<String>of()
        //                          : c.telefonos()).stream())
        //          .collect(java.util.stream.Collectors.toSet());
        // OJO: el test mete un cliente con ["600"] -> Set {"600"}. Es PLACEHOLDER
        //      (assertNull); ajústalo al resolver. flatMap (1→muchos) NO es map (1→1):
        //      aquí cada cliente aporta VARIOS teléfonos a un solo stream plano.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerTodosTelefonos");
    }

    /**
     * RETO EXTRA 4: Comprobar si el cliente reside en Madrid.
     *
     * @param e cliente
     * @return true si la ciudad es "Madrid" (insensible a mayúsculas/minúsculas)
     */
    public static boolean esDeMadrid(ClienteEntity e) {
        // GUÍA: predicado navegando el anidado, insensible a mayúsculas.
        // 1. Si e es null o e.direccion() es null o su ciudad es null -> false.
        // 2. Devuelve true si la ciudad es "Madrid" con equalsIgnoreCase.
        // PISTA: return e != null && e.direccion() != null
        //          && "Madrid".equalsIgnoreCase(e.direccion().ciudad());
        // OJO: el test usa ciudad "Madrid" -> debería ser true. Es PLACEHOLDER
        //      (assertFalse); ajústalo al resolver. Usa equalsIgnoreCase (la espec dice
        //      insensible a may/min) y pon "Madrid" a la izquierda para tolerar null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esDeMadrid");
    }

    /**
     * RETO EXTRA 5: Crear un DTO de Cliente omitiendo su dirección física.
     *
     * @param e cliente original
     * @return DTO con direccion = null
     */
    public static ClienteDto crearClienteSinDireccion(ClienteEntity e) {
        // GUÍA: variante de toDto que omite el anidado (decisión de contrato, 7.6).
        // 1. Si e es null -> IllegalArgumentException.
        // 2. Crea un ClienteDto con direccion = null; copia id, nombre y el conteo de
        //    teléfonos (0 si la lista es null).
        // PISTA: int numTel = (e.telefonos() == null) ? 0 : e.telefonos().size();
        //        return new ClienteDto(e.id(), e.nombre(), null, numTel);
        // OJO: el test es PLACEHOLDER (assertNull); ajústalo al resolver. Aquí el null
        //      en direccion es DELIBERADO: estás recortando el grafo en el contrato,
        //      no es un descuido.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearClienteSinDireccion");
    }

    /**
     * RETO EXTRA 6: Contar cuántos clientes no tienen una dirección registrada.
     *
     * @param clientes lista de clientes
     * @return conteo de clientes con dirección nula
     */
    public static long contarClientesSinDireccion(java.util.List<ClienteEntity> clientes) {
        // GUÍA: filtro por null en el anidado + count.
        // 1. Si clientes es null -> devuelve 0.
        // 2. Cuenta los clientes cuya direccion() sea null.
        // PISTA: return clientes.stream().filter(c -> c.direccion() == null).count();
        // OJO: el test mete un cliente con direccion null -> esperado 1L. Es PLACEHOLDER
        //      (assertEquals(0,...)); ajústalo al resolver. count() devuelve long.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarClientesSinDireccion");
    }

    /**
     * RETO EXTRA 7: Mapear a DTO asegurando que la ciudad se guarda en mayúsculas.
     *
     * @param e cliente original
     * @return DTO con la ciudad en mayúsculas
     */
    public static ClienteDto capitalizarCiudad(ClienteEntity e) {
        // GUÍA: variante de toDto que transforma el campo del anidado.
        // 1. Si e es null -> IllegalArgumentException.
        // 2. Si hay direccion, crea DireccionDto con ciudad.toUpperCase() (calle igual);
        //    si direccion es null, deja la del DTO null.
        // 3. Reutiliza la lógica de toDto (id, nombre, conteo de teléfonos).
        // PISTA: DireccionDto dir = (e.direccion() == null) ? null
        //          : new DireccionDto(e.direccion().calle(),
        //                             e.direccion().ciudad().toUpperCase());
        // OJO: el test mete ciudad "madrid" -> en el DTO esperarías "MADRID". Es
        //      PLACEHOLDER (assertNull); ajústalo al resolver. No olvides el conteo de
        //      teléfonos null-safe (0 si la lista es null).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para capitalizarCiudad");
    }

    /**
     * RETO EXTRA 8: Obtener el primer teléfono registrado de un cliente.
     *
     * @param e cliente
     * @return el primer teléfono, o "Sin Teléfono" si no tiene ninguno o la lista es nula
     */
    public static String telefonoPrincipal(ClienteEntity e) {
        // GUÍA: acceso seguro al primer elemento de la lista anidada (bloque 1.2).
        // 1. Si e es null, su lista de teléfonos es null o está vacía -> "Sin Teléfono".
        // 2. Si hay al menos uno, devuelve telefonos().get(0).
        // PISTA: if (e == null || e.telefonos() == null || e.telefonos().isEmpty())
        //            return "Sin Teléfono";
        //        return e.telefonos().get(0);
        // OJO: el test usa ["600"] -> esperado "600". Es PLACEHOLDER (assertEquals("",...));
        //      ajústalo al resolver. El literal del caso por defecto es EXACTAMENTE
        //      "Sin Teléfono" (con tilde y esa capitalización), según la espec del Javadoc.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para telefonoPrincipal");
    }

    /**
     * RETO EXTRA 9: Determinar si el cliente es frecuente por poseer más de 3 teléfonos.
     *
     * @param e cliente
     * @return true si tiene > 3 teléfonos
     */
    public static boolean esClienteFrecuente(ClienteEntity e) {
        // GUÍA: predicado sobre el tamaño de la lista anidada.
        // 1. Si e es null o e.telefonos() es null -> false.
        // 2. Devuelve true si telefonos().size() > 3.
        // PISTA: return e != null && e.telefonos() != null && e.telefonos().size() > 3;
        // OJO: el test mete 4 teléfonos -> 4 > 3 -> debería ser true. Es PLACEHOLDER
        //      (assertFalse); ajústalo al resolver. El umbral es estricto (> 3): con
        //      exactamente 3 teléfonos NO es frecuente.
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
        // GUÍA: copia inmutable de un grafo -> cambia solo la calle del anidado (1.1).
        // 1. Si dto es null -> IllegalArgumentException.
        // 2. Crea una DireccionDto NUEVA con la nuevaCalle y la ciudad actual; luego
        //    un ClienteDto NUEVO con esa dirección y el resto de campos iguales.
        //    Cuidado si dto.direccion() es null (decide: crear con ciudad null o IAE).
        // PISTA: var dir = new DireccionDto(nuevaCalle, dto.direccion().ciudad());
        //        return new ClienteDto(dto.id(), dto.nombre(), dir, dto.numTelefonos());
        // OJO: el test parte de calle "Mayor"/"Madrid" y pide "Nueva Mayor": la ciudad
        //      "Madrid" debe conservarse. Es PLACEHOLDER (assertNull); ajústalo al
        //      resolver. Para cambiar el anidado hay que recrear DOS records (el de
        //      dirección y el de cliente): los records no se mutan.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para actualizarCalle");
    }

}
