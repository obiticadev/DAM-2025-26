package com.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import com.biblioteca.Clases.Libro;
import com.biblioteca.Clases.Prestamo;
import com.biblioteca.Clases.Usuario;
import com.biblioteca.Enum.Aviso;
import com.biblioteca.Enum.Estado;
import com.biblioteca.Enum.Genero;

public class DAOprestamos {

    private final DAOlibros daoLibros;
    private final DAOusuarios daoUsuarios;

    public DAOprestamos() {
        this.daoLibros = new DAOlibros();
        this.daoUsuarios = new DAOusuarios();
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
        Usuario usuario = daoUsuarios.buscarUsuarioPorId(prestamo.getIdUsuario());
        if (usuario == null) {
            System.out.println("No existe el usuario con ID " + prestamo.getIdUsuario());
            new Logs("Préstamo rechazado: usuario inexistente " + prestamo.getIdUsuario(), Aviso.AVISO).guardarLog();
            return;
        }

        Libro libro = daoLibros.buscarLibroPorId(prestamo.getIdLibro());
        if (libro == null) {
            System.out.println("No existe el libro con ID " + prestamo.getIdLibro());
            new Logs("Préstamo rechazado: libro inexistente " + prestamo.getIdLibro(), Aviso.AVISO).guardarLog();
            return;
        }
        if (libro.getCopiasDisponibles() <= 0) {
            System.out.println("No hay copias disponibles del libro " + libro.getTitulo());
            new Logs("Préstamo rechazado: sin copias disponibles del libro " + prestamo.getIdLibro(), Aviso.AVISO)
                    .guardarLog();
            return;
        }

        String sql = """
                INSERT INTO prestamos(id_usuario, id_libro, fecha_prestamo,
                    fecha_devolucion_prevista, fecha_devolucion_real, estado)
                VALUES(?, ?, ?, ?, ?, ?)
                """;
        String updateLibroSql = "UPDATE libros SET copias_disponibles = copias_disponibles - 1 WHERE id = ?";

        try (Connection conn = Conexion.getConexion()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                    PreparedStatement updLStmt = conn.prepareStatement(updateLibroSql)) {

                pstmt.setInt(1, prestamo.getIdUsuario());
                pstmt.setInt(2, prestamo.getIdLibro());
                pstmt.setString(3, prestamo.getFechaPrestamo().toString());
                pstmt.setString(4, prestamo.getFechaDevolucionPrevista().toString());

                if (prestamo.getFechaDevolucionReal() != null) {
                    pstmt.setString(5, prestamo.getFechaDevolucionReal().toString());
                } else {
                    pstmt.setNull(5, Types.VARCHAR);
                }

                pstmt.setString(6, prestamo.getEstado().name());

                int num = pstmt.executeUpdate();
                if (num > 0) {
                    updLStmt.setInt(1, prestamo.getIdLibro());
                    updLStmt.executeUpdate();
                    conn.commit();
                    System.out.println("Insertado correctamente");
                    new Logs("Préstamo creado: usuario " + prestamo.getIdUsuario()
                            + " libro " + prestamo.getIdLibro(), Aviso.INFO).guardarLog();
                } else {
                    conn.rollback();
                    System.out.println("Ha habido un error en alguna parte");
                    new Logs("Error al insertar préstamo", Aviso.AVISO).guardarLog();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Logs("Error de BD al insertar préstamo: " + e.getMessage(), Aviso.PELIGRO).guardarLog();
        }
    }

    public List<Prestamo> obtenerTodosLosPrestamos() {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamos";

        try (Connection conn = Conexion.getConexion();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");

                int idUsuario = rs.getInt("id_usuario");
                int idLibro = rs.getInt("id_libro");

                LocalDate fechaPrestamo = LocalDate.parse(rs.getString("fecha_prestamo"));
                LocalDate fechaDevolucionPrevista = LocalDate.parse(
                        rs.getString("fecha_devolucion_prevista"));

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

    public List<Prestamo> prestamosActivosDeUnUsuario(int idUsuario) {
        new Logs("Consulta de préstamos activos del usuario " + idUsuario, Aviso.INFO).guardarLog();
        return obtenerTodosLosPrestamos().stream()
                .filter(a -> a.getIdUsuario() == idUsuario && a.getEstado().equals(Estado.ACTIVO))
                .toList();
    }

    public Entry<Integer, Long> libroMasPrestado() {
        new Logs("Consulta de libro más prestado", Aviso.INFO).guardarLog();
        return obtenerTodosLosPrestamos().stream()
                .collect(Collectors.groupingBy(Prestamo::getIdLibro, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
    }

    public Entry<Genero, Long> generoConMasPrestamos() {
        new Logs("Consulta de género con más préstamos", Aviso.INFO).guardarLog();
        List<Libro> libros = daoLibros.obtenerTodosLosLibros();

        return obtenerTodosLosPrestamos().stream()
                .map(p -> libros.stream()
                        .filter(l -> l.getId() == p.getIdLibro())
                        .findFirst()
                        .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Libro::getGenero, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
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
                    new Logs("Devolución fallida: préstamo activo no encontrado " + idPrestamo, Aviso.AVISO)
                            .guardarLog();
                    return false;
                }
            }

            LocalDate fechaReal = LocalDate.now();
            Estado nuevoEstado = fechaReal.isAfter(fechaPrevista) ? Estado.RETRASADO : Estado.DEVUELTO;

            try (PreparedStatement updPStmt = conn.prepareStatement(updatePrestamoSql)) {
                updPStmt.setString(1, nuevoEstado.name());
                updPStmt.setString(2, fechaReal.toString());
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
