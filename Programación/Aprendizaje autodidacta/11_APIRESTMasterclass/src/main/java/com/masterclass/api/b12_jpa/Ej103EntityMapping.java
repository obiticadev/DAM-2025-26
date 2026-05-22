package com.masterclass.api.b12_jpa;

import jakarta.persistence.EntityManager;

/**
 * Ejercicio 103 · Mapeo de entidad (@Entity/@Id/@Column).
 *
 * <p>Teoría: {@code teoria/12_Spring_Data_JPA.md} (sección 12.1).
 *
 * <p>El test construye un EntityManagerFactory aislado con {@link Producto103}.
 */
public final class Ej103EntityMapping {

    private Ej103EntityMapping() {
    }

    /**
     * Persiste un producto dentro de una transacción.
     *
     * @param em      EntityManager activo
     * @param p       entidad a guardar
     * @return el id asignado tras el flush
     */
    public static Long guardar(EntityManager em, Producto103 p) {
        // TODO 1: inicia la transacción (em.getTransaction().begin()).
        // TODO 2: persiste la entidad con em.persist(p).
        // TODO 3: haz commit (em.getTransaction().commit()).
        // TODO 4: devuelve p.getId() (ya poblado tras persistir).
        // TODO 5: si algo falla, deja propagar (el test verá el error).
        return null;
    }

    /**
     * Busca por clave primaria.
     *
     * @param em EntityManager
     * @param id clave
     * @return la entidad o null si no existe
     */
    public static Producto103 buscar(EntityManager em, Long id) {
        // TODO 6: usa em.find(Producto103.class, id) (devuelve null si no existe).
        return null;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * TODO extra 1: Comprueba si una clase tiene la anotación @Entity.
     */
    public static boolean desafioTieneAnotacionEntity(Class<?> clase) {
        return clase.isAnnotationPresent(jakarta.persistence.Entity.class);
    }

    /**
     * TODO extra 2: Comprueba si una clase tiene la anotación @Table.
     */
    public static boolean desafioTieneAnotacionTable(Class<?> clase) {
        return clase.isAnnotationPresent(jakarta.persistence.Table.class);
    }

    /**
     * TODO extra 3: Comprueba si un campo específico de una clase tiene la anotación @Id.
     */
    public static boolean desafioCampoTieneId(Class<?> clase, String campo) {
        try {
            var f = clase.getDeclaredField(campo);
            return f.isAnnotationPresent(jakarta.persistence.Id.class);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * TODO extra 4: Comprueba si un campo específico de una clase tiene la anotación @Column.
     */
    public static boolean desafioCampoTieneColumn(Class<?> clase, String campo) {
        try {
            var f = clase.getDeclaredField(campo);
            return f.isAnnotationPresent(jakarta.persistence.Column.class);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * TODO extra 5: Retorna el nombre de la tabla configurado en la anotación @Table de la clase.
     */
    public static String desafioObtenerNombreTabla(Class<?> clase) {
        var table = clase.getAnnotation(jakarta.persistence.Table.class);
        return table != null ? table.name() : null;
    }

    /**
     * TODO extra 6: Retorna el nombre de columna configurado en @Column para un campo.
     */
    public static String desafioObtenerNombreColumna(Class<?> clase, String campo) {
        try {
            var f = clase.getDeclaredField(campo);
            var col = f.getAnnotation(jakarta.persistence.Column.class);
            return col != null ? col.name() : null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * TODO extra 7: Valida que el nombre de un empleado no sea nulo.
     */
    public static void desafioValidarNombreEmpleado(String nombre) {
        if (nombre == null) {
            throw new IllegalArgumentException("Nombre no válido");
        }
    }

    /**
     * TODO extra 8: Comprueba si el campo es no nullable según la anotación @Column.
     */
    public static boolean desafioColumnaNoNula(Class<?> clase, String campo) {
        try {
            var f = clase.getDeclaredField(campo);
            var col = f.getAnnotation(jakarta.persistence.Column.class);
            return col != null && !col.nullable();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * TODO extra 9: Crea una instancia básica del empleado mapeado.
     */
    public static Empleado desafioCrearEmpleadoBasico(Long id, String nombre) {
        var e = new Empleado();
        e.setId(id);
        e.setNombre(nombre);
        return e;
    }

    /**
     * TODO extra 10: Retorna verdadero si el empleado tiene un id configurado de forma correcta.
     */
    public static boolean desafioTieneIdConfigurado(Empleado e) {
        return e != null && e.getId() != null;
    }

}
