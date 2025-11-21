import java.util.Scanner;

import Clases.Coche;
import Clases.Personas;

public class App {
    
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        Personas instanciaNueva;

        String nombre;
        String NIA;
        String fecha;
        
        System.out.println("¿Cuál es tu nombre?");
        nombre = sc.nextLine();
        System.out.println("¿Cuál es tu NIA?");
        NIA = sc.nextLine();
        System.out.println("¿Cuál es tu fecha de matrícula?");
        fecha = sc.nextLine();
        
        instanciaNueva = new Personas(nombre, NIA, fecha);
        System.out.println(instanciaNueva.consultarDatosAlumno());

        

    }
}
