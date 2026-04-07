package nivel11_anatomia_llave;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * EJERCICIO 38 - EL PELIGRO DE MUTACIÓN (MEMORY LEAK)
 * 
 * Objetivo: Comprobar el letal error arquitectónico de 
 * modificar los campos clave de un objeto DESPUÉS de haberlo insertado
 * como Llave en un Hash.
 */
public class Ejercicio38_MutacionPeligrosa {

    // 💣 Clase Venenosa: Sus campos clave NO son final. Se pueden mutar.
    static class MutableKey {
        int id;
        public MutableKey(int id) { this.id = id; }
        
        public void setId(int id) { this.id = id; } // El Mutador de la Muerte

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MutableKey that = (MutableKey) o;
            return id == that.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id); // Genera hash basado en el estado actual de ID
        }
    }

    public static void demostracion() {
        System.out.println("--- DEMO: MUTANDO EN EL VACÍO ---");
        Map<MutableKey, String> sistema = new HashMap<>();
        
        MutableKey boss = new MutableKey(500); // Hash será = hashCode(500)
        sistema.put(boss, "CEO_Confidencial");

        // El programador junior, ignorante, edita al jefe. Altera su campo clave (Id).
        boss.setId(999); 

        // Ahora, el programador intenta recuperar el dato...
        System.out.println("Buscando con el mismo puntero boss mutado: " + sistema.get(boss)); 
        // ¿Qué pasó? NULL. Al hacer get(boss), se recalculó su hash basado en 999. Buscó en la caja Hash(999). 
        // ¡Pero el objeto se guardó originalmente físicamente en la caja Hash(500)! 
        // EL DATO QUEDÓ HUÉRFANO Y ATRAPADO EN MEMORIA PARA SIEMPRE DENTRO DEL MAPA. 
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 38: ATRAPANDO AL HUÉRFANO ---");
        
        Map<MutableKey, Integer> inventarioGemas = new HashMap<>();
        
        // TODO 1: Crea un objeto MutableKey con id 10. Llámalo 'gema'.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Inserte 'gema' en el map con un valor de 1000 (dólares).
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Muta el id de 'gema' mediante gema.setId(20). 
        // Intenta hacer un remove(). Guarda el resultado en variable boolean 'sePudoBorrar' (si remove no lo halla, map size no decaerá).
        // Actualiza el Integer 'tamanioFinal'.
        boolean sePudoBorrar = true;
        Integer tamanioFinal = 0;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN DETALLADA ---
        boolean trampaAtrapada = tamanioFinal != null && tamanioFinal == 1; // Aunque remove falló o get falló, el HashMap SIGUE lleno.
        
        if (sePudoBorrar) System.err.println("-> [TODO 3] Falló: ¿Eliminaste sin mutar antes? Ojo, la trampa no funcionó.");
        else if (!trampaAtrapada) System.err.println("-> [TODO 3] Falló: TamanioFinal debía ser 1. El objeto quedó aislado en el limbo.");

        if (!sePudoBorrar && trampaAtrapada) {
            System.out.println(">> PERFECTO: Regla de Oro. Las properties usadas en el equals/hashCode en una clase Entity NUNCA se permiten alterar mediante setters una vez instanciadas. Usa FINAL de ser posible.\033[0;32m [OK]\033[0m");
        }
    }
}
