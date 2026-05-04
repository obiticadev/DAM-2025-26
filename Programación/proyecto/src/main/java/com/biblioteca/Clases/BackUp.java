package com.biblioteca.Clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import java.sql.Connection;
import java.sql.Statement;

import com.biblioteca.Enum.Estado;
import com.biblioteca.Enum.Formato;
import com.biblioteca.Enum.Genero;
import com.biblioteca.Enum.Tipo;
import com.biblioteca.dao.Conexion;
import com.biblioteca.dao.DAOlibros;
import com.biblioteca.dao.DAOprestamos;
import com.biblioteca.dao.DAOusuarios;

public class BackUp {
    private static final String ARCHIVO_LIBROS = "backup_libros.csv";
    private static final String ARCHIVO_USUARIOS = "backup_usuarios.csv";
    private static final String ARCHIVO_PRESTAMOS = "backup_prestamos.csv";

    private DAOlibros daoLibros;
    private DAOusuarios daoUsuarios;
    private DAOprestamos daoPrestamos;

    public BackUp() {
        this.daoLibros = new DAOlibros();
        this.daoUsuarios = new DAOusuarios();
        this.daoPrestamos = new DAOprestamos();
    }

    public void crearBackup() {
        List<Libro> libros = daoLibros.obtenerTodosLosLibros();
        List<Usuario> usuarios = daoUsuarios.obtenerTodosLosUsuarios();
        List<Prestamo> prestamos = daoPrestamos.obtenerTodosLosPrestamos();

        crearBackupLibros(libros);
        crearBackupUsuarios(usuarios);
        crearBackupPrestamos(prestamos);

        System.out.println("Backup creado correctamente en carpeta del proyecto");
    }

    private void crearBackupLibros(List<Libro> libros) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_LIBROS))) {
            bw.write("id,titulo,autor,genero,isbn,anioPublicacion,copiasTotales,copiasDisponibles,tipo,subtipo,formato,urlDescarga,ubicacion");
            bw.newLine();

            for (Libro libro : libros) {
                StringBuilder sb = new StringBuilder();
                sb.append(libro.getId()).append(",");
                sb.append(escapeCSV(libro.getTitulo())).append(",");
                sb.append(escapeCSV(libro.getAutor())).append(",");
                sb.append(libro.getGenero().name()).append(",");
                sb.append(libro.getIsbn()).append(",");
                sb.append(libro.getAnioPublicacion()).append(",");
                sb.append(libro.getCopiasTotales()).append(",");
                sb.append(libro.getCopiasDisponibles()).append(",");
                sb.append(libro.getTipo()).append(",");

                if (libro instanceof LibroElectronico le) {
                    sb.append("ELECTRONICO,");
                    sb.append(le.getFormato().name()).append(",");
                    sb.append(escapeCSV(le.getUrlDescarga())).append(",");
                } else if (libro instanceof LibroEnPapel lp) {
                    sb.append("PAPEL,,,");
                    sb.append(escapeCSV(lp.getUbicacion()));
                }

                bw.write(sb.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al crear backup de libros: " + e.getMessage());
        }
    }

    private void crearBackupUsuarios(List<Usuario> usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_USUARIOS))) {
            bw.write("id,nombre,apellido,email,telefono,fechaRegistro");
            bw.newLine();

            for (Usuario usuario : usuarios) {
                bw.write(usuario.getId() + ",");
                bw.write(escapeCSV(usuario.getNombre()) + ",");
                bw.write(escapeCSV(usuario.getApellido()) + ",");
                bw.write(escapeCSV(usuario.getEmail()) + ",");
                bw.write(escapeCSV(usuario.getTelefono()) + ",");
                bw.write(usuario.getFechaRegistro().toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al crear backup de usuarios: " + e.getMessage());
        }
    }

    private void crearBackupPrestamos(List<Prestamo> prestamos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_PRESTAMOS))) {
            bw.write("id,idUsuario,idLibro,fechaPrestamo,fechaDevolucionPrevista,fechaDevolucionReal,estado");
            bw.newLine();

            for (Prestamo prestamo : prestamos) {
                bw.write(prestamo.getId() + ",");
                bw.write(prestamo.getIdUsuario() + ",");
                bw.write(prestamo.getIdLibro() + ",");
                bw.write(prestamo.getFechaPrestamo() + ",");
                bw.write(prestamo.getFechaDevolucionPrevista() + ",");
                String fechaReal = prestamo.getFechaDevolucionReal() != null 
                    ? prestamo.getFechaDevolucionReal().toString() 
                    : "";
                bw.write(fechaReal + ",");
                bw.write(prestamo.getEstado().name());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al crear backup de prestamos: " + e.getMessage());
        }
    }

    private String escapeCSV(String value) {
        if (value == null) return "";
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }

    public void restaurarBackup() {
        try (Connection conn = Conexion.getConexion(); Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM prestamos");
            stmt.execute("DELETE FROM libros");
            stmt.execute("DELETE FROM usuarios");
        } catch (Exception e) {
            System.err.println("Error al limpiar datos: " + e.getMessage());
        }

        restaurarLibros();
        restaurarUsuarios();
        restaurarPrestamos();
        System.out.println("Backup restaurado correctamente");
    }

    private void restaurarLibros() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_LIBROS))) {
            br.readLine();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                String titulo = datos[1];
                String autor = datos[2];
                Genero genero = Genero.valueOf(datos[3]);
                String isbn = datos[4];
                LocalDate anio = LocalDate.parse(datos[5]);
                int copiasTotales = Integer.parseInt(datos[6]);
                int copiasDisp = Integer.parseInt(datos[7]);
                Tipo tipo = Tipo.valueOf(datos[8]);

                if ("ELECTRONICO".equals(datos[9])) {
                    Formato formato = Formato.valueOf(datos[10]);
                    String url = datos[11];
                    daoLibros.insertarLibro(new LibroElectronico(id, titulo, autor, genero, isbn, anio, 
                        copiasTotales, copiasDisp, tipo, id, formato, url));
                } else {
                    String ubicacion = datos[12];
                    daoLibros.insertarLibro(new LibroEnPapel(id, titulo, autor, genero, isbn, anio, 
                        copiasTotales, copiasDisp, tipo, id, ubicacion));
                }
            }
        } catch (Exception e) {
            System.err.println("Error al restaurar libros: " + e.getMessage());
        }
    }

    private void restaurarUsuarios() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            br.readLine();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String apellido = datos[2];
                String email = datos[3];
                String telefono = datos[4];
                LocalDate fecha = LocalDate.parse(datos[5]);
                daoUsuarios.insertarUsuario(new Usuario(id, nombre, apellido, email, telefono, fecha));
            }
        } catch (Exception e) {
            System.err.println("Error al restaurar usuarios: " + e.getMessage());
        }
    }

    private void restaurarPrestamos() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_PRESTAMOS))) {
            br.readLine();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                int idUsu = Integer.parseInt(datos[1]);
                int idLib = Integer.parseInt(datos[2]);
                LocalDate fechaPre = LocalDate.parse(datos[3]);
                LocalDate fechaPrev = LocalDate.parse(datos[4]);
                LocalDate fechaReal = datos[5].isEmpty() ? null : LocalDate.parse(datos[5]);
                Estado estado = Estado.valueOf(datos[6]);

                daoPrestamos.insertarPrestamo(new Prestamo(id, idUsu, idLib, fechaPre, fechaPrev, fechaReal, estado));
            }
        } catch (Exception e) {
            System.err.println("Error al restaurar prestamos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        BackUp backup = new BackUp();
        backup.crearBackup();
    }
}