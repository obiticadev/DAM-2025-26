package com.masterclass.api.b37_fxcustom;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Ejercicio 297 · Gráficos integrados: {@code LineChart}/{@code BarChart}/{@code PieChart}.
 *
 * <p>Teoría: {@code teoria/37_JavaFX_Componentes_Canvas.md} (sección 5).
 *
 * <p>JavaFX trae gráficos listos. Tú no pintas barras a mano: construyes una
 * {@code XYChart.Series} con sus {@code XYChart.Data(x, y)} y el gráfico se dibuja solo. Lo
 * testeable —y lo que de verdad importa— es <b>preparar los datos</b>: convertir tus números en la
 * serie correcta, calcular porcentajes para una tarta, agrupar datos crudos en barras, hallar el
 * rango del eje. Aquí el core produce esas estructuras de datos (pares X/Y, porcentajes) y los
 * asserts comprueban los valores. Un par X/Y se representa como {@code double[]{x, y}}.
 */
public final class Ej297ChartsBuiltIn {

    private Ej297ChartsBuiltIn() {
    }

    /**
     * Convierte un array de valores en los pares {@code (x, y)} de una serie de líneas/barras: el
     * eje X es la POSICIÓN (1, 2, 3...) y el eje Y es el valor. Es lo que alimenta una
     * {@code XYChart.Series}.
     *
     * @param valores valores a representar en el eje Y
     * @return lista de pares {@code [x, y]} con x empezando en 1; {@code List.of()} sin implementar
     */
    public static List<double[]> serieXY(double[] valores) {
        // TODO 1: si valores es null o vacío, devuelve List.of().
        // TODO 2: crea una List<double[]> (new ArrayList<>()).
        // TODO 3: recorre i de 0 a valores.length-1.
        // TODO 4: la X del punto es (i + 1) -> el primer dato va en X=1, no en X=0.
        // TODO 5: la Y del punto es valores[i].
        // TODO 6: añade new double[]{ i + 1, valores[i] }.
        // TODO 7: devuelve la lista (el test comprueba {10,20,30} -> (1,10),(2,20),(3,30)).
        return List.of();
    }

