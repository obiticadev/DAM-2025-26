import java.util.Scanner;

public class Con_mas_de_3_variables_4 {


/*
 * Conversion a horas y minutos Realiza un programa que reciba una cantidad de minutos
 * y muestre por pantalla a cuantas horas y minutos corresponde.
 * Por ejemplo: 1000 minutos son 16 horas y 40 minutos.
 */

    public static void main(String[] args) {
        // Declaración de variables
        int entradaMin;
        int horas;
        int min;
        


        // Arrancamos la librería de Scanner
        Scanner numScanner = new Scanner(System.in);

        System.out.println("DAME MINUTOS Y TE LO PASO A HORAS Y MINUTOS");
        
        System.out.println("Entregame unos minutos para la conversión");
        entradaMin = numScanner.nextInt();
        


        horas = entradaMin / 60;
        min = entradaMin % 60;

        System.out.println(entradaMin + " min son: " + horas + "h " + min + " min");
    }
}
