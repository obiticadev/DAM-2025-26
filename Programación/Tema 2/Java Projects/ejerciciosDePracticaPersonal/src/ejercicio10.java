
import java.util.Scanner;

/*
Enunciado:

Un dispositivo envía datos como una cadena de texto que representa un número binario de 8 bits (por ejemplo, "11001010"). Escribe un programa que lea esta cadena, la convierta a su valor numérico entero (en base 10), y luego calcule un "factor de corrección" multiplicando ese valor por 0.25. Finalmente, muestra el resultado de la corrección como un long, ignorando cualquier parte decimal.

Ejemplo de Salida:

Ingresa el dato binario de 8 bits:
10101010
El valor decimal es: 170
Factor de corrección (long): 42
*/


public class ejercicio10 {
    public static void main(String[] args) throws Exception {

        // Declaración de variables
        int binario;
        int sumaBinario = 0;
        int decimal;
        long decimalOperado;
        int decimalOperadoFinal;

        
        Scanner numScan = new Scanner(System.in);
        
        
        System.out.println("Ingresa el dato binario de 8 bits:");
        binario = numScan.nextInt();
        // Contamos el número de caracteres
        int num = String.valueOf(binario).length();

        // Y creamos estos arrays con la longitud de los caracteres contados previamente
        int[] binarioArray = new int[num];
        int[] decimalArray = new int[num];

        System.out.print("El valor decimal es: ");
        for (int i = num - 1 ; i >= 0 ; i--){
            // En este ponemos en cada variable el valor real, 1 o 0 convirtiendo el binario de entrada en String, del String sacamos el caracter. Por último sacamos el valor del caracter numérico
            binarioArray[i] = Character.getNumericValue(String.valueOf(binario).charAt(i));
            // Y en decimalArray sacamos las potencias de base dos multplicadas por los binarioArray correspondientes
            decimalArray[i] = ((int) Math.pow(2, (i+1))) * (binarioArray[i]);
            // Guardamos los datos calculados y listo
            sumaBinario = sumaBinario + decimalArray[i];
        }
        System.out.println(sumaBinario);
        
        System.out.print("Factor de corrección (long): ");
        decimalOperado = (long) (sumaBinario * 0.25);
        System.out.println(decimalOperado);
    }
}
