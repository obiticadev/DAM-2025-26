package conjuntos;

import modelos.Producto;
import java.util.HashSet;
import java.util.Set;

/**
 * MÓDULO 2.1: HASHSET (Implementación de la interfaz Set)
 * 
 * TEORÍA:
 * Un Set (Conjunto) es una colección que NO PERMITE DUPLICADOS.
 * La implementación HashSet usa internamente un "Hash" (una tabla dispersa)
 * para guardar y buscar.
 * 
 * PROS:
 * - Es INCREÍBLEMENTE RÁPIDO O(1) para buscar si un elemento existe (contains),
 * así como para añadir (add) y borrar (remove).
 * - Ideal para filtrar duplicados de una lista.
 * 
 * CONTRAS:
 * - NO TIENE ORDEN. No puedes pedirle el elemento '0' ni el '1' (no hay método
 * .get(indice)).
 * - Ni siquiera mantiene el orden de inserción. Entran de una forma y salen de
 * otra (completamente caótico).
 * - Exige que la clase de los objetos guardados tenga bien programados los
 * métodos equals() y hashCode().
 * 
 * ¿CUÁNDO USARLO?
 * Cuando solo te importa saber rápidamente "SI ALGO EXISTE O NO" en el grupo y
 * asegurarte
 * de que nunca haya elementos repetidos.
 */
public class Ejercicio03_HashSet {

    public static void demostracion() {
        System.out.println("\n--- DEMOSTRACIÓN: HASHSET ---");

        // Fíjate en que usamos la interfaz Set genérica.
        Set<Producto> inventario = new HashSet<>();

        Producto raton = new Producto(1, "Ratón Logitech", 25.50);
        Producto teclado = new Producto(2, "Teclado Mec", 80.99);

        // Creamos OTRA instancia de producto en memoria pero con el MISMO ID.
        // Como sobreescribimos equals() y hashCode() en la clase Producto, el Set sabrá
        // que son el "mismo" lógicamente.
        Producto ratonDuplicado = new Producto(1, "Raton Copia Barata", 10.00);

        System.out.println("1. Añadimos un ratón: " + inventario.add(raton)); // true
        System.out.println("2. Añadimos un teclado: " + inventario.add(teclado)); // true

        // Al intentar meter "ratonDuplicado" con id=1, el Set ve que el hash es igual e
        // internamente
        // ejecuta equals(). Como equals dice que sí, deniega la entrada y devuelve
        // false.
        System.out.println("3. Intentamos añadir el ratón duplicado (mismo ID): " + inventario.add(ratonDuplicado)); // false

        System.out.println("Contenido final (Fíjate en que puede que no salgan en el orden que entraron):");
        for (Producto p : inventario) {
            System.out.println("- " + p);
        }
        System.out.println("--------------------------------\n");
    }

    /**
     * EJERCICIO:
     * Tienes una serie de emails de suscripciones. Como los usuarios se equivocan
     * pulsando, el arreglo de
     * debajo tiene correos repetidos.
     * Únete al poder del HashSet para que con solo unas líneas filtres los
     * repetidos.
     */
    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 3: HASHSET ---");

        String[] emailsRegistrados = {
                "usuario1@gmail.com",
                "usuario2@gmail.com",
                "admin@gmail.com",
                "usuario1@gmail.com", // Repetido
                "bot@spam.com",
                "admin@gmail.com" // Repetido
        };

        // TODO: Crea un HashSet de Strings llamado emailsUnicos.
        Set<String> emailsUnicos = null; // <- Inicializa aquí como new HashSet<>()

        // TODO: Mediante un for (o for-each) añade todos los emails del array al
        // HashSet.
        // Comprobarás que el HashSet automáticamente ignora las repeticiones.

        // --- CÓDIGO DE COMPROBACIÓN (NO MODIFICAR) ---
        if (emailsUnicos != null && emailsUnicos.size() == 4 &&
                emailsUnicos.contains("usuario1@gmail.com") && !emailsUnicos.contains("inventado@mail.com")) {
            System.out
                    .println(">> ¡CORRECTO! Una de las principales utilidades de Set dominada.\033[0;32m [OK]\033[0m");
            System.out.println("Emails limpios: " + emailsUnicos);
        } else {
            System.err
                    .println(">> ALGO FALLA. \033[0;31m [ERROR]\033[0m ¿Has creado y rellenado el Set correctamente?");
            if (emailsUnicos != null)
                System.out.println("Tamaño de los emails únicos calculado: " + emailsUnicos.size());
        }
        System.out.println("-------------------------------\n");
    }
}
