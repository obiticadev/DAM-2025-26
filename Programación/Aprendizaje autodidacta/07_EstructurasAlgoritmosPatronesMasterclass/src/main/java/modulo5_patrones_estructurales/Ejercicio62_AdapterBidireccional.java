package modulo5_patrones_estructurales;

/**
 * ============================================================================
 * EJERCICIO 62: Adapter — Bidireccional XML ↔ JSON
 * ============================================================================
 * 📚 Teoría: teoria/05_Patrones_Estructurales.md - Sección 5.2
 *
 * CONTEXTO:
 * Dos sistemas necesitan comunicarse: uno envía datos en formato XML,
 * otro en JSON. El Adapter bidireccional traduce en AMBAS direcciones.
 *
 * Implementa:
 * - Interfaz XmlService: enviarXml(String xml), recibirXml().
 * - Interfaz JsonService: enviarJson(String json), recibirJson().
 * - XmlToJsonAdapter: implementa JsonService, delega en XmlService.
 * - JsonToXmlAdapter: implementa XmlService, delega en JsonService.
 *
 * "Conversiones" simuladas con String manipulation simple:
 * - XML: "<nombre>Alice</nombre>" → JSON: "{"nombre": "Alice"}"
 *
 * RESTRICCIONES:
 * - No usar librerías de parsing XML/JSON reales.
 * - Simular la conversión con manipulación de Strings.
 * - Demostrar que el mismo dato viaja en ambos formatos.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio62_AdapterBidireccional {

    // TODO 1: Definir interfaz XmlService con:
    //         void enviarXml(String xml)
    //         String recibirXml()

    // TODO 2: Definir interfaz JsonService con:
    //         void enviarJson(String json)
    //         String recibirJson()

    // TODO 3: Implementar SistemaXml (implementa XmlService):
    //         - Almacena un String datos internamente.
    //         - enviarXml(): guarda el xml y imprime "[XML] Recibido: <xml>"
    //         - recibirXml(): retorna los datos almacenados en formato XML.

    // TODO 4: Implementar SistemaJson (implementa JsonService):
    //         - Almacena un String datos internamente.
    //         - enviarJson(): guarda el json y imprime "[JSON] Recibido: <json>"
    //         - recibirJson(): retorna los datos en formato JSON.

    // TODO 5: Implementar XmlToJsonAdapter (implementa JsonService):
    //         - Tiene un campo XmlService.
    //         - enviarJson(json): convierte JSON→XML y llama xmlService.enviarXml().
    //         - recibirJson(): llama xmlService.recibirXml() y convierte XML→JSON.
    //         Conversión simplificada:
    //           xmlToJson: reemplaza "<tag>" por "\"tag\":" y "</tag>" por ""
    //           jsonToXml: operación inversa.

    // TODO 6: Implementar JsonToXmlAdapter (implementa XmlService):
    //         Inverso del anterior.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 62: Adapter Bidireccional ===\n");

        // TODO 7: Demostrar comunicación bidireccional:
        //         SistemaXml xml = new SistemaXml();
        //         SistemaJson json = new SistemaJson();
        //
        //         // Sistema JSON quiere hablar con sistema XML
        //         JsonService adaptado1 = new XmlToJsonAdapter(xml);
        //         adaptado1.enviarJson("{\"nombre\": \"Alice\"}");
        //         System.out.println("Respuesta: " + adaptado1.recibirJson());
        //
        //         // Sistema XML quiere hablar con sistema JSON
        //         XmlService adaptado2 = new JsonToXmlAdapter(json);
        //         adaptado2.enviarXml("<edad>25</edad>");
        //         System.out.println("Respuesta: " + adaptado2.recibirXml());

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 62 ===");
    }
}
