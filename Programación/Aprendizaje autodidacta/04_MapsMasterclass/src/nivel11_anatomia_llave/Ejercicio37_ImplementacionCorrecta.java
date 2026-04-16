package nivel11_anatomia_llave;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * EJERCICIO 37 - LA LLAVE PERFECTA (HashCode y Equals Inmutables)
 * 
 * Objetivo: Redimir la tragedia anterior construyendo una estructura
 * impenetrable orientada explícitamente a ser Key en los mapas. 
 * Mostrando una Clase Custom y demostrando por contraparte el uso de REST Objects (Record).
 */
public class Ejercicio37_ImplementacionCorrecta {

    // ✅ Clase Perfecta 1 (Estilo Java 8)
    static final class DocumentoIdentidad {
        private final String pais; // El final asegura inmutabilidad. CRITICO.
        private final String numero;

        public DocumentoIdentidad(String pais, String numero) {
            this.pais = pais;
            this.numero = numero;
        }

        // --- MÁGIA AUTO-GENERADA POR EL IDE (Alt+Insert) ---
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DocumentoIdentidad that = (DocumentoIdentidad) o;
            return pais.equals(that.pais) && numero.equals(that.numero);
        }

        @Override
        public int hashCode() {
            return Objects.hash(pais, numero);
        }
    }

    // ✅ Clase Perfecta 2 (Estilo Java 14+)
    // Un "record" autogenera internamente atributos final, equals y hashCode basados en sus paámetros.
    record DniRecord(String numero) {}

    public static void demostracion() {
        System.out.println("--- DEMO: LA LLAVE DE ORO ---");
        Map<DocumentoIdentidad, String> padron = new HashMap<>();
        padron.put(new DocumentoIdentidad("ES", "1234F"), "Ana García");

        // Creamos instancia distinta, un "clon".
        DocumentoIdentidad ptrBusqueda = new DocumentoIdentidad("ES", "1234F");

        // FUNCIONA PORQUE:
        // 1. Objects.hash() devuelve el mismo hash para ("ES", "1234F"). Van ambos al mismo Index del Array del Mapa.
        // 2. equals() da True al compararse en el index.
        System.out.println("Resultado búsqueda sana: " + padron.get(ptrBusqueda));
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 37: EL MAPEO PERFECTO DE REGISTROS ---");
        
        // TODO 1: Vamos a usar el modernísimo concepto "Record DniRecord" (Declarado arriba).
        // Crea un Map<DniRecord, String> llamado 'registroCivil'.
        Map<DniRecord, String> registroCivil = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Ingresa tres personas creando los DniRecord en el acto dentro del put().
        // Ej: put(new DniRecord("777"), "Agente 007");
        // Haz "111" -> "Pepe", "222" -> "Juan", "333" -> "Silvia"
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Crea un buscador totalmente independiente: DniRecord buscar222 = new DniRecord("222");
        // Elimina (.remove) a esa persona del mapa usando la llave recién instanciada.
        // Si el Hash/Equals subyacente del Record funciona bien (¡y lo hace!), lo destruirá del mapa.
        DniRecord buscar222 = new DniRecord("222");
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN DETALLADA ---
        boolean ok1 = registroCivil != null;
        boolean sizeAdecuado = ok1 && registroCivil.size() == 2;
        boolean seBorroElCorrecto = ok1 && !registroCivil.containsValue("Juan") && registroCivil.containsValue("Pepe");

        if (!ok1) System.err.println("-> [TODO 1] Falló: Mapa no inicializado.");
        if (ok1 && !sizeAdecuado) System.err.println("-> [TODO 2/3] Falló: Faltan inserciones o el borrar no impactó. El Map size debería ser 2 (3 creados, 1 borrado).");
        if (ok1 && !seBorroElCorrecto) System.err.println("-> [TODO 3] Falló: Borraste el archivo equivocado. ¿Instanciaste DniRecord('222')?");

        if (ok1 && sizeAdecuado && seBorroElCorrecto) {
            System.out.println(">> PERFECTO: Cuando crees APIS para empresas, usa siempre Objetos con HashCode estricto o un glorioso Record. \033[0;32m [OK]\033[0m");
        }
    }
}
