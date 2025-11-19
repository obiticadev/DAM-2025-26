package UT02;
/**
 * Ejercicio 1.
 * Crea un programa en java que genera la tabla de multiplicar
 * de un número que se declara en una variable al inicio del mismo.
 * Solo puede usar una variable de tipo entero en todo el programa.
 */
public class e3e1 {
    public static void main(String[] args){
        System.out.println("--- Ejemplo 3 - Ejercicio 1 ---");
        int n;
        n = 8;
        System.out.println("La tabla del " + n);
        System.out.println(n + "x1=" + (n*1));
        System.out.println(n + "x2=" + (n*2));
        System.out.println(n + "x3=" + (n*3));
        System.out.println(n + "x4=" + (n*4));
        System.out.println(n + "x5=" + (n*5));
        System.out.println(n + "x6=" + (n*6));
        System.out.println(n + "x7=" + (n*7));
        System.out.println(n + "x8=" + (n*8));
        System.out.println(n + "x9=" + (n*9));
        System.out.println(n + "x10=" + (n*10));
    }
}
