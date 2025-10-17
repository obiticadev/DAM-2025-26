// Ejercicio 4
// Calcula el promedio de las notas de un usuario. Le pediremos el número de módulos que tiene, después le iremos pidiendo las notas de cada uno de los módulos, y por último le mostraremos la nota media.

import java.util.Scanner;

public class notasPromeido {
    public static void main(String[] args) throws Exception {

        String entradaTerminal;
        double nota1;
        double nota2;
        double nota3;
        int promedio;
        final int MODULOS = 3;
        double[] notas = new double[MODULOS];
        double suma = 0;

        
        Scanner stringScan = new Scanner(System.in);
        System.out.println("Introduce las tres notas:");
        entradaTerminal = stringScan.nextLine();


        Scanner lector = new Scanner(entradaTerminal);
        lector.useDelimiter("\s");
        for (int i = 0 ; i < 3 ; i++){
            notas[i] = lector.nextDouble();
            suma = suma + notas[i];
            System.out.println("Notas: " + notas[i]);
        }
        
        promedio = (int)(suma / MODULOS);
        
        System.out.println("Promedio: " + promedio);

    }
}
