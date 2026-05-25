package com.masterclass.api.b14_jpaadv;

import jakarta.persistence.*;
import java.util.List;

/**
 * Ejercicio 129 · Borrado lógico (soft delete).
 *
 * <p>Teoría: {@code teoria/14_JPA_Avanzado.md} (sección 14.3).
 *
 * <p>No se borra la fila: se marca {@code borrado=true} y las consultas la excluyen.
 */
public final class Ej129SoftDelete {

    private final EntityManager em;

    public Ej129SoftDelete(EntityManager em) {
        this.em = em;
    }

    /** Guarda un cliente activo. @param c cliente @return id */
    public Long crear(ClienteSD129 c) {
        // TODO 1: begin tx, persist(c), commit, devuelve c.getId().
        return null;
    }

    /**
     * Borrado lógico: marca el flag, NO ejecuta DELETE.
     *
     * @param id id del cliente
     * @return true si existía y se marcó
     */
    public boolean borrarLogico(Long id) {
        // TODO 2: begin tx.
        // TODO 3: find del cliente; si null -> false (rollback/commit vacío).
        // TODO 4: setBorrado(true) (NO em.remove).
        // TODO 5: commit.
        // TODO 6: devuelve true.
        return false;
    }

    /**
     * Lista solo los NO borrados.
     *
     * @return clientes activos (borrado = false)
     */
    public List<ClienteSD129> listarActivos() {
        // TODO 7: JPQL "select c from ClienteSD129 c where c.borrado = false order by c.id".
        // TODO 8: getResultList().
        // TODO 9: los borrados lógicamente NO deben aparecer aquí.
        // TODO 10: la fila sigue en BD (consultable sin el filtro) — soft, no físico.
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Obtiene el nombre del cliente de forma segura.
     */
    public static String obtenerNombre(ClienteSD129 c) {
        // TODO extra: Reto Extra 1: Obtiene el nombre del cliente de forma segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerNombre");
    }

    /**
     * Reto Extra 2: Comprueba si el cliente esta marcado como borrado.
     */
    public static boolean estaBorrado(ClienteSD129 c) {
        // TODO extra: Reto Extra 2: Comprueba si el cliente esta marcado como borrado.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaBorrado");
    }

    /**
     * Reto Extra 3: Crea un nuevo cliente activo.
     */
    public static ClienteSD129 crearCliente(String nombre) {
        // TODO extra: Reto Extra 3: Crea un nuevo cliente activo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearCliente");
    }

    /**
     * Reto Extra 4: Marca un cliente como borrado (soft delete manual).
     */
    public static void borrarClienteManual(ClienteSD129 c) {
        // TODO extra: Reto Extra 4: Marca un cliente como borrado (soft delete manual).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para borrarClienteManual");
    }

    /**
     * Reto Extra 5: Restaura un cliente borrado logicamente.
     */
    public static void restaurarCliente(ClienteSD129 c) {
        // TODO extra: Reto Extra 5: Restaura un cliente borrado logicamente.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para restaurarCliente");
    }

    /**
     * Reto Extra 6: Obtiene el ID del cliente de forma segura.
     */
    public static Long obtenerId(ClienteSD129 c) {
        // TODO extra: Reto Extra 6: Obtiene el ID del cliente de forma segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerId");
    }

    /**
     * Reto Extra 7: Comprueba si el cliente es nuevo (ID nulo).
     */
    public static boolean esNuevo(ClienteSD129 c) {
        // TODO extra: Reto Extra 7: Comprueba si el cliente es nuevo (ID nulo).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNuevo");
    }

    /**
     * Reto Extra 8: Comprueba si el nombre del cliente contiene una palabra.
     */
    public static boolean nombreContiene(ClienteSD129 c, String palabra) {
        // TODO extra: Reto Extra 8: Comprueba si el nombre del cliente contiene una palabra.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreContiene");
    }

    /**
     * Reto Extra 9: Comprueba si el cliente esta activo (no borrado).
     */
    public static boolean estaActivo(ClienteSD129 c) {
        // TODO extra: Reto Extra 9: Comprueba si el cliente esta activo (no borrado).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaActivo");
    }

    /**
     * Reto Extra 10: Retorna formato del cliente.
     */
    public static String formatearCliente(ClienteSD129 c) {
        // TODO extra: Reto Extra 10: Retorna formato del cliente.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearCliente");
    }



}

@Entity
class ClienteSD129 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private boolean borrado = false;

    protected ClienteSD129() {
    }

    public ClienteSD129(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean b) {
        this.borrado = b;
    }
}
