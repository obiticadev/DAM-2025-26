package mapas;

import java.util.Map;
import java.util.TreeMap;

/**
 * MÓDULO 3.2: TREEMAP (Implementación de Map y NavigableMap)
 * 
 * TEORÍA:
 * Igual que con HashSet y TreeSet... TreeMap hace lo mismo que HashMap PERO
 * ordenando
 * el diccionario SIEMPRE por su CLAVE.
 * 
 * PROS:
 * - Obtienes un Diccionario Clave-Valor que SIEMPRE está listado por orden
 * (alfabético, numérico...).
 * - Te permite hacer recortes del diccionario por límites de Clave (por ejemplo
 * "Dame todos los usuarios del rango de Letras M a Z").
 * 
 * CONTRAS:
 * - Es más lento (O(log n)) que el HashMap en encontrar y añadir, porque hay
 * que organizar el árbol (por sus CLAVES).
 * 
 * ¿CUÁNDO USARLO?
 * Un ejemplo clásico: Listines telefónicos, un diccionario real de palabras
 * indexadas alfabéticamente, o
 * guardar un registro de ventas ordenado por fecha (donde la Clave es
 * LocalDateTime y el Valor es la Venta).
 */
public class Ejercicio06_TreeMap {

    public static void demostracion() {
        System.out.println("\n--- DEMOSTRACIÓN: TREEMAP ---");

        // Crearemos un registro de edades por nombre. Clave = Nombre (String), Valor =
        // Edad (Integer).
        // Al usar un String como clave, se usará el orden alfabético automático de los
        // Strings.
        Map<String, Integer> edades = new TreeMap<>();

        edades.put("Zoe", 25);
        edades.put("Ana", 30);
        edades.put("Carlos", 22);
        edades.put("Beatriz", 28);

        System.out.println("Las claves se imprimen automáticamente ordenadas por orden alfabético:");
        for (Map.Entry<String, Integer> e : edades.entrySet()) {
            System.out.println(e.getKey() + " tiene " + e.getValue() + " años.");
        }

        // Al tener TreeMap podemos averiguar el primer y último registro (Claves).
        // Hay que hacer un casting de interfaz Map al tipo implementado TreeMap, o
        // haberlo declarado como TreeMap arriba directamente.
        TreeMap<String, Integer> directEdades = (TreeMap<String, Integer>) edades;

        System.out.println("\nPrimer nombre del listado (diccionario alfabético): " + directEdades.firstKey());
        System.out.println("Último nombre del listado: " + directEdades.lastKey());
        System.out.println("--------------------------------\n");
    }

    /**
     * EJERCICIO:
     * Vas a hacer un registro de "Año" (Clave Integer) y "Acontecimiento" (Valor
     * String).
     * Queremos que independientemente de en qué orden lo añadas, al iterar el
     * TreeMap nos dé la línea
     * temporal histórica correctamente ordenada (los ints se ordenan de < a >
     * automáticamente).
     */
    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 6: TREEMAP ---");

        // TODO: Declara un Map TreeMap llamado 'lineaTemporal' con Clave 'Integer' y
        // Valor 'String'.
        Map<Integer, String> lineaTemporal = null;

        // TODO: Añade el año 1969 con el texto "Llegada a la luna"

        // TODO: Añade el año 1492 con el texto "Descubrimiento de América"

        // TODO: Añade el año 1914 con el texto "Primera guerra mundial"

        // TODO: Añade el año 2007 con el texto "Presentación del primer iPhone"

        // Ahora vamos a extraer todas las llaves en un array o lista para comprobar el
        // orden.
        // keySet() nos devuelve un Set con todas las llaves ORDENADAS (pues provienen
        // de un TreeMap).
        Integer primerAñoMundial = 0;
        Integer ultimoAñoMundial = 0;

        int cont = 0;
        if (lineaTemporal != null) {
            for (Integer year : lineaTemporal.keySet()) {
                if (cont == 0)
                    primerAñoMundial = year;
                if (cont == lineaTemporal.size() - 1)
                    ultimoAñoMundial = year;
                cont++;
            }
        }

        // --- CÓDIGO DE COMPROBACIÓN (NO MODIFICAR) ---
        if (lineaTemporal.size() == 4 && primerAñoMundial == 1492 && ultimoAñoMundial == 2007) {
            System.out.println(
                    ">> ¡CORRECTO! Orden cronológico garantizado sin necesidad de llamar a `.sort()`.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(
                    ">> ALGO FALLA. \033[0;31m [ERROR]\033[0m Revisa si los enteros que pusiste como Clave son correctos.");
        }
        System.out.println("-------------------------------\n");
    }
}
