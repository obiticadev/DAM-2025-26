package com.masterclass.api.b13_rel;

import jakarta.persistence.*;

/**
 * Ejercicio 115 · @OneToOne.
 *
 * <p>Teoría: {@code teoria/13_Relaciones_JPA.md} (sección 13.1).
 */
public final class Ej115OneToOne {

    private Ej115OneToOne() {
    }

    /**
     * Persiste un usuario con su perfil asociado y lo recarga.
     *
     * @param em EntityManager
     * @param u  usuario con perfil seteado
     * @return el usuario recargado desde BD (con su perfil)
     */
    public static Usuario115 guardarYRecargar(EntityManager em, Usuario115 u) {
        // TODO 1: begin tx.
        // TODO 2: persist(u) — con cascade debe persistir también el perfil.
        // TODO 3: commit.
        // TODO 4: em.clear() para leer fresco desde BD.
        // TODO 5: devuelve em.find(Usuario115.class, u.getId()).
        return null;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Valida si el usuario tiene un perfil asociado.
     */
    public static boolean validarPerfilAsociado(Usuario115 u) {
        // TODO extra: Reto Extra 1: Valida si el usuario tiene un perfil asociado.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para validarPerfilAsociado");
    }

    /**
     * Reto Extra 2: Obtiene la biografia del perfil del usuario, o un valor por defecto.
     */
    public static String obtenerBioDelUsuario(Usuario115 u, String porDefecto) {
        // TODO extra: Reto Extra 2: Obtiene la biografia del perfil del usuario, o un valor por defecto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerBioDelUsuario");
    }

    /**
     * Reto Extra 3: Comprueba si la biografia tiene una longitud suficiente.
     */
    public static boolean esBioSuficiente(Usuario115 u, int minLongitud) {
        // TODO extra: Reto Extra 3: Comprueba si la biografia tiene una longitud suficiente.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esBioSuficiente");
    }

    /**
     * Reto Extra 4: Comprueba si el usuario tiene el mismo nombre que su bio.
     */
    public static boolean tieneMismoNombreYBio(Usuario115 u) {
        // TODO extra: Reto Extra 4: Comprueba si el usuario tiene el mismo nombre que su bio.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneMismoNombreYBio");
    }

    /**
     * Reto Extra 5: Comprueba si la biografia contiene una palabra clave.
     */
    public static boolean bioContienePalabra(Usuario115 u, String palabra) {
        // TODO extra: Reto Extra 5: Comprueba si la biografia contiene una palabra clave.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para bioContienePalabra");
    }

    /**
     * Reto Extra 6: Crea una instancia de Perfil115 con bio saneada.
     */
    public static Perfil115 crearPerfilSaneado(String bio) {
        // TODO extra: Reto Extra 6: Crea una instancia de Perfil115 con bio saneada.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearPerfilSaneado");
    }

    /**
     * Reto Extra 7: Genera una representacion formateada del perfil.
     */
    public static String formatearPerfil(Usuario115 u) {
        // TODO extra: Reto Extra 7: Genera una representacion formateada del perfil.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearPerfil");
    }

    /**
     * Reto Extra 8: Comprueba si el ID del perfil es par.
     */
    public static boolean perfilTieneIdPar(Usuario115 u) {
        // TODO extra: Reto Extra 8: Comprueba si el ID del perfil es par.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para perfilTieneIdPar");
    }

    /**
     * Reto Extra 9: Comprueba si el usuario tiene id asignado.
     */
    public static boolean usuarioTieneId(Usuario115 u) {
        // TODO extra: Reto Extra 9: Comprueba si el usuario tiene id asignado.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para usuarioTieneId");
    }

    /**
     * Reto Extra 10: Clona superficialmente el usuario.
     */
    public static Usuario115 clonarUsuarioSencillo(Usuario115 u) {
        // TODO extra: Reto Extra 10: Clona superficialmente el usuario.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clonarUsuarioSencillo");
    }



}

@Entity
class Perfil115 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bio;

    protected Perfil115() {
    }

    public Perfil115(String bio) {
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }
}

@Entity
class Usuario115 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    // TODO 6: anota 'perfil' con @OneToOne.
    // TODO 7: añade cascade = CascadeType.ALL (al guardar el usuario, guarda el perfil).
    // TODO 8: usa @JoinColumn(name = "perfil_id") para la FK.
    private Perfil115 perfil;

    protected Usuario115() {
    }

    public Usuario115(String nombre, Perfil115 perfil) {
        // TODO 9: asigna nombre y perfil.
        // TODO 10: la relación es 1–1: un usuario tiene exactamente un perfil.
        this.nombre = nombre;
        this.perfil = perfil;
    }

    public Long getId() {
        return id;
    }

    public Perfil115 getPerfil() {
        return perfil;
    }
}
