/*
Calcular sueldo con horas extra

Pide al usuario: horas trabajadas en la semana

horas extra trabajadas

Usa una constante para el valor de la hora normal y otra para la hora extra. Calcula y muestra el sueldo total.
 
*/

import java.util.Scanner;

public class Con_constantes_2 {
    public static void main(String[] args) {
        
        // Declaración de variables
        float horas;
        float horasExtra;
        final float euroxHoras = 12.5f;
        float salario;

        System.out.println("CALCULAMOS TU SALARIO POR LAS HORAS TRABAJADAS\n");

        // Arrancamos con la librería de Scanner
        Scanner numScan = new Scanner(System.in);
        
        System.out.println("¿Cuántas horas has trabajado?");
        horas = numScan.nextFloat();

        System.out.println("¿Y cuántas horas extra?");
        horasExtra = numScan.nextFloat();

        salario = (horas + horasExtra) * euroxHoras;

        System.out.println("Hoy cobras " + salario + " €");
    }
}
