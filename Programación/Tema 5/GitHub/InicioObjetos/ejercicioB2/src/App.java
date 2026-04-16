import java.util.Scanner;

import Clases.Persona;

public class App {
    public static void main(String[] args) throws Exception {
        Persona personaIniciada;
        Scanner sc = new Scanner(System.in);
        Scanner scNum = new Scanner(System.in);

        String nombre;
        String apellido;
        int DNI;
        int edad;

        System.out.print("Nombre: ");
        nombre = sc.nextLine();
        System.out.print("Apellido: ");
        apellido = sc.nextLine();
        System.out.print("DNI: ");
        DNI = scNum.nextInt();
        System.out.print("Edad: ");
        edad = scNum.nextInt();

        personaIniciada = new Persona(DNI, nombre, apellido, edad);

        System.out.println(personaIniciada.consultarDatosPersona());

        
    }
}
