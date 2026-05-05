import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import Clases.AlumnnoPresencial;
import Clases.Alumno;
import Clases.AlumnoOnline;
import DAO.DAOalumno;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static DAOalumno dao;

    public static void main(String[] args) {
        boolean continuar = true;
        String respuesta;
        try {
            dao = new DAOalumno();

            while (continuar) {
                menu();
                respuesta = sc.nextLine().trim();
                switch (respuesta) {
                    case "1" -> {
                        agregarAlumnoPresencial();
                    }
                    case "2" -> {
                        agregarAlumnoOnline();
                    }
                    case "3" -> {
                        System.out.println(dao.listarAlumnos());
                    }
                    case "4" -> {
                        System.out.print("Introduce el nombre del alumno a buscar: ");
                        String nombreABuscar = sc.nextLine();

                        dao.buscarAlumno(nombreABuscar).ifPresentOrElse(
                                alumnoEncontrado -> System.out
                                        .println("Alumno encontrado: \n" + alumnoEncontrado.getResumen()),
                                () -> System.out.println("❌ No se ha encontrado ningún alumno con ese nombre."));
                    }
                    case "5" -> {
                        Alumno alumno;
                        if ((alumno = dao.buscarAlumnoMasNotaMedia()) == null) {
                            System.out.println("No se ha encontrado datos");
                        } else {
                            System.out.println(alumno.getResumen());
                        }

                    }
                    case "6" -> {
                        System.out.printf("Importe total de matriculas es de %,.2f EUR%n",
                                dao.importeTotalMatriculas());
                    }

                    case "0" -> {
                        System.out.println("Saliendo del programa...");
                        dao.guardarFichero();
                        continuar = false;
                    }

                    default -> {
                        System.out.println("Selecciona una opción válida");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.printf("Error: %s%n", e.getClass().getSimpleName());
        } catch (IOException io) {
            System.out.printf("Error: %s%n", io.getClass().getSimpleName());
        } catch (ClassNotFoundException cnf) {
            System.out.printf("Error: %s%n", cnf.getClass().getSimpleName());
        }
    }

    private static void menu() {
        System.out.print("""
                === INSTITUTO ===
                1. Añadir alumno presencial
                2. Añadir alumno online
                3. Listar todos los alumnos
                4. Buscar alumno por nombre
                5. Mostrar alumno con mayor nota media
                6. Mostrar importe total de matrículas
                0. Salir (guarda automáticamente)

                Selecciona una opción:\s""");
    }

    private static void agregarAlumnoPresencial() {
        System.out.println("\n--- AÑADIR ALUMNO PRESENCIAL ---");
        try {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Curso: ");
            String curso = sc.nextLine();

            System.out.print("Nota Media: ");
            double notaMedia = Double.parseDouble(sc.nextLine());

            System.out.print("Número de módulos: ");
            int numModulo = Integer.parseInt(sc.nextLine());

            System.out.print("¿Es familia numerosa? (true/false): ");
            boolean familiaNumerosa = Boolean.parseBoolean(sc.nextLine());

            // Usamos la clase con doble 'n' tal y como la tienes definida
            AlumnnoPresencial nuevoAlumno = new AlumnnoPresencial(nombre, curso, notaMedia, numModulo, familiaNumerosa);

            // Suponiendo que tu variable estática se llama 'dao'
            dao.insertarAlumno(nuevoAlumno);

            System.out.println("✅ Alumno presencial guardado correctamente.");

        } catch (Exception e) {
            System.out.println("❌ Error en la introducción de datos. Revisa los formatos (ej: usa números válidos).");
        }
    }

    private static void agregarAlumnoOnline() {
        System.out.println("\n--- AÑADIR ALUMNO ONLINE ---");
        try {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Curso: ");
            String curso = sc.nextLine();

            System.out.print("Nota Media: ");
            double notaMedia = Double.parseDouble(sc.nextLine());

            System.out.print("Número de módulos: ");
            int numModulo = Integer.parseInt(sc.nextLine());

            System.out.print("Factor de participación (ej: 0.8): ");
            double factorParticipacion = Double.parseDouble(sc.nextLine());

            AlumnoOnline nuevoAlumno = new AlumnoOnline(nombre, curso, notaMedia, numModulo, factorParticipacion);

            dao.insertarAlumno(nuevoAlumno);

            System.out.println("✅ Alumno online guardado correctamente.");

        } catch (Exception e) {
            System.out.println("❌ Error en la introducción de datos. Revisa los formatos.");
        }
    }

}
