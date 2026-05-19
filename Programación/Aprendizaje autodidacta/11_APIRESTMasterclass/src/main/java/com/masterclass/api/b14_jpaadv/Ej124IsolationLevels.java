package com.masterclass.api.b14_jpaadv;

import java.util.Set;

/**
 * Ejercicio 124 · Niveles de aislamiento y fenómenos que previenen.
 *
 * <p>Teoría: {@code teoria/14_JPA_Avanzado.md} (sección 14.2).
 */
public final class Ej124IsolationLevels {

    /** Fenómenos de concurrencia. */
    public enum Fenomeno {
        DIRTY_READ, NON_REPEATABLE_READ, PHANTOM_READ
    }

    public enum Nivel {
        READ_UNCOMMITTED, READ_COMMITTED, REPEATABLE_READ, SERIALIZABLE
    }

    private Ej124IsolationLevels() {
    }

    /**
     * Devuelve el conjunto de fenómenos que el nivel PREVIENE.
     *
     * @param nivel nivel de aislamiento
     * @return set de fenómenos evitados (cuanto más alto el nivel, más previene)
     */
    public static Set<Fenomeno> previene(Nivel nivel) {
        // TODO 1: READ_UNCOMMITTED -> no previene nada (set vacío).
        // TODO 2: READ_COMMITTED -> previene DIRTY_READ.
        // TODO 3: REPEATABLE_READ -> previene DIRTY_READ y NON_REPEATABLE_READ.
        // TODO 4: SERIALIZABLE -> previene los TRES fenómenos.
        // TODO 5: la relación es ACUMULATIVA (cada nivel previene lo del anterior + más).
        // TODO 6: usa un switch sobre 'nivel'.
        // TODO 7: devuelve un Set (EnumSet/Set.of) según el caso.
        // TODO 8: READ_UNCOMMITTED permite leer datos no confirmados (dirty).
        // TODO 9: SERIALIZABLE es el más estricto (y más lento).
        // TODO 10: devuelve el conjunto correspondiente.
        return Set.of();
    }

    public static void main(String[] args) {
        System.out.println(previene(Nivel.REPEATABLE_READ));
    }
}
