package nivel13_magia_arboles;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * EJERCICIO 44 - CONSUMO DESTRUCTIVO (El Devorador de Árboles)
 * 
 * Objetivo: Ver de cerca cómo se implementa una Cola de Prioridad Estricta y No-Duplicada
 * usando un TreeMap y sus métodos pollFirstEntry y pollLastEntry.
 */
public class Ejercicio44_ConsumoDestructivo {

    public static void demostracion() {
        System.out.println("--- DEMO: DEVORANDO FACTURAS ---");
        NavigableMap<Integer, String> tareasPendientes = new TreeMap<>();
        tareasPendientes.put(3, "Baja Prioridad");
        tareasPendientes.put(1, "URGENTÍSIMO");
        tareasPendientes.put(2, "Prioridad Media");

        System.out.println("Cantidad inicial: " + tareasPendientes.size());

        // Arrancamos y devoramos la entrada "Más Próxima" o Menor.
        Map.Entry<Integer, String> consumida = tareasPendientes.pollFirstEntry();
        
        System.out.println("He consumido y DESTRUIDO la tarea: " + consumida.getValue());
        System.out.println("Cantidad residual: " + tareasPendientes.size());
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 44: EL WORKER (PROCESADOR DE TAREAS) ---");
        
        NavigableMap<Integer, String> colaMensajes = new TreeMap<>();
        colaMensajes.put(1001, "Enviar Email 1");
        colaMensajes.put(1005, "Enviar Email 5");
        colaMensajes.put(1003, "Enviar Email 3");
        colaMensajes.put(1002, "Enviar Email 2");

        // Queremos "imprimir/procesar" TODOS los correos en orden ascendente (1001, 1002, etc), 
        // DEJANDO EL MAPA LIMPIO (Vació absoluto) al finalizar.
        
        // TODO 1: Haz un bucle `while(!colaMensajes.isEmpty())`.
        // TODO 2: Dentro, llama a `pollFirstEntry()` y guárdalo en una variable `var entry`.
        // TODO 3: Añade cada string (`entry.getValue()`) a tu propia variable String llamada 'buzon'.
        //         Añádelas separadas por comas (ej: "Enviar Email 1,Enviar Email 2,...").
        
        String buzon = "";
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN DETALLADA ---
        boolean vacio = colaMensajes.isEmpty();
        boolean ordenado = buzon.startsWith("Enviar Email 1,") && buzon.contains("Enviar Email 5");

        if (!vacio) System.err.println("-> [TODO 1 / 2] Falló: No consumiste el árbol entero, quedaron restos (pollFirstEntry arranca el nodo).");
        if (vacio && !ordenado) System.err.println("-> [TODO 3] Falló: Acumulaste mal los strings o no siguieron un orden ascendente estricto.");

        if (vacio && ordenado) {
            System.out.println(">> PERFECTO: Cuando veas a alguien usando PriorityQueue para objetos que ya tienen un Key único natural, lánzales un pollFirstEntry() a la cara. \033[0;32m [OK]\033[0m");
        }
    }
}
