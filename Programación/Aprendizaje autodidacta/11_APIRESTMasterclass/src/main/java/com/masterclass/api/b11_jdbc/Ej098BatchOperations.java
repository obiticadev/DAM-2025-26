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

    /**
     * TODO extra 1: Retorna el SQL de inserción en lote.
     */
    public static String desafioObtenerSqlInsertBatch() {
        return "INSERT INTO REGISTRO(id, texto) VALUES (?, ?)";
    }

    /**
     * TODO extra 2: Añade parámetros individuales al lote del PreparedStatement.
     */
    public static void desafioAñadirAlLote(java.sql.PreparedStatement ps, int id, String texto) throws java.sql.SQLException {
        ps.setInt(1, id);
        ps.setString(2, texto);
        ps.addBatch();
    }

    /**
     * TODO extra 3: Ejecuta las operaciones acumuladas en el lote.
     */
    public static int[] desafioEjecutarLote(java.sql.PreparedStatement ps) throws java.sql.SQLException {
        return ps.executeBatch();
    }

    /**
     * TODO extra 4: Valida que el tamaño de la lista de datos a procesar sea positivo.
     */
    public static boolean desafioValidarTamañoDatos(java.util.List<?> datos) {
        return datos != null && !datos.isEmpty();
    }

    /**
     * TODO extra 5: Comprueba si todos los resultados del lote indican éxito (filas > 0 o SUCCESS_NO_INFO).
     */
    public static boolean desafioVerificarExitoLote(int[] resultados) {
        for (int r : resultados) {
            if (r < 0 && r != java.sql.Statement.SUCCESS_NO_INFO) {
                return false;
            }
        }
        return true;
    }

    /**
     * TODO extra 6: Calcula el total de filas afectadas sumando los resultados individuales.
     */
    public static int desafioSumarFilasAfectadas(int[] resultados) {
        int sum = 0;
        for (int r : resultados) {
            if (r > 0) sum += r;
        }
        return sum;
    }

    /**
     * TODO extra 7: Lanza una excepción si el lote contiene un fallo específico.
     */
    public static void desafioValidarSinFallosLote(int[] resultados) {
        for (int r : resultados) {
            if (r == java.sql.Statement.EXECUTE_FAILED) {
                throw new IllegalStateException("Fallo detectado en lote");
            }
        }
    }

    /**
     * TODO extra 8: Vacía la lista de sentencias del lote de forma segura.
     */
    public static void desafioLimpiarLote(java.sql.PreparedStatement ps) throws java.sql.SQLException {
        ps.clearBatch();
    }

    /**
     * TODO extra 9: Comprueba si el tamaño del lote actual es divisible por un tamaño de batch (por ejemplo, 100).
     */
    public static boolean desafioEsLimiteLote(int indiceActual, int tamañoBatch) {
        return indiceActual > 0 && indiceActual % tamañoBatch == 0;
    }

    /**
     * TODO extra 10: Retorna un mensaje resumido sobre la ejecución del lote.
     */
    public static String desafioResumirEjecucionLote(int[] resultados) {
        return "Lote ejecutado. Filas: " + desafioSumarFilasAfectadas(resultados);
    }

}
