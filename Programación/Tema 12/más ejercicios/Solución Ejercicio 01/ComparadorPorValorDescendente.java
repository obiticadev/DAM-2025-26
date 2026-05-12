package gestorarchivos;

import java.util.Comparator;
import java.util.Map.Entry;

/**
 * Comparador que permite ordenar pares clave-valor (Entry) de String e Integer
 * según el valor numérico (número de letras) en orden descendente.
 */
public class ComparadorPorValorDescendente implements Comparator<Entry<String, Integer>> {

    @Override
    public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
        // Ordena de mayor a menor según el valor (número de letras)
        return Integer.compare(e2.getValue(), e1.getValue());
    }
}

