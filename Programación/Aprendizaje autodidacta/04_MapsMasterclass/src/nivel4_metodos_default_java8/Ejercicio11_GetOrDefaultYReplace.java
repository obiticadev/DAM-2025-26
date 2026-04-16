package nivel4_metodos_default_java8;

import java.util.HashMap;
import java.util.Map;

/**
 * EJERCICIO 11 - SEGURIDAD AL LEER Y MODIFICAR: getOrDefault Y replace
 * 
 * Objetivo: Cómo prevenir NullPointerExceptions limpiamente al leer,
 * y cómo actualizar SOLAMENTE datos que consideremos que ya existían con replace().
 */
public class Ejercicio11_GetOrDefaultYReplace {

    public static void demostracion() {
        System.out.println("--- DEMO: GetOrDefault y Replace ---");
        Map<Integer, String> mesas = new HashMap<>();
        mesas.put(1, "Libre");

        // getOrDefault evita que "valor" sea null si la clave no existe.
        String estadoMesa2 = mesas.getOrDefault(2, "Desconocido");
        System.out.println("Estado mesa 2 (no existe): " + estadoMesa2);

        // replace(K, V) solo actúa sobre claves que SÍ existan, si no, ignora.
        mesas.replace(2, "Ocupada"); // Falla silenciosamente, la mesa no existe
        mesas.replace(1, "Ocupada"); // Éxito
        
        System.out.println("Mesas final: " + mesas + "\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 11: PANEL DE CONFIGURACIÓN ---");
        Map<String, Integer> preferenciasUsuario = new HashMap<>();
        preferenciasUsuario.put("Volumen", 60);
        preferenciasUsuario.put("Brillo", 80);

        // TODO 1: Queremos leer la preferencia de "Daltonismo". 
        // Como el usuario nunca la definió, usar `get("Daltonismo")` devolvería un null horrendo.
        // Usa getOrDefault() para leerla, asignando -1 de default, y guárdalo en 'ajusteDaltonismo'.
        Integer ajusteDaltonismo = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Alguien ha pulsado "Subir Volumen al 100". Modifica "Volumen" a 100 usando replace().
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Existe una política estricta en el sistema. NO puedes invocar código nuevo desde replace
        // si la llave no existía. Intenta usar replace("Contraste", 50) y verifica si funcionó (no debería).
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 4: Hay una VERSIÓN AVANZADA de replace(K key, V oldValue, V newValue).
        // Solo reemplaza si el valor actual coincide exactamente con el oldValue.
        // Prueba: replace("Brillo", 80, 100). Debería subir el brillo a 100 porque el viejo era 80.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = ajusteDaltonismo != null && ajusteDaltonismo == -1;
        boolean ok2 = preferenciasUsuario.get("Volumen") != null && preferenciasUsuario.get("Volumen") == 100;
        boolean ok3 = !preferenciasUsuario.containsKey("Contraste");
        boolean ok4 = preferenciasUsuario.get("Brillo") != null && preferenciasUsuario.get("Brillo") == 100;

        if (ok1 && ok2 && ok3 && ok4) {
            System.out.println(">> CORRECTO: Manejas magistralmente las lecturas seguras y los reemplazos estrictos.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa las respuestas. Las aserciones demuestran que te equivocaste de método o no leíste bien y alteraste la estructura base.");
        }
    }
}
