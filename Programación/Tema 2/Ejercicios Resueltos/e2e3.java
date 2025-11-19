package UT02;
/**
 * Ejercicio 3.
 * Crea tres variables de tipo char y emplearlas en una sola sentencia
 * System.out.println para generar una palabre de tres letras como Sol.
 */
public class e2e3 {
    public static void main(String[] args){
        System.out.println("--- Ejemplo X - Ejercicio Y ---");
        char a, b, c;
        a = 'a';
        b = 'b';
        c = 'c';
        System.out.println(a + b + c); // salen números :(
        // Dos soluciones
        System.out.println(a + "" + b + "" + c);
        System.out.println(Character.toString(a) + Character.toString(b) + Character.toString(c));
    }
}
