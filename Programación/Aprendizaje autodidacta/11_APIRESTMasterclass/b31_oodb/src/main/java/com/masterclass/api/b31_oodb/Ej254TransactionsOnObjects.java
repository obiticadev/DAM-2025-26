package com.masterclass.api.b31_oodb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

/**
 * Ejercicio 254 · Transacciones sobre objetos: todo o nada (commit / rollback).
 *
 * <p>Una BD objeto-relacional/OO promete que persistir un objeto es <b>atómico</b>: o se
 * guardan todos sus cambios o ninguno. Sobre JDBC eso se consigue con
 * {@code setAutoCommit(false)} + {@code commit()} / {@code rollback()}. Aquí lo aplicas a
 * "objetos" (departamentos con presupuesto, lotes de empleados) y enlazas con b11·Ej097.
 *
 * <p>El test crea DEPT(id, nombre, presupuesto) y EMP(id PK, nombre).
 *
 * <p>Teoría: {@code teoria/31_ObjetoRelacional_OO.md} (sección 31.6).
 */
public final class Ej254TransactionsOnObjects {

    private Ej254TransactionsOnObjects() {
    }

    /** Excepción de negocio: no hay presupuesto suficiente para la transferencia. */
    public static class PresupuestoException extends Exception {
        public PresupuestoException(String mensaje) {
            super(mensaje);
        }
    }

    /**
     * Transfiere presupuesto de un departamento a otro de forma atómica.
     * Si el origen no tiene fondos, lanza {@link PresupuestoException} y deja todo como estaba.
     */
    public static void transferirPresupuesto(Connection c, int idOrigen, int idDestino, int monto)
            throws SQLException, PresupuestoException {
        // TODO 1: guarda el estado: boolean autoPrevio = c.getAutoCommit(); y desactívalo: c.setAutoCommit(false).
        // TODO 2: en un try, consulta el presupuesto del origen ("SELECT presupuesto FROM DEPT WHERE id=?").
        // TODO 3: si presupuesto < monto, lanza new PresupuestoException("Presupuesto insuficiente").
        // TODO 4: "UPDATE DEPT SET presupuesto = presupuesto - ? WHERE id=?" (origen) y "+ ?" (destino): unidad atómica.
        // TODO 5: c.commit() si todo fue bien; en catch (SQLException | PresupuestoException) haz c.rollback() y RELANZA.
        // TODO 6: en finally restaura c.setAutoCommit(autoPrevio) (deja la conexión como la encontraste).
    }

    /**
     * Guarda una lista de empleados en una sola transacción: si uno falla (id duplicado),
     * no se guarda ninguno.
     *
     * @return true si se guardaron todos, false si hubo rollback
     */
    public static boolean guardarTodoONada(Connection c, List<Empleado> empleados) throws SQLException {
        // TODO 7: boolean autoPrevio = c.getAutoCommit(); c.setAutoCommit(false).
        // TODO 8: en un try, por cada Empleado "INSERT INTO EMP(id, nombre) VALUES (?,?)" (un id repetido viola la PK).
        // TODO 9: si todos entran, c.commit() y devuelve true.
        // TODO 10: en catch (SQLException) haz c.rollback() y devuelve false; en finally restaura el autoCommit.
        return false;
    }

    public static void main(String[] args) throws SQLException {
        try (Connection c = DriverManager.getConnection("jdbc:h2:mem:demo254", "sa", "")) {
            var st = c.createStatement();
            st.execute("CREATE TABLE DEPT(id INT PRIMARY KEY, nombre VARCHAR(50), presupuesto INT)");
            st.execute("INSERT INTO DEPT VALUES (1,'IT',1000),(2,'RRHH',0)");
            try {
                transferirPresupuesto(c, 1, 2, 400);
                System.out.println("Transferencia OK");
            } catch (PresupuestoException e) {
                System.out.println("Rechazada: " + e.getMessage());
            }
        }
    }

    /**
     * Reto Extra 1: el presupuesto actual de un departamento.
     * @return el presupuesto (el test inserta IT=1000 y lo espera)
     */
    public static int presupuestoDe(Connection c, int id) throws SQLException {
        // GUÍA: "SELECT presupuesto FROM DEPT WHERE id=?"; rs.next(); return rs.getInt(1).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para presupuestoDe");
    }

