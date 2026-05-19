package com.masterclass.api.b15_query;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 137 · Specifications (predicados componibles con Criteria).
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.3).
 */
public final class Ej137Specifications {

    private final EntityManager em;

    public Ej137Specifications(EntityManager em) {
        this.em = em;
    }

    /**
     * Busca libros componiendo predicados con CriteriaBuilder.
     *
     * @param generoOpt   filtro opcional por género (null = ignorar)
     * @param paginasMin  filtro opcional páginas mínimas (null = ignorar)
     * @return libros que cumplen TODOS los predicados activos
     */
    public List<Libro137> buscar(String generoOpt, Integer paginasMin) {
        // TODO 1: obtén CriteriaBuilder con em.getCriteriaBuilder().
        // TODO 2: crea CriteriaQuery<Libro137> cq = cb.createQuery(Libro137.class).
        // TODO 3: Root<Libro137> root = cq.from(Libro137.class).
        // TODO 4: crea una List<Predicate> vacía.
        // TODO 5: si generoOpt != null, añade cb.equal(root.get("genero"), generoOpt).
        // TODO 6: si paginasMin != null, añade cb.ge(root.get("paginas"), paginasMin).
        // TODO 7: cq.where(cb.and(predicados.toArray(new Predicate[0]))).
        // TODO 8: cq.orderBy(cb.asc(root.get("id"))).
        // TODO 9: em.createQuery(cq).getResultList().
        // TODO 10: devuelve la lista (predicados combinados con AND).
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }
}

@Entity
class Libro137 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String genero;
    private int paginas;

    public Libro137() {
    }

    public Libro137(String genero, int paginas) {
        this.genero = genero;
        this.paginas = paginas;
    }

    public String getGenero() {
        return genero;
    }

    public int getPaginas() {
        return paginas;
    }
}
