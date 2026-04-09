package nivel11_anatomia_llave;

import java.util.HashMap;
import java.util.Map;

/**
 * EJERCICIO 36 - ROMPIENDO EL CONTRATO DE HASH
 * 
 * Objetivo: Experimentar en primera persona cómo la ausencia de hashCode
 * destruye la utilidad de un diccionario haciendo que los getters devuelvan NULL
 * aunque estés usando "objetos lógicamente idénticos".
 */
public class Ejercicio36_RomperContrato {

    // 💣 Clase trampa: Tiene equals pero NO tiene hashCode.
    static class LlaveRota {
        String token;
        public LlaveRota(String token) { this.token = token; }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            LlaveRota otra = (LlaveRota) obj;
            return token.equals(otra.token);
        }
    }

    public static void demostracion() {
        System.out.println("--- DEMO: EL CAOS DE HASHCODE ---");
        Map<LlaveRota, String> accesos = new HashMap<>();
        
        LlaveRota key1 = new LlaveRota("ABC-123");
        accesos.put(key1, "Usuario SuperAdmin");

        // Creamos una segunda llave idéntica en semántica (su equals() da true con la primera)
        LlaveRota keyBuscadora = new LlaveRota("ABC-123");

        System.out.println("¿Es equals la llave que tengo con la del map?: " + keyBuscadora.equals(key1)); 
        System.out.println("Buscando usuario en mapa... -> " + accesos.get(keyBuscadora));
        System.out.println("(Dio NULL porque como no sobreescribimos hashCode, calcularon hashes basados en memoria física distinta, y el HashMap miró en la cubeta equivocada).");
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 36: DEMOSTRAR LA FALLA ---");
        
        // TODO 1: Vamos a instanciar el mapa con LlaveRota
        Map<LlaveRota, Integer> puntuaciones = new HashMap<>();
        
        // TODO 2: Crea un jugador y mételo en el mapa.
        LlaveRota j1 = new LlaveRota("JUG-1");
        // Métele un put(j1, 100);
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Crea una instancia idéntica LlaveRota buscador = new LlaveRota("JUG-1");
        // Y trata de recuperar el Integer (.get) usando este buscador. Guárdalo en una variable.
        Integer puntosRecuperados = 100; // Sobreescríbelo con el getter.
        Integer sizeDelMapa = 0;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 4: Además del getter, intenta BORRAR al jugador usando el `buscador`. (Hará un remove).
        // Y actualiza el valor de sizeDelMapa llamando a puntuaciones.size().
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN DETALLADA ---
        boolean trampaGet = puntosRecuperados == null;
        boolean trampaRemove = sizeDelMapa == 1; // El remove falló, por lo que el mapa sigue en tamaño 1.

        if (!trampaGet) System.err.println("-> [TODO 3] Falló: Acaso no hiciste el .get()? Debería haberte devuelto un null catastrófico debido al bug de la clase.");
        if (!trampaRemove) System.err.println("-> [TODO 4] Falló: Acaso el tamaño te bajó a 0? El remove tampoco debería haberlo encontrado en la caja de hash.");

        if (trampaGet && trampaRemove) {
            System.out.println(">> PERFECTO: Has presenciado el fallo silencioso del HashCode ausente. \033[0;32m [OK]\033[0m");
        }
    }
}
