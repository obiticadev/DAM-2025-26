package com.masterclass.api.b14_jpaadv;

import jakarta.persistence.*;

/**
 * Ejercicio 126 · Bloqueo pesimista (LockModeType).
 *
 * <p>Teoría: {@code teoria/14_JPA_Avanzado.md} (sección 14.2).
 */
public final class Ej126PessimisticLocking {

    private Ej126PessimisticLocking() {
    }

    /**
     * Lee una fila con bloqueo de escritura (PESSIMISTIC_WRITE) y resta stock.
     *
     * @param em       EntityManager
     * @param id       id del artículo
     * @param cantidad unidades a descontar
     * @return stock resultante
     * @throws IllegalStateException si no hay stock suficiente
     */
    public static int reservar(EntityManager em, Long id, int cantidad) {
        // TODO 1: begin tx.
        // TODO 2: usa em.find(ArtStock126.class, id, LockModeType.PESSIMISTIC_WRITE)
        //         para bloquear la fila mientras dura la tx.
        // TODO 3: si la entidad es null -> IllegalStateException.
        // TODO 4: comprueba que stock >= cantidad; si no -> IllegalStateException.
        // TODO 5: resta 'cantidad' al stock.
        // TODO 6: commit (libera el lock).
        // TODO 7: el lock pesimista evita que dos reservas concurrentes sobrevendan.
        // TODO 8: devuelve el stock resultante.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Obtiene el stock de un articulo.
     */
    public static int obtenerStock(ArtStock126 a) {
        // TODO extra: Reto Extra 1: Obtiene el stock de un articulo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerStock");
    }

    /**
     * Reto Extra 2: Comprueba si hay stock disponible (mayor que 0).
     */
    public static boolean tieneStockDisponible(ArtStock126 a) {
        // TODO extra: Reto Extra 2: Comprueba si hay stock disponible (mayor que 0).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneStockDisponible");
    }

    /**
     * Reto Extra 3: Comprueba si hay suficiente stock para cubrir una cantidad.
     */
    public static boolean stockSuficiente(ArtStock126 a, int cant) {
        // TODO extra: Reto Extra 3: Comprueba si hay suficiente stock para cubrir una cantidad.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para stockSuficiente");
    }

    /**
     * Reto Extra 4: Crea un nuevo articulo con stock inicial.
     */
    public static ArtStock126 crearArticulo(Long id, int stock) {
        // TODO extra: Reto Extra 4: Crea un nuevo articulo con stock inicial.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearArticulo");
    }

    /**
     * Reto Extra 5: Incrementa el stock de un articulo.
     */
    public static void incrementarStock(ArtStock126 a, int cant) {
        // TODO extra: Reto Extra 5: Incrementa el stock de un articulo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para incrementarStock");
    }

    /**
     * Reto Extra 6: Decrementa el stock si hay suficiente.
     */
    public static boolean decrementarStock(ArtStock126 a, int cant) {
        // TODO extra: Reto Extra 6: Decrementa el stock si hay suficiente.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para decrementarStock");
    }

    /**
     * Reto Extra 7: Obtiene el ID del articulo.
     */
    public static Long obtenerId(ArtStock126 a) {
        // TODO extra: Reto Extra 7: Obtiene el ID del articulo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerId");
    }

    /**
     * Reto Extra 8: Comprueba si el stock es cero.
     */
    public static boolean stockEsCero(ArtStock126 a) {
        // TODO extra: Reto Extra 8: Comprueba si el stock es cero.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para stockEsCero");
    }

    /**
     * Reto Extra 9: Comprueba si el stock es par.
     */
    public static boolean stockEsPar(ArtStock126 a) {
        // TODO extra: Reto Extra 9: Comprueba si el stock es par.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para stockEsPar");
    }

    /**
     * Reto Extra 10: Retorna formato de texto del stock.
     */
    public static String formatearStock(ArtStock126 a) {
        // TODO extra: Reto Extra 10: Retorna formato de texto del stock.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearStock");
    }



}

@Entity
class ArtStock126 {
    @Id
    private Long id;
    private int stock;

    protected ArtStock126() {
    }

    public ArtStock126(Long id, int stock) {
        // TODO 9: asigna id y stock.
        this.id = id;
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int s) {
        // TODO 10: asigna el nuevo stock.
        this.stock = s;
    }
}
