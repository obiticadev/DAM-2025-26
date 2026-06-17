package com.masterclass.api.b14_jpaadv;

/**
 * Ejercicio 123 · Propagación de transacciones (modelo conceptual).
 *
 * <p>Teoría: {@code teoria/14_JPA_Avanzado.md} (sección 14.1).
 *
 * <p>Simula qué transacción usa un método según su propagación.
 */
public final class Ej123TransactionPropagation {

    public enum Propagacion {
        REQUIRED, REQUIRES_NEW, MANDATORY, NEVER, SUPPORTS
    }

    private Ej123TransactionPropagation() {
    }

    /**
     * Devuelve el id de transacción efectivo de un método llamado desde otro.
     *
     * @param txActiva   id de la tx del llamante (0 = no hay tx activa)
     * @param prop       propagación del método llamado
     * @param siguienteId id que se asignaría si hubiera que CREAR una tx nueva
     * @return id de tx en que se ejecuta el método (0 = sin transacción)
     * @throws IllegalStateException si MANDATORY sin tx, o NEVER con tx
     */
    public static int txEfectiva(int txActiva, Propagacion prop, int siguienteId) {
        // TODO 1: REQUIRED -> si hay tx activa, úsala (txActiva); si no, crea (siguienteId).
        // TODO 2: REQUIRES_NEW -> SIEMPRE crea una nueva (siguienteId), aunque haya activa.
        // TODO 3: MANDATORY -> exige tx activa; si txActiva==0 -> IllegalStateException.
        // TODO 4: MANDATORY con tx -> usa la activa.
        // TODO 5: NEVER -> exige NO tx; si txActiva!=0 -> IllegalStateException.
        // TODO 6: NEVER sin tx -> 0 (se ejecuta sin transacción).
        // TODO 7: SUPPORTS -> usa la activa si existe; si no, 0 (sin tx, sin crear).
        // TODO 8: usa un switch sobre 'prop'.
        // TODO 9: cubre TODOS los valores del enum (exhaustivo).
        // TODO 10: devuelve el id resultante.
        return -999;
    }

    public static void main(String[] args) {
        System.out.println(txEfectiva(7, Propagacion.REQUIRES_NEW, 8));
    }

    /**
     * Reto Extra 1: Comprueba si prop requiere una nueva transaccion siempre.
     */
    public static boolean requiereNueva(Propagacion prop) {
        // GUÍA: teoría 14.1 (tabla de propagaciones).
        // 1. "Requiere una nueva SIEMPRE" describe exactamente a REQUIRES_NEW.
        // 2. Una línea: return prop == Propagacion.REQUIRES_NEW;
        // OJO: el test exige true para REQUIRES_NEW y false para REQUIRED (que
        //      solo crea si NO hay tx activa; no SIEMPRE). No confundas ambas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para requiereNueva");
    }

    /**
     * Reto Extra 2: Comprueba si prop exige una transaccion activa obligatoriamente.
     */
    public static boolean exigeTransaccion(Propagacion prop) {
        // GUÍA: teoría 14.1. "Exige tx activa obligatoriamente" = MANDATORY
        // (si no hay tx, lanza). NEVER es justo lo contrario (la prohíbe).
        // 1. Una línea: return prop == Propagacion.MANDATORY;
        // OJO: el test pide true para MANDATORY y false para NEVER.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para exigeTransaccion");
    }

    /**
     * Reto Extra 3: Comprueba si prop prohibe tener transaccion activa.
     */
    public static boolean prohibeTransaccion(Propagacion prop) {
        // GUÍA: teoría 14.1. "Prohíbe tener tx" = NEVER (lanza si txActiva!=0).
        // 1. Una línea: return prop == Propagacion.NEVER;
        // OJO: el test pide true para NEVER y false para REQUIRED.
        // Es el espejo de exigeTransaccion (reto 2).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para prohibeTransaccion");
    }

    /**
     * Reto Extra 4: Comprueba si prop soporta transaccion activa si existe.
     */
    public static boolean soportaTransaccion(Propagacion prop) {
        // GUÍA: teoría 14.1. "Soporta la tx si existe pero no la crea" = SUPPORTS.
        // 1. Una línea: return prop == Propagacion.SUPPORTS;
        // OJO: el test pide true para SUPPORTS y false para REQUIRES_NEW.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para soportaTransaccion");
    }