    /**
     * Calcula el porcentaje (0..100) que representa cada valor sobre el total, para un
     * {@code PieChart}. Cada porción de la tarta es valor/total*100.
     *
     * @param valores valores de cada porción (no negativos)
     * @return lista de porcentajes en el mismo orden; {@code List.of()} sin implementar
     */
    public static List<Double> porcentajesPie(double[] valores) {
        // TODO 8: calcula el total sumando todos los valores. Si el total es 0, devuelve List.of()
        //         (una tarta vacía no tiene porciones; evita dividir por cero).
        // TODO 9: por cada valor, añade valor / total * 100 a la lista resultado.
        // TODO 10: devuelve la lista (el test comprueba {25,25,50} -> [25.0, 25.0, 50.0]).
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("Serie de {10,20,30}: " + serieXY(new double[]{10, 20, 30}).size() + " puntos");
        System.out.println("Porcentajes {25,25,50}: " + porcentajesPie(new double[]{25, 25, 50}));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Etiqueta de categoría.
     * El nombre que va en el eje X de un BarChart para la posición dada (1-based).
     */
    public static String etiquetaCategoria(int indice) {
        // GUÍA: teoría 5.1 (el CategoryAxis usa String, no números, en su eje).
        // 1. return "Cat " + indice;
        // OJO: el test prueba indice=1 -> "Cat 1" (con el espacio).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para etiquetaCategoria");
    }

    /**
     * Reto Extra 2: Máximo de la serie.
     * El valor más alto, que marca dónde debe llegar el eje Y.
     */
    public static double maximoSerie(double[] valores) {
        // GUÍA: teoría 5.1 (necesitas el máximo para no recortar las barras altas).
        // 1. Si valores es null o vacío, devuelve 0.
        // 2. Recorre llevando el mayor con Math.max.
        // OJO: el test prueba {3, 9, 5} -> 9.0; array vacío -> 0.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para maximoSerie");
    }

    /**
     * Reto Extra 3: Media de la serie.
     * El promedio de los valores (la línea de referencia "media" de un gráfico).
     */
    public static double mediaSerie(double[] valores) {
        // GUÍA: teoría 5.2 (la media es una serie horizontal extra muy común en dashboards).
        // 1. Si valores es null o vacío, devuelve 0.
        // 2. Suma todos y divide entre valores.length (¡como double!).
        // PISTA: acumula en un double; divide entre valores.length.
        // OJO: el test prueba {2, 4, 6} -> 4.0; vacío -> 0.0 (no dividas por cero).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mediaSerie");
    }

    /**
     * Reto Extra 4: Serie acumulada.
     * Cada punto es la suma de todos los anteriores más el actual (para un AreaChart de acumulado).
     */
    public static List<Double> acumulado(double[] valores) {
        // GUÍA: teoría 5.2 (la suma corrida: ventas acumuladas del año, etc.).
        // 1. Lleva un acumulador 'suma' a 0.
        // 2. Por cada valor, suma += valor y añade 'suma' a la lista.
        // OJO: el test prueba {1, 2, 3} -> [1.0, 3.0, 6.0] (1, 1+2, 1+2+3).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para acumulado");
    }

    /**
     * Reto Extra 5: Normalizar a 0..100.
     * Reescala cada valor para que el máximo sea 100 (barras de progreso comparables).
     */
    public static List<Double> normalizar0a100(double[] valores) {
        // GUÍA: teoría 5.3 (normalizar permite comparar series de escalas distintas).
        // 1. Halla el máximo (reto 2). Si es 0, devuelve una lista de 0.0 del mismo tamaño.
        // 2. Por cada valor, añade valor / maximo * 100.
        // PISTA: reutiliza maximoSerie(valores).
        // OJO: el test prueba {5, 10} -> [50.0, 100.0]; el mayor siempre acaba en 100.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizar0a100");
    }

    /**
     * Reto Extra 6: Agrupar datos crudos por categoría (BarChart desde datos sueltos).
     * Suma los importes que comparten la misma categoría, conservando el orden de aparición.
     */
    public static Map<String, Double> agruparPorCategoria(List<String> categorias, double[] importes) {
        // GUÍA: teoría 5.3 (de una lista de ventas sueltas a las barras "por sucursal").
        // 1. Usa un LinkedHashMap<String,Double> (mantiene el orden de primera aparición).
        // 2. Por cada i, mapa.merge(categorias.get(i), importes[i], Double::sum).
        // 3. Devuelve el mapa.
        // PISTA: merge(clave, valor, Double::sum) suma si la clave ya existía, o la crea si no.
        // OJO: el test usa ["A","B","A"] con [10,5,20] -> {A=30, B=5}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para agruparPorCategoria");
    }

    /**
     * Reto Extra 7: Rango "bonito" del eje Y.
     * Devuelve [0, techo] donde 'techo' es el siguiente múltiplo de 'paso' por encima del máximo.
     */
    public static double[] rangoEjeY(double[] valores, double paso) {
        // GUÍA: teoría 5.4 (un eje que llega a 73 queda feo; mejor redondear al alza a 80).
        // 1. Halla el máximo (reto 2).
        // 2. techo = Math.ceil(maximo / paso) * paso.
        // 3. return new double[]{0, techo}.
        // PISTA: Math.ceil redondea SIEMPRE hacia arriba.
        // OJO: el test prueba {73} con paso 10 -> [0, 80]; {50} con paso 10 -> [0, 50] (ya es múltiplo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rangoEjeY");
    }

    /**
     * Reto Extra 8: Tendencia de la serie.
     * Compara el último valor con el primero: "sube", "baja" o "estable".
     */
    public static String tendencia(double[] valores) {
        // GUÍA: teoría 5.4 (el indicador de flecha ▲/▼ de un KPI).
        // 1. Si hay menos de 2 valores, devuelve "estable".
        // 2. Compara valores[ultimo] con valores[0]: mayor -> "sube", menor -> "baja", igual -> "estable".
        // OJO: el test prueba {1, 5} -> "sube"; {5, 1} -> "baja"; {3, 3} -> "estable".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tendencia");
    }

    /**
     * Reto Extra 9: Top N de una tabla de datos.
     * Devuelve las N categorías de mayor importe, de mayor a menor (un "top productos").
     */
    public static List<String> topN(Map<String, Double> datos, int n) {
        // GUÍA: teoría 5.5 (los gráficos suelen mostrar solo el "top 5"; el resto va a "Otros").
        // 1. Vuelca las entradas a una lista y ordénala por valor DESCENDENTE.
        //    Comparator.comparingDouble(Map.Entry::getValue) y luego .reversed().
        // 2. Coge las n primeras claves (cuida que n no supere el tamaño: usa Math.min).
        // 3. Devuelve la lista de claves en ese orden.
        // PISTA: lista.sort(Comparator.comparingDouble(Map.Entry<String,Double>::getValue).reversed());
        // OJO: el test usa {A=10, B=30, C=20} con n=2 -> [B, C] (los dos mayores, en orden).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para topN");
    }

    /**
     * Reto Extra 10: Datos para un gráfico apilado (stacked).
     * Dadas dos series, devuelve el total apilado por posición (la altura total de cada barra apilada).
     */
    public static List<Double> apiladoTotal(double[] serieA, double[] serieB) {
        // GUÍA: teoría 5.5 (un StackedBarChart apila series; su altura es la suma por categoría).
        // 1. La longitud es la del array más corto (Math.min de ambos length).
        // 2. Por cada i, añade serieA[i] + serieB[i].
        // 3. Devuelve la lista.
        // PISTA: usa Math.min(serieA.length, serieB.length) como tope del bucle.
        // OJO: el test usa {1,2,3} y {10,20} -> [11.0, 22.0] (se para en el más corto).
        // CULTURA: estos datos agregados alimentan directamente los informes de b38.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para apiladoTotal");
    }

    /** Helper de demostración: arma un mapa ordenado categoría->importe. */
    static Map<String, Double> datos(String[] claves, double[] valores) {
        Map<String, Double> m = new LinkedHashMap<>();
        for (int i = 0; i < claves.length; i++) {
            m.put(claves[i], valores[i]);
        }
        return m;
    }

    /** Helper de demostración: arma una lista mutable de pares X/Y. */
    static List<double[]> pares(double[]... ps) {
        return new ArrayList<>(List.of(ps));
    }
}
