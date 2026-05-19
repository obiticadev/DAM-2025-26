package com.masterclass.api.b15_query;

import jakarta.persistence.*;
import java.util.List;

/**
 * Ejercicio 133 · Paginación (offset/limit + total).
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.1).
 */
public final class Ej133Pagination {

    /** Página: contenido + metadatos. */
    public record Pagina<T>(List<T> contenido, long total, int pagina, int tamano) {
        public int totalPaginas() {
            return tamano == 0 ? 0 : (int) Math.ceil((double) total / tamano);
        }
    }

    private final EntityManager em;

    public Ej133Pagination(EntityManager em) {
        this.em = em;
    }

    /**
     * Devuelve una página de items ordenados por id.
     *
     * @param pagina índice 0-based
     * @param tamano tamaño de página (&gt; 0)
     * @return Pagina con contenido y total global
     * @throws IllegalArgumentException si pagina &lt; 0 o tamano &lt;= 0
     */
    public Pagina<Item133> page(int pagina, int tamano) {
        // TODO 1: valida pagina >= 0 y tamano > 0.
        // TODO 2: calcula el offset = pagina * tamano.
        // TODO 3: query de datos JPQL "select i from Item133 i order by i.id".
        // TODO 4: aplica setFirstResult(offset).
        // TODO 5: aplica setMaxResults(tamano).
        // TODO 6: getResultList() para el contenido.
        // TODO 7: query aparte "select count(i) from Item133 i" para el total.
        // TODO 8: getSingleResult() -> long total.
        // TODO 9: construye la Pagina con contenido, total, pagina, tamano.
        // TODO 10: devuélvela (totalPaginas lo calcula el record).
        return null;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }
}

@Entity
class Item133 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Item133() {
    }

    public Item133(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