    /**
     * Reto Extra 5: Comprueba si prop requiere una transaccion (creandola si no existe).
     */
    public static boolean esRequerida(Propagacion prop) {
        // GUÍA: teoría 14.1. "Requiere tx, creándola si no existe" = REQUIRED
        // (el comportamiento por defecto de @Transactional).
        // 1. Una línea: return prop == Propagacion.REQUIRED;
        // OJO: el test pide true para REQUIRED y false para SUPPORTS (SUPPORTS
        //      NO crea: si no hay tx, se ejecuta sin ella).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRequerida");
    }

    /**
     * Reto Extra 6: Valida el estado transaccional.
     */
    public static boolean validarEstado(int txActiva, Propagacion prop) {
        // GUÍA: teoría 14.1. "Estado válido" = la combinación (txActiva, prop)
        // NO viola ninguna precondición, es decir, txEfectiva NO lanzaría.
        // 1. Es inválido solo si: MANDATORY sin tx (txActiva==0), o NEVER con tx
        //    (txActiva!=0). Todo lo demás es válido.
        // 2. return !(prop == Propagacion.MANDATORY && txActiva == 0)
        //         && !(prop == Propagacion.NEVER && txActiva != 0);
        // OJO: el test pide true para (1, REQUIRED) y false para (0, MANDATORY).
        // Puedes reutilizar exigeTransaccion (reto 2) y prohibeTransaccion (reto 3).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para validarEstado");
    }

    /**
     * Reto Extra 7: Obtiene el nombre del tipo de propagacion.
     */
    public static String obtenerNombre(Propagacion prop) {
        // GUÍA: todo enum tiene name() que devuelve su literal exacto.
        // 1. Una línea: return prop.name();
        // OJO: el test espera "REQUIRED" tal cual; name() lo da gratis (no uses
        //      toString() reimplementado ni concatenes nada).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerNombre");
    }

    /**
     * Reto Extra 8: Simula si habra rollback en caso de fallo.
     */
    public static boolean provocaraRollback(Propagacion prop) {
        // GUÍA: teoría 14.1. Solo puede haber rollback si HAY una transacción que
        // confirmar. NEVER se ejecuta SIN transacción, así que un fallo no
        // provoca rollback (no hay nada que deshacer).
        // 1. Las que garantizan tx: REQUIRED, REQUIRES_NEW, MANDATORY → true.
        //    NEVER → false (SUPPORTS depende, pero el test no lo evalúa).
        // 2. Sencillo: return prop != Propagacion.NEVER && prop != Propagacion.SUPPORTS;
        //    (o un switch). Reutiliza esRequerida/requiereNueva/exigeTransaccion.
        // OJO: el test pide true para REQUIRED y false para NEVER.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para provocaraRollback");
    }

    /**
     * Reto Extra 9: Comprueba si es una propagacion segura.
     */
    public static boolean esSegura(Propagacion prop) {
        // GUÍA: teoría 14.1. Llamamos "segura" a la propagación que NUNCA lanza
        // por sí sola, exista o no tx activa: REQUIRED, REQUIRES_NEW y SUPPORTS
        // funcionan en ambos estados. MANDATORY y NEVER pueden lanzar.
        // 1. return prop == Propagacion.REQUIRED || prop == Propagacion.REQUIRES_NEW
        //         || prop == Propagacion.SUPPORTS;
        //    (o: prop != MANDATORY && prop != NEVER).
        // OJO: el test solo comprueba que REQUIRED es segura (true).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esSegura");
    }

    /**
     * Reto Extra 10: Retorna un identificador simulado.
     */
    public static String obtenerIdSimulado(Propagacion prop) {
        // GUÍA: construye el identificador con el prefijo "PROP_" + el nombre.
        // 1. Una línea: return "PROP_" + prop.name();
        // OJO: el test espera EXACTAMENTE "PROP_REQUIRED" (prefijo en mayúsculas,
        //      guion bajo, sin espacios). Reutiliza obtenerNombre (reto 7) si quieres.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerIdSimulado");
    }



}
