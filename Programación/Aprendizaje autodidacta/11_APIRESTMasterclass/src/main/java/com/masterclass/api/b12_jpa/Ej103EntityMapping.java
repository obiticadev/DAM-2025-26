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

    public static void pasoExtra01() {
        // TODO extra aislando concepto: inicia la transacción (em.getTransaction().begin()).
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: persiste la entidad con em.persist(p).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: haz commit (em.getTransaction().commit()).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: devuelve p.getId() (ya poblado tras persistir).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: si algo falla, deja propagar (el test verá el error).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: usa em.find(Producto103.class, id) (devuelve null si no existe).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: anota la clase con @jakarta.persistence.Entity.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: anota con @jakarta.persistence.Table(name = "PRODUCTO").
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: anota 'id' con @Id (clave primaria).
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: anota 'nombre' con @Column(nullable = false, length = 80).
    }

}

/**
 * Entidad de producto. Tabla PRODUCTO.
 */
// TODO 7: anota la clase con @jakarta.persistence.Entity.
// TODO 8: anota con @jakarta.persistence.Table(name = "PRODUCTO").
class Producto103 {

    // TODO 9: anota 'id' con @Id (clave primaria).
    private Long id;

    // TODO 10: anota 'nombre' con @Column(nullable = false, length = 80).
    private String nombre;

    private double precio;

    protected Producto103() {
    }

    public Producto103(Long id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}
