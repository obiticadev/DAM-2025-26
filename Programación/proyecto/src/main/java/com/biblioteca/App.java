package com.biblioteca;

// TODO [RECOMENDACIÓN] Considerar separar la lógica del menú en una clase aparte (ej: MenuConsola.java)
//  para que App.java solo contenga el main y la orquestación de alto nivel.
//  Esto facilita futuros cambios (ej: migrar de consola a GUI).

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
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
                // TODO [RECOMENDACIÓN] Cerrar recursos al salir:
                // → Conexion.cerrarConexion();
                // → scanner.close();
                // Esto libera la conexión a SQLite y el flujo de System.in.
                System.out.println("¡Hasta luego!");
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
            // TODO [CÓDIGO FALTANTE] Ampliar menú de usuarios con opciones CRUD completas:
            // 3. Buscar usuario por ID → pedir ID, llamar a
            // daoUsuarios.buscarUsuarioPorId(id)
            // 4. Actualizar usuario → pedir ID, leer nuevos datos, llamar a
            // daoUsuarios.actualizarUsuario()
            // 5. Eliminar usuario → pedir ID, llamar a daoUsuarios.eliminarUsuario(id)
            System.out.println("""
                    --- Usuarios ---
                    1. Insertar usuario
                    2. Listar usuarios
                    0. Volver
                    """);
            opcion = leerInt("Opción");
            switch (opcion) {
                case 1 -> insertarUsuario();
                case 2 -> listarUsuarios();
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
            for (Usuario u : usuarios) {
                System.out.println(u.getId() + ": " + u.getNombre() + " " + u.getApellido() + " - " + u.getEmail());
            }
        }
    }

    private static void menuLibros() {
        int opcion;
        do {
            // TODO [CÓDIGO FALTANTE] Ampliar menú de libros con opciones de búsqueda:
            // 5. Buscar por autor → pedir String autor, llamar a
            // daoLibros.buscarPorAutor(autor)
            // 6. Buscar por género → mostrar géneros, pedir Genero, llamar a
            // daoLibros.buscarPorGenero(genero)
            // 7. Eliminar libro → pedir ID, llamar a daoLibros.eliminarLibro(id)
            System.out.println("""
                    --- Libros ---
                    1. Insertar libro en papel
                    2. Insertar libro electrónico
                    3. Listar libros
                    4. Libros disponibles
                    0. Volver
                    """);
            opcion = leerInt("Opción");
            switch (opcion) {
                case 1 -> insertarLibroEnPapel();
                case 2 -> insertarLibroElectronico();
                case 3 -> listarLibros();
                case 4 -> daoLibros.librosDisponibles();
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

        System.out.println("Géneros disponibles: " + Arrays.toString(Genero.values()));
        Genero genero = leerEnum(Genero.class, "Género");

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

        System.out.println("Géneros disponibles: " + java.util.Arrays.toString(Genero.values()));
        Genero genero = leerEnum(Genero.class, "Género");

        System.out.println("Formatos disponibles: " + java.util.Arrays.toString(Formato.values()));
        Formato formato = leerEnum(Formato.class, "Formato");

        Libro libro = new LibroElectronico(0, titulo, autor, genero, isbn, anio, 1, 1,
                Tipo.ELECTRONICO, 0, formato, url);
        daoLibros.insertarLibro(libro);
    }

    private static void listarLibros() {
        List<Libro> libros = daoLibros.obtenerTodosLosLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros");
        } else {
            System.out.println("\n--- Lista de Libros ---");
            for (Libro l : libros) {
                System.out.println(l.getId() + ": " + l.getTitulo() + " - " + l.getAutor() + " (" + l.getTipo() + ")");
            }
        }
    }

    private static void menuPrestamos() {
        int opcion;
        do {
            // TODO [CÓDIGO FALTANTE] Añadir opción "6. Devolver préstamo" al menú.
            // → Pedir ID del préstamo, llamar a daoPrestamos.devolverPrestamo(idPrestamo).
            // → Este método debe actualizar estado y fecha_devolucion_real, e incrementar
            // copias del libro.
            System.out.println("""
                    --- Préstamos ---
                    1. Crear préstamo
                    2. Listar préstamos
                    3. Préstamos activos de usuario
                    4. Libro más prestado
                    5. Género con más préstamos
                    0. Volver
                    """);
            opcion = leerInt("Opción");
            switch (opcion) {
                case 1 -> insertarPrestamo();
                case 2 -> listarPrestamos();
                case 3 -> prestamosActivosUsuario();
                case 4 -> daoPrestamos.libroMasPrestado();
                case 5 -> daoPrestamos.generoConMasPrestamos();
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
            for (Prestamo p : prestamos) {
                System.out.printf("%03d: Usuario %03d - Libro %03d - Estado: %s%n", p.getId(), p.getIdUsuario(),
                        p.getIdLibro(), p.getEstado().toString());
            }
        }
    }

    private static void prestamosActivosUsuario() {
        int idUsuario = leerInt("ID Usuario");
        // TODO [BUG] El idUsuario se lee pero NO se pasa al DAO.
        // → Cuando corrijas prestamosActivosDeUnUsuario(int idUsuario) en DAOprestamos,
        // cambiar esta línea a: daoPrestamos.prestamosActivosDeUnUsuario(idUsuario);
        daoPrestamos.prestamosActivosDeUnUsuario();
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

    private static <T extends Enum<T>> T leerEnum(Class<T> enumClass, String pregunta) {
        while (true) {
            try {
                System.out.print(pregunta + " (nombre): ");
                return Enum.valueOf(enumClass, scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: valor no válido. Intente de nuevo");
            }
        }
    }
}