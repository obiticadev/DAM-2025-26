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
        // GUÍA: teoría 13.1. Una línea con guarda de null.
        // 1. Si u es null, devuelve false (no lances NPE).
        // 2. Si u no es null, es true cuando u.getPerfil() != null.
        // PISTA: return u != null && u.getPerfil() != null;
        // OJO: el test pasa validarPerfilAsociado(null) y espera false, no excepción.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para validarPerfilAsociado");
    }

    /**
     * Reto Extra 2: Obtiene la biografia del perfil del usuario, o un valor por defecto.
     */
    public static String obtenerBioDelUsuario(Usuario115 u, String porDefecto) {
        // GUÍA: teoría 13.1 + Optional (bloque 1.2). Navega u → perfil → bio sin NPE.
        // 1. Si u es null O su perfil es null, devuelve porDefecto.
        // 2. Si hay perfil, devuelve su bio (u.getPerfil().getBio()).
        // PISTA limpia: Optional.ofNullable(u)
        //                  .map(Usuario115::getPerfil).map(Perfil115::getBio).orElse(porDefecto);
        // OJO: el test espera "hola" con perfil y "def" cuando u es null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerBioDelUsuario");
    }

    /**
     * Reto Extra 3: Comprueba si la biografia tiene una longitud suficiente.
     */
    public static boolean esBioSuficiente(Usuario115 u, int minLongitud) {
        // GUÍA: teoría 13.1. Reutiliza el patrón del reto 2 para sacar la bio.
        // 1. Obtén la bio de forma segura (o "" si no hay perfil/bio).
        // 2. Devuelve true si bio.length() >= minLongitud.
        // PISTA: obtenerBioDelUsuario(u, "").length() >= minLongitud
        // OJO: la bio "hola" mide 4 → con minLongitud 3 es true, con 10 es false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esBioSuficiente");
    }

    /**
     * Reto Extra 4: Comprueba si el usuario tiene el mismo nombre que su bio.
     */
    public static boolean tieneMismoNombreYBio(Usuario115 u) {
        // GUÍA: teoría 13.1. Compara dos Strings con equals (NUNCA con ==).
        // 1. Saca el nombre (u.getNombre()) y la bio del perfil.
        // 2. Devuelve true si nombre.equals(bio). Protege antes los null.
        // PISTA: u != null && u.getPerfil() != null
        //        && java.util.Objects.equals(u.getNombre(), u.getPerfil().getBio());
        // OJO: el test usa new Usuario115("Ana", new Perfil115("Ana")) → ambos "Ana" → true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneMismoNombreYBio");
    }

    /**
     * Reto Extra 5: Comprueba si la biografia contiene una palabra clave.
     */
    public static boolean bioContienePalabra(Usuario115 u, String palabra) {
        // GUÍA: teoría 13.1. Reutiliza obtenerBioDelUsuario(u, "") para la bio segura.
        // 1. Si palabra es null, decide un resultado seguro (false sirve).
        // 2. Devuelve bio.contains(palabra).
        // PISTA: obtenerBioDelUsuario(u, "").contains(palabra)
        // OJO: con bio "hola mundo", contiene "mundo" (true) pero no "casa" (false).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para bioContienePalabra");
    }

    /**
     * Reto Extra 6: Crea una instancia de Perfil115 con bio saneada.
     */
    public static Perfil115 crearPerfilSaneado(String bio) {
        // GUÍA: teoría 13.1 (saneado de entrada, como el constructor compacto de 1.1).
        // 1. Si bio es null, decide (puedes tratarla como "").
        // 2. Quita espacios sobrantes con trim() y construye el Perfil115.
        // PISTA: return new Perfil115(bio == null ? null : bio.trim());
        // OJO: el test manda "  hola  " y espera getBio() == "hola" (sin espacios).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearPerfilSaneado");
    }

    /**
     * Reto Extra 7: Genera una representacion formateada del perfil.
     */
    public static String formatearPerfil(Usuario115 u) {
        // GUÍA: teoría 13.1. Distingue "hay perfil" de "no hay perfil".
        // 1. Si u o su perfil son null, devuelve el literal "SIN_PERFIL".
        // 2. Si hay perfil, devuelve la bio (o el formato que quieras con ella).
        // PISTA: if (u == null || u.getPerfil() == null) return "SIN_PERFIL";
        // OJO: el test usa new Usuario115("Ana", null) y espera EXACTAMENTE "SIN_PERFIL".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearPerfil");
    }

    /**
     * Reto Extra 8: Comprueba si el ID del perfil es par.
     */
    public static boolean perfilTieneIdPar(Usuario115 u) {
        // GUÍA: teoría 13.1. El id solo existe DESPUÉS de persistir (13.1: persist→commit).
        // 1. Si no hay perfil o su id es null, devuelve false (aún no está en BD).
        // 2. Si hay id, devuelve id % 2 == 0.
        // PISTA: Long id = u.getPerfil().getId(); return id != null && id % 2 == 0;
        // OJO: el test NO persiste, así que el id es null → debe devolver false (no NPE).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para perfilTieneIdPar");
    }

    /**
     * Reto Extra 9: Comprueba si el usuario tiene id asignado.
     */
    public static boolean usuarioTieneId(Usuario115 u) {
        // GUÍA: teoría 13.1. "Tiene id" = "ya fue persistido".
        // 1. Protege u null.
        // 2. Devuelve true si u.getId() != null.
        // PISTA: return u != null && u.getId() != null;
        // OJO: el test usa un usuario recién creado (no persistido) → id null → false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para usuarioTieneId");
    }

    /**
     * Reto Extra 10: Clona superficialmente el usuario.
     */
    public static Usuario115 clonarUsuarioSencillo(Usuario115 u) {
        // GUÍA: teoría 13.1. Clon "superficial" (shallow): comparte el MISMO perfil.
        // 1. Si u es null, devuelve null.
        // 2. Crea un Usuario115 nuevo con el mismo nombre y la MISMA referencia de perfil.
        // PISTA: return new Usuario115(u.getNombre(), u.getPerfil());
        // OJO: el test exige c.getPerfil() == u.getPerfil() (assertEquals sobre la misma
        //      instancia). NO crees un Perfil115 nuevo: eso sería un clon profundo y fallaría.
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

    public String getNombre() {
        return nombre;
    }

    public Perfil115 getPerfil() {
        return perfil;
    }
}
