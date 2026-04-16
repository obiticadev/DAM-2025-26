package nivel8_desafios_finales;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * EJERCICIO 29 - DESAFÍO MASTER: FRECUENCIA DE PALABRAS
 * 
 * Objetivo: Integrar conocimientos de Strings, Lists, Map y Streams
 * para resolver una de las pruebas técnicas más famosas en menos de 3 líneas.
 */
public class Ejercicio29_DesafioFrecuencias {

    public static void demostracion() {
        System.out.println("--- DEMO: DESAFÍOS FINALES ---");
        System.out.println("Ha llegado el momento de mezclar todo lo aprendido sin piedad.");
        System.out.println("Map.compute(), Streams.groupingBy(), flatMap()... tú decides el arma.");
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 29: ANALIZADOR DE TEXTOS ---");
        String texto = "En un lugar de la Mancha de cuyo nombre no quiero acordarme no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero";
        
        // Limpiamos y dividimos en palabras
        List<String> palabras = Arrays.asList(texto.toLowerCase().split(" "));

        // TODO 1: Crea un Map<String, Long> 'frecuencias'.
        // Llave: La palabra (String), Valor: Cuántas veces aparece (Long).
        // Hay DIVERSAS formas de hacerlo:
        // Forma A: Usa un bucle for tradicional y mapa.merge()
        // Forma B (Recomendada): Usa el stream de 'palabras' y Collectors.groupingBy con counting()
        Map<String, Long> frecuencias = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Imprime las frecuencias para verificarlo visualmente.
        System.out.println("Frecuencias calculadas:");
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Averigua cuál es la frecuencia de la palabra "de" consultando al mapa y guarda el Long en 'usosDe'.
        Long usosDe = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 4: ¿Qué pasa si preguntas por la palabra "quijote"? No está en el texto.
        // Usa un método seguro (NO un if) para que devuelva un 0L si no existe.
        Long usosQuijote = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = frecuencias != null && frecuencias.size() > 5;
        boolean ok2 = usosDe != null && usosDe == 4L;
        boolean ok3 = usosQuijote != null && usosQuijote == 0L;

        if (ok1 && ok2 && ok3) {
            System.out.println(">> CORRECTO: Analizador textual completo construido. ¡Prueba técnica superada!\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa el conteo. 'de' debería tener 4 apariciones, 'quijote' 0.");
        }
    }
}
