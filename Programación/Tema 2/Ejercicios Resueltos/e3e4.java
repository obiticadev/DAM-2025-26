package UT02;
/**
 * Ejercicio 4.
 * Crea un programa que calcule la hipotenusa de un triángulo rectángulo
 * a partir del tamaño de sus catetos, los valores deben expresarse 
 * con decimales.
 */
public class e3e4 {
    public static void main(String[] args){
        System.out.println("--- Ejemplo 3 - Ejercicio 4 ---");
        double cateto1, cateto2, hipotenusa;
        cateto1 = 7.3;
        cateto2 = 3.67;
        hipotenusa = Math.sqrt(Math.pow(cateto1, 2) + Math.pow(cateto2, 2));
        System.out.println("Para un triangulo rectángulo en el que");
        System.out.println("sus catetos miden " + cateto1 + " y " + cateto2);
        System.out.println("la hipotenusa vale " + hipotenusa);
    }
}
