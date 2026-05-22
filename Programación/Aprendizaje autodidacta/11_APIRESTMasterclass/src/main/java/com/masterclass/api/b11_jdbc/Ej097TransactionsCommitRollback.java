package com.masterclass.api.b11_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Ejercicio 097 · Transacciones: commit y rollback.
 *
 * <p>Teoría: {@code teoria/11_JDBC_Profundo.md} (sección 11.3).
 *
 * <p>Tabla CUENTA(id INT, saldo INT) con dos filas (la prepara el test).
 */
public final class Ej097TransactionsCommitRollback {

    public static class FondosException extends RuntimeException {
        public FondosException(String m) {
            super(m);
        }
    }

    private Ej097TransactionsCommitRollback() {
    }

    /**
     * Transfiere 'importe' de origen a destino de forma atómica.
     *
     * @param conn    conexión
     * @param origen  id cuenta origen
     * @param destino id cuenta destino
     * @param importe cantidad
     * @throws SQLException si falla SQL
     * @throws FondosException si el origen no tiene saldo (debe hacer rollback)
     */
    public static void transferir(Connection conn, int origen, int destino, int importe) throws SQLException {
        // TODO 1: guarda el autocommit original (conn.getAutoCommit()).
        // TODO 2: desactiva autocommit: conn.setAutoCommit(false) (inicia la transacción).
        // TODO 3: abre try/catch alrededor de las operaciones.
        // TODO 4: UPDATE resta importe al saldo de 'origen' (PreparedStatement).
        // TODO 5: comprueba el saldo de 'origen': si quedó negativo, lanza FondosException.
        // TODO 6: UPDATE suma importe al saldo de 'destino'.
        // TODO 7: si todo fue bien, conn.commit().
        // TODO 8: en el catch, conn.rollback() y relanza la excepción.
        // TODO 9: en finally, restaura el autocommit original.
        // TODO 10: rollback debe dejar AMBOS saldos como estaban (atomicidad).
    }

    public static void main(String[] args) {
        System.out.println("usa el test con H2 en memoria");
    }

    /**
     * TODO extra 1: Desactiva el auto-commit en la conexión JDBC.
     */
    public static void desafioDesactivarAutoCommit(java.sql.Connection conn) throws java.sql.SQLException {
        conn.setAutoCommit(false);
    }

    /**
     * TODO extra 2: Activa el auto-commit en la conexión JDBC.
     */
    public static void desafioActivarAutoCommit(java.sql.Connection conn) throws java.sql.SQLException {
        conn.setAutoCommit(true);
    }

    /**
     * TODO extra 3: Confirma (commit) la transacción activa.
     */
    public static void desafioConfirmarTransaccion(java.sql.Connection conn) throws java.sql.SQLException {
        conn.commit();
    }

    /**
     * TODO extra 4: Revierte (rollback) la transacción activa.
     */
    public static void desafioRevertirTransaccion(java.sql.Connection conn) throws java.sql.SQLException {
        conn.rollback();
    }

    /**
     * TODO extra 5: Comprueba si el auto-commit está desactivado.
     */
    public static boolean desafioIsAutoCommitDesactivado(java.sql.Connection conn) throws java.sql.SQLException {
        return !conn.getAutoCommit();
    }

    /**
     * TODO extra 6: Comprueba si una transacción se puede procesar con un balance válido.
     */
    public static boolean desafioValidarMontoTransferencia(double monto) {
        return monto > 0;
    }

    /**
     * TODO extra 7: Simula el flujo seguro ejecutando un rollback si ocurre un error.
     */
    public static void desafioSimularRollbackSeguro(java.sql.Connection conn) {
        try {
            conn.rollback();
        } catch (java.sql.SQLException e) {
            // Ignorado en simulación
        }
    }

    /**
     * TODO extra 8: Configura el nivel de aislamiento de la transacción a READ COMMITTED.
     */
    public static void desafioAislamientoReadCommitted(java.sql.Connection conn) throws java.sql.SQLException {
        conn.setTransactionIsolation(java.sql.Connection.TRANSACTION_READ_COMMITTED);
    }

    /**
     * TODO extra 9: Crea un punto de restauración (Savepoint) en la transacción.
     */
    public static java.sql.Savepoint desafioCrearSavepoint(java.sql.Connection conn, String nombre) throws java.sql.SQLException {
        return conn.setSavepoint(nombre);
    }

    /**
     * TODO extra 10: Revierte la transacción hasta el Savepoint indicado.
     */
    public static void desafioRevertirAlSavepoint(java.sql.Connection conn, java.sql.Savepoint sv) throws java.sql.SQLException {
        conn.rollback(sv);
    }

}
