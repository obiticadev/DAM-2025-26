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

            // TODO [BUG CRÍTICO] NullPointerException aquí.
            //  → Al crear un préstamo, fechaDevolucionReal es null.
            //  → Date.valueOf(null) lanza NullPointerException.
            //  → SOLUCIÓN: Comprobar si es null antes:
            //     if (prestamo.getFechaDevolucionReal() != null) {
            //         pstmt.setDate(5, Date.valueOf(prestamo.getFechaDevolucionReal()));
            //     } else {
            //         pstmt.setNull(5, Types.DATE);
            //     }
            pstmt.setDate(5, Date.valueOf(prestamo.getFechaDevolucionReal()));

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

                // TODO [BUG] Nombres de columna incorrectos (usar snake_case):
                //  → "idUsuario" → "id_usuario"
                //  → "idLibro"   → "id_libro"
                int idUsuario = rs.getInt("idUsuario");
                int idLibro = rs.getInt("idLibro");

                // TODO [BUG] Nombres de columna incorrectos:
                //  → "fechaPrestamo"            → "fecha_prestamo"
                //  → "fechaDevolucionPrevista"  → "fecha_devolucion_prevista"
                //  → "fechaDevolucionReal"      → "fecha_devolucion_real"
                LocalDate fechaPrestamo = LocalDate.parse(rs.getString("fechaPrestamo"));
                LocalDate fechaDevolucionPrevista = LocalDate.parse(
                        rs.getString("fechaDevolucionPrevista"));

                // TODO [BUG] fecha_devolucion_real puede ser NULL en la BD.
                //  → LocalDate.parse(null) lanza NullPointerException.
                //  → SOLUCIÓN:
                //     String fdrStr = rs.getString("fecha_devolucion_real");
                //     LocalDate fechaDevolucionReal = fdrStr != null ? LocalDate.parse(fdrStr) : null;
                LocalDate fechaDevolucionReal = LocalDate.parse(
                        rs.getString("fechaDevolucionReal"));

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

    // TODO [BUG + CÓDIGO FALTANTE] Reescribir prestamosActivosDeUnUsuario() completo.
    //  ERRORES ACTUALES:
    //  1) Usa Statement pero el SQL tiene ? → debe usar PreparedStatement.
    //  2) No recibe idUsuario como parámetro → añadirlo: (int idUsuario).
    //  3) Compara estado = 'activo' (minúscula) pero el enum guarda 'ACTIVO' (mayúscula).
    //  4) El while lee columnas id/nombre/edad que NO existen en el SELECT.
    //
    //  IMPLEMENTACIÓN CORRECTA:
    //  → Firma: public List<Prestamo> prestamosActivosDeUnUsuario(int idUsuario)
    //  → SQL: SELECT l.titulo, p.fecha_prestamo, p.fecha_devolucion_prevista
    //         FROM prestamos p JOIN libros l ON p.id_libro = l.id
    //         WHERE p.id_usuario = ? AND p.estado = 'ACTIVO'
    //  → Usar PreparedStatement y setInt(1, idUsuario).
    //  → Leer las columnas reales del SELECT: titulo, fecha_prestamo, fecha_devolucion_prevista.
    //  → En App.java, pasar el idUsuario leído al método.
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

    // TODO [CÓDIGO FALTANTE] Implementar libroMasPrestado().
    //  → SQL (ya definido en doc/base_de_datos.md):
    //     SELECT l.titulo, COUNT(*) AS total
    //     FROM prestamos p JOIN libros l ON p.id_libro = l.id
    //     GROUP BY p.id_libro ORDER BY total DESC LIMIT 1
    //  → Imprimir: "El libro más prestado es: <titulo> con <total> préstamos"
    //  → Si no hay préstamos, mostrar mensaje informativo.
    public void libroMasPrestado() {
        new Logs("Consulta de libro más prestado", Aviso.INFO).guardarLog();
    }

    // TODO [BUG] Corregir generoConMasPrestamos(): el while lee id/nombre/edad
    //  pero el SELECT devuelve genero y total.
    //  → Cambiar el while a:
    //     System.out.println(rs.getString("genero") + " - " + rs.getInt("total") + " préstamos");
    //  → Cambiar el mensaje "Lista de usuarios:" → "Géneros más prestados:"
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

    // TODO [CÓDIGO FALTANTE] Implementar devolverPrestamo(int idPrestamo).
    //  → SQL: UPDATE prestamos SET estado = 'DEVUELTO', fecha_devolucion_real = ? WHERE id = ?
    //  → Además, incrementar copias_disponibles del libro:
    //     UPDATE libros SET copias_disponibles = copias_disponibles + 1 WHERE id = ?
    //  → Verificar si la devolución es tardía (fecha actual > fecha_devolucion_prevista)
    //    y en ese caso poner estado = 'RETRASADO' en vez de 'DEVUELTO'.
    //  → Añadir opción "Devolver préstamo" en el menú de préstamos de App.java.
}
