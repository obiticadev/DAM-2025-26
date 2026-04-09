package nivel3_implementaciones_map;

import java.util.HashMap;
import java.util.Map;

/**
 * EJERCICIO 06 - HASHMAP Y EL CAOS ORDENADO
 * 
 * Objetivo: Entender que HashMap no garantiza el orden de iteración.
 */
public class Ejercicio06_HashMapDesorden {

    public static void demostracion() {
        System.out.println("--- DEMO: EL ORDEN DEL HASHMAP ---");
        Map<String, String> letras = new HashMap<>();
        letras.put("Zeta", "Z");
        letras.put("Alfa", "A");
        letras.put("Beta", "B");
        letras.put("Gamma", "G");

        // Al imprimir, puede que te sorprenda ver que NO sigue un orden predecible,
        // depende de la función hash de los Strings.
        System.out.println("Llaves iteradas: " + letras.keySet() + "\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 06: BÚSQUEDA ULTRARRÁPIDA ---");
        // Tú trabajas para el registro de vehículos de un país enorme.
        // Se necesitan guardar matrículas(Key) y Propietario(Value).
        Map<String, String> vehiculos = new HashMap<>();
        vehiculos.put("1234ABC", "Juan Pérez");
        vehiculos.put("9999ZZZ", "Marta Gómez");
        vehiculos.put("0000AAA", "Luis López");
        vehiculos.put("5555WWW", "Ana Rodríguez");

        // TODO 1: Registra 2 vehículos más: "8888BBB" perteneciente a ti mismo, y una llave nula ("null") perteneciente a "Vehículo Sin Matrícula".
        // Recuerda: ¿Permite HashMap claves nulas?
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Busca el propietario del vehículo "9999ZZZ" usando get() y guárdalo en 'buscado'.
        String buscado = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Usa forEach para iterar e intenta observar el orden de salida (si en consola sale distinto al de inserción).
        System.out.println("Listado de vehículos (observa el orden):");
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 4: Modifica el valor asociado al propietario "Juan Pérez" (llave "1234ABC") concatenándole " - MULTADO".
        // Usa put() o replace() (si sabes de él, lo veremos más adelante).
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        if (vehiculos.size() == 6 && vehiculos.get(null) != null && "Marta Gómez".equals(buscado) && vehiculos.get("1234ABC").contains("MULTADO")) {
            System.out.println(">> CORRECTO: Sabes usar HashMap y observaste que el orden no se mantiene ni importa.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Verifica la cuenta de vehículos, los nulls o las actualizaciones.");
        }
    }
}
