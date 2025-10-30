/*
* Pediremos al usuarios 10 numeros ,
* al finalizar la peticion le mostremos el maximo y el minimo
* (haremos las pruebas con numeros entre cero y 100)
*/

import java.util.Scanner;

public class ejercicio1 {
    public static void main(String[] args) throws Exception {

        // Declaración de variables
        double[] num = new double[10];
        double max = num[0];
        final int NUM_ENTRADAS = 3;
        
        System.out.println("INTRODUCE " + NUM_ENTRADAS);
        Scanner numScan = new Scanner(System.in);
        for (int i = 0 ; i < NUM_ENTRADAS ; i++){
            System.out.print("Introduce el número " + (i+1) + ": ");
            num[i] = numScan.nextDouble();
        }
        for (int i = 1; i < num.length; i++) {
            max = (num[i] > max) ? num[i] : max;
            /*
            if (num[i] > max) {
                max = num[i];
            }
            */
        }

        System.out.println("El máximo es: " + max);
        
    }
}
