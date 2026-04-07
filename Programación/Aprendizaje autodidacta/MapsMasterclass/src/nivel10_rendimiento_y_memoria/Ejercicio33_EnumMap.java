package nivel10_rendimiento_y_memoria;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * EJERCICIO 33 - EL FERRARI DE LOS MAPAS: EnumMap
 * 
 * Objetivo: Entender cómo crear y usar la implementación más rápida del API
 * de colecciones cuando tus llaves son Enums.
 */
public class Ejercicio33_EnumMap {

    // Definimos un Enum privado para la demostración
    public enum Rol { ADMIN, USUARIO, INVITADO }

    public static void demostracion() {
        System.out.println("--- DEMO: EnumMap ---");
        // A diferencia del HashMap, EnumMap REQUIERE en el constructor saber qué Enum vas a usar.
        // Esto le permite pre-reservar internamente un Array de exactamente 3 posiciones (porque Rol tiene 3 valores).
        EnumMap<Rol, String> permisos = new EnumMap<>(Rol.class);
        
        permisos.put(Rol.INVITADO, "Lectura");
        permisos.put(Rol.ADMIN, "Todo");
        
        // Al imprimirlo, lo verás SIEMPRE en el orden en el que se declararon en el Enum (ADMIN, USUARIO, INVITADO).
        System.out.println("Permisos indexados a O(1) puro array: " + permisos);
        System.out.println("");
    }

    // Enum para el reto
    public enum EstadoPedido { PENDIENTE, EN_PROCESO, ENVIADO, ENTREGADO }

    public static void ejercicio() {
        System.out.println("\n--- RETO 33: ESTADOS DEL SISTEMA ---");
        
        // TODO 1: Crea un EnumMap llamado 'tiemposEstimados' donde la KEY sea 'EstadoPedido' 
        // y el VALOR sea un Integer (horas estimadas para salir de ese estado a otro).
        // Recuerda pasarle EstadoPedido.class al constructor.
        EnumMap<EstadoPedido, Integer> tiemposEstimados = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Configura estos valores:
        // PENDIENTE -> 2
        // ENVIADO -> 48
        // EN_PROCESO -> 5
        // (Nota que los insertas desordenados).
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Imprime 'tiemposEstimados'. Comprobarás que mágicamente se han ordenado 
        // siguiendo el orden posicional en el que los 'EstadoPedido' están escritos en la clase madre.
        System.out.println("Tiempos por fases:");
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN DETALLADA ---
        boolean ok1 = tiemposEstimados != null;
        boolean ok2 = false;
        
        if (ok1) {
            Map.Entry<EstadoPedido, Integer> primerElemento = tiemposEstimados.entrySet().iterator().next();
            // ¡Magia! Aunque metimos "ENVIADO" como segundo PUT, el primer elemento DEBE ser PENDIENTE porque se ordenan solos por el peso del Enum.
            ok2 = primerElemento.getKey() == EstadoPedido.PENDIENTE;
        }

        if (!ok1) System.err.println("-> [TODO 1] Falló: EnumMap no instanciado.");
        if (ok1 && tiemposEstimados.size() != 3) System.err.println("-> [TODO 2] Falló: Faltan inserciones en el mapa.");
        if (ok1 && !ok2) System.err.println("-> [TODO 2/3] Falló: El orden natural del array interno no funcionó o falta PENDIENTE.");

        if (ok1 && ok2 && tiemposEstimados.size() == 3) {
            System.out.println(">> PERFECTO: Cuando modeles juegos de estado finitos o base de datos fijas, usa EnumMap. \033[0;32m [OK]\033[0m");
        }
    }
}
