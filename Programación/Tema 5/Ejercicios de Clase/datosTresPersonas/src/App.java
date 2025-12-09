import java.util.Scanner;

import Clases.Persona;

public class App {
    /*
Pedimos datos de las tres personas
dni
nombre
apellidos
edad
sueldo
despues mostramos el total que tiene mi empresa de sueldo
*/
    public static void main(String[] args) throws Exception {
        final int numPersonas = 3;
        Persona[] user = new Persona[numPersonas];
        Scanner sc = new Scanner(System.in);
        Scanner scNum = new Scanner(System.in);

        String dni;
        String nombre;
        String apellido;
        int edad;
        double sueldo;
        double acumuladorSueldo = 0;
        
        for (int i = 0; i < user.length; i++) {
            System.out.println("¿Cuál es tu dni?");
            dni = sc.nextLine();
            System.out.println("¿Cuál es tu nombre");
            nombre = sc.nextLine();
            System.out.println("¿Cuál es tu apellido?");
            apellido = sc.nextLine();
            System.out.println("¿Cuál es tu edad?");
            edad = scNum.nextInt();
            System.out.println("¿Cuál es tu sueldo?");
            sueldo = scNum.nextDouble();

            user[i] = new Persona(dni, nombre, apellido, edad, sueldo);
            acumuladorSueldo += user[i].getSueldo();
        }
        
        System.out.println("El total a pagar por la empresa es de: " + acumuladorSueldo + "€");
    }
}
