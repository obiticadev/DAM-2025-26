package nivel15_google_guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * EJERCICIO 51 - EL MULTIMAP DE GUAVA
 * 
 * Objetivo: Sustituir el tedioso Map<K, List<V>> de Java Vanilla
 * por el limpio y fluido Multimap de Google.
 */
public class Ejercicio51_Multimap {

    public static void demostracion() {
        System.out.println("--- DEMO: MUTIMAP ---");
        // No instanciamos con new, sino estáticamente con su propia factoría.
        Multimap<String, String> generos = ArrayListMultimap.create();

        // Operador PUT acumula de forma transparente. ¡No sobreescribe! ¡Agrupa!
        generos.put("Terror", "Alien");
        generos.put("Terror", "The Thing");
        generos.put("Comedia", "Scary Movie");

        System.out.println("Generos acumulados: " + generos);
        System.out.println("Las películas de terror (Saca un List<String>): " + generos.get("Terror"));
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 51: EL HISTORIAL DE COMPRAS ---");

        // TODO 1: Crea un `Multimap<String, String>` llamado 'carrito'
        // usando `ArrayListMultimap.create()`.
        Multimap<String, String> carrito = ArrayListMultimap.create();
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<

        // TODO 2: Agrega al carrito bajo el cliente "Pedro", el producto "Ratón".
        // Luego agrega, también para "Pedro", el producto "Teclado" y por último "Pantalla".
        // (Deberás hacer 3 put() diferentes. No te preocupes de si machacan, porque Guava NO machaca).
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        carrito.put("Pedro", "Ratón");
        carrito.put("Pedro", "Teclado");
        carrito.put("Pedro", "Pantalla");

        // TODO 3: Elimina EXCLUSIVAMENTE el "Teclado" de Pedro sin borrar el resto.
        // Pista: carrito.remove(key, value); 
        // ¡En Vanilla esto era carrito.get("Pedro").remove("Teclado") y chequeos de null!
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        carrito.remove("Pedro", "Teclado");

        // --- VALIDACIÓN DETALLADA ---
        boolean ok1 = carrito != null;
        boolean okSize = false;
        boolean okResto = false;

        if (ok1) {
            okSize = carrito.size() == 2; // Quedan Ratón y Pantalla (Tamanio total aplastado es 2 pares de K-V temporales).
            okResto = carrito.get("Pedro").contains("Pantalla") && !carrito.get("Pedro").contains("Teclado");
        }

        if (!ok1) System.err.println("-> [TODO 1] Falló: No creaste el ArrayListMultimap.");
        if (ok1 && !okSize) System.err.println("-> [TODO 2/3] Falló: La longitud total debía ser 2. (Insertaste 3 y borraste 1). Size actual: " + carrito.size());
        if (okSize && !okResto) System.err.println("-> [TODO 3] Falló: El borrado individual con Guava remove(K,V) no se ejecutó bien.");

        if (ok1 && okSize && okResto) {
            System.out.println(">> PERFECTO: Cuando devuelvas datos DTO relacionales en bases de datos (Uno-A-Muchos), Guava simplifica tu vida al 1000%. \033[0;32m [OK]\033[0m");
        }
    }
}
