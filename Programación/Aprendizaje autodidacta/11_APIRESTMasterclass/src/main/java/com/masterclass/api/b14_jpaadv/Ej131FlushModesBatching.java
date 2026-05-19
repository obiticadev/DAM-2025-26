package com.masterclass.api.b14_jpaadv;

import jakarta.persistence.*;

/**
 * Ejercicio 131 · Flush explícito y orden de operaciones.
 *
 * <p>Teoría: {@code teoria/14_JPA_Avanzado.md} (sección 14.1).
 */
public final class Ej131FlushModesBatching {

    private Ej131FlushModesBatching() {
    }

    /**
     * Persiste N entidades y hace flush explícito a mitad, devolviendo cuántas
     * filas hay en BD JUSTO tras el flush (antes del commit).
     *
     * @param em EntityManager
     * @param n  número de entidades a crear
     * @return filas visibles en la BD tras el flush (debe ser n)
     */
    public static long persistirYFlush(EntityManager em, int n) {
        // TODO 1: begin tx.
        // TODO 2: bucle: persiste n entidades Item131.
        // TODO 3: llama a em.flush() explícitamente (envía los INSERT a la BD ya).
        // TODO 4: ejecuta un COUNT con SQL nativo
        //         createNativeQuery("SELECT COUNT(*) FROM Item131").
        // TODO 5: tras flush, el COUNT ya ve las n filas (aún dentro de la tx).
        // TODO 6: guarda ese count.
        // TODO 7: commit (confirma definitivamente).
        // TODO 8: convierte el resultado a long (((Number)x).longValue()).
        // TODO 9: flush != commit: flush sincroniza, commit confirma.
        // TODO 10: devuelve el count observado tras el flush.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }
}

@Entity
class Item131 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Item131() {
    }

    public Item131(String nombre) {
        this.nombre = nombre;
    }
}
