package UT02;
/**
 * Ejercicio 2.
 * Dibuja esta figura por pantalla:
 *  @@@@@@@@
 *  @ x  x @
 *  @  uu  @
 *  @@@@@@@@
 * Los ojos deben ser verdes, la boca roja y las @ amarillas.
 */
public class e1e2 {
    public static void main(String[] args){
        System.out.println("--- Ejemplo 1 - Ejercicio 2 ---");
        System.out.println("\033[33m@@@@@@@@");
        System.out.println("\033[33m@ \033[32mx  x \033[33m@");
        System.out.println("\033[33m@  \033[31muu  \033[33m@");
        System.out.println("\033[33m@@@@@@@@");
    }
}
