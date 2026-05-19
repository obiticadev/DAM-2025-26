package com.masterclass.api.b12_jpa;

import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * Ejercicio 105 · CRUD estilo repositorio (lo que Spring Data hace por dentro).
 *
 * <p>Teoría: {@code teoria/12_Spring_Data_JPA.md} (sección 12.3).
 */
public final class Ej105JpaRepository {

    private final EntityManager em;

    public Ej105JpaRepository(EntityManager em) {
        this.em = em;
    }

    /**
     * @param t entidad a guardar (insert si id null, update si ya existe)
     * @return la entidad gestionada
     */
    public Tarea105 save(Tarea105 t) {
        // TODO 1: begin transaction.
        // TODO 2: si t.getId() == null -> em.persist(t); si no -> t = em.merge(t).
        // TODO 3: commit.
        // TODO 4: devuelve la entidad (la gestionada en caso de merge).
        return null;
    }

    /** @return entidad por id o null */
    public Tarea105 findById(Long id) {
        // TODO 5: em.find(Tarea105.class, id).
        return null;
    }

    /** @return todas, ordenadas por id (JPQL) */
    public List<Tarea105> findAll() {
        // TODO 6: em.createQuery("select t from Tarea105 t order by t.id", Tarea105.class)
        // TODO 7: .getResultList().
        return List.of();
    }

    /** @param id id a borrar; @return true si existía */
    public boolean deleteById(Long id) {
        // TODO 8: busca la entidad; si es null devuelve false.
        // TODO 9: begin tx, em.remove(entidad), commit.
        // TODO 10: devuelve true (se borró).
        return false;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: begin transaction.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si t.getId() == null -> em.persist(t); si no -> t = em.merge(t).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: commit.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: devuelve la entidad (la gestionada en caso de merge).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: em.find(Tarea105.class, id).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: em.createQuery("select t from Tarea105 t order by t.id", Tarea105.class)
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: .getResultList().
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: busca la entidad; si es null devuelve false.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: begin tx, em.remove(entidad), commit.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve true (se borró).
    }

}

@jakarta.persistence.Entity
class Tarea105 {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    protected Tarea105() {
    }

    public Tarea105(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String t) {
        this.titulo = t;
    }
}
