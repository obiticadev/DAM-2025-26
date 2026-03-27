package nivel6_experto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import modelos.Aventurero;

public class RetoFinal_ElSindicatoDeAventureros {

    public static void demostracion() {
        System.out.println("--- EL SINDICATO DE AVENTUREROS ---");
        System.out.println("No hay demostración aquí. Estás solo.");
        System.out.println("Debes aglutinar todo lo aprendido en los niveles anteriores.");
        System.out.println("Pistas de oro:");
        System.out.println("1. Usa .filter(a -> a != null) ANTES de mirar propiedades si crees que hay Nulos.");
        System.out.println("2. .distinct() alivia elementos repetidos exactos.");
        System.out.println("3. .sorted(Comparator...) acepta encadenamientos thenComparing().");
        System.out.println("4. .collect(Collectors.toMap(...)) para forjar Mapas directos.");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 15: ORDENANDO EL CAOS ---");
        
        // --- BASE DE DATOS CORRUPTA (SIMULACIÓN MASIVA) ---
        List<Aventurero> baseDatosBruta = Arrays.asList(
            new Aventurero("Arthur", "Guerrero", 80, 500, true),
            null, // ¡DATO CORRUPTO!
            new Aventurero("Merlin", "Mago", 99, 1000, true),
            new Aventurero("Robin", "Arquero", 40, 500, false), // Muerto
            new Aventurero("Trivi", "Mago", -5, 0, true), // ¡NIVEL NEGATIVO (CORRUPTO)!
            new Aventurero("Conan", "Guerrero", 80, 500, true),
            new Aventurero("Merlin", "Mago", 99, 1000, true), // ¡REPETIDO!
            new Aventurero("Vivi", "Mago", 45, 1200, true),
            null
        );

        /* =========================================================================
         * TODO EL RETO MAESTRO:
         * Imagina que estás en una entrevista técnica para una FAANG.
         * Tienes que crear el pipeline PERFECTO y almacenarlo en la variable 
         * 'mapaEliteMagos', un diccionario que contenga SÓLO a la élite.
         * 
         * PASOS OBLIGATORIOS (En una sola directriz cruzada de Puntos):
         * 1. Extiende de 'baseDatosBruta.stream()'.
         * 2. Filtra que el Aventurero NO SEA NULL (Filtro Anti-Crash).
         * 3. Filtra a los Aventureros que tengan Niveles lógicos ( > 0 ).
         * 4. Usa un método intermedio de stream que elimine Copias Mágicamente (Pista: lo hace mirando Equals/Hashcode).
         * 5. Filtra para quedarte SÓLO con la clase "Mago".
         * 6. Filtra para quedarte SÓLO con los vivos.
         * 7. ORDENACIÓN MULTICRITERIO SÓLIDA: 
         *      - Principalmente, ordénalos por SU ORO de forma DESCENDENTE (El que más oro tenga vaya el primero).
         *      - Si hay empate en oro, desempatar de forma DESCENDENTE por su NIVEL.
         * 8. (TERMINAL): Recolectalos convirtiéndolos en un Diccionario (Map), 
         *    donde la Llave (Key) sea el Nombre, y el Valor (Value) sea la cantidad íntegra de ORO ganado.
         * ========================================================================= */
        
        Map<String, Double> mapaEliteMagos = null; // <- ESCRIBE AQUÍ TU MEGAPIPELINE (.stream().filter()...)
        
        

        // --- VALIDACIÓN EXTREMA ---
        if (mapaEliteMagos != null) {
            boolean tamañoCorrecto = mapaEliteMagos.size() == 2;
            boolean contieneAMerlinYVivi = mapaEliteMagos.containsKey("Merlin") && mapaEliteMagos.containsKey("Vivi");
            boolean merlinTieneMasOroQueViviEnValores = false;
            
            if(tamañoCorrecto && contieneAMerlinYVivi) {
                 if(mapaEliteMagos.get("Vivi") == 1200.0 && mapaEliteMagos.get("Merlin") == 1000.0) {
                     merlinTieneMasOroQueViviEnValores = true;
                 }
            }

            if (tamañoCorrecto && contieneAMerlinYVivi && merlinTieneMasOroQueViviEnValores) {
                System.out.println(">> ¡[ÉXITO TOTAL]! Has demostrado una comprensión Nivel 100 de Mapeo, Tratamiento Nulo, Filtrado Complejo y Recolección Estructural.\033[0;32m [OK]\033[0m");
                System.out.println("¡TE HAS CORONADO COMO UN AUTÉNTICO GURÚ DE JAVA COLLECTIONS Y STREAMS!");
            } else {
                System.err.println(">> [ERROR] Pipeline erróneo. Comprueba que:");
                System.err.println("1. Solo haya 2 magos vivos y sin clon en el mapa final.");
                System.err.println("2. Los valores del Diccionario sean dobles que registren su oro exacto (Ej: Vivi -> 1200.0).");
            }
        } else {
            System.err.println(">> [ERROR] Tienes que inicializar mapaEliteMagos.");
        }
    }
}
