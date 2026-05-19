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
}

// TODO 8: anota la clase con @jakarta.persistence.Entity.
class Nota104 {

    // TODO 9: anota 'id' con @Id.
    // TODO 10: anota 'id' con @GeneratedValue(strategy = GenerationType.IDENTITY)
    //          para que la BD genere el valor automáticamente.
    private Long id;

    private String texto;

    protected Nota104() {
    }

    public Nota104(String texto) {
        this.texto = texto;
    }

    public Long getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }
}
