package com.masterclass.api.b15_query;

import jakarta.persistence.*;
import java.util.List;

/**
 * Ejercicio 135 · Slice vs Page (Slice no hace COUNT).
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.1).
 */
public final class Ej135SliceVsPage {

    /** Slice: contenido + si hay más, SIN total. */
    public record Slice<T>(List<T> contenido, boolean haySiguiente) {
    }

    private final EntityManager em;

    public Ej135SliceVsPage(EntityManager em) {
        this.em = em;
    }

    /**
     * Devuelve un Slice: pide tamano+1 para SABER si hay siguiente sin un COUNT.
     *
     * @param pagina índice 0-based
     * @param tamano tamaño de página
     * @return Slice (contenido recortado a 'tamano' + flag haySiguiente)
     */
    public Slice<Reg135> slice(int pagina, int tamano) {
        // TODO 1: valida pagina >= 0 y tamano > 0.
        // TODO 2: offset = pagina * tamano.
        // TODO 3: JPQL ordenado por id.
        // TODO 4: setFirstResult(offset).
        // TODO 5: setMaxResults(tamano + 1)  <-- el truco: pide UNO de más.
        // TODO 6: getResultList() -> lista (puede tener tamano+1 elementos).
        // TODO 7: haySiguiente = (lista.size() > tamano).
        // TODO 8: si haySiguiente, recorta la lista a 'tamano' (quita el extra).
        // TODO 9: NO ejecutes ningún COUNT (esa es la ventaja del Slice).
        // TODO 10: devuelve new Slice(contenido, haySiguiente).
        return null;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Comprueba si un Slice tiene pagina siguiente de forma logica.
     */
    public static boolean tieneSiguiente(Slice<?> s) {
        // TODO extra: Reto Extra 1: Comprueba si un Slice tiene pagina siguiente de forma logica.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneSiguiente");
    }

    /**
     * Reto Extra 2: Comprueba si un Slice tiene pagina anterior.
     */
    public static boolean tieneAnterior(Slice<?> s) {
        // TODO extra: Reto Extra 2: Comprueba si un Slice tiene pagina anterior.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneAnterior");
    }

    /**
     * Reto Extra 3: Comprueba si es la primera pagina de un Slice.
     */
    public static boolean esPrimera(Slice<?> s) {
        // TODO extra: Reto Extra 3: Comprueba si es la primera pagina de un Slice.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPrimera");
    }

    /**
     * Reto Extra 4: Valida los parametros de Slice.
     */
    public static boolean esValida(int pagina, int tamano) {
        // TODO extra: Reto Extra 4: Valida los parametros de Slice.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esValida");
    }

    /**
     * Reto Extra 5: Calcula el offset de forma segura.
     */
    public static int calcularOffset(int pagina, int tamano) {
        // TODO extra: Reto Extra 5: Calcula el offset de forma segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularOffset");
    }

    /**
     * Reto Extra 6: Retorna el tamaño del contenido actual.
     */
    public static int tamanoContenido(Slice<?> s) {
        // TODO extra: Reto Extra 6: Retorna el tamaño del contenido actual.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanoContenido");
    }

    /**
     * Reto Extra 7: Comprueba si el Slice esta vacio.
     */
    public static boolean estaVacio(Slice<?> s) {
        // TODO extra: Reto Extra 7: Comprueba si el Slice esta vacio.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaVacio");
    }

    /**
     * Reto Extra 8: Obtiene el numero de pagina actual.
     */
    public static int numeroPagina(Slice<?> s) {
        // TODO extra: Reto Extra 8: Obtiene el numero de pagina actual.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para numeroPagina");
    }

    /**
     * Reto Extra 9: Comprueba si el tamano de pagina es correcto.
     */
    public static boolean tamanoEsCorrecto(Slice<?> s, int esperado) {
        // TODO extra: Reto Extra 9: Comprueba si el tamano de pagina es correcto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanoEsCorrecto");
    }

    /**
     * Reto Extra 10: Retorna representacion estructurada de texto.
     */
    public static String formatearSlice(Slice<?> s) {
        // TODO extra: Reto Extra 10: Retorna representacion estructurada de texto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearSlice");
    }



}

@Entity
class Reg135 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dato;

    public Reg135() {
    }

    public Reg135(String dato) {
        this.dato = dato;
    }

    public Long getId() {
        return id;
    }
}
