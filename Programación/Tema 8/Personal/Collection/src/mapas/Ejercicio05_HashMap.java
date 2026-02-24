package mapas;

import modelos.Producto;
import java.util.HashMap;
import java.util.Map;

/**
 * MÓDULO 3.1: HASHMAP (Implementación de la interfaz Map)
 * 
 * TEORÍA:
 * Técnicamente, Map NO HEREDA de Collection en Java, pero es parte del "Java
 * Collections Framework".
 * Funciona como un "Diccionario": asocia una CLAVE (única) a un VALOR.
 * 
 * PROS:
 * - Es INCREÍBLEMENTE RÁPIDO (O(1)) acceder a un valor si sabes su Clave.
 * Olvida recorrer un array con un `for` buscando un ID, haz `mapa.get(id)` y
 * directo al grano.
 * 
 * CONTRAS:
 * - NO TIENE ORDEN. No puedes ordenar un HashMap por valor (al menos no de
 * forma directa y fácil).
 * - Las Claves no pueden repetirse. Si insertas otra cosa con la misma Clave,
 * SObRESCRIBE el valor anterior.
 * 
 * ¿CUÁNDO USARLO?
 * En cualquier base de datos en memoria, sistema de cachés, o cuando necesitas
 * asociar
 * identificadores (DNI, Matrícula, ID) con un objeto entero.
 */
public class Ejercicio05_HashMap {

    public static void demostracion() {
        System.out.println("\n--- DEMOSTRACIÓN: HASHMAP ---");

        // Map<TipoClave, TipoValor> nombre = new HashMap<>();
        Map<String, Producto> catalogo = new HashMap<>();

        Producto lapiz = new Producto(1, "Lápiz", 1.50);
        Producto goma = new Producto(2, "Goma", 0.50);
        Producto regla = new Producto(3, "Regla", 2.00);

        // Añadimos con put(CLAVE, VALOR)
        catalogo.put("PAPELERIA-001", lapiz);
        catalogo.put("PAPELERIA-002", goma);
        catalogo.put("PAPELERIA-003", regla);

        System.out.println("1. Catálogo original tamaño: " + catalogo.size());

        // Acceso rapidísimo por clave
        Producto buscado = catalogo.get("PAPELERIA-002");
        System.out.println("2. Búsqueda instantánea de PAPELERIA-002: " + buscado.getNombre());

        // Si usamos una clave existente y añadimos un producto nuevo... ¡LO
        // SOBRESCRIBE!
        Producto boligrafo = new Producto(4, "Bolígrafo Azul", 1.25);
        catalogo.put("PAPELERIA-002", boligrafo);

        System.out
                .println("3. Búsqueda de PAPELERIA-002 tras reasignarlo: " + catalogo.get("PAPELERIA-002").getNombre());

        // Iterar un HashMap (Se puede iterar por claves, por valores, o por Entradas
        // completas)
        System.out.println("\nIterando sobre todas las entradas (Clave - Valor):");
        for (Map.Entry<String, Producto> entrada : catalogo.entrySet()) {
            System.out.println("Clave: " + entrada.getKey() + " -> Valor: " + entrada.getValue().getNombre());
        }
        System.out.println("--------------------------------\n");
    }

    /**
     * EJERCICIO:
     * Vas a crear un pequeño diccionario traductor Inglés-Español.
     */
    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 5: HASHMAP ---");

        // TODO: Declara un HashMap llamado 'diccionario' donde la Clave sea String
        // (Inglés) y el Valor String (Español)
        Map<String, String> diccionario = null;

        // TODO: Inserta (put) las traducciones para "Dog" -> "Perro" y "Cat" -> "Gato".

        // TODO: Oh no, te equivocaste. Inserta "Dog" -> "Perrito". Automáticamente
        // sobrescribirá el valor anterior.

        // TODO: Recupera (get) la traducción de "Cat" y guárdala en una variable
        // llamada 'traduccionCat'.
        String traduccionCat = "";

        // TODO: Usa el método remove(clave) para eliminar a "Dog" del diccionario.

        // --- CÓDIGO DE COMPROBACIÓN (NO MODIFICAR) ---
        if (diccionario != null && diccionario.size() == 1 && !diccionario.containsKey("Dog")
                && "Gato".equals(traduccionCat)) {
            System.out.println(
                    ">> ¡CORRECTO! Ya sabes hacer el equivalente a un JSON/Diccionario en Java.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(
                    ">> ALGO FALLA. \033[0;31m [ERROR]\033[0m Revisa si has insertado, sacado y eliminado las claves correctas.");
        }
        System.out.println("-------------------------------\n");
    }
}
