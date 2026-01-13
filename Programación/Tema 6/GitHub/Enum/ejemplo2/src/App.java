import java.util.Scanner;

import Clases.Jugador;
import Clases.Posicion;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Scanner scNum = new Scanner(System.in);

        final int INSTANCIAS = 3;
        Jugador[] player = new Jugador[INSTANCIAS];

        String nombreCompleto;
        String fechaNacimiento;
        double altura;
        int dorsal;
        Posicion p;

        
        for (int i = 0; i < INSTANCIAS; i++) {
            System.out.println("Introduce el nombre completo: ");
            nombreCompleto = sc.nextLine();
            System.out.println();
            System.out.println("Introduce la fecha de nacimiento: ");
            fechaNacimiento = sc.nextLine();
            System.out.println();
            try {
                System.out.println("Introduce la altura: ");
                altura = scNum.nextDouble();
                
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Tienes que introducir un valor numérico\nIntroduciremos un 0 en su lugar\n");
                altura = 0;
                scNum.next();
            }
            try {
                System.out.println("Introduce el dorsal: ");
                dorsal = scNum.nextInt();
                
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Tienes que introducir un valor numérico\nIntroduciremos un 0 en su lugar\n");
                dorsal = 0;
                scNum.next();
            }
            try {
                System.out.println("Introduce la posición:");
                p = Posicion.valueOf(sc.nextLine().toUpperCase());
                
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Tienes que introducir alguna de estas opciones: Base, Escolta, Alero, Ala-Pivot o Pivot\nIntroduciremos en su lugar la posición Base\n");
                p = Posicion.BASE;
            }

            player[i] = new Jugador(nombreCompleto, fechaNacimiento, altura, dorsal, p);
            System.out.println("JUGADOR " + (i+1) + " COMPLETADO\n\n");
        }

        for (Jugador jugador : player) {
            System.out.println(jugador);
        }
    }
}
