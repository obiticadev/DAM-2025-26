
import java.util.Scanner;

import Clases.Alumno;
import Clases.Curso;
import DAO.CursoDAO;

public class GestionAlumnos {

    private static Scanner sc = new Scanner(System.in);
    private static CursoDAO dao = new CursoDAO();

    // TODO - COMPLETA EL CÓDIGO DE LA CLASE GESTIONALUMNOS PARA QUE FUNCIONE
    // CORRECTAMENTE CON EL RESTO DE CLASES DEL PROYECTO.

    public static void main(String[] args) {

        String opcion;
        do {

            mostrarMenu();
            opcion = sc.nextLine();
            switch (opcion) {

                case "1":
                    crearCurso();
                    break;
                case "2":
                    listarCursos();
                    break;
                case "3":
                    matricularAlumno();
                    break;
                case "4":
                    listarMatriculaciones();
                    break;
                case "5":
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (!opcion.equals("5"));
    }

    private static void mostrarMenu() {

        System.out.println("\n===== MENÚ CENTRO =====");
        System.out.println("1. Crear curso");
        System.out.println("2. Listar cursos");
        System.out.println("3. Matricular alumno");
        System.out.println("4. Listar matriculaciones");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void crearCurso() {
        System.out.print("Código del curso: ");
        String codigo = sc.nextLine();
        System.out.print("Nombre del curso: ");
        String nombre = sc.nextLine();
        System.out.print("Horas del curso: ");

        // TODO - COMPLETA LA GESTIÓN DE EXCEPCIONES PARA QUE SI EL USUARIO INTRODUCE UN
        // VALOR NO VÁLIDO PARA LAS HORAS,
        // SE LE INDIQUE EL ERROR Y SE LE VUELVA A PEDIR EL VALOR HASTA QUE INTRODUZCA
        // UNO VÁLIDO.
        boolean continuar = true;
        do {
            try {
                int horas = Integer.parseInt(sc.nextLine());
                continuar = false;
                dao.guardar(new Curso(codigo, nombre, horas));
            } catch (Exception e) {
                // TODO: handle exception
            }

        } while (continuar);

        // TODO – COMPLETA EL MÉTODO PARA QUE EL CURSO SE GUARDE EN EL DAO
        // MUESTRA LOS MENSAJES DE ERROR O DE ÉXITO CORRESPONDIENTES.
    }

    private static void listarCursos() {

        // TODO – COMPLETA EL CÓDIGO PARA QUE SE MUESTREN EN CONSOLA LA INFORMACIÓN
        // DE LOS CURSOS EN EL SIGUIENTE FORMATO...
        System.out.println(dao.listar());
    }

    private static void matricularAlumno() {

        System.out.print("Dni del alumno: ");

        String dni = sc.nextLine();

        System.out.print("Nombre del alumno: ");

        String nombreAlumno = sc.nextLine();

        System.out.print("Código del curso: ");

        String codigoCurso = sc.nextLine();

        System.out.print("Email: ");

        String email = sc.nextLine();

        // TODO – COMPLETA EL CÓDIGO PARA QUE EL ALUMNO QUEDE MATRICULADO EN // EL CURSO
        // CORRECTAMENTE

        // MUESTRA LOS MENSAJES DE ERROR O DE ÉXITO CORRESPONDIENTES.
        if (dao.buscarPorCodigo(codigoCurso) != null) {
            dao.buscarPorCodigo(codigoCurso).matricularAlumno(new Alumno(dni, nombreAlumno, email));
            System.out.println("Alumno matriculado correctamente.");
        } else {
            System.out.println("Curso no existe");
        }

    }

    private static void listarMatriculaciones() {

        System.out.print("Código del curso del que quieres ver la lista de matriculados: ");

        String codigoCurso = sc.nextLine();

        // TODO – COMPLETA EL CÓDIGO PARA QUE SE MUESTREN EN CONSOLA LA //LISTA DE
        // MATRICULADOS DEL CÓDIGO DE CURSO INTRODUCIDO
        StringBuilder sb = new StringBuilder();
        if (dao.buscarPorCodigo(codigoCurso) != null) {
            sb.append("ALUMNOS MATRICULADOS: \n");
            for (Alumno a : dao.buscarPorCodigo(codigoCurso).getAlumnosMatriculados()) {
                sb.append(a.toString()).append("\n");
            }
        } else {
            sb.append("No hay alumnos matriculados");
        }
        System.out.println(sb.toString());
    }
}
