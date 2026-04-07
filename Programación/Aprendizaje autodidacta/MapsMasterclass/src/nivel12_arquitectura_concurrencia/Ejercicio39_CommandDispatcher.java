package nivel12_arquitectura_concurrencia;

import java.util.HashMap;
import java.util.Map;

/**
 * EJERCICIO 39 - PATRÓN COMMAND DISPATCHER
 * 
 * Objetivo: Destruir bloque switch-case inmensos inyectando
 * comportamiento dinámico en un diccionario usando Interfaces Funcionales (Runnable).
 */
public class Ejercicio39_CommandDispatcher {

    static class Motor {
        boolean encendido = false;
        long rpm = 0;
        
        public void arrancar() { this.encendido = true; System.out.println("Brum!"); }
        public void acelerar() { this.rpm += 1000; System.out.println("Fiiium! RPM: " + rpm); }
        public void apagar()   { this.encendido = false; this.rpm = 0; }
    }

    public static void demostracion() {
        System.out.println("--- DEMO: COMMAND DISPATCHER ---");
        Motor motor = new Motor();
        
        // Enlazar botones (Strings de consola) a funciones (Métodos del objeto real).
        Map<String, Runnable> consola = new HashMap<>();
        consola.put("START", motor::arrancar);
        consola.put("ACCEL", motor::acelerar);
        consola.put("STOP", motor::apagar);

        // Simulamos el input del usuario iterando una lista:
        String[] comandos = {"START", "ACCEL", "STOP"};
        for(String cmd : comandos) {
            // El Default hace nada (Runnable vacío () -> {}) si el comando no existe.
            consola.getOrDefault(cmd, () -> System.out.println("Comando inválido")).run();
        }
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 39: ENRUTADOR VIRTUAL (API REST) ---");
        
        // Vamos a guardar las "respuestas" acumuladas en este un array de un solo elemento para poder mutarlo en lambda.
        final String[] responseBuffer = {""}; 

        // TODO 1: Crea un Map<String, Runnable> llamado 'router'.
        Map<String, Runnable> router = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Configura 3 rutas en tu router usando Lambdas () -> { ... }:
        // "/home" -> Asigna a responseBuffer[0] el texto "<h1>Inicio</h1>"
        // "/api"  -> Asigna a responseBuffer[0] el texto "{ status: 200 }"
        // "/log"  -> Asigna a responseBuffer[0] el texto "Logs vacíos"
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Ejecuta la ruta solicitada en 'pathCliente' buscando en tu map.
        String pathCliente = "/api";
        // IMPORTANTE: Usa getOrDefault (para que si meten una ruta que no existe, la lambda por defecto responda "404"). 
        // Y NO OLVIDES llamar a .run() al final.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN DETALLADA ---
        boolean ok1 = router != null;
        boolean ok2 = ok1 && router.size() == 3;
        boolean ok3 = responseBuffer[0].equals("{ status: 200 }");

        if (!ok1) System.err.println("-> [TODO 1] Falló: El mapa router no ha sido instanciado.");
        if (ok1 && !ok2) System.err.println("-> [TODO 2] Falló: El router debía tener exactamente 3 rutas definidas.");
        if (ok2 && !ok3) System.err.println("-> [TODO 3] Falló: El enrutador no se ejecutó, el buffer devolvió: " + responseBuffer[0] + " en lugar de '{ status: 200 }'. ¿Llamaste al .run()?");

        if (ok1 && ok2 && ok3) {
            System.out.println(">> PERFECTO: Has destrozado los if/else inyectando métodos lógicos al Diccionario. Pura elegancia O(1). \033[0;32m [OK]\033[0m");
        }
    }
}
