package nivel14_master_streams;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * EJERCICIO 50 - EL TRABAJO DE INVESTIGACIÓN: DOCTORADO MASTER VANILLA
 * 
 * Objetivo: Eres el Arquitecto de un Microservicio. Tienes que construir
 * el núcleo enrutador utilizando los patrones adquiridos.
 */
public class Ejercicio50_DoctoradoFinal {

    /**
     * Requisito:
     * 1. Un Mapeo Concurrente de Rutas (Command Dispatcher).
     * 2. Soporte para Funciones limpias (Input -> Output) usando Interfaces Funcionales.
     * 3. Un sistema de conteo atómico de métricas para saber cuántas veces se visita cada ruta.
     */
    static class RouterServidor {
        
        // El mapa central. Ojo al tipo: Map<Ruta, FuncionQueTomaStringYDevuelveString>
        private final Map<String, Function<String, String>> rutas = new ConcurrentHashMap<>();
        
        // Mapa de métricas concurrentes. ConcurrentHashMap para hilos. AtomicInteger para la suma multicore.
        private final Map<String, AtomicInteger> metricas = new ConcurrentHashMap<>();

        public void registrarRuta(String path, Function<String, String> handler) {
            // TODO 1: Añade al mapa 'rutas' el 'path' con su 'handler'. (Revisa la declaración de variables arriba si dudas).
            // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
            
            // Inicializa la métrica en 0 (usando computeIfAbsent).
            metricas.computeIfAbsent(path, k -> new AtomicInteger(0));
        }

        public String simularPeticionHttp(String path, String payload) {
            // TODO 2: Incrementa la métrica atómicamente.
            // Para eso, primero coge el AtomicInteger del map 'metricas' para ese 'path'.
            // Si el AtomicInteger NO es nulo, llámale a .incrementAndGet();
            // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
            

            // TODO 3: Trata de extraer el 'handler' del map 'rutas' usando 'path' como llave.
            // Guárdalo en 'Function<String, String> handler'.
            Function<String, String> handler = null; // Reemplaza esto con un .get() al mapa
            // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
            

            // TODO 4: Si el handler es nulo (la ruta no existe), devuelve "404 NOT FOUND".
            // Si el handler existe, EJECUTA el handler pasándole el 'payload' (handler.apply(payload)) y devuelve su resultado.
            // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
            return ""; 
        }

        public int obtenerVisitas(String path) {
            AtomicInteger contador = metricas.get(path);
            return (contador != null) ? contador.get() : 0;
        }
    }

    public static void demostracion() {}
    public static void ejercicio() {
        System.out.println("\n--- DESAFÍO 50: MICRO FRAMEWORK WEB VANILLA ---");
        
        RouterServidor app = new RouterServidor();

        // Registramos un endpoint que recibe JSON y devuelve la palabra en mayúsculas
        app.registrarRuta("/api/mayusculas", req -> req.toUpperCase());
        
        // Registramos un endpoint de Eco
        app.registrarRuta("/api/eco", req -> "Eco: " + req);

        // Simulamos Tráfico
        String resp1 = app.simularPeticionHttp("/api/eco", "Hola");
        app.simularPeticionHttp("/api/eco", "Prueba 2");
        String resp404 = app.simularPeticionHttp("/rutaInvalida", "Nada");
        String respMayus = app.simularPeticionHttp("/api/mayusculas", "grito");

        // --- VALIDACIÓN DETALLADA ---
        boolean trampa404 = "404 NOT FOUND".equals(resp404);
        boolean trampaLogicaEco = "Eco: Hola".equals(resp1);
        boolean trampaLogicaMayus = "GRITO".equals(respMayus);
        boolean métricaBien = app.obtenerVisitas("/api/eco") == 2 && app.obtenerVisitas("/api/mayusculas") == 1;

        if (!trampa404) System.err.println("-> [TODO 4] Falló: La ruta inválida debía devolver '404 NOT FOUND'. Devolvió: " + resp404);
        if (!trampaLogicaEco || !trampaLogicaMayus) System.err.println("-> [TODO 1 / 3 / 4] Falló: La inyección de handlers o su ejecución `.apply()` falló. Eco=" + resp1 + " Mayus=" + respMayus);
        if (!métricaBien) System.err.println("-> [TODO 2] Falló: El métricas .incrementAndGet() no se ejecutó correctamente.");

        if (trampa404 && trampaLogicaEco && trampaLogicaMayus && métricaBien) {
            System.out.println("🎓 GRADUACIÓN CON HONORES 🎓");
            System.out.println("Has diseñado la médula espinal de Spring Boot y ExpressJS desde cero usando Java Vanilla.");
            System.out.println("Nivel de Dominio Alcanzado: DOCTORADO EN MAPS (Vanilla).");
            System.out.println("Preparando la siguiente fase... \033[0;32m [NIVEL SUPERADO]\033[0m");
        }
    }
}
