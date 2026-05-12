// Paquete al que pertenece esta clase
package gestorarchivos;

// Importaciones necesarias para trabajar con colecciones y mapas
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;

/**
 * Clase que se encarga de procesar una lista de nombres de archivo:
 * - Elimina duplicados
 * - Cuenta cuántas letras tiene cada nombre (ignorando símbolos, números y espacios)
 * - Ordena los nombres en orden descendente por la cantidad de letras
 */
public class ProcesadorNombres {

    /**
     * Método que procesa la lista de nombres recibida y devuelve un mapa ordenado.
     *
     * @param nombres Lista de nombres de archivo sin extensión.
     * @return Mapa con cada nombre como clave y su número de letras como valor,
     *         ordenado de mayor a menor por la cantidad de letras.
     */
    public static Map<String, Integer> procesar(List<String> nombres) {
        // Si la lista es nula, devolvemos un mapa vacío
        if (nombres == null) return new LinkedHashMap<>();

        // Creamos un mapa para almacenar los nombres y su longitud
        Map<String, Integer> mapa = new HashMap<>();

        // Recorremos la lista y procesamos cada nombre
        for (String nombre : nombres) {
            // Eliminamos todo lo que no sean letras (espacios, números, símbolos)
            String limpio = nombre.replaceAll("[^a-zA-Z]", "");

            // Solo añadimos el nombre si aún no está en el mapa (para evitar duplicados)
            if (!mapa.containsKey(nombre)) {
                mapa.put(nombre, limpio.length());
            }
        }

        // Convertimos el mapa en una lista de entradas para poder ordenarlas
        List<Entry<String, Integer>> listaOrdenada = new ArrayList<>(mapa.entrySet());

        // Ordenamos la lista usando un comparador externo definido en otra clase
        Collections.sort(listaOrdenada, new ComparadorPorValorDescendente());

        // Creamos un nuevo mapa que mantendrá el orden de inserción
        Map<String, Integer> resultado = new LinkedHashMap<>();

        // Insertamos las entradas ya ordenadas en el nuevo mapa
        for (Entry<String, Integer> entrada : listaOrdenada) {
            resultado.put(entrada.getKey(), entrada.getValue());
        }

        // Devolvemos el mapa final con los nombres ordenados por número de letras
        return resultado;
    }
}

