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
        // GUÍA: una línea — return d.getTexto();
        // (getTexto() ya existe en DocAud128.) El test espera "hola".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerTexto");
    }

    /**
     * Reto Extra 2: Comprueba si tiene fecha de creacion asignada.
     */
    public static boolean tieneCreadoEn(DocAud128 d) {
        // GUÍA: teoría 14.6. creadoEn solo se rellena en @PrePersist (al insertar).
        // Un documento en memoria, sin persistir, lo tiene null.
        // 1. Una línea: return d.getCreadoEn() != null;
        // OJO: el test crea el doc sin guardarlo y espera false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneCreadoEn");
    }

    /**
     * Reto Extra 3: Comprueba si tiene fecha de actualizacion asignada.
     */
    public static boolean tieneActualizadoEn(DocAud128 d) {
        // GUÍA: teoría 14.6. actualizadoEn solo se rellena en @PreUpdate (tras un
        // UPDATE). Recién creado, e incluso recién insertado, es null.
        // 1. Una línea: return d.getActualizadoEn() != null;
        // OJO: el test espera false en un doc sin modificar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneActualizadoEn");
    }

    /**
     * Reto Extra 4: Crea un nuevo documento auditable.
     */
    public static DocAud128 crearDocumento(String texto) {
        // GUÍA: una línea — return new DocAud128(texto);
        // El test solo comprueba que no es null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearDocumento");
    }

    /**
     * Reto Extra 5: Comprueba si el documento ha sido editado (creadoEn != actualizadoEn).
     */
    public static boolean haSidoEditado(DocAud128 d) {
        // GUÍA: "editado" = las dos fechas difieren. Compáralas con seguridad ante
        // nulls usando java.util.Objects.equals (NO uses != entre objetos: compara
        // referencias, no contenido).
        // 1. return !java.util.Objects.equals(d.getCreadoEn(), d.getActualizadoEn());
        // OJO: en un doc nuevo ambas son null → Objects.equals(null,null)=true →
        //      "no editado" → false. Es justo lo que espera el test.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para haSidoEditado");
    }

    /**
     * Reto Extra 6: Actualiza el texto de un documento de forma manual.
     */
    public static void actualizarTexto(DocAud128 d, String nuevoTexto) {
        // GUÍA: una línea — d.setTexto(nuevoTexto);
        // El test cambia "hola" por "mundo" y comprueba getTexto()=="mundo".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para actualizarTexto");
    }

    /**
     * Reto Extra 7: Obtiene el ID del documento.
     */
    public static Long obtenerId(DocAud128 d) {
        // GUÍA: una línea — return d.getId();
        // El test usa un doc sin persistir y espera null (id @GeneratedValue).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerId");
    }

    /**
     * Reto Extra 8: Comprueba si el texto contiene una palabra.
     */
    public static boolean contienePalabra(DocAud128 d, String palabra) {
        // GUÍA: una línea — return d.getTexto().contains(palabra);
        // OJO: el test pide true para "mundo" y false para "casa" sobre "hola mundo".
        // contains distingue mayúsculas/minúsculas (búsqueda exacta de subcadena).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contienePalabra");
    }

    /**
     * Reto Extra 9: Comprueba si el documento es nuevo (ID nulo).
     */
    public static boolean esNuevo(DocAud128 d) {
        // GUÍA: "nuevo" = aún sin persistir = id null. Es el espejo del reto 7.
        // 1. Una línea: return d.getId() == null;
        // OJO: el test espera true en un doc recién creado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNuevo");
    }

    /**
     * Reto Extra 10: Retorna formato de auditoria del documento.
     */
    public static String formatearAuditoria(DocAud128 d) {
        // GUÍA: usa getId() y getCreadoEn().
        // 1. return "Doc[Id=" + d.getId() + ", Creado=" + d.getCreadoEn() + "]";
        // OJO: el test usa un doc nuevo y espera EXACTAMENTE "Doc[Id=null, Creado=null]"
        //      (concatenar un null da el literal "null"; respeta el espacio tras la coma).
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

    public String getTexto() {
        return texto;
    }

    public void setTexto(String t) {
        // TODO 9: asigna el nuevo texto.
        // TODO 10: el cambio de texto disparará @PreUpdate al hacer commit.
        this.texto = t;
    }
}
