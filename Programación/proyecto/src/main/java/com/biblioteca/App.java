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

public class App {

    private static Scanner scanner = new Scanner(System.in);
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

    private static void mostrarMenu() {
        System.out.println("""
                === BIBLIOTECA ===
                1. Crear tablas
                2. Gestión de Usuarios
                3. Gestión de Libros
                4. Gestión de Préstamos
                0. Salir
                """);
    }

    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> crearTablas();
            case 2 -> menuUsuarios();
            case 3 -> menuLibros();
            case 4 -> menuPrestamos();
            case 0 -> {
                System.out.println("¡Hasta luego!");
                Conexion.close();
                scanner.close();
            }
            default -> System.out.println("Opción inválida");
        }
    }

    private static void crearTablas() {
        daoUsuarios.crearTabla();
        daoLibros.crearTabla();
        daoPrestamos.crearTabla();
    }

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

    private static void buscarUsuarioPorId() {
        int id = leerInt("ID del usuario");
        Usuario u = daoUsuarios.buscarUsuarioPorId(id);
        if (u != null) {
            System.out.println(u.getId() + ": " + u.getNombre() + " " + u.getApellido() + " - " + u.getEmail());
        } else {
            System.out.println("No se ha encontrado ningún usuario con ID " + id);
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
        if (daoUsuarios.eliminarUsuario(id)) {
            System.out.println("Usuario eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el usuario.");
        }
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
            usuarios.forEach(u -> System.out.println(
                    u.getId() + ": " + u.getNombre() + " " + u.getApellido() + " - " + u.getEmail()));
        }
    }

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
                case 0 -> System.out.println();
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private static void mostrarLibrosDisponibles() {
        List<Libro> libros = daoLibros.librosDisponibles();
        imprimirListaLibros(libros, "No hay libros disponibles", "--- Libros disponibles ---");
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
            libros.forEach(l -> System.out.println(
                    l.getId() + ": " + l.getTitulo() + " - " + l.getAutor() + " (" + l.getTipo() + ")"));
        }
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
        if (daoLibros.eliminarLibro(id)) {
            System.out.println("Libro eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el libro.");
        }
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

    private static void mostrarLibroMasPrestado() {
        Entry<Integer, Long> entry = daoPrestamos.libroMasPrestado();
        if (entry == null) {
            System.out.println("No hay préstamos registrados");
            return;
        }
        Libro libro = daoLibros.buscarLibroPorId(entry.getKey());
        String titulo = libro != null ? libro.getTitulo() : "Desconocido";
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

    private static void imprimirPrestamo(Prestamo p) {
        System.out.printf("%03d: Usuario %03d - Libro %03d - Estado: %s%n",
                p.getId(), p.getIdUsuario(), p.getIdLibro(), p.getEstado().toString());
    }

    private static String leerString(String pregunta) {
        System.out.print(pregunta + ": ");
        return scanner.nextLine();
    }

    private static int leerInt(String pregunta) {
        while (true) {
            try {
                System.out.print(pregunta + ": ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: debe introducir un número entero");
            }
        }
    }

    private static Genero leerGenero() {
        while (true) {
            System.out.print("Género " + Arrays.toString(Genero.values()) + ": ");
            try {
                return Genero.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Género no válido.");
            }
        }
    }

    private static Formato leerFormato() {
        while (true) {
            System.out.print("Formato " + Arrays.toString(Formato.values()) + ": ");
            try {
                return Formato.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Formato no válido.");
            }
        }
    }

    private static Tipo leerTipo() {
        while (true) {
            System.out.print("Tipo (PAPEL/ELECTRONICO) " + Arrays.toString(Tipo.values()) + ": ");
            try {
                return Tipo.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Tipo no válido.");
            }
        }
    }
}