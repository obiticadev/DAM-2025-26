package com.biblioteca.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.biblioteca.Clases.Prestamo;
import com.biblioteca.Enum.Aviso;
import com.biblioteca.Enum.Estado;

public class DAOprestamos {

    public DAOprestamos() {
    }

    public void crearTabla() {
        String sql = """
                CREATE TABLE IF NOT EXISTS prestamos (
                    id                          INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_usuario                  INTEGER NOT NULL,
                    id_libro                    INTEGER NOT NULL,
                    fecha_prestamo              TEXT NOT NULL,
                    fecha_devolucion_prevista   TEXT NOT NULL,
                    fecha_devolucion_real       TEXT,
                    estado                      TEXT NOT NULL DEFAULT 'ACTIVO',
                    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
                    FOREIGN KEY (id_libro)   REFERENCES libros(id)
                );
                """;

        try (Connection conn = Conexion.getConexion();
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla PRESTAMOS lista");
            new Logs("Tabla prestamos creada", Aviso.INFO).guardarLog();
        } catch (SQLException e) {
            e.printStackTrace();
            new Logs("Error al crear tabla prestamos: " + e.getMessage(), Aviso.PELIGRO).guardarLog();
        }
    }

    public void insertarPrestamo(Prestamo prestamo) {
        String sql = """
                INSERT INTO prestamos(id_usuario, id_libro, fecha_prestamo,
                    fecha_devolucion_prevista, fecha_devolucion_real, estado)
                VALUES(?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = Conexion.getConexion();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, prestamo.getIdUsuario());
            pstmt.setInt(2, prestamo.getIdLibro());
            pstmt.setDate(3, Date.valueOf(prestamo.getFechaPrestamo()));
            pstmt.setDate(4, Date.valueOf(prestamo.getFechaDevolucionPrevista()));

            // TODO [CORREGIDO] NullPointerException al insertar fecha de devolucion real solucionado.
            if (prestamo.getFechaDevolucionReal() != null) {
                pstmt.setDate(5, Date.valueOf(prestamo.getFechaDevolucionReal()));
            } else {
                pstmt.setNull(5, Types.DATE);
            }

            pstmt.setString(6, prestamo.getEstado().name());

            int num = pstmt.executeUpdate();
            if (num > 0) {
                System.out.println("Insertado correctamente");
                new Logs("Préstamo creado: usuario " + prestamo.getIdUsuario()
                        + " libro " + prestamo.getIdLibro(), Aviso.INFO).guardarLog();
            } else {
                System.out.println("Ha habido un error en alguna parte");
                new Logs("Error al insertar préstamo", Aviso.AVISO).guardarLog();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Logs("Error de BD al insertar préstamo: " + e.getMessage(), Aviso.PELIGRO).guardarLog();
        }
    }

    // TODO [CÓDIGO FALTANTE] Añadir validación antes de insertar préstamo:
    //  → Verificar que el usuario exista (DAOusuarios.buscarUsuarioPorId).
    //  → Verificar que el libro exista y tenga copias_disponibles > 0.
    //  → Al insertar, decrementar copias_disponibles del libro (UPDATE libros SET copias_disponibles = copias_disponibles - 1 WHERE id = ?).

    public List<Prestamo> obtenerTodosLosPrestamos() {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamos";

        try (Connection conn = Conexion.getConexion();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");

                // TODO [CORREGIDO] Nombres de columna en BD aplicados.
                int idUsuario = rs.getInt("id_usuario");
                int idLibro = rs.getInt("id_libro");

                // TODO [CORREGIDO] Nombres de columna en BD aplicados.
                LocalDate fechaPrestamo = LocalDate.parse(rs.getString("fecha_prestamo"));
                LocalDate fechaDevolucionPrevista = LocalDate.parse(
                        rs.getString("fecha_devolucion_prevista"));

                // TODO [CORREGIDO] Bug null pointer al parsear fecha_devolucion_real
                String fdrStr = rs.getString("fecha_devolucion_real");
                LocalDate fechaDevolucionReal = fdrStr != null ? LocalDate.parse(fdrStr) : null;

                Estado estado = Estado.valueOf(rs.getString("estado"));

                lista.add(new Prestamo(id, idUsuario, idLibro, fechaPrestamo,
                        fechaDevolucionPrevista, fechaDevolucionReal, estado));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Logs("Error al obtener préstamos: " + e.getMessage(), Aviso.PELIGRO).guardarLog();
        }
        new Logs("Obtenidos " + lista.size() + " préstamos", Aviso.INFO).guardarLog();
        return lista;
    }

    // TODO [PRÁCTICA STREAMS] Reescribir prestamosActivosDeUnUsuario(int idUsuario) completo.
    // → Objetivo: Obtener la lista completa de préstamos con obtenerTodosLosPrestamos() y usar Streams.
    // → Filtrar aquellos préstamos cuyo idUsuario coincida con el pasado por parámetro y cuyo estado sea Estado.ACTIVO.
    // → Opcionalmente, imprimir el título del libro recuperando el objeto Libro con DAOlibros.
    public void prestamosActivosDeUnUsuario() {
        String sql = """
                SELECT l.titulo, p.fecha_prestamo, p.fecha_devolucion_prevista
                FROM prestamos p
                JOIN libros l ON p.id_libro = l.id
                WHERE p.id_usuario = ? AND p.estado = 'activo'""";

        try (Connection conn = Conexion.getConexion();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Lista de usuarios:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " - " +
                                rs.getString("nombre") + " - " +
                                rs.getInt("edad"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Logs("Error préstamos activos: " + e.getMessage(), Aviso.PELIGRO).guardarLog();
        }
    }

    // TODO [PRÁCTICA STREAMS] Implementar libroMasPrestado().
    // → Objetivo: Usar la lista de préstamos de obtenerTodosLosPrestamos() para agrupar por idLibro (Collectors.groupingBy)
    // y contar el número de préstamos (Collectors.counting). Encontrar la entrada del mapa con el valor máximo.
    // → Imprimir el resultado (puedes apoyarte en DAOlibros para mostrar el título).
    public void libroMasPrestado() {
        new Logs("Consulta de libro más prestado", Aviso.INFO).guardarLog();
    }

    // TODO [PRÁCTICA STREAMS] Corregir generoConMasPrestamos().
    // → Objetivo: Obtener la lista de préstamos, y por cada uno, buscar su género a través del DAOlibros.
    // → Agrupar por género y contar con Streams, encontrando luego el género más frecuente.
    // → Reemplazar por completo el código SQL incorrecto de este método por la solución con colecciones.
    public void generoConMasPrestamos() {
        String sql = """
                SELECT l.genero, COUNT(*) AS total
                FROM prestamos p
                JOIN libros l ON p.id_libro = l.id
                GROUP BY l.genero
                ORDER BY total DESC""";

        try (Connection conn = Conexion.getConexion();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Lista de usuarios:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " - " +
                                rs.getString("nombre") + " - " +
                                rs.getInt("edad"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Logs("Error género más prestado: " + e.getMessage(), Aviso.PELIGRO).guardarLog();
        }
    }

    public boolean devolverPrestamo(int idPrestamo) {
        String selectSql = "SELECT id_libro, fecha_devolucion_prevista FROM prestamos WHERE id = ? AND estado = 'ACTIVO'";
        String updatePrestamoSql = "UPDATE prestamos SET estado = ?, fecha_devolucion_real = ? WHERE id = ?";
        String updateLibroSql = "UPDATE libros SET copias_disponibles = copias_disponibles + 1 WHERE id = ?";

        try (Connection conn = Conexion.getConexion()) {
            conn.setAutoCommit(false);

            int idLibro = -1;
            LocalDate fechaPrevista = null;

            try (PreparedStatement selStmt = conn.prepareStatement(selectSql)) {
                selStmt.setInt(1, idPrestamo);
                ResultSet rs = selStmt.executeQuery();
                if (rs.next()) {
                    idLibro = rs.getInt("id_libro");
                    fechaPrevista = LocalDate.parse(rs.getString("fecha_devolucion_prevista"));
                } else {
                    System.out.println("No se encontró el préstamo activo con ID " + idPrestamo);
                    return false;
                }
            }

            LocalDate fechaReal = LocalDate.now();
            Estado nuevoEstado = fechaReal.isAfter(fechaPrevista) ? Estado.RETRASADO : Estado.DEVUELTO;

            try (PreparedStatement updPStmt = conn.prepareStatement(updatePrestamoSql)) {
                updPStmt.setString(1, nuevoEstado.name());
                updPStmt.setDate(2, Date.valueOf(fechaReal));
                updPStmt.setInt(3, idPrestamo);
                updPStmt.executeUpdate();
            }

            try (PreparedStatement updLStmt = conn.prepareStatement(updateLibroSql)) {
                updLStmt.setInt(1, idLibro);
                updLStmt.executeUpdate();
            }

            conn.commit();
            new Logs("Préstamo " + idPrestamo + " devuelto con estado " + nuevoEstado, Aviso.INFO).guardarLog();
            System.out.println("Préstamo devuelto correctamente. Estado: " + nuevoEstado);
            return true;
        } catch (SQLException e) {
            new Logs("Error devolviendo préstamo: " + e.getMessage(), Aviso.PELIGRO).guardarLog();
            return false;
        }
    }
}