    /**
     * Reto Extra 2: desactiva el autocommit (inicia una transacción manual).
     * El test comprueba con c.getAutoCommit() == false después.
     */
    public static void desactivarAutocommit(Connection c) throws SQLException {
        // GUÍA: una línea: c.setAutoCommit(false);
        // CULTURA: con autocommit a true cada sentencia se confirma sola; para "todo o nada" hay que apagarlo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desactivarAutocommit");
    }

    /**
     * Reto Extra 3: confirma la transacción en curso.
     * El test solo comprueba que no lanza (assertDoesNotThrow) con autocommit ya desactivado.
     */
    public static void confirmar(Connection c) throws SQLException {
        // GUÍA: una línea: c.commit();
        // OJO: commit() con autocommit en true lanza SQLException; el test lo desactiva antes.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para confirmar");
    }

    /**
     * Reto Extra 4: revierte la transacción en curso.
     * El test solo comprueba que no lanza (assertDoesNotThrow) con autocommit desactivado.
     */
    public static void revertir(Connection c) throws SQLException {
        // GUÍA: una línea: c.rollback();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para revertir");
    }

    /**
     * Reto Extra 5: valida que un monto de transferencia es positivo.
     * @return true si monto > 0 (el test envía 100 -> true y -5 -> false)
     */
    public static boolean montoValido(int monto) {
        // GUÍA: una línea: return monto > 0;
        // OJO: el 0 NO es válido (no tiene sentido transferir 0).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para montoValido");
    }

    /**
     * Reto Extra 6: crea un savepoint con nombre dentro de la transacción.
     * @return el Savepoint creado (el test comprueba que no es null, con autocommit desactivado)
     */
    public static Savepoint crearSavepoint(Connection c, String nombre) throws SQLException {
        // GUÍA: una línea: return c.setSavepoint(nombre);
        // CULTURA: un savepoint es un "punto de retorno" intermedio: puedes deshacer hasta él sin abortar todo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearSavepoint");
    }

    /**
     * Reto Extra 7: revierte hasta un savepoint dado.
     * El test crea el savepoint y comprueba que rollback(sp) no lanza.
     */
    public static void revertirASavepoint(Connection c, Savepoint sp) throws SQLException {
        // GUÍA: una línea: c.rollback(sp);
        // OJO: rollback(sp) deshace solo lo posterior al savepoint, no toda la transacción.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para revertirASavepoint");
    }

    /**
     * Reto Extra 8: transfiere y devuelve el presupuesto resultante del destino.
     * @return presupuestoDe(destino) tras la transferencia (el test: IT=1000 -> RRHH 0 +400 = 400)
     */
    public static int transferirYLeerDestino(Connection c, int idOrigen, int idDestino, int monto)
            throws SQLException, PresupuestoException {
        // GUÍA: llama a transferirPresupuesto(c, idOrigen, idDestino, monto) y luego return presupuestoDe(c, idDestino).
        // CUIDADO: depende de que transferirPresupuesto y presupuestoDe estén implementados.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para transferirYLeerDestino");
    }

    /**
     * Reto Extra 9: fija el nivel de aislamiento SERIALIZABLE.
     * El test comprueba c.getTransactionIsolation() == Connection.TRANSACTION_SERIALIZABLE.
     */
    public static void aislamientoSerializable(Connection c) throws SQLException {
        // GUÍA: una línea: c.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        // CULTURA: SERIALIZABLE es el nivel más estricto (sin fenómenos de concurrencia) y el más caro. Enlaza con b27.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aislamientoSerializable");
    }

    /**
     * Reto Extra 10: ¿tiene el departamento fondos suficientes para un monto?
     * @return true si presupuestoDe(id) >= monto (el test: IT=1000, monto 400 -> true)
     */
    public static boolean hayFondos(Connection c, int id, int monto) throws SQLException {
        // GUÍA: return presupuestoDe(c, id) >= monto;  // reutiliza el reto 1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para hayFondos");
    }
}
