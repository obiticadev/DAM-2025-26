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

    public static void pasoExtra01() {
        // TODO extra aislando concepto: guarda el autocommit original (conn.getAutoCommit()).
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: desactiva autocommit: conn.setAutoCommit(false) (inicia la transacción).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: abre try/catch alrededor de las operaciones.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: UPDATE resta importe al saldo de 'origen' (PreparedStatement).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: comprueba el saldo de 'origen': si quedó negativo, lanza FondosException.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: UPDATE suma importe al saldo de 'destino'.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: si todo fue bien, conn.commit().
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: en el catch, conn.rollback() y relanza la excepción.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: en finally, restaura el autocommit original.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: rollback debe dejar AMBOS saldos como estaban (atomicidad).
    }

}
