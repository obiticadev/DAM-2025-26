package com.masterclass.api.b11_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Ejercicio 098 · Inserción por lotes (batch).
 *
 * <p>Teoría: {@code teoria/11_JDBC_Profundo.md} (sección 11.6).
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
        // GUÍA: teoría 11.6 — la plantilla parametrizada que se reusa en cada addBatch.
        // Una línea: return "INSERT INTO REGISTRO(id, texto) VALUES (?, ?)";
        // OJO: el test compara con equals — aquí la tabla es REGISTRO(id, texto) y hay
        //      espacios tras las comas y alrededor (VALUES (?, ?)). Cópialo literal.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioObtenerSqlInsertBatch");
    }

    /**
     * TODO extra 2: Añade parámetros individuales al lote del PreparedStatement.
     */
    public static void desafioAñadirAlLote(java.sql.PreparedStatement ps, int id, String texto) throws java.sql.SQLException {
        // GUÍA: teoría 11.6 — setear parámetros y ACUMULAR con addBatch (no ejecuta).
        // 1. ps.setInt(1, id);
        // 2. ps.setString(2, texto);
        // 3. ps.addBatch();
        // OJO: no olvides el addBatch() final — sin él no se acumula nada.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioAñadirAlLote");
    }

    /**
     * TODO extra 3: Ejecuta las operaciones acumuladas en el lote.
     */
    public static int[] desafioEjecutarLote(java.sql.PreparedStatement ps) throws java.sql.SQLException {
        // GUÍA: teoría 11.6 — executeBatch envía todo de golpe y devuelve un int[].
        // Una línea: return ps.executeBatch();
        // OJO: el test acumula 1 sentencia y espera que res.length == 1 (un resultado por sentencia).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioEjecutarLote");
    }

    /**
     * TODO extra 4: Valida que el tamaño de la lista de datos a procesar sea positivo.
     */
    public static boolean desafioValidarTamañoDatos(java.util.List<?> datos) {
        // GUÍA: un batch vacío no tiene sentido (teoría 11.6: insertarLote devuelve 0 si vacío).
        // Una línea: return datos != null && !datos.isEmpty();
        // OJO: el test manda List.of("a") (true) y List.of() (false).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioValidarTamañoDatos");
    }

    /**
     * TODO extra 5: Comprueba si todos los resultados del lote indican éxito (filas > 0 o SUCCESS_NO_INFO).
     */
    public static boolean desafioVerificarExitoLote(int[] resultados) {
        // GUÍA: teoría 11.6 (tabla de valores del int[]). Un negativo es sospechoso,
        // SALVO SUCCESS_NO_INFO (-2), que significa "ok pero sin conteo".
        // for (int r : resultados) if (r < 0 && r != Statement.SUCCESS_NO_INFO) return false;
        // return true;
        // OJO: el test {1, 2, SUCCESS_NO_INFO} → true; {1, EXECUTE_FAILED} → false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioVerificarExitoLote");
    }

    /**
     * TODO extra 6: Calcula el total de filas afectadas sumando los resultados individuales.
     */
    public static int desafioSumarFilasAfectadas(int[] resultados) {
        // GUÍA: teoría 11.6 — suma SOLO los valores >= 0 (ignora -2 y -3).
        // int sum = 0; for (int r : resultados) if (r > 0) sum += r; return sum;
        // OJO: el test {1, 2} espera 3.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioSumarFilasAfectadas");
    }

    /**
     * TODO extra 7: Lanza una excepción si el lote contiene un fallo específico.
     */
    public static void desafioValidarSinFallosLote(int[] resultados) {
        // GUÍA: teoría 11.6 — EXECUTE_FAILED (-3) marca una sentencia que falló.
        // for (int r : resultados)
        //     if (r == Statement.EXECUTE_FAILED) throw new IllegalStateException("Fallo detectado en lote");
        // OJO: el test pasa {EXECUTE_FAILED} y espera IllegalStateException.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioValidarSinFallosLote");
    }

    /**
     * TODO extra 8: Vacía la lista de sentencias del lote de forma segura.
     */
    public static void desafioLimpiarLote(java.sql.PreparedStatement ps) throws java.sql.SQLException {
        // GUÍA: teoría 11.6 — clearBatch descarta lo acumulado sin ejecutarlo.
        // Una línea: ps.clearBatch();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioLimpiarLote");
    }

    /**
     * TODO extra 9: Comprueba si el tamaño del lote actual es divisible por un tamaño de batch (por ejemplo, 100).
     */
    public static boolean desafioEsLimiteLote(int indiceActual, int tamañoBatch) {
        // GUÍA: teoría 11.6 — el "flush periódico": ejecutar cada N filas.
        // Una línea: return indiceActual > 0 && indiceActual % tamañoBatch == 0;
        // OJO: (10, 5) → true (10 es múltiplo de 5); (8, 5) → false. El > 0 evita
        //      disparar en el índice 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioEsLimiteLote");
    }

    /**
     * TODO extra 10: Retorna un mensaje resumido sobre la ejecución del lote.
     */
    public static String desafioResumirEjecucionLote(int[] resultados) {
        // GUÍA: reutiliza el reto 6 para el total (teoría 11.6).
        // return "Lote ejecutado. Filas: " + desafioSumarFilasAfectadas(resultados);
        // OJO: el test {1, 1} espera EXACTAMENTE "Lote ejecutado. Filas: 2"
        //      (respeta el punto, los espacios y las mayúsculas).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioResumirEjecucionLote");
    }

}
