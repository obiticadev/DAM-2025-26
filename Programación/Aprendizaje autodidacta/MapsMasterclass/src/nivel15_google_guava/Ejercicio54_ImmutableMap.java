package nivel15_google_guava;

import com.google.common.collect.ImmutableMap;

/**
 * EJERCICIO 54 - FÁBRICA INMUTABLE CON BUILDER DE GUAVA
 * 
 * Objetivo: Entender cómo configurar un DTO/Diccionario constante y complejo utilizando
 * el patrón de diseño Builder, un standard de Guava para los ImmutableMaps.
 */
public class Ejercicio54_ImmutableMap {

    public static void demostracion() {
        System.out.println("--- DEMO: GUAVA BUILDER PATTERN ---");
        // Guava introdujo en el 2011 lo que Java metió a medias en el 2019. 
        // Usando un Builder, podemos concatenar infinitamente los ".put()" con llamadas variables de lógica if/else
        // en medio de su construcción, construyendo luego UN mapa inmutable final.
        ImmutableMap<Integer, String> estatus = ImmutableMap.<Integer, String>builder()
            .put(200, "OK")
            .put(404, "Not Found")
            .put(500, "Server Error")
            .build(); // Sella el mapa para siempre.

        System.out.println("Diccionario Constante HTTP (Tamaño: " + estatus.size() + "): " + estatus);

        try {
            estatus.put(403, "Hacker");
        } catch (UnsupportedOperationException e) {
            System.out.println("La coraza Inmutable defendió al mapa Guava de alteraciones póstumas.");
        }
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 54/FINAL: EL ENTABLADO DEL SISTEMA IMPERANTE ---");

        // TODO 1: Utiliza el `ImmutableMap.<String, String>builder()` para crear una constante
        // Configuración de red llamada 'entornoApp'.
        // Llena el builder con: "IP" -> "127", "PORT" -> "80", "MODE" -> "PROD".
        // No te olvides de llamar al método `.build()` al final de cadena.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        ImmutableMap<String, String> entornoApp = ImmutableMap.<String, String>builder()
            .put("IP", "127")
            .put("PORT", "80")
            .put("MODE", "PROD")
            .build();

        // TODO 2: Al igual que en Vanilla, los Guava Inmutables desprecian los NULLs.
        // Haz un Try/Catch(NullPointerException e).
        // Dentro, instancia `ImmutableMap.of("Prueba", null);` (El equivalente Guava al Map.of()).
        // Si salta NPE, pon `trampaNulaDetectada` en true.
        boolean trampaNulaDetectada = false;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        try {
            ImmutableMap.of("Prueba", null);
        } catch (NullPointerException e) {
            trampaNulaDetectada = true;
        }

        // --- VALIDACIÓN DETALLADA ---
        boolean ok1 = entornoApp != null && entornoApp.size() == 3;
        boolean ok2 = entornoApp != null && "PROD".equals(entornoApp.get("MODE"));

        if (!ok1) System.err.println("-> [TODO 1] Falló: No creaste el builder con éxito (O fallaste al poner los datos).");
        if (ok1 && !ok2) System.err.println("-> [TODO 1] Falló: El valor de MODE no corresponde a PROD.");
        if (ok2 && !trampaNulaDetectada) System.err.println("-> [TODO 2] Falló: Guava, al igual que Vanilla prohibe valores NULL en ImmutableMaps por Performance y seguridad. Debías haber cazado el NPE atrapado en el try/catch.");

        if (ok1 && ok2 && trampaNulaDetectada) {
            System.out.println(">> PERFECTO: Ahora conoces el poder absoluto de las bibliotecas de Google en el Back-end Java. \033[0;32m [OK]\033[0m");
            System.out.println();
            System.out.println("==========================================================");
            System.out.println("🎓🎓 MASTERCLASS SUPERADA: EL PROYECTO HA TERMINADO 🎓🎓");
            System.out.println("==========================================================");
        }
    }
}
