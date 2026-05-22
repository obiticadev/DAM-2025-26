package com.masterclass.api.b14_jpaadv;

import jakarta.persistence.*;

/**
 * Ejercicio 125 · Bloqueo optimista con @Version.
 *
 * <p>Teoría: {@code teoria/14_JPA_Avanzado.md} (sección 14.2).
 */
public final class Ej125OptimisticLocking {

    private Ej125OptimisticLocking() {
    }

    /**
     * Persiste un producto y devuelve su id.
     *
     * @param em EntityManager
     * @param p  producto
     * @return id generado
     */
    public static Long guardar(EntityManager em, ProdVer125 p) {
        // TODO 1: begin tx, persist(p), commit.
        // TODO 2: devuelve p.getId().
        return null;
    }

    /**
     * Actualiza el precio en una transacción independiente.
     *
     * @param em      EntityManager
     * @param id      id del producto
     * @param precio  nuevo precio
     */
    public static void actualizarPrecio(EntityManager em, Long id, double precio) {
        // TODO 3: begin tx.
        // TODO 4: find del producto (queda managed con su version actual).
        // TODO 5: cambia el precio con el setter.
        // TODO 6: commit -> Hibernate incrementa la columna @Version.
        // TODO 7: si otra tx ya cambió la version, aquí saltaría OptimisticLockException
        //         (el test provoca ese escenario con dos EntityManager).
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Obtiene el precio de un producto.
     */
    public static double obtenerPrecio(ProdVer125 p) {
        // TODO extra: Reto Extra 1: Obtiene el precio de un producto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerPrecio");
    }

    /**
     * Reto Extra 2: Obtiene la version de un producto.
     */
    public static long obtenerVersion(ProdVer125 p) {
        // TODO extra: Reto Extra 2: Obtiene la version de un producto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerVersion");
    }

    /**
     * Reto Extra 3: Comprueba si el precio es mayor que un limite.
     */
    public static boolean precioEsMayor(ProdVer125 p, double limite) {
        // TODO extra: Reto Extra 3: Comprueba si el precio es mayor que un limite.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para precioEsMayor");
    }

    /**
     * Reto Extra 4: Comprueba si la version es inicial (0).
     */
    public static boolean esVersionInicial(ProdVer125 p) {
        // TODO extra: Reto Extra 4: Comprueba si la version es inicial (0).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esVersionInicial");
    }

    /**
     * Reto Extra 5: Incrementa el precio en base a un porcentaje.
     */
    public static void incrementarPrecioPorcentaje(ProdVer125 p, double pct) {
        // TODO extra: Reto Extra 5: Incrementa el precio en base a un porcentaje.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para incrementarPrecioPorcentaje");
    }

    /**
     * Reto Extra 6: Crea un nuevo producto.
     */
    public static ProdVer125 crearProducto(double precio) {
        // TODO extra: Reto Extra 6: Crea un nuevo producto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearProducto");
    }

    /**
     * Reto Extra 7: Descuenta un valor del precio.
     */
    public static void aplicarDescuento(ProdVer125 p, double desc) {
        // TODO extra: Reto Extra 7: Descuenta un valor del precio.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aplicarDescuento");
    }

    /**
     * Reto Extra 8: Comprueba si el producto tiene ID.
     */
    public static boolean tieneId(ProdVer125 p) {
        // TODO extra: Reto Extra 8: Comprueba si el producto tiene ID.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneId");
    }

    /**
     * Reto Extra 9: Valida si el precio es positivo.
     */
    public static boolean precioEsValido(ProdVer125 p) {
        // TODO extra: Reto Extra 9: Valida si el precio es positivo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para precioEsValido");
    }

    /**
     * Reto Extra 10: Retorna formato de texto del producto.
     */
    public static String formatearProducto(ProdVer125 p) {
        // TODO extra: Reto Extra 10: Retorna formato de texto del producto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearProducto");
    }



}

@Entity
class ProdVer125 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double precio;

    // TODO 8: anota 'version' con @jakarta.persistence.Version.
    // TODO 9: el tipo long/Integer es válido para @Version; aquí usamos long.
    private long version;

    protected ProdVer125() {
    }

    public ProdVer125(double precio) {
        // TODO 10: asigna el precio inicial.
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double p) {
        this.precio = p;
    }
}
