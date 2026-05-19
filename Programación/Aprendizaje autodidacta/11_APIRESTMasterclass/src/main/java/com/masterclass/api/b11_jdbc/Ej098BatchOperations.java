package com.masterclass.api.b11_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Ejercicio 098 · Inserción por lotes (batch).
 *
 * <p>Teoría: {@code teoria/11_JDBC_Profundo.md} (sección 11.1).
 *
 * <p>Tabla LOG(id INT, msg VARCHAR) (la crea el test). El batch reduce viajes a BD.
 */
public final class Ej098BatchOperations {

    private Ej098BatchOperations() {
    }

    /**
     * Inserta todos los mensajes en un único batch.
     *
     * @param conn     conexión
     * @param mensajes lista de mensajes (el índice 0-based será el id)
     * @return número total de filas insertadas
     * @throws SQLException si falla el batch
     */
    public static int insertarLote(Connection conn, List<String> mensajes) throws SQLException {
        // TODO 1: si mensajes es null/vacío, devuelve 0 (nada que insertar).
        // TODO 2: prepara "INSERT INTO LOG(id,msg) VALUES (?,?)".
        // TODO 3: recorre la lista con índice.
        // TODO 4: para cada elemento, setInt(1, indice) y setString(2, msg).
        // TODO 5: ps.addBatch() acumula la operación (NO ejecuta aún).
        // TODO 6: tras el bucle, ps.executeBatch() envía TODO de una vez.
        // TODO 7: executeBatch devuelve un int[] con filas por sentencia.
        // TODO 8: suma ese array para obtener el total de filas.
        // TODO 9: usa try-with-resources para el PreparedStatement.
        // TODO 10: devuelve el total insertado.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con H2 en memoria");
    }
}
