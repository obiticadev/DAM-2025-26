package com.masterclass.api.b15_query;

import jakarta.persistence.*;
import java.util.List;

/**
 * Ejercicio 135 · Slice vs Page (Slice no hace COUNT).
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.1).
 */
public final class Ej135SliceVsPage {

    /** Slice: contenido + si hay más, SIN total. */
    public record Slice<T>(List<T> contenido, boolean haySiguiente) {
    }

    private final EntityManager em;

    public Ej135SliceVsPage(EntityManager em) {
        this.em = em;
    }

    /**
     * Devuelve un Slice: pide tamano+1 para SABER si hay siguiente sin un COUNT.
     *
     * @param pagina índice 0-based
     * @param tamano tamaño de página
     * @return Slice (contenido recortado a 'tamano' + flag haySiguiente)
     */
    public Slice<Reg135> slice(int pagina, int tamano) {
        // TODO 1: valida pagina >= 0 y tamano > 0.
        // TODO 2: offset = pagina * tamano.
        // TODO 3: JPQL ordenado por id.
        // TODO 4: setFirstResult(offset).
        // TODO 5: setMaxResults(tamano + 1)  <-- el truco: pide UNO de más.
        // TODO 6: getResultList() -> lista (puede tener tamano+1 elementos).
        // TODO 7: haySiguiente = (lista.size() > tamano).
        // TODO 8: si haySiguiente, recorta la lista a 'tamano' (quita el extra).
        // TODO 9: NO ejecutes ningún COUNT (esa es la ventaja del Slice).
        // TODO 10: devuelve new Slice(contenido, haySiguiente).
        return null;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }
}

@Entity
class Reg135 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dato;

    public Reg135() {
    }

    public Reg135(String dato) {
        this.dato = dato;
    }

    public Long getId() {
        return id;
    }
}
