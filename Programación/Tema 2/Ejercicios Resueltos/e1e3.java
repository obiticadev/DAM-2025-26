package UT02;
/**
 * Ejercicio 3.
 * Ejecuta las siguientes líneas de código en un nuevo programa:
 *      System.out.print("\033[35m uno");
        System.out.print("dos");
 * Reemplaza "print" por "println" ¿Qué diferencias aprecias?
 */
public class e1e3 {
       public static void main(String[] args){
              System.out.println("--- Ejemplo 1 - Ejercicio 3 ---");
              // Sin ln
              System.out.print("\033[35m uno");
              System.out.print("dos");
              // Con ln
              System.out.println("");
              System.out.println("\033[35m uno");
              System.out.println("dos");
          }
}
