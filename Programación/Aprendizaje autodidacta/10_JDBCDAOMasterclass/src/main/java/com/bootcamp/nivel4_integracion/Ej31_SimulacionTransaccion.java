package com.bootcamp.nivel4_integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Ej31 — Transacciones: setAutoCommit, commit y rollback
 * Teoría: teoria/04_Integracion_Escenarios.md
 *
 * Objetivo: agrupar varias operaciones en una unidad atómica (todo o nada).
 * Por defecto SQLite confirma cada sentencia al instante (autoCommit = true).
 * Para transacciones explícitas:
 *   1. conn.setAutoCommit(false)
 *   2. Ejecuta las operaciones
 *   3. conn.commit()     → si todo OK
 *      conn.rollback()   → si alguna falla
 *   4. conn.setAutoCommit(true)  → restaurar modo normal
 */
public class Ej31_SimulacionTransaccion {

    // ── Singleton ─────────────────────────────────────────────────────────

    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej31.db";

    public static Connection getConexion() {
        try {
            if (instance == null || instance.isClosed())
                instance = DriverManager.getConnection(URL);
        } catch (SQLException e) { System.err.println(e.getMessage()); }
        return instance;
    }

    // ── DAO ───────────────────────────────────────────────────────────────

    public static class DAOCuenta {

        /**
         * Crea la tabla Cuentas si no existe.
         *
         * @throws SQLException si la sentencia DDL falla
         */
        public void crearTabla() throws SQLException {
            try (Statement s = getConexion().createStatement()) {
                s.execute("""
                        CREATE TABLE IF NOT EXISTS Cuentas (
                            id     INTEGER PRIMARY KEY AUTOINCREMENT,
                            nombre TEXT NOT NULL,
                            saldo  REAL NOT NULL
                        )""");
            }
        }

        /**
         * Inserta una cuenta con el saldo inicial dado.
         *
         * @param nombre nombre del titular
         * @param saldo  saldo inicial
         * @throws SQLException si la inserción falla
         */
        public void insertar(String nombre, double saldo) throws SQLException {
            String sql = "INSERT INTO Cuentas (nombre, saldo) VALUES (?, ?)";
            try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
                pst.setString(1, nombre);
                pst.setDouble(2, saldo);
                pst.executeUpdate();
            }
        }

        /**
         * Devuelve el saldo de una cuenta dado su id, o -1 si no existe.
         *
         * @param id identificador de la cuenta
         * @return saldo actual, o -1 si la cuenta no existe
         * @throws SQLException si la consulta falla
         */
        public double obtenerSaldo(int id) throws SQLException {
            String sql = "SELECT saldo FROM Cuentas WHERE id = ?";
            try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
                pst.setInt(1, id);
                try (ResultSet rs = pst.executeQuery()) {
                    return rs.next() ? rs.getDouble("saldo") : -1;
                }
            }
        }

        /**
         * Transfiere un importe de una cuenta origen a una cuenta destino.
         * Si la cuenta origen no tiene saldo suficiente, hace rollback y lanza excepción.
         * Ambas operaciones se ejecutan dentro de una transacción explícita.
         *
         * @param idOrigen  id de la cuenta que envía el dinero
         * @param idDestino id de la cuenta que recibe el dinero
         * @param importe   cantidad a transferir (positiva)
         * @throws SQLException si la transferencia falla o no hay saldo suficiente
         */
        public void transferir(int idOrigen, int idDestino, double importe) throws SQLException {
            // TODO 1: Implementa la transferencia con transacción explícita.
            //
            //         Pasos:
            //         Connection conn = getConexion();
            //         conn.setAutoCommit(false);
            //         try {
            //             1. Obtén el saldo de idOrigen con obtenerSaldo(idOrigen).
            //             2. Si saldo < importe → lanza new SQLException("Saldo insuficiente").
            //             3. UPDATE Cuentas SET saldo = saldo - ? WHERE id = ? (importe, idOrigen)
            //             4. UPDATE Cuentas SET saldo = saldo + ? WHERE id = ? (importe, idDestino)
            //             5. conn.commit()
            //         } catch (SQLException e) {
            //             conn.rollback();
            //             throw e;
            //         } finally {
            //             conn.setAutoCommit(true);
            //         }
        }

        /**
         * Inserta un lote de cuentas dentro de una única transacción.
         * Si cualquier inserción falla, todas se revierten.
         *
         * @param nombres array de nombres de titulares
         * @param saldos  array de saldos iniciales (mismo orden que nombres)
         * @throws SQLException si alguna inserción falla; se hace rollback de todo el lote
         */
        public void insertarLote(String[] nombres, double[] saldos) throws SQLException {
            // TODO 2: Implementa la inserción en lote con transacción.
            //
            //         Connection conn = getConexion();
            //         conn.setAutoCommit(false);
            //         try {
            //             for (int i = 0; i < nombres.length; i++)
            //                 insertar(nombres[i], saldos[i]);
            //             conn.commit();
            //         } catch (SQLException e) {
            //             conn.rollback();
            //             throw e;
            //         } finally {
            //             conn.setAutoCommit(true);
            //         }
        }
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        DAOCuenta dao = new DAOCuenta();
        dao.crearTabla();

        dao.insertar("Alice", 1000.0);
        dao.insertar("Bob",    500.0);

        System.out.println("Antes — Alice: " + dao.obtenerSaldo(1) + ", Bob: " + dao.obtenerSaldo(2));

        try {
            dao.transferir(1, 2, 200.0);
            System.out.println("Transferencia OK");
        } catch (SQLException e) {
            System.out.println("Transferencia fallida: " + e.getMessage());
        }

        System.out.println("Después — Alice: " + dao.obtenerSaldo(1) + ", Bob: " + dao.obtenerSaldo(2));

        // Intento de transferencia que excede el saldo → rollback
        try {
            dao.transferir(2, 1, 10000.0);
        } catch (SQLException e) {
            System.out.println("Rollback aplicado: " + e.getMessage());
        }

        System.out.println("Saldo Bob tras rollback: " + dao.obtenerSaldo(2));
    }
}
