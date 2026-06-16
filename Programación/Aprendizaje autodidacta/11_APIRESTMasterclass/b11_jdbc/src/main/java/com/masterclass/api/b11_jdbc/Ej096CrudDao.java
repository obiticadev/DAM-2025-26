package com.masterclass.api.b11_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Ejercicio 096 · DAO CRUD con JDBC puro.
 *
 * <p>Teoría: {@code teoria/11_JDBC_Profundo.md} (sección 11.4).
 *
 * <p>Tabla CLIENTE(id INT PRIMARY KEY, nombre VARCHAR) (la crea el test). Los
 * retos extra trabajan sobre una variante con columna {@code email} y el record
 * {@link Cliente} de paquete.
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
        // GUÍA: teoría 11.4 — INSERT parametrizado con las 3 columnas.
        // Una línea: return "INSERT INTO CLIENTE(id,nombre,email) VALUES (?,?,?)";
        // OJO: el test compara con equals — fíjate en que aquí son TRES columnas
        //      (id,nombre,email) y TRES marcadores (?,?,?).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioObtenerSqlInsert");
    }

    /**
     * TODO extra 2: Retorna el SQL para buscar un cliente por ID.
     */
    public static String desafioObtenerSqlFindById() {
        // GUÍA: teoría 11.4. Una línea:
        // return "SELECT id,nombre,email FROM CLIENTE WHERE id = ?";
        // OJO: el test exige espacios alrededor del '=' ("WHERE id = ?").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioObtenerSqlFindById");
    }

    /**
     * TODO extra 3: Retorna el SQL para listar todos los clientes.
     */
    public static String desafioObtenerSqlFindAll() {
        // GUÍA: teoría 11.4. Una línea: return "SELECT id,nombre,email FROM CLIENTE";
        // OJO: sin WHERE ni ORDER BY — el test compara la cadena exacta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioObtenerSqlFindAll");
    }

    /**
     * TODO extra 4: Retorna el SQL para eliminar un cliente por ID.
     */
    public static String desafioObtenerSqlDelete() {
        // GUÍA: teoría 11.4. Una línea: return "DELETE FROM CLIENTE WHERE id = ?";
        // OJO: el test exige "WHERE id = ?" con espacios.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioObtenerSqlDelete");
    }

    /**
     * TODO extra 5: Comprueba si un Cliente es válido para inserción.
     */
    public static void desafioValidarCliente(Cliente c) {
        // GUÍA: validación defensiva antes de tocar la BD (teoría 1.9).
        // if (c == null || c.nombre() == null || c.email() == null)
        //     throw new IllegalArgumentException("Cliente invalido");
        // OJO: el test espera IllegalArgumentException con null, y NINGUNA con
        //      new Cliente(1, "A", "b@c.com"). El mensaje "Cliente invalido" va sin tilde.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioValidarCliente");
    }

    /**
     * TODO extra 6: Mapea una fila de ResultSet a un Cliente.
     */
    public static Cliente desafioMapearCliente(java.sql.ResultSet rs) throws java.sql.SQLException {
        // GUÍA: teoría 11.3/11.9 — fila → objeto (igual que un RowMapper a mano).
        // return new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"));
        // OJO: el test inserta (1,'Ana','a@b.com') y comprueba id()=1, nombre()="Ana",
        //      email()="a@b.com": lee por nombre de columna.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioMapearCliente");
    }

    /**
     * TODO extra 7: Configura los parámetros para eliminar un cliente.
     */
    public static void desafioConfigurarEliminar(java.sql.PreparedStatement ps, int id) throws java.sql.SQLException {
        // GUÍA: teoría 11.2 — el único parámetro del DELETE es el índice 1.
        // Una línea: ps.setInt(1, id);
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioConfigurarEliminar");
    }

    /**
     * TODO extra 8: Verifica si se ha insertado con éxito analizando las filas afectadas.
     */
    public static boolean desafioVerificarFilaAfectada(int filasAfectadas) {
        // GUÍA: teoría 11.4 — executeUpdate devuelve nº de filas; > 0 = hubo cambio.
        // Una línea: return filasAfectadas > 0;
        // OJO: el test manda 1 (true) y 0 (false).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioVerificarFilaAfectada");
    }

    /**
     * TODO extra 9: Comprueba que una lista de clientes no contenga elementos nulos.
     */
    public static boolean desafioNoTieneNulos(java.util.List<Cliente> clientes) {
        // GUÍA: streams (teoría 1.3) — allMatch con la referencia Objects::nonNull.
        // Una línea: return clientes.stream().allMatch(java.util.Objects::nonNull);
        // OJO: el test pasa una lista de un Cliente no nulo y espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioNoTieneNulos");
    }

    /**
     * TODO extra 10: Retorna un cliente vacío por defecto para simular un Fallback.
     */
    public static Cliente desafioClienteVacíoFallback() {
        // GUÍA: el patrón "objeto nulo" / fallback (teoría 1.2: alternativa a null).
        // Una línea: return new Cliente(0, "Sin Nombre", "sin@email.com");
        // OJO: el test comprueba id()=0 y nombre()="Sin Nombre" (con espacio y mayúsculas).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioClienteVacíoFallback");
    }

}
