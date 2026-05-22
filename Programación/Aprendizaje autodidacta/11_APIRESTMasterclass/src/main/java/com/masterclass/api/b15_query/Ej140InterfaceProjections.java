package com.masterclass.api.b15_query;

import jakarta.persistence.*;
import java.util.List;

/**
 * Ejercicio 140 · Proyección a un tipo reducido (solo los campos necesarios).
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.1).
 */
public final class Ej140InterfaceProjections {

    /** Vista reducida: solo id y email (NO toda la entidad). */
    public record UsuarioVista(Long id, String email) {
    }

    private final EntityManager em;

    public Ej140InterfaceProjections(EntityManager em) {
        this.em = em;
    }

    /**
     * Devuelve solo (id, email) de cada usuario, sin materializar la entidad completa.
     *
     * @return lista de UsuarioVista ordenada por id
     */
    public List<UsuarioVista> vistas() {
        // TODO 1: usa proyección por constructor en JPQL con "select new".
        // TODO 2: nombre COMPLETO del record:
        //         com.masterclass.api.b15_query.Ej140InterfaceProjections$UsuarioVista.
        // TODO 3: selecciona SOLO u.id, u.email (no u entero).
        // TODO 4: order by u.id.
        // TODO 5: createQuery(jpql, UsuarioVista.class).
        // TODO 6: getResultList().
        // TODO 7: no se cargan columnas innecesarias (proyección = menos I/O).
        // TODO 8: la vista NO está gestionada por el contexto.
        // TODO 9: si no hay usuarios, lista vacía.
        // TODO 10: devuelve la lista.
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Obtiene el nombre del producto de forma segura.
     */
    public static String obtenerNombre(Prod140 p) {
        // TODO extra: Reto Extra 1: Obtiene el nombre del producto de forma segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerNombre");
    }

    /**
     * Reto Extra 2: Obtiene la categoria del producto.
     */
    public static String obtenerCategoria(Prod140 p) {
        // TODO extra: Reto Extra 2: Obtiene la categoria del producto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerCategoria");
    }

    /**
     * Reto Extra 3: Comprueba si el precio es valido.
     */
    public static boolean esPrecioValido(Prod140 p) {
        // TODO extra: Reto Extra 3: Comprueba si el precio es valido.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPrecioValido");
    }

    /**
     * Reto Extra 4: Crea un nuevo producto.
     */
    public static Prod140 crearProducto(String nombre, String categoria, double precio) {
        // TODO extra: Reto Extra 4: Crea un nuevo producto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearProducto");
    }

    /**
     * Reto Extra 5: Comprueba si un producto tiene ID asignado.
     */
    public static boolean tieneId(Prod140 p) {
        // TODO extra: Reto Extra 5: Comprueba si un producto tiene ID asignado.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneId");
    }

    /**
     * Reto Extra 6: Obtiene el ID del producto de forma segura.
     */
    public static Long obtenerId(Prod140 p) {
        // TODO extra: Reto Extra 6: Obtiene el ID del producto de forma segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerId");
    }

    /**
     * Reto Extra 7: Normaliza el texto.
     */
    public static String normalizarTexto(String s) {
        // TODO extra: Reto Extra 7: Normaliza el texto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarTexto");
    }

    /**
     * Reto Extra 8: Comprueba si el producto es nuevo.
     */
    public static boolean esNuevo(Prod140 p) {
        // TODO extra: Reto Extra 8: Comprueba si el producto es nuevo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNuevo");
    }

    /**
     * Reto Extra 9: Comprueba si la categoria contiene una palabra clave.
     */
    public static boolean categoriaContiene(Prod140 p, String keyword) {
        // TODO extra: Reto Extra 9: Comprueba si la categoria contiene una palabra clave.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para categoriaContiene");
    }

    /**
     * Reto Extra 10: Retorna representacion estructurada de texto.
     */
    public static String formatearProducto(Prod140 p) {
        // TODO extra: Reto Extra 10: Retorna representacion estructurada de texto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearProducto");
    }



}

@Entity
class Usuario140 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String passwordHash;

    public Usuario140() {
    }

    public Usuario140(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }
}
