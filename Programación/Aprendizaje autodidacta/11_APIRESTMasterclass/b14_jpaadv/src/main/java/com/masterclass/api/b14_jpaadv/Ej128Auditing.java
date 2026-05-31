package com.masterclass.api.b14_jpaadv;

import jakarta.persistence.*;
import java.time.Instant;

/**
 * Ejercicio 128 · Auditoría con @PrePersist/@PreUpdate.
 *
 * <p>Teoría: {@code teoria/14_JPA_Avanzado.md} (sección 14.3).
 */
public final class Ej128Auditing {

    private Ej128Auditing() {
    }

    /**
     * Persiste y luego modifica para comprobar que las marcas de auditoría se rellenan.
     *
     * @param em EntityManager
     * @param d  documento nuevo
     * @return el id generado
     */
    public static Long guardar(EntityManager em, DocAud128 d) {
        // TODO 1: begin tx, persist(d), commit -> @PrePersist fija creadoEn.
        // TODO 2: devuelve d.getId().
        return null;
    }

    /**
     * @param em EntityManager
     * @param id id
     * @param texto nuevo contenido (provoca @PreUpdate -> actualizadoEn)
     */
    public static void modificar(EntityManager em, Long id, String texto) {
        // TODO 3: begin tx, find, cambiar texto, commit -> @PreUpdate fija actualizadoEn.
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Obtiene el texto de un documento de forma segura.
     */
    public static String obtenerTexto(DocAud128 d) {
        // TODO extra: Reto Extra 1: Obtiene el texto de un documento de forma segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerTexto");
    }

    /**
     * Reto Extra 2: Comprueba si tiene fecha de creacion asignada.
     */
    public static boolean tieneCreadoEn(DocAud128 d) {
        // TODO extra: Reto Extra 2: Comprueba si tiene fecha de creacion asignada.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneCreadoEn");
    }

    /**
     * Reto Extra 3: Comprueba si tiene fecha de actualizacion asignada.
     */
    public static boolean tieneActualizadoEn(DocAud128 d) {
        // TODO extra: Reto Extra 3: Comprueba si tiene fecha de actualizacion asignada.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneActualizadoEn");
    }

    /**
     * Reto Extra 4: Crea un nuevo documento auditable.
     */
    public static DocAud128 crearDocumento(String texto) {
        // TODO extra: Reto Extra 4: Crea un nuevo documento auditable.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearDocumento");
    }

    /**
     * Reto Extra 5: Comprueba si el documento ha sido editado (creadoEn != actualizadoEn).
     */
    public static boolean haSidoEditado(DocAud128 d) {
        // TODO extra: Reto Extra 5: Comprueba si el documento ha sido editado (creadoEn != actualizadoEn).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para haSidoEditado");
    }

    /**
     * Reto Extra 6: Actualiza el texto de un documento de forma manual.
     */
    public static void actualizarTexto(DocAud128 d, String nuevoTexto) {
        // TODO extra: Reto Extra 6: Actualiza el texto de un documento de forma manual.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para actualizarTexto");
    }

    /**
     * Reto Extra 7: Obtiene el ID del documento.
     */
    public static Long obtenerId(DocAud128 d) {
        // TODO extra: Reto Extra 7: Obtiene el ID del documento.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerId");
    }

    /**
     * Reto Extra 8: Comprueba si el texto contiene una palabra.
     */
    public static boolean contienePalabra(DocAud128 d, String palabra) {
        // TODO extra: Reto Extra 8: Comprueba si el texto contiene una palabra.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contienePalabra");
    }

    /**
     * Reto Extra 9: Comprueba si el documento es nuevo (ID nulo).
     */
    public static boolean esNuevo(DocAud128 d) {
        // TODO extra: Reto Extra 9: Comprueba si el documento es nuevo (ID nulo).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNuevo");
    }

    /**
     * Reto Extra 10: Retorna formato de auditoria del documento.
     */
    public static String formatearAuditoria(DocAud128 d) {
        // TODO extra: Reto Extra 10: Retorna formato de auditoria del documento.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearAuditoria");
    }



}

@Entity
class DocAud128 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;
    private Instant creadoEn;
    private Instant actualizadoEn;

    protected DocAud128() {
    }

    public DocAud128(String texto) {
        this.texto = texto;
    }

    // TODO 4: anota con @PrePersist.
    void prePersist() {
        // TODO 5: asigna creadoEn = Instant.now() (solo al insertar).
    }

    // TODO 6: anota con @PreUpdate.
    void preUpdate() {
        // TODO 7: asigna actualizadoEn = Instant.now() (en cada UPDATE).
        // TODO 8: NO toques creadoEn aquí (la fecha de creación es inmutable).
    }

    public Long getId() {
        return id;
    }

    public Instant getCreadoEn() {
        return creadoEn;
    }

    public Instant getActualizadoEn() {
        return actualizadoEn;
    }

    public void setTexto(String t) {
        // TODO 9: asigna el nuevo texto.
        // TODO 10: el cambio de texto disparará @PreUpdate al hacer commit.
        this.texto = t;
    }
}
