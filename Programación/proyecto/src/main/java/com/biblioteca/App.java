package com.biblioteca;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import com.biblioteca.Clases.Libro;
import com.biblioteca.Clases.LibroElectronico;
import com.biblioteca.Clases.LibroEnPapel;
import com.biblioteca.Clases.Prestamo;
import com.biblioteca.Clases.Usuario;
import com.biblioteca.Enum.Estado;
import com.biblioteca.Enum.Formato;
import com.biblioteca.Enum.Genero;
import com.biblioteca.Enum.Tipo;
import com.biblioteca.dao.Conexion;
import com.biblioteca.dao.DAOlibros;
import com.biblioteca.dao.DAOprestamos;
import com.biblioteca.dao.DAOusuarios;
import com.biblioteca.dao.Exportador;
import com.biblioteca.dao.Logs;

public class App {

    private static Scanner sc = new Scanner(System.in);
    private static DAOusuarios daoUsuarios = new DAOusuarios();
    private static DAOlibros daoLibros = new DAOlibros();
    private static DAOprestamos daoPrestamos = new DAOprestamos();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerInt("Opción");
            procesarOpcion(opcion);
        } while (opcion != 0);
    }

    // MENÚ PRINCIPAL ----------------------------

    private static void mostrarMenu() {
        System.out.println("""
                === BIBLIOTECA ===
                1. Crear tablas
                2. Gestión de Usuarios
                3. Gestión de Libros
                4. Gestión de Préstamos
                5. Ver últimos logs
                0. Salir
                """);
    }

    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> crearTablas();
            case 2 -> menuUsuarios();
            case 3 -> menuLibros();
            case 4 -> menuPrestamos();
            case 5 -> verUltimosLogs();
            case 0 -> {
                System.out.println("Saliendo del programa...!");
                Conexion.close();
                sc.close();
            }
            default -> System.out.println("Opción inválida");
        }
    }

    private static void crearTablas() {
        daoUsuarios.crearTabla();
        daoLibros.crearTabla();
        daoPrestamos.crearTabla();
    }

    private static void verUltimosLogs() {
        List<String> logs = Logs.leerUltimosLogs(5);
        if (logs.isEmpty()) {
            System.out.println("No hay logs disponibles.");
        } else {
            System.out.println("\n--- Últimos 5 logs ---");
            for (String linea : logs) {
                System.out.println(linea);
            }
        }
    }

    // MENÚ USUARIOS ----------------------------

    private static void menuUsuarios() {
        int opcion;
        do {
            System.out.println("""
                    --- Usuarios ---
                    1. Insertar usuario
                    2. Listar usuarios
                    3. Actualizar usuario
                    4. Eliminar usuario
                    5. Buscar usuario por ID
                    0. Volver
                    """);
            opcion = leerInt("Opción");
            switch (opcion) {
                case 1 -> insertarUsuario();
                case 2 -> listarUsuarios();
                case 3 -> actualizarUsuario();
                case 4 -> eliminarUsuario();
                case 5 -> buscarUsuarioPorId();
                case 0 -> System.out.println();
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private static void insertarUsuario() {
        String nombre = leerString("Nombre");
        String apellidos = leerString("Apellidos");
        String email = leerString("Email");
        String telefono = leerString("Teléfono");

        Usuario usuario = new Usuario(0, nombre, apellidos, email, telefono, LocalDate.now());
        daoUsuarios.insertarUsuario(usuario);
    }

    private static void listarUsuarios() {
        List<Usuario> usuarios = daoUsuarios.obtenerTodosLosUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios");
        } else {
            System.out.println("\n--- Lista de Usuarios ---");
            usuarios.forEach(usuario -> System.out.printf(
                    "[%03d] %-12s %-18s  %s%n",
                    usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail()));
        }
    }

    private static void actualizarUsuario() {
        int id = leerInt("ID del usuario a actualizar");
        String nombre = leerString("Nuevo nombre");
        String apellidos = leerString("Nuevos apellidos");
        String email = leerString("Nuevo email");
        String telefono = leerString("Nuevo teléfono");
        Usuario usuario = new Usuario(id, nombre, apellidos, email, telefono, LocalDate.now());
        if (daoUsuarios.actualizarUsuario(usuario)) {
            System.out.println("Usuario actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el usuario.");
        }
    }

    private static void eliminarUsuario() {
        int id = leerInt("ID del usuario a eliminar");
        if (!daoPrestamos.prestamosActivosDeUnUsuario(id).isEmpty()) {
            System.out.println("No se puede eliminar: el usuario tiene préstamos activos.");
            return;
        }
        if (daoUsuarios.eliminarUsuario(id)) {
            System.out.println("Usuario eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el usuario.");
        }
    }

    private static void buscarUsuarioPorId() {
        int id = leerInt("ID del usuario");
        Usuario u = daoUsuarios.buscarUsuarioPorId(id);
        if (u != null) {
            System.out.println(u.getId() + ": " + u.getNombre() + " " + u.getApellido() + " - " + u.getEmail());
        } else {
            System.out.println("No se ha encontrado ningún usuario con ID " + id);
        }
    }

    // MENÚ LIBROS ----------------------------

    private static void menuLibros() {
        int opcion;
        do {
            System.out.println("""
                    --- Libros ---
                    1. Insertar libro en papel
                    2. Insertar libro electrónico
                    3. Listar libros
                    4. Libros disponibles
                    5. Actualizar libro
                    6. Eliminar libro
                    7. Buscar por autor
                    8. Buscar por género
                    9. Guardar listado en fichero (CSV)
                    0. Volver
                    """);
            opcion = leerInt("Opción");
            switch (opcion) {
                case 1 -> insertarLibroEnPapel();
                case 2 -> insertarLibroElectronico();
                case 3 -> listarLibros();
                case 4 -> mostrarLibrosDisponibles();
                case 5 -> actualizarLibro();
                case 6 -> eliminarLibro();
                case 7 -> buscarLibrosPorAutor();
                case 8 -> buscarLibrosPorGenero();
                case 9 -> menuExportarFichero();
                case 0 -> System.out.println();
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private static void insertarLibroEnPapel() {
        String titulo = leerString("Título");
        String autor = leerString("Autor");
        String isbn = leerString("ISBN");
        int anio = leerInt("Año publicación");
        int copias = leerInt("Copias totales");
        String ubicacion = leerString("Ubicación");

        Genero genero = leerGenero();

        Libro libro = new LibroEnPapel(0, titulo, autor, genero, isbn, anio, copias, copias,
                Tipo.PAPEL, 0, ubicacion);
        daoLibros.insertarLibro(libro);
    }

    private static void insertarLibroElectronico() {
        String titulo = leerString("Título");
        String autor = leerString("Autor");
        String isbn = leerString("ISBN");
        int anio = leerInt("Año publicación");
        String url = leerString("URL descarga");

        Genero genero = leerGenero();
        Formato formato = leerFormato();

        Libro libro = new LibroElectronico(0, titulo, autor, genero, isbn, anio, 1, 1,
                Tipo.ELECTRONICO, 0, formato, url);
        daoLibros.insertarLibro(libro);
    }

    private static void listarLibros() {
        List<Libro> libros = daoLibros.obtenerTodosLosLibros();
        imprimirListaLibros(libros, "No hay libros", "--- Lista de Libros ---");
    }

    private static void mostrarLibrosDisponibles() {
        List<Libro> libros = daoLibros.librosDisponibles();
        imprimirListaLibros(libros, "No hay libros disponibles", "--- Libros disponibles ---");
    }

    private static void actualizarLibro() {
        int id = leerInt("ID del libro a actualizar");
        String titulo = leerString("Nuevo Título");
        String autor = leerString("Nuevo Autor");
        String isbn = leerString("Nuevo ISBN");
        int anio = leerInt("Nuevo Año publicación");
        int copiasTotales = leerInt("Nuevas Copias totales");
        int copiasDisponibles = leerInt("Nuevas Copias disponibles");

        System.out.println("Géneros disponibles: " + Arrays.toString(Genero.values()));
        Genero genero = leerGenero();
        Tipo tipo = leerTipo();

        Libro libro;
        if (tipo == Tipo.ELECTRONICO) {
            Formato formato = leerFormato();
            String url = leerString("Nueva URL descarga");
            libro = new LibroElectronico(id, titulo, autor, genero, isbn, anio, copiasTotales, copiasDisponibles, tipo,
                    id, formato, url);
        } else {
            String ubicacion = leerString("Nueva Ubicación");
            libro = new LibroEnPapel(id, titulo, autor, genero, isbn, anio, copiasTotales, copiasDisponibles, tipo, id,
                    ubicacion);
        }

        if (daoLibros.actualizarLibro(libro)) {
            System.out.println("Libro actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el libro.");
        }
    }

    private static void eliminarLibro() {
        int id = leerInt("ID del libro a eliminar");
        if (!daoPrestamos.prestamosActivosDeUnLibro(id).isEmpty()) {
            System.out.println("No se puede eliminar: el libro tiene préstamos activos.");
            return;
        }
        if (daoLibros.eliminarLibro(id)) {
            System.out.println("Libro eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el libro.");
        }
    }

    private static void buscarLibrosPorAutor() {
        String autor = leerString("Autor");
        List<Libro> libros = daoLibros.buscarPorAutor(autor);
        imprimirListaLibros(libros, "No hay libros de ese autor", "--- Libros de " + autor + " ---");
    }

    private static void buscarLibrosPorGenero() {
        Genero genero = leerGenero();
        List<Libro> libros = daoLibros.buscarPorGenero(genero);
        imprimirListaLibros(libros, "No hay libros de ese género", "--- Libros de género " + genero + " ---");
    }

    private static void imprimirListaLibros(List<Libro> libros, String mensajeVacio, String cabecera) {
        if (libros.isEmpty()) {
            System.out.println(mensajeVacio);
        } else {
            System.out.println("\n" + cabecera);
            libros.forEach(libro -> System.out.printf(
                    "[%03d] %-30s - %-25s [%02d/%02d copias] (%s)%n",
                    libro.getId(), libro.getTitulo(), libro.getAutor(),
                    libro.getCopiasDisponibles(), libro.getCopiasTotales(), libro.getTipo()));
        }
    }

    // MENÚ EXPORTAR A FICHERO ----------------------------

    private static void menuExportarFichero() {
        System.out.println("""
                --- Guardar listado en fichero ---
                1. Todos los libros
                2. Por autor
                3. Por género
                4. Libros disponibles
                5. Libros no disponibles
                0. Volver
                """);
        int opcion = leerInt("Opción");
        switch (opcion) {
            case 1 -> {
                List<Libro> libros = daoLibros.obtenerTodosLosLibros();
                Exportador.guardarLibrosCSV(libros, Exportador.nombreArchivo("libros_todos"));
            }
            case 2 -> {
                String autor = leerString("Autor");
                List<Libro> libros = daoLibros.buscarPorAutor(autor);
                Exportador.guardarLibrosCSV(libros,
                        Exportador.nombreArchivo("libros_autor_" + autor.replaceAll("\\s+", "_")));
            }
            case 3 -> {
                Genero genero = leerGenero();
                List<Libro> libros = daoLibros.buscarPorGenero(genero);
                Exportador.guardarLibrosCSV(libros, Exportador.nombreArchivo("libros_genero_" + genero));
            }
            case 4 -> {
                List<Libro> libros = daoLibros.librosDisponibles();
                Exportador.guardarLibrosCSV(libros, Exportador.nombreArchivo("libros_disponibles"));
            }
            case 5 -> {
                List<Libro> libros = daoLibros.obtenerTodosLosLibros().stream()
                        .filter(libro -> libro.getCopiasDisponibles() == 0)
                        .toList();
                Exportador.guardarLibrosCSV(libros, Exportador.nombreArchivo("libros_no_disponibles"));
            }
            case 0 -> System.out.println();
            default -> System.out.println("Opción inválida");
        }
    }

    // MENÚ PRÉSTAMOS ----------------------------

    private static void menuPrestamos() {
        int opcion;
        do {
            System.out.println("""
                    --- Préstamos ---
                    1. Crear préstamo
                    2. Listar préstamos
                    3. Préstamos activos de usuario
                    4. Libro más prestado
                    5. Género con más préstamos
                    6. Devolver préstamo
                    0. Volver
                    """);
            opcion = leerInt("Opción");
            switch (opcion) {
                case 1 -> insertarPrestamo();
                case 2 -> listarPrestamos();
                case 3 -> prestamosActivosUsuario();
                case 4 -> mostrarLibroMasPrestado();
                case 5 -> mostrarGeneroConMasPrestamos();
                case 6 -> devolverPrestamo();
                case 0 -> System.out.println();
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private static void insertarPrestamo() {
        int idUsuario = leerInt("ID Usuario");
        int idLibro = leerInt("ID Libro");
        int dias = leerInt("Días de duración");

        LocalDate fechaPrestamo = LocalDate.now();
        LocalDate fechaPrevista = fechaPrestamo.plusDays(dias);

        Prestamo prestamo = new Prestamo(0, idUsuario, idLibro, fechaPrestamo, fechaPrevista, null, Estado.ACTIVO);
        daoPrestamos.insertarPrestamo(prestamo);
    }

    private static void listarPrestamos() {
        List<Prestamo> prestamos = daoPrestamos.obtenerTodosLosPrestamos();
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos");
        } else {
            System.out.println("\n--- Lista de Préstamos ---");
            prestamos.forEach(App::imprimirPrestamo);
        }
    }

    private static void prestamosActivosUsuario() {
        int idUsuario = leerInt("ID Usuario");
        List<Prestamo> prestamos = daoPrestamos.prestamosActivosDeUnUsuario(idUsuario);
        if (prestamos.isEmpty()) {
            System.out.println("El usuario " + idUsuario + " no tiene préstamos activos");
        } else {
            System.out.println("\n--- Préstamos activos del usuario " + idUsuario + " ---");
            prestamos.forEach(App::imprimirPrestamo);
        }
    }

    private static void mostrarLibroMasPrestado() {
        Entry<Integer, Long> entry = daoPrestamos.libroMasPrestado();
        if (entry == null) {
            System.out.println("No hay préstamos registrados");
            return;
        }
        Libro libro = daoLibros.buscarLibroPorId(entry.getKey());
        String titulo;
        if (libro != null) {
            titulo = libro.getTitulo();
        } else {
            titulo = "Desconocido";
        }
        System.out.println("Libro más prestado: " + titulo
                + " (ID " + entry.getKey() + ", " + entry.getValue() + " préstamos)");
    }

    private static void mostrarGeneroConMasPrestamos() {
        Entry<Genero, Long> entry = daoPrestamos.generoConMasPrestamos();
        if (entry == null) {
            System.out.println("No hay préstamos registrados");
            return;
        }
        System.out.println("Género con más préstamos: " + entry.getKey()
                + " (" + entry.getValue() + " préstamos)");
    }

    private static void devolverPrestamo() {
        int id = leerInt("ID del préstamo a devolver");
        if (daoPrestamos.devolverPrestamo(id)) {
            System.out.println("Préstamo procesado con éxito.");
        } else {
            System.out.println("Error al devolver el préstamo.");
        }
    }

    private static void imprimirPrestamo(Prestamo prestamo) {
        Usuario usuario = daoUsuarios.buscarUsuarioPorId(prestamo.getIdUsuario());
        Libro libro = daoLibros.buscarLibroPorId(prestamo.getIdLibro());

        String nombreUsuario;
        if (usuario != null) {
            nombreUsuario = usuario.getNombre() + " " + usuario.getApellido();
        } else {
            nombreUsuario = "Usuario " + prestamo.getIdUsuario();
        }

        String tituloLibro;
        if (libro != null) {
            tituloLibro = libro.getTitulo();
        } else {
            tituloLibro = "Libro " + prestamo.getIdLibro();
        }

        String estadoInfo;
        if (prestamo.getEstado() == Estado.ACTIVO) {
            long diasRestantes = prestamo.getFechaDevolucionPrevista().toEpochDay() - LocalDate.now().toEpochDay();
            if (diasRestantes >= 0) {
                estadoInfo = "ACTIVO (" + diasRestantes + " días restantes)";
            } else {
                estadoInfo = "ACTIVO (RETRASO de " + Math.abs(diasRestantes) + " días)";
            }
        } else {
            estadoInfo = prestamo.getEstado().toString();
        }

        System.out.printf("[%03d] %-22s  %-30s  %s%n", prestamo.getId(), nombreUsuario, tituloLibro, estadoInfo);
    }

    // MÉTODOS DE ENTRADA ----------------------------

    private static String leerString(String pregunta) {
        System.out.print(pregunta + ": ");
        return sc.nextLine();
    }

    private static int leerInt(String pregunta) {
        while (true) {
            try {
                System.out.print(pregunta + ": ");
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: debe introducir un número entero");
            }
        }
    }

    private static Genero leerGenero() {
        while (true) {
            System.out.print("Género " + Arrays.toString(Genero.values()) + ": ");
            try {
                return Genero.valueOf(sc.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Género no válido.");
            }
        }
    }

    private static Formato leerFormato() {
        while (true) {
            System.out.print("Formato " + Arrays.toString(Formato.values()) + ": ");
            try {
                return Formato.valueOf(sc.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Formato no válido.");
            }
        }
    }

    private static Tipo leerTipo() {
        while (true) {
            System.out.print("Tipo (PAPEL/ELECTRONICO) " + Arrays.toString(Tipo.values()) + ": ");
            try {
                return Tipo.valueOf(sc.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Tipo no válido.");
            }
        }
    }
}
