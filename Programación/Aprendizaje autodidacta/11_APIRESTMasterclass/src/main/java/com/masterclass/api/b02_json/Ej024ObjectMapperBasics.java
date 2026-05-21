package com.masterclass.api.b02_json;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Ejercicio 024 · ObjectMapper: serializar y deserializar.
 *
 * <p>Teoría: {@code teoria/02_JSON_y_Jackson.md} (sección 2.2).
 */
public final class Ej024ObjectMapperBasics {

    /** DTO de ejemplo (los componentes son el contrato). */
    public record Cliente(Long id, String nombre) {
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Ej024ObjectMapperBasics() {
    }

    /**
     * Serializa un Cliente a JSON.
     *
     * @param c cliente a convertir
     * @return cadena JSON, p.ej. {@code {"id":1,"nombre":"Ana"}}
     * @throws RuntimeException si Jackson falla (envuelve la checked)
     */
    public static String toJson(Cliente c) {
        // TODO 1: abre un try alrededor de la serialización.
        // TODO 2: usa MAPPER.writeValueAsString(c).
        // TODO 3: devuelve la cadena resultante.
        // TODO 4: captura JsonProcessingException.
        // TODO 5: relánzala envuelta en una RuntimeException (no la silencies).
        return "";
    }

    /**
     * Deserializa JSON a un Cliente.
     *
     * @param json texto JSON con id y nombre
     * @return instancia de Cliente
     * @throws RuntimeException si el JSON es inválido
     */
    public static Cliente fromJson(String json) {
        // TODO 6: abre un try alrededor de la deserialización.
        // TODO 7: usa MAPPER.readValue(json, Cliente.class).
        // TODO 8: devuelve el objeto poblado.
        // TODO 9: captura JsonProcessingException.
        // TODO 10: relánzala como RuntimeException con el mensaje original.
        return null;
    }

    public static void main(String[] args) {
        String j = toJson(new Cliente(1L, "Ana"));
        System.out.println(j + " -> " + fromJson(j));
    }

    /**
     * Reto Extra 1: Serialización estética (Pretty Printing).
     * Serializa el cliente con sangrías e identación para que sea legible por humanos.
     *
     * @param c cliente
     * @return JSON formateado estéticamente
     */
    public static String serializarBonito(Cliente c) {
        // TODO extra: utiliza MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(c) manejando la excepción checked
        return null;
    }

    /**
     * Reto Extra 2: Deserialización genérica tipo-segura.
     * Deserializa una cadena JSON a cualquier tipo de clase indicado por parámetro.
     *
     * @param json   texto JSON
     * @param clase  clase de destino
     * @param <T>    tipo genérico de retorno
     * @return instancia de la clase de destino deserializada
     */
    public static <T> T deserializarGenerico(String json, Class<T> clase) {
        // TODO extra: utiliza MAPPER.readValue(json, clase) manejando la excepción checked
        return null;
    }

    /**
     * Reto Extra 3: Validación estructural JSON.
     * Valida sintácticamente un texto para verificar si es un JSON estructurado correcto mediante Jackson.
     *
     * @param json cadena de texto
     * @return true si es un JSON sintácticamente correcto, false si está corrupto o es nulo
     */
    public static boolean esJsonValido(String json) {
        // TODO extra: intenta realizar MAPPER.readTree(json) y retorna true; si lanza excepción, retorna false
        return false;
    }

    /**
     * Reto Extra 4: Tolerancia a propiedades desconocidas.
     * Deserializa un JSON a Cliente tolerando propiedades adicionales que no estén presentes en el DTO Cliente
     * sin lanzar excepciones.
     *
     * @param json JSON con campos extra (ej. {"id":1,"nombre":"Ana","email":"ana@correo.com"})
     * @return objeto Cliente deserializado omitiendo los campos sobrantes
     */
    public static Cliente deserializarIgnorandoCamposDesconocidos(String json) {
        // TODO extra: configura un ObjectMapper local para desactivar DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES y lee el Cliente
        return null;
    }

    /**
     * Reto Extra 5: Serialización binaria.
     * Serializa el DTO Cliente a una matriz de bytes en codificación UTF-8.
     *
     * @param c cliente
     * @return array de bytes serializado
     */
    public static byte[] serializarABytes(Cliente c) {
        // TODO extra: utiliza MAPPER.writeValueAsBytes(c) manejando la excepción checked
        return null;
    }

    /**
     * Reto Extra 6: Deserialización binaria.
     * Deserializa el DTO Cliente a partir de una matriz de bytes.
     *
     * @param bytes array de bytes con codificación UTF-8
     * @return cliente deserializado
     */
    public static Cliente deserializarDesdeBytes(byte[] bytes) {
        // TODO extra: utiliza MAPPER.readValue(bytes, Cliente.class) manejando la excepción checked
        return null;
    }

    /**
     * Reto Extra 7: Simulación de actualización parcial (Patch).
     * Lee propiedades parciales de un JSON y las mezcla con un objeto Cliente existente,
     * produciendo un nuevo Cliente actualizado (dado que Cliente es un Record inmutable).
     *
     * @param json      JSON con campos parciales (ej. {"nombre":"Ana María"})
     * @param existente cliente actual base
     * @return nueva instancia de Cliente con los datos fusionados
     */
    public static Cliente actualizarConMap(String json, Cliente existente) {
        // TODO extra: deserializa el JSON a un Map<String, Object>, extrae id/nombre usando el mapa si existen, o el existente si no, y retorna el nuevo Cliente
        return null;
    }

    /**
     * Reto Extra 8: Conversión de tipos dinámicos de Jackson.
     * Convierte cualquier objeto origen a un tipo destino usando Jackson (simulando mapeo de DTOs).
     *
     * @param origen       objeto de origen (ej. un mapa o DTO compatible)
     * @param claseDestino clase a la que queremos convertirlo
     * @param <T>          tipo de retorno
     * @return objeto convertido
     */
    public static <T> T convertirEntreTipos(Object origen, Class<T> claseDestino) {
        // TODO extra: utiliza MAPPER.convertValue(origen, claseDestino) para realizar la conversión de tipos
        return null;
    }

    /**
     * Reto Extra 9: Verificación de indentación.
     * Serializa el cliente de forma bonita y comprueba si la salida contiene saltos de línea.
     *
     * @param c cliente
     * @return true si la serialización es identada (tiene saltos de línea)
     */
    public static boolean serializarConIdentacionYComprobar(Cliente c) {
        // TODO extra: genera el JSON bonito y valida si contiene algún carácter '\n' o '\r'
        return false;
    }

    /**
     * Reto Extra 10: Persistencia temporal en disco de JSON.
     * Escribe el cliente en un archivo temporal en disco y luego lo vuelve a leer para comprobar la integridad del proceso.
     *
     * @param c cliente
     * @return el cliente recuperado desde el archivo
     */
    public static Cliente escribirYLeerDeArchivoTemporal(Cliente c) {
        // TODO extra: crea un archivo temporal con File.createTempFile, escribe c usando MAPPER.writeValue, lee de vuelta y bórralo
        return null;
    }

}
