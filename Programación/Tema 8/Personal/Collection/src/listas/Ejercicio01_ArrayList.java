package listas;

import modelos.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 * MÓDULO 1.1: ARRAYLIST (Implementación de la interfaz List)
 * 
 * TEORÍA:
 * Un ArrayList es un array dinámico que puede crecer y encogerse en tiempo de
 * ejecución.
 * 
 * PROS:
 * - Acceso a cualquier elemento muy rápido O(1) si sabemos el índice (ej:
 * lista.get(5)).
 * - Es muy rápido al añadir elementos AL FINAL de la lista.
 * - Consume menos memoria (por elemento) que otras colecciones.
 * 
 * CONTRAS:
 * - Es MUY LENTO si intentamos insertar o borrar en medio o al principio de la
 * lista,
 * porque al ser un array internamente, tiene que desplazar el resto de
 * elementos hacia la derecha/izquierda.
 * 
 * ¿CUÁNDO USARLO?
 * En el 90% de los casos que necesites una lista en Java, úsalo. Especialmente
 * cuando
 * solo quieres añadir elementos uno tras otro y leerlos.
 */
public class Ejercicio01_ArrayList {

    public static void demostracion() {
        System.out.println("\n--- DEMOSTRACIÓN: ARRAYLIST ---");

        // Se recomienda declarar la variable con la interfaz (List) y no con la clase
        // (ArrayList)
        // Esto permite cambiar en el futuro el tipo de lista facilmente.
        List<Producto> carrito = new ArrayList<>();

        // Añadir al final (O(1) - Rápido)
        System.out.println("1. Añadiendo elementos al final...");
        carrito.add(new Producto(1, "Ratón Logitech", 25.50));
        carrito.add(new Producto(2, "Teclado Mecánico", 80.99));
        carrito.add(new Producto(3, "Monitor 144Hz", 199.00));

        // Imprimir lista (toString se invoca implícitamente)
        System.out.println("Contenido actual: " + carrito);

        // Acceso por índice (O(1) - Rápido)
        Producto segundo = carrito.get(1);
        System.out.println("2. Saco el segundo producto (índice 1): " + segundo.getNombre());

        // Inserción en una posición específica (O(n) - Lento porque desplaza los demás)
        System.out.println("3. Añado un Auricular en la posición 1...");
        carrito.add(1, new Producto(4, "Auriculares Sony", 50.00));

        // Mostramos el resultado. Nota que Teclado y Monitor se han desplazado:
        System.out.println("Contenido tras inserción: " + carrito);
        System.out.println("Tamaño total: " + carrito.size() + " elementos.");
        System.out.println("--------------------------------\n");
    }

    /**
     * EJERCICIO:
     * Queremos añadir a un ArrayList 3 nombres String.
     * Luego, queremos actualizar el segundo nombre modificándolo por "Maria".
     * Finalmente, comprueba si la lista contiene el nombre "Maria".
     */
    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 1: ARRAYLIST ---");

        // 1. Inicializa un ArrayList de Strings llamado 'nombres'
        List<String> nombres = new ArrayList<>(); // <-- DESCOMENTA Y COMPLETA AQUÍ SI ES NECESARIO

        // TODO: Añade "Juan", "Pedro", "Ana" (en ese orden).

        // TODO: Cambia ("Set") la posición del elemento 1 ("Pedro") para que ahora sea
        // "Maria".

        // TODO: Comprueba si la lista contiene "Maria" (método contains). Guarda en
        // 'existe'
        boolean existe = false;

        // --- CÓDIGO DE COMPROBACIÓN (NO MODIFICAR) ---
        if (nombres.size() == 3 && nombres.get(1).equals("Maria") && existe) {
            System.out.println(">> ¡CORRECTO! Has entendido los métodos básicos de ArrayList.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> ALGO FALLA. \033[0;31m [ERROR]\033[0m Revisa los índices y los métodos usados.");
            System.out.println("Contenido de nombres: " + nombres);
        }
        System.out.println("-------------------------------\n");
    }
}
