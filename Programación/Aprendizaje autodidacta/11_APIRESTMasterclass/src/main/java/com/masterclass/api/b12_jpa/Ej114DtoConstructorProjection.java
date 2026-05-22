package com.masterclass.api.b12_jpa;

import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * Ejercicio 114 · Proyección por constructor en JPQL (select new Dto(...)).
 *
 * <p>Teoría: {@code teoria/12_Spring_Data_JPA.md} (sección 12.4).
 *
 * <p>No traigas la entidad entera si solo necesitas 2 campos: proyecta a un DTO.
 */
public final class Ej114DtoConstructorProjection {

    /** DTO de solo lectura (NO es entidad). */
    public record ResumenPedido(Long id, double total) {
    }

    private final EntityManager em;

    public Ej114DtoConstructorProjection(EntityManager em) {
        this.em = em;
    }

    /**
     * Devuelve un resumen (id, total) por pedido, sin cargar la entidad completa.
     *
     * @return lista de ResumenPedido ordenada por id
     */
    public List<ResumenPedido> resumen() {
        // TODO 1: el JPQL usa el nombre COMPLETO de la clase DTO en "select new".
        // TODO 2: "select new com.masterclass.api.b12_jpa.Ej114DtoConstructorProjection$ResumenPedido(
        //          p.id, p.total) from Pedido114 p order by p.id".
        // TODO 3: el DTO debe tener un constructor que case con los tipos/orden seleccionados.
        // TODO 4: crea la query con createQuery(jpql, ResumenPedido.class).
        // TODO 5: getResultList().
        // TODO 6: la proyección evita el overhead de materializar entidades completas.
        // TODO 7: el DTO NO está gestionado por el contexto de persistencia.
        // TODO 8: respeta el orden (ORDER BY p.id).
        // TODO 9: si no hay pedidos, devuelve lista vacía (no null).
        // TODO 10: devuelve la lista de ResumenPedido.
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * TODO extra 1: Comprueba si un repositorio JPA de tipo EmpleadoRepository está instanciado.
     */
    public static boolean desafioRepositoryActivo(EmpleadoRepository repo) {
        return repo != null;
    }

    /**
     * TODO extra 2: Busca las proyecciones de DTO de los empleados mediante el repositorio.
     */
    public static java.util.List<EmpleadoDto> desafioBuscarProyecciones(EmpleadoRepository repo) {
        return repo.buscarTodosProyeccion();
    }

    /**
     * TODO extra 3: Comprueba si un EmpleadoDto no es nulo y tiene un nombre correcto.
     */
    public static boolean desafioDtoValido(EmpleadoDto dto) {
        return dto != null && dto.nombre() != null && !dto.nombre().isBlank();
    }

    /**
     * TODO extra 4: Crea una instancia rápida del DTO EmpleadoDto.
     */
    public static EmpleadoDto desafioCrearDto(String nombre, String dep) {
        return new EmpleadoDto(nombre, dep);
    }

    /**
     * TODO extra 5: Comprueba si una lista de DTOs contiene al menos un elemento con el departamento IT.
     */
    public static boolean desafioContieneDeptoIT(java.util.List<EmpleadoDto> dtos) {
        return dtos.stream().anyMatch(d -> "IT".equals(d.departamento()));
    }

    /**
     * TODO extra 6: Retorna una lista con los nombres contenidos en la lista de DTOs.
     */
    public static java.util.List<String> desafioObtenerNombresDtos(java.util.List<EmpleadoDto> dtos) {
        return dtos.stream().map(EmpleadoDto::nombre).toList();
    }

    /**
     * TODO extra 7: Lanza una excepción si el DTO no tiene departamento asignado.
     */
    public static void desafioValidarDtoCompleto(EmpleadoDto dto) {
        if (dto == null || dto.departamento() == null) {
            throw new IllegalArgumentException("DTO incompleto");
        }
    }

    /**
     * TODO extra 8: Crea un Empleado completo para persistir de prueba.
     */
    public static Empleado desafioCrearEmpleadoEntidad(String nombre, String dep) {
        var e = new Empleado();
        e.setNombre(nombre);
        e.setDepartamento(dep);
        return e;
    }

    /**
     * TODO extra 9: Cuenta cuántos DTOs de empleados existen en el repositorio.
     */
    public static int desafioContarDtos(EmpleadoRepository repo) {
        return repo.buscarTodosProyeccion().size();
    }

    /**
     * TODO extra 10: Retorna verdadero si una lista de DTOs es no vacía.
     */
    public static boolean desafioTieneDatos(java.util.List<EmpleadoDto> dtos) {
        return dtos != null && !dtos.isEmpty();
    }

}
