package com.masterclass.api.b12_jpa;

import jakarta.persistence.EntityManager;

/**
 * Ejercicio 104 · Estrategias de generación de id (@GeneratedValue).
 *
 * <p>Teoría: {@code teoria/12_Spring_Data_JPA.md} (sección 12.1).
 */
public final class Ej104IdGenerationStrategies {

    private Ej104IdGenerationStrategies() {
    }

    /**
     * Persiste dos entidades sin asignar id manualmente y devuelve sus ids.
     *
     * @param em EntityManager
     * @param a  primera entidad (id null)
     * @param b  segunda entidad (id null)
     * @return array {idA, idB} generados por la BD
     */
    public static Long[] guardarDos(EntityManager em, Nota104 a, Nota104 b) {
        // TODO 1: begin transaction.
        // TODO 2: persist(a).
        // TODO 3: persist(b).
        // TODO 4: commit.
        // TODO 5: tras commit los ids están generados (no eran asignados a mano).
        // TODO 6: devuelve new Long[]{a.getId(), b.getId()}.
        // TODO 7: los ids deben ser distintos y no null (la estrategia los crea).
        return null;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * TODO extra 1: Comprueba si una clase tiene el campo id anotado con @GeneratedValue.
     */
    public static boolean desafioTieneGeneratedValue(Class<?> clase, String campo) {
        try {
            var f = clase.getDeclaredField(campo);
            return f.isAnnotationPresent(jakarta.persistence.GeneratedValue.class);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * TODO extra 2: Obtiene la estrategia de generación de ID para un campo.
     */
    public static jakarta.persistence.GenerationType desafioObtenerEstrategiaGeneracion(Class<?> clase, String campo) {
        try {
            var f = clase.getDeclaredField(campo);
            var gen = f.getAnnotation(jakarta.persistence.GeneratedValue.class);
            return gen != null ? gen.strategy() : null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * TODO extra 3: Comprueba si un campo utiliza la estrategia SEQUENCE.
     */
    public static boolean desafioEsEstrategiaSequence(Class<?> clase, String campo) {
        return jakarta.persistence.GenerationType.SEQUENCE == desafioObtenerEstrategiaGeneracion(clase, campo);
    }

    /**
     * TODO extra 4: Comprueba si un campo utiliza la estrategia IDENTITY.
     */
    public static boolean desafioEsEstrategiaIdentity(Class<?> clase, String campo) {
        return jakarta.persistence.GenerationType.IDENTITY == desafioObtenerEstrategiaGeneracion(clase, campo);
    }

    /**
     * TODO extra 5: Comprueba si la entidad tiene anotado un @SequenceGenerator.
     */
    public static boolean desafioTieneSequenceGenerator(Class<?> clase, String campo) {
        try {
            var f = clase.getDeclaredField(campo);
            return f.isAnnotationPresent(jakarta.persistence.SequenceGenerator.class);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * TODO extra 6: Obtiene el nombre del sequence de base de datos desde el generador anotado.
     */
    public static String desafioObtenerNombreSequence(Class<?> clase, String campo) {
        try {
            var f = clase.getDeclaredField(campo);
            var seq = f.getAnnotation(jakarta.persistence.SequenceGenerator.class);
            return seq != null ? seq.sequenceName() : null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * TODO extra 7: Obtiene el tamaño del pool de secuencia (allocationSize) del sequence generator.
     */
    public static int desafioObtenerAllocationSize(Class<?> clase, String campo) {
        try {
            var f = clase.getDeclaredField(campo);
            var seq = f.getAnnotation(jakarta.persistence.SequenceGenerator.class);
            return seq != null ? seq.allocationSize() : -1;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * TODO extra 8: Instancia de forma manual un Registro con ID manual para pruebas.
     */
    public static RegistroConSecuencia desafioCrearRegistroConSecuencia(Long id, String datos) {
        var r = new RegistroConSecuencia();
        r.setId(id);
        r.setDatos(datos);
        return r;
    }

    /**
     * TODO extra 9: Instancia de forma manual un Registro con ID auto-incremental para pruebas.
     */
    public static RegistroConIdentidad desafioCrearRegistroConIdentidad(Long id, String datos) {
        var r = new RegistroConIdentidad();
        r.setId(id);
        r.setDatos(datos);
        return r;
    }

    /**
     * TODO extra 10: Retorna verdadero si el ID de una entidad generada es nulo inicialmente (antes de persistir).
     */
    public static boolean desafioIdInicialEsNulo(Object entidad) {
        try {
            var method = entidad.getClass().getMethod("getId");
            return method.invoke(entidad) == null;
        } catch (Exception e) {
            return false;
        }
    }

}
