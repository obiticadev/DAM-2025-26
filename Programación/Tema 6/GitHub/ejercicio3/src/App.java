import Clases.Alumno;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int numAlumnos;

        System.out.println("LISTADO\n---------\n");
        try {
            System.out.print("Introduce el número de alumnos que quieres registrar: ");
            numAlumnos = Integer.parseInt(sc.nextLine());

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Has introducido una cantidad incoherente, asignaremos 5 alumnos por defecto");
            numAlumnos = 5;

        }
        Alumno[] registro = new Alumno[numAlumnos];

        for (int i = 0; i < registro.length; i++) {
            System.out.println("Introduce el nombre del alumno " + (i + 1));
            String nombre = sc.nextLine();
            registro[i] = new Alumno(nombre);
        }

        for (int i = 0; i < registro.length; i++) {
            System.out.println("Alumno " + (i + 1) + ": " + registro[i].mostrarInfo());
        }

    }
}
