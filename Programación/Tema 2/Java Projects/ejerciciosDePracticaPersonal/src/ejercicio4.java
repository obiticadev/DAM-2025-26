
import java.util.Scanner;

/*Parte 2: Ejercicios Combinados (Scanner + Conversión)
Estos ejercicios requieren que pidas datos al usuario y luego los manipules.
Ejercicio 4: Calculadora de Suma Simple
Objetivo: Leer datos de tipo texto y convertirlos a número para operar.
Crea un objeto Scanner para leer la entrada del teclado.
Pide al usuario que ingrese un primer número (como String).
Pide al usuario que ingrese un segundo número (también como String).
Convierte ambas cadenas a tipo double usando Double.parseDouble().
Suma los dos números e imprime el resultado con un mensaje claro.
No olvides cerrar el scanner.
Ejemplo de salida:

Ingresa el primer número: 10.5
Ingresa el segundo número: 20
El resultado de la suma es: 30.5*/


public class ejercicio4 {
    public static void main(String[] args) throws Exception {

        // Declaración de variables
        String num1;
        String num2;
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Ejercicio 4: Calculadora de Suma Simple");
        System.out.println("Ingresa el primer número: ");
        num1 = sc.nextLine();
        System.out.println("Ingresa un segundo númeor: ");
        num2 = sc.nextLine();

        double newNum1 = Double.parseDouble(num1);
        double newNum2 = Double.parseDouble(num2);

        double suma = newNum1 + newNum2;
        int sumaInt = (int) newNum1 + (int) newNum2;
        

        System.out.println("Ingresa el primer número: " + newNum1 + " en double");
        System.out.println("Ingresa el segundo número: " + newNum2 + " en double");
        System.out.println("El resultado de la suma es: " + suma + " en double, en int sería " + sumaInt);

    }
}
