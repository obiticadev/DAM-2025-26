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

    /**
     * Reto Extra 1: Verifica si el nivel previene dirty reads.
     */
    public static boolean previeneDirtyRead(Nivel n) {
        // GUÍA: teoría 14.2 (tabla acumulativa). Reutiliza el método base.
        // 1. Una línea: return previene(n).contains(Fenomeno.DIRTY_READ);
        // OJO: el test pide true para READ_COMMITTED y false para READ_UNCOMMITTED
        //      (el único que no previene NADA).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para previeneDirtyRead");
    }

    /**
     * Reto Extra 2: Verifica si el nivel previene non-repeatable reads.
     */
    public static boolean previeneNonRepeatableRead(Nivel n) {
        // GUÍA: teoría 14.2. Mismo patrón que el reto 1.
        // 1. Una línea: return previene(n).contains(Fenomeno.NON_REPEATABLE_READ);
        // OJO: el test pide true para REPEATABLE_READ y false para READ_COMMITTED
        //      (READ_COMMITTED solo evita DIRTY_READ).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para previeneNonRepeatableRead");
    }

    /**
     * Reto Extra 3: Verifica si el nivel previene phantom reads.
     */
    public static boolean previenePhantomRead(Nivel n) {
        // GUÍA: teoría 14.2. Solo SERIALIZABLE evita los phantom reads.
        // 1. Una línea: return previene(n).contains(Fenomeno.PHANTOM_READ);
        // OJO: el test pide true para SERIALIZABLE y false para REPEATABLE_READ.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para previenePhantomRead");
    }

    /**
     * Reto Extra 4: Comprueba si el nivel previene todos los fenomenos.
     */
    public static boolean previeneTodo(Nivel n) {
        // GUÍA: teoría 14.2. "Previene todo" = el set cubre los 3 fenómenos.
        // 1. Compara el tamaño con el total de fenómenos del enum:
        //    return previene(n).size() == Fenomeno.values().length;
        //    (alternativa: previene(n).size() == 3, pero values().length no
        //     se rompe si algún día añaden un fenómeno).
        // OJO: el test pide true para SERIALIZABLE y false para REPEATABLE_READ.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para previeneTodo");
    }

    /**
     * Reto Extra 5: Comprueba si a es mas estricto que b.
     */
    public static boolean esMasEstricto(Nivel a, Nivel b) {
        // GUÍA: el enum Nivel está DECLARADO de menor a mayor aislamiento, así que
        // ordinal() crece con la estrictez (READ_UNCOMMITTED=0 ... SERIALIZABLE=3).
        // 1. Una línea: return a.ordinal() > b.ordinal();
        // PISTA: ordinal() devuelve la posición del valor en la declaración.
        // OJO: el test pide true para (SERIALIZABLE, REPEATABLE_READ) y false para
        //      (READ_COMMITTED, SERIALIZABLE). Es estricto >, no >=.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMasEstricto");
    }

    /**
     * Reto Extra 6: Retorna el nombre en texto.
     */
    public static String obtenerNombreNivel(Nivel n) {
        // GUÍA: una línea — return n.name();
        // OJO: el test espera "READ_COMMITTED" tal cual; name() lo da gratis.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerNombreNivel");
    }

    /**
     * Reto Extra 7: Retorna el nombre en texto del fenomeno.
     */
    public static String obtenerNombreFenomeno(Fenomeno f) {
        // GUÍA: una línea — return f.name();
        // OJO: el test espera "DIRTY_READ". Igual que el reto 6 pero sobre Fenomeno.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerNombreFenomeno");
    }

    /**
     * Reto Extra 8: Comprueba si un nivel es el mas bajo disponible.
     */
    public static boolean esElMasBajo(Nivel n) {
        // GUÍA: el más bajo es el primero declarado, READ_UNCOMMITTED (ordinal 0).
        // 1. Opciones: return n == Nivel.READ_UNCOMMITTED;  o  return n.ordinal() == 0;
        // OJO: el test pide true para READ_UNCOMMITTED y false para READ_COMMITTED.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esElMasBajo");
    }

    /**
     * Reto Extra 9: Comprueba si un nivel es el mas estricto disponible.
     */
    public static boolean esElMasAlto(Nivel n) {
        // GUÍA: el más alto es SERIALIZABLE (el último declarado).
        // 1. Opciones: return n == Nivel.SERIALIZABLE;
        //    o, sin hardcodear: return n.ordinal() == Nivel.values().length - 1;
        // OJO: el test pide true para SERIALIZABLE y false para REPEATABLE_READ.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esElMasAlto");
    }

    /**
     * Reto Extra 10: Retorna un identificador estructurado del nivel.
     */
    public static String obtenerIdNivel(Nivel n) {
        // GUÍA: prefijo "ISOLATION_" + el nombre del nivel.
        // 1. Una línea: return "ISOLATION_" + n.name();
        // OJO: el test espera EXACTAMENTE "ISOLATION_SERIALIZABLE".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerIdNivel");
    }



}
