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
}
