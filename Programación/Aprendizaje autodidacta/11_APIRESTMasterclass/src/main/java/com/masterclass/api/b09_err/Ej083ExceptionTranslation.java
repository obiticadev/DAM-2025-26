package com.masterclass.api.b09_err;

import java.sql.SQLException;

/**
 * Ejercicio 083 · Traducción de excepciones de infraestructura a dominio.
 *
 * <p>Teoría: {@code teoria/09_Manejo_de_Errores.md} (sección 9.3).
 *
 * <p>Una SQLException no debe filtrarse a la API: se traduce a una excepción
 * de dominio limpia.
 */
public final class Ej083ExceptionTranslation {

    public static class DatoDuplicadoException extends RuntimeException {
        public DatoDuplicadoException(String m) {
            super(m);
        }
    }

    public static class PersistenciaException extends RuntimeException {
        public PersistenciaException(String m, Throwable causa) {
            super(m, causa);
        }
    }

    private Ej083ExceptionTranslation() {
    }

    /**
     * Traduce una SQLException a una excepción de dominio según su SQLState.
     *
     * <p>SQLState "23505" = clave única duplicada (Postgres).
     *
     * @param ex excepción técnica de JDBC
     * @return la excepción de dominio equivalente (no la lanza, la devuelve)
     * @throws IllegalArgumentException si ex es null
     */
    public static RuntimeException traducir(SQLException ex) {
        // TODO 1: si ex es null -> IllegalArgumentException.
        // TODO 2: obtén el SQLState con ex.getSQLState().
        // TODO 3: si es "23505" -> devuelve DatoDuplicadoException("registro duplicado").
        // TODO 4: el mensaje de dominio NO debe exponer detalles internos de SQL.
        // TODO 5: para cualquier otro SQLState -> PersistenciaException.
        // TODO 6: en PersistenciaException, conserva 'ex' como causa (no la pierdas).
        // TODO 7: el mensaje genérico: "error de persistencia".
        // TODO 8: nunca propagues la SQLException cruda hacia la capa web.
        // TODO 9: preservar la causa permite depurar sin filtrarla al cliente.
        // TODO 10: devuelve la excepción de dominio resultante.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(traducir(new SQLException("dup", "23505")).getClass().getSimpleName());
    }
}
