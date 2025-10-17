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
        
        Scanner numScan = new Scanner(System.in);
        for (int i = 0 ; i < 10 ; i++){
            System.out.print("Introduce 10 números: ");
            num[i] = numScan.nextDouble();
        }
        
    }
}
