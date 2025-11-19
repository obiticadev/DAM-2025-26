/*
Ejercicio 3: Leer un número positivo con do-while
Escribe un programa que solicite al usuario un número positivo. Si el número ingresado es negativo,
el programa debe pedir otro número hasta que se ingrese un número positivo. Usa do-while.
 */

import java.util.Scanner;

public class ejercicio3 {
    public static void main(String[] args) throws Exception {
        System.out.print("\033[H\033[2J");
        int num;
        
        Scanner numScan = new Scanner(System.in);

        do{

            System.out.println("Introduce un número para comprobar si es positivo");
            num = numScan.nextInt();

        
            if (num >= 0){
                System.out.print("\033[H\033[2J");
                System.out.println(num +" es positivo, muy bien");
            }else{
                System.out.print("\033[H\033[2J");
                System.out.println(num + " no es positivo, vuelve a intentarlo");
            }
        }while (num < 0);
    }
}
