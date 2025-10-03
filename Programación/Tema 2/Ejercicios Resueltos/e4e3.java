package UT02;
/**
 * Ejercicio 3.
 * Realiza conversiones de texto a variables de tipo double y float.
 * (Busca en internet si no encuentras la forma de hacerlo!)
 */
public class e4e3 {
    public static void main(String[] args){
        System.out.println("--- Ejemplo 4 - Ejercicio 3 ---");
        String piTexto = "3.1415";
        float piNumero = Float.parseFloat(piTexto);
        System.out.println("El valor de pi: " + piNumero);
        String eTexto = "2.7182";
        double eNumero = Double.parseDouble(eTexto);
        System.out.println("El valor de e: " + eNumero);
    }
}
