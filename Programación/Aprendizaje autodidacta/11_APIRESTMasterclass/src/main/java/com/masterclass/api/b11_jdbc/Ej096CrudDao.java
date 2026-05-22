package com.masterclass.api.b11_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Ejercicio 096 · DAO CRUD con JDBC puro.
 *
 * <p>Teoría: {@code teoria/11_JDBC_Profundo.md} (sección 11.1).
 *
 * <p>Tabla CLIENTE(id INT PRIMARY KEY, nombre VARCHAR) (la crea el test).
 */
public final class Ej096CrudDao {

    private final Connection conn;

    public Ej096CrudDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * @param id     id
     * @param nombre nombre
     * @throws SQLException si falla el INSERT
     */
    public void crear(int id, String nombre) throws SQLException {
        // TODO 1: prepara "INSERT INTO CLIENTE(id,nombre) VALUES (?,?)".
        // TODO 2: setea parámetros y executeUpdate (try-with-resources).
    }

    /**
     * @param id id buscado
     * @return el nombre, o null si no existe
     * @throws SQLException si falla la query
     */
    public String leerNombre(int id) throws SQLException {
        // TODO 3: prepara "SELECT nombre FROM CLIENTE WHERE id=?".
        // TODO 4: ejecuta; si rs.next() devuelve el nombre.
        // TODO 5: si no hay fila, devuelve null.
        return null;
    }

    /**
     * @param id     id a actualizar
     * @param nombre nuevo nombre
     * @return true si se actualizó alguna fila
     * @throws SQLException si falla el UPDATE
     */
    public boolean actualizar(int id, String nombre) throws SQLException {
        // TODO 6: prepara "UPDATE CLIENTE SET nombre=? WHERE id=?".
        // TODO 7: executeUpdate devuelve nº de filas; true si > 0.
        return false;
    }

    /**
     * @param id id a borrar
     * @return true si se borró
     * @throws SQLException si falla el DELETE
     */
    public boolean borrar(int id) throws SQLException {
        // TODO 8: prepara "DELETE FROM CLIENTE WHERE id=?".
        // TODO 9: executeUpdate; true si afectó alguna fila.
        // TODO 10: cada método abre y cierra su PreparedStatement (no fugas de recursos).
        return false;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con H2 en memoria");
    }

    /**
     * TODO extra 1: Retorna el SQL para insertar un cliente.
     */
    public static String desafioObtenerSqlInsert() {
        return "INSERT INTO CLIENTE(id,nombre,email) VALUES (?,?,?)";
    }

    /**
     * TODO extra 2: Retorna el SQL para buscar un cliente por ID.
     */
    public static String desafioObtenerSqlFindById() {
        return "SELECT id,nombre,email FROM CLIENTE WHERE id = ?";
    }

    /**
     * TODO extra 3: Retorna el SQL para listar todos los clientes.
     */
    public static String desafioObtenerSqlFindAll() {
        return "SELECT id,nombre,email FROM CLIENTE";
    }

    /**
     * TODO extra 4: Retorna el SQL para eliminar un cliente por ID.
     */
    public static String desafioObtenerSqlDelete() {
        return "DELETE FROM CLIENTE WHERE id = ?";
    }

    /**
     * TODO extra 5: Comprueba si un Cliente es válido para inserción.
     */
    public static void desafioValidarCliente(Cliente c) {
        if (c == null || c.nombre() == null || c.email() == null) {
            throw new IllegalArgumentException("Cliente invalido");
        }
    }

    /**
     * TODO extra 6: Mapea una fila de ResultSet a un Cliente.
     */
    public static Cliente desafioMapearCliente(java.sql.ResultSet rs) throws java.sql.SQLException {
        return new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"));
    }

    /**
     * TODO extra 7: Configura los parámetros para eliminar un cliente.
     */
    public static void desafioConfigurarEliminar(java.sql.PreparedStatement ps, int id) throws java.sql.SQLException {
        ps.setInt(1, id);
    }

    /**
     * TODO extra 8: Verifica si se ha insertado con éxito analizando las filas afectadas.
     */
    public static boolean desafioVerificarFilaAfectada(int filasAfectadas) {
        return filasAfectadas > 0;
    }

    /**
     * TODO extra 9: Comprueba que una lista de clientes no contenga elementos nulos.
     */
    public static boolean desafioNoTieneNulos(java.util.List<Cliente> clientes) {
        return clientes.stream().allMatch(java.util.Objects::nonNull);
    }

    /**
     * TODO extra 10: Retorna un cliente vacío por defecto para simular un Fallback.
     */
    public static Cliente desafioClienteVacíoFallback() {
        return new Cliente(0, "Sin Nombre", "sin@email.com");
    }

}
