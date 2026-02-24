import listas.*;
import conjuntos.*;
import mapas.*;
import colas.*;
import utilidades.*;

import java.util.Scanner;

/**
 * =========================================================================
 * CURSO INTERACTIVO Y PRÁCTICO: JAVA COLLECTIONS FRAMEWORK
 * =========================================================================
 * 
 * ¡Bienvenido a tu campo de pruebas!
 * 
 * ESTRUCTURA DEL PROYECTO:
 * 1. Cada paquete (listas, conjuntos, mapas, etc.) contiene clases por cada
 * tipo de colección.
 * 2. Si abres las clases encontrarás un apartado "TEORÍA", un método
 * `demostracion()` con código ejecutándose,
 * y un método `ejercicio()` donde tendrás que escribir para superar la prueba.
 * 3. Ejecuta este archivo App.java, y un menú por consola te preguntará qué
 * quieres ejecutar.
 * 
 * INSTRUCCIONES ESTUDIANTE:
 * Selecciona una demostración en el menú para ver cómo funciona.
 * Luego, abre la clase de ese ejercicio (ej. Ejercicio01_ArrayList.java), lee
 * la teoría, busca el TODO del
 * método de ejercicio, hazlo y ejecuta la opción del menú para comprobar si la
 * consola te da [OK].
 */
public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("==============================================");
            System.out.println("           MENÚ JAVA COLLECTIONS              ");
            System.out.println("==============================================");
            System.out.println("--- 1. LISTAS (List) ---");
            System.out.println(" 11. [DEMO] ArrayList");
            System.out.println(" 12. [RETO] Ejercicio ArrayList");
            System.out.println(" 13. [DEMO] LinkedList");
            System.out.println(" 14. [RETO] Ejercicio LinkedList");
            System.out.println("\n--- 2. CONJUNTOS (Set) ---");
            System.out.println(" 21. [DEMO] HashSet");
            System.out.println(" 22. [RETO] Ejercicio HashSet");
            System.out.println(" 23. [DEMO] TreeSet");
            System.out.println(" 24. [RETO] Ejercicio TreeSet");
            System.out.println("\n--- 3. MAPAS/DICCIONARIOS (Map) ---");
            System.out.println(" 31. [DEMO] HashMap");
            System.out.println(" 32. [RETO] Ejercicio HashMap");
            System.out.println(" 33. [DEMO] TreeMap (Ordenado)");
            System.out.println(" 34. [RETO] Ejercicio TreeMap");
            System.out.println("\n--- 4. COLAS Y UTILIDADES ---");
            System.out.println(" 41. [DEMO] Colas y Deques (ArrayDeque/PriorityQueue)");
            System.out.println(" 42. [RETO] Ejercicio ArrayDeque (LIFO)");
            System.out.println(" 43. [DEMO] Clase Collections y uso de Iterator");
            System.out.println(" 44. [RETO] Ejercicio Iterator y sort()");
            System.out.println("\n 0. Salir del programa");
            System.out.print("\nElige una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 11:
                        Ejercicio01_ArrayList.demostracion();
                        break;
                    case 12:
                        Ejercicio01_ArrayList.ejercicio();
                        break;
                    case 13:
                        Ejercicio02_LinkedList.demostracion();
                        break;
                    case 14:
                        Ejercicio02_LinkedList.ejercicio();
                        break;

                    case 21:
                        Ejercicio03_HashSet.demostracion();
                        break;
                    case 22:
                        Ejercicio03_HashSet.ejercicio();
                        break;
                    case 23:
                        Ejercicio04_TreeSet.demostracion();
                        break;
                    case 24:
                        Ejercicio04_TreeSet.ejercicio();
                        break;

                    case 31:
                        Ejercicio05_HashMap.demostracion();
                        break;
                    case 32:
                        Ejercicio05_HashMap.ejercicio();
                        break;
                    case 33:
                        Ejercicio06_TreeMap.demostracion();
                        break;
                    case 34:
                        Ejercicio06_TreeMap.ejercicio();
                        break;

                    case 41:
                        Ejercicio07_ColasYDeques.demostracion();
                        break;
                    case 42:
                        Ejercicio07_ColasYDeques.ejercicio();
                        break;
                    case 43:
                        Ejercicio08_OrdenacionEIteradores.demostracion();
                        break;
                    case 44:
                        Ejercicio08_OrdenacionEIteradores.ejercicio();
                        break;

                    case 0:
                        System.out.println("¡Nos vemos! Sigue practicando.");
                        break;

                    default:
                        System.out.println("Opción incorrecta.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número.");
            }
        }
        scanner.close();
    }
}
